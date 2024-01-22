package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPMovementHeartRateInfo;
import com.crrepa.f.w0;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class j {
    public static List<CRPMovementHeartRateInfo> a(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr) || bArr.length != 72) {
            return null;
        }
        int length = bArr.length / 3;
        byte[] bArr2 = new byte[length];
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 3; i++) {
            System.arraycopy(bArr, length * i, bArr2, 0, length);
            com.crrepa.i0.c.c("heart rate data: " + com.crrepa.i0.e.c(bArr2));
            arrayList.add(b(bArr2));
        }
        return arrayList;
    }

    public static CRPMovementHeartRateInfo b(byte[] bArr) {
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 0, bArr2, 0, 4);
        long a2 = w0.a(com.crrepa.i0.e.d(bArr2) * 1000);
        System.arraycopy(bArr, 4, bArr2, 0, 4);
        long a3 = w0.a(com.crrepa.i0.e.d(bArr2) * 1000);
        int b = com.crrepa.i0.e.b(bArr[9], bArr[8]);
        byte b2 = bArr[11];
        System.arraycopy(bArr, 12, bArr2, 0, 4);
        System.arraycopy(bArr, 16, bArr2, 0, 4);
        int b3 = com.crrepa.i0.e.b(bArr[21], bArr[20]);
        CRPMovementHeartRateInfo cRPMovementHeartRateInfo = new CRPMovementHeartRateInfo();
        cRPMovementHeartRateInfo.setType(b2);
        cRPMovementHeartRateInfo.setStartTime(a2);
        cRPMovementHeartRateInfo.setEndTime(a3);
        cRPMovementHeartRateInfo.setValidTime(b);
        cRPMovementHeartRateInfo.setSteps((int) com.crrepa.i0.e.d(bArr2));
        cRPMovementHeartRateInfo.setDistance((int) com.crrepa.i0.e.d(bArr2));
        cRPMovementHeartRateInfo.setCalories(b3);
        return cRPMovementHeartRateInfo;
    }
}
