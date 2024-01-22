package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleSensAISummaryData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetSensAISummaryDataRes extends BaseResponse {
    public List<BleSensAISummaryData> e;

    public GetSensAISummaryDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final void a() {
        int i;
        char c;
        char c2;
        byte b;
        byte b2;
        int i2;
        int i3;
        byte b3;
        char c3;
        char c4;
        byte b4;
        byte b5;
        byte b6;
        byte b7;
        byte b8;
        byte b9;
        byte b10;
        byte b11;
        byte b12;
        byte b13;
        if (this.c.getDataList() != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> dataList = this.c.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            Iterator it = copyOnWriteArrayList.iterator();
            while (true) {
                i = 8;
                c = 3;
                c2 = 2;
                b = 0;
                b2 = 1;
                if (!it.hasNext()) {
                    break;
                }
                try {
                    String str = (String) it.next();
                    String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                    if ((((Byte.parseByte(split[3].trim()) & 255) << 8) | (Byte.parseByte(split[2].trim()) & 255)) == 0) {
                        Byte.parseByte(split[0]);
                        int length = split.length;
                        for (int i4 = 12; i4 < length; i4++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i4].trim())));
                        }
                    } else {
                        int length2 = split.length;
                        for (int i5 = 4; i5 < length2; i5++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i5].trim())));
                        }
                    }
                } catch (Exception e) {
                    LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            byte byteValue = ((Byte) arrayList.get(0)).byteValue();
            int i6 = 0;
            int i7 = 0;
            while (i6 < byteValue) {
                int i8 = i7 + b2;
                byte byteValue2 = ((Byte) arrayList.get(i8)).byteValue();
                int i9 = i8 + b2;
                byte byteValue3 = ((Byte) arrayList.get(i9)).byteValue();
                int i10 = i9 + b2;
                byte byteValue4 = ((Byte) arrayList.get(i10)).byteValue();
                int i11 = i10 + b2;
                byte byteValue5 = ((Byte) arrayList.get(i11)).byteValue();
                int i12 = i11 + b2;
                byte byteValue6 = ((Byte) arrayList.get(i12)).byteValue();
                int i13 = i12 + b2;
                byte byteValue7 = ((Byte) arrayList.get(i13)).byteValue();
                byte[] bArr = new byte[i];
                bArr[b] = b;
                bArr[b2] = b;
                bArr[c2] = b;
                bArr[c] = byteValue7;
                bArr[4] = byteValue6;
                bArr[5] = byteValue5;
                bArr[6] = byteValue4;
                bArr[7] = byteValue3;
                long j = ByteBuffer.wrap(bArr).getLong();
                int i14 = i13 + b2;
                byte byteValue8 = ((Byte) arrayList.get(i14)).byteValue();
                int i15 = i14 + b2;
                byte byteValue9 = ((Byte) arrayList.get(i15)).byteValue();
                int i16 = i15 + b2;
                byte byteValue10 = ((Byte) arrayList.get(i16)).byteValue();
                int i17 = i16 + b2;
                byte byteValue11 = ((Byte) arrayList.get(i17)).byteValue();
                int i18 = i17 + b2;
                byte byteValue12 = ((Byte) arrayList.get(i18)).byteValue();
                byte[] bArr2 = new byte[i];
                bArr2[b] = b;
                bArr2[b2] = b;
                bArr2[c2] = b;
                bArr2[c] = b;
                bArr2[4] = byteValue12;
                bArr2[5] = byteValue11;
                bArr2[6] = byteValue10;
                bArr2[7] = byteValue9;
                long j2 = ByteBuffer.wrap(bArr2).getLong() * 1000;
                int i19 = i18 + b2;
                byte byteValue13 = ((Byte) arrayList.get(i19)).byteValue();
                int i20 = i19 + b2;
                byte byteValue14 = ((Byte) arrayList.get(i20)).byteValue();
                byte[] bArr3 = new byte[4];
                bArr3[b] = b;
                bArr3[b2] = b;
                bArr3[c2] = byteValue14;
                bArr3[c] = byteValue13;
                int i21 = ByteBuffer.wrap(bArr3).getInt();
                int i22 = i20 + b2;
                byte byteValue15 = ((Byte) arrayList.get(i22)).byteValue();
                int i23 = i22 + b2;
                byte byteValue16 = ((Byte) arrayList.get(i23)).byteValue();
                byte[] bArr4 = new byte[4];
                bArr4[b] = b;
                bArr4[b2] = b;
                bArr4[c2] = byteValue16;
                bArr4[c] = byteValue15;
                int i24 = ByteBuffer.wrap(bArr4).getInt();
                int i25 = i23 + b2;
                byte byteValue17 = ((Byte) arrayList.get(i25)).byteValue();
                int i26 = i25 + b2;
                byte byteValue18 = ((Byte) arrayList.get(i26)).byteValue();
                int i27 = i26 + b2;
                byte byteValue19 = ((Byte) arrayList.get(i27)).byteValue();
                int i28 = i27 + b2;
                byte byteValue20 = ((Byte) arrayList.get(i28)).byteValue();
                byte[] bArr5 = new byte[8];
                bArr5[b] = b;
                bArr5[b2] = b;
                bArr5[2] = b;
                bArr5[3] = b;
                bArr5[4] = byteValue20;
                bArr5[5] = byteValue19;
                bArr5[6] = byteValue18;
                bArr5[7] = byteValue17;
                long j3 = ByteBuffer.wrap(bArr5).getLong();
                int i29 = i28 + b2;
                byte byteValue21 = ((Byte) arrayList.get(i29)).byteValue();
                int i30 = i29 + b2;
                byte byteValue22 = ((Byte) arrayList.get(i30)).byteValue();
                int i31 = i30 + b2;
                int byteValue23 = ((Byte) arrayList.get(i31)).byteValue() & 255;
                int i32 = i31 + b2;
                byte byteValue24 = ((Byte) arrayList.get(i32)).byteValue();
                int i33 = i32 + b2;
                byte byteValue25 = ((Byte) arrayList.get(i33)).byteValue();
                byte b14 = byteValue;
                byte[] bArr6 = new byte[4];
                bArr6[b] = b;
                bArr6[b2] = b;
                bArr6[2] = byteValue25;
                bArr6[3] = byteValue24;
                int i34 = ByteBuffer.wrap(bArr6).getInt();
                if (byteValue8 == b2) {
                    int i35 = i33 + 1;
                    byte byteValue26 = ((Byte) arrayList.get(i35)).byteValue();
                    int i36 = i35 + b2;
                    byte byteValue27 = ((Byte) arrayList.get(i36)).byteValue();
                    i2 = byteValue23;
                    byte[] bArr7 = new byte[4];
                    bArr7[b] = b;
                    bArr7[1] = b;
                    bArr7[2] = byteValue27;
                    bArr7[3] = byteValue26;
                    int i37 = ByteBuffer.wrap(bArr7).getInt();
                    int i38 = i36 + 1;
                    byte byteValue28 = ((Byte) arrayList.get(i38)).byteValue();
                    int i39 = i38 + 1;
                    byte byteValue29 = ((Byte) arrayList.get(i39)).byteValue();
                    byte[] bArr8 = new byte[4];
                    bArr8[b] = b;
                    bArr8[1] = b;
                    bArr8[2] = byteValue29;
                    bArr8[3] = byteValue28;
                    int i40 = ByteBuffer.wrap(bArr8).getInt();
                    int i41 = i39 + 1;
                    byte byteValue30 = ((Byte) arrayList.get(i41)).byteValue();
                    int i42 = i41 + 1;
                    byte byteValue31 = ((Byte) arrayList.get(i42)).byteValue();
                    int i43 = i42 + 1;
                    byte byteValue32 = ((Byte) arrayList.get(i43)).byteValue();
                    byte[] bArr9 = new byte[4];
                    bArr9[b] = b;
                    bArr9[1] = b;
                    bArr9[2] = byteValue32;
                    bArr9[3] = byteValue31;
                    int i44 = ByteBuffer.wrap(bArr9).getInt();
                    int i45 = i43 + 1;
                    byte byteValue33 = ((Byte) arrayList.get(i45)).byteValue();
                    i3 = i45 + 1;
                    byte byteValue34 = ((Byte) arrayList.get(i3)).byteValue();
                    byte[] bArr10 = new byte[4];
                    bArr10[b] = b;
                    bArr10[1] = b;
                    bArr10[2] = byteValue34;
                    bArr10[3] = byteValue33;
                    b9 = ByteBuffer.wrap(bArr10).getInt();
                    b7 = byteValue30;
                    b10 = b;
                    b11 = b10;
                    b12 = b11;
                    b13 = b12;
                    b4 = b13;
                    b3 = 1;
                    b5 = i37;
                    b6 = i40;
                    b8 = i44;
                    c3 = 2;
                    c4 = 3;
                } else {
                    i2 = byteValue23;
                    int i46 = i33 + 1;
                    byte byteValue35 = ((Byte) arrayList.get(i46)).byteValue();
                    int i47 = i46 + b2;
                    byte byteValue36 = ((Byte) arrayList.get(i47)).byteValue();
                    int i48 = i47 + b2;
                    byte byteValue37 = ((Byte) arrayList.get(i48)).byteValue();
                    byte[] bArr11 = new byte[4];
                    bArr11[b] = b;
                    bArr11[1] = b;
                    bArr11[2] = byteValue37;
                    bArr11[3] = byteValue36;
                    int i49 = ByteBuffer.wrap(bArr11).getInt();
                    int i50 = i48 + 1;
                    byte byteValue38 = ((Byte) arrayList.get(i50)).byteValue();
                    int i51 = i50 + 1;
                    byte byteValue39 = ((Byte) arrayList.get(i51)).byteValue();
                    byte[] bArr12 = new byte[4];
                    bArr12[b] = b;
                    bArr12[1] = b;
                    bArr12[2] = byteValue39;
                    bArr12[3] = byteValue38;
                    int i52 = ByteBuffer.wrap(bArr12).getInt();
                    int i53 = i51 + 1;
                    byte byteValue40 = ((Byte) arrayList.get(i53)).byteValue();
                    int i54 = i53 + 1;
                    byte byteValue41 = ((Byte) arrayList.get(i54)).byteValue();
                    byte[] bArr13 = new byte[4];
                    bArr13[b] = b;
                    bArr13[1] = b;
                    bArr13[2] = byteValue41;
                    bArr13[3] = byteValue40;
                    int i55 = ByteBuffer.wrap(bArr13).getInt();
                    int i56 = i54 + 1;
                    byte byteValue42 = ((Byte) arrayList.get(i56)).byteValue();
                    i3 = i56 + 1;
                    byte byteValue43 = ((Byte) arrayList.get(i3)).byteValue();
                    byte[] bArr14 = new byte[4];
                    bArr14[b] = b;
                    b3 = 1;
                    bArr14[1] = b;
                    c3 = 2;
                    bArr14[2] = byteValue43;
                    c4 = 3;
                    bArr14[3] = byteValue42;
                    b4 = ByteBuffer.wrap(bArr14).getInt();
                    b5 = b;
                    b6 = b5;
                    b7 = b6;
                    b8 = b7;
                    b9 = b8;
                    b10 = byteValue35;
                    b11 = i49;
                    b12 = i52;
                    b13 = i55;
                }
                int i57 = i3 + 1;
                int byteValue44 = ((Byte) arrayList.get(i57)).byteValue() & 255;
                i7 = i57 + 1;
                BleSensAISummaryData build = new BleSensAISummaryData.Builder(j2, byteValue2, j, byteValue8, i21, i24, j3, byteValue21, byteValue22, i2, i34, b5, b6, b7, b8, b9, ((Byte) arrayList.get(i7)).byteValue() & 255, byteValue44, b10, b11, b12, b13, b4).build();
                if (this.e == null) {
                    this.e = new ArrayList();
                }
                this.e.add(build);
                LogHelper.d("sensAI", "" + this.e);
                i6++;
                b2 = b3;
                c2 = c3;
                c = c4;
                byteValue = b14;
                i = 8;
                b = 0;
            }
        }
    }

    public List<BleSensAISummaryData> getBleActivitySummaryDataList() {
        a();
        return this.e;
    }
}
