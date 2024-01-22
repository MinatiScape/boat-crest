package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.security.GeneralSecurityException;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public final class c implements SeekableByteChannel {
    @GuardedBy("this")
    public SeekableByteChannel j;
    @GuardedBy("this")
    public long k;
    @GuardedBy("this")
    public long l;
    public byte[] n;
    @GuardedBy("this")
    public SeekableByteChannel h = null;
    @GuardedBy("this")
    public SeekableByteChannel i = null;
    public Deque<StreamingAead> m = new ArrayDeque();

    public c(PrimitiveSet<StreamingAead> primitiveSet, SeekableByteChannel seekableByteChannel, byte[] bArr) throws IOException {
        for (PrimitiveSet.Entry<StreamingAead> entry : primitiveSet.getRawPrimitives()) {
            this.m.add(entry.getPrimitive());
        }
        this.j = seekableByteChannel;
        this.k = -1L;
        this.l = seekableByteChannel.position();
        this.n = (byte[]) bArr.clone();
    }

    @GuardedBy("this")
    public final synchronized SeekableByteChannel a() throws IOException {
        SeekableByteChannel newSeekableDecryptingChannel;
        while (!this.m.isEmpty()) {
            this.j.position(this.l);
            try {
                newSeekableDecryptingChannel = this.m.removeFirst().newSeekableDecryptingChannel(this.j, this.n);
                long j = this.k;
                if (j >= 0) {
                    newSeekableDecryptingChannel.position(j);
                }
            } catch (GeneralSecurityException unused) {
            }
        }
        throw new IOException("No matching key found for the ciphertext in the stream.");
        return newSeekableDecryptingChannel;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    @GuardedBy("this")
    public synchronized void close() throws IOException {
        this.j.close();
    }

    @Override // java.nio.channels.Channel
    @GuardedBy("this")
    public synchronized boolean isOpen() {
        return this.j.isOpen();
    }

    @Override // java.nio.channels.SeekableByteChannel
    @GuardedBy("this")
    public synchronized SeekableByteChannel position(long j) throws IOException {
        SeekableByteChannel seekableByteChannel = this.i;
        if (seekableByteChannel != null) {
            seekableByteChannel.position(j);
        } else if (j >= 0) {
            this.k = j;
            SeekableByteChannel seekableByteChannel2 = this.h;
            if (seekableByteChannel2 != null) {
                seekableByteChannel2.position(j);
            }
        } else {
            throw new IllegalArgumentException("Position must be non-negative");
        }
        return this;
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    @GuardedBy("this")
    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() == 0) {
            return 0;
        }
        SeekableByteChannel seekableByteChannel = this.i;
        if (seekableByteChannel != null) {
            return seekableByteChannel.read(byteBuffer);
        }
        if (this.h == null) {
            this.h = a();
        }
        while (true) {
            try {
                int read = this.h.read(byteBuffer);
                if (read == 0) {
                    return 0;
                }
                this.i = this.h;
                this.h = null;
                return read;
            } catch (IOException unused) {
                this.h = a();
            }
        }
    }

    @Override // java.nio.channels.SeekableByteChannel
    @GuardedBy("this")
    public synchronized long size() throws IOException {
        SeekableByteChannel seekableByteChannel;
        seekableByteChannel = this.i;
        if (seekableByteChannel != null) {
        } else {
            throw new IOException("Cannot determine size before first read()-call.");
        }
        return seekableByteChannel.size();
    }

    @Override // java.nio.channels.SeekableByteChannel
    public SeekableByteChannel truncate(long j) throws IOException {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.SeekableByteChannel
    @GuardedBy("this")
    public synchronized long position() throws IOException {
        SeekableByteChannel seekableByteChannel = this.i;
        if (seekableByteChannel != null) {
            return seekableByteChannel.position();
        }
        return this.k;
    }
}
