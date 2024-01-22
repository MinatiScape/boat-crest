package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.RawPPGHistoryData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.ReadRawPPGDataReq;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class ReadRawPPGDataRes extends BaseResponse {
    public ArrayList<RawPPGHistoryData> e;

    public ReadRawPPGDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final ArrayList<RawPPGHistoryData> b() {
        ResponseData responseData;
        int i;
        if ((this.b instanceof ReadRawPPGDataReq) && (responseData = this.c) != null && responseData.getDataList() != null) {
            this.e = new ArrayList<>();
            ResponseData responseData2 = this.c;
            ArrayList arrayList = new ArrayList();
            ArrayList<String> dataList = responseData2.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            Iterator it = copyOnWriteArrayList.iterator();
            while (true) {
                i = 1;
                if (!it.hasNext()) {
                    break;
                }
                try {
                    String str = (String) it.next();
                    String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                    if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[3].trim()) & 255) << 8)) == 0) {
                        int length = split.length;
                        for (int i2 = 12; i2 < length; i2++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i2].trim())));
                        }
                    } else {
                        int length2 = split.length;
                        for (int i3 = 4; i3 < length2; i3++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i3].trim())));
                        }
                    }
                } catch (Exception e) {
                    LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            ArrayList arrayList2 = (ArrayList) arrayList.clone();
            byte byteValue = ((Byte) arrayList.get(0)).byteValue();
            while (i < arrayList2.size()) {
                int byteValue2 = i + 13 + ((((Byte) arrayList.get(i + 12)).byteValue() & 255) << 24) + ((((Byte) arrayList.get(i + 11)).byteValue() & 255) << 16) + ((((Byte) arrayList.get(i + 10)).byteValue() & 255) << 8) + (((Byte) arrayList.get(i + 9)).byteValue() & 255);
                this.e.add(new RawPPGHistoryData(arrayList.subList(i, byteValue2)));
                i = byteValue2;
            }
            if (this.e.size() == byteValue) {
                LogHelper.d("ReadRawPPGDataRes", "PPG Data is matching");
                return this.e;
            }
            LogHelper.d("ReadRawPPGDataRes", "PPG Data is not matching");
        }
        return null;
    }

    public ArrayList<RawPPGHistoryData> getRawPPGHistoryData() {
        return b();
    }
}
