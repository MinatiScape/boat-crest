package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.security.GeneralSecurityException;
import java.util.Arrays;
/* loaded from: classes10.dex */
public class k implements ReadableByteChannel {
    public ReadableByteChannel h;
    public ByteBuffer i;
    public ByteBuffer j;
    public ByteBuffer k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public byte[] p;
    public int q;
    public final StreamSegmentDecrypter r;
    public final int s;
    public final int t;

    public k(i iVar, ReadableByteChannel readableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        this.r = iVar.newStreamSegmentDecrypter();
        this.h = readableByteChannel;
        this.k = ByteBuffer.allocate(iVar.getHeaderLength());
        this.p = Arrays.copyOf(bArr, bArr.length);
        int ciphertextSegmentSize = iVar.getCiphertextSegmentSize();
        this.s = ciphertextSegmentSize;
        ByteBuffer allocate = ByteBuffer.allocate(ciphertextSegmentSize + 1);
        this.i = allocate;
        allocate.limit(0);
        this.t = ciphertextSegmentSize - iVar.getCiphertextOffset();
        ByteBuffer allocate2 = ByteBuffer.allocate(iVar.getPlaintextSegmentSize() + 16);
        this.j = allocate2;
        allocate2.limit(0);
        this.l = false;
        this.m = false;
        this.n = false;
        this.q = 0;
        this.o = true;
    }

    public final void a(ByteBuffer byteBuffer) throws IOException {
        int read;
        do {
            read = this.h.read(byteBuffer);
            if (read <= 0) {
                break;
            }
        } while (byteBuffer.remaining() > 0);
        if (read == -1) {
            this.m = true;
        }
    }

    public final void b() {
        this.o = false;
        this.j.limit(0);
    }

    public final boolean c() throws IOException {
        if (!this.m) {
            a(this.i);
        }
        byte b = 0;
        if (this.i.remaining() <= 0 || this.m) {
            if (!this.m) {
                ByteBuffer byteBuffer = this.i;
                b = byteBuffer.get(byteBuffer.position() - 1);
                ByteBuffer byteBuffer2 = this.i;
                byteBuffer2.position(byteBuffer2.position() - 1);
            }
            this.i.flip();
            this.j.clear();
            try {
                this.r.decryptSegment(this.i, this.q, this.m, this.j);
                this.q++;
                this.j.flip();
                this.i.clear();
                if (!this.m) {
                    this.i.clear();
                    this.i.limit(this.s + 1);
                    this.i.put(b);
                }
                return true;
            } catch (GeneralSecurityException e) {
                b();
                throw new IOException(e.getMessage() + "\n" + toString() + "\nsegmentNr:" + this.q + " endOfCiphertext:" + this.m, e);
            }
        }
        return false;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.h.close();
    }

    public final boolean d() throws IOException {
        if (!this.m) {
            a(this.k);
            if (this.k.remaining() > 0) {
                return false;
            }
            this.k.flip();
            try {
                this.r.init(this.k, this.p);
                this.l = true;
                return true;
            } catch (GeneralSecurityException e) {
                b();
                throw new IOException(e);
            }
        }
        throw new IOException("Ciphertext is too short");
    }

    @Override // java.nio.channels.Channel
    public synchronized boolean isOpen() {
        return this.h.isOpen();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        if (this.o) {
            if (!this.l) {
                if (!d()) {
                    return 0;
                }
                this.i.clear();
                this.i.limit(this.t + 1);
            }
            if (this.n) {
                return -1;
            }
            int position = byteBuffer.position();
            while (true) {
                if (byteBuffer.remaining() <= 0) {
                    break;
                }
                if (this.j.remaining() == 0) {
                    if (this.m) {
                        this.n = true;
                        break;
                    } else if (!c()) {
                        break;
                    }
                }
                if (this.j.remaining() <= byteBuffer.remaining()) {
                    this.j.remaining();
                    byteBuffer.put(this.j);
                } else {
                    int remaining = byteBuffer.remaining();
                    ByteBuffer duplicate = this.j.duplicate();
                    duplicate.limit(duplicate.position() + remaining);
                    byteBuffer.put(duplicate);
                    ByteBuffer byteBuffer2 = this.j;
                    byteBuffer2.position(byteBuffer2.position() + remaining);
                }
            }
            int position2 = byteBuffer.position() - position;
            if (position2 == 0 && this.n) {
                return -1;
            }
            return position2;
        }
        throw new IOException("This StreamingAeadDecryptingChannel is in an undefined state");
    }

    public synchronized String toString() {
        return "StreamingAeadDecryptingChannel\nsegmentNr:" + this.q + "\nciphertextSegmentSize:" + this.s + "\nheaderRead:" + this.l + "\nendOfCiphertext:" + this.m + "\nendOfPlaintext:" + this.n + "\ndefinedState:" + this.o + "\nHeader position:" + this.k.position() + " limit:" + this.k.position() + "\nciphertextSgement position:" + this.i.position() + " limit:" + this.i.limit() + "\nplaintextSegment position:" + this.j.position() + " limit:" + this.j.limit();
    }
}
