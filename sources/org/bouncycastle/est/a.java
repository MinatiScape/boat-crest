package org.bouncycastle.est;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes13.dex */
public class a extends InputStream {
    public final InputStream h;
    public final byte[] i = new byte[1024];
    public final byte[] j = new byte[768];
    public final OutputStream k = new C0904a();
    public final Long l;
    public int m;
    public int n;
    public long o;

    /* renamed from: org.bouncycastle.est.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public class C0904a extends OutputStream {
        public C0904a() {
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            a aVar = a.this;
            byte[] bArr = aVar.j;
            int i2 = aVar.n;
            aVar.n = i2 + 1;
            bArr[i2] = (byte) i;
        }
    }

    public a(InputStream inputStream, Long l) {
        this.h = inputStream;
        this.l = l;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0054, code lost:
        org.bouncycastle.util.encoders.Base64.decode(r11.i, 0, r2, r11.k);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005c, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0073, code lost:
        throw new java.io.IOException("Decode Base64 Content-Transfer-Encoding: " + r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a() throws java.io.IOException {
        /*
            r11 = this;
            long r0 = r11.o
            java.lang.Long r2 = r11.l
            long r2 = r2.longValue()
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = -1
            if (r0 < 0) goto Le
            return r1
        Le:
            r0 = 0
            r2 = r0
        L10:
            java.io.InputStream r3 = r11.h
            int r3 = r3.read()
            r4 = 33
            r5 = 10
            r6 = 1
            if (r3 >= r4) goto L2d
            r4 = 13
            if (r3 == r4) goto L2d
            if (r3 != r5) goto L25
            goto L2d
        L25:
            if (r3 < 0) goto L3d
            long r8 = r11.o
            long r8 = r8 + r6
            r11.o = r8
            goto L3d
        L2d:
            byte[] r4 = r11.i
            int r8 = r4.length
            if (r2 >= r8) goto L7a
            int r8 = r2 + 1
            byte r9 = (byte) r3
            r4[r2] = r9
            long r9 = r11.o
            long r9 = r9 + r6
            r11.o = r9
            r2 = r8
        L3d:
            if (r3 <= r1) goto L52
            byte[] r4 = r11.i
            int r4 = r4.length
            if (r2 >= r4) goto L52
            if (r3 == r5) goto L52
            long r4 = r11.o
            java.lang.Long r6 = r11.l
            long r6 = r6.longValue()
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 < 0) goto L10
        L52:
            if (r2 <= 0) goto L74
            byte[] r1 = r11.i     // Catch: java.lang.Exception -> L5c
            java.io.OutputStream r3 = r11.k     // Catch: java.lang.Exception -> L5c
            org.bouncycastle.util.encoders.Base64.decode(r1, r0, r2, r3)     // Catch: java.lang.Exception -> L5c
            goto L77
        L5c:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Decode Base64 Content-Transfer-Encoding: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L74:
            if (r3 != r1) goto L77
            return r1
        L77:
            int r0 = r11.n
            return r0
        L7a:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Content Transfer Encoding, base64 line length > 1024"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.est.a.a():int");
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.h.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.m == this.n) {
            this.m = 0;
            this.n = 0;
            int a2 = a();
            if (a2 == -1) {
                return a2;
            }
        }
        byte[] bArr = this.j;
        int i = this.m;
        this.m = i + 1;
        return bArr[i] & 255;
    }
}
