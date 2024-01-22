package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class CustomWatchFaceUploadRes extends BaseResponse {
    public Boolean e;
    public Object f;

    public CustomWatchFaceUploadRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
        this.e = Boolean.FALSE;
    }

    public Boolean getComplete() {
        return this.e;
    }

    public Object getData() {
        return this.f;
    }

    public void setComplete(Boolean bool) {
        this.e = bool;
    }

    public void setData(Object obj) {
        this.f = obj;
    }

    public CustomWatchFaceUploadRes(CommandType commandType, BaseRequest baseRequest, boolean z) {
        super(commandType, baseRequest);
        this.e = Boolean.FALSE;
        this.e = Boolean.valueOf(z);
    }
}
