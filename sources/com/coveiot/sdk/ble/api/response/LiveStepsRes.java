package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.LiveStepsData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class LiveStepsRes extends BaseResponse {
    public LiveStepsRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final LiveStepsData b() {
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            try {
                LiveStepsData liveStepsData = new LiveStepsData();
                String str = (String) it.next();
                String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                liveStepsData.setLiveSteps(((Byte.parseByte(split[7].trim()) & 255) << 24) + ((Byte.parseByte(split[6].trim()) & 255) << 16) + ((Byte.parseByte(split[5].trim()) & 255) << 8) + (Byte.parseByte(split[4].trim()) & 255));
                if (split.length == 16) {
                    double d = ByteBuffer.wrap(new byte[]{Byte.parseByte(split[11].trim()), Byte.parseByte(split[10].trim()), Byte.parseByte(split[9].trim()), Byte.parseByte(split[8].trim())}).getFloat();
                    byte parseByte = Byte.parseByte(split[12].trim());
                    liveStepsData.setCalories(ByteBuffer.wrap(new byte[]{Byte.parseByte(split[15].trim()), Byte.parseByte(split[14].trim()), Byte.parseByte(split[13].trim()), parseByte}).getFloat());
                    liveStepsData.setMeters(d);
                }
                LogHelper.d("LiveStepsRes", liveStepsData.toString());
                return liveStepsData;
            } catch (Exception e) {
                LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        return null;
    }

    public LiveStepsData getLiveStepsData() {
        return b();
    }
}
