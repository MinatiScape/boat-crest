package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetFirmwareCapabilityRes extends BaseResponse {
    public byte[] e;

    public GetFirmwareCapabilityRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
        this.e = new byte[32];
    }

    public final byte a(byte b, int i) {
        return (byte) ((b >> i) & 1);
    }

    public byte[] getCapabilities() {
        processResponse();
        return this.e;
    }

    public final void processResponse() {
        if (this.c.getDataList() != null) {
            ArrayList<String> dataList = this.c.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            String str = (String) copyOnWriteArrayList.get(0);
            String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            int i = 0;
            for (int i2 = 5; i2 <= 8; i2++) {
                byte parseByte = Byte.parseByte(split[i2].trim());
                for (int i3 = 0; i3 < 8; i3++) {
                    this.e[i] = a(parseByte, i3);
                    i++;
                }
            }
        }
    }
}
