package com.htsmart.wristband2.packet;

import androidx.annotation.NonNull;
import com.htsmart.wristband2.bean.data.ICalculateSleepItem;
import java.util.List;
/* loaded from: classes11.dex */
public class SleepCalculateHelper {
    public static int[] calculateDuration(@NonNull List<? extends ICalculateSleepItem> list) {
        int i;
        boolean z;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        int i2 = 0;
        boolean z2 = false;
        while (i2 < list.size()) {
            ICalculateSleepItem iCalculateSleepItem = list.get(i2);
            int calculateStatus = iCalculateSleepItem.getCalculateStatus();
            if (calculateStatus != 3 || z2) {
                long calculateEndTime = iCalculateSleepItem.getCalculateEndTime() - iCalculateSleepItem.getCalculateStartTime();
                if (calculateEndTime <= 0) {
                    list.remove(i2);
                    i2--;
                } else if (calculateStatus == 3) {
                    j4 += calculateEndTime;
                } else {
                    j += j4;
                    i = 1;
                    if (calculateStatus == 1) {
                        j3 += calculateEndTime;
                    } else if (calculateStatus == 2) {
                        j2 += calculateEndTime;
                    }
                    j4 = 0;
                    z = true;
                }
                i = 1;
                z = true;
            } else {
                z = z2;
                i = 1;
            }
            i2 += i;
            z2 = z;
        }
        return new int[]{(int) (j3 / 1000), (int) (j2 / 1000), (int) (j / 1000)};
    }
}
