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
public class SportsNotificationReq extends BaseRequest {
    public List<BleDynamicSportsField> f;

    public SportsNotificationReq(Object obj, List<BleDynamicSportsField> list) {
        super(obj);
        this.f = list;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList<CommandBytes> generateSinglePacketRequest;
        byte[] bArr = {ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(this.f.size()).array()[0]};
        int i = 0;
        for (BleDynamicSportsField bleDynamicSportsField : this.f) {
            i += bleDynamicSportsField.getDataBytes().length;
        }
        byte[] bArr2 = new byte[i];
        int i2 = 0;
        for (int i3 = 0; i3 < this.f.size(); i3++) {
            this.f.get(i3).setDevicePlatformEnum(this.d);
            byte[] dataBytes = this.f.get(i3).getDataBytes();
            System.arraycopy(dataBytes, 0, bArr2, i2, dataBytes.length);
            i2 += dataBytes.length;
        }
        if (i + 4 > 150) {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateRequest((byte) 2, (byte) -110, bArr2, bArr, false, false);
        } else {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 2, (byte) -110, bArr2, bArr, false);
        }
        this.f7474a = new ExpectedDataSize(generateSinglePacketRequest.size());
        return generateSinglePacketRequest;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SPORTS_NOTIFICATION;
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

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldSendAllPacketsAtOnce() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldWaitForRes() {
        return true;
    }
}
