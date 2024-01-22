package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.bouncycastle.crypto.tls.SessionParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
/* loaded from: classes13.dex */
public abstract class TlsProtocol {
    public static final short ADS_MODE_0_N = 1;
    public static final short ADS_MODE_0_N_FIRSTONLY = 2;
    public static final short ADS_MODE_1_Nsub1 = 0;
    public static final short CS_CERTIFICATE_REQUEST = 7;
    public static final short CS_CERTIFICATE_STATUS = 5;
    public static final short CS_CERTIFICATE_VERIFY = 12;
    public static final short CS_CLIENT_CERTIFICATE = 10;
    public static final short CS_CLIENT_FINISHED = 13;
    public static final short CS_CLIENT_HELLO = 1;
    public static final short CS_CLIENT_KEY_EXCHANGE = 11;
    public static final short CS_CLIENT_SUPPLEMENTAL_DATA = 9;
    public static final short CS_END = 16;
    public static final short CS_SERVER_CERTIFICATE = 4;
    public static final short CS_SERVER_FINISHED = 15;
    public static final short CS_SERVER_HELLO = 2;
    public static final short CS_SERVER_HELLO_DONE = 8;
    public static final short CS_SERVER_KEY_EXCHANGE = 6;
    public static final short CS_SERVER_SESSION_TICKET = 14;
    public static final short CS_SERVER_SUPPLEMENTAL_DATA = 3;
    public static final short CS_START = 0;
    public static final Integer EXT_RenegotiationInfo = Integers.valueOf(65281);
    public static final Integer EXT_SessionTicket = Integers.valueOf(35);

    /* renamed from: a  reason: collision with root package name */
    public ByteQueue f14859a;
    public boolean allowCertificateStatus;
    public ByteQueue b;
    public boolean blocking;
    public ByteQueue c;
    public Hashtable clientExtensions;
    public short connection_state;
    public k d;
    public n e;
    public boolean expectSessionTicket;
    public o f;
    public volatile boolean g;
    public volatile boolean h;
    public volatile boolean i;
    public ByteQueueInputStream inputBuffers;
    public volatile boolean j;
    public volatile int k;
    public byte[] l;
    public int[] offeredCipherSuites;
    public short[] offeredCompressionMethods;
    public ByteQueueOutputStream outputBuffer;
    public Certificate peerCertificate;
    public boolean receivedChangeCipherSpec;
    public boolean resumedSession;
    public SecureRandom secureRandom;
    public boolean secure_renegotiation;
    public SecurityParameters securityParameters;
    public Hashtable serverExtensions;
    public SessionParameters sessionParameters;
    public TlsSession tlsSession;

    /* loaded from: classes13.dex */
    public class a extends ByteArrayOutputStream {
        public a(TlsProtocol tlsProtocol, short s) throws IOException {
            this(s, 60);
        }

        public a(short s, int i) throws IOException {
            super(i + 4);
            TlsUtils.writeUint8(s, (OutputStream) this);
            ((ByteArrayOutputStream) this).count += 3;
        }

        public void a() throws IOException {
            int i = ((ByteArrayOutputStream) this).count - 4;
            TlsUtils.checkUint24(i);
            TlsUtils.writeUint24(i, ((ByteArrayOutputStream) this).buf, 1);
            TlsProtocol.this.writeHandshakeMessage(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count);
            ((ByteArrayOutputStream) this).buf = null;
        }
    }

