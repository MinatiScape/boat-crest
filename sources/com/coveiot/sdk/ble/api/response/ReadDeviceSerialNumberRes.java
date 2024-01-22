package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class ReadDeviceSerialNumberRes extends BaseResponse {
    public String e;

    public ReadDeviceSerialNumberRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public String getSerialNumber() {
        return this.e;
    }

    public void setSerialNumber(String str) {
        this.e = str;
    }
}
