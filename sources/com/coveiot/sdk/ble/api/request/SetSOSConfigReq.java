package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetSOSConfigReq extends HybridCommandRequest {
    public boolean h;
    public int i;
    public String j;
    public String k;

    public SetSOSConfigReq(Object obj, short s, boolean z, int i, String str, String str2) {
        super(obj, s);
        this.h = z;
        this.i = i;
        this.j = str;
        this.k = str2;
    }

    public final byte[] a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf(this.h ? (byte) 1 : (byte) 0));
        arrayList.add(Byte.valueOf((byte) this.i));
        byte[] bytes = this.j.getBytes(StandardCharsets.UTF_8);
        arrayList.add(Byte.valueOf((byte) bytes.length));
        for (byte b : bytes) {
            arrayList.add(Byte.valueOf(b));
        }
        byte[] bytes2 = this.k.getBytes(StandardCharsets.UTF_8);
        arrayList.add(Byte.valueOf((byte) bytes2.length));
        for (byte b2 : bytes2) {
            arrayList.add(Byte.valueOf(b2));
        }
        byte[] bArr = new byte[arrayList.size()];
        int i = -1;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            i++;
            bArr[i] = ((Byte) arrayList.get(i2)).byteValue();
        }
        return bArr;
    }

    @NonNull
    public final List<CommandBytes> b() {
        ArrayList<CommandBytes> generateSinglePacketRequest;
        byte[] a2 = a();
        byte[] bArr = {65};
        if (a().length + 4 > 150) {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateRequest((byte) 0, BleUUID.CMD_ID_B7, a2, bArr, false, false);
        } else {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 0, BleUUID.CMD_ID_B7, a2, bArr, false);
        }
        this.f7474a = new ExpectedDataSize(generateSinglePacketRequest.size());
        return generateSinglePacketRequest;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return b();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_SOS_CONFIG;
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
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }
}
