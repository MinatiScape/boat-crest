package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.AutoActivityDetectionModel;
/* loaded from: classes2.dex */
public class GetAutoActivityDetectionResponse {

    /* renamed from: a  reason: collision with root package name */
    public AutoActivityDetectionModel f3601a;

    public GetAutoActivityDetectionResponse(AutoActivityDetectionModel autoActivityDetectionModel) {
        this.f3601a = autoActivityDetectionModel;
    }

    public AutoActivityDetectionModel getAutoActivityDetectionModel() {
        return this.f3601a;
    }

    public String toString() {
        return "GetAutoActivityDetectionResponse{autoActivityDetectionModel=" + this.f3601a + '}';
    }
}
