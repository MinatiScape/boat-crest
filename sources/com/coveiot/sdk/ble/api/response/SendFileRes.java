package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class SendFileRes extends BaseResponse {
    public SendFileRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public boolean isSuccess() {
        return true;
    }
}
