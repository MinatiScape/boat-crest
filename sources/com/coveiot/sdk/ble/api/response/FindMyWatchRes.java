package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class FindMyWatchRes extends BaseResponse {
    public FindMyWatchRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public boolean isSuccess() {
        String str = this.c.getDataList().get(0);
        return str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA)[4].trim().equalsIgnoreCase("1");
    }
}
