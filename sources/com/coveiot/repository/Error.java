package com.coveiot.repository;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class Error {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f7295a;
    @Nullable
    public Integer b;
    @Nullable
    public String c;

    public Error(@NotNull String message, @Nullable Integer num, @Nullable String str) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.f7295a = message;
        this.b = num;
        this.c = str;
    }

    @Nullable
    public final Integer getCode() {
        return this.b;
    }

    @Nullable
    public final String getDataType() {
        return this.c;
    }

    @NotNull
    public final String getMessage() {
        return this.f7295a;
    }

    public final void setCode(@Nullable Integer num) {
        this.b = num;
    }

    public final void setDataType(@Nullable String str) {
        this.c = str;
    }

    public final void setMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f7295a = str;
    }
}
