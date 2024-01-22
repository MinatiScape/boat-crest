package com.coveiot.sdk.ble.api.response;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.ManualBpData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class ReadManualBpRes extends BaseResponse {
    public ReadManualBpRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public static byte[] c(List<Byte> list) {
        int size = list.size();
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            bArr[i] = list.get(i).byteValue();
        }
        return bArr;
    }

    @Nullable
    public final ArrayList<ManualBpData> b() {
        ResponseData responseData = this.c;
        if (responseData == null) {
            return null;
        }
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
        ArrayList<ManualBpData> arrayList2 = new ArrayList<>();
        int i3 = 0;
        while (i3 < arrayList.size() - 1) {
            int i4 = i3 + 7;
            arrayList2.add(new ManualBpData(c(arrayList.subList(i3, i4))));
            i3 = i4;
        }
        return arrayList2;
    }

    public ArrayList<ManualBpData> getManualBpReadings() {
        return b();
    }
}
