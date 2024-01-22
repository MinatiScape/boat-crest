package com.clevertap.android.sdk.task;

import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public class b<TResult> extends com.clevertap.android.sdk.task.a<TResult> {
    public final OnFailureListener<TResult> b;

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public final /* synthetic */ Object h;

        public a(Object obj) {
            this.h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            b.this.b.onFailure(this.h);
        }
    }

    public b(Executor executor, OnFailureListener<TResult> onFailureListener) {
        super(executor);
        this.b = onFailureListener;
    }

    @Override // com.clevertap.android.sdk.task.a
    public void a(TResult tresult) {
        this.f2686a.execute(new a(tresult));
    }

    public OnFailureListener<TResult> c() {
        return this.b;
    }
}
