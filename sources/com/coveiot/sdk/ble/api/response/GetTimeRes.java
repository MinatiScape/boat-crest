package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.BleUtils;
import java.util.Calendar;
/* loaded from: classes9.dex */
public class GetTimeRes extends BaseResponse {
    public GetTimeRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public Calendar getTimeInCalendar() {
        if (BleUtils.isEmpty(this.c.getDataList())) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        String str = this.c.getDataList().get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        calendar.set(1, Integer.parseInt(((int) ((byte) (Byte.parseByte(split[4].trim()) & 255))) + "" + ((int) ((byte) (Byte.parseByte(split[5].trim()) & 255)))));
        calendar.set(2, (Byte.parseByte(split[6].trim()) & 255) - 1);
        calendar.set(5, Byte.parseByte(split[7].trim()) & 255);
        calendar.set(11, Byte.parseByte(split[8].trim()) & 255);
        calendar.set(12, Byte.parseByte(split[9].trim()) & 255);
        calendar.set(13, Byte.parseByte(split[10].trim()) & 255);
        return calendar;
    }
}
