package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetWearingHandRequest extends BleBaseRequest {
    public boolean f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3555a;

        public Builder(boolean z) {
            this.f3555a = z;
        }

        public SetWearingHandRequest build() {
            SetWearingHandRequest setWearingHandRequest = new SetWearingHandRequest();
            setWearingHandRequest.f = this.f3555a;
            return setWearingHandRequest;
        }
    }

    public boolean isRightHand() {
        return this.f;
    }
}
