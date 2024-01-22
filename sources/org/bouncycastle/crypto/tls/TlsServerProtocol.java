package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Hashtable;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class TlsServerProtocol extends TlsProtocol {
    public CertificateRequest certificateRequest;
    public short clientCertificateType;
    public TlsKeyExchange keyExchange;
    public p m;
    public TlsHandshakeHash prepareFinishHash;
    public TlsCredentials serverCredentials;
    public TlsServer tlsServer;

    public TlsServerProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        super(inputStream, outputStream, secureRandom);
        this.tlsServer = null;
        this.m = null;
        this.keyExchange = null;
        this.serverCredentials = null;
        this.certificateRequest = null;
        this.clientCertificateType = (short) -1;
        this.prepareFinishHash = null;
    }

    public TlsServerProtocol(SecureRandom secureRandom) {
        super(secureRandom);
        this.tlsServer = null;
        this.m = null;
        this.keyExchange = null;
        this.serverCredentials = null;
        this.certificateRequest = null;
        this.clientCertificateType = (short) -1;
        this.prepareFinishHash = null;
    }

    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    public a a() {
        return this.m;
    }

    public void accept(TlsServer tlsServer) throws IOException {
        if (tlsServer == null) {
            throw new IllegalArgumentException("'tlsServer' cannot be null");
        }
        if (this.tlsServer != null) {
            throw new IllegalStateException("'accept' can only be called once");
        }
        this.tlsServer = tlsServer;
        SecurityParameters securityParameters = new SecurityParameters();
        this.securityParameters = securityParameters;
        securityParameters.f14853a = 0;
        this.m = new p(this.secureRandom, this.securityParameters);
        this.securityParameters.h = TlsProtocol.createRandomBlock(tlsServer.shouldUseGMTUnixTime(), this.m.getNonceRandomGenerator());
        this.tlsServer.init(this.m);
        this.d.m(this.m);
        this.d.w(false);
        blockForHandshake();
    }

    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    public void cleanupHandshake() {
        super.cleanupHandshake();
        this.keyExchange = null;
        this.serverCredentials = null;
        this.certificateRequest = null;
        this.prepareFinishHash = null;
    }

    public boolean expectCertificateVerifyMessage() {
        short s = this.clientCertificateType;
        return s >= 0 && TlsUtils.hasSigningCapability(s);
    }

    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    public TlsContext getContext() {
        return this.m;
    }

    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    public TlsPeer getPeer() {
        return this.tlsServer;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
        if (r3 == 9) goto L13;
     */
    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleAlertWarningMessage(short r3) throws java.io.IOException {
        /*
            r2 = this;
            super.handleAlertWarningMessage(r3)
            r0 = 41
            if (r3 == r0) goto L8
            return
        L8:
            org.bouncycastle.crypto.tls.TlsContext r3 = r2.getContext()
            boolean r3 = org.bouncycastle.crypto.tls.TlsUtils.isSSL(r3)
            r0 = 10
            if (r3 == 0) goto L31
            org.bouncycastle.crypto.tls.CertificateRequest r3 = r2.certificateRequest
            if (r3 == 0) goto L31
            short r3 = r2.connection_state
            r1 = 8
            if (r3 == r1) goto L23
            r1 = 9
            if (r3 != r1) goto L31
            goto L29
        L23:
            org.bouncycastle.crypto.tls.TlsServer r3 = r2.tlsServer
            r1 = 0
            r3.processClientSupplementalData(r1)
        L29:
            org.bouncycastle.crypto.tls.Certificate r3 = org.bouncycastle.crypto.tls.Certificate.EMPTY_CHAIN
            r2.notifyClientCertificate(r3)
            r2.connection_state = r0
            return
        L31:
            org.bouncycastle.crypto.tls.TlsFatalAlert r3 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.TlsServerProtocol.handleAlertWarningMessage(short):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleHandshakeMessage(short r10, java.io.ByteArrayInputStream r11) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 498
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.TlsServerProtocol.handleHandshakeMessage(short, java.io.ByteArrayInputStream):void");
    }

    public void notifyClientCertificate(Certificate certificate) throws IOException {
        if (this.certificateRequest == null) {
            throw new IllegalStateException();
        }
        if (this.peerCertificate != null) {
            throw new TlsFatalAlert((short) 10);
        }
        this.peerCertificate = certificate;
        if (certificate.isEmpty()) {
            this.keyExchange.skipClientCredentials();
        } else {
            this.clientCertificateType = TlsUtils.i(certificate, this.serverCredentials.getCertificate());
            this.keyExchange.processClientCertificate(certificate);
        }
        this.tlsServer.notifyClientCertificate(certificate);
    }

    public void receiveCertificateMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        Certificate parse = Certificate.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        notifyClientCertificate(parse);
    }

    public void receiveCertificateVerifyMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] sessionHash;
        if (this.certificateRequest == null) {
            throw new IllegalStateException();
        }
        DigitallySigned parse = DigitallySigned.parse(getContext(), byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        try {
            SignatureAndHashAlgorithm algorithm = parse.getAlgorithm();
            if (TlsUtils.isTLSv12(getContext())) {
                TlsUtils.verifySupportedSignatureAlgorithm(this.certificateRequest.getSupportedSignatureAlgorithms(), algorithm);
                sessionHash = this.prepareFinishHash.getFinalHash(algorithm.getHash());
            } else {
                sessionHash = this.securityParameters.getSessionHash();
            }
            AsymmetricKeyParameter createKey = PublicKeyFactory.createKey(this.peerCertificate.getCertificateAt(0).getSubjectPublicKeyInfo());
            TlsSigner createTlsSigner = TlsUtils.createTlsSigner(this.clientCertificateType);
            createTlsSigner.init(getContext());
            if (createTlsSigner.verifyRawSignature(algorithm, parse.getSignature(), createKey, sessionHash)) {
                return;
            }
            throw new TlsFatalAlert((short) 51);
        } catch (TlsFatalAlert e) {
            throw e;
        } catch (Exception e2) {
            throw new TlsFatalAlert((short) 51, e2);
        }
    }

    public void receiveClientHelloMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        ProtocolVersion readVersion = TlsUtils.readVersion(byteArrayInputStream);
        this.d.x(readVersion);
        if (readVersion.isDTLS()) {
            throw new TlsFatalAlert((short) 47);
        }
        byte[] readFully = TlsUtils.readFully(32, byteArrayInputStream);
        if (TlsUtils.readOpaque8(byteArrayInputStream).length > 32) {
            throw new TlsFatalAlert((short) 47);
        }
        int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
        if (readUint16 < 2 || (readUint16 & 1) != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        this.offeredCipherSuites = TlsUtils.readUint16Array(readUint16 / 2, byteArrayInputStream);
        short readUint8 = TlsUtils.readUint8(byteArrayInputStream);
        if (readUint8 < 1) {
            throw new TlsFatalAlert((short) 47);
        }
        this.offeredCompressionMethods = TlsUtils.readUint8Array(readUint8, byteArrayInputStream);
        Hashtable readExtensions = TlsProtocol.readExtensions(byteArrayInputStream);
        this.clientExtensions = readExtensions;
        this.securityParameters.o = TlsExtensionsUtils.hasExtendedMasterSecretExtension(readExtensions);
        a().b(readVersion);
        this.tlsServer.notifyClientVersion(readVersion);
        this.tlsServer.notifyFallback(Arrays.contains(this.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV));
        this.securityParameters.g = readFully;
        this.tlsServer.notifyOfferedCipherSuites(this.offeredCipherSuites);
        this.tlsServer.notifyOfferedCompressionMethods(this.offeredCompressionMethods);
        if (Arrays.contains(this.offeredCipherSuites, 255)) {
            this.secure_renegotiation = true;
        }
        byte[] extensionData = TlsUtils.getExtensionData(this.clientExtensions, TlsProtocol.EXT_RenegotiationInfo);
        if (extensionData != null) {
            this.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        this.tlsServer.notifySecureRenegotiation(this.secure_renegotiation);
        Hashtable hashtable = this.clientExtensions;
        if (hashtable != null) {
            TlsExtensionsUtils.getPaddingExtension(hashtable);
            this.tlsServer.processClientExtensions(this.clientExtensions);
        }
    }

    public void receiveClientKeyExchangeMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        this.keyExchange.processClientKeyExchange(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (TlsUtils.isSSL(getContext())) {
            TlsProtocol.establishMasterSecret(getContext(), this.keyExchange);
        }
        this.prepareFinishHash = this.d.o();
        this.securityParameters.i = TlsProtocol.getCurrentPRFHash(getContext(), this.prepareFinishHash, null);
        if (!TlsUtils.isSSL(getContext())) {
            TlsProtocol.establishMasterSecret(getContext(), this.keyExchange);
        }
        this.d.t(getPeer().getCompression(), getPeer().getCipher());
    }

    public void sendCertificateRequestMessage(CertificateRequest certificateRequest) throws IOException {
        TlsProtocol.a aVar = new TlsProtocol.a(this, (short) 13);
        certificateRequest.encode(aVar);
        aVar.a();
    }

    public void sendCertificateStatusMessage(CertificateStatus certificateStatus) throws IOException {
        TlsProtocol.a aVar = new TlsProtocol.a(this, (short) 22);
        certificateStatus.encode(aVar);
        aVar.a();
    }

    public void sendNewSessionTicketMessage(NewSessionTicket newSessionTicket) throws IOException {
        if (newSessionTicket == null) {
            throw new TlsFatalAlert((short) 80);
        }
        TlsProtocol.a aVar = new TlsProtocol.a(this, (short) 4);
        newSessionTicket.encode(aVar);
        aVar.a();
    }

    public void sendServerHelloDoneMessage() throws IOException {
        byte[] bArr = new byte[4];
        TlsUtils.writeUint8((short) 14, bArr, 0);
        TlsUtils.writeUint24(0, bArr, 1);
        writeHandshakeMessage(bArr, 0, 4);
    }

    public void sendServerHelloMessage() throws IOException {
        TlsProtocol.a aVar = new TlsProtocol.a(this, (short) 2);
        ProtocolVersion serverVersion = this.tlsServer.getServerVersion();
        if (!serverVersion.isEqualOrEarlierVersionOf(getContext().getClientVersion())) {
            throw new TlsFatalAlert((short) 80);
        }
        this.d.v(serverVersion);
        this.d.x(serverVersion);
        boolean z = true;
        this.d.w(true);
        a().d(serverVersion);
        TlsUtils.writeVersion(serverVersion, aVar);
        aVar.write(this.securityParameters.h);
        byte[] bArr = TlsUtils.EMPTY_BYTES;
        TlsUtils.writeOpaque8(bArr, aVar);
        int selectedCipherSuite = this.tlsServer.getSelectedCipherSuite();
        if (!Arrays.contains(this.offeredCipherSuites, selectedCipherSuite) || selectedCipherSuite == 0 || CipherSuite.isSCSV(selectedCipherSuite) || !TlsUtils.isValidCipherSuiteForVersion(selectedCipherSuite, getContext().getServerVersion())) {
            throw new TlsFatalAlert((short) 80);
        }
        this.securityParameters.b = selectedCipherSuite;
        short selectedCompressionMethod = this.tlsServer.getSelectedCompressionMethod();
        if (!Arrays.contains(this.offeredCompressionMethods, selectedCompressionMethod)) {
            throw new TlsFatalAlert((short) 80);
        }
        this.securityParameters.c = selectedCompressionMethod;
        TlsUtils.writeUint16(selectedCipherSuite, aVar);
        TlsUtils.writeUint8(selectedCompressionMethod, (OutputStream) aVar);
        Hashtable serverExtensions = this.tlsServer.getServerExtensions();
        this.serverExtensions = serverExtensions;
        if (this.secure_renegotiation) {
            Integer num = TlsProtocol.EXT_RenegotiationInfo;
            if (TlsUtils.getExtensionData(serverExtensions, num) == null) {
                Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(this.serverExtensions);
                this.serverExtensions = ensureExtensionsInitialised;
                ensureExtensionsInitialised.put(num, TlsProtocol.createRenegotiationInfo(bArr));
            }
        }
        if (this.securityParameters.o) {
            Hashtable ensureExtensionsInitialised2 = TlsExtensionsUtils.ensureExtensionsInitialised(this.serverExtensions);
            this.serverExtensions = ensureExtensionsInitialised2;
            TlsExtensionsUtils.addExtendedMasterSecretExtension(ensureExtensionsInitialised2);
        }
        Hashtable hashtable = this.serverExtensions;
        if (hashtable != null) {
            this.securityParameters.n = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable);
            this.securityParameters.l = processMaxFragmentLengthExtension(this.clientExtensions, this.serverExtensions, (short) 80);
            this.securityParameters.m = TlsExtensionsUtils.hasTruncatedHMacExtension(this.serverExtensions);
            this.allowCertificateStatus = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsExtensionsUtils.EXT_status_request, (short) 80);
            if (this.resumedSession || !TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsProtocol.EXT_SessionTicket, (short) 80)) {
                z = false;
            }
            this.expectSessionTicket = z;
            TlsProtocol.writeExtensions(aVar, this.serverExtensions);
        }
        this.securityParameters.d = TlsProtocol.getPRFAlgorithm(getContext(), this.securityParameters.getCipherSuite());
        this.securityParameters.e = 12;
        applyMaxFragmentLengthExtension();
        aVar.a();
    }

    public void sendServerKeyExchangeMessage(byte[] bArr) throws IOException {
        TlsProtocol.a aVar = new TlsProtocol.a((short) 12, bArr.length);
        aVar.write(bArr);
        aVar.a();
    }
}
