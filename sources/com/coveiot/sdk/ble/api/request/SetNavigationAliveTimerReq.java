package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetNavigationAliveTimerReq extends BaseRequest {
    public int f;

    public SetNavigationAliveTimerReq(Object obj, int i) {
        super(obj);
        this.f = i;
    }

    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = {0, BleUUID.CMD_ID_B3, 5, 0};
        byte[] bArr2 = {(byte) this.f};
        byte[] bArr3 = new byte[5];
        System.arraycopy(bArr, 0, bArr3, 0, 4);
        System.arraycopy(bArr2, 0, bArr3, 4, 1);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr3);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_NAVIGATION_ALIVE_TIMER;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getSeconds() {
        return this.f;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    public void setSeconds(int i) {
        this.f = i;
    }
}
