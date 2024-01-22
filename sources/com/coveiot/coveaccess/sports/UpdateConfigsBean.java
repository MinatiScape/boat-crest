package com.coveiot.coveaccess.sports;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class UpdateConfigsBean {
    @SerializedName("matchFormat")

    /* renamed from: a  reason: collision with root package name */
    private String f6754a;
    @SerializedName("interval")
    private Integer b;
    @SerializedName("eventType")
    private String c;

    public String getEventType() {
        return this.c;
    }

    public Integer getInterval() {
        return this.b;
    }

    public String getMatchFormat() {
        return this.f6754a;
    }

    public void setEventType(String str) {
        this.c = str;
    }

    public void setInterval(Integer num) {
        this.b = num;
    }

    public void setMatchFormat(String str) {
        this.f6754a = str;
    }
}
