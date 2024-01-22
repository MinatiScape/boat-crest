package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes6.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public final ApiKey f8274a;
    public final TaskCompletionSource b = new TaskCompletionSource();

    public e(ApiKey apiKey) {
        this.f8274a = apiKey;
    }

    public final ApiKey a() {
        return this.f8274a;
    }

    public final TaskCompletionSource b() {
        return this.b;
    }
}
