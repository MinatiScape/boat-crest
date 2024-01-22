package org.bouncycastle.crypto.tls;

import androidx.core.view.InputDeviceCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.util.io.SimpleOutputStream;
/* loaded from: classes13.dex */
public class k {
    public static int u = 16384;

    /* renamed from: a  reason: collision with root package name */
    public TlsProtocol f14872a;
    public InputStream b;
    public OutputStream c;
    public TlsCompression e;
    public TlsCompression f;
    public int r;
    public int s;
    public int t;
    public TlsCompression d = null;
    public TlsCipher g = null;
    public TlsCipher h = null;
    public TlsCipher i = null;
    public b j = new b(null);
    public b k = new b(null);
    public ByteArrayOutputStream l = new ByteArrayOutputStream();
    public TlsHandshakeHash m = null;
    public SimpleOutputStream n = new a();
    public ProtocolVersion o = null;
    public ProtocolVersion p = null;
    public boolean q = true;

    /* loaded from: classes13.dex */
    public class a extends SimpleOutputStream {
        public a() {
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            k.this.m.update(bArr, i, i2);
        }
    }

    /* loaded from: classes13.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public long f14873a;
        public boolean b;

        public b() {
            this.f14873a = 0L;
            this.b = false;
        }

        public /* synthetic */ b(a aVar) {
            this();
        }

