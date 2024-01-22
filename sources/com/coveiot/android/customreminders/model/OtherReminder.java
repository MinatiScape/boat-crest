package com.coveiot.android.customreminders.model;

import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.sdk.ble.api.model.TimeInfo;
import java.util.ArrayList;
import java.util.Calendar;
/* loaded from: classes3.dex */
public class OtherReminder extends CustomReminder {
    private int advanceTime;
    private Calendar endDate;
    private Calendar startDate;
    private ArrayList<TimeInfo> timeInfos;

    public OtherReminder(boolean z, RepeatModel repeatModel, String str, Calendar calendar, Calendar calendar2, int i, ArrayList<TimeInfo> arrayList) {
        super(repeatModel, str, z, ReminderType.OTHERS.name());
        this.startDate = calendar;
        this.endDate = calendar2;
        this.advanceTime = i;
        this.timeInfos = arrayList;
    }

    public int getAdvanceTime() {
        return this.advanceTime;
    }

    public Calendar getEndDate() {
        return this.endDate;
    }

    public Calendar getStartDate() {
        return this.startDate;
    }

    public ArrayList<TimeInfo> getTimeInfos() {
        return this.timeInfos;
    }

    public void setAdvanceTime(int i) {
        this.advanceTime = i;
    }

    public void setEndDate(Calendar calendar) {
        this.endDate = calendar;
    }

    public void setStartDate(Calendar calendar) {
        this.startDate = calendar;
    }

    public void setTimeInfos(ArrayList<TimeInfo> arrayList) {
        this.timeInfos = arrayList;
    }

    public OtherReminder() {
        super(ReminderType.OTHERS.name());
    }
}
