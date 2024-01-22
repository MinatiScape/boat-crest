package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.RawAccelerometerHistoryData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.ReadRawAccelerometerDataReq;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class ReadRawAccelerometerDataRes extends BaseResponse {
    public ArrayList<RawAccelerometerHistoryData> e;

    public ReadRawAccelerometerDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final ArrayList<RawAccelerometerHistoryData> b() {
        if ((this.b instanceof ReadRawAccelerometerDataReq) && this.c.getDataList() != null) {
            this.e = new ArrayList<>();
            ResponseData responseData = this.c;
            ArrayList arrayList = new ArrayList();
            ArrayList<String> dataList = responseData.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                try {
                    String str = (String) it.next();
                    String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                    if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[3].trim()) & 255) << 8)) == 0) {
                        int length = split.length;
                        for (int i = 12; i < length; i++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i].trim())));
                        }
                    } else {
                        int length2 = split.length;
                        for (int i2 = 4; i2 < length2; i2++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i2].trim())));
                        }
                    }
                } catch (Exception e) {
                    LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            ArrayList arrayList2 = (ArrayList) arrayList.clone();
            byte byteValue = ((Byte) arrayList.get(0)).byteValue();
            do {
            } while (1 < arrayList2.size());
            if (this.e.size() == byteValue) {
                LogHelper.d("ReadRawAccelerometerDataRes", "PPG Data is matching");
                return this.e;
            }
            LogHelper.d("ReadRawAccelerometerDataRes", "PPG Data is not matching");
        }
        return null;
    }

    public ArrayList<RawAccelerometerHistoryData> getRawAccelerometerHistoryData() {
        return b();
    }
}
