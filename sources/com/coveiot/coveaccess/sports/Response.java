package com.coveiot.coveaccess.sports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.android.lms.MapplsLMSDbAdapter;
/* loaded from: classes8.dex */
public class Response {
    @SerializedName(MapplsLMSDbAdapter.KEY_TOKEN)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6745a;
    @SerializedName("expires")
    @Expose
    private String b;

    public String getExpires() {
        return this.b;
    }

    public String getToken() {
        return this.f6745a;
    }

    public void setExpires(String str) {
        this.b = str;
    }

    public void setToken(String str) {
        this.f6745a = str;
    }
}
