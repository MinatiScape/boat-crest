package com.coveiot.android.leonardo.dashboard.health.listeners;

import com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ContractPeriodicSpo2 {
    boolean isSyncInProgress();

    void updateDailyLevelData(@Nullable DailyPeriodicSpo2 dailyPeriodicSpo2);

    void updateHourlyLevelData(@Nullable ArrayList<BarEntry> arrayList, @Nullable ArrayList<String> arrayList2);
}
