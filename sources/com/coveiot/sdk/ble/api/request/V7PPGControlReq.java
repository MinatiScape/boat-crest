package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class V7PPGControlReq extends BaseRequest {
    public boolean f;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7547a;
        public Object b;

        public V7PPGControlReq build() {
            V7PPGControlReq v7PPGControlReq = new V7PPGControlReq(this.b);
            v7PPGControlReq.f = this.f7547a;
            return v7PPGControlReq;
        }

        public Builder setData(Object obj) {
            this.b = obj;
            return this;
        }

        public Builder setEnabled(boolean z) {
            this.f7547a = z;
            return this;
        }
    }

    public V7PPGControlReq(Object obj) {
        super(obj);
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        CommandBytes commandBytes = new CommandBytes();
        if (this.f) {
            commandBytes.setCommandData(BleUUID.ENABLE_RAW_PPG_v7);
        } else {
            commandBytes.setCommandData(BleUUID.DISABLE_RAW_PPG_V7);
        }
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.ENABLE_RAW_PPG;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public boolean isEnabled() {
        return this.f;
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
