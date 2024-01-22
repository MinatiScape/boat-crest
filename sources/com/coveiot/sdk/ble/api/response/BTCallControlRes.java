package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BTCallControlData;
import com.coveiot.sdk.ble.api.request.BTCallControlReq;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class BTCallControlRes extends BaseResponse {
    public BTCallControlData e;

    public BTCallControlRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public BTCallControlData getBtCallingEnableData() {
        processResponse();
        return this.e;
    }

    public final void processResponse() {
        int i;
        if (!(this.b instanceof BTCallControlReq) || this.c.getDataList() == null) {
            return;
        }
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(this.c.getDataList());
        String str = (String) copyOnWriteArrayList.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        byte[] bArr = new byte[6];
        int i2 = 0;
        while (true) {
            if (i2 >= 6) {
                break;
            }
            bArr[i2] = Byte.parseByte(split[5 + i2].trim());
            i2++;
        }
        StringBuilder sb = new StringBuilder(18);
        for (i = 5; i >= 0; i--) {
            if (sb.length() > 0) {
                sb.append(':');
            }
            sb.append(String.format("%02x", Byte.valueOf(bArr[i])));
        }
        String sb2 = sb.toString();
        int length = split.length - 11;
        byte[] bArr2 = new byte[length];
        for (int i3 = 0; i3 < length; i3++) {
            bArr2[i3] = Byte.parseByte(split[11 + i3].trim());
        }
        this.e = new BTCallControlData(sb2, length > 0 ? new String(bArr2, StandardCharsets.UTF_16LE).trim() : null);
    }
}
