package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.security.GeneralSecurityException;
import java.util.Arrays;
/* loaded from: classes10.dex */
public class o implements SeekableByteChannel {
    public final SeekableByteChannel h;
    public final ByteBuffer i;
    public final ByteBuffer j;
    public final ByteBuffer k;
    public final long l;
    public final int m;
    public final int n;
    public final byte[] o;
    public final StreamSegmentDecrypter p;
    public long q;
    public long r;
    public boolean s;
    public boolean t;
    public int u;
    public boolean v;
    public final int w;
    public final int x;
    public final int y;
    public final int z;

    public o(i iVar, SeekableByteChannel seekableByteChannel, byte[] bArr) throws IOException, GeneralSecurityException {
        this.p = iVar.newStreamSegmentDecrypter();
        this.h = seekableByteChannel;
        this.k = ByteBuffer.allocate(iVar.getHeaderLength());
        int ciphertextSegmentSize = iVar.getCiphertextSegmentSize();
        this.x = ciphertextSegmentSize;
        this.i = ByteBuffer.allocate(ciphertextSegmentSize);
        int plaintextSegmentSize = iVar.getPlaintextSegmentSize();
        this.w = plaintextSegmentSize;
        this.j = ByteBuffer.allocate(plaintextSegmentSize + 16);
        this.q = 0L;
        this.s = false;
        this.u = -1;
        this.t = false;
        long size = seekableByteChannel.size();
        this.l = size;
        this.o = Arrays.copyOf(bArr, bArr.length);
        this.v = seekableByteChannel.isOpen();
        int i = (int) (size / ciphertextSegmentSize);
        int i2 = (int) (size % ciphertextSegmentSize);
        int ciphertextOverhead = iVar.getCiphertextOverhead();
        if (i2 > 0) {
            this.m = i + 1;
            if (i2 >= ciphertextOverhead) {
                this.n = i2;
            } else {
                throw new IOException("Invalid ciphertext size");
            }
        } else {
            this.m = i;
            this.n = ciphertextSegmentSize;
        }
        int ciphertextOffset = iVar.getCiphertextOffset();
        this.y = ciphertextOffset;
        int headerLength = ciphertextOffset - iVar.getHeaderLength();
        this.z = headerLength;
        if (headerLength >= 0) {
            long j = (this.m * ciphertextOverhead) + ciphertextOffset;
            if (j <= size) {
                this.r = size - j;
                return;
            }
            throw new IOException("Ciphertext is too short");
        }
        throw new IOException("Invalid ciphertext offset or header length");
    }

    public final int a(long j) {
        return (int) ((j + this.y) / this.w);
    }

    public final boolean b() {
        return this.t && this.u == this.m - 1 && this.j.remaining() == 0;
    }

    public final boolean c(int i) throws IOException {
        int i2;
        if (i >= 0 && i < (i2 = this.m)) {
            boolean z = i == i2 - 1;
            if (i == this.u) {
                if (this.t) {
                    return true;
                }
            } else {
                int i3 = this.x;
                long j = i * i3;
                if (z) {
                    i3 = this.n;
                }
                if (i == 0) {
                    int i4 = this.y;
                    i3 -= i4;
                    j = i4;
                }
                this.h.position(j);
                this.i.clear();
                this.i.limit(i3);
                this.u = i;
                this.t = false;
            }
            if (this.i.remaining() > 0) {
                this.h.read(this.i);
            }
            if (this.i.remaining() > 0) {
                return false;
            }
            this.i.flip();
            this.j.clear();
            try {
                this.p.decryptSegment(this.i, i, z, this.j);
                this.j.flip();
                this.t = true;
                return true;
            } catch (GeneralSecurityException e) {
                this.u = -1;
                throw new IOException("Failed to decrypt", e);
            }
        }
        throw new IOException("Invalid position");
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.h.close();
        this.v = false;
    }

    public final boolean d() throws IOException {
        this.h.position(this.k.position() + this.z);
        this.h.read(this.k);
        if (this.k.remaining() > 0) {
            return false;
        }
        this.k.flip();
        try {
            this.p.init(this.k, this.o);
            this.s = true;
            return true;
        } catch (GeneralSecurityException e) {
            throw new IOException(e);
        }
    }

    @Override // java.nio.channels.Channel
    public synchronized boolean isOpen() {
        return this.v;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public synchronized long position() {
        return this.q;
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        long j;
        if (this.v) {
            if (this.s || d()) {
                int position = byteBuffer.position();
                while (byteBuffer.remaining() > 0) {
                    long j2 = this.q;
                    if (j2 < this.r) {
                        int a2 = a(j2);
                        if (a2 == 0) {
                            j = this.q;
                        } else {
                            j = (this.q + this.y) % this.w;
                        }
                        int i = (int) j;
                        if (!c(a2)) {
                            break;
                        }
                        this.j.position(i);
                        if (this.j.remaining() <= byteBuffer.remaining()) {
                            this.q += this.j.remaining();
                            byteBuffer.put(this.j);
                        } else {
                            int remaining = byteBuffer.remaining();
                            ByteBuffer duplicate = this.j.duplicate();
                            duplicate.limit(duplicate.position() + remaining);
                            byteBuffer.put(duplicate);
                            this.q += remaining;
                            ByteBuffer byteBuffer2 = this.j;
                            byteBuffer2.position(byteBuffer2.position() + remaining);
                        }
                    } else {
                        break;
                    }
                }
                int position2 = byteBuffer.position() - position;
                if (position2 == 0 && b()) {
                    return -1;
                }
                return position2;
            }
            return 0;
        }
        throw new ClosedChannelException();
    }

    @Override // java.nio.channels.SeekableByteChannel
    public long size() {
        return this.r;
    }

    public synchronized String toString() {
        StringBuilder sb;
        String str;
        sb = new StringBuilder();
        try {
            str = "position:" + this.h.position();
        } catch (IOException unused) {
            str = "position: n/a";
        }
        sb.append("StreamingAeadSeekableDecryptingChannel");
        sb.append("\nciphertextChannel");
        sb.append(str);
        sb.append("\nciphertextChannelSize:");
        sb.append(this.l);
        sb.append("\nplaintextSize:");
        sb.append(this.r);
        sb.append("\nciphertextSegmentSize:");
        sb.append(this.x);
        sb.append("\nnumberOfSegments:");
        sb.append(this.m);
        sb.append("\nheaderRead:");
        sb.append(this.s);
        sb.append("\nplaintextPosition:");
        sb.append(this.q);
        sb.append("\nHeader");
        sb.append(" position:");
        sb.append(this.k.position());
        sb.append(" limit:");
        sb.append(this.k.position());
        sb.append("\ncurrentSegmentNr:");
        sb.append(this.u);
        sb.append("\nciphertextSgement");
        sb.append(" position:");
        sb.append(this.i.position());
        sb.append(" limit:");
        sb.append(this.i.limit());
        sb.append("\nisCurrentSegmentDecrypted:");
        sb.append(this.t);
        sb.append("\nplaintextSegment");
        sb.append(" position:");
        sb.append(this.j.position());
        sb.append(" limit:");
        sb.append(this.j.limit());
        return sb.toString();
    }

    @Override // java.nio.channels.SeekableByteChannel
    public SeekableByteChannel truncate(long j) throws NonWritableChannelException {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws NonWritableChannelException {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.SeekableByteChannel
    public synchronized SeekableByteChannel position(long j) {
        this.q = j;
        return this;
    }
}
