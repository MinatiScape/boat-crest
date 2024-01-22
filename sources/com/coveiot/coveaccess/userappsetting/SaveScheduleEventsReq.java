package com.coveiot.coveaccess.userappsetting;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SaveScheduleEventsReq {
    @SerializedName("calendar")

    /* renamed from: a  reason: collision with root package name */
    private CalendarBean f6855a;

    public CalendarBean getCalendar() {
        return this.f6855a;
    }

    public void setCalendar(CalendarBean calendarBean) {
        this.f6855a = calendarBean;
    }
}
