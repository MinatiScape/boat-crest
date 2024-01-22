package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BasePendingResult;
/* loaded from: classes6.dex */
public final class b<R extends Result> extends BasePendingResult<R> {

    /* renamed from: a  reason: collision with root package name */
    public final Result f8250a;

    public b(Result result) {
        super(Looper.getMainLooper());
        this.f8250a = result;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final R createFailedResult(Status status) {
        if (status.getStatusCode() == this.f8250a.getStatus().getStatusCode()) {
            return (R) this.f8250a;
        }
        throw new UnsupportedOperationException("Creating failed results is not supported");
    }
}
