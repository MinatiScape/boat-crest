package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.LiveHealthData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class LiveHealthRes extends BaseResponse {
    public LiveHealthRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final LiveHealthData b() {
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            try {
                String str = (String) it.next();
                String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                LiveHealthData liveHealthData = new LiveHealthData();
                liveHealthData.setDbpValue(Byte.parseByte(split[5].trim()) & 255);
                liveHealthData.setSbpValue(Byte.parseByte(split[6].trim()) & 255);
                liveHealthData.setHrValue(Byte.parseByte(split[4].trim()) & 255);
                liveHealthData.setStressValue(Byte.parseByte(split[8].trim()) & 255);
                liveHealthData.setRrValue(Byte.parseByte(split[7].trim()) & 255);
                return liveHealthData;
            } catch (Exception e) {
                LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        return null;
    }

    public LiveHealthData getLiveHealthData() {
        return b();
    }
}
