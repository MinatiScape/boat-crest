package com.coveiot.android.bleabstract.request;

import com.coveiot.sdk.ble.api.request.SetWalkTargetReq;
/* loaded from: classes2.dex */
public class StepsTargetRequest extends BleBaseRequest {
    public int f = 10000;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3565a = 10000;

        public static SetWalkTargetReq.Builder aSetWalkTargetReq() {
            return new SetWalkTargetReq.Builder();
        }

        public StepsTargetRequest build() {
            StepsTargetRequest stepsTargetRequest = new StepsTargetRequest();
            stepsTargetRequest.f = this.f3565a;
            return stepsTargetRequest;
        }

        public Builder setId() {
            return this;
        }

        public Builder setTarget(int i) {
            this.f3565a = i;
            return this;
        }
    }

    public int getTarget() {
        return this.f;
    }
}
