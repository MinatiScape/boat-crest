package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes13.dex */
public class ByteQueue {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14846a;
    public int b;
    public int c;
    public boolean d;

    public ByteQueue() {
        this(1024);
    }

    public ByteQueue(int i) {
        this.b = 0;
        this.c = 0;
        this.d = false;
        this.f14846a = i == 0 ? TlsUtils.EMPTY_BYTES : new byte[i];
    }

    public ByteQueue(byte[] bArr, int i, int i2) {
        this.b = 0;
        this.c = 0;
        this.d = false;
        this.f14846a = bArr;
        this.b = i;
        this.c = i2;
        this.d = true;
    }

    public static int nextTwoPow(int i) {
        int i2 = i | (i >> 1);
        int i3 = i2 | (i2 >> 2);
        int i4 = i3 | (i3 >> 4);
        int i5 = i4 | (i4 >> 8);
        return (i5 | (i5 >> 16)) + 1;
    }

    public void addData(byte[] bArr, int i, int i2) {
        if (this.d) {
            throw new IllegalStateException("Cannot add data to read-only buffer");
        }
        int i3 = this.b;
        int i4 = this.c;
        if (i3 + i4 + i2 > this.f14846a.length) {
            int nextTwoPow = nextTwoPow(i4 + i2);
            byte[] bArr2 = this.f14846a;
            if (nextTwoPow > bArr2.length) {
                byte[] bArr3 = new byte[nextTwoPow];
                System.arraycopy(bArr2, this.b, bArr3, 0, this.c);
                this.f14846a = bArr3;
            } else {
                System.arraycopy(bArr2, this.b, bArr2, 0, this.c);
            }
            this.b = 0;
        }
        System.arraycopy(bArr, i, this.f14846a, this.b + this.c, i2);
        this.c += i2;
    }

    public int available() {
        return this.c;
    }

    public void copyTo(OutputStream outputStream, int i) throws IOException {
        if (i <= this.c) {
            outputStream.write(this.f14846a, this.b, i);
            return;
        }
        throw new IllegalStateException("Cannot copy " + i + " bytes, only got " + this.c);
    }

    public void read(byte[] bArr, int i, int i2, int i3) {
        if (bArr.length - i >= i2) {
            if (this.c - i3 < i2) {
                throw new IllegalStateException("Not enough data to read");
            }
            System.arraycopy(this.f14846a, this.b + i3, bArr, i, i2);
            return;
        }
        throw new IllegalArgumentException("Buffer size of " + bArr.length + " is too small for a read of " + i2 + " bytes");
    }

    public ByteArrayInputStream readFrom(int i) {
        int i2 = this.c;
        if (i <= i2) {
            int i3 = this.b;
            this.c = i2 - i;
            this.b = i3 + i;
            return new ByteArrayInputStream(this.f14846a, i3, i);
        }
        throw new IllegalStateException("Cannot read " + i + " bytes, only got " + this.c);
    }

    public void removeData(int i) {
        int i2 = this.c;
        if (i <= i2) {
            this.c = i2 - i;
            this.b += i;
            return;
        }
        throw new IllegalStateException("Cannot remove " + i + " bytes, only got " + this.c);
    }

    public void removeData(byte[] bArr, int i, int i2, int i3) {
        read(bArr, i, i2, i3);
        removeData(i3 + i2);
    }

    public byte[] removeData(int i, int i2) {
        byte[] bArr = new byte[i];
        removeData(bArr, 0, i, i2);
        return bArr;
    }

    public void shrink() {
        int i = this.c;
        if (i == 0) {
            this.f14846a = TlsUtils.EMPTY_BYTES;
        } else {
            int nextTwoPow = nextTwoPow(i);
            byte[] bArr = this.f14846a;
            if (nextTwoPow >= bArr.length) {
                return;
            }
            byte[] bArr2 = new byte[nextTwoPow];
            System.arraycopy(bArr, this.b, bArr2, 0, this.c);
            this.f14846a = bArr2;
        }
        this.b = 0;
    }
}
