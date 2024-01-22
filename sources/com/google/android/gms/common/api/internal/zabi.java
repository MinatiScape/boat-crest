package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
/* loaded from: classes6.dex */
public final class zabi implements zaca, zau {

    /* renamed from: a  reason: collision with root package name */
    public final Lock f8301a;
    public final Condition b;
    public final Context c;
    public final GoogleApiAvailabilityLight d;
    public final y e;
    public final Map f;
    @Nullable
    public final ClientSettings h;
    public final Map i;
    @Nullable
    public final Api.AbstractClientBuilder j;
    @NotOnlyInitialized
    public volatile zabf k;
    public int m;
    public final zabe n;
    public final zabz o;
    public final Map g = new HashMap();
    @Nullable
    public ConnectionResult l = null;

    public zabi(Context context, zabe zabeVar, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map map, @Nullable ClientSettings clientSettings, Map map2, @Nullable Api.AbstractClientBuilder abstractClientBuilder, ArrayList arrayList, zabz zabzVar) {
        this.c = context;
        this.f8301a = lock;
        this.d = googleApiAvailabilityLight;
        this.f = map;
        this.h = clientSettings;
        this.i = map2;
        this.j = abstractClientBuilder;
        this.n = zabeVar;
        this.o = zabzVar;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((zat) arrayList.get(i)).zaa(this);
        }
        this.e = new y(this, looper);
        this.b = lock.newCondition();
        this.k = new zaax(this);
    }

    public final void c() {
        this.f8301a.lock();
        try {
            this.n.h();
            this.k = new zaaj(this);
            this.k.zad();
            this.b.signalAll();
        } finally {
            this.f8301a.unlock();
        }
    }

    public final void d() {
        this.f8301a.lock();
        try {
            this.k = new zaaw(this, this.h, this.i, this.d, this.j, this.f8301a, this.c);
            this.k.zad();
            this.b.signalAll();
        } finally {
            this.f8301a.unlock();
        }
    }

    public final void e(@Nullable ConnectionResult connectionResult) {
        this.f8301a.lock();
        try {
            this.l = connectionResult;
            this.k = new zaax(this);
            this.k.zad();
            this.b.signalAll();
        } finally {
            this.f8301a.unlock();
        }
    }

    public final void f(x xVar) {
        this.e.sendMessage(this.e.obtainMessage(1, xVar));
    }

    public final void g(RuntimeException runtimeException) {
        this.e.sendMessage(this.e.obtainMessage(2, runtimeException));
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        this.f8301a.lock();
        try {
            this.k.zag(bundle);
        } finally {
            this.f8301a.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        this.f8301a.lock();
        try {
            this.k.zai(i);
        } finally {
            this.f8301a.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zau
    public final void zaa(@NonNull ConnectionResult connectionResult, @NonNull Api api, boolean z) {
        this.f8301a.lock();
        try {
            this.k.zah(connectionResult, api, z);
        } finally {
            this.f8301a.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final ConnectionResult zab() {
        zaq();
        while (this.k instanceof zaaw) {
            try {
                this.b.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        if (this.k instanceof zaaj) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.l;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final ConnectionResult zac(long j, TimeUnit timeUnit) {
        zaq();
        long nanos = timeUnit.toNanos(j);
        while (this.k instanceof zaaw) {
            if (nanos <= 0) {
                zar();
                return new ConnectionResult(14, null);
            }
            try {
                nanos = this.b.awaitNanos(nanos);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, null);
        }
        if (this.k instanceof zaaj) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.l;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @Nullable
    @GuardedBy("mLock")
    public final ConnectionResult zad(@NonNull Api api) {
        Api.AnyClientKey zab = api.zab();
        if (this.f.containsKey(zab)) {
            if (((Api.Client) this.f.get(zab)).isConnected()) {
                return ConnectionResult.RESULT_SUCCESS;
            }
            if (this.g.containsKey(zab)) {
                return (ConnectionResult) this.g.get(zab);
            }
            return null;
        }
        return null;
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final BaseImplementation.ApiMethodImpl zae(@NonNull BaseImplementation.ApiMethodImpl apiMethodImpl) {
        apiMethodImpl.zak();
        this.k.zaa(apiMethodImpl);
        return apiMethodImpl;
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final BaseImplementation.ApiMethodImpl zaf(@NonNull BaseImplementation.ApiMethodImpl apiMethodImpl) {
        apiMethodImpl.zak();
        return this.k.zab(apiMethodImpl);
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final void zaq() {
        this.k.zae();
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final void zar() {
        if (this.k.zaj()) {
            this.g.clear();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void zas(String str, @Nullable FileDescriptor fileDescriptor, PrintWriter printWriter, @Nullable String[] strArr) {
        String concat = String.valueOf(str).concat("  ");
        printWriter.append((CharSequence) str).append("mState=").println(this.k);
        for (Api api : this.i.keySet()) {
            printWriter.append((CharSequence) str).append((CharSequence) api.zad()).println(":");
            ((Api.Client) Preconditions.checkNotNull((Api.Client) this.f.get(api.zab()))).dump(concat, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("mLock")
    public final void zat() {
        if (this.k instanceof zaaj) {
            ((zaaj) this.k).b();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void zau() {
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final boolean zaw() {
        return this.k instanceof zaaj;
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final boolean zax() {
        return this.k instanceof zaaw;
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final boolean zay(SignInConnectionListener signInConnectionListener) {
        return false;
    }
}
