package com.coveiot.sdk.ble.api;

import com.coveiot.sdk.ble.api.error.Error;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.response.BaseResponse;
/* loaded from: classes9.dex */
public interface ResponseListener {
    void onFailure(Error error);

    void onProgressUpdate(ProgressDataBean progressDataBean);

    void onResponse(BaseResponse baseResponse);

    void retryCommand(BaseRequest baseRequest);
}
