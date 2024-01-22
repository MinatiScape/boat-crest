package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ProbeDataRequest extends BleBaseRequest {
    public int f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3507a;

        @NotNull
        public final ProbeDataRequest build() {
            ProbeDataRequest probeDataRequest = new ProbeDataRequest();
            probeDataRequest.setInterval(this.f3507a);
            return probeDataRequest;
        }

        public final int getInterval$bleabstract_release() {
            return this.f3507a;
        }

        @NotNull
        public final Builder setInterval(int i) {
            this.f3507a = i;
            return this;
        }

        public final void setInterval$bleabstract_release(int i) {
            this.f3507a = i;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.SET_PROBE;
    }

    public final int getInterval() {
        return this.f;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public void setBleCommand(@Nullable BleCommand bleCommand) {
        super.setBleCommand(bleCommand);
    }

    public final void setInterval(int i) {
        this.f = i;
    }
}
