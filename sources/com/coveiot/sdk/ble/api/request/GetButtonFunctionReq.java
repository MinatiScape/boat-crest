package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class GetButtonFunctionReq extends HybridCommandRequest {
    public int h;

    public GetButtonFunctionReq(Object obj, short s, int i) {
        super(obj, s);
        this.h = i;
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = {1, BleUUID.CMD_ID_AF, 6, 0, 64};
        byte[] bArr2 = {(byte) this.h};
        byte[] bArr3 = new byte[6];
        System.arraycopy(bArr, 0, bArr3, 0, 5);
        System.arraycopy(bArr2, 0, bArr3, 5, 1);
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
        return CommandNames.GET_BUTTON_FUNCTION;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getPosition() {
        return this.h;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return false;
    }

    public void setPosition(int i) {
        this.h = i;
    }
}
