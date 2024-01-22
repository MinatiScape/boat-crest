package com.google.crypto.tink.subtle;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public class n extends FilterOutputStream {
    public StreamSegmentEncrypter h;
    public int i;
    public ByteBuffer j;
    public ByteBuffer k;
    public boolean l;

    public n(i iVar, OutputStream outputStream, byte[] bArr) throws GeneralSecurityException, IOException {
        super(outputStream);
        this.h = iVar.newStreamSegmentEncrypter(bArr);
        int plaintextSegmentSize = iVar.getPlaintextSegmentSize();
        this.i = plaintextSegmentSize;
        this.j = ByteBuffer.allocate(plaintextSegmentSize);
        this.k = ByteBuffer.allocate(iVar.getCiphertextSegmentSize());
        this.j.limit(this.i - iVar.getCiphertextOffset());
        ByteBuffer header = this.h.getHeader();
        byte[] bArr2 = new byte[header.remaining()];
        header.get(bArr2);
        ((FilterOutputStream) this).out.write(bArr2);
        this.l = true;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.l) {
            try {
                this.j.flip();
                this.k.clear();
                this.h.encryptSegment(this.j, true, this.k);
                this.k.flip();
                ((FilterOutputStream) this).out.write(this.k.array(), this.k.position(), this.k.remaining());
                this.l = false;
                super.close();
            } catch (GeneralSecurityException e) {
                throw new IOException("ptBuffer.remaining():" + this.j.remaining() + " ctBuffer.remaining():" + this.k.remaining(), e);
            }
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.l) {
            while (i2 > this.j.remaining()) {
                int remaining = this.j.remaining();
                ByteBuffer wrap = ByteBuffer.wrap(bArr, i, remaining);
                i += remaining;
                i2 -= remaining;
                try {
                    this.j.flip();
                    this.k.clear();
                    this.h.encryptSegment(this.j, wrap, false, this.k);
                    this.k.flip();
                    ((FilterOutputStream) this).out.write(this.k.array(), this.k.position(), this.k.remaining());
                    this.j.clear();
                    this.j.limit(this.i);
                } catch (GeneralSecurityException e) {
                    throw new IOException(e);
                }
            }
            this.j.put(bArr, i, i2);
        } else {
            throw new IOException("Trying to write to closed stream");
        }
    }
}
