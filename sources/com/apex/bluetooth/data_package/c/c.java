package com.apex.bluetooth.data_package.c;
/* loaded from: classes.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public a f2211a = new a();

    public final byte[] a(byte[] bArr) {
        byte[] bArr2;
        if (bArr != null) {
            int length = bArr.length;
            bArr2 = new byte[length + 2];
            bArr2[0] = (byte) (length & 255);
            bArr2[1] = (byte) ((length >> 8) & 255);
            System.arraycopy(bArr, 0, bArr2, 2, length);
        } else {
            bArr2 = null;
        }
        if (bArr2 != null) {
            int length2 = bArr2.length + 2;
            byte[] bArr3 = new byte[length2];
            bArr3[0] = com.crrepa.c.a.A;
            bArr3[length2 - 1] = -17;
            System.arraycopy(bArr2, 0, bArr3, 1, bArr2.length);
            return bArr3;
        }
        return null;
    }
}
