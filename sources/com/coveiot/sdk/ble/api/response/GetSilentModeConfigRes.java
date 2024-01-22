package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.SilentModeConfig;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetSilentModeConfigRes extends BaseResponse {
    public GetSilentModeConfigRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public SilentModeConfig getSilentModeConfig() {
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        String str = (String) copyOnWriteArrayList.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            arrayList.add(Byte.valueOf(Byte.parseByte(str2.trim())));
        }
        List subList = arrayList.subList(5, arrayList.size());
        boolean z = ((Byte) subList.get(0)).byteValue() == 1;
        boolean z2 = ((Byte) subList.get(0)).byteValue() == 1;
        ((Byte) subList.get(1)).byteValue();
        ((Byte) subList.get(2)).byteValue();
        return new SilentModeConfig(z, z2);
    }
}
