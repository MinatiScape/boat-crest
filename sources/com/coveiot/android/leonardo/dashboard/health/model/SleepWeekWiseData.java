package com.coveiot.android.leonardo.dashboard.health.model;

import com.coveiot.covedb.sleep.model.SleepDataModelWeekWiseCommon;
/* loaded from: classes3.dex */
public final class SleepWeekWiseData extends SleepDataModelWeekWiseCommon {
    public int f;
    public int g;
    public int h;

    public final int getNoOfDaysCount() {
        return this.g;
    }

    public final int getTotalRemSleep() {
        return this.h;
    }

    public final int getYear() {
        return this.f;
    }

    public final void setNoOfDaysCount(int i) {
        this.g = i;
    }

    public final void setTotalRemSleep(int i) {
        this.h = i;
    }

    public final void setYear(int i) {
        this.f = i;
    }
}
