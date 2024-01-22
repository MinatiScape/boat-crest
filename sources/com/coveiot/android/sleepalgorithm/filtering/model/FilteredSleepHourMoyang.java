package com.coveiot.android.sleepalgorithm.filtering.model;

import com.coveiot.android.sleepalgorithm.filtering.SleepData;
/* loaded from: classes6.dex */
public class FilteredSleepHourMoyang extends FilteredSleepHour {
    public FilteredSleepHourMoyang(byte[] bArr) throws Exception {
        super(bArr);
        processRawData();
    }

    public void processRawData() {
        int i = 0;
        while (true) {
            byte[] bArr = this.mRawHourData;
            if (i >= bArr.length) {
                return;
            }
            if (bArr[i] == SleepData.VALUE_AWAKE) {
                this.mCountAwake++;
            }
            if (bArr[i] == SleepData.VALUE_DEEPSLEEP) {
                this.mCountDeepSleep++;
            }
            if (bArr[i] == SleepData.VALUE_LIGHTSLEEP) {
                this.mCountLightSleep++;
            }
            if (bArr[i] == SleepData.VALUE_REM) {
                this.mCountREM++;
            }
            if (bArr[i] == SleepData.VALUE_INVALID) {
                this.mCountInvalid++;
            }
            i++;
        }
    }
}
