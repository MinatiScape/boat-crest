package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.BleActivityConfigMetaData;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.utils.utility.AppUtils;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class ConfigureActivityListReq extends BaseRequest {
    public List<BleActivityConfigMetaData> f;
    public ArrayList<Byte> g;

    public ConfigureActivityListReq(Object obj, List<BleActivityConfigMetaData> list) {
        super(obj);
        this.g = new ArrayList<>();
        this.f = list;
        new ByteArrayOutputStream();
    }

    public final byte[] a() {
        if (AppUtils.isEmpty(this.g)) {
            for (BleActivityConfigMetaData bleActivityConfigMetaData : this.f) {
                for (int i = 0; i < bleActivityConfigMetaData.getDataBytes().length; i++) {
                    this.g.add(Byte.valueOf(bleActivityConfigMetaData.getDataBytes()[i]));
                }
            }
            byte[] bArr = new byte[this.g.size()];
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                bArr[i2] = this.g.get(i2).byteValue();
            }
            return bArr;
        }
        return new byte[0];
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return MultiPacketRequestGenerator.generateRequest((byte) 1, (byte) -95, a(), new byte[]{(byte) this.f.size()}, false, false);
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.CONFIGURE_ACTIVITIES;
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
