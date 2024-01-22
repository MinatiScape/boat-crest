package com.coveiot.android.touchsdk.api;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class TouchELXBaseRes {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Object f6107a;
    @Nullable
    public Object b;
    public TouchELXBaseReq baseReq;
    public boolean c;

    @NotNull
    public final TouchELXBaseReq getBaseReq() {
        TouchELXBaseReq touchELXBaseReq = this.baseReq;
        if (touchELXBaseReq != null) {
            return touchELXBaseReq;
        }
        Intrinsics.throwUninitializedPropertyAccessException("baseReq");
        return null;
    }

    @Nullable
    public final Object getObj() {
        return this.f6107a;
    }

    @Nullable
    public final Object getObj2() {
        return this.b;
    }

    public final boolean isComplete() {
        return this.c;
    }

    public final void setBaseReq(@NotNull TouchELXBaseReq touchELXBaseReq) {
        Intrinsics.checkNotNullParameter(touchELXBaseReq, "<set-?>");
        this.baseReq = touchELXBaseReq;
    }

    public final void setComplete(boolean z) {
        this.c = z;
    }

    public final void setObj(@Nullable Object obj) {
        this.f6107a = obj;
    }

    public final void setObj2(@Nullable Object obj) {
        this.b = obj;
    }
}
