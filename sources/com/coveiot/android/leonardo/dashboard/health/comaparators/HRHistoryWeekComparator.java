package com.coveiot.android.leonardo.dashboard.health.comaparators;

import com.coveiot.covedb.heartrate.model.WeeklyHeartRateData;
import java.util.Comparator;
/* loaded from: classes3.dex */
public class HRHistoryWeekComparator implements Comparator<WeeklyHeartRateData> {
    @Override // java.util.Comparator
    public int compare(WeeklyHeartRateData weeklyHeartRateData, WeeklyHeartRateData weeklyHeartRateData2) {
        int parseInt = Integer.parseInt(weeklyHeartRateData2.year);
        int parseInt2 = Integer.parseInt(weeklyHeartRateData2.getWeek());
        int parseInt3 = Integer.parseInt(weeklyHeartRateData.year);
        int parseInt4 = Integer.parseInt(weeklyHeartRateData.getWeek());
        if (parseInt > parseInt3) {
            return -1;
        }
        if (parseInt == parseInt3) {
            if (parseInt2 > parseInt4) {
                return -1;
            }
            return parseInt2 < parseInt4 ? 1 : 0;
        }
        return 1;
    }
}
