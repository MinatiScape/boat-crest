package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleGetSmartAlertConfigData;
import com.coveiot.sdk.ble.api.model.BleSmartAlertAppData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetSmartAlertConfigRes extends BaseResponse {
    public BleGetSmartAlertConfigData e;

    public GetSmartAlertConfigRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public BleGetSmartAlertConfigData getSmartAlertConfigData() {
        processResponse();
        BleGetSmartAlertConfigData bleGetSmartAlertConfigData = this.e;
        if (bleGetSmartAlertConfigData != null) {
            LogHelper.i("Smart Alert Config : ", bleGetSmartAlertConfigData.toString());
        }
        return this.e;
    }

    public void processResponse() {
        char c;
        char c2;
        if (this.c.getDataList() != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> dataList = this.c.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            Iterator it = copyOnWriteArrayList.iterator();
            while (true) {
                c = 3;
                int i = 4;
                c2 = 2;
                if (!it.hasNext()) {
                    break;
                }
                try {
                    String str = (String) it.next();
                    String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                    if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
                        if ((((Byte.parseByte(split[3].trim()) & 255) << 8) | (Byte.parseByte(split[2].trim()) & 255)) == 0) {
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
                    LogHelper.d("Exception", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            boolean z = ((Byte) arrayList.get(1)).byteValue() == 1;
            byte byteValue = ((Byte) arrayList.get(2)).byteValue();
            ArrayList arrayList2 = new ArrayList();
            int i3 = 2;
            int i4 = 0;
            while (i4 < byteValue) {
                byte[] bArr = new byte[4];
                bArr[0] = 0;
                bArr[1] = 0;
                bArr[c2] = 0;
                int i5 = i3 + 1;
                bArr[c] = ((Byte) arrayList.get(i5)).byteValue();
                int i6 = ByteBuffer.wrap(bArr).getInt();
                byte[] bArr2 = new byte[4];
                bArr2[0] = 0;
                bArr2[1] = 0;
                bArr2[c2] = 0;
                int i7 = i5 + 1;
                bArr2[c] = ((Byte) arrayList.get(i7)).byteValue();
                int i8 = ByteBuffer.wrap(bArr2).getInt();
                ArrayList arrayList3 = new ArrayList();
                int i9 = i7;
                while (i9 < i8 + i7) {
                    i9++;
                    arrayList3.add(arrayList.get(i9));
                }
                byte[] bArr3 = new byte[arrayList3.size()];
                for (int i10 = 0; i10 < arrayList3.size(); i10++) {
                    bArr3[i10] = ((Byte) arrayList3.get(i10)).byteValue();
                }
                String str2 = new String(bArr3, StandardCharsets.UTF_16LE);
                byte[] bArr4 = new byte[4];
                bArr4[0] = 0;
                bArr4[1] = 0;
                bArr4[c2] = 0;
                int i11 = i9 + 1;
                bArr4[3] = ((Byte) arrayList.get(i11)).byteValue();
                int i12 = i11 + 1;
                int i13 = i12 + 1;
                arrayList2.add(new BleSmartAlertAppData(i6, str2, ByteBuffer.wrap(bArr4).getInt(), ByteBuffer.wrap(new byte[]{0, 0, ((Byte) arrayList.get(i13)).byteValue(), ((Byte) arrayList.get(i12)).byteValue()}).getInt()));
                i4++;
                i3 = i13;
                c = 3;
                c2 = 2;
            }
            this.e = new BleGetSmartAlertConfigData(z, arrayList2);
        }
    }
}
