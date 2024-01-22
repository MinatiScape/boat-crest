package com.crrepa.f;

import com.crrepa.ble.conn.bean.CRPAlarmClockInfo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/* loaded from: classes9.dex */
public class b {
    public static Date a(int i) {
        if (i == 0) {
            return null;
        }
        String binaryString = Integer.toBinaryString(i);
        int length = 16 - binaryString.length();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            sb.append(0);
        }
        sb.append(binaryString);
        String sb2 = sb.toString();
        String substring = sb2.substring(0, 4);
        String substring2 = sb2.substring(4, 8);
        int b = com.crrepa.i0.e.b(sb2.substring(8, 16));
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, com.crrepa.i0.e.b(substring) + 2015);
        calendar.set(2, com.crrepa.i0.e.b(substring2) - 1);
        calendar.set(5, b);
        return calendar.getTime();
    }

    public static List<CRPAlarmClockInfo> a(byte[] bArr) {
        Date date;
        if (com.crrepa.i0.e.e(bArr)) {
            return null;
        }
        byte[] bArr2 = new byte[8];
        int length = bArr.length / 8;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            System.arraycopy(bArr, 8 * i, bArr2, 0, 8);
            com.crrepa.i0.c.c("alarm bytes: " + com.crrepa.i0.e.c(bArr2));
            boolean z = bArr2[1] == 1;
            byte b = bArr2[2];
            byte b2 = bArr2[3];
            byte b3 = bArr2[4];
            byte b4 = bArr2[7];
            if (b == 1) {
                date = null;
                b4 = Byte.MAX_VALUE;
            } else if (b == 0) {
                b4 = 0;
                date = a(com.crrepa.i0.e.b(bArr2[5], bArr2[6]));
            } else {
                date = null;
            }
            CRPAlarmClockInfo cRPAlarmClockInfo = new CRPAlarmClockInfo(i, b2, b3, b4, z);
            cRPAlarmClockInfo.setDate(date);
            arrayList.add(cRPAlarmClockInfo);
        }
        return arrayList;
    }

    public static byte[] a() {
        return d1.a(33, null);
    }

    public static byte[] a(CRPAlarmClockInfo cRPAlarmClockInfo) {
        int i;
        byte[] bArr = new byte[8];
        bArr[0] = (byte) cRPAlarmClockInfo.getId();
        byte b = 1;
        bArr[1] = cRPAlarmClockInfo.isEnable() ? (byte) 1 : (byte) 0;
        int repeatMode = cRPAlarmClockInfo.getRepeatMode();
        if (repeatMode == 0) {
            Calendar calendar = Calendar.getInstance();
            if (cRPAlarmClockInfo.getDate() != null) {
                calendar.setTime(cRPAlarmClockInfo.getDate());
            }
            i = ((calendar.get(1) - 2015) << 12) + ((calendar.get(2) + 1) << 8) + calendar.get(5);
            b = 0;
        } else {
            i = 0;
            if (repeatMode != 127) {
                b = 2;
            }
        }
        bArr[2] = b;
        bArr[3] = (byte) cRPAlarmClockInfo.getHour();
        bArr[4] = (byte) cRPAlarmClockInfo.getMinute();
        System.arraycopy(com.crrepa.i0.e.a(i), 0, bArr, 5, 2);
        bArr[7] = (byte) repeatMode;
        return d1.a(17, bArr);
    }
}
