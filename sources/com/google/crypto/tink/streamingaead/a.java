package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public final class a extends InputStream {
    @GuardedBy("this")
    public boolean h = false;
    @GuardedBy("this")
    public InputStream i = null;
    @GuardedBy("this")
    public InputStream j;
    public PrimitiveSet<StreamingAead> k;
    public byte[] l;

    public a(PrimitiveSet<StreamingAead> primitiveSet, InputStream inputStream, byte[] bArr) {
        this.k = primitiveSet;
        if (inputStream.markSupported()) {
            this.j = inputStream;
        } else {
            this.j = new BufferedInputStream(inputStream);
        }
        this.j.mark(Integer.MAX_VALUE);
        this.l = (byte[]) bArr.clone();
    }

    @GuardedBy("this")
    public final void a() throws IOException {
        this.j.mark(0);
    }

    @Override // java.io.InputStream
    @GuardedBy("this")
    public synchronized int available() throws IOException {
        InputStream inputStream = this.i;
        if (inputStream == null) {
            return 0;
        }
        return inputStream.available();
    }

    @GuardedBy("this")
    public final void b() throws IOException {
        this.j.reset();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    @GuardedBy("this")
    public synchronized void close() throws IOException {
        this.j.close();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.InputStream
    @GuardedBy("this")
    public synchronized int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr) == 1) {
            return bArr[0];
        }
        return -1;
    }

    @Override // java.io.InputStream
    @GuardedBy("this")
    public synchronized int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    @GuardedBy("this")
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        InputStream inputStream = this.i;
        if (inputStream != null) {
            return inputStream.read(bArr, i, i2);
        } else if (!this.h) {
            this.h = true;
            for (PrimitiveSet.Entry<StreamingAead> entry : this.k.getRawPrimitives()) {
                try {
                    InputStream newDecryptingStream = entry.getPrimitive().newDecryptingStream(this.j, this.l);
                    int read = newDecryptingStream.read(bArr, i, i2);
                    if (read != 0) {
                        this.i = newDecryptingStream;
                        a();
                        return read;
                    }
                    throw new IOException("Could not read bytes from the ciphertext stream");
                } catch (IOException unused) {
                    b();
                } catch (GeneralSecurityException unused2) {
                    b();
                }
            }
            throw new IOException("No matching key found for the ciphertext in the stream.");
        } else {
            throw new IOException("No matching key found for the ciphertext in the stream.");
        }
    }
}
