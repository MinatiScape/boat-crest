package com.google.android.gms.internal.p002authapi;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.identity.zbp;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
/* renamed from: com.google.android.gms.internal.auth-api.d  reason: invalid package */
/* loaded from: classes6.dex */
public final class d extends Api.AbstractClientBuilder {
    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return new zbaz(context, looper, (zbp) obj, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
