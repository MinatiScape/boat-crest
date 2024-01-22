package org.bouncycastle.crypto.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
/* loaded from: classes12.dex */
public class CipherOutputStream extends FilterOutputStream {
    public BufferedBlockCipher h;
    public StreamCipher i;
    public AEADBlockCipher j;
    public final byte[] k;
    public byte[] l;

    public CipherOutputStream(OutputStream outputStream, BufferedBlockCipher bufferedBlockCipher) {
        super(outputStream);
        this.k = new byte[1];
        this.h = bufferedBlockCipher;
    }

    public CipherOutputStream(OutputStream outputStream, StreamCipher streamCipher) {
        super(outputStream);
        this.k = new byte[1];
        this.i = streamCipher;
    }

    public CipherOutputStream(OutputStream outputStream, AEADBlockCipher aEADBlockCipher) {
        super(outputStream);
        this.k = new byte[1];
        this.j = aEADBlockCipher;
    }

    public final void a(int i, boolean z) {
        if (z) {
            BufferedBlockCipher bufferedBlockCipher = this.h;
            if (bufferedBlockCipher != null) {
                i = bufferedBlockCipher.getOutputSize(i);
            } else {
                AEADBlockCipher aEADBlockCipher = this.j;
                if (aEADBlockCipher != null) {
                    i = aEADBlockCipher.getOutputSize(i);
                }
            }
        } else {
            BufferedBlockCipher bufferedBlockCipher2 = this.h;
            if (bufferedBlockCipher2 != null) {
                i = bufferedBlockCipher2.getUpdateOutputSize(i);
            } else {
                AEADBlockCipher aEADBlockCipher2 = this.j;
                if (aEADBlockCipher2 != null) {
                    i = aEADBlockCipher2.getUpdateOutputSize(i);
                }
            }
        }
        byte[] bArr = this.l;
        if (bArr == null || bArr.length < i) {
            this.l = new byte[i];
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(5:1|(4:2|3|(2:5|(1:7))(2:19|(2:21|(1:23))(2:24|(1:26)))|8)|9|10|(1:12)(1:14)) */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0052, code lost:
        if (r0 == null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0054, code lost:
        r0 = r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0057 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0058  */
    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void close() throws java.io.IOException {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            r4.a(r0, r1)
            org.bouncycastle.crypto.BufferedBlockCipher r1 = r4.h     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
            if (r1 == 0) goto L19
            byte[] r2 = r4.l     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
            int r1 = r1.doFinal(r2, r0)     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
            if (r1 == 0) goto L34
            java.io.OutputStream r2 = r4.out     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
            byte[] r3 = r4.l     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
            r2.write(r3, r0, r1)     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
            goto L34
        L19:
            org.bouncycastle.crypto.modes.AEADBlockCipher r1 = r4.j     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
            if (r1 == 0) goto L2d
            byte[] r2 = r4.l     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
            int r1 = r1.doFinal(r2, r0)     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
            if (r1 == 0) goto L34
            java.io.OutputStream r2 = r4.out     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
            byte[] r3 = r4.l     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
            r2.write(r3, r0, r1)     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
            goto L34
        L2d:
            org.bouncycastle.crypto.StreamCipher r0 = r4.i     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
            if (r0 == 0) goto L34
            r0.reset()     // Catch: java.lang.Exception -> L36 org.bouncycastle.crypto.InvalidCipherTextException -> L3f
        L34:
            r0 = 0
            goto L48
        L36:
            r0 = move-exception
            org.bouncycastle.crypto.io.CipherIOException r1 = new org.bouncycastle.crypto.io.CipherIOException
            java.lang.String r2 = "Error closing stream: "
            r1.<init>(r2, r0)
            goto L47
        L3f:
            r0 = move-exception
            org.bouncycastle.crypto.io.InvalidCipherTextIOException r1 = new org.bouncycastle.crypto.io.InvalidCipherTextIOException
            java.lang.String r2 = "Error finalising cipher data"
            r1.<init>(r2, r0)
        L47:
            r0 = r1
        L48:
            r4.flush()     // Catch: java.io.IOException -> L51
            java.io.OutputStream r1 = r4.out     // Catch: java.io.IOException -> L51
            r1.close()     // Catch: java.io.IOException -> L51
            goto L55
        L51:
            r1 = move-exception
            if (r0 != 0) goto L55
            r0 = r1
        L55:
            if (r0 != 0) goto L58
            return
        L58:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.io.CipherOutputStream.close():void");
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        ((FilterOutputStream) this).out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.k;
        byte b = (byte) i;
        bArr[0] = b;
        StreamCipher streamCipher = this.i;
        if (streamCipher != null) {
            ((FilterOutputStream) this).out.write(streamCipher.returnByte(b));
        } else {
            write(bArr, 0, 1);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        a(i2, false);
        BufferedBlockCipher bufferedBlockCipher = this.h;
        if (bufferedBlockCipher != null) {
            int processBytes = bufferedBlockCipher.processBytes(bArr, i, i2, this.l, 0);
            if (processBytes != 0) {
                ((FilterOutputStream) this).out.write(this.l, 0, processBytes);
                return;
            }
            return;
        }
        AEADBlockCipher aEADBlockCipher = this.j;
        if (aEADBlockCipher == null) {
            this.i.processBytes(bArr, i, i2, this.l, 0);
            ((FilterOutputStream) this).out.write(this.l, 0, i2);
            return;
        }
        int processBytes2 = aEADBlockCipher.processBytes(bArr, i, i2, this.l, 0);
        if (processBytes2 != 0) {
            ((FilterOutputStream) this).out.write(this.l, 0, processBytes2);
        }
    }
}
