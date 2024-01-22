package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class ChangeDistanceUnitReq extends BaseRequest {
    public boolean f;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7478a;
        public Object b;

        public ChangeDistanceUnitReq build() {
            ChangeDistanceUnitReq changeDistanceUnitReq = new ChangeDistanceUnitReq(this.b);
            changeDistanceUnitReq.f = this.f7478a;
            return changeDistanceUnitReq;
        }

        public Builder setData(Object obj) {
            this.b = obj;
            return this;
        }

        public Builder setInMiles(boolean z) {
            this.f7478a = z;
            return this;
        }
    }

    public ChangeDistanceUnitReq(Object obj) {
        super(obj);
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        CommandBytes commandBytes = new CommandBytes();
        if (this.f) {
            commandBytes.setCommandData(BleUUID.SET_DISTANCE_UNIT_MILES);
        } else {
            commandBytes.setCommandData(BleUUID.SET_DISTANCE_UNIT_KM);
        }
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_DISTANCE_UNIT;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public boolean isInMiles() {
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
