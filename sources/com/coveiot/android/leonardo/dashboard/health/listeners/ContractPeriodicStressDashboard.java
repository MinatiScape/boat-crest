package com.coveiot.android.leonardo.dashboard.health.listeners;

import com.coveiot.android.leonardo.dashboard.health.model.StressPercentage;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ContractPeriodicStressDashboard {
    boolean isSyncInProgress();

    void updateDailyLevelData(@Nullable DailyStress dailyStress);

    void updateHourlyLevelData(@Nullable ArrayList<BarEntry> arrayList, @Nullable ArrayList<String> arrayList2);

    void updateRangeLevelData(@NotNull List<? extends DailyStress> list);

    void updateStressPercentage(@Nullable StressPercentage stressPercentage);
}
