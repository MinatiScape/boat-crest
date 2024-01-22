package androidx.camera.core.impl.utils;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;
/* loaded from: classes.dex */
public class c extends FilterOutputStream {
    public final OutputStream h;
    public ByteOrder i;

    public c(OutputStream outputStream, ByteOrder byteOrder) {
        super(outputStream);
        this.h = outputStream;
        this.i = byteOrder;
    }

    public void a(ByteOrder byteOrder) {
        this.i = byteOrder;
    }

    public void b(int i) throws IOException {
        this.h.write(i);
    }

    public void c(int i) throws IOException {
        ByteOrder byteOrder = this.i;
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            this.h.write((i >>> 0) & 255);
            this.h.write((i >>> 8) & 255);
            this.h.write((i >>> 16) & 255);
            this.h.write((i >>> 24) & 255);
        } else if (byteOrder == ByteOrder.BIG_ENDIAN) {
            this.h.write((i >>> 24) & 255);
            this.h.write((i >>> 16) & 255);
            this.h.write((i >>> 8) & 255);
            this.h.write((i >>> 0) & 255);
        }
    }

    public void d(short s) throws IOException {
        ByteOrder byteOrder = this.i;
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            this.h.write((s >>> 0) & 255);
            this.h.write((s >>> 8) & 255);
        } else if (byteOrder == ByteOrder.BIG_ENDIAN) {
            this.h.write((s >>> 8) & 255);
            this.h.write((s >>> 0) & 255);
        }
    }

    public void e(long j) throws IOException {
        c((int) j);
    }

    public void f(int i) throws IOException {
        d((short) i);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.h.write(bArr);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.h.write(bArr, i, i2);
    }
}
