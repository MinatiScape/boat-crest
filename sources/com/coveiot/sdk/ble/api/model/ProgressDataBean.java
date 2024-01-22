package com.coveiot.sdk.ble.api.model;

import com.coveiot.sdk.ble.api.request.BaseRequest;
/* loaded from: classes9.dex */
public class ProgressDataBean {
    public BaseRequest baseRequest;
    public ProgressType progressType;
    public int value;

    public ProgressDataBean(BaseRequest baseRequest, int i, ProgressType progressType) {
        this.baseRequest = baseRequest;
        this.value = i;
        this.progressType = progressType;
    }

    public BaseRequest getBaseRequest() {
        return this.baseRequest;
    }

    public ProgressType getProgressType() {
        return this.progressType;
    }

    public int getValue() {
        return this.value;
    }
}
