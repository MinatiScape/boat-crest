package com.coveiot.android.sleepalgorithm.filtering.model;
/* loaded from: classes6.dex */
public class FilteredSleepHourJStyle extends FilteredSleepHour {
    public FilteredSleepHourJStyle(byte[] bArr) throws Exception {
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
                if ((bArr[i] & 255) >= 0 && (bArr[i] & 255) <= 14) {
                    this.mCountDeepSleep++;
                } else if ((bArr[i] & 255) >= 15) {
                    this.mCountLightSleep++;
                }
            } else {
                this.mCountAwake++;
            }
            i++;
        }
    }
}
