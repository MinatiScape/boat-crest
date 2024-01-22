package com.coveiot.android.bleabstract.models;

import com.coveiot.sdk.ble.api.model.TimeInfo;
/* loaded from: classes2.dex */
public class HandWashReminderAbstract extends CustomReminderAbstract {
    public TimeInfo l;
    public TimeInfo m;
    public int n;

    public HandWashReminderAbstract(boolean z, int i, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, int i2, String str, TimeInfo timeInfo, TimeInfo timeInfo2, int i3) {
        super(i, z2, z3, z4, z5, z6, z7, z8, i2, str, z);
        this.l = timeInfo;
        this.m = timeInfo2;
        this.n = i3;
    }

    public TimeInfo getEndTime() {
        return this.m;
    }

    public int getFrequency() {
        return this.n;
    }

    public TimeInfo getStartTime() {
        return this.l;
    }
}
