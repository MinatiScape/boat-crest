package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zav;
import com.google.android.gms.signin.zad;
import java.util.Set;
/* loaded from: classes6.dex */
public final class zact extends com.google.android.gms.signin.internal.zac implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public static final Api.AbstractClientBuilder h = zad.zac;

    /* renamed from: a  reason: collision with root package name */
    public final Context f8307a;
    public final Handler b;
    public final Api.AbstractClientBuilder c;
    public final Set d;
    public final ClientSettings e;
    public com.google.android.gms.signin.zae f;
    public zacs g;

    @WorkerThread
    public zact(Context context, Handler handler, @NonNull ClientSettings clientSettings) {
        Api.AbstractClientBuilder abstractClientBuilder = h;
        this.f8307a = context;
        this.b = handler;
        this.e = (ClientSettings) Preconditions.checkNotNull(clientSettings, "ClientSettings must not be null");
        this.d = clientSettings.getRequiredScopes();
        this.c = abstractClientBuilder;
    }

    public static /* bridge */ /* synthetic */ void b(zact zactVar, com.google.android.gms.signin.internal.zak zakVar) {
        ConnectionResult zaa = zakVar.zaa();
        if (zaa.isSuccess()) {
            zav zavVar = (zav) Preconditions.checkNotNull(zakVar.zab());
            ConnectionResult zaa2 = zavVar.zaa();
            if (!zaa2.isSuccess()) {
                String valueOf = String.valueOf(zaa2);
                Log.wtf("SignInCoordinator", "Sign-in succeeded with resolve account failure: ".concat(valueOf), new Exception());
                zactVar.g.zae(zaa2);
                zactVar.f.disconnect();
                return;
            }
            zactVar.g.zaf(zavVar.zab(), zactVar.d);
        } else {
            zactVar.g.zae(zaa);
        }
        zactVar.f.disconnect();
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    @WorkerThread
    public final void onConnected(@Nullable Bundle bundle) {
        this.f.zad(this);
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    @WorkerThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.g.zae(connectionResult);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    @WorkerThread
    public final void onConnectionSuspended(int i) {
        this.f.disconnect();
    }

    @Override // com.google.android.gms.signin.internal.zac, com.google.android.gms.signin.internal.zae
    @BinderThread
    public final void zab(com.google.android.gms.signin.internal.zak zakVar) {
        this.b.post(new m0(this, zakVar));
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.google.android.gms.common.api.Api$Client, com.google.android.gms.signin.zae] */
    @WorkerThread
    public final void zae(zacs zacsVar) {
        com.google.android.gms.signin.zae zaeVar = this.f;
        if (zaeVar != null) {
            zaeVar.disconnect();
        }
        this.e.zae(Integer.valueOf(System.identityHashCode(this)));
        Api.AbstractClientBuilder abstractClientBuilder = this.c;
        Context context = this.f8307a;
        Looper looper = this.b.getLooper();
        ClientSettings clientSettings = this.e;
        this.f = abstractClientBuilder.buildClient(context, looper, clientSettings, (ClientSettings) clientSettings.zaa(), (GoogleApiClient.ConnectionCallbacks) this, (GoogleApiClient.OnConnectionFailedListener) this);
        this.g = zacsVar;
        Set set = this.d;
        if (set != null && !set.isEmpty()) {
            this.f.zab();
        } else {
            this.b.post(new l0(this));
        }
    }

    public final void zaf() {
        com.google.android.gms.signin.zae zaeVar = this.f;
        if (zaeVar != null) {
            zaeVar.disconnect();
        }
    }
}
