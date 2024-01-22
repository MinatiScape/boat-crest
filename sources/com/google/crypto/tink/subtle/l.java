package com.google.crypto.tink.subtle;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
/* loaded from: classes10.dex */
public class l extends FilterInputStream {
    public final ByteBuffer h;
    public final ByteBuffer i;
    public final int j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public final byte[] o;
    public int p;
    public final StreamSegmentDecrypter q;
    public final int r;
    public final int s;

    public l(i iVar, InputStream inputStream, byte[] bArr) throws GeneralSecurityException, IOException {
        super(inputStream);
        this.q = iVar.newStreamSegmentDecrypter();
        this.j = iVar.getHeaderLength();
        this.o = Arrays.copyOf(bArr, bArr.length);
        int ciphertextSegmentSize = iVar.getCiphertextSegmentSize();
        this.r = ciphertextSegmentSize;
        ByteBuffer allocate = ByteBuffer.allocate(ciphertextSegmentSize + 1);
        this.h = allocate;
        allocate.limit(0);
        this.s = ciphertextSegmentSize - iVar.getCiphertextOffset();
        ByteBuffer allocate2 = ByteBuffer.allocate(iVar.getPlaintextSegmentSize() + 16);
        this.i = allocate2;
        allocate2.limit(0);
        this.k = false;
        this.l = false;
        this.m = false;
        this.p = 0;
        this.n = false;
    }

    public final void a() throws IOException {
        while (!this.l && this.h.remaining() > 0) {
            int read = ((FilterInputStream) this).in.read(this.h.array(), this.h.position(), this.h.remaining());
            if (read > 0) {
                ByteBuffer byteBuffer = this.h;
                byteBuffer.position(byteBuffer.position() + read);
            } else if (read == -1) {
                this.l = true;
            } else if (read == 0) {
                throw new IOException("Could not read bytes from the ciphertext stream");
            }
        }
        byte b = 0;
        if (!this.l) {
            ByteBuffer byteBuffer2 = this.h;
            b = byteBuffer2.get(byteBuffer2.position() - 1);
            ByteBuffer byteBuffer3 = this.h;
            byteBuffer3.position(byteBuffer3.position() - 1);
        }
        this.h.flip();
        this.i.clear();
        try {
            this.q.decryptSegment(this.h, this.p, this.l, this.i);
            this.p++;
            this.i.flip();
            this.h.clear();
            if (this.l) {
                return;
            }
            this.h.clear();
            this.h.limit(this.r + 1);
            this.h.put(b);
        } catch (GeneralSecurityException e) {
            c();
            throw new IOException(e.getMessage() + "\n" + toString() + "\nsegmentNr:" + this.p + " endOfCiphertext:" + this.l, e);
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        return this.i.remaining();
    }

    public final void b() throws IOException {
        if (!this.k) {
            ByteBuffer allocate = ByteBuffer.allocate(this.j);
            while (allocate.remaining() > 0) {
                int read = ((FilterInputStream) this).in.read(allocate.array(), allocate.position(), allocate.remaining());
                if (read == -1) {
                    c();
                    throw new IOException("Ciphertext is too short");
                } else if (read != 0) {
                    allocate.position(allocate.position() + read);
                } else {
                    throw new IOException("Could not read bytes from the ciphertext stream");
                }
            }
            allocate.flip();
            try {
                this.q.init(allocate, this.o);
                this.k = true;
                return;
            } catch (GeneralSecurityException e) {
                throw new IOException(e);
            }
        }
        c();
        throw new IOException("Decryption failed.");
    }

    public final void c() {
        this.n = true;
        this.i.limit(0);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        super.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        int read = read(bArr, 0, 1);
        if (read == 1) {
            return bArr[0] & 255;
        }
        if (read == -1) {
            return read;
        }
        throw new IOException("Reading failed");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        int read;
        long j2 = this.r;
        if (j <= 0) {
            return 0L;
        }
        int min = (int) Math.min(j2, j);
        byte[] bArr = new byte[min];
        long j3 = j;
        while (j3 > 0 && (read = read(bArr, 0, (int) Math.min(min, j3))) > 0) {
            j3 -= read;
        }
        return j - j3;
    }

    public synchronized String toString() {
        return "StreamingAeadDecryptingStream\nsegmentNr:" + this.p + "\nciphertextSegmentSize:" + this.r + "\nheaderRead:" + this.k + "\nendOfCiphertext:" + this.l + "\nendOfPlaintext:" + this.m + "\ndecryptionErrorOccured:" + this.n + "\nciphertextSgement position:" + this.h.position() + " limit:" + this.h.limit() + "\nplaintextSegment position:" + this.i.position() + " limit:" + this.i.limit();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        if (!this.n) {
            if (!this.k) {
                b();
                this.h.clear();
                this.h.limit(this.s + 1);
            }
            if (this.m) {
                return -1;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                if (this.i.remaining() == 0) {
                    if (this.l) {
                        this.m = true;
                        break;
                    }
                    a();
                }
                int min = Math.min(this.i.remaining(), i2 - i3);
                this.i.get(bArr, i3 + i, min);
                i3 += min;
            }
            if (i3 == 0 && this.m) {
                return -1;
            }
            return i3;
        }
        throw new IOException("Decryption failed.");
    }
}
