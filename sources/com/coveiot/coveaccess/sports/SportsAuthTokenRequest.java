package com.coveiot.coveaccess.sports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SportsAuthTokenRequest {
    @SerializedName("ctx")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6749a;
    @SerializedName("extend")
    @Expose
    private Integer b;
    @SerializedName("sport")
    private String c;

    public String getCtx() {
        return this.f6749a;
    }

    public Integer getExtend() {
        return this.b;
    }

    public String getSport() {
        return this.c;
    }

    public void setCtx(String str) {
        this.f6749a = str;
    }

    public void setExtend(Integer num) {
        this.b = num;
    }

    public void setSport(String str) {
        this.c = str;
    }
}
