package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.model.BleActivitySummaryData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import java.util.List;
/* loaded from: classes9.dex */
public class GetActivitySummaryRes extends BaseResponse {
    public List<BleActivitySummaryData> e;

    public GetActivitySummaryRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0376 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            Method dump skipped, instructions count: 904
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.sdk.ble.api.response.GetActivitySummaryRes.a():void");
    }

    public List<BleActivitySummaryData> getBleActivitySummaryDataList() {
        a();
        return this.e;
    }
}
