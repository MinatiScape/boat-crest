package com.coveiot.sdk.ble.api.response;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.TodaysWalkData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import java.nio.ByteBuffer;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class TodaysFitnessDataRes extends BaseResponse {
    public TodaysFitnessDataRes(CommandType commandType, BaseRequest baseRequest) {
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
            todaysWalkData.setTotalSteps(((Byte.parseByte(split[7].trim()) & 255) << 24) + ((Byte.parseByte(split[6].trim()) & 255) << 16) + ((Byte.parseByte(split[5].trim()) & 255) << 8) + (Byte.parseByte(split[4].trim()) & 255));
            double d = ByteBuffer.wrap(new byte[]{Byte.parseByte(split[11].trim()), Byte.parseByte(split[10].trim()), Byte.parseByte(split[9].trim()), Byte.parseByte(split[8].trim())}).getFloat();
            byte parseByte = Byte.parseByte(split[12].trim());
            todaysWalkData.setTotalCalories(ByteBuffer.wrap(new byte[]{Byte.parseByte(split[15].trim()), Byte.parseByte(split[14].trim()), Byte.parseByte(split[13].trim()), parseByte}).getFloat());
            todaysWalkData.setTotalDistance((int) Math.floor(d));
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
