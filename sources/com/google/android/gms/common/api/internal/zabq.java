package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.work.PeriodicWorkRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
/* loaded from: classes6.dex */
public final class zabq implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zau {
    @NotOnlyInitialized
    public final Api.Client b;
    public final ApiKey c;
    public final zaad d;
    public final int g;
    @Nullable
    public final zact h;
    public boolean i;
    public final /* synthetic */ GoogleApiManager m;

    /* renamed from: a */
    public final Queue f8303a = new LinkedList();
    public final Set e = new HashSet();
    public final Map f = new HashMap();
    public final List j = new ArrayList();
    @Nullable
    public ConnectionResult k = null;
    public int l = 0;

    @WorkerThread
    public zabq(GoogleApiManager googleApiManager, GoogleApi googleApi) {
        Handler handler;
        Context context;
        Handler handler2;
        this.m = googleApiManager;
        handler = googleApiManager.w;
        Api.Client zab = googleApi.zab(handler.getLooper(), this);
        this.b = zab;
        this.c = googleApi.getApiKey();
        this.d = new zaad();
        this.g = googleApi.zaa();
        if (!zab.requiresSignIn()) {
            this.h = null;
            return;
        }
        context = googleApiManager.n;
        handler2 = googleApiManager.w;
        this.h = googleApi.zac(context, handler2);
    }

    public static /* bridge */ /* synthetic */ ApiKey p(zabq zabqVar) {
        return zabqVar.c;
    }

    public static /* bridge */ /* synthetic */ void q(zabq zabqVar, Status status) {
        zabqVar.c(status);
    }

    public static /* bridge */ /* synthetic */ void t(zabq zabqVar, e0 e0Var) {
        if (zabqVar.j.contains(e0Var) && !zabqVar.i) {
            if (zabqVar.b.isConnected()) {
                zabqVar.e();
            } else {
                zabqVar.zao();
            }
        }
    }

