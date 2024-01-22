package com.coveiot.coveaccess.alexa.model.request;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class ActivateAlexaReq {
    @SerializedName("userDeviceId")

    /* renamed from: a  reason: collision with root package name */
    private Long f6411a;
    @SerializedName("amazonCode")
    private String b = null;

    public String getAmazonCode() {
        return this.b;
    }

    public Long getUserDeviceId() {
        return this.f6411a;
    }

    public void setAmazonCode(String str) {
        this.b = str;
    }

    public void setUserDeviceId(Long l) {
        this.f6411a = l;
    }
}
