package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.api.model.FavouriteLocationData;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes9.dex */
public class SetNavigationFavouriteLocationReq extends HybridCommandRequest {
    public ArrayList<FavouriteLocationData> h;

    public SetNavigationFavouriteLocationReq(Object obj, short s, ArrayList<FavouriteLocationData> arrayList) {
        super(obj, s);
        this.h = arrayList;
    }

    public final byte[] a() {
        int i;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf((byte) this.h.size()));
        Iterator<FavouriteLocationData> it = this.h.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            FavouriteLocationData next = it.next();
            arrayList.add(Byte.valueOf((byte) next.getLocationIndex()));
            arrayList.add(Byte.valueOf((byte) next.getPlaceId()));
            byte[] bytes = (next.getPlaceTagName() + "\u0000").getBytes(StandardCharsets.UTF_16LE);
            byte[] bytes2 = (next.getPlaceAddress() + "\u0000").getBytes(StandardCharsets.UTF_16LE);
            if (bytes.length >= 120) {
                bytes = Arrays.copyOfRange(bytes, 0, 120);
            }
            if (bytes2.length >= 120) {
                bytes2 = Arrays.copyOfRange(bytes2, 0, 120);
            }
            for (byte b : bytes) {
                arrayList.add(Byte.valueOf(b));
            }
            while (i < bytes2.length) {
                arrayList.add(Byte.valueOf(bytes2[i]));
                i++;
            }
        }
        byte[] bArr = new byte[arrayList.size()];
        int i2 = -1;
        while (i < arrayList.size()) {
            i2++;
            bArr[i2] = ((Byte) arrayList.get(i)).byteValue();
            i++;
        }
        return bArr;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList<CommandBytes> generateSinglePacketRequest;
        byte[] a2 = a();
        byte[] bArr = {65};
        if (a().length + 4 > 150) {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateRequest((byte) 0, BleUUID.CMD_ID_B6, a2, bArr, false, false);
        } else {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 0, BleUUID.CMD_ID_B6, a2, bArr, false);
        }
        this.f7474a = new ExpectedDataSize(generateSinglePacketRequest.size());
        return generateSinglePacketRequest;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_NAVIGATION_FAVOURITE_LOCATION;
    }

    public ArrayList<FavouriteLocationData> getFavouriteLocationData() {
        return this.h;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    public void setFavouriteLocationData(ArrayList<FavouriteLocationData> arrayList) {
        this.h = arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldSendAllPacketsAtOnce() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldWaitForRes() {
        return true;
    }
}
