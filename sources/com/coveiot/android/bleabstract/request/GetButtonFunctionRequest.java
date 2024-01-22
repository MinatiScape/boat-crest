package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetButtonFunctionRequest extends BleBaseRequest {
    public int f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3491a;

        @NotNull
        public final GetButtonFunctionRequest build() {
            return new GetButtonFunctionRequest(this.f3491a);
        }

        @NotNull
        public final Builder getButtonFunctionsRequest(int i) {
            this.f3491a = i;
            return this;
        }

        public final int getPosition() {
            return this.f3491a;
        }

        public final void setPosition(int i) {
            this.f3491a = i;
        }
    }

    public GetButtonFunctionRequest(int i) {
        this.f = i;
    }

    public final int getPosition() {
        return this.f;
    }

    public final void setPosition(int i) {
        this.f = i;
    }
}
