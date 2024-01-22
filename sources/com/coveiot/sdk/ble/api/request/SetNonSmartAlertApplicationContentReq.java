package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.CommonAppContentFeatureType;
import com.coveiot.sdk.ble.api.model.DisplayPosition;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes9.dex */
public class SetNonSmartAlertApplicationContentReq extends BaseRequest {
    public int f;
    public int g;
    public int h;
    public String i;
    public String j;

    public SetNonSmartAlertApplicationContentReq(Object obj, int i, int i2, String str, String str2) {
        super(obj);
        this.f = CommonAppContentFeatureType.NON_SMART_ALERT.ordinal();
        this.h = DisplayPosition.APP_SCREEN.ordinal();
        this.g = i;
        this.h = i2;
        this.i = str;
        this.j = str2;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList<CommandBytes> generateSinglePacketRequest;
        byte[] bArr = {(byte) this.f, (byte) this.g, (byte) this.h};
        byte[] bytes = (this.i + "\u0000").getBytes(StandardCharsets.UTF_8);
        byte[] bytes2 = (this.j + "\u0000").getBytes(StandardCharsets.UTF_8);
        if (bytes.length >= 80) {
            bytes = Arrays.copyOfRange(bytes, 0, 80);
        }
        if (bytes2.length >= 80) {
            bytes2 = Arrays.copyOfRange(bytes2, 0, 80);
        }
        int length = bytes.length + 1 + bytes2.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
        bArr2[bytes.length] = -1;
        System.arraycopy(bytes2, 0, bArr2, bytes.length + 1, bytes2.length);
        if (3 + length + 4 > 150) {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateRequest((byte) 2, (byte) -88, bArr2, bArr, false, false);
        } else {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 2, (byte) -88, bArr2, bArr, false);
        }
        this.f7474a = new ExpectedDataSize(generateSinglePacketRequest.size());
        return generateSinglePacketRequest;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_COMMON_APPLICATION_CONTENT;
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
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
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
