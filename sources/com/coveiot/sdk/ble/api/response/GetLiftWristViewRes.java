package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.LiftWristViewData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class GetLiftWristViewRes extends BaseResponse {
    public GetLiftWristViewRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final LiftWristViewData b() {
        String str = this.c.getDataList().get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        boolean equalsIgnoreCase = split[4].trim().equalsIgnoreCase("1");
        LiftWristViewData liftWristViewData = new LiftWristViewData();
        liftWristViewData.setEnabled(equalsIgnoreCase);
        if (split.length > 5) {
            int parseInt = Integer.parseInt(split[5].trim());
            int parseInt2 = Integer.parseInt(split[6].trim());
            int parseInt3 = Integer.parseInt(split[7].trim());
            int parseInt4 = Integer.parseInt(split[8].trim());
            liftWristViewData.setStartHour(parseInt);
            liftWristViewData.setStartMin(parseInt2);
            liftWristViewData.setEndHour(parseInt3);
            liftWristViewData.setEndMin(parseInt4);
        }
        return liftWristViewData;
    }

    public LiftWristViewData getLiftWristViewData() {
        return b();
    }
}
