package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.LiveTemperatureData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class LiveTemperatureRes extends BaseResponse {
    public static final String TAG = "LiveTemperatureRes";

    public LiveTemperatureRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final LiveTemperatureData b() {
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            try {
                String str = (String) it.next();
                String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                byte[] bArr = new byte[split.length];
                for (int i = 0; i < split.length; i++) {
                    bArr[i] = Byte.parseByte(split[i].trim());
                }
                if (split.length < 9) {
                    return null;
                }
                return new LiveTemperatureData((((bArr[9] & 255) << 8) | (bArr[8] & 255)) / 10.0d, (((bArr[5] & 255) << 8) | (bArr[4] & 255) | ((bArr[6] & 255) << 16) | ((bArr[7] & 255) << 24)) * 1000);
            } catch (Exception e) {
                LogHelper.d(TAG, e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        return null;
    }

    public LiveTemperatureData getLiveTemperatureData() {
        return b();
    }
}
