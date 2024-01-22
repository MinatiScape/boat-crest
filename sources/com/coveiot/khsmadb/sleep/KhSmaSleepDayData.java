package com.coveiot.khsmadb.sleep;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class KhSmaSleepDayData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f7154a;
    @Nullable
    public final List<KhBleSleep> b;

    public KhSmaSleepDayData(@NotNull String date, @Nullable List<KhBleSleep> list) {
        Intrinsics.checkNotNullParameter(date, "date");
        this.f7154a = date;
        this.b = list;
    }

    @Nullable
    public final List<KhBleSleep> getAnalysedSleepData() {
        return this.b;
    }

    @NotNull
    public final String getDate() {
        return this.f7154a;
    }
}
