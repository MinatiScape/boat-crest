package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
/* loaded from: classes6.dex */
public final class h implements PendingResultUtil.ResultConverter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Response f8335a;

    public h(Response response) {
        this.f8335a = response;
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* bridge */ /* synthetic */ Object convert(Result result) {
        this.f8335a.setResult(result);
        return this.f8335a;
    }
}
