package com.crrepa.f;

import com.crrepa.ble.conn.bean.CRPPillReminderInfo;
import java.nio.charset.StandardCharsets;
import java.util.List;
/* loaded from: classes9.dex */
public class h0 {
    public static final int b = 255;

    public static String a(String str) {
        int i = 31;
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            int i4 = i2 + 1;
            i -= str.substring(i2, i4).getBytes(StandardCharsets.UTF_8).length;
            if (i <= 0) {
                return str.substring(0, i3);
            }
            i3++;
            i2 = i4;
        }
        return str;
    }

    public static byte[] a() {
        return d1.a(-86, null);
    }

    public static byte[] a(int i) {
        return d1.a(-102, new byte[]{-1, (byte) i});
    }

    public static byte[] a(CRPPillReminderInfo cRPPillReminderInfo) {
        byte[] bArr = new byte[58];
        bArr[0] = (byte) cRPPillReminderInfo.getId();
        bArr[1] = (byte) cRPPillReminderInfo.getDateOffset();
        byte[] bytes = a(cRPPillReminderInfo.getName()).getBytes(StandardCharsets.UTF_8);
        System.arraycopy(bytes, 0, bArr, 2, bytes.length);
        bArr[33] = (byte) cRPPillReminderInfo.getRepeat();
        List<CRPPillReminderInfo.ReminderTimeBean> reminderTimeList = cRPPillReminderInfo.getReminderTimeList();
        if (8 < reminderTimeList.size()) {
            reminderTimeList.subList(0, 8);
        }
        for (int i = 0; i < reminderTimeList.size(); i++) {
            CRPPillReminderInfo.ReminderTimeBean reminderTimeBean = reminderTimeList.get(i);
            int time = reminderTimeBean.getTime();
            int i2 = (i * 3) + 34;
            bArr[i2] = (byte) (time / 60);
            bArr[i2 + 1] = (byte) (time % 60);
            bArr[i2 + 2] = (byte) reminderTimeBean.getCount();
        }
        return d1.a(-102, bArr);
    }
}
