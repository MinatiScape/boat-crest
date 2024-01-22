package com.coveiot.android.sleepalgorithm.filtering.model;
/* loaded from: classes6.dex */
public abstract class FilteredSleepHour {
    public byte[] mRawHourData;
    public int mCountDeepSleep = 0;
    public int mCountLightSleep = 0;
    public int mCountAwake = 0;
    public int mCountREM = 0;
    public int mCountInvalid = 0;

    public FilteredSleepHour(byte[] bArr) throws Exception {
        if (bArr.length == 60) {
            this.mRawHourData = bArr;
            return;
        }
        throw new Exception("Not enough data");
    }

    public int getCountAwake() {
        return this.mCountAwake;
    }

    public int getCountDeepSleep() {
        return this.mCountDeepSleep;
    }

    public int getCountInvalid() {
        return this.mCountInvalid;
    }

    public int getCountLightSleep() {
        return this.mCountLightSleep;
    }

    public int getCountREM() {
        return this.mCountREM;
    }

    public byte[] getRawHourData() {
        return this.mRawHourData;
    }
}
