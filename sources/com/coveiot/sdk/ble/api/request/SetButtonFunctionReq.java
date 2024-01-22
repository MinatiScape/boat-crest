package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetButtonFunctionReq extends HybridCommandRequest {
    public int h;
    public int i;
    public int j;
    public int k;

    public SetButtonFunctionReq(Object obj, short s, int i, int i2, int i3, int i4) {
        super(obj, s);
        this.k = i4;
        this.h = i;
        this.j = i3;
        this.i = i2;
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = {1, BleUUID.CMD_ID_AF, 9, 0, 65};
        byte[] bArr2 = {(byte) this.h};
        byte[] bArr3 = {(byte) this.i};
        byte[] bArr4 = {(byte) this.j};
        byte[] bArr5 = {(byte) this.k};
        byte[] bArr6 = new byte[9];
        System.arraycopy(bArr, 0, bArr6, 0, 5);
        System.arraycopy(bArr2, 0, bArr6, 5, 1);
        System.arraycopy(bArr3, 0, bArr6, 6, 1);
        System.arraycopy(bArr4, 0, bArr6, 7, 1);
        System.arraycopy(bArr5, 0, bArr6, 8, 1);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr6);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_BUTTON_FUNCTION;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getLongPress() {
        return this.k;
    }

    public int getPosition() {
        return this.i;
    }

    public int getShortPress() {
        return this.j;
    }

    public int getVersionNumber() {
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

    public void setLongPress(int i) {
        this.k = i;
    }

    public void setPosition(int i) {
        this.i = i;
    }

    public void setShortPress(int i) {
        this.j = i;
    }

    public void setVersionNumber(int i) {
        this.h = i;
    }
}
