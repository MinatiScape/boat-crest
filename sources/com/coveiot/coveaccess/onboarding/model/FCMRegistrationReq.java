package com.coveiot.coveaccess.onboarding.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class FCMRegistrationReq {
    @SerializedName("gcmRegKey")

    /* renamed from: a  reason: collision with root package name */
    private String f6661a;
    public String b;

    public FCMRegistrationReq(String str, String str2) {
        this.f6661a = str;
        this.b = str2;
    }

    public String getGcmRegKey() {
        return this.f6661a;
    }

    public String getUserId() {
        return this.b;
    }
}
