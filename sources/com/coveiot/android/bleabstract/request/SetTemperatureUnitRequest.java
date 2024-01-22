package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetTemperatureUnitRequest extends BleBaseRequest {
    public boolean f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3551a;

        public Builder(boolean z) {
            this.f3551a = z;
        }

        public SetTemperatureUnitRequest build() {
            SetTemperatureUnitRequest setTemperatureUnitRequest = new SetTemperatureUnitRequest();
            setTemperatureUnitRequest.f = this.f3551a;
            return setTemperatureUnitRequest;
        }
    }

    public boolean isTemperatureInCelsius() {
        return this.f;
    }
}
