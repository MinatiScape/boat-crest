package com.clevertap.android.sdk.task;

import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public abstract class a<TResult> {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f2686a;

    public a(Executor executor) {
        this.f2686a = executor;
    }

    public abstract void a(TResult tresult);
}
