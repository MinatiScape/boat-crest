package com.coveiot.textrecognition.base;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class OcrError {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f7603a;
    @Nullable
    public final Integer b;

    public OcrError(@NotNull String errorMsg) {
        Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
        this.f7603a = errorMsg;
    }

    @Nullable
    public final Integer getErrorCode() {
        return this.b;
    }

    @NotNull
    public final String getErrorMsg() {
        return this.f7603a;
    }
}
