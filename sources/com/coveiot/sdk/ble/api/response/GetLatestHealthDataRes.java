package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleHealthData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.GetLatestHealthDataReq;
import com.coveiot.sdk.ble.model.CommandType;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetLatestHealthDataRes extends BaseResponse {
    public GetLatestHealthDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public BleHealthData getData() {
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        String str = (String) copyOnWriteArrayList.get(0);
        new ArrayList();
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        long j = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, Byte.parseByte(split[7].trim()), Byte.parseByte(split[6].trim()), Byte.parseByte(split[5].trim()), Byte.parseByte(split[4].trim())}).getLong() * 1000;
        int i = ByteBuffer.wrap(new byte[]{0, 0, Byte.parseByte(split[9].trim()), Byte.parseByte(split[8].trim())}).getInt();
        BaseRequest baseRequest = this.b;
        if (baseRequest instanceof GetLatestHealthDataReq) {
            return new BleHealthData(((GetLatestHealthDataReq) baseRequest).getHealthDataType(), j, i);
        }
        return null;
    }
}
