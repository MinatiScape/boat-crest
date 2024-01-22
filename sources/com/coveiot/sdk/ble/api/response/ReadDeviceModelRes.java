package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class ReadDeviceModelRes extends BaseResponse {
    public String e;

    public ReadDeviceModelRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public String getModelNumber() {
        return this.e;
    }

    public void setModelNumber(String str) {
        this.e = str;
    }
}
