package com.apex.bluetooth.data_package.c;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final String f2209a = a.class.getSimpleName();

    public final byte[] a(byte[] bArr, int i) {
        if (bArr != null) {
            byte[] bArr2 = new byte[bArr.length + 2];
            bArr2[0] = (byte) (i & 255);
            bArr2[1] = (byte) ((i >> 8) & 255);
            System.arraycopy(bArr, 0, bArr2, 2, bArr.length);
            return bArr2;
        }
        return null;
    }
}
