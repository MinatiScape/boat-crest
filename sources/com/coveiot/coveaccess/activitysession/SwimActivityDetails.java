package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SwimActivityDetails extends ActivitiesSampleDetails {
    @SerializedName("totalStrokes")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private int f6394a;

    public int getTotalStrokes() {
        return this.f6394a;
    }

    public void setTotalStrokes(int i) {
        this.f6394a = i;
    }
}
