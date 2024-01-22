package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public final class BatchResult implements Result {
    public final Status h;
    public final PendingResult[] i;

    public BatchResult(Status status, PendingResult[] pendingResultArr) {
        this.h = status;
        this.i = pendingResultArr;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    public Status getStatus() {
        return this.h;
    }

    @NonNull
    public <R extends Result> R take(@NonNull BatchResultToken<R> batchResultToken) {
        Preconditions.checkArgument(batchResultToken.mId < this.i.length, "The result token does not belong to this batch");
        return (R) this.i[batchResultToken.mId].await(0L, TimeUnit.MILLISECONDS);
    }
}
