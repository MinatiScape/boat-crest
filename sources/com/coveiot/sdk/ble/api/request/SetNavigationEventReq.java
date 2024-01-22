package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes9.dex */
public class SetNavigationEventReq extends HybridCommandRequest {
    public Boolean h;
    public String i;
    public String j;
    public int k;

    public SetNavigationEventReq(Object obj, short s, Boolean bool, String str, String str2, int i) {
        super(obj, s);
        this.h = bool;
        this.i = str;
        this.j = str2;
        this.k = i;
    }

    public final byte[] a() {
        ArrayList arrayList = new ArrayList();
        if (this.h.booleanValue()) {
            arrayList.add((byte) 1);
            byte[] bytes = this.i.getBytes(StandardCharsets.UTF_16LE);
            if (bytes.length >= 120) {
                bytes = Arrays.copyOfRange(bytes, 0, 120);
            }
            arrayList.add(Byte.valueOf((byte) bytes.length));
            for (byte b : bytes) {
                arrayList.add(Byte.valueOf(b));
            }
            byte[] bytes2 = this.j.getBytes(StandardCharsets.UTF_16LE);
            if (bytes2.length >= 120) {
                bytes2 = Arrays.copyOfRange(bytes2, 0, 120);
            }
            arrayList.add(Byte.valueOf((byte) bytes2.length));
            for (byte b2 : bytes2) {
                arrayList.add(Byte.valueOf(b2));
            }
            arrayList.add(Byte.valueOf((byte) this.k));
        } else {
            arrayList.add((byte) 2);
        }
        byte[] bArr = new byte[arrayList.size()];
        int i = -1;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            i++;
            bArr[i] = ((Byte) arrayList.get(i2)).byteValue();
        }
        return bArr;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList<CommandBytes> generateSinglePacketRequest;
        byte[] a2 = a();
        byte[] bArr = {65};
        if (a().length + 4 > 150) {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateRequest((byte) 2, (byte) -90, a2, bArr, false, false);
        } else {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 2, (byte) -90, a2, bArr, false);
        }
        this.f7474a = new ExpectedDataSize(generateSinglePacketRequest.size());
        return generateSinglePacketRequest;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_NAVIGATION_EVENT;
    }

    public String getDestination() {
        return this.j;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public Boolean getIsStart() {
        return this.h;
    }

    public int getMode() {
        return this.k;
    }

    public String getSource() {
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

    public void setDestination(String str) {
        this.j = str;
    }

    public void setIsStart(Boolean bool) {
        this.h = bool;
    }

    public void setMode(int i) {
        this.k = i;
    }

    public void setSource(String str) {
        this.i = str;
    }

    public SetNavigationEventReq(Object obj, short s, Boolean bool) {
        super(obj, s);
        this.h = bool;
    }
}
