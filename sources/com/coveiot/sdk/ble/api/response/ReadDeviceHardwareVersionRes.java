package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class ReadDeviceHardwareVersionRes extends BaseResponse {
    public String e;

    public ReadDeviceHardwareVersionRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public String getHardwareVersion() {
        return this.e;
    }

    public void setHardwareVersion(String str) {
        this.e = str;
    }
}
