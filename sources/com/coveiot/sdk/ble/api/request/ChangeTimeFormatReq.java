package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class ChangeTimeFormatReq extends BaseRequest {
    public boolean f;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7480a;
        public Object b;

        public ChangeTimeFormatReq build() {
            ChangeTimeFormatReq changeTimeFormatReq = new ChangeTimeFormatReq(this.b);
            changeTimeFormatReq.f = this.f7480a;
            return changeTimeFormatReq;
        }

        public Builder setData(Object obj) {
            this.b = obj;
            return this;
        }

        public Builder setIs24HrFormat(boolean z) {
            this.f7480a = z;
            return this;
        }
    }

    public ChangeTimeFormatReq(Object obj) {
        super(obj);
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        CommandBytes commandBytes = new CommandBytes();
        if (this.f) {
            commandBytes.setCommandData(BleUUID.SET_DEVICE_TIME_24_HOUR_FORMAT);
        } else {
            commandBytes.setCommandData(BleUUID.SET_DEVICE_TIME_12_HOUR_FORMAT);
        }
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_TIME_FORMAT;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public boolean isIs24HrFormat() {
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
