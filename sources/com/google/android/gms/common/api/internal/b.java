package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class b implements zaca {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8269a;
    public final zabe b;
    public final Looper c;
    public final zabi d;
    public final zabi e;
    public final Map f;
    @Nullable
    public final Api.Client h;
    @Nullable
    public Bundle i;
    public final Lock m;
    public final Set g = Collections.newSetFromMap(new WeakHashMap());
    @Nullable
    public ConnectionResult j = null;
    @Nullable
    public ConnectionResult k = null;
    public boolean l = false;
    @GuardedBy("mLock")
    public int n = 0;

    public b(Context context, zabe zabeVar, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map map, Map map2, ClientSettings clientSettings, Api.AbstractClientBuilder abstractClientBuilder, @Nullable Api.Client client, ArrayList arrayList, ArrayList arrayList2, Map map3, Map map4) {
        this.f8269a = context;
        this.b = zabeVar;
        this.m = lock;
        this.c = looper;
        this.h = client;
        this.d = new zabi(context, zabeVar, lock, looper, googleApiAvailabilityLight, map2, null, map4, null, arrayList2, new a1(this, null));
        this.e = new zabi(context, zabeVar, lock, looper, googleApiAvailabilityLight, map, clientSettings, map3, abstractClientBuilder, arrayList, new b1(this, null));
        ArrayMap arrayMap = new ArrayMap();
        for (Api.AnyClientKey anyClientKey : map2.keySet()) {
            arrayMap.put(anyClientKey, this.d);
        }
        for (Api.AnyClientKey anyClientKey2 : map.keySet()) {
            arrayMap.put(anyClientKey2, this.e);
        }
        this.f = Collections.unmodifiableMap(arrayMap);
    }

    public static boolean e(@Nullable ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    public static b g(Context context, zabe zabeVar, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map map, ClientSettings clientSettings, Map map2, Api.AbstractClientBuilder abstractClientBuilder, ArrayList arrayList) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        Api.Client client = null;
        for (Map.Entry entry : map.entrySet()) {
            Api.Client client2 = (Api.Client) entry.getValue();
            if (true == client2.providesSignIn()) {
                client = client2;
            }
            if (client2.requiresSignIn()) {
                arrayMap.put((Api.AnyClientKey) entry.getKey(), client2);
            } else {
                arrayMap2.put((Api.AnyClientKey) entry.getKey(), client2);
            }
        }
        Preconditions.checkState(!arrayMap.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        ArrayMap arrayMap3 = new ArrayMap();
        ArrayMap arrayMap4 = new ArrayMap();
        for (Api api : map2.keySet()) {
            Api.AnyClientKey zab = api.zab();
            if (arrayMap.containsKey(zab)) {
                arrayMap3.put(api, (Boolean) map2.get(api));
            } else if (arrayMap2.containsKey(zab)) {
                arrayMap4.put(api, (Boolean) map2.get(api));
            } else {
                throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            zat zatVar = (zat) arrayList.get(i);
            if (arrayMap3.containsKey(zatVar.zaa)) {
                arrayList2.add(zatVar);
            } else if (arrayMap4.containsKey(zatVar.zaa)) {
                arrayList3.add(zatVar);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
            }
        }
        return new b(context, zabeVar, lock, looper, googleApiAvailabilityLight, arrayMap, arrayMap2, clientSettings, abstractClientBuilder, client, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    public static /* bridge */ /* synthetic */ void n(b bVar, int i, boolean z) {
        bVar.b.zac(i, z);
        bVar.k = null;
        bVar.j = null;
    }

    public static /* bridge */ /* synthetic */ void o(b bVar, Bundle bundle) {
        Bundle bundle2 = bVar.i;
        if (bundle2 == null) {
            bVar.i = bundle;
        } else if (bundle != null) {
            bundle2.putAll(bundle);
        }
    }

    public static /* bridge */ /* synthetic */ void p(b bVar) {
        ConnectionResult connectionResult;
        if (e(bVar.j)) {
            if (!e(bVar.k) && !bVar.c()) {
                ConnectionResult connectionResult2 = bVar.k;
                if (connectionResult2 != null) {
                    if (bVar.n == 1) {
                        bVar.b();
                        return;
                    }
                    bVar.a(connectionResult2);
                    bVar.d.zar();
                    return;
                }
                return;
            }
            int i = bVar.n;
            if (i != 1) {
                if (i != 2) {
                    Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                    bVar.n = 0;
                }
                ((zabe) Preconditions.checkNotNull(bVar.b)).zab(bVar.i);
            }
            bVar.b();
            bVar.n = 0;
        } else if (bVar.j != null && e(bVar.k)) {
            bVar.e.zar();
            bVar.a((ConnectionResult) Preconditions.checkNotNull(bVar.j));
        } else {
            ConnectionResult connectionResult3 = bVar.j;
            if (connectionResult3 == null || (connectionResult = bVar.k) == null) {
                return;
            }
            if (bVar.e.m < bVar.d.m) {
                connectionResult3 = connectionResult;
            }
            bVar.a(connectionResult3);
        }
    }

    @GuardedBy("mLock")
    public final void a(ConnectionResult connectionResult) {
        int i = this.n;
        if (i != 1) {
            if (i == 2) {
                this.b.zaa(connectionResult);
            } else {
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                this.n = 0;
            }
        }
        b();
        this.n = 0;
    }

    @GuardedBy("mLock")
    public final void b() {
        for (SignInConnectionListener signInConnectionListener : this.g) {
            signInConnectionListener.onComplete();
        }
        this.g.clear();
    }

    @GuardedBy("mLock")
    public final boolean c() {
        ConnectionResult connectionResult = this.k;
        return connectionResult != null && connectionResult.getErrorCode() == 4;
    }

    public final boolean d(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        zabi zabiVar = (zabi) this.f.get(apiMethodImpl.getClientKey());
        Preconditions.checkNotNull(zabiVar, "GoogleApiClient is not configured to use the API required for this call.");
        return zabiVar.equals(this.e);
    }

    @Nullable
    public final PendingIntent r() {
        if (this.h == null) {
            return null;
        }
        return PendingIntent.getActivity(this.f8269a, System.identityHashCode(this.b), this.h.getSignInIntent(), com.google.android.gms.internal.base.zap.zaa | 134217728);
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final ConnectionResult zab() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final ConnectionResult zac(long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @Nullable
    @GuardedBy("mLock")
    public final ConnectionResult zad(@NonNull Api api) {
        if (Objects.equal(this.f.get(api.zab()), this.e)) {
            if (c()) {
                return new ConnectionResult(4, r());
            }
            return this.e.zad(api);
        }
        return this.d.zad(api);
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final BaseImplementation.ApiMethodImpl zae(@NonNull BaseImplementation.ApiMethodImpl apiMethodImpl) {
        if (d(apiMethodImpl)) {
            if (c()) {
                apiMethodImpl.setFailedResult(new Status(4, (String) null, r()));
                return apiMethodImpl;
            }
            this.e.zae(apiMethodImpl);
            return apiMethodImpl;
        }
        this.d.zae(apiMethodImpl);
        return apiMethodImpl;
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final BaseImplementation.ApiMethodImpl zaf(@NonNull BaseImplementation.ApiMethodImpl apiMethodImpl) {
        if (d(apiMethodImpl)) {
            if (c()) {
                apiMethodImpl.setFailedResult(new Status(4, (String) null, r()));
                return apiMethodImpl;
            }
            return this.e.zaf(apiMethodImpl);
        }
        return this.d.zaf(apiMethodImpl);
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final void zaq() {
        this.n = 2;
        this.l = false;
        this.k = null;
        this.j = null;
        this.d.zaq();
        this.e.zaq();
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final void zar() {
        this.k = null;
        this.j = null;
        this.n = 0;
        this.d.zar();
        this.e.zar();
        b();
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void zas(String str, @Nullable FileDescriptor fileDescriptor, PrintWriter printWriter, @Nullable String[] strArr) {
        printWriter.append((CharSequence) str).append("authClient").println(":");
        this.e.zas(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append((CharSequence) str).append("anonClient").println(":");
        this.d.zas(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final void zat() {
        this.d.zat();
        this.e.zat();
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void zau() {
        this.m.lock();
        try {
            boolean zax = zax();
            this.e.zar();
            this.k = new ConnectionResult(4);
            if (zax) {
                new com.google.android.gms.internal.base.zau(this.c).post(new z0(this));
            } else {
                b();
            }
        } finally {
            this.m.unlock();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
        if (r3.n == 1) goto L12;
     */
    @Override // com.google.android.gms.common.api.internal.zaca
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zaw() {
        /*
            r3 = this;
            java.util.concurrent.locks.Lock r0 = r3.m
            r0.lock()
            com.google.android.gms.common.api.internal.zabi r0 = r3.d     // Catch: java.lang.Throwable -> L28
            boolean r0 = r0.zaw()     // Catch: java.lang.Throwable -> L28
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L22
            com.google.android.gms.common.api.internal.zabi r0 = r3.e     // Catch: java.lang.Throwable -> L28
            boolean r0 = r0.zaw()     // Catch: java.lang.Throwable -> L28
            if (r0 != 0) goto L21
            boolean r0 = r3.c()     // Catch: java.lang.Throwable -> L28
            if (r0 != 0) goto L21
            int r0 = r3.n     // Catch: java.lang.Throwable -> L28
            if (r0 != r2) goto L22
        L21:
            r1 = r2
        L22:
            java.util.concurrent.locks.Lock r0 = r3.m
            r0.unlock()
            return r1
        L28:
            r0 = move-exception
            java.util.concurrent.locks.Lock r1 = r3.m
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.b.zaw():boolean");
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final boolean zax() {
        this.m.lock();
        try {
            return this.n == 2;
        } finally {
            this.m.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final boolean zay(SignInConnectionListener signInConnectionListener) {
        this.m.lock();
        try {
            if ((zax() || zaw()) && !this.e.zaw()) {
                this.g.add(signInConnectionListener);
                if (this.n == 0) {
                    this.n = 1;
                }
                this.k = null;
                this.e.zaq();
                return true;
            }
            this.m.unlock();
            return false;
        } finally {
            this.m.unlock();
        }
    }
}
