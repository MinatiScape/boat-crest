package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class GetNearbyDeviceListRequest extends BleBaseRequest {
    public boolean f;
    public long g;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f3495a;
        public boolean b;
        public long c;

        public static Builder Builder() {
            return new Builder();
        }

        public GetNearbyDeviceListRequest build() {
            GetNearbyDeviceListRequest getNearbyDeviceListRequest = new GetNearbyDeviceListRequest();
            getNearbyDeviceListRequest.f = this.b;
            getNearbyDeviceListRequest.g = this.c;
            return getNearbyDeviceListRequest;
        }

        public void setId(Object obj) {
            this.f3495a = obj;
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

    public long getTimeOut() {
        return this.g;
    }

    public boolean isDeviceName() {
        return this.f;
    }
}
