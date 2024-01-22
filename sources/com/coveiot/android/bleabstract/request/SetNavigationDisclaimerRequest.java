package com.coveiot.android.bleabstract.request;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetNavigationDisclaimerRequest extends BleBaseRequest {
    @NotNull
    public String f;
    @NotNull
    public String g;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public String f3539a = "";
        @NotNull
        public String b = "";

        @NotNull
        public final SetNavigationDisclaimerRequest build() {
            return new SetNavigationDisclaimerRequest(this.f3539a, this.b);
        }

        @NotNull
        public final String getDisclaimerText() {
            return this.b;
        }

        @NotNull
        public final String getVersionText() {
            return this.f3539a;
        }

        public final void setDisclaimerText(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.b = str;
        }

        @NotNull
        public final Builder setNavigationDisclaimerRequest(@NotNull String versionText, @NotNull String disclaimerText) {
            Intrinsics.checkNotNullParameter(versionText, "versionText");
            Intrinsics.checkNotNullParameter(disclaimerText, "disclaimerText");
            this.f3539a = versionText;
            this.b = disclaimerText;
            return this;
        }

        public final void setVersionText(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.f3539a = str;
        }
    }

    public SetNavigationDisclaimerRequest(@NotNull String vesrionText, @NotNull String disclaimerText) {
        Intrinsics.checkNotNullParameter(vesrionText, "vesrionText");
        Intrinsics.checkNotNullParameter(disclaimerText, "disclaimerText");
        this.f = vesrionText;
        this.g = disclaimerText;
    }

    @NotNull
    public final String getDisclaimerText() {
        return this.g;
    }

    @NotNull
    public final String getVesrionText() {
        return this.f;
    }

    public final void setDisclaimerText(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.g = str;
    }

    public final void setVesrionText(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }
}
