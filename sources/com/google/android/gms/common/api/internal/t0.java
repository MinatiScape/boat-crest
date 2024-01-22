package com.google.android.gms.common.api.internal;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
/* loaded from: classes6.dex */
public final class t0 implements GoogleApiClient.OnConnectionFailedListener {

    /* renamed from: a  reason: collision with root package name */
    public final int f8286a;
    public final GoogleApiClient b;
    @Nullable
    public final GoogleApiClient.OnConnectionFailedListener c;
    public final /* synthetic */ zak d;

    public t0(zak zakVar, int i, @Nullable GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.d = zakVar;
        this.f8286a = i;
        this.b = googleApiClient;
        this.c = onConnectionFailedListener;
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("AutoManageHelper", "beginFailureResolution for ".concat(String.valueOf(connectionResult)));
        this.d.zah(connectionResult, this.f8286a);
    }
}
