package com.coveiot.android.leonardo.dashboard.health.listeners;

import com.coveiot.covedb.sleep.DailySleepDataAlias;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ContractSleepDashboard {
    boolean isSyncInProgress();

    void updateDailyLevelData(@Nullable DailySleepDataAlias dailySleepDataAlias);

    void updateHourlyLevelData(@Nullable List<? extends SleepDataModelForLastNight> list);
}
