package com.coveiot.coveaccess.userdevicesetting;

import com.coveiot.coveaccess.model.server.ActivitySession;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SaveActivitySessionReq {
    @SerializedName("activitySession")

    /* renamed from: a  reason: collision with root package name */
    private ActivitySession f6872a;

    public ActivitySession getActivitySession() {
        return this.f6872a;
    }

    public void setActivitySession(ActivitySession activitySession) {
        this.f6872a = activitySession;
    }
}
