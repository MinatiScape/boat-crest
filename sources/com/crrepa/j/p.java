package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPSleepInfo;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class p {
    public static int a(int i, int i2) {
        int i3 = i % 3;
        return i2 == 0 ? i3 + 18 : i2 + i3;
    }

    public static CRPSleepInfo a(CRPSleepInfo cRPSleepInfo) {
        int i;
        if (cRPSleepInfo != null && cRPSleepInfo.getDetails() != null && !cRPSleepInfo.getDetails().isEmpty()) {
            List<CRPSleepInfo.DetailBean> details = cRPSleepInfo.getDetails();
            ArrayList arrayList = new ArrayList();
            int i2 = 70;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < details.size(); i6++) {
                CRPSleepInfo.DetailBean detailBean = details.get(i6);
                int totalTime = detailBean.getTotalTime();
                i4 += totalTime;
                if (i6 != 0) {
                    int type = detailBean.getType();
                    int type2 = details.get(i6 - 1).getType();
                    if (type == 1 && type2 == 2 && (i = i4 - totalTime) >= i2) {
                        i5 = a(i, i5);
                        if (totalTime <= i5) {
                            detailBean.setType(3);
                        } else {
                            int startTime = detailBean.getStartTime();
                            int i7 = startTime + i5;
                            CRPSleepInfo.DetailBean detailBean2 = new CRPSleepInfo.DetailBean();
                            detailBean2.setStartTime(startTime);
                            detailBean2.setEndTime(i7);
                            detailBean2.setType(3);
                            detailBean2.setTotalTime(i5);
                            arrayList.add(detailBean2);
                            detailBean.setStartTime(i7);
                            detailBean.setTotalTime(detailBean.getTotalTime() - i5);
                        }
                        arrayList.add(detailBean);
                        i2 += 90;
                    }
                }
                b(arrayList, detailBean);
            }
            int i8 = 0;
            for (CRPSleepInfo.DetailBean detailBean3 : arrayList) {
                int type3 = detailBean3.getType();
                int totalTime2 = detailBean3.getTotalTime();
                if (type3 == 1) {
                    i3 += totalTime2;
                } else if (type3 == 3) {
                    i8 += totalTime2;
                }
            }
            cRPSleepInfo.setLightTime(i3);
            cRPSleepInfo.setRemTime(i8);
            cRPSleepInfo.setDetails(arrayList);
        }
        return cRPSleepInfo;
    }

    public static void b(List<CRPSleepInfo.DetailBean> list, CRPSleepInfo.DetailBean detailBean) {
        if (!list.isEmpty()) {
            CRPSleepInfo.DetailBean detailBean2 = list.get(list.size() - 1);
            if (detailBean2.getType() == detailBean.getType()) {
                detailBean2.setEndTime(detailBean.getEndTime());
                detailBean2.setTotalTime(detailBean2.getTotalTime() + detailBean.getTotalTime());
                return;
            }
        }
        list.add(detailBean);
    }
}
