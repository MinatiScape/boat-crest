package com.coveiot.android.leonardo.dashboard.health.listeners;

import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ContractPeriodicHRVDashboard {
    boolean isSyncInProgress();

    void onRangeBarChartLoaded(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<Entry> arrayList2, @Nullable ArrayList<String> arrayList3, double d);

    void updateDailyLevelData(@Nullable DailyHRV dailyHRV);

    void updateDailyLevelStressData(@Nullable DailyStress dailyStress);

    void updateHourlyLevelData(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2);
}
