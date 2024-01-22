package com.coveiot.android.bleabstract.formatter;

import com.coveiot.android.bleabstract.response.ECGResultResponse;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SmartTFormatter {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f3339a;

    public SmartTFormatter(@Nullable String str) {
        this.f3339a = str;
    }

    @NotNull
    public final ECGResultResponse getECGResultResponse(@NotNull List<Integer> queueEcg, @NotNull String ecgStartDate, @NotNull String ecgEndDate) {
        Intrinsics.checkNotNullParameter(queueEcg, "queueEcg");
        Intrinsics.checkNotNullParameter(ecgStartDate, "ecgStartDate");
        Intrinsics.checkNotNullParameter(ecgEndDate, "ecgEndDate");
        ECGResultResponse eCGResultResponse = new ECGResultResponse();
        eCGResultResponse.setStartDateTime(ecgStartDate);
        eCGResultResponse.setEndDateTime(ecgEndDate);
        eCGResultResponse.setQueueEcg(queueEcg);
        eCGResultResponse.setMMacAddress(this.f3339a);
        return eCGResultResponse;
    }
}
