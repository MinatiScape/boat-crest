package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetCurrentWatchFaceRes extends BaseResponse {
    public GetCurrentWatchFaceRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final Integer b() {
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        String str = (String) copyOnWriteArrayList.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        return Integer.valueOf(((Byte.parseByte(split[5].trim()) & 255) << 8) | (Byte.parseByte(split[4].trim()) & 255));
    }

    public Integer getCurrentWatchFaceId() {
        return b();
    }
}
