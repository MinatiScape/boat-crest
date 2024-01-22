package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetSensAIActivityConfigRequest extends BleBaseRequest {
    public final boolean f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3548a;

        @NotNull
        public final SetSensAIActivityConfigRequest build() {
            return new SetSensAIActivityConfigRequest(this.f3548a);
        }

        public final boolean isActivityConfigControl() {
            return this.f3548a;
        }

        public final void setActivityConfigControl(boolean z) {
            this.f3548a = z;
        }

        @NotNull
        public final Builder setSensAiActivityConfigRequest(boolean z) {
            this.f3548a = z;
            return this;
        }
    }

    public SetSensAIActivityConfigRequest(boolean z) {
        this.f = z;
    }

    public final boolean isActivityConfigControl() {
        return this.f;
    }
}
