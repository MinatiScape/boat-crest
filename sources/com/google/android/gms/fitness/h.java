package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.DailyTotalResult;
/* loaded from: classes6.dex */
public final /* synthetic */ class h implements PendingResultUtil.ResultConverter {

    /* renamed from: a  reason: collision with root package name */
    public static final PendingResultUtil.ResultConverter f8439a = new h();

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return HistoryClient.d((DailyTotalResult) result);
    }
}
