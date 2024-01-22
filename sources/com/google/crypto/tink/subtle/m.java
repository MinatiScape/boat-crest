package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public class m implements WritableByteChannel {
    public WritableByteChannel h;
    public StreamSegmentEncrypter i;
    public ByteBuffer j;
    public ByteBuffer k;
    public int l;
    public boolean m = true;

    public m(i iVar, WritableByteChannel writableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        this.h = writableByteChannel;
        this.i = iVar.newStreamSegmentEncrypter(bArr);
        int plaintextSegmentSize = iVar.getPlaintextSegmentSize();
        this.l = plaintextSegmentSize;
        ByteBuffer allocate = ByteBuffer.allocate(plaintextSegmentSize);
        this.j = allocate;
        allocate.limit(this.l - iVar.getCiphertextOffset());
        ByteBuffer allocate2 = ByteBuffer.allocate(iVar.getCiphertextSegmentSize());
        this.k = allocate2;
        allocate2.put(this.i.getHeader());
        this.k.flip();
        writableByteChannel.write(this.k);
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.m) {
            while (this.k.remaining() > 0) {
                if (this.h.write(this.k) <= 0) {
                    throw new IOException("Failed to write ciphertext before closing");
                }
            }
            try {
                this.k.clear();
                this.j.flip();
                this.i.encryptSegment(this.j, true, this.k);
                this.k.flip();
                while (this.k.remaining() > 0) {
                    if (this.h.write(this.k) <= 0) {
                        throw new IOException("Failed to write ciphertext before closing");
                    }
                }
                this.h.close();
                this.m = false;
            } catch (GeneralSecurityException e) {
                throw new IOException(e);
            }
        }
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.m;
    }

    @Override // java.nio.channels.WritableByteChannel
    public synchronized int write(ByteBuffer byteBuffer) throws IOException {
        if (this.m) {
            if (this.k.remaining() > 0) {
                this.h.write(this.k);
            }
            int position = byteBuffer.position();
            while (byteBuffer.remaining() > this.j.remaining()) {
                if (this.k.remaining() > 0) {
                    return byteBuffer.position() - position;
                }
                int remaining = this.j.remaining();
                ByteBuffer slice = byteBuffer.slice();
                slice.limit(remaining);
                byteBuffer.position(byteBuffer.position() + remaining);
                try {
                    this.j.flip();
                    this.k.clear();
                    if (slice.remaining() != 0) {
                        this.i.encryptSegment(this.j, slice, false, this.k);
                    } else {
                        this.i.encryptSegment(this.j, false, this.k);
                    }
                    this.k.flip();
                    this.h.write(this.k);
                    this.j.clear();
                    this.j.limit(this.l);
                } catch (GeneralSecurityException e) {
                    throw new IOException(e);
                }
            }
            this.j.put(byteBuffer);
            return byteBuffer.position() - position;
        }
        throw new ClosedChannelException();
    }
}
