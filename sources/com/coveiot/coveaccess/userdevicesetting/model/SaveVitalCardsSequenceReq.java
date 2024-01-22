package com.coveiot.coveaccess.userdevicesetting.model;

import com.coveiot.coveaccess.model.server.AppDashboardDTO;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SaveVitalCardsSequenceReq {
    @SerializedName("appDashboard")

    /* renamed from: a  reason: collision with root package name */
    private AppDashboardDTO f6897a;

    public AppDashboardDTO getAppDashboard() {
        return this.f6897a;
    }

    public void setAppDashboard(AppDashboardDTO appDashboardDTO) {
        this.f6897a = appDashboardDTO;
    }
}
