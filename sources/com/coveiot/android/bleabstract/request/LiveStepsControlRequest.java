package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class LiveStepsControlRequest extends BleBaseRequest {
    public boolean f;

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3503a;

        public Builder(boolean z) {
            this.f3503a = false;
            this.f3503a = z;
        }

        public LiveStepsControlRequest build() {
            LiveStepsControlRequest liveStepsControlRequest = new LiveStepsControlRequest();
            liveStepsControlRequest.f = this.f3503a;
            return liveStepsControlRequest;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.GET_LIVE_STEPS_CONTROL;
    }

    public boolean isEnable() {
        return this.f;
    }
}
