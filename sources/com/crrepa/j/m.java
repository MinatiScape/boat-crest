package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPPillReminderInfo;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class m {
    public static String a(byte[] bArr) {
        int i;
        int length = bArr.length;
        while (true) {
            length--;
            if (length < 0) {
                i = 0;
                break;
            } else if (bArr[length] != 0) {
                i = length + 1;
                break;
            }
        }
        try {
            return new String(bArr, 0, i, StandardCharsets.UTF_8);
        } catch (Exception unused) {
            return "";
        }
    }

    public static List<CRPPillReminderInfo> b(byte[] bArr) {
        if (bArr.length < 58) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < bArr.length; i += 58) {
            CRPPillReminderInfo cRPPillReminderInfo = new CRPPillReminderInfo();
            cRPPillReminderInfo.setId(bArr[i]);
            cRPPillReminderInfo.setDateOffset(bArr[i + 1]);
            byte[] bArr2 = new byte[31];
            System.arraycopy(bArr, i + 2, bArr2, 0, 31);
            cRPPillReminderInfo.setName(a(bArr2));
            cRPPillReminderInfo.setRepeat(bArr[i + 33]);
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < 24; i2 += 3) {
                int i3 = i + 34 + i2;
                byte b = bArr[i3 + 2];
                if (b > 0) {
                    arrayList2.add(new CRPPillReminderInfo.ReminderTimeBean((bArr[i3] * 60) + bArr[i3 + 1], b));
                }
            }
            cRPPillReminderInfo.setReminderTimeList(arrayList2);
            arrayList.add(cRPPillReminderInfo);
        }
        return arrayList;
    }
}
