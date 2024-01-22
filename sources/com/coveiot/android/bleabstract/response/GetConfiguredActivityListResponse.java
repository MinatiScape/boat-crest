package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.ConfiguredActivities;
/* loaded from: classes2.dex */
public class GetConfiguredActivityListResponse {

    /* renamed from: a  reason: collision with root package name */
    public ConfiguredActivities f3605a;

    public GetConfiguredActivityListResponse(ConfiguredActivities configuredActivities) {
        this.f3605a = configuredActivities;
    }

    public ConfiguredActivities getConfiguredActivities() {
        return this.f3605a;
    }

    public String toString() {
        return "GetConfiguredActivityListResponse{configuredActivities=" + this.f3605a + '}';
    }
}
