package com.coveiot.android.smasdk.api;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaBaseRes {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Object f5765a;
    public SmaBaseReq baseReq;

    @NotNull
    public final SmaBaseReq getBaseReq() {
        SmaBaseReq smaBaseReq = this.baseReq;
        if (smaBaseReq != null) {
            return smaBaseReq;
        }
        Intrinsics.throwUninitializedPropertyAccessException("baseReq");
        return null;
    }

    @Nullable
    public final Object getObj() {
        return this.f5765a;
    }

    public final void setBaseReq(@NotNull SmaBaseReq smaBaseReq) {
        Intrinsics.checkNotNullParameter(smaBaseReq, "<set-?>");
        this.baseReq = smaBaseReq;
    }

    public final void setObj(@Nullable Object obj) {
        this.f5765a = obj;
    }
}
