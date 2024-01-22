package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.FirmwareCapabilityInfo;
import com.coveiot.sdk.ble.model.ResponseData;
/* loaded from: classes9.dex */
public class BaseResponse {

    /* renamed from: a  reason: collision with root package name */
    public CommandType f7551a;
    public BaseRequest b;
    public ResponseData c;
    public FirmwareCapabilityInfo d;

    public BaseResponse(CommandType commandType, BaseRequest baseRequest) {
        this.f7551a = commandType;
        this.b = baseRequest;
    }

    public CommandType getActivityType() {
        return this.f7551a;
    }

    public BaseRequest getBaseRequest() {
        return this.b;
    }

    public FirmwareCapabilityInfo getFirmwareCapabilityInfo() {
        return this.d;
    }

    public ResponseData getResponseData() {
        return this.c;
    }

    public void setFirmwareCapabilityInfo(FirmwareCapabilityInfo firmwareCapabilityInfo) {
        this.d = firmwareCapabilityInfo;
    }

    public void setResponseData(ResponseData responseData) {
        this.c = responseData;
    }
}
