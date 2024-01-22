package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleGetSensAISummaryDetailData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetSensAISummaryDetailsDataRes extends BaseResponse {
    public static final String f = "GetSensAISummaryDetailsDataRes";
    public BleGetSensAISummaryDetailData e;

    public GetSensAISummaryDetailsDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final void a() {
        int i;
        int i2;
        int i3;
        int i4;
        GetSensAISummaryDetailsDataRes getSensAISummaryDetailsDataRes = this;
        try {
        } catch (Exception e) {
            e = e;
        }
        if (getSensAISummaryDetailsDataRes.c.getDataList() == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList<String> dataList = getSensAISummaryDetailsDataRes.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Iterator it = copyOnWriteArrayList.iterator();
        while (true) {
            i2 = 0;
            i3 = 1;
            if (!it.hasNext()) {
                break;
            }
            try {
                String str = (String) it.next();
                String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                if ((((Byte.parseByte(split[3].trim()) & 255) << 8) | (Byte.parseByte(split[2].trim()) & 255)) == 0) {
                    int length = split.length;
                    for (i = Byte.parseByte(split[0]) == Byte.MAX_VALUE ? 12 : 4; i < length; i++) {
                        arrayList.add(Byte.valueOf(Byte.parseByte(split[i].trim())));
                    }
                } else {
                    int length2 = split.length;
                    while (i < length2) {
                        arrayList.add(Byte.valueOf(Byte.parseByte(split[i].trim())));
                        i++;
                    }
                }
            } catch (Exception e2) {
                LogHelper.d(f, e2.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
            e = e;
            e.printStackTrace();
            return;
        }
        byte byteValue = ((Byte) arrayList.get(0)).byteValue();
        long j = ByteBuffer.wrap(new byte[]{0, 0, 0, ((Byte) arrayList.get(4)).byteValue(), ((Byte) arrayList.get(3)).byteValue(), ((Byte) arrayList.get(2)).byteValue(), ((Byte) arrayList.get(1)).byteValue(), byteValue}).getLong();
        int byteValue2 = ((Byte) arrayList.get(5)).byteValue() & 255;
        int byteValue3 = ((Byte) arrayList.get(6)).byteValue() & 255;
        long j2 = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, ((Byte) arrayList.get(10)).byteValue(), ((Byte) arrayList.get(9)).byteValue(), ((Byte) arrayList.get(8)).byteValue(), ((Byte) arrayList.get(7)).byteValue()}).getLong() * 1000;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        int i5 = 10;
        while (i2 < byteValue3) {
            int i6 = i5 + 1;
            try {
                if (byteValue2 == i3) {
                    int i7 = i6 + 1;
                    byte byteValue4 = ((Byte) arrayList.get(i7)).byteValue();
                    int i8 = i7 + 1;
                    byte byteValue5 = ((Byte) arrayList.get(i8)).byteValue();
                    i4 = i8;
                    byte[] bArr = new byte[i];
                    bArr[0] = 0;
                    bArr[1] = 0;
                    bArr[2] = byteValue5;
                    bArr[3] = byteValue4;
                    arrayList2.add(Integer.valueOf(ByteBuffer.wrap(bArr).getInt()));
                } else {
                    if (byteValue2 == 2) {
                        int i9 = i6 + 1;
                        byte byteValue6 = ((Byte) arrayList.get(i9)).byteValue();
                        int i10 = i9 + 1;
                        byte byteValue7 = ((Byte) arrayList.get(i10)).byteValue();
                        i4 = i10;
                        byte[] bArr2 = new byte[i];
                        bArr2[0] = 0;
                        bArr2[1] = 0;
                        bArr2[2] = byteValue7;
                        bArr2[3] = byteValue6;
                        arrayList3.add(Integer.valueOf(ByteBuffer.wrap(bArr2).getInt()));
                    }
                    int i11 = i6 + 1;
                    arrayList4.add(Integer.valueOf(((Byte) arrayList.get(i11)).byteValue() & 255));
                    int i12 = i11 + 1;
                    arrayList5.add(Integer.valueOf(((Byte) arrayList.get(i12)).byteValue() & 255));
                    int i13 = i12 + 1;
                    arrayList6.add(Integer.valueOf(((Byte) arrayList.get(i13)).byteValue() & 255));
                    int i14 = i13 + 1;
                    byte byteValue8 = ((Byte) arrayList.get(i14)).byteValue();
                    int i15 = i14 + 1;
                    byte byteValue9 = ((Byte) arrayList.get(i15)).byteValue();
                    int i16 = i15 + 1;
                    byte byteValue10 = ((Byte) arrayList.get(i16)).byteValue();
                    int i17 = i16 + 1;
                    arrayList7.add(Integer.valueOf(ByteBuffer.wrap(new byte[]{((Byte) arrayList.get(i17)).byteValue(), byteValue10, byteValue9, byteValue8}).getInt()));
                    int i18 = i17 + 1;
                    arrayList8.add(Integer.valueOf(((Byte) arrayList.get(i18)).byteValue()));
                    i2++;
                    i5 = i18;
                    i = 4;
                    i3 = 1;
                    arrayList5 = arrayList5;
                    getSensAISummaryDetailsDataRes = this;
                }
                arrayList4.add(Integer.valueOf(((Byte) arrayList.get(i11)).byteValue() & 255));
                int i122 = i11 + 1;
                arrayList5.add(Integer.valueOf(((Byte) arrayList.get(i122)).byteValue() & 255));
                int i132 = i122 + 1;
                arrayList6.add(Integer.valueOf(((Byte) arrayList.get(i132)).byteValue() & 255));
                int i142 = i132 + 1;
                byte byteValue82 = ((Byte) arrayList.get(i142)).byteValue();
                int i152 = i142 + 1;
                byte byteValue92 = ((Byte) arrayList.get(i152)).byteValue();
                int i162 = i152 + 1;
                byte byteValue102 = ((Byte) arrayList.get(i162)).byteValue();
                int i172 = i162 + 1;
                arrayList7.add(Integer.valueOf(ByteBuffer.wrap(new byte[]{((Byte) arrayList.get(i172)).byteValue(), byteValue102, byteValue92, byteValue82}).getInt()));
                int i182 = i172 + 1;
                arrayList8.add(Integer.valueOf(((Byte) arrayList.get(i182)).byteValue()));
                i2++;
                i5 = i182;
                i = 4;
                i3 = 1;
                arrayList5 = arrayList5;
                getSensAISummaryDetailsDataRes = this;
            } catch (Exception e3) {
                e = e3;
            }
            i6 = i4;
            int i112 = i6 + 1;
        }
        try {
            this.e = new BleGetSensAISummaryDetailData(j, byteValue2, byteValue3, j2, arrayList2, arrayList4, arrayList5, arrayList6, arrayList7, arrayList8, arrayList3);
            LogHelper.d("sensAI", "" + this.e);
        } catch (Exception e4) {
            e = e4;
        }
    }

    public BleGetSensAISummaryDetailData getSensAISummaryDetailData() {
        a();
        return this.e;
    }
}
