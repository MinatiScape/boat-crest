package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleConfiguredActivities;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetConfiguredActivityListRes extends BaseResponse {
    public BleConfiguredActivities e;

    public GetConfiguredActivityListRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public BleConfiguredActivities getBleConfiguredActivities() {
        processResponse();
        return this.e;
    }

    public final void processResponse() {
        int i;
        if (this.c.getDataList() != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> dataList = this.c.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            Iterator it = copyOnWriteArrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                try {
                    String str = (String) it.next();
                    String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                    if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[3].trim()) & 255) << 8)) == 0) {
                        Byte.parseByte(split[0]);
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
            byte byteValue = ((Byte) arrayList.get(0)).byteValue();
            ArrayList arrayList2 = new ArrayList();
            int i4 = 0;
            for (i = 0; i < byteValue; i++) {
                int i5 = i4 + 1;
                byte byteValue2 = ((Byte) arrayList.get(i5)).byteValue();
                int i6 = i5 + 1;
                byte byteValue3 = ((Byte) arrayList.get(i6)).byteValue();
                int i7 = i6 + 1;
                int i8 = i7 + 1;
                i4 = i8 + 1;
                arrayList2.add(new BleConfiguredActivities.ActivityInfo(byteValue2, (((Byte) arrayList.get(i7)).byteValue() & 255) | ((((Byte) arrayList.get(i8)).byteValue() & 255) << 8), byteValue3, ((Byte) arrayList.get(i4)).byteValue()));
            }
            this.e = new BleConfiguredActivities(arrayList2);
        }
    }
}
