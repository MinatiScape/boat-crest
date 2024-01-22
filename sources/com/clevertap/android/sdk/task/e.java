package com.clevertap.android.sdk.task;

import com.clevertap.android.sdk.CleverTapInstanceConfig;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public class e<TResult> extends com.clevertap.android.sdk.task.a<TResult> {
    public final OnSuccessListener<TResult> b;

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public final /* synthetic */ Object h;

        public a(Object obj) {
            this.h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            e.this.b.onSuccess(this.h);
        }
    }

    public e(Executor executor, OnSuccessListener<TResult> onSuccessListener, CleverTapInstanceConfig cleverTapInstanceConfig) {
        super(executor);
        this.b = onSuccessListener;
    }

    @Override // com.clevertap.android.sdk.task.a
    public void a(TResult tresult) {
        this.f2686a.execute(new a(tresult));
    }

    public OnSuccessListener<TResult> c() {
        return this.b;
    }
}
