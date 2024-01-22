package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetNavigationConfigurationReq extends HybridCommandRequest {
    public boolean h;
    public boolean i;
    public boolean j;

    public SetNavigationConfigurationReq(Object obj, short s, boolean z, boolean z2, boolean z3) {
        super(obj, s);
        this.h = z;
        this.i = z2;
        this.j = z3;
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = {0, BleUUID.CMD_ID_B5, 8, 0, 65};
        byte[] bArr2 = {isAudioEnabled() ? (byte) 1 : (byte) 0};
        byte[] bArr3 = {isHapticEnabled() ? (byte) 1 : (byte) 0};
        byte[] bArr4 = {isAODEnabled() ? (byte) 1 : (byte) 0};
        byte[] bArr5 = new byte[8];
        System.arraycopy(bArr, 0, bArr5, 0, 5);
        System.arraycopy(bArr2, 0, bArr5, 5, 1);
        System.arraycopy(bArr3, 0, bArr5, 6, 1);
        System.arraycopy(bArr4, 0, bArr5, 7, 1);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr5);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_NAVIGATION_CONFIGURATION;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public boolean isAODEnabled() {
        return this.j;
    }

    public boolean isAudioEnabled() {
        return this.h;
    }

    public boolean isHapticEnabled() {
        return this.i;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    public void setAODEnabled(boolean z) {
        this.j = z;
    }

    public void setAudioEnabled(boolean z) {
        this.h = z;
    }

    public void setHapticEnabled(boolean z) {
        this.i = z;
    }
}
