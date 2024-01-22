package org.bouncycastle.crypto.tls;

import java.io.IOException;
/* loaded from: classes13.dex */
public class f implements DatagramTransport {

    /* renamed from: a  reason: collision with root package name */
    public final DatagramTransport f14866a;
    public final TlsContext b;
    public final TlsPeer c;
    public volatile int j;
    public c k;
    public c l;
    public c m;
    public c n;
    public final ByteQueue d = new ByteQueue();
    public volatile boolean e = false;
    public volatile boolean f = false;
    public volatile ProtocolVersion g = null;
    public volatile ProtocolVersion h = null;
    public d o = null;
    public c p = null;
    public long q = 0;
    public volatile boolean i = true;

    public f(DatagramTransport datagramTransport, TlsContext tlsContext, TlsPeer tlsPeer, short s) {
        this.f14866a = datagramTransport;
        this.b = tlsContext;
        this.c = tlsPeer;
        c cVar = new c(0, new TlsNullCipher(tlsContext));
        this.k = cVar;
        this.l = null;
        this.m = cVar;
        this.n = cVar;
        m(16384);
    }

    public static long d(int i, long j) {
        return ((i & 4294967295L) << 48) | j;
    }

    public final void a() {
        if (this.e) {
            return;
        }
        try {
            if (!this.f) {
                p((short) 0, null);
            }
            this.f14866a.close();
        } catch (Exception unused) {
        }
        this.e = true;
    }

    public void b(short s) {
        if (this.e) {
            return;
        }
        try {
            i((short) 2, s, null, null);
        } catch (Exception unused) {
        }
        this.f = true;
        a();
    }

    public void c() {
        if (this.e) {
            return;
        }
        this.f = true;
        a();
    }

    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    public void close() throws IOException {
        if (this.e) {
            return;
        }
        if (this.i) {
            p((short) 90, "User canceled handshake");
        }
        a();
    }

    public int e() {
        return this.m.c();
    }

    public ProtocolVersion f() {
        return this.g;
    }

    public void g(d dVar) {
        c cVar = this.m;
        c cVar2 = this.k;
        if (cVar == cVar2 || this.n == cVar2) {
            throw new IllegalStateException();
        }
        if (dVar != null) {
            this.o = dVar;
            this.p = cVar2;
            this.q = System.currentTimeMillis() + 240000;
        }
        this.i = false;
        this.k = this.l;
        this.l = null;
    }

    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    public int getReceiveLimit() throws IOException {
        return Math.min(this.j, this.m.b().getPlaintextLimit(this.f14866a.getReceiveLimit() - 13));
    }

    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    public int getSendLimit() throws IOException {
        return Math.min(this.j, this.n.b().getPlaintextLimit(this.f14866a.getSendLimit() - 13));
    }

    public void h(TlsCipher tlsCipher) {
        if (this.l != null) {
            throw new IllegalStateException();
        }
        this.l = new c(this.n.c() + 1, tlsCipher);
    }

    public final void i(short s, short s2, String str, Throwable th) throws IOException {
        this.c.notifyAlertRaised(s, s2, str, th);
        l((short) 21, new byte[]{(byte) s, (byte) s2}, 0, 2);
    }

    public final int j(byte[] bArr, int i, int i2, int i3) throws IOException {
        int readUint16;
        int i4;
        if (this.d.available() <= 0) {
            int receive = this.f14866a.receive(bArr, i, i2, i3);
            if (receive < 13 || receive <= (readUint16 = TlsUtils.readUint16(bArr, i + 11) + 13)) {
                return receive;
            }
            this.d.addData(bArr, i + readUint16, receive - readUint16);
            return readUint16;
        }
        if (this.d.available() >= 13) {
            byte[] bArr2 = new byte[2];
            this.d.read(bArr2, 0, 2, 11);
            i4 = TlsUtils.readUint16(bArr2, 0);
        } else {
            i4 = 0;
        }
        int min = Math.min(this.d.available(), i4 + 13);
        this.d.removeData(bArr, i, min, 0);
        return min;
    }

