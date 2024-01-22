package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class Spo2DataRequest extends BleBaseRequest {
    public boolean f;
    public boolean g;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3560a;
        public boolean b;

        @NotNull
        public final Spo2DataRequest build() {
            Spo2DataRequest spo2DataRequest = new Spo2DataRequest();
            spo2DataRequest.f = this.f3560a;
            spo2DataRequest.g = this.b;
            return spo2DataRequest;
        }

        public final boolean isEnabled$bleabstract_release() {
            return this.f3560a;
        }

        public final boolean isWaveEnabled$bleabstract_release() {
            return this.b;
        }

        @NotNull
        public final Builder setEnabled(boolean z) {
            this.f3560a = z;
            return this;
        }

        public final void setEnabled$bleabstract_release(boolean z) {
            this.f3560a = z;
        }

        @NotNull
        public final Builder setWaveEnabled(boolean z) {
            this.b = z;
            return this;
        }

        public final void setWaveEnabled$bleabstract_release(boolean z) {
            this.b = z;
        }
    }

    public final boolean isEnabled() {
        return this.f;
    }

    public final boolean isWaveEnabled() {
        return this.g;
    }
}
