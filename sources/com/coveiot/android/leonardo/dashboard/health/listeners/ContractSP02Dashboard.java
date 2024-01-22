package com.coveiot.android.leonardo.dashboard.health.listeners;

import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ContractSP02Dashboard {
    void updateHourlyLevelData(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2, @Nullable String str, @Nullable Long l, @Nullable Calendar calendar);

    void updateHourlySPO2Data(@Nullable List<? extends EntityManualData> list, @Nullable String str, @Nullable Long l, @Nullable Calendar calendar);
}
