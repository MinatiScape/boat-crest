package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.DataTypeResult;
/* loaded from: classes6.dex */
public final /* synthetic */ class e implements PendingResultUtil.ResultConverter {

    /* renamed from: a  reason: collision with root package name */
    public static final PendingResultUtil.ResultConverter f8437a = new e();

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ConfigClient.d((DataTypeResult) result);
    }
}
