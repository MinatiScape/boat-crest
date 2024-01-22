package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
/* loaded from: classes8.dex */
public final class v extends Api.AbstractClientBuilder<zzav, Api.ApiOptions.HasGoogleSignInAccountOptions> {
    public v() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ zzav buildClient(Context context, Looper looper, ClientSettings clientSettings, Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzav(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
