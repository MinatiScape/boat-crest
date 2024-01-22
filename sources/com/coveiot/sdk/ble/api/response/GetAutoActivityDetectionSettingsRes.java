package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.AppAutoActivityDetectionModel;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetAutoActivityDetectionSettingsRes extends BaseResponse {
    public AppAutoActivityDetectionModel e;

    public GetAutoActivityDetectionSettingsRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final byte a(byte b, int i) {
        return (byte) ((b >> i) & 1);
    }

    public AppAutoActivityDetectionModel getAutoActivityDetectionModel() {
        processResponse();
        return this.e;
    }

    public final void processResponse() {
        if (this.c.getDataList() != null) {
            ArrayList<String> dataList = this.c.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            String str = (String) copyOnWriteArrayList.get(0);
            String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            byte[] bArr = new byte[split.length];
            for (int i = 0; i < split.length; i++) {
                bArr[i] = Byte.parseByte(split[i].trim());
            }
            byte[] bArr2 = new byte[32];
            int i2 = 0;
            int i3 = 5;
            while (i3 <= 8) {
                for (int i4 = 0; i4 < 8; i4++) {
                    bArr2[i2] = a(bArr[i3], i4);
                    i2++;
                }
                i3++;
            }
            boolean z = a(bArr[i3], 0) == 1;
            boolean z2 = a(bArr[i3], 0) == 2;
            boolean z3 = a(bArr[i3], 0) == 3;
            boolean z4 = a(bArr[i3], 0) == 4;
            boolean z5 = a(bArr[i3], 0) == 5;
            boolean z6 = a(bArr[i3], 0) == 6;
            int i5 = i3 + 1;
            boolean z7 = a(bArr[i3], 0) == 7;
            int i6 = i5 + 1;
            int i7 = bArr[i5] & 255;
            int i8 = i6 + 1;
            boolean z8 = bArr[i6] == 1;
            ArrayList arrayList = new ArrayList();
            byte b = bArr[i8];
            if (b > 0) {
                int i9 = i8;
                int i10 = 0;
                while (i10 < b) {
                    int i11 = i9 + 1;
                    int i12 = i11 + 1;
                    int i13 = i12 + 1;
                    byte b2 = b;
                    int i14 = i13 + 1;
                    arrayList.add(new AppAutoActivityDetectionModel.Period(Integer.valueOf((bArr[i11] & 255) | ((bArr[i12] & 255) << 8)), Integer.valueOf((bArr[i13] & 255) | ((bArr[i14] & 255) << 8))));
                    i10++;
                    bArr2 = bArr2;
                    i9 = i14;
                    b = b2;
                }
            }
            this.e = new AppAutoActivityDetectionModel(bArr2, Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3), Boolean.valueOf(z4), Boolean.valueOf(z5), Boolean.valueOf(z6), Boolean.valueOf(z7), Integer.valueOf(i7), Boolean.valueOf(z8), arrayList);
        }
    }
}
