package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetDistanceUnitRequest extends BleBaseRequest {
    public boolean f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3528a;

        public Builder(boolean z) {
            this.f3528a = z;
        }

        public SetDistanceUnitRequest build() {
            SetDistanceUnitRequest setDistanceUnitRequest = new SetDistanceUnitRequest();
            setDistanceUnitRequest.f = this.f3528a;
            return setDistanceUnitRequest;
        }
    }

    public boolean isDistanceUnitinMile() {
        return this.f;
    }
}
