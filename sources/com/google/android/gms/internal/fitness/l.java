package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
/* loaded from: classes8.dex */
public final class l extends Api.AbstractClientBuilder<zzaj, Api.ApiOptions.HasGoogleSignInAccountOptions> {
    public l() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ zzaj buildClient(Context context, Looper looper, ClientSettings clientSettings, Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzaj(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
