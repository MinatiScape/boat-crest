package com.coveiot.coveaccess.preparationplan.requestmodel;
/* loaded from: classes8.dex */
public class PlanProgressUpdateReq {

    /* renamed from: a  reason: collision with root package name */
    public String f6682a;
    public int b;
    public String c;

    public int getPercentage() {
        return this.b;
    }

    public String getProgressStatus() {
        return this.c;
    }

    public String getUserPlanId() {
        return this.f6682a;
    }

    public void setPercentage(int i) {
        this.b = i;
    }

    public void setProgressStatus(String str) {
        this.c = str;
    }

    public void setUserPlanId(String str) {
        this.f6682a = str;
    }
}
