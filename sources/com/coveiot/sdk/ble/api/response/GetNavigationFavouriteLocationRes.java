package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.FavouriteLocationData;
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
public class GetNavigationFavouriteLocationRes extends BaseResponse {
    public ArrayList<FavouriteLocationData> e;

    public GetNavigationFavouriteLocationRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
        this.e = new ArrayList<>();
    }

    public ArrayList<FavouriteLocationData> getFavouriteLocations() {
        if (this.c.getDataList() != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> dataList = this.c.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                try {
                    String str = (String) it.next();
                    String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                    if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[3].trim()) & 255) << 8)) == 0) {
                        Byte.parseByte(split[0]);
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
                    LogHelper.d("Exception", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            ArrayList arrayList2 = new ArrayList(arrayList.subList(2, arrayList.size()));
            while (arrayList2.size() >= 6) {
                FavouriteLocationData favouriteLocationData = new FavouriteLocationData();
                favouriteLocationData.setLocationIndex(ByteBuffer.wrap(new byte[]{0, ((Byte) arrayList2.get(0)).byteValue()}).getShort());
                favouriteLocationData.setPlaceId(ByteBuffer.wrap(new byte[]{0, ((Byte) arrayList2.get(1)).byteValue()}).getShort());
                ArrayList arrayList3 = new ArrayList(arrayList2.subList(2, arrayList2.size()));
                int i3 = 0;
                for (int i4 = 0; i4 < arrayList3.size() / 2; i4++) {
                    int i5 = i4 * 2;
                    if (((Byte) arrayList3.get(i5)).byteValue() == 0 && ((Byte) arrayList3.get(i5 + 1)).byteValue() == 0) {
                        break;
                    }
                    i3++;
                }
                int i6 = i3 * 2;
                if (i6 != 0) {
                    byte[] bArr = new byte[i6];
                    for (int i7 = 0; i7 < i6; i7++) {
                        bArr[i7] = ((Byte) arrayList3.get(i7)).byteValue();
                    }
                    ArrayList arrayList4 = new ArrayList(arrayList3.subList(i6 + 2, arrayList3.size()));
                    favouriteLocationData.setPlaceTagName(new String(bArr, StandardCharsets.UTF_16LE));
                    arrayList3 = arrayList4;
                }
                int i8 = 0;
                for (int i9 = 0; i9 < arrayList3.size() / 2; i9++) {
                    int i10 = i9 * 2;
                    if (((Byte) arrayList3.get(i10)).byteValue() == 0 && ((Byte) arrayList3.get(i10 + 1)).byteValue() == 0) {
                        break;
                    }
                    i8++;
                }
                int i11 = i8 * 2;
                if (i11 != 0) {
                    byte[] bArr2 = new byte[i11];
                    for (int i12 = 0; i12 < i11; i12++) {
                        bArr2[i12] = ((Byte) arrayList3.get(i12)).byteValue();
                    }
                    ArrayList arrayList5 = new ArrayList(arrayList3.subList(i11 + 2, arrayList3.size()));
                    favouriteLocationData.setPlaceAddress(new String(bArr2, StandardCharsets.UTF_16LE));
                    arrayList2 = arrayList5;
                } else {
                    arrayList2 = arrayList3;
                }
                this.e.add(favouriteLocationData);
            }
        }
        return this.e;
    }
}
