package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.QRCodeData;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class GetQRCodeRes extends BaseResponse {
    public List<QRCodeData> e;

    public GetQRCodeRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
        this.e = new ArrayList();
    }

    public List<QRCodeData> getQrCodes() {
        processResponse();
        LogHelper.d("qrCheck", "getQrCodes: " + new Gson().toJson(this.e));
        return this.e;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x018c A[LOOP:6: B:40:0x018a->B:41:0x018c, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void processResponse() {
        /*
            Method dump skipped, instructions count: 469
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.sdk.ble.api.response.GetQRCodeRes.processResponse():void");
    }
}
