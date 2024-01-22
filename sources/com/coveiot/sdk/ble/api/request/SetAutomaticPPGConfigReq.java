package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetAutomaticPPGConfigReq extends BaseRequest {
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;

    public SetAutomaticPPGConfigReq(Object obj, int i, int i2, int i3, int i4, int i5) {
        super(obj);
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = i4;
        this.j = i5;
        this.f = i;
        this.g = i2;
        this.h = i3;
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SET_AUTOMATIC_PPG_CONFIG;
        byte[] bArr2 = {(byte) this.f};
        ByteBuffer allocate = ByteBuffer.allocate(4);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byte[] array = allocate.order(byteOrder).putInt(this.g).array();
        byte[] bArr3 = {array[0], array[1]};
        byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.h).array();
        byte[] bArr4 = {array2[0], array2[1]};
        byte[] bArr5 = {(byte) this.i};
        byte[] array3 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.j).array();
        byte[] bArr6 = {array3[0], array3[1]};
        byte[] bArr7 = new byte[bArr.length + 1 + 2 + 2 + 1 + 2];
        System.arraycopy(bArr, 0, bArr7, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr7, bArr.length, 1);
        System.arraycopy(bArr3, 0, bArr7, bArr.length + 1, 2);
        System.arraycopy(bArr4, 0, bArr7, bArr.length + 1 + 2, 2);
        System.arraycopy(bArr5, 0, bArr7, bArr.length + 1 + 2 + 2, 1);
        System.arraycopy(bArr6, 0, bArr7, bArr.length + 1 + 2 + 2 + 1, 2);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr7);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_AUTOMATIC_PPG_CONFIG;
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