    public void k() {
        c cVar = this.p;
        if (cVar == null) {
            cVar = this.k;
        }
        this.n = cVar;
    }

    public final void l(short s, byte[] bArr, int i, int i2) throws IOException {
        if (this.h == null) {
            return;
        }
        if (i2 > this.j) {
            throw new TlsFatalAlert((short) 80);
        }
        if (i2 < 1 && s != 23) {
            throw new TlsFatalAlert((short) 80);
        }
        int c = this.n.c();
        long a2 = this.n.a();
        byte[] encodePlaintext = this.n.b().encodePlaintext(d(c, a2), s, bArr, i, i2);
        int length = encodePlaintext.length + 13;
        byte[] bArr2 = new byte[length];
        TlsUtils.writeUint8(s, bArr2, 0);
        TlsUtils.writeVersion(this.h, bArr2, 1);
        TlsUtils.writeUint16(c, bArr2, 3);
        TlsUtils.writeUint48(a2, bArr2, 5);
        TlsUtils.writeUint16(encodePlaintext.length, bArr2, 11);
        System.arraycopy(encodePlaintext, 0, bArr2, 13, encodePlaintext.length);
        this.f14866a.send(bArr2, 0, length);
    }

    public void m(int i) {
        this.j = i;
    }

    public void n(ProtocolVersion protocolVersion) {
        this.g = protocolVersion;
    }

    public void o(ProtocolVersion protocolVersion) {
        this.h = protocolVersion;
    }

    public void p(short s, String str) throws IOException {
        i((short) 1, s, str, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:84:0x0122, code lost:
        if (r18.i != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0126, code lost:
        if (r18.o == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0128, code lost:
        r18.o = null;
        r18.p = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x012d, code lost:
        java.lang.System.arraycopy(r3, 0, r19, r20, r3.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0137, code lost:
        return r3.length;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0074 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0075 A[Catch: IOException -> 0x0138, TryCatch #0 {IOException -> 0x0138, blocks: (B:8:0x0018, B:10:0x001c, B:12:0x0026, B:13:0x002a, B:18:0x003b, B:21:0x0045, B:22:0x0049, B:24:0x004d, B:26:0x005a, B:38:0x0075, B:41:0x0085, B:44:0x0091, B:46:0x0095, B:49:0x009e, B:52:0x00cc, B:54:0x00d0, B:55:0x00d2, B:83:0x0120, B:85:0x0124, B:87:0x0128, B:88:0x012d, B:57:0x00d6, B:60:0x00db, B:62:0x00df, B:64:0x00e3, B:65:0x00e9, B:67:0x00ed, B:70:0x00fe, B:71:0x0102, B:72:0x010a, B:74:0x010d, B:76:0x0110, B:82:0x011d, B:79:0x0117, B:81:0x011b, B:30:0x0063, B:32:0x0067, B:34:0x006d), top: B:92:0x0018 }] */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [org.bouncycastle.crypto.tls.d, org.bouncycastle.crypto.tls.c] */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v3 */
    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int receive(byte[] r19, int r20, int r21, int r22) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.f.receive(byte[], int, int, int):int");
    }

    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    public void send(byte[] bArr, int i, int i2) throws IOException {
        short s;
        if (this.i || this.n == this.p) {
            s = 22;
            if (TlsUtils.readUint8(bArr, i) == 20) {
                c cVar = null;
                if (this.i) {
                    cVar = this.l;
                } else if (this.n == this.p) {
                    cVar = this.k;
                }
                if (cVar == null) {
                    throw new IllegalStateException();
                }
                l((short) 20, new byte[]{1}, 0, 1);
                this.n = cVar;
            }
        } else {
            s = 23;
        }
        l(s, bArr, i, i2);
    }
}
