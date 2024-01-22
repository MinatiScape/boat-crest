package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.DeviceMatchType;
import com.coveiot.sdk.ble.api.model.NearbyDevice;
import com.coveiot.sdk.ble.api.model.NearbyDevices;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetNearbyDevicesRes extends BaseResponse {
    public static final char[] f = "0123456789ABCDEF".toCharArray();
    public ArrayList<Byte> e;

    public GetNearbyDevicesRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
        this.e = new ArrayList<>();
    }

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = f;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    public final int a(byte[] bArr, byte b) {
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] == b) {
                return i;
            }
        }
        return 0;
    }

    public final DeviceMatchType b(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 4) {
                    if (i != 8) {
                        if (i != 16) {
                            return DeviceMatchType.UUID;
                        }
                        return DeviceMatchType.SHORT_NAME;
                    }
                    return DeviceMatchType.APPEARANCE;
                }
                return DeviceMatchType.UUID;
            }
            return DeviceMatchType.ADDRESS;
        }
        return DeviceMatchType.NAME;
    }

    public final ArrayList<NearbyDevice> c() {
        int i;
        char c;
        char c2;
        String str;
        ResponseData responseData = this.c;
        ArrayList<NearbyDevice> arrayList = new ArrayList<>();
        ArrayList<String> dataList = responseData.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Iterator it = copyOnWriteArrayList.iterator();
        LogHelper.i("GetNearbyDevicesRes", "dataList.size(): " + dataList.size(), ModuleNames.BLEABSTRACT.getModuleName());
        while (true) {
            i = 8;
            c = 3;
            c2 = 2;
            int i2 = 4;
            if (!it.hasNext()) {
                break;
            }
            try {
                String str2 = (String) it.next();
                String[] split = str2.substring(1, str2.length() - 1).split(Constants.SEPARATOR_COMMA);
                if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
                    if ((((Byte.parseByte(split[3].trim()) & 255) << 8) | (Byte.parseByte(split[2].trim()) & 255)) == 0) {
                        ByteBuffer.wrap(new byte[]{0, 0, Byte.parseByte(split[11].trim()), Byte.parseByte(split[10].trim())}).getInt();
                        int length = split.length;
                        for (int i3 = 12; i3 < length; i3++) {
                            this.e.add(Byte.valueOf(Byte.parseByte(split[i3].trim())));
                        }
                    } else {
                        int length2 = split.length;
                        while (i2 < length2) {
                            this.e.add(Byte.valueOf(Byte.parseByte(split[i2].trim())));
                            i2++;
                        }
                    }
                } else {
                    int length3 = split.length;
                    while (i2 < length3) {
                        this.e.add(Byte.valueOf(Byte.parseByte(split[i2].trim())));
                        i2++;
                    }
                }
            } catch (Exception e) {
                LogHelper.d("GetNearbyDevicesRes", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        LogHelper.i("GetNearbyDevicesRes", "filteredDataList.size(): " + this.e.size(), ModuleNames.BLEABSTRACT.getModuleName());
        int size = this.e.size();
        int i4 = 0;
        while (i4 < size) {
            byte[] bArr = new byte[i];
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[c2] = 0;
            bArr[c] = 0;
            bArr[4] = this.e.get(i4 + 3).byteValue();
            bArr[5] = this.e.get(i4 + 2).byteValue();
            bArr[6] = this.e.get(i4 + 1).byteValue();
            bArr[7] = this.e.get(i4).byteValue();
            long j = ByteBuffer.wrap(bArr).getLong() * 1000;
            byte[] bArr2 = new byte[4];
            bArr2[0] = 0;
            bArr2[1] = 0;
            bArr2[c2] = 0;
            bArr2[c] = this.e.get(i4 + 4).byteValue();
            int i5 = ByteBuffer.wrap(bArr2).getInt();
            byte byteValue = this.e.get(i4 + 5).byteValue();
            byte[] bArr3 = new byte[18];
            bArr3[0] = this.e.get(i4 + 6).byteValue();
            bArr3[1] = this.e.get(i4 + 7).byteValue();
            bArr3[c2] = this.e.get(i4 + 8).byteValue();
            bArr3[c] = this.e.get(i4 + 9).byteValue();
            bArr3[4] = this.e.get(i4 + 10).byteValue();
            bArr3[5] = this.e.get(i4 + 11).byteValue();
            bArr3[6] = this.e.get(i4 + 12).byteValue();
            bArr3[7] = this.e.get(i4 + 13).byteValue();
            bArr3[8] = this.e.get(i4 + 14).byteValue();
            bArr3[9] = this.e.get(i4 + 15).byteValue();
            bArr3[10] = this.e.get(i4 + 16).byteValue();
            bArr3[11] = this.e.get(i4 + 17).byteValue();
            bArr3[12] = this.e.get(i4 + 18).byteValue();
            bArr3[13] = this.e.get(i4 + 19).byteValue();
            bArr3[14] = this.e.get(i4 + 20).byteValue();
            bArr3[15] = this.e.get(i4 + 21).byteValue();
            bArr3[16] = this.e.get(i4 + 22).byteValue();
            bArr3[17] = this.e.get(i4 + 23).byteValue();
            try {
                str = new String(Arrays.copyOfRange(bArr3, 0, a(bArr3, (byte) 0)), "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                str = "";
            }
            NearbyDevice nearbyDevice = new NearbyDevice();
            nearbyDevice.setDeviceName(str);
            nearbyDevice.setRssi(byteValue);
            nearbyDevice.setTimestamp(j);
            nearbyDevice.setType(b(i5));
            arrayList.add(nearbyDevice);
            i4 += 24;
            i = 8;
            c = 3;
            c2 = 2;
        }
        return arrayList;
    }

    public NearbyDevices getNearbyDevices() {
        return new NearbyDevices(c());
    }
}
