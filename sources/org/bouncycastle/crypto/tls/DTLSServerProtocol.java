package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.tls.g;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class DTLSServerProtocol extends DTLSProtocol {
    public boolean verifyRequests;

    /* loaded from: classes13.dex */
    public static class ServerHandshakeState {

        /* renamed from: a  reason: collision with root package name */
        public TlsServer f14849a = null;
        public p b = null;
        public TlsSession c = null;
        public SessionParameters d = null;
        public int[] e = null;
        public short[] f = null;
        public Hashtable g = null;
        public Hashtable h = null;
        public boolean i = false;
        public boolean j = false;
        public boolean k = false;
        public boolean l = false;
        public TlsKeyExchange m = null;
        public TlsCredentials n = null;
        public CertificateRequest o = null;
        public short p = -1;
        public Certificate q = null;
    }

    public DTLSServerProtocol(SecureRandom secureRandom) {
        super(secureRandom);
        this.verifyRequests = true;
    }

    public void abortServerHandshake(ServerHandshakeState serverHandshakeState, f fVar, short s) {
        fVar.b(s);
        invalidateSession(serverHandshakeState);
    }

    public DTLSTransport accept(TlsServer tlsServer, DatagramTransport datagramTransport) throws IOException {
        if (tlsServer != null) {
            if (datagramTransport != null) {
                SecurityParameters securityParameters = new SecurityParameters();
                securityParameters.f14853a = 0;
                ServerHandshakeState serverHandshakeState = new ServerHandshakeState();
                serverHandshakeState.f14849a = tlsServer;
                serverHandshakeState.b = new p(this.secureRandom, securityParameters);
                securityParameters.h = TlsProtocol.createRandomBlock(tlsServer.shouldUseGMTUnixTime(), serverHandshakeState.b.getNonceRandomGenerator());
                tlsServer.init(serverHandshakeState.b);
                f fVar = new f(datagramTransport, serverHandshakeState.b, tlsServer, (short) 22);
                try {
                    try {
                        try {
                            return serverHandshake(serverHandshakeState, fVar);
                        } catch (IOException e) {
                            abortServerHandshake(serverHandshakeState, fVar, (short) 80);
                            throw e;
                        }
                    } catch (RuntimeException e2) {
                        abortServerHandshake(serverHandshakeState, fVar, (short) 80);
                        throw new TlsFatalAlert((short) 80, e2);
                    } catch (TlsFatalAlert e3) {
                        abortServerHandshake(serverHandshakeState, fVar, e3.getAlertDescription());
                        throw e3;
                    }
                } finally {
                    securityParameters.a();
                }
            }
            throw new IllegalArgumentException("'transport' cannot be null");
        }
        throw new IllegalArgumentException("'server' cannot be null");
    }

    public boolean expectCertificateVerifyMessage(ServerHandshakeState serverHandshakeState) {
        short s = serverHandshakeState.p;
        return s >= 0 && TlsUtils.hasSigningCapability(s);
    }

    public byte[] generateCertificateRequest(ServerHandshakeState serverHandshakeState, CertificateRequest certificateRequest) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificateRequest.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] generateCertificateStatus(ServerHandshakeState serverHandshakeState, CertificateStatus certificateStatus) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificateStatus.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] generateNewSessionTicket(ServerHandshakeState serverHandshakeState, NewSessionTicket newSessionTicket) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        newSessionTicket.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] generateServerHello(ServerHandshakeState serverHandshakeState) throws IOException {
        SecurityParameters securityParameters = serverHandshakeState.b.getSecurityParameters();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ProtocolVersion serverVersion = serverHandshakeState.f14849a.getServerVersion();
        if (serverVersion.isEqualOrEarlierVersionOf(serverHandshakeState.b.getClientVersion())) {
            serverHandshakeState.b.d(serverVersion);
            TlsUtils.writeVersion(serverHandshakeState.b.getServerVersion(), byteArrayOutputStream);
            byteArrayOutputStream.write(securityParameters.getServerRandom());
            byte[] bArr = TlsUtils.EMPTY_BYTES;
            TlsUtils.writeOpaque8(bArr, byteArrayOutputStream);
            int selectedCipherSuite = serverHandshakeState.f14849a.getSelectedCipherSuite();
            if (!Arrays.contains(serverHandshakeState.e, selectedCipherSuite) || selectedCipherSuite == 0 || CipherSuite.isSCSV(selectedCipherSuite) || !TlsUtils.isValidCipherSuiteForVersion(selectedCipherSuite, serverHandshakeState.b.getServerVersion())) {
                throw new TlsFatalAlert((short) 80);
            }
            DTLSProtocol.validateSelectedCipherSuite(selectedCipherSuite, (short) 80);
            securityParameters.b = selectedCipherSuite;
            short selectedCompressionMethod = serverHandshakeState.f14849a.getSelectedCompressionMethod();
            if (Arrays.contains(serverHandshakeState.f, selectedCompressionMethod)) {
                securityParameters.c = selectedCompressionMethod;
                TlsUtils.writeUint16(selectedCipherSuite, byteArrayOutputStream);
                TlsUtils.writeUint8(selectedCompressionMethod, (OutputStream) byteArrayOutputStream);
                Hashtable serverExtensions = serverHandshakeState.f14849a.getServerExtensions();
                serverHandshakeState.h = serverExtensions;
                boolean z = true;
                if (serverHandshakeState.j) {
                    Integer num = TlsProtocol.EXT_RenegotiationInfo;
                    if (TlsUtils.getExtensionData(serverExtensions, num) == null) {
                        Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(serverHandshakeState.h);
                        serverHandshakeState.h = ensureExtensionsInitialised;
                        ensureExtensionsInitialised.put(num, TlsProtocol.createRenegotiationInfo(bArr));
                    }
                }
                if (securityParameters.o) {
                    Hashtable ensureExtensionsInitialised2 = TlsExtensionsUtils.ensureExtensionsInitialised(serverHandshakeState.h);
                    serverHandshakeState.h = ensureExtensionsInitialised2;
                    TlsExtensionsUtils.addExtendedMasterSecretExtension(ensureExtensionsInitialised2);
                }
                Hashtable hashtable = serverHandshakeState.h;
                if (hashtable != null) {
                    securityParameters.n = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable);
                    securityParameters.l = DTLSProtocol.evaluateMaxFragmentLengthExtension(serverHandshakeState.i, serverHandshakeState.g, serverHandshakeState.h, (short) 80);
                    securityParameters.m = TlsExtensionsUtils.hasTruncatedHMacExtension(serverHandshakeState.h);
                    serverHandshakeState.k = !serverHandshakeState.i && TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.h, TlsExtensionsUtils.EXT_status_request, (short) 80);
                    if (serverHandshakeState.i || !TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.h, TlsProtocol.EXT_SessionTicket, (short) 80)) {
                        z = false;
                    }
                    serverHandshakeState.l = z;
                    TlsProtocol.writeExtensions(byteArrayOutputStream, serverHandshakeState.h);
                }
                securityParameters.d = TlsProtocol.getPRFAlgorithm(serverHandshakeState.b, securityParameters.getCipherSuite());
                securityParameters.e = 12;
                return byteArrayOutputStream.toByteArray();
            }
            throw new TlsFatalAlert((short) 80);
        }
        throw new TlsFatalAlert((short) 80);
    }

    public boolean getVerifyRequests() {
        return this.verifyRequests;
    }

    public void invalidateSession(ServerHandshakeState serverHandshakeState) {
        SessionParameters sessionParameters = serverHandshakeState.d;
        if (sessionParameters != null) {
            sessionParameters.clear();
            serverHandshakeState.d = null;
        }
        TlsSession tlsSession = serverHandshakeState.c;
        if (tlsSession != null) {
            tlsSession.invalidate();
            serverHandshakeState.c = null;
        }
    }

    public void notifyClientCertificate(ServerHandshakeState serverHandshakeState, Certificate certificate) throws IOException {
        if (serverHandshakeState.o == null) {
            throw new IllegalStateException();
        }
        if (serverHandshakeState.q != null) {
            throw new TlsFatalAlert((short) 10);
        }
        serverHandshakeState.q = certificate;
        if (certificate.isEmpty()) {
            serverHandshakeState.m.skipClientCredentials();
        } else {
            serverHandshakeState.p = TlsUtils.i(certificate, serverHandshakeState.n.getCertificate());
            serverHandshakeState.m.processClientCertificate(certificate);
        }
        serverHandshakeState.f14849a.notifyClientCertificate(certificate);
    }

    public void processCertificateVerify(ServerHandshakeState serverHandshakeState, byte[] bArr, TlsHandshakeHash tlsHandshakeHash) throws IOException {
        byte[] sessionHash;
        if (serverHandshakeState.o == null) {
            throw new IllegalStateException();
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        p pVar = serverHandshakeState.b;
        DigitallySigned parse = DigitallySigned.parse(pVar, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        try {
            SignatureAndHashAlgorithm algorithm = parse.getAlgorithm();
            if (TlsUtils.isTLSv12(pVar)) {
                TlsUtils.verifySupportedSignatureAlgorithm(serverHandshakeState.o.getSupportedSignatureAlgorithms(), algorithm);
                sessionHash = tlsHandshakeHash.getFinalHash(algorithm.getHash());
            } else {
                sessionHash = pVar.getSecurityParameters().getSessionHash();
            }
            AsymmetricKeyParameter createKey = PublicKeyFactory.createKey(serverHandshakeState.q.getCertificateAt(0).getSubjectPublicKeyInfo());
            TlsSigner createTlsSigner = TlsUtils.createTlsSigner(serverHandshakeState.p);
            createTlsSigner.init(pVar);
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

    public void processClientCertificate(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Certificate parse = Certificate.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        notifyClientCertificate(serverHandshakeState, parse);
    }

    public void processClientHello(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ProtocolVersion readVersion = TlsUtils.readVersion(byteArrayInputStream);
        if (!readVersion.isDTLS()) {
            throw new TlsFatalAlert((short) 47);
        }
        byte[] readFully = TlsUtils.readFully(32, byteArrayInputStream);
        if (TlsUtils.readOpaque8(byteArrayInputStream).length > 32) {
            throw new TlsFatalAlert((short) 47);
        }
        TlsUtils.readOpaque8(byteArrayInputStream);
        int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
        if (readUint16 < 2 || (readUint16 & 1) != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        serverHandshakeState.e = TlsUtils.readUint16Array(readUint16 / 2, byteArrayInputStream);
        short readUint8 = TlsUtils.readUint8(byteArrayInputStream);
        if (readUint8 < 1) {
            throw new TlsFatalAlert((short) 47);
        }
        serverHandshakeState.f = TlsUtils.readUint8Array(readUint8, byteArrayInputStream);
        serverHandshakeState.g = TlsProtocol.readExtensions(byteArrayInputStream);
        p pVar = serverHandshakeState.b;
        SecurityParameters securityParameters = pVar.getSecurityParameters();
        securityParameters.o = TlsExtensionsUtils.hasExtendedMasterSecretExtension(serverHandshakeState.g);
        pVar.b(readVersion);
        serverHandshakeState.f14849a.notifyClientVersion(readVersion);
        serverHandshakeState.f14849a.notifyFallback(Arrays.contains(serverHandshakeState.e, (int) CipherSuite.TLS_FALLBACK_SCSV));
        securityParameters.g = readFully;
        serverHandshakeState.f14849a.notifyOfferedCipherSuites(serverHandshakeState.e);
        serverHandshakeState.f14849a.notifyOfferedCompressionMethods(serverHandshakeState.f);
        if (Arrays.contains(serverHandshakeState.e, 255)) {
            serverHandshakeState.j = true;
        }
        byte[] extensionData = TlsUtils.getExtensionData(serverHandshakeState.g, TlsProtocol.EXT_RenegotiationInfo);
        if (extensionData != null) {
            serverHandshakeState.j = true;
            if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        serverHandshakeState.f14849a.notifySecureRenegotiation(serverHandshakeState.j);
        Hashtable hashtable = serverHandshakeState.g;
        if (hashtable != null) {
            TlsExtensionsUtils.getPaddingExtension(hashtable);
            serverHandshakeState.f14849a.processClientExtensions(serverHandshakeState.g);
        }
    }

    public void processClientKeyExchange(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        serverHandshakeState.m.processClientKeyExchange(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    public void processClientSupplementalData(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        serverHandshakeState.f14849a.processClientSupplementalData(TlsProtocol.readSupplementalDataMessage(new ByteArrayInputStream(bArr)));
    }

    public DTLSTransport serverHandshake(ServerHandshakeState serverHandshakeState, f fVar) throws IOException {
        Certificate certificate;
        CertificateStatus certificateStatus;
        SecurityParameters securityParameters = serverHandshakeState.b.getSecurityParameters();
        g gVar = new g(serverHandshakeState.b, fVar);
        g.b l = gVar.l();
        if (l.c() == 1) {
            processClientHello(serverHandshakeState, l.a());
            byte[] generateServerHello = generateServerHello(serverHandshakeState);
            DTLSProtocol.applyMaxFragmentLengthExtension(fVar, securityParameters.l);
            ProtocolVersion serverVersion = serverHandshakeState.b.getServerVersion();
            fVar.n(serverVersion);
            fVar.o(serverVersion);
            gVar.q((short) 2, generateServerHello);
            gVar.h();
            Vector serverSupplementalData = serverHandshakeState.f14849a.getServerSupplementalData();
            if (serverSupplementalData != null) {
                gVar.q((short) 23, DTLSProtocol.generateSupplementalData(serverSupplementalData));
            }
            TlsKeyExchange keyExchange = serverHandshakeState.f14849a.getKeyExchange();
            serverHandshakeState.m = keyExchange;
            keyExchange.init(serverHandshakeState.b);
            TlsCredentials credentials = serverHandshakeState.f14849a.getCredentials();
            serverHandshakeState.n = credentials;
            if (credentials == null) {
                serverHandshakeState.m.skipServerCredentials();
                certificate = null;
            } else {
                serverHandshakeState.m.processServerCredentials(credentials);
                certificate = serverHandshakeState.n.getCertificate();
                gVar.q((short) 11, DTLSProtocol.generateCertificate(certificate));
            }
            if (certificate == null || certificate.isEmpty()) {
                serverHandshakeState.k = false;
            }
            if (serverHandshakeState.k && (certificateStatus = serverHandshakeState.f14849a.getCertificateStatus()) != null) {
                gVar.q((short) 22, generateCertificateStatus(serverHandshakeState, certificateStatus));
            }
            byte[] generateServerKeyExchange = serverHandshakeState.m.generateServerKeyExchange();
            if (generateServerKeyExchange != null) {
                gVar.q((short) 12, generateServerKeyExchange);
            }
            if (serverHandshakeState.n != null) {
                CertificateRequest certificateRequest = serverHandshakeState.f14849a.getCertificateRequest();
                serverHandshakeState.o = certificateRequest;
                if (certificateRequest != null) {
                    if (TlsUtils.isTLSv12(serverHandshakeState.b) != (serverHandshakeState.o.getSupportedSignatureAlgorithms() != null)) {
                        throw new TlsFatalAlert((short) 80);
                    }
                    serverHandshakeState.m.validateCertificateRequest(serverHandshakeState.o);
                    gVar.q((short) 13, generateCertificateRequest(serverHandshakeState, serverHandshakeState.o));
                    TlsUtils.k(gVar.f(), serverHandshakeState.o.getSupportedSignatureAlgorithms());
                }
            }
            gVar.q((short) 14, TlsUtils.EMPTY_BYTES);
            gVar.f().sealHashAlgorithms();
            g.b l2 = gVar.l();
            if (l2.c() == 23) {
                processClientSupplementalData(serverHandshakeState, l2.a());
                l2 = gVar.l();
            } else {
                serverHandshakeState.f14849a.processClientSupplementalData(null);
            }
            if (serverHandshakeState.o == null) {
                serverHandshakeState.m.skipClientCredentials();
            } else if (l2.c() == 11) {
                processClientCertificate(serverHandshakeState, l2.a());
                l2 = gVar.l();
            } else if (TlsUtils.isTLSv12(serverHandshakeState.b)) {
                throw new TlsFatalAlert((short) 10);
            } else {
                notifyClientCertificate(serverHandshakeState, Certificate.EMPTY_CHAIN);
            }
            if (l2.c() == 16) {
                processClientKeyExchange(serverHandshakeState, l2.a());
                TlsHandshakeHash j = gVar.j();
                securityParameters.i = TlsProtocol.getCurrentPRFHash(serverHandshakeState.b, j, null);
                TlsProtocol.establishMasterSecret(serverHandshakeState.b, serverHandshakeState.m);
                fVar.h(serverHandshakeState.f14849a.getCipher());
                if (expectCertificateVerifyMessage(serverHandshakeState)) {
                    processCertificateVerify(serverHandshakeState, gVar.m((short) 15), j);
                }
                p pVar = serverHandshakeState.b;
                processFinished(gVar.m((short) 20), TlsUtils.f(pVar, ExporterLabel.client_finished, TlsProtocol.getCurrentPRFHash(pVar, gVar.f(), null)));
                if (serverHandshakeState.l) {
                    gVar.q((short) 4, generateNewSessionTicket(serverHandshakeState, serverHandshakeState.f14849a.getNewSessionTicket()));
                }
                p pVar2 = serverHandshakeState.b;
                gVar.q((short) 20, TlsUtils.f(pVar2, ExporterLabel.server_finished, TlsProtocol.getCurrentPRFHash(pVar2, gVar.f(), null)));
                gVar.e();
                serverHandshakeState.f14849a.notifyHandshakeComplete();
                return new DTLSTransport(fVar);
            }
            throw new TlsFatalAlert((short) 10);
        }
        throw new TlsFatalAlert((short) 10);
    }

    public void setVerifyRequests(boolean z) {
        this.verifyRequests = z;
    }
}
