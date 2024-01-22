package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class LiveBpControlRequest extends BleBaseRequest {
    public boolean f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3500a;

        public Builder(boolean z) {
            this.f3500a = z;
        }

        @NotNull
        public final LiveBpControlRequest build() {
            LiveBpControlRequest liveBpControlRequest = new LiveBpControlRequest();
            liveBpControlRequest.setEnabled(this.f3500a);
            return liveBpControlRequest;
        }

        public final boolean isEnabled$bleabstract_release() {
            return this.f3500a;
        }

        public final void setEnabled$bleabstract_release(boolean z) {
            this.f3500a = z;
        }
    }

    public final boolean isEnabled() {
        return this.f;
    }

    public final void setEnabled(boolean z) {
        this.f = z;
    }
}
