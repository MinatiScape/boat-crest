package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes6.dex */
public final class s implements GoogleApiClient.ConnectionCallbacks {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AtomicReference f8283a;
    public final /* synthetic */ StatusPendingResult b;
    public final /* synthetic */ zabe c;

    public s(zabe zabeVar, AtomicReference atomicReference, StatusPendingResult statusPendingResult) {
        this.c = zabeVar;
        this.f8283a = atomicReference;
        this.b = statusPendingResult;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        this.c.j((GoogleApiClient) Preconditions.checkNotNull((GoogleApiClient) this.f8283a.get()), this.b, true);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }
}
