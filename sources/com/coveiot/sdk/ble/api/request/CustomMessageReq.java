package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.BleDynamicSportsField;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class CustomMessageReq extends BaseRequest {
    public List<BleDynamicSportsField> f;
    public short g;
    public short h;
    public short i;
    public short j;
    public short k;

    public CustomMessageReq(Object obj, List<BleDynamicSportsField> list, short s, short s2, short s3, short s4, short s5) {
        super(obj);
        this.f = list;
        this.g = s;
        this.h = s2;
        this.i = s3;
        this.j = s4;
        this.k = s5;
    }

    public short displayTime() {
        return this.i;
    }

    public short enterDirection() {
        return this.j;
    }

    public short exitDirection() {
        return this.k;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList<CommandBytes> generateSinglePacketRequest;
        byte[] bArr = {(byte) (this.g & 255), (byte) (this.h & 255), (byte) (this.i & 255), (byte) (this.j & 255), (byte) (this.k & 255)};
        byte[] bArr2 = {bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(this.f.size()).array()[0]};
        int i = 0;
        for (BleDynamicSportsField bleDynamicSportsField : this.f) {
            bleDynamicSportsField.setDevicePlatformEnum(this.d);
            i += bleDynamicSportsField.getDataBytes().length;
        }
        byte[] bArr3 = new byte[i];
        int i2 = 0;
        for (int i3 = 0; i3 < this.f.size(); i3++) {
            byte[] dataBytes = this.f.get(i3).getDataBytes();
            System.arraycopy(dataBytes, 0, bArr3, i2, dataBytes.length);
            i2 += dataBytes.length;
        }
        if (i + 4 > 150) {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateRequest((byte) 2, (byte) -103, bArr3, bArr2, false, false);
        } else {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 2, (byte) -103, bArr3, bArr2, false);
        }
        this.f7474a = new ExpectedDataSize(generateSinglePacketRequest.size());
        return generateSinglePacketRequest;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.CUSTOM_MESSAGE;
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

    public short messageType() {
        return this.g;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldSendAllPacketsAtOnce() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldWaitForRes() {
        return true;
    }

    public short vibrationDuration() {
        return this.h;
    }
}
