package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class PersonalizedMessageRes extends BaseResponse {
    public PersonalizedMessageRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public boolean isSuccess() {
        return true;
    }
}