package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetDiagnosticControlRequest extends BleBaseRequest {
    public final boolean f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3526a;

        @NotNull
        public final Builder SetDiagnosticProtocolControlRequest(boolean z) {
            this.f3526a = z;
            return this;
        }

        @NotNull
        public final SetDiagnosticControlRequest build() {
            return new SetDiagnosticControlRequest(this.f3526a);
        }

        public final boolean isDiagnosticControl() {
            return this.f3526a;
        }

        public final void setDiagnosticControl(boolean z) {
            this.f3526a = z;
        }
    }

    public SetDiagnosticControlRequest(boolean z) {
        this.f = z;
    }

    public final boolean isDiagnosticControl() {
        return this.f;
    }
}
