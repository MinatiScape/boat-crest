package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class Source {
    @SerializedName("type")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6393a;
    @SerializedName("identifier")
    @Expose
    private String b;

    public String getIdentifier() {
        return this.b;
    }

    public String getType() {
        return this.f6393a;
    }

    public void setIdentifier(String str) {
        this.b = str;
    }

    public void setType(String str) {
        this.f6393a = str;
    }
}
