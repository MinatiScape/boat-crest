package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.internal.zaj;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class zabe extends GoogleApiClient implements zabz {
    public final Lock b;
    public final com.google.android.gms.common.internal.zak c;
    public final int e;
    public final Context f;
    public final Looper g;
    public volatile boolean i;
    public long j;
    public long k;
    public final v l;
    public final GoogleApiAvailability m;
    @Nullable
    @VisibleForTesting
    public zabx n;
    public final Map o;
    public Set p;
    public final ClientSettings q;
    public final Map r;
    public final Api.AbstractClientBuilder s;
    public final ListenerHolders t;
    public final ArrayList u;
    public Integer v;
    @Nullable
    public Set w;
    public final zadc x;
    public final zaj y;
    @Nullable
    public zaca d = null;
    @VisibleForTesting
    public final Queue h = new LinkedList();

    public zabe(Context context, Lock lock, Looper looper, ClientSettings clientSettings, GoogleApiAvailability googleApiAvailability, Api.AbstractClientBuilder abstractClientBuilder, Map map, List list, List list2, Map map2, int i, int i2, ArrayList arrayList) {
        this.j = true != ClientLibraryUtils.isPackageSide() ? 120000L : 10000L;
        this.k = 5000L;
        this.p = new HashSet();
        this.t = new ListenerHolders();
        this.v = null;
        this.w = null;
        r rVar = new r(this);
        this.y = rVar;
        this.f = context;
        this.b = lock;
        this.c = new com.google.android.gms.common.internal.zak(looper, rVar);
        this.g = looper;
        this.l = new v(this, looper);
        this.m = googleApiAvailability;
        this.e = i;
        if (i >= 0) {
            this.v = Integer.valueOf(i2);
        }
        this.r = map;
        this.o = map2;
        this.u = arrayList;
        this.x = new zadc();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.c.zaf((GoogleApiClient.ConnectionCallbacks) it.next());
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            this.c.zag((GoogleApiClient.OnConnectionFailedListener) it2.next());
        }
        this.q = clientSettings;
        this.s = abstractClientBuilder;
    }

    public static String d(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNKNOWN" : "SIGN_IN_MODE_NONE" : "SIGN_IN_MODE_OPTIONAL" : "SIGN_IN_MODE_REQUIRED";
    }

    public static /* bridge */ /* synthetic */ void f(zabe zabeVar) {
        zabeVar.b.lock();
        try {
            if (zabeVar.i) {
                zabeVar.k();
            }
        } finally {
            zabeVar.b.unlock();
        }
    }

    public static /* bridge */ /* synthetic */ void g(zabe zabeVar) {
        zabeVar.b.lock();
        try {
            if (zabeVar.h()) {
                zabeVar.k();
            }
        } finally {
            zabeVar.b.unlock();
        }
    }

    public static int zad(Iterable iterable, boolean z) {
        Iterator it = iterable.iterator();
        boolean z2 = false;
        boolean z3 = false;
        while (it.hasNext()) {
            Api.Client client = (Api.Client) it.next();
            z2 |= client.requiresSignIn();
            z3 |= client.providesSignIn();
        }
        if (z2) {
            return (z3 && z) ? 2 : 1;
        }
        return 3;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult blockingConnect() {
        boolean z = true;
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.b.lock();
        try {
            if (this.e >= 0) {
                if (this.v == null) {
                    z = false;
                }
                Preconditions.checkState(z, "Sign-in mode should have been set explicitly by auto-manage.");
            } else {
                Integer num = this.v;
                if (num == null) {
                    this.v = Integer.valueOf(zad(this.o.values(), false));
                } else if (num.intValue() == 2) {
                    throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
            }
            i(((Integer) Preconditions.checkNotNull(this.v)).intValue());
            this.c.zab();
            return ((zaca) Preconditions.checkNotNull(this.d)).zab();
        } finally {
            this.b.unlock();
        }
    }

    public final String c() {
        StringWriter stringWriter = new StringWriter();
        dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final PendingResult<Status> clearDefaultAccountAndReconnect() {
        Preconditions.checkState(isConnected(), "GoogleApiClient is not connected yet.");
        Integer num = this.v;
        boolean z = true;
        if (num != null && num.intValue() == 2) {
            z = false;
        }
        Preconditions.checkState(z, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        StatusPendingResult statusPendingResult = new StatusPendingResult(this);
        if (this.o.containsKey(Common.CLIENT_KEY)) {
            j(this, statusPendingResult, false);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            s sVar = new s(this, atomicReference, statusPendingResult);
            t tVar = new t(this, statusPendingResult);
            GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this.f);
            builder.addApi(Common.API);
            builder.addConnectionCallbacks(sVar);
            builder.addOnConnectionFailedListener(tVar);
            builder.setHandler(this.l);
            GoogleApiClient build = builder.build();
            atomicReference.set(build);
            build.connect();
        }
        return statusPendingResult;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void connect() {
        this.b.lock();
        try {
            int i = 2;
            boolean z = false;
            if (this.e >= 0) {
                Preconditions.checkState(this.v != null, "Sign-in mode should have been set explicitly by auto-manage.");
            } else {
                Integer num = this.v;
                if (num == null) {
                    this.v = Integer.valueOf(zad(this.o.values(), false));
                } else if (num.intValue() == 2) {
                    throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
            }
            int intValue = ((Integer) Preconditions.checkNotNull(this.v)).intValue();
            this.b.lock();
            if (intValue == 3 || intValue == 1) {
                i = intValue;
            } else if (intValue != 2) {
                i = intValue;
                Preconditions.checkArgument(z, "Illegal sign-in mode: " + i);
                i(i);
                k();
                this.b.unlock();
            }
            z = true;
            Preconditions.checkArgument(z, "Illegal sign-in mode: " + i);
            i(i);
            k();
            this.b.unlock();
        } finally {
            this.b.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void disconnect() {
        Lock lock;
        this.b.lock();
        try {
            this.x.zab();
            zaca zacaVar = this.d;
            if (zacaVar != null) {
                zacaVar.zar();
            }
            this.t.zab();
            for (BaseImplementation.ApiMethodImpl apiMethodImpl : this.h) {
                apiMethodImpl.zan(null);
                apiMethodImpl.cancel();
            }
            this.h.clear();
            if (this.d == null) {
                lock = this.b;
            } else {
                h();
                this.c.zaa();
                lock = this.b;
            }
            lock.unlock();
        } catch (Throwable th) {
            this.b.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void dump(String str, @Nullable FileDescriptor fileDescriptor, PrintWriter printWriter, @Nullable String[] strArr) {
        printWriter.append((CharSequence) str).append("mContext=").println(this.f);
        printWriter.append((CharSequence) str).append("mResuming=").print(this.i);
        printWriter.append(" mWorkQueue.size()=").print(this.h.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.x.f8309a.size());
        zaca zacaVar = this.d;
        if (zacaVar != null) {
            zacaVar.zas(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        Lock lock;
        Api<?> api = t.getApi();
        boolean containsKey = this.o.containsKey(t.getClientKey());
        String zad = api != null ? api.zad() : "the API";
        Preconditions.checkArgument(containsKey, "GoogleApiClient is not configured to use " + zad + " required for this call.");
        this.b.lock();
        try {
            zaca zacaVar = this.d;
            if (zacaVar == null) {
                this.h.add(t);
                lock = this.b;
            } else {
                t = (T) zacaVar.zae(t);
                lock = this.b;
            }
            lock.unlock();
            return t;
        } catch (Throwable th) {
            this.b.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        Lock lock;
        Api<?> api = t.getApi();
        boolean containsKey = this.o.containsKey(t.getClientKey());
        String zad = api != null ? api.zad() : "the API";
        Preconditions.checkArgument(containsKey, "GoogleApiClient is not configured to use " + zad + " required for this call.");
        this.b.lock();
        try {
            zaca zacaVar = this.d;
            if (zacaVar != null) {
                if (this.i) {
                    this.h.add(t);
                    while (!this.h.isEmpty()) {
                        BaseImplementation.ApiMethodImpl apiMethodImpl = (BaseImplementation.ApiMethodImpl) this.h.remove();
                        this.x.a(apiMethodImpl);
                        apiMethodImpl.setFailedResult(Status.RESULT_INTERNAL_ERROR);
                    }
                    lock = this.b;
                } else {
                    t = (T) zacaVar.zaf(t);
                    lock = this.b;
                }
                lock.unlock();
                return t;
            }
            throw new IllegalStateException("GoogleApiClient is not connected yet.");
        } catch (Throwable th) {
            this.b.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    @NonNull
    public final <C extends Api.Client> C getClient(@NonNull Api.AnyClientKey<C> anyClientKey) {
        C c = (C) this.o.get(anyClientKey);
        Preconditions.checkNotNull(c, "Appropriate Api was not requested.");
        return c;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    @NonNull
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        ConnectionResult connectionResult;
        Lock lock;
        this.b.lock();
        try {
            if (!isConnected() && !this.i) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            }
            if (this.o.containsKey(api.zab())) {
                ConnectionResult zad = ((zaca) Preconditions.checkNotNull(this.d)).zad(api);
                if (zad == null) {
                    if (this.i) {
                        connectionResult = ConnectionResult.RESULT_SUCCESS;
                        lock = this.b;
                    } else {
                        Log.w("GoogleApiClientImpl", c());
                        String zad2 = api.zad();
                        Log.wtf("GoogleApiClientImpl", zad2 + " requested in getConnectionResult is not connected but is not present in the failed  connections map", new Exception());
                        connectionResult = new ConnectionResult(8, null);
                        lock = this.b;
                    }
                    lock.unlock();
                    return connectionResult;
                }
                return zad;
            }
            String zad3 = api.zad();
            throw new IllegalArgumentException(zad3 + " was never registered with GoogleApiClient");
        } finally {
            this.b.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Context getContext() {
        return this.f;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Looper getLooper() {
        return this.g;
    }

    @GuardedBy("mLock")
    public final boolean h() {
        if (this.i) {
            this.i = false;
            this.l.removeMessages(2);
            this.l.removeMessages(1);
            zabx zabxVar = this.n;
            if (zabxVar != null) {
                zabxVar.zab();
                this.n = null;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean hasApi(@NonNull Api<?> api) {
        return this.o.containsKey(api.zab());
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean hasConnectedApi(@NonNull Api<?> api) {
        Api.Client client;
        return isConnected() && (client = (Api.Client) this.o.get(api.zab())) != null && client.isConnected();
    }

    public final void i(int i) {
        Integer num = this.v;
        if (num == null) {
            this.v = Integer.valueOf(i);
        } else if (num.intValue() != i) {
            int intValue = this.v.intValue();
            throw new IllegalStateException("Cannot use sign-in mode: " + d(i) + ". Mode was already set to " + d(intValue));
        }
        if (this.d != null) {
            return;
        }
        boolean z = false;
        boolean z2 = false;
        for (Api.Client client : this.o.values()) {
            z |= client.requiresSignIn();
            z2 |= client.providesSignIn();
        }
        int intValue2 = this.v.intValue();
        if (intValue2 != 1) {
            if (intValue2 == 2 && z) {
                this.d = b.g(this.f, this, this.b, this.g, this.m, this.o, this.q, this.r, this.s, this.u);
                return;
            }
        } else if (!z) {
            throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
        } else {
            if (z2) {
                throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
        }
        this.d = new zabi(this.f, this, this.b, this.g, this.m, this.o, this.q, this.r, this.s, this.u, this);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnected() {
        zaca zacaVar = this.d;
        return zacaVar != null && zacaVar.zaw();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnecting() {
        zaca zacaVar = this.d;
        return zacaVar != null && zacaVar.zax();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.c.zaj(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.c.zak(onConnectionFailedListener);
    }

    public final void j(GoogleApiClient googleApiClient, StatusPendingResult statusPendingResult, boolean z) {
        Common.zaa.zaa(googleApiClient).setResultCallback(new u(this, statusPendingResult, z, googleApiClient));
    }

    @GuardedBy("mLock")
    public final void k() {
        this.c.zab();
        ((zaca) Preconditions.checkNotNull(this.d)).zaq();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        zaca zacaVar = this.d;
        return zacaVar != null && zacaVar.zay(signInConnectionListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void maybeSignOut() {
        zaca zacaVar = this.d;
        if (zacaVar != null) {
            zacaVar.zau();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void reconnect() {
        disconnect();
        connect();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.c.zaf(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.c.zag(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <L> ListenerHolder<L> registerListener(@NonNull L l) {
        this.b.lock();
        try {
            return this.t.zaa(l, this.g, "NO_TYPE");
        } finally {
            this.b.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void stopAutoManage(@NonNull FragmentActivity fragmentActivity) {
        LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity) fragmentActivity);
        if (this.e >= 0) {
            zak.zaa(lifecycleActivity).zae(this.e);
            return;
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.c.zah(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.c.zai(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    @GuardedBy("mLock")
    public final void zaa(ConnectionResult connectionResult) {
        if (!this.m.isPlayServicesPossiblyUpdating(this.f, connectionResult.getErrorCode())) {
            h();
        }
        if (this.i) {
            return;
        }
        this.c.zac(connectionResult);
        this.c.zaa();
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    @GuardedBy("mLock")
    public final void zab(@Nullable Bundle bundle) {
        while (!this.h.isEmpty()) {
            execute((BaseImplementation.ApiMethodImpl) this.h.remove());
        }
        this.c.zad(bundle);
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    @GuardedBy("mLock")
    public final void zac(int i, boolean z) {
        if (i == 1) {
            if (!z && !this.i) {
                this.i = true;
                if (this.n == null && !ClientLibraryUtils.isPackageSide()) {
                    try {
                        this.n = this.m.zac(this.f.getApplicationContext(), new w(this));
                    } catch (SecurityException unused) {
                    }
                }
                v vVar = this.l;
                vVar.sendMessageDelayed(vVar.obtainMessage(1), this.j);
                v vVar2 = this.l;
                vVar2.sendMessageDelayed(vVar2.obtainMessage(2), this.k);
            }
            i = 1;
        }
        for (BasePendingResult basePendingResult : (BasePendingResult[]) this.x.f8309a.toArray(new BasePendingResult[0])) {
            basePendingResult.forceFailureUnlessReady(zadc.zaa);
        }
        this.c.zae(i);
        this.c.zaa();
        if (i == 2) {
            k();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zao(zada zadaVar) {
        this.b.lock();
        try {
            if (this.w == null) {
                this.w = new HashSet();
            }
            this.w.add(zadaVar);
        } finally {
            this.b.unlock();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0041, code lost:
        if (r3 == false) goto L17;
     */
    @Override // com.google.android.gms.common.api.GoogleApiClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zap(com.google.android.gms.common.api.internal.zada r3) {
        /*
            r2 = this;
            java.util.concurrent.locks.Lock r0 = r2.b
            r0.lock()
            java.util.Set r0 = r2.w     // Catch: java.lang.Throwable -> L57
            java.lang.String r1 = "GoogleApiClientImpl"
            if (r0 != 0) goto L16
            java.lang.Exception r3 = new java.lang.Exception     // Catch: java.lang.Throwable -> L57
            r3.<init>()     // Catch: java.lang.Throwable -> L57
            java.lang.String r0 = "Attempted to remove pending transform when no transforms are registered."
            android.util.Log.wtf(r1, r0, r3)     // Catch: java.lang.Throwable -> L57
            goto L4a
        L16:
            boolean r3 = r0.remove(r3)     // Catch: java.lang.Throwable -> L57
            if (r3 != 0) goto L27
            java.lang.Exception r3 = new java.lang.Exception     // Catch: java.lang.Throwable -> L57
            r3.<init>()     // Catch: java.lang.Throwable -> L57
            java.lang.String r0 = "Failed to remove pending transform - this may lead to memory leaks!"
            android.util.Log.wtf(r1, r0, r3)     // Catch: java.lang.Throwable -> L57
            goto L4a
        L27:
            java.util.concurrent.locks.Lock r3 = r2.b     // Catch: java.lang.Throwable -> L57
            r3.lock()     // Catch: java.lang.Throwable -> L57
            java.util.Set r3 = r2.w     // Catch: java.lang.Throwable -> L50
            if (r3 != 0) goto L36
            java.util.concurrent.locks.Lock r3 = r2.b     // Catch: java.lang.Throwable -> L57
            r3.unlock()     // Catch: java.lang.Throwable -> L57
            goto L43
        L36:
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> L50
            r3 = r3 ^ 1
            java.util.concurrent.locks.Lock r0 = r2.b     // Catch: java.lang.Throwable -> L57
            r0.unlock()     // Catch: java.lang.Throwable -> L57
            if (r3 != 0) goto L4a
        L43:
            com.google.android.gms.common.api.internal.zaca r3 = r2.d     // Catch: java.lang.Throwable -> L57
            if (r3 == 0) goto L4a
            r3.zat()     // Catch: java.lang.Throwable -> L57
        L4a:
            java.util.concurrent.locks.Lock r3 = r2.b
            r3.unlock()
            return
        L50:
            r3 = move-exception
            java.util.concurrent.locks.Lock r0 = r2.b     // Catch: java.lang.Throwable -> L57
            r0.unlock()     // Catch: java.lang.Throwable -> L57
            throw r3     // Catch: java.lang.Throwable -> L57
        L57:
            r3 = move-exception
            java.util.concurrent.locks.Lock r0 = r2.b
            r0.unlock()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zabe.zap(com.google.android.gms.common.api.internal.zada):void");
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        Preconditions.checkNotNull(timeUnit, "TimeUnit must not be null");
        this.b.lock();
        try {
            Integer num = this.v;
            if (num == null) {
                this.v = Integer.valueOf(zad(this.o.values(), false));
            } else if (num.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            i(((Integer) Preconditions.checkNotNull(this.v)).intValue());
            this.c.zab();
            return ((zaca) Preconditions.checkNotNull(this.d)).zac(j, timeUnit);
        } finally {
            this.b.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void connect(int i) {
        this.b.lock();
        boolean z = true;
        if (i != 3 && i != 1) {
            if (i == 2) {
                i = 2;
            } else {
                z = false;
            }
        }
        try {
            Preconditions.checkArgument(z, "Illegal sign-in mode: " + i);
            i(i);
            k();
        } finally {
            this.b.unlock();
        }
    }
}
