package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public final class RewindableReadableByteChannel implements ReadableByteChannel {
    @GuardedBy("this")
    public final ReadableByteChannel h;
    @GuardedBy("this")
    public ByteBuffer i = null;
    @GuardedBy("this")
    public boolean j = true;
    @GuardedBy("this")
    public boolean k = false;

    public RewindableReadableByteChannel(ReadableByteChannel readableByteChannel) {
        this.h = readableByteChannel;
    }

    public final synchronized void a(int i) {
        if (this.i.capacity() < i) {
            int position = this.i.position();
            ByteBuffer allocate = ByteBuffer.allocate(Math.max(this.i.capacity() * 2, i));
            this.i.rewind();
            allocate.put(this.i);
            allocate.position(position);
            this.i = allocate;
        }
        this.i.limit(i);
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.j = false;
        this.k = true;
        this.h.close();
    }

    public synchronized void disableRewinding() {
        this.j = false;
    }

    @Override // java.nio.channels.Channel
    public synchronized boolean isOpen() {
        return this.h.isOpen();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        if (this.k) {
            return this.h.read(byteBuffer);
        }
        int remaining = byteBuffer.remaining();
        if (remaining == 0) {
            return 0;
        }
        ByteBuffer byteBuffer2 = this.i;
        if (byteBuffer2 == null) {
            if (!this.j) {
                this.k = true;
                return this.h.read(byteBuffer);
            }
            ByteBuffer allocate = ByteBuffer.allocate(remaining);
            this.i = allocate;
            int read = this.h.read(allocate);
            this.i.flip();
            if (read > 0) {
                byteBuffer.put(this.i);
            }
            return read;
        } else if (byteBuffer2.remaining() >= remaining) {
            int limit = this.i.limit();
            ByteBuffer byteBuffer3 = this.i;
            byteBuffer3.limit(byteBuffer3.position() + remaining);
            byteBuffer.put(this.i);
            this.i.limit(limit);
            if (!this.j && !this.i.hasRemaining()) {
                this.i = null;
                this.k = true;
            }
            return remaining;
        } else {
            int remaining2 = this.i.remaining();
            int position = this.i.position();
            int limit2 = this.i.limit();
            a((remaining - remaining2) + limit2);
            this.i.position(limit2);
            int read2 = this.h.read(this.i);
            this.i.flip();
            this.i.position(position);
            byteBuffer.put(this.i);
            if (remaining2 != 0 || read2 >= 0) {
                int position2 = this.i.position() - position;
                if (!this.j && !this.i.hasRemaining()) {
                    this.i = null;
                    this.k = true;
                }
                return position2;
            }
            return -1;
        }
    }

    public synchronized void rewind() throws IOException {
        if (this.j) {
            ByteBuffer byteBuffer = this.i;
            if (byteBuffer != null) {
                byteBuffer.position(0);
            }
        } else {
            throw new IOException("Cannot rewind anymore.");
        }
    }
}
