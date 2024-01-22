package com.coveiot.textrecognition.model;

import com.coveiot.textrecognition.base.BaseOcrResponse;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class OxymeterResponse extends BaseOcrResponse {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f7604a = "";
    @NotNull
    public String b = "";

    @NotNull
    public final String getPulseRate() {
        return this.b;
    }

    @NotNull
    public final String getSpo2() {
        return this.f7604a;
    }

    public final void setPulseRate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setSpo2(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f7604a = str;
    }
}