    public TlsProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        this.f14859a = new ByteQueue(0);
        this.b = new ByteQueue(2);
        this.c = new ByteQueue(0);
        this.e = null;
        this.f = null;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = true;
        this.k = 0;
        this.l = null;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.securityParameters = null;
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = (short) 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
        this.blocking = true;
        this.d = new k(this, inputStream, outputStream);
        this.secureRandom = secureRandom;
    }

    public TlsProtocol(SecureRandom secureRandom) {
        this.f14859a = new ByteQueue(0);
        this.b = new ByteQueue(2);
        this.c = new ByteQueue(0);
        this.e = null;
        this.f = null;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = true;
        this.k = 0;
        this.l = null;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.securityParameters = null;
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = (short) 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
        this.blocking = false;
        this.inputBuffers = new ByteQueueInputStream();
        this.outputBuffer = new ByteQueueOutputStream();
        this.d = new k(this, this.inputBuffers, this.outputBuffer);
        this.secureRandom = secureRandom;
    }

    public static void assertEmpty(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (byteArrayInputStream.available() > 0) {
            throw new TlsFatalAlert((short) 50);
        }
    }

    public static byte[] createRandomBlock(boolean z, RandomGenerator randomGenerator) {
        byte[] bArr = new byte[32];
        randomGenerator.nextBytes(bArr);
        if (z) {
            TlsUtils.writeGMTUnixTime(bArr, 0);
        }
        return bArr;
    }

    public static byte[] createRenegotiationInfo(byte[] bArr) throws IOException {
        return TlsUtils.encodeOpaque8(bArr);
    }

    public static void establishMasterSecret(TlsContext tlsContext, TlsKeyExchange tlsKeyExchange) throws IOException {
        byte[] generatePremasterSecret = tlsKeyExchange.generatePremasterSecret();
        try {
            tlsContext.getSecurityParameters().f = TlsUtils.d(tlsContext, generatePremasterSecret);
        } finally {
            if (generatePremasterSecret != null) {
                Arrays.fill(generatePremasterSecret, (byte) 0);
            }
        }
    }

    public static byte[] getCurrentPRFHash(TlsContext tlsContext, TlsHandshakeHash tlsHandshakeHash, byte[] bArr) {
        Digest forkPRFHash = tlsHandshakeHash.forkPRFHash();
        if (bArr != null && TlsUtils.isSSL(tlsContext)) {
            forkPRFHash.update(bArr, 0, bArr.length);
        }
        byte[] bArr2 = new byte[forkPRFHash.getDigestSize()];
        forkPRFHash.doFinal(bArr2, 0);
        return bArr2;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getPRFAlgorithm(org.bouncycastle.crypto.tls.TlsContext r4, int r5) throws java.io.IOException {
        /*
            boolean r4 = org.bouncycastle.crypto.tls.TlsUtils.isTLSv12(r4)
            r0 = 1
            r1 = 47
            switch(r5) {
                case 59: goto L38;
                case 60: goto L38;
                case 61: goto L38;
                case 62: goto L38;
                case 63: goto L38;
                case 64: goto L38;
                default: goto La;
            }
        La:
            switch(r5) {
                case 103: goto L38;
                case 104: goto L38;
                case 105: goto L38;
                case 106: goto L38;
                case 107: goto L38;
                case 108: goto L38;
                case 109: goto L38;
                default: goto Ld;
            }
        Ld:
            r2 = 2
            switch(r5) {
                case 156: goto L38;
                case 157: goto L2f;
                case 158: goto L38;
                case 159: goto L2f;
                case 160: goto L38;
                case 161: goto L2f;
                case 162: goto L38;
                case 163: goto L2f;
                case 164: goto L38;
                case 165: goto L2f;
                case 166: goto L38;
                case 167: goto L2f;
                case 168: goto L38;
                case 169: goto L2f;
                case 170: goto L38;
                case 171: goto L2f;
                case 172: goto L38;
                case 173: goto L2f;
                default: goto L11;
            }
        L11:
            r3 = 0
            switch(r5) {
                case 175: goto L2b;
                case 177: goto L2b;
                case 179: goto L2b;
                case 181: goto L2b;
                case 183: goto L2b;
                case 49208: goto L2b;
                case 49211: goto L2b;
                case 49266: goto L38;
                case 49267: goto L2f;
                case 49268: goto L38;
                case 49269: goto L2f;
                case 49270: goto L38;
                case 49271: goto L2f;
                case 49272: goto L38;
                case 49273: goto L2f;
                case 49274: goto L38;
                case 49275: goto L2f;
                case 49276: goto L38;
                case 49277: goto L2f;
                case 49278: goto L38;
                case 49279: goto L2f;
                case 49280: goto L38;
                case 49281: goto L2f;
                case 49282: goto L38;
                case 49283: goto L2f;
                case 49284: goto L38;
                case 49285: goto L2f;
                case 49286: goto L38;
                case 49287: goto L2f;
                case 49288: goto L38;
                case 49289: goto L2f;
                case 49290: goto L38;
                case 49291: goto L2f;
                case 49292: goto L38;
                case 49293: goto L2f;
                case 49294: goto L38;
                case 49295: goto L2f;
                case 49296: goto L38;
                case 49297: goto L2f;
                case 49298: goto L38;
                case 49299: goto L2f;
                case 49301: goto L2b;
                case 49303: goto L2b;
                case 49305: goto L2b;
                default: goto L15;
            }
        L15:
            switch(r5) {
                case 185: goto L2b;
                case 186: goto L38;
                case 187: goto L38;
                case 188: goto L38;
                case 189: goto L38;
                case 190: goto L38;
                case 191: goto L38;
                case 192: goto L38;
                case 193: goto L38;
                case 194: goto L38;
                case 195: goto L38;
                case 196: goto L38;
                case 197: goto L38;
                default: goto L18;
            }
        L18:
            switch(r5) {
                case 49187: goto L38;
                case 49188: goto L2f;
                case 49189: goto L38;
                case 49190: goto L2f;
                case 49191: goto L38;
                case 49192: goto L2f;
                case 49193: goto L38;
                case 49194: goto L2f;
                case 49195: goto L38;
                case 49196: goto L2f;
                case 49197: goto L38;
                case 49198: goto L2f;
                case 49199: goto L38;
                case 49200: goto L2f;
                case 49201: goto L38;
                case 49202: goto L2f;
                default: goto L1b;
            }
        L1b:
            switch(r5) {
                case 49307: goto L2b;
                case 49308: goto L38;
                case 49309: goto L38;
                case 49310: goto L38;
                case 49311: goto L38;
                case 49312: goto L38;
                case 49313: goto L38;
                case 49314: goto L38;
                case 49315: goto L38;
                case 49316: goto L38;
                case 49317: goto L38;
                case 49318: goto L38;
                case 49319: goto L38;
                case 49320: goto L38;
                case 49321: goto L38;
                case 49322: goto L38;
                case 49323: goto L38;
                case 49324: goto L38;
                case 49325: goto L38;
                case 49326: goto L38;
                case 49327: goto L38;
                default: goto L1e;
            }
        L1e:
            switch(r5) {
                case 52392: goto L38;
                case 52393: goto L38;
                case 52394: goto L38;
                case 52395: goto L38;
                case 52396: goto L38;
                case 52397: goto L38;
                case 52398: goto L38;
                default: goto L21;
            }
        L21:
            switch(r5) {
                case 65280: goto L38;
                case 65281: goto L38;
                case 65282: goto L38;
                case 65283: goto L38;
                case 65284: goto L38;
                case 65285: goto L38;
                default: goto L24;
            }
        L24:
            switch(r5) {
                case 65296: goto L38;
                case 65297: goto L38;
                case 65298: goto L38;
                case 65299: goto L38;
                case 65300: goto L38;
                case 65301: goto L38;
                default: goto L27;
            }
        L27:
            if (r4 == 0) goto L2a
            return r0
        L2a:
            return r3
        L2b:
            if (r4 == 0) goto L2e
            return r2
        L2e:
            return r3
        L2f:
            if (r4 == 0) goto L32
            return r2
        L32:
            org.bouncycastle.crypto.tls.TlsFatalAlert r4 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r4.<init>(r1)
            throw r4
        L38:
            if (r4 == 0) goto L3b
            return r0
        L3b:
            org.bouncycastle.crypto.tls.TlsFatalAlert r4 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r4.<init>(r1)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.TlsProtocol.getPRFAlgorithm(org.bouncycastle.crypto.tls.TlsContext, int):int");
    }

    public static Hashtable readExtensions(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (byteArrayInputStream.available() < 1) {
            return null;
        }
        byte[] readOpaque16 = TlsUtils.readOpaque16(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(readOpaque16);
        Hashtable hashtable = new Hashtable();
        while (byteArrayInputStream2.available() > 0) {
            if (hashtable.put(Integers.valueOf(TlsUtils.readUint16(byteArrayInputStream2)), TlsUtils.readOpaque16(byteArrayInputStream2)) != null) {
                throw new TlsFatalAlert((short) 47);
            }
        }
        return hashtable;
    }

    public static Vector readSupplementalDataMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] readOpaque24 = TlsUtils.readOpaque24(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(readOpaque24);
        Vector vector = new Vector();
        while (byteArrayInputStream2.available() > 0) {
            vector.addElement(new SupplementalDataEntry(TlsUtils.readUint16(byteArrayInputStream2), TlsUtils.readOpaque16(byteArrayInputStream2)));
        }
        return vector;
    }

    public static void writeExtensions(OutputStream outputStream, Hashtable hashtable) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeSelectedExtensions(byteArrayOutputStream, hashtable, true);
        writeSelectedExtensions(byteArrayOutputStream, hashtable, false);
        TlsUtils.writeOpaque16(byteArrayOutputStream.toByteArray(), outputStream);
    }

    public static void writeSelectedExtensions(OutputStream outputStream, Hashtable hashtable, boolean z) throws IOException {
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Integer num = (Integer) keys.nextElement();
            int intValue = num.intValue();
            byte[] bArr = (byte[]) hashtable.get(num);
            if (z == (bArr.length == 0)) {
                TlsUtils.checkUint16(intValue);
                TlsUtils.writeUint16(intValue, outputStream);
                TlsUtils.writeOpaque16(bArr, outputStream);
            }
        }
    }

    public static void writeSupplementalData(OutputStream outputStream, Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < vector.size(); i++) {
            SupplementalDataEntry supplementalDataEntry = (SupplementalDataEntry) vector.elementAt(i);
            int dataType = supplementalDataEntry.getDataType();
            TlsUtils.checkUint16(dataType);
            TlsUtils.writeUint16(dataType, byteArrayOutputStream);
            TlsUtils.writeOpaque16(supplementalDataEntry.getData(), byteArrayOutputStream);
        }
        TlsUtils.writeOpaque24(byteArrayOutputStream.toByteArray(), outputStream);
    }

    public abstract org.bouncycastle.crypto.tls.a a();

    public int applicationDataAvailable() {
        return this.f14859a.available();
    }

    public void applyMaxFragmentLengthExtension() throws IOException {
        short s = this.securityParameters.l;
        if (s >= 0) {
            if (!MaxFragmentLength.isValid(s)) {
                throw new TlsFatalAlert((short) 80);
            }
            this.d.u(1 << (this.securityParameters.l + 8));
        }
    }

    public final void b() throws IOException {
        while (this.b.available() >= 2) {
            byte[] removeData = this.b.removeData(2, 0);
            handleAlertMessage(removeData[0], removeData[1]);
        }
    }

    public void blockForHandshake() throws IOException {
        if (this.blocking) {
            while (this.connection_state != 16) {
                if (this.g) {
                    throw new TlsFatalAlert((short) 80);
                }
                safeReadRecord();
            }
        }
    }

    public final void c() {
    }

    public void checkReceivedChangeCipherSpec(boolean z) throws IOException {
        if (z != this.receivedChangeCipherSpec) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    public void cleanupHandshake() {
        byte[] bArr = this.l;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            this.l = null;
        }
        this.securityParameters.a();
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
    }

    public void close() throws IOException {
        handleClose(true);
    }

    public void closeInput() throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use closeInput() in blocking mode!");
        }
        if (this.g) {
            return;
        }
        if (this.inputBuffers.available() > 0) {
            throw new EOFException();
        }
        if (!this.i) {
            throw new TlsFatalAlert((short) 40);
        }
        throw new TlsNoCloseNotifyException();
    }

    public void completeHandshake() throws IOException {
        try {
            this.connection_state = (short) 16;
            this.b.shrink();
            this.c.shrink();
            this.d.f();
            this.j = !TlsUtils.isTLSv11(getContext());
            if (!this.i) {
                this.i = true;
                if (this.blocking) {
                    this.e = new n(this);
                    this.f = new o(this);
                }
            }
            if (this.tlsSession != null) {
                if (this.sessionParameters == null) {
                    this.sessionParameters = new SessionParameters.Builder().setCipherSuite(this.securityParameters.getCipherSuite()).setCompressionAlgorithm(this.securityParameters.getCompressionAlgorithm()).setMasterSecret(this.securityParameters.getMasterSecret()).setPeerCertificate(this.peerCertificate).setPSKIdentity(this.securityParameters.getPSKIdentity()).setSRPIdentity(this.securityParameters.getSRPIdentity()).setServerExtensions(this.serverExtensions).build();
                    this.tlsSession = new q(this.tlsSession.getSessionID(), this.sessionParameters);
                }
                a().c(this.tlsSession);
            }
            getPeer().notifyHandshakeComplete();
        } finally {
            cleanupHandshake();
        }
    }

    public byte[] createVerifyData(boolean z) {
        TlsContext context = getContext();
        return TlsUtils.f(context, z ? ExporterLabel.server_finished : ExporterLabel.client_finished, getCurrentPRFHash(context, this.d.i(), z ? TlsUtils.b : TlsUtils.f14860a));
    }

    public final void d(byte[] bArr, int i, int i2) throws IOException {
        for (int i3 = 0; i3 < i2; i3++) {
            if (TlsUtils.readUint8(bArr, i + i3) != 1) {
                throw new TlsFatalAlert((short) 50);
            }
            if (this.receivedChangeCipherSpec || this.b.available() > 0 || this.c.available() > 0) {
                throw new TlsFatalAlert((short) 10);
            }
            this.d.q();
            this.receivedChangeCipherSpec = true;
            handleChangeCipherSpecMessage();
        }
    }

    public final void e(ByteQueue byteQueue) throws IOException {
        while (byteQueue.available() >= 4) {
            byte[] bArr = new byte[4];
            boolean z = false;
            byteQueue.read(bArr, 0, 4, 0);
            short readUint8 = TlsUtils.readUint8(bArr, 0);
            int readUint24 = TlsUtils.readUint24(bArr, 1);
            int i = readUint24 + 4;
            if (byteQueue.available() < i) {
                return;
            }
            if (this.connection_state == 16 || readUint8 == 20) {
                z = true;
            }
            checkReceivedChangeCipherSpec(z);
            if (readUint8 != 0) {
                if (readUint8 == 20) {
                    TlsContext context = getContext();
                    if (this.l == null && context.getSecurityParameters().getMasterSecret() != null) {
                        this.l = createVerifyData(!context.isServer());
                    }
                }
                byteQueue.copyTo(this.d.j(), i);
            }
            byteQueue.removeData(4);
            handleHandshakeMessage(readUint8, byteQueue.readFrom(readUint24));
        }
    }

    public void flush() throws IOException {
        this.d.g();
    }

    public int getAvailableInputBytes() {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use getAvailableInputBytes() in blocking mode! Use getInputStream().available() instead.");
        }
        return applicationDataAvailable();
    }

    public int getAvailableOutputBytes() {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use getAvailableOutputBytes() in blocking mode! Use getOutputStream() instead.");
        }
        return this.outputBuffer.getBuffer().available();
    }

    public abstract TlsContext getContext();

    public InputStream getInputStream() {
        if (this.blocking) {
            return this.e;
        }
        throw new IllegalStateException("Cannot use InputStream in non-blocking mode! Use offerInput() instead.");
    }

    public OutputStream getOutputStream() {
        if (this.blocking) {
            return this.f;
        }
        throw new IllegalStateException("Cannot use OutputStream in non-blocking mode! Use offerOutput() instead.");
    }

    public abstract TlsPeer getPeer();

    public void handleAlertMessage(short s, short s2) throws IOException {
        getPeer().notifyAlertReceived(s, s2);
        if (s == 1) {
            handleAlertWarningMessage(s2);
        } else {
            handleFailure();
            throw new TlsFatalAlertReceived(s2);
        }
    }

    public void handleAlertWarningMessage(short s) throws IOException {
        if (s == 0) {
            if (!this.i) {
                throw new TlsFatalAlert((short) 40);
            }
            handleClose(false);
        }
    }

    public void handleChangeCipherSpecMessage() throws IOException {
    }

    public void handleClose(boolean z) throws IOException {
        if (this.g) {
            return;
        }
        this.g = true;
        if (z && !this.i) {
            raiseAlertWarning((short) 90, "User canceled handshake");
        }
        raiseAlertWarning((short) 0, "Connection closed");
        this.d.r();
        if (this.i) {
            return;
        }
        cleanupHandshake();
    }

    public void handleException(short s, String str, Throwable th) throws IOException {
        if (this.g) {
            return;
        }
        raiseAlertFatal(s, str, th);
        handleFailure();
    }

    public void handleFailure() {
        this.g = true;
        this.h = true;
        invalidateSession();
        this.d.r();
        if (this.i) {
            return;
        }
        cleanupHandshake();
    }

    public abstract void handleHandshakeMessage(short s, ByteArrayInputStream byteArrayInputStream) throws IOException;

    public void invalidateSession() {
        SessionParameters sessionParameters = this.sessionParameters;
        if (sessionParameters != null) {
            sessionParameters.clear();
            this.sessionParameters = null;
        }
        TlsSession tlsSession = this.tlsSession;
        if (tlsSession != null) {
            tlsSession.invalidate();
            this.tlsSession = null;
        }
    }

    public boolean isClosed() {
        return this.g;
    }

    public void offerInput(byte[] bArr) throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerInput() in blocking mode! Use getInputStream() instead.");
        }
        if (this.g) {
            throw new IOException("Connection is closed, cannot accept any more input");
        }
        this.inputBuffers.addBytes(bArr);
        while (this.inputBuffers.available() >= 5) {
            byte[] bArr2 = new byte[5];
            this.inputBuffers.peek(bArr2);
            if (this.inputBuffers.available() < TlsUtils.readUint16(bArr2, 3) + 5) {
                safeCheckRecordHeader(bArr2);
                return;
            }
            safeReadRecord();
            if (this.g) {
                if (this.connection_state != 16) {
                    throw new TlsFatalAlert((short) 80);
                }
                return;
            }
        }
    }

    public void offerOutput(byte[] bArr, int i, int i2) throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerOutput() in blocking mode! Use getOutputStream() instead.");
        }
        if (!this.i) {
            throw new IOException("Application data cannot be sent until the handshake is complete!");
        }
        writeData(bArr, i, i2);
    }

    public void processFinishedMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] bArr = this.l;
        if (bArr == null) {
            throw new TlsFatalAlert((short) 80);
        }
        byte[] readFully = TlsUtils.readFully(bArr.length, byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        if (!Arrays.constantTimeAreEqual(this.l, readFully)) {
            throw new TlsFatalAlert((short) 51);
        }
    }

    public short processMaxFragmentLengthExtension(Hashtable hashtable, Hashtable hashtable2, short s) throws IOException {
        short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (maxFragmentLengthExtension < 0 || (MaxFragmentLength.isValid(maxFragmentLengthExtension) && (this.resumedSession || maxFragmentLengthExtension == TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable)))) {
            return maxFragmentLengthExtension;
        }
        throw new TlsFatalAlert(s);
    }

    public void processRecord(short s, byte[] bArr, int i, int i2) throws IOException {
        switch (s) {
            case 20:
                d(bArr, i, i2);
                return;
            case 21:
                this.b.addData(bArr, i, i2);
                b();
                return;
            case 22:
                if (this.c.available() > 0) {
                    this.c.addData(bArr, i, i2);
                    e(this.c);
                    return;
                }
                ByteQueue byteQueue = new ByteQueue(bArr, i, i2);
                e(byteQueue);
                int available = byteQueue.available();
                if (available > 0) {
                    this.c.addData(bArr, (i + i2) - available, available);
                    return;
                }
                return;
            case 23:
                if (!this.i) {
                    throw new TlsFatalAlert((short) 10);
                }
                this.f14859a.addData(bArr, i, i2);
                c();
                return;
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    public void raiseAlertFatal(short s, String str, Throwable th) throws IOException {
        getPeer().notifyAlertRaised((short) 2, s, str, th);
        try {
            this.d.y((short) 21, new byte[]{2, (byte) s}, 0, 2);
        } catch (Exception unused) {
        }
    }

    public void raiseAlertWarning(short s, String str) throws IOException {
        getPeer().notifyAlertRaised((short) 1, s, str, null);
        safeWriteRecord((short) 21, new byte[]{1, (byte) s}, 0, 2);
    }

    public int readApplicationData(byte[] bArr, int i, int i2) throws IOException {
        if (i2 < 1) {
            return 0;
        }
        while (this.f14859a.available() == 0) {
            if (this.g) {
                if (this.h) {
                    throw new IOException("Cannot read application data on failed TLS connection");
                }
                if (this.i) {
                    return -1;
                }
                throw new IllegalStateException("Cannot read application data until initial handshake completed.");
            }
            safeReadRecord();
        }
        int min = Math.min(i2, this.f14859a.available());
        this.f14859a.removeData(bArr, i, min, 0);
        return min;
    }

    public int readInput(byte[] bArr, int i, int i2) {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use readInput() in blocking mode! Use getInputStream() instead.");
        }
        try {
            return readApplicationData(bArr, i, Math.min(i2, applicationDataAvailable()));
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public int readOutput(byte[] bArr, int i, int i2) {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use readOutput() in blocking mode! Use getOutputStream() instead.");
        }
        int min = Math.min(getAvailableOutputBytes(), i2);
        this.outputBuffer.getBuffer().removeData(bArr, i, min, 0);
        return min;
    }

    public void refuseRenegotiation() throws IOException {
        if (TlsUtils.isSSL(getContext())) {
            throw new TlsFatalAlert((short) 40);
        }
        raiseAlertWarning((short) 100, "Renegotiation not supported");
    }

    public void safeCheckRecordHeader(byte[] bArr) throws IOException {
        try {
            this.d.c(bArr);
        } catch (RuntimeException e) {
            handleException((short) 80, "Failed to read record", e);
            throw new TlsFatalAlert((short) 80, e);
        } catch (TlsFatalAlert e2) {
            handleException(e2.getAlertDescription(), "Failed to read record", e2);
            throw e2;
        } catch (IOException e3) {
            handleException((short) 80, "Failed to read record", e3);
            throw e3;
        }
    }

    public void safeReadRecord() throws IOException {
        try {
            if (this.d.p()) {
                return;
            }
            if (!this.i) {
                throw new TlsFatalAlert((short) 40);
            }
            handleFailure();
            throw new TlsNoCloseNotifyException();
        } catch (RuntimeException e) {
            handleException((short) 80, "Failed to read record", e);
            throw new TlsFatalAlert((short) 80, e);
        } catch (TlsFatalAlert e2) {
            handleException(e2.getAlertDescription(), "Failed to read record", e2);
            throw e2;
        } catch (TlsFatalAlertReceived e3) {
            throw e3;
        } catch (IOException e4) {
            handleException((short) 80, "Failed to read record", e4);
            throw e4;
        }
    }

    public void safeWriteRecord(short s, byte[] bArr, int i, int i2) throws IOException {
        try {
            this.d.y(s, bArr, i, i2);
        } catch (RuntimeException e) {
            handleException((short) 80, "Failed to write record", e);
            throw new TlsFatalAlert((short) 80, e);
        } catch (TlsFatalAlert e2) {
            handleException(e2.getAlertDescription(), "Failed to write record", e2);
            throw e2;
        } catch (IOException e3) {
            handleException((short) 80, "Failed to write record", e3);
            throw e3;
        }
    }

    public void sendCertificateMessage(Certificate certificate) throws IOException {
        if (certificate == null) {
            certificate = Certificate.EMPTY_CHAIN;
        }
        if (certificate.isEmpty() && !getContext().isServer()) {
            ProtocolVersion serverVersion = getContext().getServerVersion();
            if (serverVersion.isSSL()) {
                raiseAlertWarning((short) 41, serverVersion.toString() + " client didn't provide credentials");
                return;
            }
        }
        a aVar = new a(this, (short) 11);
        certificate.encode(aVar);
        aVar.a();
    }

    public void sendChangeCipherSpecMessage() throws IOException {
        safeWriteRecord((short) 20, new byte[]{1}, 0, 1);
        this.d.s();
    }

    public void sendFinishedMessage() throws IOException {
        byte[] createVerifyData = createVerifyData(getContext().isServer());
        a aVar = new a((short) 20, createVerifyData.length);
        aVar.write(createVerifyData);
        aVar.a();
    }

    public void sendSupplementalDataMessage(Vector vector) throws IOException {
        a aVar = new a(this, (short) 23);
        writeSupplementalData(aVar, vector);
        aVar.a();
    }

    public void setAppDataSplitMode(int i) {
        if (i >= 0 && i <= 2) {
            this.k = i;
            return;
        }
        throw new IllegalArgumentException("Illegal appDataSplitMode mode: " + i);
    }

    public void writeData(byte[] bArr, int i, int i2) throws IOException {
        if (this.g) {
            throw new IOException("Cannot write application data on closed/failed TLS connection");
        }
        while (i2 > 0) {
            if (this.j) {
                int i3 = this.k;
                if (i3 != 1) {
                    if (i3 != 2) {
                        safeWriteRecord((short) 23, bArr, i, 1);
                        i++;
                        i2--;
                    } else {
                        this.j = false;
                    }
                }
                safeWriteRecord((short) 23, TlsUtils.EMPTY_BYTES, 0, 0);
            }
            if (i2 > 0) {
                int min = Math.min(i2, this.d.k());
                safeWriteRecord((short) 23, bArr, i, min);
                i += min;
                i2 -= min;
            }
        }
    }

    public void writeHandshakeMessage(byte[] bArr, int i, int i2) throws IOException {
        if (i2 < 4) {
            throw new TlsFatalAlert((short) 80);
        }
        if (TlsUtils.readUint8(bArr, i) != 0) {
            this.d.j().write(bArr, i, i2);
        }
        int i3 = 0;
        do {
            int min = Math.min(i2 - i3, this.d.k());
            safeWriteRecord((short) 22, bArr, i + i3, min);
            i3 += min;
        } while (i3 < i2);
    }
}
