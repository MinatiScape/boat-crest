package com.google.crypto.tink.subtle;
/* loaded from: classes10.dex */
public final class ImmutableByteArray {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f11035a;

    public ImmutableByteArray(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.f11035a = bArr2;
        System.arraycopy(bArr, i, bArr2, 0, i2);
    }

    public static ImmutableByteArray of(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return of(bArr, 0, bArr.length);
    }

    public byte[] getBytes() {
        byte[] bArr = this.f11035a;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public int getLength() {
        return this.f11035a.length;
    }

    public static ImmutableByteArray of(byte[] bArr, int i, int i2) {
        return new ImmutableByteArray(bArr, i, i2);
    }
}
