package com.coveiot.android.leonardo.dashboard.health;

import com.coveiot.android.leonardo.dashboard.health.model.SleepDataModelWithDate;
import java.util.Comparator;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class RemoveDuplicateDailyDataComparator implements Comparator<SleepDataModelWithDate> {
    @Override // java.util.Comparator
    public int compare(@NotNull SleepDataModelWithDate sleepDataModelWithDate, @NotNull SleepDataModelWithDate sleepDataModelWithDate1) {
        Intrinsics.checkNotNullParameter(sleepDataModelWithDate, "sleepDataModelWithDate");
        Intrinsics.checkNotNullParameter(sleepDataModelWithDate1, "sleepDataModelWithDate1");
        return !sleepDataModelWithDate.getDate().getTime().equals(sleepDataModelWithDate1.getDate().getTime());
    }
}
