package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
/* loaded from: classes6.dex */
public final class zabv extends zaag {
    @NotOnlyInitialized
    public final GoogleApi c;

    public zabv(GoogleApi googleApi) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        this.c = googleApi;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        return (T) this.c.doRead((GoogleApi) t);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        return (T) this.c.doWrite((GoogleApi) t);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Context getContext() {
        return this.c.getApplicationContext();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Looper getLooper() {
        return this.c.getLooper();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zao(zada zadaVar) {
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zap(zada zadaVar) {
    }
}
