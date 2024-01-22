package org.bouncycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.util.Integers;
/* loaded from: classes13.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    public f f14867a;
    public TlsHandshakeHash b;
    public Hashtable c = new Hashtable();
    public Hashtable d = null;
    public Vector e = new Vector();
    public boolean f = true;
    public int g = 0;
    public int h = 0;

    /* loaded from: classes13.dex */
    public class a implements d {
        public a() {
        }

        @Override // org.bouncycastle.crypto.tls.d
        public void a(int i, byte[] bArr, int i2, int i3) throws IOException {
            g.this.k(0, i, bArr, i2, i3);
        }
    }

    /* loaded from: classes13.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final int f14869a;
        public final short b;
        public final byte[] c;

        public b(int i, short s, byte[] bArr) {
            this.f14869a = i;
            this.b = s;
            this.c = bArr;
        }

        public /* synthetic */ b(int i, short s, byte[] bArr, a aVar) {
            this(i, s, bArr);
        }

        public byte[] a() {
            return this.c;
        }

        public int b() {
            return this.f14869a;
        }

        public short c() {
            return this.b;
        }
    }

    /* loaded from: classes13.dex */
    public static class c extends ByteArrayOutputStream {
        public c(int i) {
            super(i);
        }

        public void a(f fVar) throws IOException {
            fVar.send(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count);
            ((ByteArrayOutputStream) this).buf = null;
        }
    }

    public g(TlsContext tlsContext, f fVar) {
        this.f14867a = fVar;
        i iVar = new i();
        this.b = iVar;
        iVar.init(tlsContext);
    }

    public static boolean c(Hashtable hashtable) {
        Enumeration elements = hashtable.elements();
        while (elements.hasMoreElements()) {
            if (((e) elements.nextElement()).b() == null) {
                return false;
            }
        }
        return true;
    }

    public static void o(Hashtable hashtable) {
        Enumeration elements = hashtable.elements();
        while (elements.hasMoreElements()) {
            ((e) elements.nextElement()).d();
        }
    }

    public final int b(int i) {
        return Math.min(i * 2, 60000);
    }

    public final void d() {
        Enumeration keys = this.c.keys();
        while (keys.hasMoreElements()) {
            ((Integer) keys.nextElement()).intValue();
        }
    }

    public void e() {
        a aVar = null;
        if (this.f) {
            i(null);
            if (this.d != null) {
                aVar = new a();
            }
        } else {
            d();
        }
        this.f14867a.g(aVar);
    }

    public TlsHandshakeHash f() {
        return this.b;
    }

    public final b g() throws IOException {
        byte[] b2;
        e eVar = (e) this.c.get(Integers.valueOf(this.h));
        if (eVar == null || (b2 = eVar.b()) == null) {
            return null;
        }
        this.d = null;
        int i = this.h;
        this.h = i + 1;
        return r(new b(i, eVar.c(), b2, null));
    }

    public void h() {
        this.b = this.b.notifyPRFDetermined();
    }

    public final void i(Hashtable hashtable) {
        o(this.c);
        this.d = this.c;
        this.c = hashtable;
    }

    public TlsHandshakeHash j() {
        TlsHandshakeHash tlsHandshakeHash = this.b;
        this.b = tlsHandshakeHash.stopTracking();
        return tlsHandshakeHash;
    }

    public final boolean k(int i, int i2, byte[] bArr, int i3, int i4) throws IOException {
        int readUint24;
        int readUint242;
        e eVar;
        boolean z = false;
        int i5 = i3;
        int i6 = i4;
        boolean z2 = false;
        while (i6 >= 12 && i6 >= (readUint242 = (readUint24 = TlsUtils.readUint24(bArr, i5 + 9)) + 12)) {
            int readUint243 = TlsUtils.readUint24(bArr, i5 + 1);
            int readUint244 = TlsUtils.readUint24(bArr, i5 + 6);
            if (readUint244 + readUint24 > readUint243) {
                break;
            }
            short readUint8 = TlsUtils.readUint8(bArr, i5 + 0);
            if (i2 != (readUint8 == 20 ? 1 : 0)) {
                break;
            }
            int readUint16 = TlsUtils.readUint16(bArr, i5 + 4);
            int i7 = this.h;
            if (readUint16 < i7 + i) {
                if (readUint16 >= i7) {
                    e eVar2 = (e) this.c.get(Integers.valueOf(readUint16));
                    if (eVar2 == null) {
                        eVar2 = new e(readUint8, readUint243);
                        this.c.put(Integers.valueOf(readUint16), eVar2);
                    }
                    eVar2.a(readUint8, readUint243, bArr, i5 + 12, readUint244, readUint24);
                } else {
                    Hashtable hashtable = this.d;
                    if (hashtable != null && (eVar = (e) hashtable.get(Integers.valueOf(readUint16))) != null) {
                        eVar.a(readUint8, readUint243, bArr, i5 + 12, readUint244, readUint24);
                        z2 = true;
                    }
                }
            }
            i5 += readUint242;
            i6 -= readUint242;
        }
        if (z2 && c(this.d)) {
            z = true;
        }
        if (z) {
            n();
            o(this.d);
        }
        return z;
    }

    public b l() throws IOException {
        b g;
        if (this.f) {
            this.f = false;
            i(new Hashtable());
        }
        byte[] bArr = null;
        int i = 1000;
        while (true) {
            try {
                g = g();
            } catch (IOException unused) {
            }
            if (g != null) {
                return g;
            }
            int receiveLimit = this.f14867a.getReceiveLimit();
            if (bArr == null || bArr.length < receiveLimit) {
                bArr = new byte[receiveLimit];
            }
            int receive = this.f14867a.receive(bArr, 0, receiveLimit, i);
            if (receive < 0) {
                n();
                i = b(i);
            } else if (k(16, this.f14867a.e(), bArr, 0, receive)) {
                i = b(i);
            }
        }
    }

    public byte[] m(short s) throws IOException {
        b l = l();
        if (l.c() == s) {
            return l.a();
        }
        throw new TlsFatalAlert((short) 10);
    }

    public final void n() throws IOException {
        this.f14867a.k();
        for (int i = 0; i < this.e.size(); i++) {
            t((b) this.e.elementAt(i));
        }
    }

    public void p() {
        this.b.reset();
    }

    public void q(short s, byte[] bArr) throws IOException {
        TlsUtils.checkUint24(bArr.length);
        if (!this.f) {
            d();
            this.f = true;
            this.e.removeAllElements();
        }
        int i = this.g;
        this.g = i + 1;
        b bVar = new b(i, s, bArr, null);
        this.e.addElement(bVar);
        t(bVar);
        r(bVar);
    }

    public final b r(b bVar) throws IOException {
        if (bVar.c() != 0) {
            byte[] a2 = bVar.a();
            byte[] bArr = new byte[12];
            TlsUtils.writeUint8(bVar.c(), bArr, 0);
            TlsUtils.writeUint24(a2.length, bArr, 1);
            TlsUtils.writeUint16(bVar.b(), bArr, 4);
            TlsUtils.writeUint24(0, bArr, 6);
            TlsUtils.writeUint24(a2.length, bArr, 9);
            this.b.update(bArr, 0, 12);
            this.b.update(a2, 0, a2.length);
        }
        return bVar;
    }

    public final void s(b bVar, int i, int i2) throws IOException {
        c cVar = new c(i2 + 12);
        TlsUtils.writeUint8(bVar.c(), (OutputStream) cVar);
        TlsUtils.writeUint24(bVar.a().length, cVar);
        TlsUtils.writeUint16(bVar.b(), cVar);
        TlsUtils.writeUint24(i, cVar);
        TlsUtils.writeUint24(i2, cVar);
        cVar.write(bVar.a(), i, i2);
        cVar.a(this.f14867a);
    }

    public final void t(b bVar) throws IOException {
        int sendLimit = this.f14867a.getSendLimit() - 12;
        if (sendLimit < 1) {
            throw new TlsFatalAlert((short) 80);
        }
        int length = bVar.a().length;
        int i = 0;
        do {
            int min = Math.min(length - i, sendLimit);
            s(bVar, i, min);
            i += min;
        } while (i < length);
    }
}
