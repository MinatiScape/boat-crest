package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class EcgDataRequest extends BleBaseRequest {
    public int f = 4;
    public boolean g;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3488a = 4;
        public boolean b;

        @NotNull
        public final EcgDataRequest build() {
            EcgDataRequest ecgDataRequest = new EcgDataRequest();
            ecgDataRequest.setLevel(this.f3488a);
            ecgDataRequest.g = this.b;
            return ecgDataRequest;
        }

        public final int getLevel() {
            return this.f3488a;
        }

        public final boolean isEnabled$bleabstract_release() {
            return this.b;
        }

        @NotNull
        public final Builder setEnabled(boolean z) {
            this.b = z;
            return this;
        }

        public final void setEnabled$bleabstract_release(boolean z) {
            this.b = z;
        }

        /* renamed from: setLevel  reason: collision with other method in class */
        public final void m35setLevel(int i) {
            this.f3488a = i;
        }

        @NotNull
        public final Builder setLevel(int i) {
            this.f3488a = i;
            return this;
        }
    }

    public final int getLevel() {
        return this.f;
    }

    public final boolean isEnabled() {
        return this.g;
    }

    public final void setLevel(int i) {
        this.f = i;
    }
}
