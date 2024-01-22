package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.BleDynamicSportsField;
import com.coveiot.sdk.ble.api.model.CommonAppContentFeatureType;
import com.coveiot.sdk.ble.api.model.DisplayPosition;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetSmartAlertApplicationContentReq extends BaseRequest {
    public int f;
    public int g;
    public int h;
    public List<BleDynamicSportsField> i;

    public SetSmartAlertApplicationContentReq(Object obj, int i, int i2, List<BleDynamicSportsField> list) {
        super(obj);
        this.f = CommonAppContentFeatureType.SMART_ALERT.ordinal();
        this.h = DisplayPosition.APP_SCREEN.ordinal();
        this.g = i;
        this.h = i2;
        this.i = list;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList<CommandBytes> arrayList = new ArrayList<>();
        List<BleDynamicSportsField> list = this.i;
        if (list != null) {
            byte[] bArr = {(byte) this.f, (byte) this.g, (byte) this.h, (byte) list.size()};
            int i = 0;
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                i += this.i.get(i2).getDataBytes().length;
            }
            byte[] bArr2 = new byte[i];
            int i3 = 0;
            for (int i4 = 0; i4 < this.i.size(); i4++) {
                this.i.get(i4).setDevicePlatformEnum(this.d);
                byte[] dataBytes = this.i.get(i4).getDataBytes();
                System.arraycopy(dataBytes, 0, bArr2, i3, dataBytes.length);
                i3 += dataBytes.length;
            }
            if (i + 4 + 4 > 150) {
                arrayList = MultiPacketRequestGenerator.generateRequest((byte) 2, (byte) -88, bArr2, bArr, false, false);
            } else {
                arrayList = MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 2, (byte) -88, bArr2, bArr, false);
            }
            this.f7474a = new ExpectedDataSize(arrayList.size());
        }
        return arrayList;
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
