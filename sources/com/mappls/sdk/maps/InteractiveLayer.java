package com.mappls.sdk.maps;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes11.dex */
public class InteractiveLayer {
    @SerializedName("id")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f12628a;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String b;

    public String getId() {
        return this.f12628a;
    }

    public String getName() {
        return this.b;
    }

    public void setId(String str) {
        this.f12628a = str;
    }

    public void setName(String str) {
        this.b = str;
    }
}
