package com.coveiot.android.leonardo.dashboard.health.listeners;

import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ContractFitnessDashBoard {
    boolean isSyncInProgress();

    void onDataLoaded(@Nullable ArrayList<BarEntry> arrayList, @Nullable ArrayList<String> arrayList2, @NotNull List<? extends DailyWalkData> list);

    void updateDailyLevelData(@Nullable DailyWalkData dailyWalkData);

    void updateHourlyLevelData(@Nullable ArrayList<BarEntry> arrayList, @Nullable ArrayList<String> arrayList2, boolean z);
}
