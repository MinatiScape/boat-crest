package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BasePendingResult;
/* loaded from: classes6.dex */
public final class c<R extends Result> extends BasePendingResult<R> {

    /* renamed from: a  reason: collision with root package name */
    public final Result f8251a;

    public c(GoogleApiClient googleApiClient, Result result) {
        super(googleApiClient);
        this.f8251a = result;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final R createFailedResult(Status status) {
        return (R) this.f8251a;
    }
}
