package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.DNDSettingsData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
/* loaded from: classes9.dex */
public class GetDNDModeSettingsRes extends BaseResponse {
    public GetDNDModeSettingsRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final DNDSettingsData b() {
        String str = this.c.getDataList().get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        boolean equalsIgnoreCase = split[4].trim().equalsIgnoreCase("1");
        boolean equalsIgnoreCase2 = split[5].trim().equalsIgnoreCase("1");
        boolean equalsIgnoreCase3 = split[6].trim().equalsIgnoreCase("1");
        int parseInt = Integer.parseInt(split[7].trim());
        int parseInt2 = Integer.parseInt(split[8].trim());
        int parseInt3 = Integer.parseInt(split[9].trim());
        int parseInt4 = Integer.parseInt(split[10].trim());
        DNDSettingsData dNDSettingsData = new DNDSettingsData();
        dNDSettingsData.setDNDEnabled(equalsIgnoreCase);
        dNDSettingsData.setLiftWristEnabled(equalsIgnoreCase3);
        dNDSettingsData.setNotificationEnabled(equalsIgnoreCase2);
        dNDSettingsData.setStartHour(parseInt);
        dNDSettingsData.setStartMin(parseInt2);
        dNDSettingsData.setEndHour(parseInt3);
        dNDSettingsData.setEndMin(parseInt4);
        return dNDSettingsData;
    }

    public DNDSettingsData getDNDModeSettingsData() {
        return b();
    }
}
