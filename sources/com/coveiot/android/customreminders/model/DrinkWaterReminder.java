package com.coveiot.android.customreminders.model;

import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.sdk.ble.api.model.TimeInfo;
/* loaded from: classes3.dex */
public class DrinkWaterReminder extends CustomReminder {
    private TimeInfo endTime;
    private int frequency;
    private TimeInfo startTime;

    public DrinkWaterReminder(RepeatModel repeatModel, String str, boolean z, TimeInfo timeInfo, TimeInfo timeInfo2, int i) {
        super(repeatModel, str, z, ReminderType.DRINK.name());
        this.startTime = timeInfo;
        this.endTime = timeInfo2;
        this.frequency = i;
    }

    public TimeInfo getEndTime() {
        return this.endTime;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public TimeInfo getStartTime() {
        return this.startTime;
    }
}
