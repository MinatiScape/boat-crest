package com.coveiot.coveaccess.userdevicesetting.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class ContactsSettings {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)

    /* renamed from: a  reason: collision with root package name */
    private String f6893a;
    @SerializedName("mobileNumber")
    private String b;

    public String getMobileNumber() {
        return this.b;
    }

    public String getName() {
        return this.f6893a;
    }

    public void setMobileNumber(String str) {
        this.b = str;
    }

    public void setName(String str) {
        this.f6893a = str;
    }

    public String toString() {
        return "ContactsSettings{name='" + this.f6893a + "', mobileNumber='" + this.b + "'}";
    }
}
