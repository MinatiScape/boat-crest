package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class GetNearbyDeviceListReq extends BaseRequest {
    public boolean f;
    public long g;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7491a;
        public boolean b;
        public long c;

        public static Builder Builder() {
            return new Builder();
        }

        public GetNearbyDeviceListReq build() {
            GetNearbyDeviceListReq getNearbyDeviceListReq = new GetNearbyDeviceListReq(this.f7491a);
            getNearbyDeviceListReq.f = this.b;
            getNearbyDeviceListReq.g = this.c;
            return getNearbyDeviceListReq;
        }

        public void setId(Object obj) {
            this.f7491a = obj;
        }

        public Builder setIsDeviceName(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setTimeOut(long j) {
            this.c = j;
            return this;
        }
    }

    public GetNearbyDeviceListReq(Object obj) {
        super(obj);
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(BleUUID.GET_NEARBY_DEVICE_LIST);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        if (this.f) {
            return CommandNames.GET_NEARBY_DEVICE_LIST;
        }
        return CommandNames.GET_NEARBY_DEVICE_LIST_MAC;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public long getTimeOut() {
        return this.g;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return false;
    }
}
