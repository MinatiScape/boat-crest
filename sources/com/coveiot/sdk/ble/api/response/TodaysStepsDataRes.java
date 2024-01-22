package com.coveiot.sdk.ble.api.response;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.TodaysWalkData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class TodaysStepsDataRes extends BaseResponse {
    public TodaysStepsDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final boolean b() {
        String str = this.c.getDataList().get(0);
        return str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA).length > 5;
    }

    @Nullable
    public final TodaysWalkData c() {
        TodaysWalkData todaysWalkData = new TodaysWalkData();
        ArrayList<String> dataList = this.c.getDataList();
        if (dataList != null && dataList.size() > 0) {
            String str = dataList.get(0);
            String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            int parseByte = (Byte.parseByte(split[6].trim()) & 255) << 8;
            Byte.parseByte(split[7].trim());
            Byte.parseByte(split[8].trim());
            todaysWalkData.setTotalSteps(parseByte | (Byte.parseByte(split[5].trim()) & 255));
            todaysWalkData.setTotalCalories(0.0d);
            todaysWalkData.setTotalDistance(0);
        }
        return todaysWalkData;
    }

    public TodaysWalkData getStepsData() {
        return c();
    }

    public boolean isSuccess() {
        return b();
    }
}
