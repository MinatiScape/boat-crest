package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetWatchFaceListRes extends BaseResponse {
    public GetWatchFaceListRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final ArrayList<Integer> b() {
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        String str = (String) copyOnWriteArrayList.get(0);
        ArrayList<Integer> arrayList = new ArrayList<>();
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        for (int i = 5; i < split.length; i += 2) {
            arrayList.add(Integer.valueOf((Byte.parseByte(split[i].trim()) & 255) | ((Byte.parseByte(split[i + 1].trim()) & 255) << 8)));
        }
        return arrayList;
    }

    public ArrayList<Integer> getWatchFaceIds() {
        return b();
    }
}
