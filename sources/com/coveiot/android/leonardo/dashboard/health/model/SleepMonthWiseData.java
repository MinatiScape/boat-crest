package com.coveiot.android.leonardo.dashboard.health.model;

import com.coveiot.covedb.sleep.model.SleepDataModelMonthWiseCommon;
/* loaded from: classes3.dex */
public final class SleepMonthWiseData extends SleepDataModelMonthWiseCommon {
    public int f;
    public int g;

    public final int getNoOfDaysCount() {
        return this.f;
    }

    public final int getTotalRemSleep() {
        return this.g;
    }

    public final void setNoOfDaysCount(int i) {
        this.f = i;
    }

    public final void setTotalRemSleep(int i) {
        this.g = i;
    }
}
