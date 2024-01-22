package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;
/* loaded from: classes6.dex */
public final class a implements PendingResult.StatusListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Batch f8249a;

    public a(Batch batch) {
        this.f8249a = batch;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        Object obj;
        int i;
        int i2;
        boolean z;
        boolean z2;
        Status status2;
        PendingResult[] pendingResultArr;
        obj = this.f8249a.e;
        synchronized (obj) {
            if (this.f8249a.isCanceled()) {
                return;
            }
            if (status.isCanceled()) {
                this.f8249a.c = true;
            } else if (!status.isSuccess()) {
                this.f8249a.b = true;
            }
            Batch batch = this.f8249a;
            i = batch.f8242a;
            batch.f8242a = i - 1;
            Batch batch2 = this.f8249a;
            i2 = batch2.f8242a;
            if (i2 == 0) {
                z = batch2.c;
                if (z) {
                    super/*com.google.android.gms.common.api.internal.BasePendingResult*/.cancel();
                } else {
                    z2 = batch2.b;
                    if (z2) {
                        status2 = new Status(13);
                    } else {
                        status2 = Status.RESULT_SUCCESS;
                    }
                    Batch batch3 = this.f8249a;
                    pendingResultArr = batch3.d;
                    batch3.setResult(new BatchResult(status2, pendingResultArr));
                }
            }
        }
    }
}
