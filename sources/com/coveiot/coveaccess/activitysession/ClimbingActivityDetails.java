package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class ClimbingActivityDetails extends ActivitiesSampleDetails {
    @SerializedName("maxAltitude")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private int f6384a;
    @SerializedName("minAltitude")
    @Expose
    private int b;
    @SerializedName("baseAltitude")
    @Expose
    private int c;

    public int getBaseAltitude() {
        return this.c;
    }

    public int getMaxAltitude() {
        return this.f6384a;
    }

    public int getMinAltitude() {
        return this.b;
    }

    public void setBaseAltitude(int i) {
        this.c = i;
    }

    public void setMaxAltitude(int i) {
        this.f6384a = i;
    }

    public void setMinAltitude(int i) {
        this.b = i;
    }
}
