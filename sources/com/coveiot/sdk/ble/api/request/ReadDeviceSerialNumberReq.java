package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.List;
/* loaded from: classes9.dex */
public class ReadDeviceSerialNumberReq extends BaseRequest {

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7517a;

        public static Builder aReadDeviceReq() {
            return new Builder();
        }

        public ReadDeviceSerialNumberReq build() {
            return new ReadDeviceSerialNumberReq(this.f7517a);
        }

        public Builder setId(Object obj) {
            this.f7517a = obj;
            return this;
        }
    }

    public ReadDeviceSerialNumberReq(Object obj) {
        super(obj);
    }

    @Nullable
    public final String a() {
        return BleUUID.SERIAL_NUMBER_CHAR_UUID;
    }

    @NonNull
    public final String b() {
        return BleUUID.DEVICE_INFO_SERVICE_UUID;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return b();
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