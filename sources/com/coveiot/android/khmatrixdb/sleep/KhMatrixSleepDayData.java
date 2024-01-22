package com.coveiot.android.khmatrixdb.sleep;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class KhMatrixSleepDayData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f4624a;
    @Nullable
    public final List<KhMatrixSleepDetailData> b;

    public KhMatrixSleepDayData(@NotNull String date, @Nullable List<KhMatrixSleepDetailData> list) {
        Intrinsics.checkNotNullParameter(date, "date");
        this.f4624a = date;
        this.b = list;
    }

    @Nullable
    public final List<KhMatrixSleepDetailData> getAnalysedSleepData() {
        return this.b;
    }

    @NotNull
    public final String getDate() {
        return this.f4624a;
    }
}
