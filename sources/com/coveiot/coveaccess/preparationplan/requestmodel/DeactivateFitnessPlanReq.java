package com.coveiot.coveaccess.preparationplan.requestmodel;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class DeactivateFitnessPlanReq {
    @SerializedName("userPlanId")

    /* renamed from: a  reason: collision with root package name */
    private String f6681a;

    public DeactivateFitnessPlanReq(String str) {
        this.f6681a = str;
    }

    public String getUserPlanId() {
        return this.f6681a;
    }

    public void setUserPlanId(String str) {
        this.f6681a = str;
    }
}
