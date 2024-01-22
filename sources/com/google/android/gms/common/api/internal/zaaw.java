package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zav;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class zaaw implements zabf {

    /* renamed from: a  reason: collision with root package name */
    public final zabi f8298a;
    public final Lock b;
    public final Context c;
    public final GoogleApiAvailabilityLight d;
    @Nullable
    public ConnectionResult e;
    public int f;
    public int h;
    @Nullable
    public com.google.android.gms.signin.zae k;
    public boolean l;
    public boolean m;
    public boolean n;
    @Nullable
    public IAccountAccessor o;
    public boolean p;
    public boolean q;
    @Nullable
    public final ClientSettings r;
    public final Map s;
    @Nullable
    public final Api.AbstractClientBuilder t;
    public int g = 0;
    public final Bundle i = new Bundle();
    public final Set j = new HashSet();
    public final ArrayList u = new ArrayList();

    public zaaw(zabi zabiVar, @Nullable ClientSettings clientSettings, Map map, GoogleApiAvailabilityLight googleApiAvailabilityLight, @Nullable Api.AbstractClientBuilder abstractClientBuilder, Lock lock, Context context) {
        this.f8298a = zabiVar;
        this.r = clientSettings;
        this.s = map;
        this.d = googleApiAvailabilityLight;
        this.t = abstractClientBuilder;
        this.b = lock;
        this.c = context;
    }

    public static final String j(int i) {
        return i != 0 ? "STEP_GETTING_REMOTE_SERVICE" : "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
    }

    public static /* bridge */ /* synthetic */ Set q(zaaw zaawVar) {
        ClientSettings clientSettings = zaawVar.r;
        if (clientSettings == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(clientSettings.getRequiredScopes());
        Map zad = zaawVar.r.zad();
        for (Api api : zad.keySet()) {
            if (!zaawVar.f8298a.g.containsKey(api.zab())) {
                hashSet.addAll(((com.google.android.gms.common.internal.zab) zad.get(api)).zaa);
            }
        }
        return hashSet;
    }

    public static /* bridge */ /* synthetic */ void t(zaaw zaawVar, com.google.android.gms.signin.internal.zak zakVar) {
        if (zaawVar.g(0)) {
            ConnectionResult zaa = zakVar.zaa();
            if (zaa.isSuccess()) {
                zav zavVar = (zav) Preconditions.checkNotNull(zakVar.zab());
                ConnectionResult zaa2 = zavVar.zaa();
                if (!zaa2.isSuccess()) {
                    String valueOf = String.valueOf(zaa2);
                    Log.wtf("GACConnecting", "Sign-in succeeded with resolve account failure: ".concat(valueOf), new Exception());
                    zaawVar.d(zaa2);
                    return;
                }
                zaawVar.n = true;
                zaawVar.o = (IAccountAccessor) Preconditions.checkNotNull(zavVar.zab());
                zaawVar.p = zavVar.zac();
                zaawVar.q = zavVar.zad();
                zaawVar.f();
            } else if (zaawVar.i(zaa)) {
                zaawVar.a();
                zaawVar.f();
            } else {
                zaawVar.d(zaa);
            }
        }
    }

    public final void B() {
        ArrayList arrayList = this.u;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((Future) arrayList.get(i)).cancel(true);
        }
        this.u.clear();
    }

    @GuardedBy("mLock")
    public final void a() {
        this.m = false;
        this.f8298a.n.p = Collections.emptySet();
        for (Api.AnyClientKey anyClientKey : this.j) {
            if (!this.f8298a.g.containsKey(anyClientKey)) {
                this.f8298a.g.put(anyClientKey, new ConnectionResult(17, null));
            }
        }
    }

    @GuardedBy("mLock")
    public final void b(boolean z) {
        com.google.android.gms.signin.zae zaeVar = this.k;
        if (zaeVar != null) {
            if (zaeVar.isConnected() && z) {
                zaeVar.zaa();
            }
            zaeVar.disconnect();
            ClientSettings clientSettings = (ClientSettings) Preconditions.checkNotNull(this.r);
            this.o = null;
        }
    }

    @GuardedBy("mLock")
    public final void c() {
        this.f8298a.c();
        zabj.zaa().execute(new h(this));
        com.google.android.gms.signin.zae zaeVar = this.k;
        if (zaeVar != null) {
            if (this.p) {
                zaeVar.zac((IAccountAccessor) Preconditions.checkNotNull(this.o), this.q);
            }
            b(false);
        }
        for (Api.AnyClientKey anyClientKey : this.f8298a.g.keySet()) {
            ((Api.Client) Preconditions.checkNotNull((Api.Client) this.f8298a.f.get(anyClientKey))).disconnect();
        }
        this.f8298a.o.zab(this.i.isEmpty() ? null : this.i);
    }

    @GuardedBy("mLock")
    public final void d(ConnectionResult connectionResult) {
        B();
        b(!connectionResult.hasResolution());
        this.f8298a.e(connectionResult);
        this.f8298a.o.zaa(connectionResult);
    }

    @GuardedBy("mLock")
    public final void e(ConnectionResult connectionResult, Api api, boolean z) {
        int priority = api.zac().getPriority();
        if ((!z || connectionResult.hasResolution() || this.d.getErrorResolutionIntent(connectionResult.getErrorCode()) != null) && (this.e == null || priority < this.f)) {
            this.e = connectionResult;
            this.f = priority;
        }
        this.f8298a.g.put(api.zab(), connectionResult);
    }

    @GuardedBy("mLock")
    public final void f() {
        if (this.h != 0) {
            return;
        }
        if (!this.m || this.n) {
            ArrayList arrayList = new ArrayList();
            this.g = 1;
            this.h = this.f8298a.f.size();
            for (Api.AnyClientKey anyClientKey : this.f8298a.f.keySet()) {
                if (this.f8298a.g.containsKey(anyClientKey)) {
                    if (h()) {
                        c();
                    }
                } else {
                    arrayList.add((Api.Client) this.f8298a.f.get(anyClientKey));
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            this.u.add(zabj.zaa().submit(new m(this, arrayList)));
        }
    }

    @GuardedBy("mLock")
    public final boolean g(int i) {
        if (this.g != i) {
            Log.w("GACConnecting", this.f8298a.n.c());
            Log.w("GACConnecting", "Unexpected callback in ".concat(toString()));
            int i2 = this.h;
            Log.w("GACConnecting", "mRemainingConnections=" + i2);
            int i3 = this.g;
            Log.e("GACConnecting", "GoogleApiClient connecting is in step " + j(i3) + " but received callback for step " + j(i), new Exception());
            d(new ConnectionResult(8, null));
            return false;
        }
        return true;
    }

    @GuardedBy("mLock")
    public final boolean h() {
        int i = this.h - 1;
        this.h = i;
        if (i > 0) {
            return false;
        }
        if (i < 0) {
            Log.w("GACConnecting", this.f8298a.n.c());
            Log.wtf("GACConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            d(new ConnectionResult(8, null));
            return false;
        }
        ConnectionResult connectionResult = this.e;
        if (connectionResult != null) {
            this.f8298a.m = this.f;
            d(connectionResult);
            return false;
        }
        return true;
    }

    @GuardedBy("mLock")
    public final boolean i(ConnectionResult connectionResult) {
        return this.l && !connectionResult.hasResolution();
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final BaseImplementation.ApiMethodImpl zaa(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        this.f8298a.n.h.add(apiMethodImpl);
        return apiMethodImpl;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final BaseImplementation.ApiMethodImpl zab(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [com.google.android.gms.common.api.Api$Client, com.google.android.gms.signin.zae] */
    @Override // com.google.android.gms.common.api.internal.zabf
    @GuardedBy("mLock")
    public final void zad() {
        this.f8298a.g.clear();
        this.m = false;
        this.e = null;
        this.g = 0;
        this.l = true;
        this.n = false;
        this.p = false;
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (Api api : this.s.keySet()) {
            Api.Client client = (Api.Client) Preconditions.checkNotNull((Api.Client) this.f8298a.f.get(api.zab()));
            z |= api.zac().getPriority() == 1;
            boolean booleanValue = ((Boolean) this.s.get(api)).booleanValue();
            if (client.requiresSignIn()) {
                this.m = true;
                if (booleanValue) {
                    this.j.add(api.zab());
                } else {
                    this.l = false;
                }
            }
            hashMap.put(client, new i(this, api, booleanValue));
        }
        if (z) {
            this.m = false;
        }
        if (this.m) {
            Preconditions.checkNotNull(this.r);
            Preconditions.checkNotNull(this.t);
            this.r.zae(Integer.valueOf(System.identityHashCode(this.f8298a.n)));
            p pVar = new p(this, null);
            Api.AbstractClientBuilder abstractClientBuilder = this.t;
            Context context = this.c;
            Looper looper = this.f8298a.n.getLooper();
            ClientSettings clientSettings = this.r;
            this.k = abstractClientBuilder.buildClient(context, looper, clientSettings, (ClientSettings) clientSettings.zaa(), (GoogleApiClient.ConnectionCallbacks) pVar, (GoogleApiClient.OnConnectionFailedListener) pVar);
        }
        this.h = this.f8298a.f.size();
        this.u.add(zabj.zaa().submit(new l(this, hashMap)));
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zae() {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    @GuardedBy("mLock")
    public final void zag(@Nullable Bundle bundle) {
        if (g(1)) {
            if (bundle != null) {
                this.i.putAll(bundle);
            }
            if (h()) {
                c();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    @GuardedBy("mLock")
    public final void zah(ConnectionResult connectionResult, Api api, boolean z) {
        if (g(1)) {
            e(connectionResult, api, z);
            if (h()) {
                c();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    @GuardedBy("mLock")
    public final void zai(int i) {
        d(new ConnectionResult(8, null));
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    @GuardedBy("mLock")
    public final boolean zaj() {
        B();
        b(true);
        this.f8298a.e(null);
        return true;
    }
}
