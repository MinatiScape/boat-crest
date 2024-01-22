package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.model.BleVibrationModel;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetVibrationReq extends BaseRequest {
    public List<BleVibrationModel> f;

    public SetVibrationReq(Object obj, List<BleVibrationModel> list) {
        super(obj);
        this.f = list;
    }

    public final byte[] a() {
        byte[] bArr = new byte[this.f.size() * 3];
        int i = -1;
        for (BleVibrationModel bleVibrationModel : this.f) {
            int i2 = i + 1;
            bArr[i2] = (byte) (bleVibrationModel.getType() & 255);
            int i3 = i2 + 1;
            bArr[i3] = (byte) (bleVibrationModel.getStrength() & 255);
            i = i3 + 1;
            bArr[i] = (byte) (bleVibrationModel.getDuration() & 255);
        }
        return bArr;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        CommandBytes commandBytes = new CommandBytes();
        byte[] a2 = a();
        int i = 5;
        int length = a2.length + 5;
        byte[] bArr = new byte[length];
        bArr[0] = 4;
        bArr[1] = -127;
        bArr[2] = (byte) length;
        bArr[3] = 0;
        bArr[4] = (byte) this.f.size();
        for (byte b : a2) {
            bArr[i] = b;
            i++;
        }
        commandBytes.setCommandData(bArr);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_VIBRATION;
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
