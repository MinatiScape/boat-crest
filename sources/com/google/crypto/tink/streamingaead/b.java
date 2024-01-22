package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.subtle.RewindableReadableByteChannel;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.security.GeneralSecurityException;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public final class b implements ReadableByteChannel {
    @GuardedBy("this")
    public RewindableReadableByteChannel j;
    public byte[] l;
    @GuardedBy("this")
    public ReadableByteChannel h = null;
    @GuardedBy("this")
    public ReadableByteChannel i = null;
    public Deque<StreamingAead> k = new ArrayDeque();

    public b(PrimitiveSet<StreamingAead> primitiveSet, ReadableByteChannel readableByteChannel, byte[] bArr) {
        for (PrimitiveSet.Entry<StreamingAead> entry : primitiveSet.getRawPrimitives()) {
            this.k.add(entry.getPrimitive());
        }
        this.j = new RewindableReadableByteChannel(readableByteChannel);
        this.l = (byte[]) bArr.clone();
    }

    @GuardedBy("this")
    public final synchronized ReadableByteChannel a() throws IOException {
        while (!this.k.isEmpty()) {
            try {
            } catch (GeneralSecurityException unused) {
                this.j.rewind();
            }
        }
        throw new IOException("No matching key found for the ciphertext in the stream.");
        return this.k.removeFirst().newDecryptingChannel(this.j, this.l);
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.j.close();
    }

    @Override // java.nio.channels.Channel
    public synchronized boolean isOpen() {
        return this.j.isOpen();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() == 0) {
            return 0;
        }
        ReadableByteChannel readableByteChannel = this.i;
        if (readableByteChannel != null) {
            return readableByteChannel.read(byteBuffer);
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
                this.j.disableRewinding();
                return read;
            } catch (IOException unused) {
                this.j.rewind();
                this.h = a();
            }
        }
    }
}
