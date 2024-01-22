package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleSedentaryData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.ReadSedentaryDataReq;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class ReadSedentaryDataRes extends BaseResponse {
    public ArrayList<BleSedentaryData> e;

    public ReadSedentaryDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final ArrayList<BleSedentaryData> b() {
        BaseRequest baseRequest = this.b;
        if (baseRequest instanceof ReadSedentaryDataReq) {
            ReadSedentaryDataReq readSedentaryDataReq = (ReadSedentaryDataReq) baseRequest;
            ResponseData responseData = this.c;
            if (responseData == null || responseData.getDataList() == null) {
                return null;
            }
            this.e = new ArrayList<>();
            ResponseData responseData2 = this.c;
            ArrayList arrayList = new ArrayList();
            ArrayList<String> dataList = responseData2.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                try {
                    String str = (String) it.next();
                    String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                    if ((((Byte.parseByte(split[3].trim()) & 255) << 8) | (Byte.parseByte(split[2].trim()) & 255)) == 0 && Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
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
            if (arrayList.size() > 0) {
                byte byteValue = ((Byte) arrayList.get(0)).byteValue();
                for (int i3 = 1; i3 < arrayList2.size(); i3 += 5) {
                    int i4 = i3 + 1;
                    ((Byte) arrayList.get(i4)).byteValue();
                    int i5 = i3 + 2;
                    ((Byte) arrayList.get(i5)).byteValue();
                    int i6 = i3 + 3;
                    ((Byte) arrayList.get(i6)).byteValue();
                    int i7 = i3 + 4;
                    ((Byte) arrayList.get(i7)).byteValue();
                    this.e.add(new BleSedentaryData(((Byte) arrayList.get(i3)).byteValue() & 255, ByteBuffer.wrap(new byte[]{0, 0, 0, 0, ((Byte) arrayList.get(i7)).byteValue(), ((Byte) arrayList.get(i6)).byteValue(), ((Byte) arrayList.get(i5)).byteValue(), ((Byte) arrayList.get(i4)).byteValue()}).getLong() * 1000));
                }
                if (this.e.size() == byteValue) {
                    LogHelper.d("ReadSedentaryDataRes", " Data is matching");
                } else {
                    LogHelper.d("ReadSedentaryDataRes", " Data is not matching");
                    return null;
                }
            }
            return this.e;
        }
        return null;
    }

    public ArrayList<BleSedentaryData> getRawSendentaryData() {
        return b();
    }
}
