package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public class GetLatestHealthDataResponse {

    /* renamed from: a  reason: collision with root package name */
    public HealthData f3612a;

    public GetLatestHealthDataResponse(HealthData healthData) {
        this.f3612a = healthData;
    }

    public HealthData getHealthData() {
        return this.f3612a;
    }

    public String toString() {
        return "GetLatestHealthDataResponse{healthData=" + this.f3612a + '}';
    }
}
