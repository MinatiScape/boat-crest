package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
/* loaded from: classes6.dex */
public final class zak implements Handler.Callback {
    @NotOnlyInitialized
    public final zaj h;
    public final Handler o;
    public final ArrayList i = new ArrayList();
    @VisibleForTesting
    public final ArrayList j = new ArrayList();
    public final ArrayList k = new ArrayList();
    public volatile boolean l = false;
    public final AtomicInteger m = new AtomicInteger(0);
    public boolean n = false;
    public final Object p = new Object();

    public zak(Looper looper, zaj zajVar) {
        this.h = zajVar;
        this.o = new com.google.android.gms.internal.base.zau(looper, this);
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) message.obj;
            synchronized (this.p) {
                if (this.l && this.h.isConnected() && this.i.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(null);
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle message: " + i, new Exception());
        return false;
    }

    public final void zaa() {
        this.l = false;
        this.m.incrementAndGet();
    }

    public final void zab() {
        this.l = true;
    }

    @VisibleForTesting
    public final void zac(ConnectionResult connectionResult) {
        Preconditions.checkHandlerThread(this.o, "onConnectionFailure must only be called on the Handler thread");
        this.o.removeMessages(1);
        synchronized (this.p) {
            ArrayList arrayList = new ArrayList(this.k);
            int i = this.m.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener) it.next();
                if (this.l && this.m.get() == i) {
                    if (this.k.contains(onConnectionFailedListener)) {
                        onConnectionFailedListener.onConnectionFailed(connectionResult);
                    }
                }
                return;
            }
        }
    }

    @VisibleForTesting
    public final void zad(@Nullable Bundle bundle) {
        Preconditions.checkHandlerThread(this.o, "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.p) {
            Preconditions.checkState(!this.n);
            this.o.removeMessages(1);
            this.n = true;
            Preconditions.checkState(this.j.isEmpty());
            ArrayList arrayList = new ArrayList(this.i);
            int i = this.m.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!this.l || !this.h.isConnected() || this.m.get() != i) {
                    break;
                } else if (!this.j.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.j.clear();
            this.n = false;
        }
    }

    @VisibleForTesting
    public final void zae(int i) {
        Preconditions.checkHandlerThread(this.o, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.o.removeMessages(1);
        synchronized (this.p) {
            this.n = true;
            ArrayList arrayList = new ArrayList(this.i);
            int i2 = this.m.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!this.l || this.m.get() != i2) {
                    break;
                } else if (this.i.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.j.clear();
            this.n = false;
        }
    }

    public final void zaf(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.p) {
            if (this.i.contains(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + valueOf + " is already registered");
            } else {
                this.i.add(connectionCallbacks);
            }
        }
        if (this.h.isConnected()) {
            Handler handler = this.o;
            handler.sendMessage(handler.obtainMessage(1, connectionCallbacks));
        }
    }

    public final void zag(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.p) {
            if (this.k.contains(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + valueOf + " is already registered");
            } else {
                this.k.add(onConnectionFailedListener);
            }
        }
    }

    public final void zah(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.p) {
            if (!this.i.remove(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + valueOf + " not found");
            } else if (this.n) {
                this.j.add(connectionCallbacks);
            }
        }
    }

    public final void zai(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.p) {
            if (!this.k.remove(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + valueOf + " not found");
            }
        }
    }

    public final boolean zaj(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        boolean contains;
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.p) {
            contains = this.i.contains(connectionCallbacks);
        }
        return contains;
    }

    public final boolean zak(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        boolean contains;
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.p) {
            contains = this.k.contains(onConnectionFailedListener);
        }
        return contains;
    }
}
