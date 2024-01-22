package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleGetSOSRecords;
import com.coveiot.sdk.ble.api.model.SOSRecordItem;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetSOSRecordsRes extends BaseResponse implements Serializable {
    private BleGetSOSRecords e;

    public GetSOSRecordsRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    private void a() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        if (this.c.getDataList() != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> dataList = this.c.getDataList();
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
                        Byte.parseByte(split[0]);
                        int length = split.length;
                        for (int i6 = 12; i6 < length; i6++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i6].trim())));
                        }
                    } else {
                        int length2 = split.length;
                        for (int i7 = 4; i7 < length2; i7++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i7].trim())));
                        }
                    }
                } catch (Exception e) {
                    LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            byte byteValue = ((Byte) arrayList.get(0)).byteValue();
            ArrayList arrayList2 = new ArrayList();
            int i8 = 0;
            while (i8 < byteValue) {
                byte byteValue2 = ((Byte) arrayList.get(i)).byteValue();
                int i9 = i + 1 + 1 + 1 + 1 + 1;
                Long valueOf = Long.valueOf((((((Byte) arrayList.get(i5)).byteValue() & 255) << 24) + ((((Byte) arrayList.get(i4)).byteValue() & 255) << 16) + ((((Byte) arrayList.get(i3)).byteValue() & 255) << 8) + (((Byte) arrayList.get(i2)).byteValue() & 255)) * 1000);
                int i10 = i9 + 1;
                int byteValue3 = ((Byte) arrayList.get(i9)).byteValue();
                byte[] bArr = new byte[byteValue3];
                int i11 = 0;
                while (i11 < byteValue3) {
                    bArr[i11] = ((Byte) arrayList.get(i10)).byteValue();
                    i11++;
                    i10++;
                }
                String str2 = new String(bArr, StandardCharsets.UTF_8);
                int i12 = i10 + 1;
                int byteValue4 = ((Byte) arrayList.get(i10)).byteValue();
                byte[] bArr2 = new byte[byteValue4];
                int i13 = 0;
                while (i13 < byteValue4) {
                    bArr2[i13] = ((Byte) arrayList.get(i12)).byteValue();
                    i13++;
                    i12++;
                }
                arrayList2.add(new SOSRecordItem(byteValue2, valueOf, byteValue3, str2, byteValue4, new String(bArr2, StandardCharsets.UTF_8)));
                i8++;
                i = i12;
            }
            BleGetSOSRecords bleGetSOSRecords = new BleGetSOSRecords(byteValue, arrayList2);
            LogHelper.i("Get SOS records", "" + bleGetSOSRecords);
            this.e = bleGetSOSRecords;
        }
    }

    public BleGetSOSRecords getBleGetSOSRecords() {
        a();
        return this.e;
    }
}
