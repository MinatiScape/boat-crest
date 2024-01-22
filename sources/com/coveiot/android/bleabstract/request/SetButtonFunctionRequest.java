package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetButtonFunctionRequest extends BleBaseRequest {
    public int f;
    public int g;
    public int h;
    public int i;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3521a;
        public int b;
        public int c;
        public int d;

        @NotNull
        public final SetButtonFunctionRequest build() {
            return new SetButtonFunctionRequest(this.f3521a, this.b, this.c, this.d);
        }

        public final int getLongPress() {
            return this.d;
        }

        public final int getPosition() {
            return this.b;
        }

        public final int getShortPress() {
            return this.c;
        }

        public final int getVesrion() {
            return this.f3521a;
        }

        @NotNull
        public final Builder setButtonFunctionRequest(int i, int i2, int i3, int i4) {
            this.f3521a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            return this;
        }

        public final void setLongPress(int i) {
            this.d = i;
        }

        public final void setPosition(int i) {
            this.b = i;
        }

        public final void setShortPress(int i) {
            this.c = i;
        }

        public final void setVesrion(int i) {
            this.f3521a = i;
        }
    }

    public SetButtonFunctionRequest(int i, int i2, int i3, int i4) {
        this.f = i;
        this.g = i2;
        this.h = i3;
        this.i = i4;
    }

    public final int getLongPress() {
        return this.i;
    }

    public final int getPosition() {
        return this.g;
    }

    public final int getShortPress() {
        return this.h;
    }

    public final int getVesrion() {
        return this.f;
    }

    public final void setLongPress(int i) {
        this.i = i;
    }

    public final void setPosition(int i) {
        this.g = i;
    }

    public final void setShortPress(int i) {
        this.h = i;
    }

    public final void setVesrion(int i) {
        this.f = i;
    }
}
