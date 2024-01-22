package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class ReadDeviceManufacturerRes extends BaseResponse {
    public String e;

    public ReadDeviceManufacturerRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public String getManufacturerName() {
        return this.e;
    }

    public void setManufacturerName(String str) {
        this.e = str;
    }
}
