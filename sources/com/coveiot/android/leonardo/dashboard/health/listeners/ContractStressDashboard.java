package com.coveiot.android.leonardo.dashboard.health.listeners;

import com.coveiot.covedb.manualdata.entities.EntityManualData;
import java.util.Calendar;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ContractStressDashboard {
    void updateHourlyStressData(@Nullable List<? extends EntityManualData> list, @Nullable String str, @Nullable Long l, @Nullable Calendar calendar);
}
