package com.coveiot.android.leonardo.dashboard.health.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DataBean {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Integer f4729a;
    public final float b;
    @NotNull
    public final String c;

    public DataBean(@Nullable Integer num, float f, @NotNull String stateName) {
        Intrinsics.checkNotNullParameter(stateName, "stateName");
        this.f4729a = num;
        this.b = f;
        this.c = stateName;
    }

    @Nullable
    public final Integer getIndex() {
        return this.f4729a;
    }

    public final float getPercentage() {
        return this.b;
    }

    @NotNull
    public final String getStateName() {
        return this.c;
    }
}
