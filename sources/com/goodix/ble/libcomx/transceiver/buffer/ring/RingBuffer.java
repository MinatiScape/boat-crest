package com.goodix.ble.libcomx.transceiver.buffer.ring;
/* loaded from: classes5.dex */
public class RingBuffer implements IRingBufferMutable {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f8046a;
    public int b;
    public int c;

    public RingBuffer(int i) {
        this.f8046a = new byte[i];
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.ring.IRingBufferMutable
    public void drop(int i) {
        int i2 = this.b;
        int i3 = this.c;
        int i4 = i2 - i3;
        if (i4 >= 0) {
            if (i < i4) {
                this.c = i3 + i;
                return;
            } else {
                this.c = i2;
                return;
            }
        }
        byte[] bArr = this.f8046a;
        if (i < bArr.length + i4) {
            this.c = (i3 + i) % bArr.length;
        } else {
            this.c = i2;
        }
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.ring.IRingBufferMutable
    public void dropAll() {
        this.c = this.b;
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.ring.IRingBuffer
    public int get(int i) {
        int i2 = i + this.c;
        byte[] bArr = this.f8046a;
        if (i2 >= bArr.length) {
            i2 %= bArr.length;
        }
        return bArr[i2] & 255;
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.ring.IRingBuffer
    public int getFreeSize() {
        int length = (this.f8046a.length - getSize()) - 1;
        if (length < 0) {
            return 0;
        }
        return length;
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.ring.IRingBuffer
    public int getIntValue(int i, int i2, boolean z) {
        int size = getSize();
        if (size <= 0) {
            return 0;
        }
        if (i > size) {
            i %= size;
        }
        int i3 = i + this.c;
        if (i2 > 4) {
            i2 = 4;
        }
        if (size > i2) {
            size = i2;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            int i6 = i3 + i5;
            byte[] bArr = this.f8046a;
            if (i6 >= bArr.length) {
                i6 -= bArr.length;
            }
            i4 = (i4 << 8) | (bArr[i6] & 255);
        }
        if (z) {
            return i4;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            i7 = (i7 << 8) | (i4 & 255);
            i4 >>= 8;
        }
        return i7;
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.ring.IRingBuffer
    public int getReadPos() {
        return this.c;
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.ring.IRingBuffer
    public int getSize() {
        int i = this.b - this.c;
        return i < 0 ? i + this.f8046a.length : i;
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.ring.IRingBuffer
    public int getWritePos() {
        return this.b;
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.ring.IRingBufferMutable
    public int pop(int i, byte[] bArr) {
        int i2 = get(i, bArr);
        drop(i + i2);
        return i2;
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.ring.IRingBufferMutable
    public int put(byte[] bArr, int i, int i2) {
        int length;
        int i3 = 0;
        if (bArr != null && (this.f8046a.length - getSize()) - 1 > 0) {
            if (i2 <= 0) {
                i2 = bArr.length;
            }
            if (i2 <= length) {
                length = i2;
            }
            while (i3 < length) {
                put(bArr[i]);
                i3++;
                i++;
            }
            return length;
        }
        return 0;
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.ring.IRingBufferMutable
    public boolean put(byte b) {
        int i = this.b;
        int i2 = i + 1;
        byte[] bArr = this.f8046a;
        if (i2 >= bArr.length) {
            i2 -= bArr.length;
        }
        if (i2 != this.c) {
            bArr[i] = b;
            this.b = i2;
            return true;
        }
        return false;
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.ring.IRingBuffer
    public int get(int i, byte[] bArr) {
        int size;
        if (bArr != null && (size = getSize()) > 0) {
            if (i > size) {
                i %= size;
            }
            int i2 = i + this.c;
            if (size > bArr.length) {
                size = bArr.length;
            }
            for (int i3 = 0; i3 < size; i3++) {
                int i4 = i2 + i3;
                byte[] bArr2 = this.f8046a;
                if (i4 >= bArr2.length) {
                    i4 -= bArr2.length;
                }
                bArr[i3] = bArr2[i4];
            }
            return size;
        }
        return 0;
    }
}
