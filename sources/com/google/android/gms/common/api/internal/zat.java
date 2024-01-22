package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes6.dex */
public final class zat implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8312a;
    @Nullable
    public zau b;
    public final Api zaa;

    public zat(Api api, boolean z) {
        this.zaa = api;
        this.f8312a = z;
    }

    public final zau a() {
        Preconditions.checkNotNull(this.b, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
        return this.b;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        a().onConnected(bundle);
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        a().zaa(connectionResult, this.zaa, this.f8312a);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        a().onConnectionSuspended(i);
    }

    public final void zaa(zau zauVar) {
        this.b = zauVar;
    }
}
