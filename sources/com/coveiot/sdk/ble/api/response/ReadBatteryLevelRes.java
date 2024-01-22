package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class ReadBatteryLevelRes extends BaseResponse {
    public int e;

    public ReadBatteryLevelRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public int getBatteryLevel() {
        return this.e;
    }

    public void setBatteryLevel(int i) {
        this.e = i;
    }
}
