package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class LiveTemperatureControlRequest extends BleBaseRequest {
    public boolean f = false;

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3504a;

        public Builder(boolean z) {
            this.f3504a = false;
            this.f3504a = z;
        }

        public LiveTemperatureControlRequest build() {
            LiveTemperatureControlRequest liveTemperatureControlRequest = new LiveTemperatureControlRequest();
            liveTemperatureControlRequest.f = this.f3504a;
            return liveTemperatureControlRequest;
        }
    }

    public boolean isEnabled() {
        return this.f;
    }
}
