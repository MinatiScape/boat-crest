package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.PPGTypes;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class LiveRawPPGControlRequest extends BleBaseRequest {
    public boolean f = false;
    public String g = PPGTypes.PPG.getValue();

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.RAW_PPG_CONTROL;
    }

    public String getmPPGType() {
        return this.g;
    }

    public boolean isEnabled() {
        return this.f;
    }

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3502a;
        public String b;

        public Builder(boolean z) {
            this.f3502a = false;
            this.b = PPGTypes.PPG.getValue();
            this.f3502a = z;
        }

        public LiveRawPPGControlRequest build() {
            LiveRawPPGControlRequest liveRawPPGControlRequest = new LiveRawPPGControlRequest();
            liveRawPPGControlRequest.f = this.f3502a;
            liveRawPPGControlRequest.g = this.b;
            return liveRawPPGControlRequest;
        }

        public Builder(boolean z, String str) {
            this.f3502a = false;
            this.b = PPGTypes.PPG.getValue();
            this.f3502a = z;
            this.b = str;
        }
    }
}
