package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetNavigationConfigurationRequest extends BleBaseRequest {
    public boolean f;
    public boolean g;
    public boolean h;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3538a;
        public boolean b;
        public boolean c;

        @NotNull
        public final SetNavigationConfigurationRequest build() {
            return new SetNavigationConfigurationRequest(this.f3538a, this.b, this.c);
        }

        public final boolean isAODEnabled() {
            return this.c;
        }

        public final boolean isAudioEnabled() {
            return this.f3538a;
        }

        public final boolean isHapticEnabled() {
            return this.b;
        }

        public final void setAODEnabled(boolean z) {
            this.c = z;
        }

        public final void setAudioEnabled(boolean z) {
            this.f3538a = z;
        }

        public final void setHapticEnabled(boolean z) {
            this.b = z;
        }

        @NotNull
        public final Builder setNavigationConfigurationRequest(boolean z, boolean z2, boolean z3) {
            this.f3538a = z;
            this.b = z2;
            this.c = z3;
            return this;
        }
    }

    public SetNavigationConfigurationRequest(boolean z, boolean z2, boolean z3) {
        this.f = z;
        this.g = z2;
        this.h = z3;
    }

    public final boolean isAODEnabled() {
        return this.h;
    }

    public final boolean isAudioEnabled() {
        return this.f;
    }

    public final boolean isHapticEnabled() {
        return this.g;
    }

    public final void setAODEnabled(boolean z) {
        this.h = z;
    }

    public final void setAudioEnabled(boolean z) {
        this.f = z;
    }

    public final void setHapticEnabled(boolean z) {
        this.g = z;
    }
}
