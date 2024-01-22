package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPHrvInfo;
import java.util.ArrayList;
import java.util.Date;
/* loaded from: classes9.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7734a = 1;
    public static final int b = 2;
    public static final int c = 3;

    public static CRPHrvInfo a(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length < 6) {
                return null;
            }
            CRPHrvInfo cRPHrvInfo = new CRPHrvInfo();
            byte[] bArr2 = new byte[4];
            System.arraycopy(bArr, 0, bArr2, 0, 4);
            cRPHrvInfo.setDate(new Date(com.crrepa.i0.e.d(bArr2) * 1000));
            cRPHrvInfo.setAtiveLevel(bArr[5]);
            if (6 < bArr.length) {
                ArrayList arrayList = new ArrayList();
                for (int i = 6; i < bArr.length; i++) {
                    arrayList.add(Integer.valueOf(bArr[i]));
                }
                cRPHrvInfo.setRriList(arrayList);
            }
            return cRPHrvInfo;
        }
        return null;
    }
}
