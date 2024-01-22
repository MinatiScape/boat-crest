package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleAlarmInfo;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.BleUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetAlarmListRes extends BaseResponse {
    public GetAlarmListRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public List<BleAlarmInfo> getAlarmInfoList() {
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        String str = (String) copyOnWriteArrayList.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            arrayList.add(Byte.valueOf(Byte.parseByte(str2.trim())));
        }
        List subList = arrayList.subList(4, arrayList.size());
        ((Byte) subList.get(0)).byteValue();
        ArrayList arrayList2 = new ArrayList();
        List subList2 = subList.subList(1, subList.size());
        while (subList2.size() >= 5) {
            byte byteValue = ((Byte) subList2.get(0)).byteValue();
            List<Integer> eachBit = BleUtils.getEachBit(((Byte) subList2.get(1)).byteValue() & 255);
            arrayList2.add(new BleAlarmInfo(eachBit.size() == 8 && eachBit.get(7).intValue() == 1, byteValue, "", ((Byte) subList2.get(2)).byteValue(), ((Byte) subList2.get(3)).byteValue(), eachBit.get(0).intValue() == 1, eachBit.get(1).intValue() == 1, eachBit.get(2).intValue() == 1, eachBit.get(3).intValue() == 1, eachBit.get(4).intValue() == 1, eachBit.get(5).intValue() == 1, eachBit.get(6).intValue() == 1));
            subList2 = subList2.subList(4, subList2.size());
            while (subList2.size() > 0) {
                byte byteValue2 = ((Byte) subList2.get(0)).byteValue();
                subList2 = subList2.subList(1, subList2.size());
                if (byteValue2 == 0) {
                    break;
                }
            }
        }
        return arrayList2;
    }
}
