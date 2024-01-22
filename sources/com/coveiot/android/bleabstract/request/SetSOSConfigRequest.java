package com.coveiot.android.bleabstract.request;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetSOSConfigRequest extends BleBaseRequest {
    public final boolean f;
    public final int g;
    @NotNull
    public final String h;
    @NotNull
    public final String i;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3545a;
        public int b;
        @NotNull
        public String c = "";
        @NotNull
        public String d = "";

        @NotNull
        public final SetSOSConfigRequest build() {
            return new SetSOSConfigRequest(this.f3545a, this.b, this.c, this.d);
        }

        @NotNull
        public final String getContactName() {
            return this.c;
        }

        @NotNull
        public final String getContactNumber() {
            return this.d;
        }

        public final int getTimer() {
            return this.b;
        }

        public final boolean isSOSConfigControl() {
            return this.f3545a;
        }

        public final void setContactName(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.c = str;
        }

        public final void setContactNumber(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.d = str;
        }

        public final void setSOSConfigControl(boolean z) {
            this.f3545a = z;
        }

        @NotNull
        public final Builder setSOSConfigRequests(boolean z, int i, @NotNull String contactName, @NotNull String contactNumber) {
            Intrinsics.checkNotNullParameter(contactName, "contactName");
            Intrinsics.checkNotNullParameter(contactNumber, "contactNumber");
            this.f3545a = z;
            this.b = i;
            this.c = contactName;
            this.d = contactNumber;
            return this;
        }

        public final void setTimer(int i) {
            this.b = i;
        }
    }

    public SetSOSConfigRequest(boolean z, int i, @NotNull String contactName, @NotNull String contactNumber) {
        Intrinsics.checkNotNullParameter(contactName, "contactName");
        Intrinsics.checkNotNullParameter(contactNumber, "contactNumber");
        this.f = z;
        this.g = i;
        this.h = contactName;
        this.i = contactNumber;
    }

    @NotNull
    public final String getContactName() {
        return this.h;
    }

    @NotNull
    public final String getContactNumber() {
        return this.i;
    }

    public final int getTimer() {
        return this.g;
    }

    public final boolean isSOSConfigControl() {
        return this.f;
    }
}
