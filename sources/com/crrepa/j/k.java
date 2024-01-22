package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPPeriodTimeInfo;
/* loaded from: classes9.dex */
public class k {
    public static CRPPeriodTimeInfo a(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr) || bArr.length != 4) {
            return null;
        }
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, 0, bArr2, 0, 2);
        long b = com.crrepa.i0.e.b(bArr2[1], bArr2[0]);
        System.arraycopy(bArr, 2, bArr2, 0, 2);
        long b2 = com.crrepa.i0.e.b(bArr2[1], bArr2[0]);
        return new CRPPeriodTimeInfo((int) (b / 60), (int) (b % 60), (int) (b2 / 60), (int) (b2 % 60));
    }
}
