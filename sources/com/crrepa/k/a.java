package com.crrepa.k;

import com.crrepa.i0.e;
import java.util.Date;
/* loaded from: classes9.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7740a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;

    public abstract int[] a(byte[] bArr);

    public Date b(byte[] bArr) {
        if (bArr.length < 5) {
            return null;
        }
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 1, bArr2, 0, 4);
        long d2 = e.d(bArr2) * 1000;
        if (d2 <= 0) {
            return null;
        }
        return new Date(d2);
    }

    public int c(byte[] bArr) {
        if (e.e(bArr)) {
            return -1;
        }
        return bArr[0];
    }
}
