package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SendUnbindBTCallReq extends HybridCommandRequest {
    public SendUnbindBTCallReq(Object obj, short s) {
        super(obj, s);
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SEND_UNBIND_BT_CALL;
        ArrayList arrayList2 = new ArrayList();
        for (byte b : bArr) {
            arrayList2.add(Byte.valueOf(b));
        }
        arrayList2.addAll(super.getPayload());
        byte[] bArr2 = new byte[arrayList2.size()];
        for (int i = 0; i < arrayList2.size(); i++) {
            bArr2[i] = ((Byte) arrayList2.get(i)).byteValue();
        }
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr2);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SEND_UNBIND_BT_CALL;
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
