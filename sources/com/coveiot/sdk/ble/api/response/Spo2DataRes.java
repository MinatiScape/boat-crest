package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.ManualSpo2Data;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.SPO2DataReq;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class Spo2DataRes extends BaseResponse {
    public ArrayList<ManualSpo2Data> e;

    public Spo2DataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final ArrayList<ManualSpo2Data> b() {
        BaseRequest baseRequest = this.b;
        if (baseRequest instanceof SPO2DataReq) {
            SPO2DataReq sPO2DataReq = (SPO2DataReq) baseRequest;
            if (this.c.getDataList() != null) {
                this.e = new ArrayList<>();
                ResponseData responseData = this.c;
                Calendar calendar = Calendar.getInstance();
                calendar.set(11, 0);
                calendar.set(12, 0);
                calendar.set(13, 0);
                calendar.add(6, -responseData.getDay());
                ArrayList arrayList = new ArrayList();
                ArrayList<String> dataList = responseData.getDataList();
                CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
                copyOnWriteArrayList.addAll(dataList);
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    try {
                        String str = (String) it.next();
                        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                        int i = 4;
                        if ((((Byte.parseByte(split[3].trim()) & 255) << 8) | (Byte.parseByte(split[2].trim()) & 255)) == 0) {
                            if (Byte.parseByte(split[0].trim()) == Byte.MAX_VALUE) {
                                int length = split.length;
                                for (int i2 = 12; i2 < length; i2++) {
                                    arrayList.add(Byte.valueOf(Byte.parseByte(split[i2].trim())));
                                }
                            } else {
                                int length2 = split.length;
                                while (i < length2) {
                                    arrayList.add(Byte.valueOf(Byte.parseByte(split[i].trim())));
                                    i++;
                                }
                            }
                        } else {
                            int length3 = split.length;
                            while (i < length3) {
                                arrayList.add(Byte.valueOf(Byte.parseByte(split[i].trim())));
                                i++;
                            }
                        }
                    } catch (Exception e) {
                        LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                    }
                }
                for (int i3 = 0; i3 < arrayList.size() - 3; i3 += 4) {
                    ((Byte) arrayList.get(i3)).byteValue();
                    calendar.set(11, ((Byte) arrayList.get(i3 + 1)).byteValue() & 255);
                    calendar.set(12, ((Byte) arrayList.get(i3 + 2)).byteValue() & 255);
                    this.e.add(new ManualSpo2Data(calendar.getTimeInMillis(), ((Byte) arrayList.get(i3 + 3)).byteValue() & 255));
                }
                return this.e;
            }
            return null;
        }
        return null;
    }

    public ArrayList<ManualSpo2Data> getManualSpo2Data() {
        return b();
    }
}
