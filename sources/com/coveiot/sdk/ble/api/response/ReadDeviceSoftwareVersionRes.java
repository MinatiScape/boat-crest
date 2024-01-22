package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class ReadDeviceSoftwareVersionRes extends BaseResponse {
    public String e;

    public ReadDeviceSoftwareVersionRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public String getSoftwareVersion() {
        return this.e;
    }

    public void setSoftwareVersion(String str) {
        this.e = str;
    }
}
