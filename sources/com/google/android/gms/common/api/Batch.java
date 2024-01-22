package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public final class Batch extends BasePendingResult<BatchResult> {

    /* renamed from: a  reason: collision with root package name */
    public int f8242a;
    public boolean b;
    public boolean c;
    public final PendingResult[] d;
    public final Object e;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public List f8243a = new ArrayList();
        public GoogleApiClient b;

        public Builder(@NonNull GoogleApiClient googleApiClient) {
            this.b = googleApiClient;
        }

        @NonNull
        public <R extends Result> BatchResultToken<R> add(@NonNull PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.f8243a.size());
            this.f8243a.add(pendingResult);
            return batchResultToken;
        }

        @NonNull
        public Batch build() {
            return new Batch(this.f8243a, this.b, null);
        }
    }

    public /* synthetic */ Batch(List list, GoogleApiClient googleApiClient, zac zacVar) {
        super(googleApiClient);
        this.e = new Object();
        int size = list.size();
        this.f8242a = size;
        PendingResult[] pendingResultArr = new PendingResult[size];
        this.d = pendingResultArr;
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                PendingResult pendingResult = (PendingResult) list.get(i);
                this.d[i] = pendingResult;
                pendingResult.addStatusListener(new a(this));
            }
            return;
        }
        setResult(new BatchResult(Status.RESULT_SUCCESS, pendingResultArr));
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult, com.google.android.gms.common.api.PendingResult
    public void cancel() {
        super.cancel();
        for (PendingResult pendingResult : this.d) {
            pendingResult.cancel();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    @NonNull
    public BatchResult createFailedResult(@NonNull Status status) {
        return new BatchResult(status, this.d);
    }
}
