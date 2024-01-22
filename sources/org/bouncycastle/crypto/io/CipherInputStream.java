package org.bouncycastle.crypto.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.SkippingCipher;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class CipherInputStream extends FilterInputStream {
    public SkippingCipher h;
    public byte[] i;
    public BufferedBlockCipher j;
    public StreamCipher k;
    public AEADBlockCipher l;
    public byte[] m;
    public byte[] n;
    public int o;
    public int p;
    public boolean q;
    public long r;
    public int s;

    public CipherInputStream(InputStream inputStream, BufferedBlockCipher bufferedBlockCipher) {
        this(inputStream, bufferedBlockCipher, 2048);
    }

    public CipherInputStream(InputStream inputStream, BufferedBlockCipher bufferedBlockCipher, int i) {
        super(inputStream);
        this.j = bufferedBlockCipher;
        this.i = new byte[i];
        this.h = bufferedBlockCipher instanceof SkippingCipher ? (SkippingCipher) bufferedBlockCipher : null;
    }

    public CipherInputStream(InputStream inputStream, StreamCipher streamCipher) {
        this(inputStream, streamCipher, 2048);
    }

    public CipherInputStream(InputStream inputStream, StreamCipher streamCipher, int i) {
        super(inputStream);
        this.k = streamCipher;
        this.i = new byte[i];
        this.h = streamCipher instanceof SkippingCipher ? (SkippingCipher) streamCipher : null;
    }

    public CipherInputStream(InputStream inputStream, AEADBlockCipher aEADBlockCipher) {
        this(inputStream, aEADBlockCipher, 2048);
    }

    public CipherInputStream(InputStream inputStream, AEADBlockCipher aEADBlockCipher, int i) {
        super(inputStream);
        this.l = aEADBlockCipher;
        this.i = new byte[i];
        this.h = aEADBlockCipher instanceof SkippingCipher ? (SkippingCipher) aEADBlockCipher : null;
    }

    public final void a(int i, boolean z) {
        if (z) {
            BufferedBlockCipher bufferedBlockCipher = this.j;
            if (bufferedBlockCipher != null) {
                i = bufferedBlockCipher.getOutputSize(i);
            } else {
                AEADBlockCipher aEADBlockCipher = this.l;
                if (aEADBlockCipher != null) {
                    i = aEADBlockCipher.getOutputSize(i);
                }
            }
        } else {
            BufferedBlockCipher bufferedBlockCipher2 = this.j;
            if (bufferedBlockCipher2 != null) {
                i = bufferedBlockCipher2.getUpdateOutputSize(i);
            } else {
                AEADBlockCipher aEADBlockCipher2 = this.l;
                if (aEADBlockCipher2 != null) {
                    i = aEADBlockCipher2.getUpdateOutputSize(i);
                }
            }
        }
        byte[] bArr = this.m;
        if (bArr == null || bArr.length < i) {
            this.m = new byte[i];
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return this.p - this.o;
    }

    public final void b() throws IOException {
        int doFinal;
        try {
            this.q = true;
            a(0, true);
            BufferedBlockCipher bufferedBlockCipher = this.j;
            if (bufferedBlockCipher != null) {
                doFinal = bufferedBlockCipher.doFinal(this.m, 0);
            } else {
                AEADBlockCipher aEADBlockCipher = this.l;
                if (aEADBlockCipher == null) {
                    this.p = 0;
                    return;
                }
                doFinal = aEADBlockCipher.doFinal(this.m, 0);
            }
            this.p = doFinal;
        } catch (InvalidCipherTextException e) {
            throw new InvalidCipherTextIOException("Error finalising cipher", e);
        } catch (Exception e2) {
            throw new IOException("Error finalising cipher " + e2);
        }
    }

    public final int c() throws IOException {
        if (this.q) {
            return -1;
        }
        this.o = 0;
        this.p = 0;
        while (true) {
            int i = this.p;
            if (i != 0) {
                return i;
            }
            int read = ((FilterInputStream) this).in.read(this.i);
            if (read == -1) {
                b();
                int i2 = this.p;
                if (i2 == 0) {
                    return -1;
                }
                return i2;
            }
            try {
                a(read, false);
                BufferedBlockCipher bufferedBlockCipher = this.j;
                if (bufferedBlockCipher != null) {
                    read = bufferedBlockCipher.processBytes(this.i, 0, read, this.m, 0);
                } else {
                    AEADBlockCipher aEADBlockCipher = this.l;
                    if (aEADBlockCipher != null) {
                        read = aEADBlockCipher.processBytes(this.i, 0, read, this.m, 0);
                    } else {
                        this.k.processBytes(this.i, 0, read, this.m, 0);
                    }
                }
                this.p = read;
            } catch (Exception e) {
                throw new CipherIOException("Error processing stream ", e);
            }
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            ((FilterInputStream) this).in.close();
            this.o = 0;
            this.p = 0;
            this.s = 0;
            this.r = 0L;
            byte[] bArr = this.n;
            if (bArr != null) {
                Arrays.fill(bArr, (byte) 0);
                this.n = null;
            }
            byte[] bArr2 = this.m;
            if (bArr2 != null) {
                Arrays.fill(bArr2, (byte) 0);
                this.m = null;
            }
            Arrays.fill(this.i, (byte) 0);
        } finally {
            if (!this.q) {
                b();
            }
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
        ((FilterInputStream) this).in.mark(i);
        SkippingCipher skippingCipher = this.h;
        if (skippingCipher != null) {
            this.r = skippingCipher.getPosition();
        }
        byte[] bArr = this.m;
        if (bArr != null) {
            byte[] bArr2 = new byte[bArr.length];
            this.n = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
        this.s = this.o;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        if (this.h != null) {
            return ((FilterInputStream) this).in.markSupported();
        }
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.o < this.p || c() >= 0) {
            byte[] bArr = this.m;
            int i = this.o;
            this.o = i + 1;
            return bArr[i] & 255;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.o < this.p || c() >= 0) {
            int min = Math.min(i2, available());
            System.arraycopy(this.m, this.o, bArr, i, min);
            this.o += min;
            return min;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        if (this.h == null) {
            throw new IOException("cipher must implement SkippingCipher to be used with reset()");
        }
        ((FilterInputStream) this).in.reset();
        this.h.seekTo(this.r);
        byte[] bArr = this.n;
        if (bArr != null) {
            this.m = bArr;
        }
        this.o = this.s;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        if (j <= 0) {
            return 0L;
        }
        if (this.h == null) {
            int min = (int) Math.min(j, available());
            this.o += min;
            return min;
        }
        long available = available();
        if (j <= available) {
            this.o = (int) (this.o + j);
            return j;
        }
        this.o = this.p;
        long skip = ((FilterInputStream) this).in.skip(j - available);
        if (skip == this.h.skip(skip)) {
            return skip + available;
        }
        throw new IOException("Unable to skip cipher " + skip + " bytes.");
    }
}
