package com.coveiot.android.leonardo.dashboard.health.listeners;

import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.github.mikephil.charting.data.CandleEntry;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ContractHeartRateDashboard {
    boolean isSyncInProgress();

    void updateDailyLevelData(@Nullable EntityDailyHeartRateData entityDailyHeartRateData);

    void updateHourlyLevelData(@Nullable ArrayList<CandleEntry> arrayList, @Nullable ArrayList<String> arrayList2);

    void updateRangeLevelData(@NotNull List<? extends EntityDailyHeartRateData> list);
}
