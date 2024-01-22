package com.coveiot.coveaccess.feedback;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class PostFeedBackReq {

    /* renamed from: a  reason: collision with root package name */
    public String f6524a;
    public String b;
    public String c;
    @SerializedName("contactPreference")
    private String d;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String e;

    public String getContactPreference() {
        return this.d;
    }

    public String getEmailId() {
        return this.f6524a;
    }

    public String getMessage() {
        return this.c;
    }

    public String getName() {
        return this.e;
    }

    public String getSubject() {
        return this.b;
    }

    public void setContactPreference(String str) {
        this.d = str;
    }

    public void setEmailId(String str) {
        this.f6524a = str;
    }

    public void setMessage(String str) {
        this.c = str;
    }

    public void setName(String str) {
        this.e = str;
    }

    public void setSubject(String str) {
        this.b = str;
    }
}
