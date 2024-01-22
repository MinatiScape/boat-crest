package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BatterySaverConfig;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetBatterySaverConfigRes extends BaseResponse {
    public GetBatterySaverConfigRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public BatterySaverConfig getBatterySaverConfig() {
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        boolean z = false;
        String str = (String) copyOnWriteArrayList.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            arrayList.add(Byte.valueOf(Byte.parseByte(str2.trim())));
        }
        List subList = arrayList.subList(5, arrayList.size());
        boolean z2 = ((Byte) subList.get(0)).byteValue() == 1;
        byte byteValue = ((Byte) subList.get(1)).byteValue();
        byte byteValue2 = ((Byte) subList.get(2)).byteValue();
        if (subList.size() > 3 && ((Byte) subList.get(3)).byteValue() == 1) {
            z = true;
        }
        return new BatterySaverConfig(z2, byteValue, byteValue2, z);
    }
}
