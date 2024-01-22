package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class ReadDeviceFirmwareVersionRes extends BaseResponse {
    public String e;

    public ReadDeviceFirmwareVersionRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public String getFirmwareVersion() {
        return this.e;
    }

    public void setFirmwareVersion(String str) {
        this.e = str;
    }
}