    public static /* bridge */ /* synthetic */ void u(zabq zabqVar, e0 e0Var) {
        Handler handler;
        Handler handler2;
        Feature feature;
        Feature[] zab;
        if (zabqVar.j.remove(e0Var)) {
            handler = zabqVar.m.w;
            handler.removeMessages(15, e0Var);
            handler2 = zabqVar.m.w;
            handler2.removeMessages(16, e0Var);
            feature = e0Var.b;
            ArrayList arrayList = new ArrayList(zabqVar.f8303a.size());
            for (zai zaiVar : zabqVar.f8303a) {
                if ((zaiVar instanceof zac) && (zab = ((zac) zaiVar).zab(zabqVar)) != null && ArrayUtils.contains(zab, feature)) {
                    arrayList.add(zaiVar);
                }
            }
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zai zaiVar2 = (zai) arrayList.get(i);
                zabqVar.f8303a.remove(zaiVar2);
                zaiVar2.zae(new UnsupportedApiCallException(feature));
            }
        }
    }

    public static /* bridge */ /* synthetic */ boolean w(zabq zabqVar, boolean z) {
        return zabqVar.m(false);
    }

    @Nullable
    @WorkerThread
    public final Feature a(@Nullable Feature[] featureArr) {
        if (featureArr != null && featureArr.length != 0) {
            Feature[] availableFeatures = this.b.getAvailableFeatures();
            if (availableFeatures == null) {
                availableFeatures = new Feature[0];
            }
            ArrayMap arrayMap = new ArrayMap(availableFeatures.length);
            for (Feature feature : availableFeatures) {
                arrayMap.put(feature.getName(), Long.valueOf(feature.getVersion()));
            }
            for (Feature feature2 : featureArr) {
                Long l = (Long) arrayMap.get(feature2.getName());
                if (l == null || l.longValue() < feature2.getVersion()) {
                    return feature2;
                }
            }
        }
        return null;
    }

    @WorkerThread
    public final void b(ConnectionResult connectionResult) {
        for (zal zalVar : this.e) {
            zalVar.zac(this.c, connectionResult, Objects.equal(connectionResult, ConnectionResult.RESULT_SUCCESS) ? this.b.getEndpointPackageName() : null);
        }
        this.e.clear();
    }

    @WorkerThread
    public final void c(Status status) {
        Handler handler;
        handler = this.m.w;
        Preconditions.checkHandlerThread(handler);
        d(status, null, false);
    }

    @WorkerThread
    public final void d(@Nullable Status status, @Nullable Exception exc, boolean z) {
        Handler handler;
        handler = this.m.w;
        Preconditions.checkHandlerThread(handler);
        if ((status == null) != (exc == null)) {
            Iterator it = this.f8303a.iterator();
            while (it.hasNext()) {
                zai zaiVar = (zai) it.next();
                if (!z || zaiVar.zac == 2) {
                    if (status != null) {
                        zaiVar.zad(status);
                    } else {
                        zaiVar.zae(exc);
                    }
                    it.remove();
                }
            }
            return;
        }
        throw new IllegalArgumentException("Status XOR exception should be null");
    }

    @WorkerThread
    public final void e() {
        ArrayList arrayList = new ArrayList(this.f8303a);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            zai zaiVar = (zai) arrayList.get(i);
            if (!this.b.isConnected()) {
                return;
            }
            if (k(zaiVar)) {
                this.f8303a.remove(zaiVar);
            }
        }
    }

    @WorkerThread
    public final void f() {
        zan();
        b(ConnectionResult.RESULT_SUCCESS);
        j();
        Iterator it = this.f.values().iterator();
        while (it.hasNext()) {
            zaci zaciVar = (zaci) it.next();
            if (a(zaciVar.zaa.getRequiredFeatures()) != null) {
                it.remove();
            } else {
                try {
                    zaciVar.zaa.registerListener(this.b, new TaskCompletionSource<>());
                } catch (DeadObjectException unused) {
                    onConnectionSuspended(3);
                    this.b.disconnect("DeadObjectException thrown while calling register listener method.");
                } catch (RemoteException unused2) {
                    it.remove();
                }
            }
        }
        e();
        h();
    }

    @WorkerThread
    public final void g(int i) {
        Handler handler;
        Handler handler2;
        long j;
        Handler handler3;
        Handler handler4;
        long j2;
        com.google.android.gms.common.internal.zal zalVar;
        zan();
        this.i = true;
        this.d.e(i, this.b.getLastDisconnectMessage());
        GoogleApiManager googleApiManager = this.m;
        handler = googleApiManager.w;
        handler2 = googleApiManager.w;
        Message obtain = Message.obtain(handler2, 9, this.c);
        j = this.m.h;
        handler.sendMessageDelayed(obtain, j);
        GoogleApiManager googleApiManager2 = this.m;
        handler3 = googleApiManager2.w;
        handler4 = googleApiManager2.w;
        Message obtain2 = Message.obtain(handler4, 11, this.c);
        j2 = this.m.i;
        handler3.sendMessageDelayed(obtain2, j2);
        zalVar = this.m.p;
        zalVar.zac();
        for (zaci zaciVar : this.f.values()) {
            zaciVar.zac.run();
        }
    }

    public final void h() {
        Handler handler;
        Handler handler2;
        Handler handler3;
        long j;
        handler = this.m.w;
        handler.removeMessages(12, this.c);
        GoogleApiManager googleApiManager = this.m;
        handler2 = googleApiManager.w;
        handler3 = googleApiManager.w;
        Message obtainMessage = handler3.obtainMessage(12, this.c);
        j = this.m.j;
        handler2.sendMessageDelayed(obtainMessage, j);
    }

    @WorkerThread
    public final void i(zai zaiVar) {
        zaiVar.zag(this.d, zaz());
        try {
            zaiVar.zaf(this);
        } catch (DeadObjectException unused) {
            onConnectionSuspended(1);
            this.b.disconnect("DeadObjectException thrown while running ApiCallRunner.");
        }
    }

    @WorkerThread
    public final void j() {
        Handler handler;
        Handler handler2;
        if (this.i) {
            handler = this.m.w;
            handler.removeMessages(11, this.c);
            handler2 = this.m.w;
            handler2.removeMessages(9, this.c);
            this.i = false;
        }
    }

    @WorkerThread
    public final boolean k(zai zaiVar) {
        boolean z;
        Handler handler;
        Handler handler2;
        long j;
        Handler handler3;
        Handler handler4;
        long j2;
        Handler handler5;
        Handler handler6;
        Handler handler7;
        long j3;
        if (!(zaiVar instanceof zac)) {
            i(zaiVar);
            return true;
        }
        zac zacVar = (zac) zaiVar;
        Feature a2 = a(zacVar.zab(this));
        if (a2 == null) {
            i(zaiVar);
            return true;
        }
        String name = this.b.getClass().getName();
        String name2 = a2.getName();
        long version = a2.getVersion();
        Log.w("GoogleApiManager", name + " could not execute call because it requires feature (" + name2 + ", " + version + ").");
        z = this.m.x;
        if (z && zacVar.zaa(this)) {
            e0 e0Var = new e0(this.c, a2, null);
            int indexOf = this.j.indexOf(e0Var);
            if (indexOf >= 0) {
                e0 e0Var2 = (e0) this.j.get(indexOf);
                handler5 = this.m.w;
                handler5.removeMessages(15, e0Var2);
                GoogleApiManager googleApiManager = this.m;
                handler6 = googleApiManager.w;
                handler7 = googleApiManager.w;
                Message obtain = Message.obtain(handler7, 15, e0Var2);
                j3 = this.m.h;
                handler6.sendMessageDelayed(obtain, j3);
                return false;
            }
            this.j.add(e0Var);
            GoogleApiManager googleApiManager2 = this.m;
            handler = googleApiManager2.w;
            handler2 = googleApiManager2.w;
            Message obtain2 = Message.obtain(handler2, 15, e0Var);
            j = this.m.h;
            handler.sendMessageDelayed(obtain2, j);
            GoogleApiManager googleApiManager3 = this.m;
            handler3 = googleApiManager3.w;
            handler4 = googleApiManager3.w;
            Message obtain3 = Message.obtain(handler4, 16, e0Var);
            j2 = this.m.i;
            handler3.sendMessageDelayed(obtain3, j2);
            ConnectionResult connectionResult = new ConnectionResult(2, null);
            if (l(connectionResult)) {
                return false;
            }
            this.m.d(connectionResult, this.g);
            return false;
        }
        zacVar.zae(new UnsupportedApiCallException(a2));
        return true;
    }

    @WorkerThread
    public final boolean l(@NonNull ConnectionResult connectionResult) {
        Object obj;
        zaae zaaeVar;
        Set set;
        zaae zaaeVar2;
        obj = GoogleApiManager.z;
        synchronized (obj) {
            GoogleApiManager googleApiManager = this.m;
            zaaeVar = googleApiManager.t;
            if (zaaeVar != null) {
                set = googleApiManager.u;
                if (set.contains(this.c)) {
                    zaaeVar2 = this.m.t;
                    zaaeVar2.zah(connectionResult, this.g);
                    return true;
                }
            }
            return false;
        }
    }

    @WorkerThread
    public final boolean m(boolean z) {
        Handler handler;
        handler = this.m.w;
        Preconditions.checkHandlerThread(handler);
        if (this.b.isConnected() && this.f.size() == 0) {
            if (!this.d.f()) {
                this.b.disconnect("Timing out service connection.");
                return true;
            }
            if (z) {
                h();
            }
            return false;
        }
        return false;
    }

    @WorkerThread
    public final int n() {
        return this.l;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        Handler handler;
        Handler handler2;
        Looper myLooper = Looper.myLooper();
        handler = this.m.w;
        if (myLooper == handler.getLooper()) {
            f();
            return;
        }
        handler2 = this.m.w;
        handler2.post(new a0(this));
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    @WorkerThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        zar(connectionResult, null);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        Handler handler;
        Handler handler2;
        Looper myLooper = Looper.myLooper();
        handler = this.m.w;
        if (myLooper == handler.getLooper()) {
            g(i);
            return;
        }
        handler2 = this.m.w;
        handler2.post(new b0(this, i));
    }

    @WorkerThread
    public final void v() {
        this.l++;
    }

    public final boolean x() {
        return this.b.isConnected();
    }

    @WorkerThread
    public final boolean zaA() {
        return m(true);
    }

    @Override // com.google.android.gms.common.api.internal.zau
    public final void zaa(ConnectionResult connectionResult, Api api, boolean z) {
        throw null;
    }

    public final int zab() {
        return this.g;
    }

    @Nullable
    @WorkerThread
    public final ConnectionResult zad() {
        Handler handler;
        handler = this.m.w;
        Preconditions.checkHandlerThread(handler);
        return this.k;
    }

    public final Api.Client zaf() {
        return this.b;
    }

    public final Map zah() {
        return this.f;
    }

    @WorkerThread
    public final void zan() {
        Handler handler;
        handler = this.m.w;
        Preconditions.checkHandlerThread(handler);
        this.k = null;
    }

    @WorkerThread
    public final void zao() {
        Handler handler;
        com.google.android.gms.common.internal.zal zalVar;
        Context context;
        handler = this.m.w;
        Preconditions.checkHandlerThread(handler);
        if (this.b.isConnected() || this.b.isConnecting()) {
            return;
        }
        try {
            GoogleApiManager googleApiManager = this.m;
            zalVar = googleApiManager.p;
            context = googleApiManager.n;
            int zab = zalVar.zab(context, this.b);
            if (zab != 0) {
                ConnectionResult connectionResult = new ConnectionResult(zab, null);
                String name = this.b.getClass().getName();
                String obj = connectionResult.toString();
                Log.w("GoogleApiManager", "The service for " + name + " is not available: " + obj);
                zar(connectionResult, null);
                return;
            }
            GoogleApiManager googleApiManager2 = this.m;
            Api.Client client = this.b;
            g0 g0Var = new g0(googleApiManager2, client, this.c);
            if (client.requiresSignIn()) {
                ((zact) Preconditions.checkNotNull(this.h)).zae(g0Var);
            }
            try {
                this.b.connect(g0Var);
            } catch (SecurityException e) {
                zar(new ConnectionResult(10), e);
            }
        } catch (IllegalStateException e2) {
            zar(new ConnectionResult(10), e2);
        }
    }

    @WorkerThread
    public final void zap(zai zaiVar) {
        Handler handler;
        handler = this.m.w;
        Preconditions.checkHandlerThread(handler);
        if (this.b.isConnected()) {
            if (k(zaiVar)) {
                h();
                return;
            } else {
                this.f8303a.add(zaiVar);
                return;
            }
        }
        this.f8303a.add(zaiVar);
        ConnectionResult connectionResult = this.k;
        if (connectionResult != null && connectionResult.hasResolution()) {
            zar(this.k, null);
        } else {
            zao();
        }
    }

    @WorkerThread
    public final void zar(@NonNull ConnectionResult connectionResult, @Nullable Exception exc) {
        Handler handler;
        com.google.android.gms.common.internal.zal zalVar;
        boolean z;
        Status e;
        Status e2;
        Status e3;
        Handler handler2;
        Handler handler3;
        long j;
        Handler handler4;
        Status status;
        Handler handler5;
        Handler handler6;
        handler = this.m.w;
        Preconditions.checkHandlerThread(handler);
        zact zactVar = this.h;
        if (zactVar != null) {
            zactVar.zaf();
        }
        zan();
        zalVar = this.m.p;
        zalVar.zac();
        b(connectionResult);
        if ((this.b instanceof com.google.android.gms.common.internal.service.zap) && connectionResult.getErrorCode() != 24) {
            this.m.k = true;
            GoogleApiManager googleApiManager = this.m;
            handler5 = googleApiManager.w;
            handler6 = googleApiManager.w;
            handler5.sendMessageDelayed(handler6.obtainMessage(19), PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
        }
        if (connectionResult.getErrorCode() == 4) {
            status = GoogleApiManager.y;
            c(status);
        } else if (this.f8303a.isEmpty()) {
            this.k = connectionResult;
        } else if (exc != null) {
            handler4 = this.m.w;
            Preconditions.checkHandlerThread(handler4);
            d(null, exc, false);
        } else {
            z = this.m.x;
            if (z) {
                e2 = GoogleApiManager.e(this.c, connectionResult);
                d(e2, null, true);
                if (this.f8303a.isEmpty() || l(connectionResult) || this.m.d(connectionResult, this.g)) {
                    return;
                }
                if (connectionResult.getErrorCode() == 18) {
                    this.i = true;
                }
                if (this.i) {
                    GoogleApiManager googleApiManager2 = this.m;
                    handler2 = googleApiManager2.w;
                    handler3 = googleApiManager2.w;
                    Message obtain = Message.obtain(handler3, 9, this.c);
                    j = this.m.h;
                    handler2.sendMessageDelayed(obtain, j);
                    return;
                }
                e3 = GoogleApiManager.e(this.c, connectionResult);
                c(e3);
                return;
            }
            e = GoogleApiManager.e(this.c, connectionResult);
            c(e);
        }
    }

    @WorkerThread
    public final void zas(@NonNull ConnectionResult connectionResult) {
        Handler handler;
        handler = this.m.w;
        Preconditions.checkHandlerThread(handler);
        Api.Client client = this.b;
        String name = client.getClass().getName();
        String valueOf = String.valueOf(connectionResult);
        client.disconnect("onSignInFailed for " + name + " with " + valueOf);
        zar(connectionResult, null);
    }

    @WorkerThread
    public final void zat(zal zalVar) {
        Handler handler;
        handler = this.m.w;
        Preconditions.checkHandlerThread(handler);
        this.e.add(zalVar);
    }

    @WorkerThread
    public final void zau() {
        Handler handler;
        handler = this.m.w;
        Preconditions.checkHandlerThread(handler);
        if (this.i) {
            zao();
        }
    }

    @WorkerThread
    public final void zav() {
        Handler handler;
        handler = this.m.w;
        Preconditions.checkHandlerThread(handler);
        c(GoogleApiManager.zaa);
        this.d.zaf();
        for (ListenerHolder.ListenerKey listenerKey : (ListenerHolder.ListenerKey[]) this.f.keySet().toArray(new ListenerHolder.ListenerKey[0])) {
            zap(new zah(listenerKey, new TaskCompletionSource()));
        }
        b(new ConnectionResult(4));
        if (this.b.isConnected()) {
            this.b.onUserSignOut(new d0(this));
        }
    }

    @WorkerThread
    public final void zaw() {
        Handler handler;
        GoogleApiAvailability googleApiAvailability;
        Context context;
        Status status;
        handler = this.m.w;
        Preconditions.checkHandlerThread(handler);
        if (this.i) {
            j();
            GoogleApiManager googleApiManager = this.m;
            googleApiAvailability = googleApiManager.o;
            context = googleApiManager.n;
            if (googleApiAvailability.isGooglePlayServicesAvailable(context) == 18) {
                status = new Status(21, "Connection timed out waiting for Google Play services update to complete.");
            } else {
                status = new Status(22, "API failed to connect while resuming due to an unknown error.");
            }
            c(status);
            this.b.disconnect("Timing out connection while resuming.");
        }
    }

    public final boolean zaz() {
        return this.b.requiresSignIn();
    }
}
