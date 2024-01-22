package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPSleepInfo;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class s {
    public static int a(int i, int i2, int i3, int i4) {
        if (i > i3) {
            i3 += 24;
        }
        return (((i3 - i) * 60) + i4) - i2;
    }

    public static CRPSleepInfo.DetailBean a(int i, int i2, int i3, int i4, int i5, int i6) {
        CRPSleepInfo.DetailBean detailBean = new CRPSleepInfo.DetailBean();
        detailBean.setTotalTime(i6);
        detailBean.setType(i);
        detailBean.setStartTime(com.crrepa.i0.h.a(i2, i3));
        detailBean.setEndTime(com.crrepa.i0.h.a(i4, i5));
        return detailBean;
    }

    public static CRPSleepInfo a(byte[] bArr, boolean z) {
        byte b;
        CRPSleepInfo cRPSleepInfo = new CRPSleepInfo();
        if (bArr != null) {
            int i = 3;
            if (bArr.length % 3 == 0) {
                int length = bArr.length;
                byte[] bArr2 = new byte[3];
                byte b2 = -1;
                int i2 = 0;
                byte b3 = 0;
                int i3 = 0;
                while (i3 < length) {
                    System.arraycopy(bArr, i3, bArr2, 0, i);
                    int i4 = bArr2[1] % 24;
                    byte b4 = bArr2[2];
                    int a2 = a(i2, b3, i4, b4);
                    if (b2 >= 0) {
                        b(cRPSleepInfo, b2, i2, b3, i4, b4, a2);
                    }
                    byte b5 = bArr2[0];
                    if (z || i3 != length - 3 || b5 == 0) {
                        b = b5;
                    } else {
                        int parseInt = Integer.parseInt(com.crrepa.i0.h.a("HH"));
                        int parseInt2 = Integer.parseInt(com.crrepa.i0.h.a("mm"));
                        a2 = a(i4, b4, parseInt, parseInt2);
                        b = b5;
                        b(cRPSleepInfo, b5, i4, b4, parseInt, parseInt2, a2);
                    }
                    com.crrepa.i0.c.c("state: " + ((int) b) + " light: " + a2);
                    i3 += 3;
                    b2 = b;
                    i2 = i4;
                    b3 = b4;
                    i = 3;
                }
                cRPSleepInfo.setTotalTime(cRPSleepInfo.getRestfulTime() + cRPSleepInfo.getLightTime());
            }
        }
        return cRPSleepInfo;
    }

    public static void b(CRPSleepInfo cRPSleepInfo, int i, int i2, int i3, int i4, int i5, int i6) {
        if (i == 0) {
            cRPSleepInfo.setAwakeTime(cRPSleepInfo.getAwakeTime() + i6);
        } else if (i == 1) {
            cRPSleepInfo.setLightTime(cRPSleepInfo.getLightTime() + i6);
        } else if (i == 2) {
            cRPSleepInfo.setRestfulTime(cRPSleepInfo.getRestfulTime() + i6);
        }
        CRPSleepInfo.DetailBean a2 = a(i, i2, i3, i4, i5, i6);
        if (cRPSleepInfo.getDetails() == null) {
            cRPSleepInfo.setDetails(new ArrayList());
        }
        if (a2 != null) {
            cRPSleepInfo.getDetails().add(a2);
        }
    }
}
