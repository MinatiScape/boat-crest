package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPHistoryTrainingInfo;
import com.crrepa.ble.conn.bean.CRPTrainingInfo;
import com.crrepa.f.w0;
import com.crrepa.f.x0;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class w {

    /* renamed from: a  reason: collision with root package name */
    public static CRPTrainingInfo f7738a;
    public static List<Integer> b = new ArrayList();

    public static List<CRPHistoryTrainingInfo> a(byte[] bArr) {
        if (bArr.length % 5 != 1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < bArr.length; i += 5) {
            byte[] bArr2 = new byte[4];
            System.arraycopy(bArr, i, bArr2, 0, 4);
            arrayList.add(new CRPHistoryTrainingInfo(w0.a(com.crrepa.i0.e.d(bArr2) * 1000), bArr[4]));
        }
        return arrayList;
    }

    public static void b(int i, int i2) {
        com.crrepa.m.f.d().a(x0.a(i, i2));
    }

    public static void b(byte[] bArr) {
        if (bArr.length < 26) {
            return;
        }
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 2, bArr2, 0, 4);
        long a2 = w0.a(com.crrepa.i0.e.d(bArr2) * 1000);
        System.arraycopy(bArr, 6, bArr2, 0, 4);
        long a3 = w0.a(com.crrepa.i0.e.d(bArr2) * 1000);
        int b2 = com.crrepa.i0.e.b(bArr[11], bArr[10]);
        byte b3 = bArr[13];
        System.arraycopy(bArr, 14, bArr2, 0, 4);
        System.arraycopy(bArr, 18, bArr2, 0, 4);
        int b4 = com.crrepa.i0.e.b(bArr[23], bArr[22]);
        CRPTrainingInfo cRPTrainingInfo = new CRPTrainingInfo();
        f7738a = cRPTrainingInfo;
        cRPTrainingInfo.setType(b3);
        f7738a.setStartTime(a2);
        f7738a.setEndTime(a3);
        f7738a.setValidTime(b2);
        f7738a.setSteps((int) com.crrepa.i0.e.d(bArr2));
        f7738a.setDistance((int) com.crrepa.i0.e.d(bArr2));
        f7738a.setCalories(b4);
        b.clear();
        b(bArr[1], 0);
    }

    public static CRPTrainingInfo c(byte[] bArr) {
        if (bArr.length < 4) {
            return null;
        }
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, 2, bArr2, 0, 2);
        int b2 = com.crrepa.i0.e.b(bArr2[0], bArr2[1]);
        com.crrepa.i0.c.a("offset: " + b2);
        for (int i = 4; i < bArr.length; i++) {
            b.add(Integer.valueOf(com.crrepa.i0.e.a(bArr[i])));
        }
        if (b2 == 65535) {
            f7738a.setHrList(b);
            return f7738a;
        }
        b(bArr[1], b2);
        return null;
    }
}
