package com.coveiot.android.bleabstract.models;

import com.coveiot.sdk.ble.api.model.TimeInfo;
import java.util.ArrayList;
import java.util.Date;
/* loaded from: classes2.dex */
public class MeetingReminderAbstract extends CustomReminderAbstract {
    public Date l;
    public Date m;
    public int n;
    public ArrayList<TimeInfo> o;

    public MeetingReminderAbstract(boolean z, int i, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, int i2, String str, Date date, Date date2, int i3, ArrayList<TimeInfo> arrayList) {
        super(i, z2, z3, z4, z5, z6, z7, z8, i2, str, z);
        this.l = date;
        this.m = date2;
        this.n = i3;
        this.o = arrayList;
    }

    public int getAdvanceTime() {
        return this.n;
    }

    public Date getEndDate() {
        return this.m;
    }

    public Date getStartDate() {
        return this.l;
    }

    public ArrayList<TimeInfo> getTimeInfos() {
        return this.o;
    }
}
