package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class ChangeTemperatureUnitReq extends BaseRequest {
    public boolean f;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7479a;
        public Object b;

        public ChangeTemperatureUnitReq build() {
            ChangeTemperatureUnitReq changeTemperatureUnitReq = new ChangeTemperatureUnitReq(this.b);
            changeTemperatureUnitReq.f = this.f7479a;
            return changeTemperatureUnitReq;
        }

        public Builder setData(Object obj) {
            this.b = obj;
            return this;
        }

        public Builder setFahrenheit(boolean z) {
            this.f7479a = z;
            return this;
        }
    }

    public ChangeTemperatureUnitReq(Object obj) {
        super(obj);
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        CommandBytes commandBytes = new CommandBytes();
        if (this.f) {
            commandBytes.setCommandData(BleUUID.SET_TEMPERATURE_UNIT_FAHRENHEIT);
        } else {
            commandBytes.setCommandData(BleUUID.SET_TEMPERATURE_UNIT_CELSIUS);
        }
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_TEMPERATURE_UNIT;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public boolean isFahrenheit() {
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
