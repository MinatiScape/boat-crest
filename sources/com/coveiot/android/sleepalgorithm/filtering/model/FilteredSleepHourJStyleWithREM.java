package com.coveiot.android.sleepalgorithm.filtering.model;

import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepData;
/* loaded from: classes6.dex */
public class FilteredSleepHourJStyleWithREM extends FilteredSleepHour {
    public FilteredSleepHourJStyleWithREM(byte[] bArr) throws Exception {
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
                if ((bArr[i] & 255) >= JStyleSleepData.JSTYLE_VALUE_DEEPSLEEP_START_WITH_REM && (bArr[i] & 255) <= JStyleSleepData.JSTYLE_VALUE_DEEPSLEEP_END_WITH_REM) {
                    this.mCountDeepSleep++;
                } else if ((bArr[i] & 255) >= JStyleSleepData.JSTYLE_VALUE_LIGHTSLEEP_START_WITH_REM && (bArr[i] & 255) <= JStyleSleepData.JSTYLE_VALUE_LIGHTSLEEP_END_WITH_REM) {
                    this.mCountLightSleep++;
                } else if ((bArr[i] & 255) >= JStyleSleepData.JSTYLE_VALUE_REMSLEEP_START_WITH_REM && (bArr[i] & 255) <= JStyleSleepData.JSTYLE_VALUE_REMSLEEP_END_WITH_REM) {
                    this.mCountREM++;
                } else if ((bArr[i] & 255) >= JStyleSleepData.JSTYLE_VALUE_AWAKE_START_WITH_REM && (bArr[i] & 255) <= JStyleSleepData.JSTYLE_VALUE_AWAKE_END_WITH_REM) {
                    this.mCountAwake++;
                }
            } else {
                this.mCountAwake++;
            }
            i++;
        }
    }
}
