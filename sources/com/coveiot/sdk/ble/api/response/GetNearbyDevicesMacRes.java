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
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetNearbyDevicesMacRes extends BaseResponse {
    public ArrayList<Byte> e;

    public GetNearbyDevicesMacRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
        this.e = new ArrayList<>();
    }

    public final DeviceMatchType a(int i) {
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

    public final ArrayList<NearbyDevice> b() {
        int i;
        ResponseData responseData = this.c;
        ArrayList<NearbyDevice> arrayList = new ArrayList<>();
        ArrayList<String> dataList = responseData.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Iterator it = copyOnWriteArrayList.iterator();
        LogHelper.i("GetNearbyDevicesMacRes", "dataList.size(): " + dataList.size(), ModuleNames.BLEABSTRACT.getModuleName());
        int i2 = 0;
        while (true) {
            i = 8;
            if (!it.hasNext()) {
                break;
            }
            try {
                String str = (String) it.next();
                String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                if ((((Byte.parseByte(split[3].trim()) & 255) << 8) | (Byte.parseByte(split[2].trim()) & 255)) == 0) {
                    i2 = ByteBuffer.wrap(new byte[]{0, 0, Byte.parseByte(split[11].trim()), Byte.parseByte(split[10].trim())}).getInt();
                    int length = split.length;
                    for (int i3 = 12; i3 < length; i3++) {
                        this.e.add(Byte.valueOf(Byte.parseByte(split[i3].trim())));
                    }
                } else {
                    int length2 = split.length;
                    for (int i4 = 4; i4 < length2; i4++) {
                        this.e.add(Byte.valueOf(Byte.parseByte(split[i4].trim())));
                    }
                }
            } catch (Exception e) {
                LogHelper.d("GetNearbyDevicesMacRes", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.i("GetNearbyDevicesMacRes", "filteredDataList.size(): " + this.e.size(), moduleNames.getModuleName());
        LogHelper.i("GetNearbyDevicesMacRes", "packetSize: " + i2, moduleNames.getModuleName());
        if (this.e.size() < i2) {
            LogHelper.e("GetNearbyDevicesMacRes", "Incomplete or corrupt data!", moduleNames.getModuleName());
            return arrayList;
        }
        int size = this.e.size();
        int i5 = 0;
        while (i5 < size) {
            byte[] bArr = new byte[i];
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            bArr[3] = 0;
            bArr[4] = this.e.get(i5 + 3).byteValue();
            bArr[5] = this.e.get(i5 + 2).byteValue();
            bArr[6] = this.e.get(i5 + 1).byteValue();
            bArr[7] = this.e.get(i5).byteValue();
            int i6 = ByteBuffer.wrap(new byte[]{0, 0, 0, this.e.get(i5 + 4).byteValue()}).getInt();
            String format = String.format("%02x:%02x:%02x:%02x:%02x:%02x", this.e.get(i5 + 5), this.e.get(i5 + 6), this.e.get(i5 + 7), this.e.get(i5 + 8), this.e.get(i5 + 9), this.e.get(i5 + 10));
            byte byteValue = this.e.get(i5 + 11).byteValue();
            NearbyDevice nearbyDevice = new NearbyDevice();
            nearbyDevice.setDeviceName(format);
            nearbyDevice.setRssi(byteValue);
            nearbyDevice.setTimestamp(ByteBuffer.wrap(bArr).getLong() * 1000);
            nearbyDevice.setType(a(i6));
            arrayList.add(nearbyDevice);
            i5 += 12;
            i = 8;
        }
        return arrayList;
    }

    public NearbyDevices getNearbyDevices() {
        return new NearbyDevices(b());
    }
}
