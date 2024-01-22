package com.coveiot.coveaccess.userappsetting;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class CalendarBean {
    @SerializedName("events")

    /* renamed from: a  reason: collision with root package name */
    private List<EventsBean> f6809a;

    public List<EventsBean> getEvents() {
        return this.f6809a;
    }

    public void setEvents(List<EventsBean> list) {
        this.f6809a = list;
    }
}
