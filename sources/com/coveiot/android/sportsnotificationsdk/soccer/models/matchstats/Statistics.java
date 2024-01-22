package com.coveiot.android.sportsnotificationsdk.soccer.models.matchstats;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class Statistics {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)

    /* renamed from: a  reason: collision with root package name */
    private String f5997a;
    @SerializedName("home")
    private Double b;
    @SerializedName("away")
    private Double c;

    public Double getAway() {
        return this.c;
    }

    public Double getHome() {
        return this.b;
    }

    public String getName() {
        return this.f5997a;
    }

    public void setAway(Double d) {
        this.c = d;
    }

    public void setHome(Double d) {
        this.b = d;
    }

    public void setName(String str) {
        this.f5997a = str;
    }
}
