package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class ReadDeviceNameRes extends BaseResponse {
    public String e;

    public ReadDeviceNameRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public String getName() {
        return this.e;
    }

    public void setName(String str) {
        this.e = str;
    }
}
