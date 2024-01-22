package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.tls.SessionParameters;
import org.bouncycastle.crypto.tls.g;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class DTLSClientProtocol extends DTLSProtocol {

    /* loaded from: classes13.dex */
    public static class ClientHandshakeState {

        /* renamed from: a  reason: collision with root package name */
        public TlsClient f14848a = null;
        public m b = null;
        public TlsSession c = null;
        public SessionParameters d = null;
        public int[] e = null;
        public short[] f = null;
        public Hashtable g = null;
        public Hashtable h = null;
        public byte[] i = null;
        public boolean j = false;
        public boolean k = false;
        public boolean l = false;
        public boolean m = false;
        public TlsKeyExchange n = null;
        public TlsAuthentication o = null;
        public CertificateRequest p = null;
        public TlsCredentials q = null;
    }

    public DTLSClientProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    public static byte[] patchClientHelloWithCookie(byte[] bArr, byte[] bArr2) throws IOException {
        int readUint8 = 35 + TlsUtils.readUint8(bArr, 34);
        int i = readUint8 + 1;
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, readUint8);
        TlsUtils.checkUint8(bArr2.length);
        TlsUtils.writeUint8(bArr2.length, bArr3, readUint8);
        System.arraycopy(bArr2, 0, bArr3, i, bArr2.length);
        System.arraycopy(bArr, i, bArr3, bArr2.length + i, bArr.length - i);
        return bArr3;
    }

    public void abortClientHandshake(ClientHandshakeState clientHandshakeState, f fVar, short s) {
        fVar.b(s);
        invalidateSession(clientHandshakeState);
    }

    public DTLSTransport clientHandshake(ClientHandshakeState clientHandshakeState, f fVar) throws IOException {
        g.b bVar;
        Certificate certificate;
        SecurityParameters securityParameters = clientHandshakeState.b.getSecurityParameters();
        g gVar = new g(clientHandshakeState.b, fVar);
        byte[] generateClientHello = generateClientHello(clientHandshakeState, clientHandshakeState.f14848a);
        fVar.o(ProtocolVersion.DTLSv10);
        gVar.q((short) 1, generateClientHello);
        while (true) {
            g.b l = gVar.l();
            if (l.c() != 3) {
                if (l.c() == 2) {
                    ProtocolVersion f = fVar.f();
                    reportServerVersion(clientHandshakeState, f);
                    fVar.o(f);
                    processServerHello(clientHandshakeState, l.a());
                    gVar.h();
                    DTLSProtocol.applyMaxFragmentLengthExtension(fVar, securityParameters.l);
                    if (clientHandshakeState.j) {
                        securityParameters.f = Arrays.clone(clientHandshakeState.d.getMasterSecret());
                        fVar.h(clientHandshakeState.f14848a.getCipher());
                        m mVar = clientHandshakeState.b;
                        processFinished(gVar.m((short) 20), TlsUtils.f(mVar, ExporterLabel.server_finished, TlsProtocol.getCurrentPRFHash(mVar, gVar.f(), null)));
                        m mVar2 = clientHandshakeState.b;
                        gVar.q((short) 20, TlsUtils.f(mVar2, ExporterLabel.client_finished, TlsProtocol.getCurrentPRFHash(mVar2, gVar.f(), null)));
                        gVar.e();
                        clientHandshakeState.b.c(clientHandshakeState.c);
                        clientHandshakeState.f14848a.notifyHandshakeComplete();
                        return new DTLSTransport(fVar);
                    }
                    invalidateSession(clientHandshakeState);
                    byte[] bArr = clientHandshakeState.i;
                    if (bArr.length > 0) {
                        clientHandshakeState.c = new q(bArr, null);
                    }
                    g.b l2 = gVar.l();
                    if (l2.c() == 23) {
                        processServerSupplementalData(clientHandshakeState, l2.a());
                        l2 = gVar.l();
                    } else {
                        clientHandshakeState.f14848a.processServerSupplementalData(null);
                    }
                    TlsKeyExchange keyExchange = clientHandshakeState.f14848a.getKeyExchange();
                    clientHandshakeState.n = keyExchange;
                    keyExchange.init(clientHandshakeState.b);
                    if (l2.c() == 11) {
                        certificate = processServerCertificate(clientHandshakeState, l2.a());
                        bVar = gVar.l();
                    } else {
                        clientHandshakeState.n.skipServerCredentials();
                        bVar = l2;
                        certificate = null;
                    }
                    if (certificate == null || certificate.isEmpty()) {
                        clientHandshakeState.l = false;
                    }
                    if (bVar.c() == 22) {
                        processCertificateStatus(clientHandshakeState, bVar.a());
                        bVar = gVar.l();
                    }
                    if (bVar.c() == 12) {
                        processServerKeyExchange(clientHandshakeState, bVar.a());
                        bVar = gVar.l();
                    } else {
                        clientHandshakeState.n.skipServerKeyExchange();
                    }
                    if (bVar.c() == 13) {
                        processCertificateRequest(clientHandshakeState, bVar.a());
                        TlsUtils.k(gVar.f(), clientHandshakeState.p.getSupportedSignatureAlgorithms());
                        bVar = gVar.l();
                    }
                    if (bVar.c() == 14) {
                        if (bVar.a().length == 0) {
                            gVar.f().sealHashAlgorithms();
                            Vector clientSupplementalData = clientHandshakeState.f14848a.getClientSupplementalData();
                            if (clientSupplementalData != null) {
                                gVar.q((short) 23, DTLSProtocol.generateSupplementalData(clientSupplementalData));
                            }
                            CertificateRequest certificateRequest = clientHandshakeState.p;
                            if (certificateRequest != null) {
                                TlsCredentials clientCredentials = clientHandshakeState.o.getClientCredentials(certificateRequest);
                                clientHandshakeState.q = clientCredentials;
                                Certificate certificate2 = clientCredentials != null ? clientCredentials.getCertificate() : null;
                                if (certificate2 == null) {
                                    certificate2 = Certificate.EMPTY_CHAIN;
                                }
                                gVar.q((short) 11, DTLSProtocol.generateCertificate(certificate2));
                            }
                            TlsCredentials tlsCredentials = clientHandshakeState.q;
                            if (tlsCredentials != null) {
                                clientHandshakeState.n.processClientCredentials(tlsCredentials);
                            } else {
                                clientHandshakeState.n.skipClientCredentials();
                            }
                            gVar.q((short) 16, generateClientKeyExchange(clientHandshakeState));
                            TlsHandshakeHash j = gVar.j();
                            securityParameters.i = TlsProtocol.getCurrentPRFHash(clientHandshakeState.b, j, null);
                            TlsProtocol.establishMasterSecret(clientHandshakeState.b, clientHandshakeState.n);
                            fVar.h(clientHandshakeState.f14848a.getCipher());
                            TlsCredentials tlsCredentials2 = clientHandshakeState.q;
                            if (tlsCredentials2 != null && (tlsCredentials2 instanceof TlsSignerCredentials)) {
                                TlsSignerCredentials tlsSignerCredentials = (TlsSignerCredentials) tlsCredentials2;
                                SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(clientHandshakeState.b, tlsSignerCredentials);
                                gVar.q((short) 15, generateCertificateVerify(clientHandshakeState, new DigitallySigned(signatureAndHashAlgorithm, tlsSignerCredentials.generateCertificateSignature(signatureAndHashAlgorithm == null ? securityParameters.getSessionHash() : j.getFinalHash(signatureAndHashAlgorithm.getHash())))));
                            }
                            m mVar3 = clientHandshakeState.b;
                            gVar.q((short) 20, TlsUtils.f(mVar3, ExporterLabel.client_finished, TlsProtocol.getCurrentPRFHash(mVar3, gVar.f(), null)));
                            if (clientHandshakeState.m) {
                                g.b l3 = gVar.l();
                                if (l3.c() != 4) {
                                    throw new TlsFatalAlert((short) 10);
                                }
                                processNewSessionTicket(clientHandshakeState, l3.a());
                            }
                            m mVar4 = clientHandshakeState.b;
                            processFinished(gVar.m((short) 20), TlsUtils.f(mVar4, ExporterLabel.server_finished, TlsProtocol.getCurrentPRFHash(mVar4, gVar.f(), null)));
                            gVar.e();
                            if (clientHandshakeState.c != null) {
                                clientHandshakeState.d = new SessionParameters.Builder().setCipherSuite(securityParameters.getCipherSuite()).setCompressionAlgorithm(securityParameters.getCompressionAlgorithm()).setMasterSecret(securityParameters.getMasterSecret()).setPeerCertificate(certificate).setPSKIdentity(securityParameters.getPSKIdentity()).setSRPIdentity(securityParameters.getSRPIdentity()).setServerExtensions(clientHandshakeState.h).build();
                                TlsSession importSession = TlsUtils.importSession(clientHandshakeState.c.getSessionID(), clientHandshakeState.d);
                                clientHandshakeState.c = importSession;
                                clientHandshakeState.b.c(importSession);
                            }
                            clientHandshakeState.f14848a.notifyHandshakeComplete();
                            return new DTLSTransport(fVar);
                        }
                        throw new TlsFatalAlert((short) 50);
                    }
                    throw new TlsFatalAlert((short) 10);
                }
                throw new TlsFatalAlert((short) 10);
            } else if (!fVar.f().isEqualOrEarlierVersionOf(clientHandshakeState.b.getClientVersion())) {
                throw new TlsFatalAlert((short) 47);
            } else {
                fVar.n(null);
                byte[] patchClientHelloWithCookie = patchClientHelloWithCookie(generateClientHello, processHelloVerifyRequest(clientHandshakeState, l.a()));
                gVar.p();
                gVar.q((short) 1, patchClientHelloWithCookie);
            }
        }
    }

    public DTLSTransport connect(TlsClient tlsClient, DatagramTransport datagramTransport) throws IOException {
        SessionParameters exportSessionParameters;
        if (tlsClient != null) {
            if (datagramTransport != null) {
                SecurityParameters securityParameters = new SecurityParameters();
                securityParameters.f14853a = 1;
                ClientHandshakeState clientHandshakeState = new ClientHandshakeState();
                clientHandshakeState.f14848a = tlsClient;
                clientHandshakeState.b = new m(this.secureRandom, securityParameters);
                securityParameters.g = TlsProtocol.createRandomBlock(tlsClient.shouldUseGMTUnixTime(), clientHandshakeState.b.getNonceRandomGenerator());
                tlsClient.init(clientHandshakeState.b);
                f fVar = new f(datagramTransport, clientHandshakeState.b, tlsClient, (short) 22);
                TlsSession sessionToResume = clientHandshakeState.f14848a.getSessionToResume();
                if (sessionToResume != null && sessionToResume.isResumable() && (exportSessionParameters = sessionToResume.exportSessionParameters()) != null) {
                    clientHandshakeState.c = sessionToResume;
                    clientHandshakeState.d = exportSessionParameters;
                }
                try {
                    try {
                        return clientHandshake(clientHandshakeState, fVar);
                    } catch (RuntimeException e) {
                        abortClientHandshake(clientHandshakeState, fVar, (short) 80);
                        throw new TlsFatalAlert((short) 80, e);
                    } catch (TlsFatalAlert e2) {
                        abortClientHandshake(clientHandshakeState, fVar, e2.getAlertDescription());
                        throw e2;
                    } catch (IOException e3) {
                        abortClientHandshake(clientHandshakeState, fVar, (short) 80);
                        throw e3;
                    }
                } finally {
                    securityParameters.a();
                }
            }
            throw new IllegalArgumentException("'transport' cannot be null");
        }
        throw new IllegalArgumentException("'client' cannot be null");
    }

    public byte[] generateCertificateVerify(ClientHandshakeState clientHandshakeState, DigitallySigned digitallySigned) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        digitallySigned.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] generateClientHello(ClientHandshakeState clientHandshakeState, TlsClient tlsClient) throws IOException {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ProtocolVersion clientVersion = tlsClient.getClientVersion();
        if (clientVersion.isDTLS()) {
            m mVar = clientHandshakeState.b;
            mVar.b(clientVersion);
            TlsUtils.writeVersion(clientVersion, byteArrayOutputStream);
            byteArrayOutputStream.write(mVar.getSecurityParameters().getClientRandom());
            byte[] bArr2 = TlsUtils.EMPTY_BYTES;
            TlsSession tlsSession = clientHandshakeState.c;
            if (tlsSession == null || (bArr = tlsSession.getSessionID()) == null || bArr.length > 32) {
                bArr = bArr2;
            }
            TlsUtils.writeOpaque8(bArr, byteArrayOutputStream);
            TlsUtils.writeOpaque8(bArr2, byteArrayOutputStream);
            boolean isFallback = tlsClient.isFallback();
            clientHandshakeState.e = tlsClient.getCipherSuites();
            Hashtable clientExtensions = tlsClient.getClientExtensions();
            clientHandshakeState.g = clientExtensions;
            boolean z = TlsUtils.getExtensionData(clientExtensions, TlsProtocol.EXT_RenegotiationInfo) == null;
            boolean z2 = !Arrays.contains(clientHandshakeState.e, 255);
            if (z && z2) {
                clientHandshakeState.e = Arrays.append(clientHandshakeState.e, 255);
            }
            if (isFallback && !Arrays.contains(clientHandshakeState.e, (int) CipherSuite.TLS_FALLBACK_SCSV)) {
                clientHandshakeState.e = Arrays.append(clientHandshakeState.e, (int) CipherSuite.TLS_FALLBACK_SCSV);
            }
            TlsUtils.writeUint16ArrayWithUint16Length(clientHandshakeState.e, byteArrayOutputStream);
            short[] sArr = {0};
            clientHandshakeState.f = sArr;
            TlsUtils.writeUint8ArrayWithUint8Length(sArr, byteArrayOutputStream);
            Hashtable hashtable = clientHandshakeState.g;
            if (hashtable != null) {
                TlsProtocol.writeExtensions(byteArrayOutputStream, hashtable);
            }
            return byteArrayOutputStream.toByteArray();
        }
        throw new TlsFatalAlert((short) 80);
    }

    public byte[] generateClientKeyExchange(ClientHandshakeState clientHandshakeState) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        clientHandshakeState.n.generateClientKeyExchange(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public void invalidateSession(ClientHandshakeState clientHandshakeState) {
        SessionParameters sessionParameters = clientHandshakeState.d;
        if (sessionParameters != null) {
            sessionParameters.clear();
            clientHandshakeState.d = null;
        }
        TlsSession tlsSession = clientHandshakeState.c;
        if (tlsSession != null) {
            tlsSession.invalidate();
            clientHandshakeState.c = null;
        }
    }

    public void processCertificateRequest(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        if (clientHandshakeState.o == null) {
            throw new TlsFatalAlert((short) 40);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        clientHandshakeState.p = CertificateRequest.parse(clientHandshakeState.b, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        clientHandshakeState.n.validateCertificateRequest(clientHandshakeState.p);
    }

    public void processCertificateStatus(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        if (!clientHandshakeState.l) {
            throw new TlsFatalAlert((short) 10);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        CertificateStatus.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    public byte[] processHelloVerifyRequest(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ProtocolVersion readVersion = TlsUtils.readVersion(byteArrayInputStream);
        byte[] readOpaque8 = TlsUtils.readOpaque8(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (readVersion.isEqualOrEarlierVersionOf(clientHandshakeState.b.getClientVersion())) {
            if (ProtocolVersion.DTLSv12.isEqualOrEarlierVersionOf(readVersion) || readOpaque8.length <= 32) {
                return readOpaque8;
            }
            throw new TlsFatalAlert((short) 47);
        }
        throw new TlsFatalAlert((short) 47);
    }

    public void processNewSessionTicket(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        NewSessionTicket parse = NewSessionTicket.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        clientHandshakeState.f14848a.notifyNewSessionTicket(parse);
    }

    public Certificate processServerCertificate(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Certificate parse = Certificate.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        clientHandshakeState.n.processServerCertificate(parse);
        TlsAuthentication authentication = clientHandshakeState.f14848a.getAuthentication();
        clientHandshakeState.o = authentication;
        authentication.notifyServerCertificate(parse);
        return parse;
    }

    public void processServerHello(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        TlsSession tlsSession;
        SecurityParameters securityParameters = clientHandshakeState.b.getSecurityParameters();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        reportServerVersion(clientHandshakeState, TlsUtils.readVersion(byteArrayInputStream));
        securityParameters.h = TlsUtils.readFully(32, byteArrayInputStream);
        byte[] readOpaque8 = TlsUtils.readOpaque8(byteArrayInputStream);
        clientHandshakeState.i = readOpaque8;
        if (readOpaque8.length > 32) {
            throw new TlsFatalAlert((short) 47);
        }
        clientHandshakeState.f14848a.notifySessionID(readOpaque8);
        byte[] bArr2 = clientHandshakeState.i;
        boolean z = false;
        clientHandshakeState.j = bArr2.length > 0 && (tlsSession = clientHandshakeState.c) != null && Arrays.areEqual(bArr2, tlsSession.getSessionID());
        int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
        if (!Arrays.contains(clientHandshakeState.e, readUint16) || readUint16 == 0 || CipherSuite.isSCSV(readUint16) || !TlsUtils.isValidCipherSuiteForVersion(readUint16, clientHandshakeState.b.getServerVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        DTLSProtocol.validateSelectedCipherSuite(readUint16, (short) 47);
        clientHandshakeState.f14848a.notifySelectedCipherSuite(readUint16);
        short readUint8 = TlsUtils.readUint8(byteArrayInputStream);
        if (!Arrays.contains(clientHandshakeState.f, readUint8)) {
            throw new TlsFatalAlert((short) 47);
        }
        clientHandshakeState.f14848a.notifySelectedCompressionMethod(readUint8);
        Hashtable readExtensions = TlsProtocol.readExtensions(byteArrayInputStream);
        clientHandshakeState.h = readExtensions;
        if (readExtensions != null) {
            Enumeration keys = readExtensions.keys();
            while (keys.hasMoreElements()) {
                Integer num = (Integer) keys.nextElement();
                if (!num.equals(TlsProtocol.EXT_RenegotiationInfo) && TlsUtils.getExtensionData(clientHandshakeState.g, num) == null) {
                    throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                }
            }
        }
        byte[] extensionData = TlsUtils.getExtensionData(clientHandshakeState.h, TlsProtocol.EXT_RenegotiationInfo);
        if (extensionData != null) {
            clientHandshakeState.k = true;
            if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        clientHandshakeState.f14848a.notifySecureRenegotiation(clientHandshakeState.k);
        Hashtable hashtable = clientHandshakeState.g;
        Hashtable hashtable2 = clientHandshakeState.h;
        if (clientHandshakeState.j) {
            if (readUint16 != clientHandshakeState.d.getCipherSuite() || readUint8 != clientHandshakeState.d.getCompressionAlgorithm()) {
                throw new TlsFatalAlert((short) 47);
            }
            hashtable = null;
            hashtable2 = clientHandshakeState.d.readServerExtensions();
        }
        securityParameters.b = readUint16;
        securityParameters.c = readUint8;
        if (hashtable2 != null) {
            boolean hasEncryptThenMACExtension = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable2);
            if (hasEncryptThenMACExtension && !TlsUtils.isBlockCipherSuite(securityParameters.getCipherSuite())) {
                throw new TlsFatalAlert((short) 47);
            }
            securityParameters.n = hasEncryptThenMACExtension;
            securityParameters.o = TlsExtensionsUtils.hasExtendedMasterSecretExtension(hashtable2);
            securityParameters.l = DTLSProtocol.evaluateMaxFragmentLengthExtension(clientHandshakeState.j, hashtable, hashtable2, (short) 47);
            securityParameters.m = TlsExtensionsUtils.hasTruncatedHMacExtension(hashtable2);
            clientHandshakeState.l = !clientHandshakeState.j && TlsUtils.hasExpectedEmptyExtensionData(hashtable2, TlsExtensionsUtils.EXT_status_request, (short) 47);
            if (!clientHandshakeState.j && TlsUtils.hasExpectedEmptyExtensionData(hashtable2, TlsProtocol.EXT_SessionTicket, (short) 47)) {
                z = true;
            }
            clientHandshakeState.m = z;
        }
        if (hashtable != null) {
            clientHandshakeState.f14848a.processServerExtensions(hashtable2);
        }
        securityParameters.d = TlsProtocol.getPRFAlgorithm(clientHandshakeState.b, securityParameters.getCipherSuite());
        securityParameters.e = 12;
    }

    public void processServerKeyExchange(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        clientHandshakeState.n.processServerKeyExchange(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    public void processServerSupplementalData(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        clientHandshakeState.f14848a.processServerSupplementalData(TlsProtocol.readSupplementalDataMessage(new ByteArrayInputStream(bArr)));
    }

    public void reportServerVersion(ClientHandshakeState clientHandshakeState, ProtocolVersion protocolVersion) throws IOException {
        m mVar = clientHandshakeState.b;
        ProtocolVersion serverVersion = mVar.getServerVersion();
        if (serverVersion == null) {
            mVar.d(protocolVersion);
            clientHandshakeState.f14848a.notifyServerVersion(protocolVersion);
        } else if (!serverVersion.equals(protocolVersion)) {
            throw new TlsFatalAlert((short) 47);
        }
    }
}
