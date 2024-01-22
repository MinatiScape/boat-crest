package com.coveiot.coveaccess.contacttracing.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SourceBean {
    @SerializedName("type")

    /* renamed from: a  reason: collision with root package name */
    private String f6448a;
    @SerializedName("trackerId")
    private String b;

    public String getTrackerId() {
        return this.b;
    }

    public String getType() {
        return this.f6448a;
    }

    public void setTrackerId(String str) {
        this.b = str;
    }

    public void setType(String str) {
        this.f6448a = str;
    }
}
