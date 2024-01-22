package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetDeviceParametersRequest extends BleBaseRequest {
    public boolean f = true;
    public boolean g = true;
    public boolean h = true;
    public boolean i = true;
    public boolean j = true;
    public boolean k = true;
    public int l = 1;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3525a = true;
        public boolean b = true;
        public boolean c = true;
        public boolean d = true;
        public boolean e = true;
        public int f = 1;
        public boolean g = true;

        public SetDeviceParametersRequest build() {
            SetDeviceParametersRequest setDeviceParametersRequest = new SetDeviceParametersRequest();
            setDeviceParametersRequest.f = this.f3525a;
            setDeviceParametersRequest.g = this.b;
            setDeviceParametersRequest.h = this.c;
            setDeviceParametersRequest.i = this.d;
            setDeviceParametersRequest.j = this.e;
            setDeviceParametersRequest.l = this.f;
            setDeviceParametersRequest.k = this.g;
            return setDeviceParametersRequest;
        }

        public Builder setBrightnessLevel(int i) {
            this.f = i;
            return this;
        }

        public Builder setDisplayState(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setDistanceUnit(boolean z) {
            this.c = z;
            return this;
        }

        public Builder setHandState(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setHourFormat(boolean z) {
            this.f3525a = z;
            return this;
        }

        public Builder setLiftWristEnable(boolean z) {
            this.e = z;
            return this;
        }

        public Builder setTemperatureUnitInCelsius(boolean z) {
            this.g = z;
            return this;
        }
    }

    public int brightnessLevel() {
        return this.l;
    }

    public boolean is12HourFormat() {
        return this.f;
    }

    public boolean isDistanceUnitinMile() {
        return this.h;
    }

    public boolean isHorizontalDisplay() {
        return this.i;
    }

    public boolean isLeftHand() {
        return this.g;
    }

    public boolean isLiftWristEnabled() {
        return this.j;
    }

    public boolean isTemperatureUnitInCelsius() {
        return this.k;
    }
}
