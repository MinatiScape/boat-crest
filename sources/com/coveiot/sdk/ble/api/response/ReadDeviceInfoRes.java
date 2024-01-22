package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.BleDeviceInfo;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class ReadDeviceInfoRes extends BaseResponse {
    public BleDeviceInfo e;

    public ReadDeviceInfoRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public BleDeviceInfo getBleDeviceInfo() {
        return this.e;
    }

    public void setBleDeviceInfo(BleDeviceInfo bleDeviceInfo) {
        this.e = bleDeviceInfo;
    }
}
