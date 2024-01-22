package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class BTCallControlReq extends HybridCommandRequest {
    public short h;
    public boolean i;
    public short j;

    public BTCallControlReq(Object obj, short s, boolean z, boolean z2, short s2) {
        super(obj, s);
        this.h = (short) 0;
        this.i = z2;
        this.j = s2;
        this.h = (short) (b(z) | a(this.i));
    }

    public static short a(boolean z) {
        return z ? (short) 1 : (short) 0;
    }

    public static short b(boolean z) {
        return z ? (short) 128 : (short) 0;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.BT_CALL_CONTROL;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Byte.valueOf((byte) this.h));
        arrayList2.add(Byte.valueOf((byte) this.j));
        setPayload(arrayList2);
        List<Byte> payload = super.getPayload();
        ArrayList arrayList3 = new ArrayList();
        for (byte b : bArr) {
            arrayList3.add(Byte.valueOf(b));
        }
        arrayList3.addAll(payload);
        byte[] bArr2 = new byte[arrayList3.size()];
        for (int i = 0; i < arrayList3.size(); i++) {
            bArr2[i] = ((Byte) arrayList3.get(i)).byteValue();
        }
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr2);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.BT_CALL_CONTROL;
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
