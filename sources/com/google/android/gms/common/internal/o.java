package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import androidx.annotation.Nullable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
/* loaded from: classes6.dex */
public final class o implements ServiceConnection, zzt {
    public final Map h = new HashMap();
    public int i = 2;
    public boolean j;
    @Nullable
    public IBinder k;
    public final zzo l;
    public ComponentName m;
    public final /* synthetic */ q n;

    public o(q qVar, zzo zzoVar) {
        this.n = qVar;
        this.l = zzoVar;
    }

    public final int a() {
        return this.i;
    }

    public final ComponentName b() {
        return this.m;
    }

    @Nullable
    public final IBinder c() {
        return this.k;
    }

    public final void d(ServiceConnection serviceConnection, ServiceConnection serviceConnection2, String str) {
        this.h.put(serviceConnection, serviceConnection2);
    }

    public final void e(String str, @Nullable Executor executor) {
        ConnectionTracker connectionTracker;
        Context context;
        Context context2;
        ConnectionTracker connectionTracker2;
        Context context3;
        Handler handler;
        Handler handler2;
        long j;
        this.i = 3;
        StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
        if (PlatformVersion.isAtLeastS()) {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(vmPolicy).permitUnsafeIntentLaunch().build());
        }
        try {
            q qVar = this.n;
            connectionTracker = qVar.j;
            context = qVar.g;
            zzo zzoVar = this.l;
            context2 = qVar.g;
            boolean zza = connectionTracker.zza(context, str, zzoVar.zzb(context2), this, 4225, executor);
            this.j = zza;
            if (zza) {
                handler = this.n.h;
                Message obtainMessage = handler.obtainMessage(1, this.l);
                handler2 = this.n.h;
                j = this.n.l;
                handler2.sendMessageDelayed(obtainMessage, j);
            } else {
                this.i = 2;
                try {
                    q qVar2 = this.n;
                    connectionTracker2 = qVar2.j;
                    context3 = qVar2.g;
                    connectionTracker2.unbindService(context3, this);
                } catch (IllegalArgumentException unused) {
                }
            }
        } finally {
            StrictMode.setVmPolicy(vmPolicy);
        }
    }

    public final void f(ServiceConnection serviceConnection, String str) {
        this.h.remove(serviceConnection);
    }

    public final void g(String str) {
        Handler handler;
        ConnectionTracker connectionTracker;
        Context context;
        handler = this.n.h;
        handler.removeMessages(1, this.l);
        q qVar = this.n;
        connectionTracker = qVar.j;
        context = qVar.g;
        connectionTracker.unbindService(context, this);
        this.j = false;
        this.i = 2;
    }

    public final boolean h(ServiceConnection serviceConnection) {
        return this.h.containsKey(serviceConnection);
    }

    public final boolean i() {
        return this.h.isEmpty();
    }

    public final boolean j() {
        return this.j;
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        onServiceDisconnected(componentName);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        HashMap hashMap;
        Handler handler;
        hashMap = this.n.f;
        synchronized (hashMap) {
            handler = this.n.h;
            handler.removeMessages(1, this.l);
            this.k = iBinder;
            this.m = componentName;
            for (ServiceConnection serviceConnection : this.h.values()) {
                serviceConnection.onServiceConnected(componentName, iBinder);
            }
            this.i = 1;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        HashMap hashMap;
        Handler handler;
        hashMap = this.n.f;
        synchronized (hashMap) {
            handler = this.n.h;
            handler.removeMessages(1, this.l);
            this.k = null;
            this.m = componentName;
            for (ServiceConnection serviceConnection : this.h.values()) {
                serviceConnection.onServiceDisconnected(componentName);
            }
            this.i = 2;
        }
    }
}
