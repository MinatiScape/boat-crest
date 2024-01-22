package com.coveiot.android.matrixsdk.api;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class MatrixBaseRes {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Object f5468a;
    public MatrixBaseReq baseReq;

    @NotNull
    public final MatrixBaseReq getBaseReq() {
        MatrixBaseReq matrixBaseReq = this.baseReq;
        if (matrixBaseReq != null) {
            return matrixBaseReq;
        }
        Intrinsics.throwUninitializedPropertyAccessException("baseReq");
        return null;
    }

    @Nullable
    public final Object getObj() {
        return this.f5468a;
    }

    public final void setBaseReq(@NotNull MatrixBaseReq matrixBaseReq) {
        Intrinsics.checkNotNullParameter(matrixBaseReq, "<set-?>");
        this.baseReq = matrixBaseReq;
    }

    public final void setObj(@Nullable Object obj) {
        this.f5468a = obj;
    }
}
