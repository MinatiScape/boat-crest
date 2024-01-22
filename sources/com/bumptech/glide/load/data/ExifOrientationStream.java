package com.bumptech.glide.load.data;

import androidx.annotation.NonNull;
import com.crrepa.c.a;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public final class ExifOrientationStream extends FilterInputStream {
    public static final byte[] j;
    public static final int k;
    public static final int l;
    public final byte h;
    public int i;

    static {
        byte[] bArr = {-1, -31, 0, 28, a.E0, 120, 105, 102, 0, 0, 77, 77, 0, 0, 0, 0, 0, 8, 0, 1, 1, 18, 0, 2, 0, 0, 0, 1, 0};
        j = bArr;
        int length = bArr.length;
        k = length;
        l = length + 2;
    }

    public ExifOrientationStream(InputStream inputStream, int i) {
        super(inputStream);
        if (i >= -1 && i <= 8) {
            this.h = (byte) i;
            return;
        }
        throw new IllegalArgumentException("Cannot add invalid orientation: " + i);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read;
        int i;
        int i2 = this.i;
        if (i2 < 2 || i2 > (i = l)) {
            read = super.read();
        } else if (i2 == i) {
            read = this.h;
        } else {
            read = j[i2 - 2] & 255;
        }
        if (read != -1) {
            this.i++;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j2) throws IOException {
        long skip = super.skip(j2);
        if (skip > 0) {
            this.i = (int) (this.i + skip);
        }
        return skip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(@NonNull byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4 = this.i;
        int i5 = l;
        if (i4 > i5) {
            i3 = super.read(bArr, i, i2);
        } else if (i4 == i5) {
            bArr[i] = this.h;
            i3 = 1;
        } else if (i4 < 2) {
            i3 = super.read(bArr, i, 2 - i4);
        } else {
            int min = Math.min(i5 - i4, i2);
            System.arraycopy(j, this.i - 2, bArr, i, min);
            i3 = min;
        }
        if (i3 > 0) {
            this.i += i3;
        }
        return i3;
    }
}
