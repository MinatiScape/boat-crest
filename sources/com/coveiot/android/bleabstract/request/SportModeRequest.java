package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SportModeRequest extends BleBaseRequest {
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3562a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;

        public SportModeRequest build() {
            SportModeRequest sportModeRequest = new SportModeRequest();
            sportModeRequest.i = this.c;
            sportModeRequest.f = this.f3562a;
            sportModeRequest.g = this.d;
            sportModeRequest.h = this.b;
            sportModeRequest.j = this.e;
            sportModeRequest.k = this.f;
            return sportModeRequest;
        }

        public Builder setIsIndoor(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setModes(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
            this.f3562a = z;
            this.d = z2;
            this.b = z3;
            this.c = z4;
            this.e = z5;
            return this;
        }
    }

    public boolean isCycling() {
        return this.h;
    }

    public boolean isIndoor() {
        return this.k;
    }

    public boolean isRunning() {
        return this.f;
    }

    public boolean isSwimming() {
        return this.g;
    }

    public boolean isTaichi() {
        return this.j;
    }

    public boolean isWalking() {
        return this.i;
    }
}
