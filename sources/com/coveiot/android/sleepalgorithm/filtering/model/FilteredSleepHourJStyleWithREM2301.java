package com.coveiot.android.sleepalgorithm.filtering.model;
/* loaded from: classes6.dex */
public class FilteredSleepHourJStyleWithREM2301 extends FilteredSleepHour {
    public FilteredSleepHourJStyleWithREM2301(byte[] bArr) throws Exception {
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
            if (bArr[i] != -1) {
                if ((bArr[i] & 255) == 1) {
                    this.mCountDeepSleep++;
                } else if ((bArr[i] & 255) == 2) {
                    this.mCountLightSleep++;
                } else if ((bArr[i] & 255) == 3) {
                    this.mCountREM++;
                } else if ((bArr[i] & 255) == 5) {
                    this.mCountAwake++;
                } else {
                    this.mCountLightSleep++;
                }
            } else {
                this.mCountAwake++;
            }
            i++;
        }
    }
}
