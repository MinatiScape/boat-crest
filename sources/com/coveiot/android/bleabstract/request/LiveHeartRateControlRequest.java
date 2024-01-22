package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class LiveHeartRateControlRequest extends BleBaseRequest {
    public boolean f = false;

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3501a;

        public Builder(boolean z) {
            this.f3501a = false;
            this.f3501a = z;
        }

        public LiveHeartRateControlRequest build() {
            LiveHeartRateControlRequest liveHeartRateControlRequest = new LiveHeartRateControlRequest();
            liveHeartRateControlRequest.f = this.f3501a;
            return liveHeartRateControlRequest;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.HEART_RATE_MEASUREMENT_CONTROL;
    }

    public boolean isEnabled() {
        return this.f;
    }
}
