package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.locks.Lock;
/* loaded from: classes6.dex */
public final class p implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zaaw f8281a;

    public /* synthetic */ p(zaaw zaawVar, zaas zaasVar) {
        this.f8281a = zaawVar;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        ClientSettings clientSettings;
        com.google.android.gms.signin.zae zaeVar;
        clientSettings = this.f8281a.r;
        ClientSettings clientSettings2 = (ClientSettings) Preconditions.checkNotNull(clientSettings);
        zaeVar = this.f8281a.k;
        ((com.google.android.gms.signin.zae) Preconditions.checkNotNull(zaeVar)).zad(new o(this.f8281a));
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Lock lock;
        Lock lock2;
        boolean i;
        lock = this.f8281a.b;
        lock.lock();
        try {
            i = this.f8281a.i(connectionResult);
            if (i) {
                this.f8281a.a();
                this.f8281a.f();
            } else {
                this.f8281a.d(connectionResult);
            }
        } finally {
            lock2 = this.f8281a.b;
            lock2.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }
}