        public synchronized long a(short s) throws TlsFatalAlert {
            long j;
            if (this.b) {
                throw new TlsFatalAlert(s);
            }
            j = this.f14873a;
            long j2 = 1 + j;
            this.f14873a = j2;
            if (j2 == 0) {
                this.b = true;
            }
            return j;
        }
    }

    public k(TlsProtocol tlsProtocol, InputStream inputStream, OutputStream outputStream) {
        this.e = null;
        this.f = null;
        this.f14872a = tlsProtocol;
        this.b = inputStream;
        this.c = outputStream;
        TlsNullCompression tlsNullCompression = new TlsNullCompression();
        this.e = tlsNullCompression;
        this.f = tlsNullCompression;
    }

    public static void b(int i, int i2, short s) throws IOException {
        if (i > i2) {
            throw new TlsFatalAlert(s);
        }
    }

    public static void d(short s, short s2) throws IOException {
        switch (s) {
            case 20:
            case 21:
            case 22:
            case 23:
                return;
            default:
                throw new TlsFatalAlert(s2);
        }
    }

    public void c(byte[] bArr) throws IOException {
        d(TlsUtils.readUint8(bArr, 0), (short) 10);
        if (this.q) {
            ProtocolVersion readVersion = TlsUtils.readVersion(bArr, 1);
            ProtocolVersion protocolVersion = this.o;
            if (protocolVersion != null && !readVersion.equals(protocolVersion)) {
                throw new TlsFatalAlert((short) 47);
            }
        } else if ((TlsUtils.readVersionRaw(bArr, 1) & InputDeviceCompat.SOURCE_ANY) != 768) {
            throw new TlsFatalAlert((short) 47);
        }
        b(TlsUtils.readUint16(bArr, 3), this.t, (short) 22);
    }

    public byte[] e(short s, InputStream inputStream, int i) throws IOException {
        byte[] readFully = TlsUtils.readFully(i, inputStream);
        byte[] decodeCiphertext = this.h.decodeCiphertext(this.j.a((short) 10), s, readFully, 0, readFully.length);
        b(decodeCiphertext.length, this.s, (short) 22);
        OutputStream decompress = this.e.decompress(this.l);
        if (decompress != this.l) {
            decompress.write(decodeCiphertext, 0, decodeCiphertext.length);
            decompress.flush();
            decodeCiphertext = h();
        }
        b(decodeCiphertext.length, this.r, (short) 30);
        if (decodeCiphertext.length >= 1 || s == 23) {
            return decodeCiphertext;
        }
        throw new TlsFatalAlert((short) 47);
    }

    public void f() throws IOException {
        TlsCompression tlsCompression = this.e;
        TlsCompression tlsCompression2 = this.d;
        if (tlsCompression == tlsCompression2 && this.f == tlsCompression2) {
            TlsCipher tlsCipher = this.h;
            TlsCipher tlsCipher2 = this.g;
            if (tlsCipher == tlsCipher2 && this.i == tlsCipher2) {
                this.d = null;
                this.g = null;
                return;
            }
        }
        throw new TlsFatalAlert((short) 40);
    }

    public void g() throws IOException {
        this.c.flush();
    }

    public final byte[] h() {
        byte[] byteArray = this.l.toByteArray();
        this.l.reset();
        return byteArray;
    }

    public TlsHandshakeHash i() {
        return this.m;
    }

    public OutputStream j() {
        return this.n;
    }

    public int k() {
        return this.r;
    }

    public ProtocolVersion l() {
        return this.o;
    }

    public void m(TlsContext tlsContext) {
        TlsNullCipher tlsNullCipher = new TlsNullCipher(tlsContext);
        this.h = tlsNullCipher;
        this.i = tlsNullCipher;
        i iVar = new i();
        this.m = iVar;
        iVar.init(tlsContext);
        u(u);
    }

    public void n() {
        this.m = this.m.notifyPRFDetermined();
    }

    public TlsHandshakeHash o() {
        TlsHandshakeHash tlsHandshakeHash = this.m;
        this.m = tlsHandshakeHash.stopTracking();
        return tlsHandshakeHash;
    }

    public boolean p() throws IOException {
        byte[] readAllOrNothing = TlsUtils.readAllOrNothing(5, this.b);
        if (readAllOrNothing == null) {
            return false;
        }
        short readUint8 = TlsUtils.readUint8(readAllOrNothing, 0);
        d(readUint8, (short) 10);
        if (this.q) {
            ProtocolVersion readVersion = TlsUtils.readVersion(readAllOrNothing, 1);
            ProtocolVersion protocolVersion = this.o;
            if (protocolVersion == null) {
                this.o = readVersion;
            } else if (!readVersion.equals(protocolVersion)) {
                throw new TlsFatalAlert((short) 47);
            }
        } else if ((TlsUtils.readVersionRaw(readAllOrNothing, 1) & InputDeviceCompat.SOURCE_ANY) != 768) {
            throw new TlsFatalAlert((short) 47);
        }
        int readUint16 = TlsUtils.readUint16(readAllOrNothing, 3);
        b(readUint16, this.t, (short) 22);
        byte[] e = e(readUint8, this.b, readUint16);
        this.f14872a.processRecord(readUint8, e, 0, e.length);
        return true;
    }

    public void q() throws IOException {
        TlsCipher tlsCipher;
        TlsCompression tlsCompression = this.d;
        if (tlsCompression == null || (tlsCipher = this.g) == null) {
            throw new TlsFatalAlert((short) 40);
        }
        this.e = tlsCompression;
        this.h = tlsCipher;
        this.j = new b(null);
    }

    public void r() {
        try {
            this.b.close();
        } catch (IOException unused) {
        }
        try {
            this.c.close();
        } catch (IOException unused2) {
        }
    }

    public void s() throws IOException {
        TlsCipher tlsCipher;
        TlsCompression tlsCompression = this.d;
        if (tlsCompression == null || (tlsCipher = this.g) == null) {
            throw new TlsFatalAlert((short) 40);
        }
        this.f = tlsCompression;
        this.i = tlsCipher;
        this.k = new b(null);
    }

    public void t(TlsCompression tlsCompression, TlsCipher tlsCipher) {
        this.d = tlsCompression;
        this.g = tlsCipher;
    }

    public void u(int i) {
        this.r = i;
        int i2 = i + 1024;
        this.s = i2;
        this.t = i2 + 1024;
    }

    public void v(ProtocolVersion protocolVersion) {
        this.o = protocolVersion;
    }

    public void w(boolean z) {
        this.q = z;
    }

    public void x(ProtocolVersion protocolVersion) {
        this.p = protocolVersion;
    }

    public void y(short s, byte[] bArr, int i, int i2) throws IOException {
        byte[] encodePlaintext;
        if (this.p == null) {
            return;
        }
        d(s, (short) 80);
        b(i2, this.r, (short) 80);
        if (i2 < 1 && s != 23) {
            throw new TlsFatalAlert((short) 80);
        }
        OutputStream compress = this.f.compress(this.l);
        long a2 = this.k.a((short) 80);
        if (compress == this.l) {
            encodePlaintext = this.i.encodePlaintext(a2, s, bArr, i, i2);
        } else {
            compress.write(bArr, i, i2);
            compress.flush();
            byte[] h = h();
            b(h.length, i2 + 1024, (short) 80);
            encodePlaintext = this.i.encodePlaintext(a2, s, h, 0, h.length);
        }
        b(encodePlaintext.length, this.t, (short) 80);
        byte[] bArr2 = new byte[encodePlaintext.length + 5];
        TlsUtils.writeUint8(s, bArr2, 0);
        TlsUtils.writeVersion(this.p, bArr2, 1);
        TlsUtils.writeUint16(encodePlaintext.length, bArr2, 3);
        System.arraycopy(encodePlaintext, 0, bArr2, 5, encodePlaintext.length);
        this.c.write(bArr2);
        this.c.flush();
    }
}
