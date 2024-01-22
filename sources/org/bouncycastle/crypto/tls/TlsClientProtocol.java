package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class TlsClientProtocol extends TlsProtocol {
    public TlsAuthentication authentication;
    public CertificateRequest certificateRequest;
    public CertificateStatus certificateStatus;
    public TlsKeyExchange keyExchange;
    public m m;
    public byte[] selectedSessionID;
    public TlsClient tlsClient;

    public TlsClientProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        super(inputStream, outputStream, secureRandom);
        this.tlsClient = null;
        this.m = null;
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    public TlsClientProtocol(SecureRandom secureRandom) {
        super(secureRandom);
        this.tlsClient = null;
        this.m = null;
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    public a a() {
        return this.m;
    }

    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    public void cleanupHandshake() {
        super.cleanupHandshake();
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    public void connect(TlsClient tlsClient) throws IOException {
        SessionParameters exportSessionParameters;
        if (tlsClient == null) {
            throw new IllegalArgumentException("'tlsClient' cannot be null");
        }
        if (this.tlsClient != null) {
            throw new IllegalStateException("'connect' can only be called once");
        }
        this.tlsClient = tlsClient;
        SecurityParameters securityParameters = new SecurityParameters();
        this.securityParameters = securityParameters;
        securityParameters.f14853a = 1;
        this.m = new m(this.secureRandom, this.securityParameters);
        this.securityParameters.g = TlsProtocol.createRandomBlock(tlsClient.shouldUseGMTUnixTime(), this.m.getNonceRandomGenerator());
        this.tlsClient.init(this.m);
        this.d.m(this.m);
        TlsSession sessionToResume = tlsClient.getSessionToResume();
        if (sessionToResume != null && sessionToResume.isResumable() && (exportSessionParameters = sessionToResume.exportSessionParameters()) != null) {
            this.tlsSession = sessionToResume;
            this.sessionParameters = exportSessionParameters;
        }
        sendClientHelloMessage();
        this.connection_state = (short) 1;
        blockForHandshake();
    }

    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    public TlsContext getContext() {
        return this.m;
    }

    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    public TlsPeer getPeer() {
        return this.tlsClient;
    }

    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    public void handleHandshakeMessage(short s, ByteArrayInputStream byteArrayInputStream) throws IOException {
        TlsCredentials clientCredentials;
        Certificate certificate;
        if (this.resumedSession) {
            if (s != 20 || this.connection_state != 2) {
                throw new TlsFatalAlert((short) 10);
            }
            processFinishedMessage(byteArrayInputStream);
            this.connection_state = (short) 15;
            sendChangeCipherSpecMessage();
            sendFinishedMessage();
            this.connection_state = (short) 13;
            completeHandshake();
        } else if (s == 0) {
            TlsProtocol.assertEmpty(byteArrayInputStream);
            if (this.connection_state == 16) {
                refuseRenegotiation();
            }
        } else if (s == 2) {
            if (this.connection_state != 1) {
                throw new TlsFatalAlert((short) 10);
            }
            receiveServerHelloMessage(byteArrayInputStream);
            this.connection_state = (short) 2;
            this.d.n();
            applyMaxFragmentLengthExtension();
            if (this.resumedSession) {
                this.securityParameters.f = Arrays.clone(this.sessionParameters.getMasterSecret());
                this.d.t(getPeer().getCompression(), getPeer().getCipher());
                return;
            }
            invalidateSession();
            byte[] bArr = this.selectedSessionID;
            if (bArr.length > 0) {
                this.tlsSession = new q(bArr, null);
            }
        } else if (s == 4) {
            if (this.connection_state != 13) {
                throw new TlsFatalAlert((short) 10);
            }
            if (!this.expectSessionTicket) {
                throw new TlsFatalAlert((short) 10);
            }
            invalidateSession();
            receiveNewSessionTicketMessage(byteArrayInputStream);
            this.connection_state = (short) 14;
        } else if (s == 20) {
            short s2 = this.connection_state;
            if (s2 != 13) {
                if (s2 != 14) {
                    throw new TlsFatalAlert((short) 10);
                }
            } else if (this.expectSessionTicket) {
                throw new TlsFatalAlert((short) 10);
            }
            processFinishedMessage(byteArrayInputStream);
            this.connection_state = (short) 15;
            completeHandshake();
        } else if (s == 22) {
            if (this.connection_state != 4) {
                throw new TlsFatalAlert((short) 10);
            }
            if (!this.allowCertificateStatus) {
                throw new TlsFatalAlert((short) 10);
            }
            this.certificateStatus = CertificateStatus.parse(byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            this.connection_state = (short) 5;
        } else if (s == 23) {
            if (this.connection_state != 2) {
                throw new TlsFatalAlert((short) 10);
            }
            handleSupplementalData(TlsProtocol.readSupplementalDataMessage(byteArrayInputStream));
        } else {
            switch (s) {
                case 11:
                    short s3 = this.connection_state;
                    if (s3 == 2) {
                        handleSupplementalData(null);
                    } else if (s3 != 3) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    this.peerCertificate = Certificate.parse(byteArrayInputStream);
                    TlsProtocol.assertEmpty(byteArrayInputStream);
                    Certificate certificate2 = this.peerCertificate;
                    if (certificate2 == null || certificate2.isEmpty()) {
                        this.allowCertificateStatus = false;
                    }
                    this.keyExchange.processServerCertificate(this.peerCertificate);
                    TlsAuthentication authentication = this.tlsClient.getAuthentication();
                    this.authentication = authentication;
                    authentication.notifyServerCertificate(this.peerCertificate);
                    this.connection_state = (short) 4;
                    return;
                case 12:
                    short s4 = this.connection_state;
                    if (s4 == 2) {
                        handleSupplementalData(null);
                    } else if (s4 != 3) {
                        if (s4 != 4 && s4 != 5) {
                            throw new TlsFatalAlert((short) 10);
                        }
                        this.keyExchange.processServerKeyExchange(byteArrayInputStream);
                        TlsProtocol.assertEmpty(byteArrayInputStream);
                        this.connection_state = (short) 6;
                        return;
                    }
                    this.keyExchange.skipServerCredentials();
                    this.authentication = null;
                    this.keyExchange.processServerKeyExchange(byteArrayInputStream);
                    TlsProtocol.assertEmpty(byteArrayInputStream);
                    this.connection_state = (short) 6;
                    return;
                case 13:
                    short s5 = this.connection_state;
                    if (s5 == 4 || s5 == 5) {
                        this.keyExchange.skipServerKeyExchange();
                    } else if (s5 != 6) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    if (this.authentication == null) {
                        throw new TlsFatalAlert((short) 40);
                    }
                    this.certificateRequest = CertificateRequest.parse(getContext(), byteArrayInputStream);
                    TlsProtocol.assertEmpty(byteArrayInputStream);
                    this.keyExchange.validateCertificateRequest(this.certificateRequest);
                    TlsUtils.k(this.d.i(), this.certificateRequest.getSupportedSignatureAlgorithms());
                    this.connection_state = (short) 7;
                    return;
                case 14:
                    switch (this.connection_state) {
                        case 2:
                            handleSupplementalData(null);
                        case 3:
                            this.keyExchange.skipServerCredentials();
                            this.authentication = null;
                        case 4:
                        case 5:
                            this.keyExchange.skipServerKeyExchange();
                            break;
                        case 6:
                        case 7:
                            break;
                        default:
                            throw new TlsFatalAlert((short) 10);
                    }
                    TlsProtocol.assertEmpty(byteArrayInputStream);
                    this.connection_state = (short) 8;
                    this.d.i().sealHashAlgorithms();
                    Vector clientSupplementalData = this.tlsClient.getClientSupplementalData();
                    if (clientSupplementalData != null) {
                        sendSupplementalDataMessage(clientSupplementalData);
                    }
                    this.connection_state = (short) 9;
                    CertificateRequest certificateRequest = this.certificateRequest;
                    if (certificateRequest == null) {
                        this.keyExchange.skipClientCredentials();
                        clientCredentials = null;
                    } else {
                        clientCredentials = this.authentication.getClientCredentials(certificateRequest);
                        TlsKeyExchange tlsKeyExchange = this.keyExchange;
                        if (clientCredentials == null) {
                            tlsKeyExchange.skipClientCredentials();
                            certificate = Certificate.EMPTY_CHAIN;
                        } else {
                            tlsKeyExchange.processClientCredentials(clientCredentials);
                            certificate = clientCredentials.getCertificate();
                        }
                        sendCertificateMessage(certificate);
                    }
                    this.connection_state = (short) 10;
                    sendClientKeyExchangeMessage();
                    this.connection_state = (short) 11;
                    if (TlsUtils.isSSL(getContext())) {
                        TlsProtocol.establishMasterSecret(getContext(), this.keyExchange);
                    }
                    TlsHandshakeHash o = this.d.o();
                    this.securityParameters.i = TlsProtocol.getCurrentPRFHash(getContext(), o, null);
                    if (!TlsUtils.isSSL(getContext())) {
                        TlsProtocol.establishMasterSecret(getContext(), this.keyExchange);
                    }
                    this.d.t(getPeer().getCompression(), getPeer().getCipher());
                    if (clientCredentials != null && (clientCredentials instanceof TlsSignerCredentials)) {
                        TlsSignerCredentials tlsSignerCredentials = (TlsSignerCredentials) clientCredentials;
                        SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(getContext(), tlsSignerCredentials);
                        sendCertificateVerifyMessage(new DigitallySigned(signatureAndHashAlgorithm, tlsSignerCredentials.generateCertificateSignature(signatureAndHashAlgorithm == null ? this.securityParameters.getSessionHash() : o.getFinalHash(signatureAndHashAlgorithm.getHash()))));
                        this.connection_state = (short) 12;
                    }
                    sendChangeCipherSpecMessage();
                    sendFinishedMessage();
                    this.connection_state = (short) 13;
                    return;
                default:
                    throw new TlsFatalAlert((short) 10);
            }
        }
    }

    public void handleSupplementalData(Vector vector) throws IOException {
        this.tlsClient.processServerSupplementalData(vector);
        this.connection_state = (short) 3;
        TlsKeyExchange keyExchange = this.tlsClient.getKeyExchange();
        this.keyExchange = keyExchange;
        keyExchange.init(getContext());
    }

    public void receiveNewSessionTicketMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        NewSessionTicket parse = NewSessionTicket.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        this.tlsClient.notifyNewSessionTicket(parse);
    }

    public void receiveServerHelloMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        TlsSession tlsSession;
        ProtocolVersion readVersion = TlsUtils.readVersion(byteArrayInputStream);
        if (readVersion.isDTLS()) {
            throw new TlsFatalAlert((short) 47);
        }
        if (!readVersion.equals(this.d.l())) {
            throw new TlsFatalAlert((short) 47);
        }
        if (!readVersion.isEqualOrEarlierVersionOf(getContext().getClientVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        this.d.x(readVersion);
        a().d(readVersion);
        this.tlsClient.notifyServerVersion(readVersion);
        this.securityParameters.h = TlsUtils.readFully(32, byteArrayInputStream);
        byte[] readOpaque8 = TlsUtils.readOpaque8(byteArrayInputStream);
        this.selectedSessionID = readOpaque8;
        if (readOpaque8.length > 32) {
            throw new TlsFatalAlert((short) 47);
        }
        this.tlsClient.notifySessionID(readOpaque8);
        byte[] bArr = this.selectedSessionID;
        boolean z = false;
        this.resumedSession = bArr.length > 0 && (tlsSession = this.tlsSession) != null && Arrays.areEqual(bArr, tlsSession.getSessionID());
        int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
        if (!Arrays.contains(this.offeredCipherSuites, readUint16) || readUint16 == 0 || CipherSuite.isSCSV(readUint16) || !TlsUtils.isValidCipherSuiteForVersion(readUint16, getContext().getServerVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        this.tlsClient.notifySelectedCipherSuite(readUint16);
        short readUint8 = TlsUtils.readUint8(byteArrayInputStream);
        if (!Arrays.contains(this.offeredCompressionMethods, readUint8)) {
            throw new TlsFatalAlert((short) 47);
        }
        this.tlsClient.notifySelectedCompressionMethod(readUint8);
        Hashtable readExtensions = TlsProtocol.readExtensions(byteArrayInputStream);
        this.serverExtensions = readExtensions;
        if (readExtensions != null) {
            Enumeration keys = readExtensions.keys();
            while (keys.hasMoreElements()) {
                Integer num = (Integer) keys.nextElement();
                if (!num.equals(TlsProtocol.EXT_RenegotiationInfo) && TlsUtils.getExtensionData(this.clientExtensions, num) == null) {
                    throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                }
            }
        }
        byte[] extensionData = TlsUtils.getExtensionData(this.serverExtensions, TlsProtocol.EXT_RenegotiationInfo);
        if (extensionData != null) {
            this.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        this.tlsClient.notifySecureRenegotiation(this.secure_renegotiation);
        Hashtable hashtable = this.clientExtensions;
        Hashtable hashtable2 = this.serverExtensions;
        if (this.resumedSession) {
            if (readUint16 != this.sessionParameters.getCipherSuite() || readUint8 != this.sessionParameters.getCompressionAlgorithm()) {
                throw new TlsFatalAlert((short) 47);
            }
            hashtable = null;
            hashtable2 = this.sessionParameters.readServerExtensions();
        }
        SecurityParameters securityParameters = this.securityParameters;
        securityParameters.b = readUint16;
        securityParameters.c = readUint8;
        if (hashtable2 != null) {
            boolean hasEncryptThenMACExtension = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable2);
            if (hasEncryptThenMACExtension && !TlsUtils.isBlockCipherSuite(readUint16)) {
                throw new TlsFatalAlert((short) 47);
            }
            SecurityParameters securityParameters2 = this.securityParameters;
            securityParameters2.n = hasEncryptThenMACExtension;
            securityParameters2.o = TlsExtensionsUtils.hasExtendedMasterSecretExtension(hashtable2);
            this.securityParameters.l = processMaxFragmentLengthExtension(hashtable, hashtable2, (short) 47);
            this.securityParameters.m = TlsExtensionsUtils.hasTruncatedHMacExtension(hashtable2);
            this.allowCertificateStatus = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(hashtable2, TlsExtensionsUtils.EXT_status_request, (short) 47);
            if (!this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(hashtable2, TlsProtocol.EXT_SessionTicket, (short) 47)) {
                z = true;
            }
            this.expectSessionTicket = z;
        }
        if (hashtable != null) {
            this.tlsClient.processServerExtensions(hashtable2);
        }
        this.securityParameters.d = TlsProtocol.getPRFAlgorithm(getContext(), this.securityParameters.getCipherSuite());
        this.securityParameters.e = 12;
    }

    public void sendCertificateVerifyMessage(DigitallySigned digitallySigned) throws IOException {
        TlsProtocol.a aVar = new TlsProtocol.a(this, (short) 15);
        digitallySigned.encode(aVar);
        aVar.a();
    }

    public void sendClientHelloMessage() throws IOException {
        byte[] bArr;
        SessionParameters sessionParameters;
        this.d.x(this.tlsClient.getClientHelloRecordLayerVersion());
        ProtocolVersion clientVersion = this.tlsClient.getClientVersion();
        if (clientVersion.isDTLS()) {
            throw new TlsFatalAlert((short) 80);
        }
        a().b(clientVersion);
        byte[] bArr2 = TlsUtils.EMPTY_BYTES;
        TlsSession tlsSession = this.tlsSession;
        if (tlsSession == null || (bArr = tlsSession.getSessionID()) == null || bArr.length > 32) {
            bArr = bArr2;
        }
        boolean isFallback = this.tlsClient.isFallback();
        this.offeredCipherSuites = this.tlsClient.getCipherSuites();
        this.offeredCompressionMethods = this.tlsClient.getCompressionMethods();
        if (bArr.length <= 0 || (sessionParameters = this.sessionParameters) == null || (Arrays.contains(this.offeredCipherSuites, sessionParameters.getCipherSuite()) && Arrays.contains(this.offeredCompressionMethods, this.sessionParameters.getCompressionAlgorithm()))) {
            bArr2 = bArr;
        }
        this.clientExtensions = this.tlsClient.getClientExtensions();
        TlsProtocol.a aVar = new TlsProtocol.a(this, (short) 1);
        TlsUtils.writeVersion(clientVersion, aVar);
        aVar.write(this.securityParameters.getClientRandom());
        TlsUtils.writeOpaque8(bArr2, aVar);
        boolean z = TlsUtils.getExtensionData(this.clientExtensions, TlsProtocol.EXT_RenegotiationInfo) == null;
        boolean z2 = !Arrays.contains(this.offeredCipherSuites, 255);
        if (z && z2) {
            this.offeredCipherSuites = Arrays.append(this.offeredCipherSuites, 255);
        }
        if (isFallback && !Arrays.contains(this.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV)) {
            this.offeredCipherSuites = Arrays.append(this.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV);
        }
        TlsUtils.writeUint16ArrayWithUint16Length(this.offeredCipherSuites, aVar);
        TlsUtils.writeUint8ArrayWithUint8Length(this.offeredCompressionMethods, aVar);
        Hashtable hashtable = this.clientExtensions;
        if (hashtable != null) {
            TlsProtocol.writeExtensions(aVar, hashtable);
        }
        aVar.a();
    }

    public void sendClientKeyExchangeMessage() throws IOException {
        TlsProtocol.a aVar = new TlsProtocol.a(this, (short) 16);
        this.keyExchange.generateClientKeyExchange(aVar);
        aVar.a();
    }
}
