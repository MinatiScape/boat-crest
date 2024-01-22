package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetNavigationAliveTimerRequest extends BleBaseRequest {
    public int f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3537a;

        @NotNull
        public final SetNavigationAliveTimerRequest build() {
            return new SetNavigationAliveTimerRequest(this.f3537a);
        }

        public final int getSeconds() {
            return this.f3537a;
        }

        @NotNull
        public final Builder setNavigationAliveTimerRequest(int i) {
            this.f3537a = i;
            return this;
        }

        public final void setSeconds(int i) {
            this.f3537a = i;
        }
    }

    public SetNavigationAliveTimerRequest(int i) {
        this.f = i;
    }

    public final int getSeconds() {
        return this.f;
    }

    public final void setSeconds(int i) {
        this.f = i;
    }
}
