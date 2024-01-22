package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class HikingActivityDetails extends ActivitiesSampleDetails {
    @SerializedName("totalDecalt")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private int f6389a;
    @SerializedName("totalIncalt")
    @Expose
    private int b;
    @SerializedName("maxAltitude")
    @Expose
    private int c;
    @SerializedName("minAltitude")
    @Expose
    private int d;
    @SerializedName("baseAltitude")
    @Expose
    private int e;

    public int getBaseAltitude() {
        return this.e;
    }

    public int getMaxAltitude() {
        return this.c;
    }

    public int getMinAltitude() {
        return this.d;
    }

    public int getTotalDecalt() {
        return this.f6389a;
    }

    public int getTotalIncalt() {
        return this.b;
    }

    public void setBaseAltitude(int i) {
        this.e = i;
    }

    public void setMaxAltitude(int i) {
        this.c = i;
    }

    public void setMinAltitude(int i) {
        this.d = i;
    }

    public void setTotalDecalt(int i) {
        this.f6389a = i;
    }

    public void setTotalIncalt(int i) {
        this.b = i;
    }
}
