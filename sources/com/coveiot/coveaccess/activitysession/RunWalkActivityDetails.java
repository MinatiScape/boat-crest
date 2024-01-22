package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class RunWalkActivityDetails extends ActivitiesSampleDetails {
    @SerializedName("totalLaps")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6391a;
    @SerializedName("lapDistance")
    @Expose
    private Integer b;

    public Integer getLapDistance() {
        return this.b;
    }

    public Integer getTotalLaps() {
        return this.f6391a;
    }

    public void setLapDistance(Integer num) {
        this.b = num;
    }

    public void setTotalLaps(Integer num) {
        this.f6391a = num;
    }
}
