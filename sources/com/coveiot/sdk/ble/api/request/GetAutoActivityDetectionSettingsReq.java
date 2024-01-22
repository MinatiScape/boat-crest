package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class GetAutoActivityDetectionSettingsReq extends BaseRequest {
    public GetAutoActivityDetectionSettingsReq(Object obj) {
        super(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.GET_AUTO_ACTIVITY_DETECTION_SETTINGS;
        ArrayList arrayList2 = new ArrayList();
        for (byte b : bArr) {
            arrayList2.add(Byte.valueOf(b));
        }
        arrayList2.add((byte) 64);
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
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.GET_AUTO_ACTIVITY_DETECTION_SETTINGS;
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
        return false;
    }
}
