package com.coveiot.android.leonardo.dashboard.health.comaparators;

import com.coveiot.covedb.ambientsound.model.WeeklyAmbientSoundData;
import java.util.Comparator;
/* loaded from: classes3.dex */
public class AmbientSoundHistoryComparator implements Comparator<WeeklyAmbientSoundData> {
    @Override // java.util.Comparator
    public int compare(WeeklyAmbientSoundData weeklyAmbientSoundData, WeeklyAmbientSoundData weeklyAmbientSoundData2) {
        int parseInt = Integer.parseInt(weeklyAmbientSoundData2.year);
        int parseInt2 = Integer.parseInt(weeklyAmbientSoundData2.getWeek());
        int parseInt3 = Integer.parseInt(weeklyAmbientSoundData.year);
        int parseInt4 = Integer.parseInt(weeklyAmbientSoundData.getWeek());
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
