package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import com.coveiot.android.leonardo.dashboard.health.model.SleepDataModelWithDate;
import java.util.Calendar;
import java.util.Comparator;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SleepDataComparator implements Comparator<SleepDataModelWithDate> {
    @Override // java.util.Comparator
    public int compare(@Nullable SleepDataModelWithDate sleepDataModelWithDate, @Nullable SleepDataModelWithDate sleepDataModelWithDate2) {
        Intrinsics.checkNotNull(sleepDataModelWithDate);
        Calendar date = sleepDataModelWithDate.getDate();
        Intrinsics.checkNotNull(sleepDataModelWithDate2);
        return date.compareTo(sleepDataModelWithDate2.getDate());
    }
}
