package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.List;
/* loaded from: classes9.dex */
public class ScanDeviceReq extends BaseRequest {
    public String f;
    public long g;
    public boolean h;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f7529a;
        public long b;
        public Object c;
        public boolean d;

        public ScanDeviceReq build() {
            ScanDeviceReq scanDeviceReq = new ScanDeviceReq(this.c);
            scanDeviceReq.g = this.b;
            scanDeviceReq.h = this.d;
            scanDeviceReq.f = this.f7529a;
            return scanDeviceReq;
        }

        public Builder setId(Object obj) {
            this.c = obj;
            return this;
        }

        public Builder setScanDuration(long j) {
            this.b = j;
            return this;
        }

        public Builder setScanFilter(String str) {
            this.f7529a = str;
            return this;
        }

        public Builder setShouldProvideBatchResult(boolean z) {
            this.d = z;
            return this;
        }
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
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public long getScanDuration() {
        return this.g;
    }

    public String getScanFilter() {
        return this.f;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return false;
    }

    public boolean isShouldProvideBatchResult() {
        return this.h;
    }

    public ScanDeviceReq(Object obj) {
        super(obj);
        this.g = 10000L;
    }
}
