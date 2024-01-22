package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetNavigationStatusRequest extends BleBaseRequest {
    public int f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3542a;

        @NotNull
        public final SetNavigationStatusRequest build() {
            return new SetNavigationStatusRequest(this.f3542a);
        }

        public final int getStatus() {
            return this.f3542a;
        }

        @NotNull
        public final Builder setNavigationStatusRequest(int i) {
            this.f3542a = i;
            return this;
        }

        public final void setStatus(int i) {
            this.f3542a = i;
        }
    }

    public SetNavigationStatusRequest(int i) {
        this.f = i;
    }

    public final int getStatus() {
        return this.f;
    }

    public final void setStatus(int i) {
        this.f = i;
    }
}
