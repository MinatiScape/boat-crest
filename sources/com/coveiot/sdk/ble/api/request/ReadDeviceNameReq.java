package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.List;
/* loaded from: classes9.dex */
public class ReadDeviceNameReq extends BaseRequest {

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7515a;

        public static Builder aReadDeviceReq() {
            return new Builder();
        }

        public ReadDeviceNameReq build() {
            return new ReadDeviceNameReq(this.f7515a);
        }

        public Builder setId(Object obj) {
            this.f7515a = obj;
            return this;
        }
    }

    public ReadDeviceNameReq(Object obj) {
        super(obj);
    }

    @Nullable
    public final String a() {
        return BleUUID.DEVICE_NAME_CHAR_UUID;
    }

    @NonNull
    public final String b() {
        return BleUUID.GENERIC_INFO_SERVICE_UUID;
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
