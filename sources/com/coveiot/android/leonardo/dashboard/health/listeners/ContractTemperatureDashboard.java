package com.coveiot.android.leonardo.dashboard.health.listeners;

import com.coveiot.covedb.temperature.entity.DailyTemperature;
import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ContractTemperatureDashboard {
    boolean isSyncInProgress();

    void updateDailyLevelData(@Nullable DailyTemperature dailyTemperature);

    void updateHourlyLevelData(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2);
}
