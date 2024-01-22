package com.coveiot.android.respiratoryrate.utils;

import com.coveiot.android.respiratoryrate.model.WeeklyRespiratoryRateData;
import java.util.Comparator;
/* loaded from: classes6.dex */
public class RespiratoryRateHistoryWeekComparator implements Comparator<WeeklyRespiratoryRateData> {
    @Override // java.util.Comparator
    public int compare(WeeklyRespiratoryRateData weeklyRespiratoryRateData, WeeklyRespiratoryRateData weeklyRespiratoryRateData2) {
        int parseInt = Integer.parseInt(weeklyRespiratoryRateData2.getYear());
        int parseInt2 = Integer.parseInt(weeklyRespiratoryRateData2.getWeek());
        int parseInt3 = Integer.parseInt(weeklyRespiratoryRateData.getYear());
        int parseInt4 = Integer.parseInt(weeklyRespiratoryRateData.getWeek());
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
