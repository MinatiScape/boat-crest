package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.BleSmartAlertAppData;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetSmartAlertConfigReq extends HybridCommandRequest {
    public boolean h;
    public List<BleSmartAlertAppData> i;

    public SetSmartAlertConfigReq(Object obj, short s, boolean z, List<BleSmartAlertAppData> list) {
        super(obj, s);
        this.h = z;
        this.i = list;
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList<CommandBytes> generateSinglePacketRequest;
        new ArrayList();
        ArrayList arrayList = new ArrayList();
        if (this.h) {
            arrayList.add((byte) 1);
            arrayList.add(Byte.valueOf((byte) this.i.size()));
            for (int i = 0; i < this.i.size(); i++) {
                arrayList.add(Byte.valueOf((byte) this.i.get(i).getId()));
                byte[] bytes = this.i.get(i).getName().getBytes(StandardCharsets.UTF_16LE);
                arrayList.add(Byte.valueOf((byte) bytes.length));
                for (int i2 = 0; i2 < bytes.length && i2 < 60; i2++) {
                    arrayList.add(Byte.valueOf(bytes[i2]));
                }
                arrayList.add(Byte.valueOf((byte) this.i.get(i).getLogoSize()));
                byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(this.i.get(i).getLogoInfo()).array();
                arrayList.add(Byte.valueOf(array[1]));
                arrayList.add(Byte.valueOf(array[0]));
            }
        } else {
            arrayList.add((byte) 0);
        }
        setPayload(arrayList);
        List<Byte> payload = super.getPayload();
        int size = payload.size();
        byte[] bArr = new byte[size];
        for (int i3 = 0; i3 < payload.size(); i3++) {
            bArr[i3] = payload.get(i3).byteValue();
        }
        if (size + 4 > 150) {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateRequest((byte) 2, (byte) -89, bArr, new byte[0], false, false);
        } else {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 2, (byte) -89, bArr, new byte[0], false);
        }
        this.f7474a = new ExpectedDataSize(generateSinglePacketRequest.size());
        return generateSinglePacketRequest;
    }

    public List<BleSmartAlertAppData> getBleSmartAlertAppDataList() {
        return this.i;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_SMART_ALERT_CONFIG;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public boolean isEnabled() {
        return this.h;
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
