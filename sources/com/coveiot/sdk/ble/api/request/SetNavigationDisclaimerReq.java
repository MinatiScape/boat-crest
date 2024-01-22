package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes9.dex */
public class SetNavigationDisclaimerReq extends HybridCommandRequest {
    public String h;
    public String i;

    public SetNavigationDisclaimerReq(Object obj, short s, String str, String str2) {
        super(obj, s);
        this.h = str;
        this.i = str2;
    }

    public final byte[] a() {
        ArrayList arrayList = new ArrayList();
        byte[] bytes = (this.h + "\u0000").getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            arrayList.add(Byte.valueOf(bytes[i]));
        }
        byte[] bytes2 = this.i.getBytes(StandardCharsets.UTF_8);
        int length = bytes2.length;
        byte[] bArr = {(byte) length, (byte) (length >> 8)};
        arrayList.add(Byte.valueOf(bArr[0]));
        arrayList.add(Byte.valueOf(bArr[1]));
        if (bytes2.length >= 1000) {
            bytes2 = Arrays.copyOfRange(bytes2, 0, 1000);
        }
        for (byte b : bytes2) {
            arrayList.add(Byte.valueOf(b));
        }
        byte[] bArr2 = new byte[arrayList.size()];
        int i2 = -1;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            i2++;
            bArr2[i2] = ((Byte) arrayList.get(i3)).byteValue();
        }
        return bArr2;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList<CommandBytes> generateSinglePacketRequest;
        byte[] a2 = a();
        byte[] bArr = {65};
        if (a().length + 4 > 150) {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateRequest((byte) 0, (byte) -78, a2, bArr, false, false);
        } else {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 0, (byte) -78, a2, bArr, false);
        }
        this.f7474a = new ExpectedDataSize(generateSinglePacketRequest.size());
        return generateSinglePacketRequest;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_NAVIGATION_DISCLAIMER;
    }

    public String getDisclaimerText() {
        return this.i;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public String getVersionText() {
        return this.h;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    public void setDisclaimerText(String str) {
        this.i = str;
    }

    public void setVersionText(String str) {
        this.h = str;
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
