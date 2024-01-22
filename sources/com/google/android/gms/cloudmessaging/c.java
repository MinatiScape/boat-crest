package com.google.android.gms.cloudmessaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class c implements ServiceConnection {
    @GuardedBy("this")
    public int h;
    public final Messenger i;
    public l j;
    @GuardedBy("this")
    public final Queue<m<?>> k;
    @GuardedBy("this")
    public final SparseArray<m<?>> l;
    public final /* synthetic */ zze m;

    public c(zze zzeVar) {
        this.m = zzeVar;
        this.h = 0;
        this.i = new Messenger(new com.google.android.gms.internal.cloudmessaging.zze(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.android.gms.cloudmessaging.f
            public final c h;

            {
                this.h = this;
            }

            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.h.d(message);
            }
        }));
        this.k = new ArrayDeque();
        this.l = new SparseArray<>();
    }

    public final void a() {
        this.m.b.execute(new Runnable(this) { // from class: com.google.android.gms.cloudmessaging.g
            public final c h;

            {
                this.h = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                final m<?> poll;
                final c cVar = this.h;
                while (true) {
                    synchronized (cVar) {
                        if (cVar.h != 2) {
                            return;
                        }
                        if (cVar.k.isEmpty()) {
                            cVar.f();
                            return;
                        }
                        poll = cVar.k.poll();
                        cVar.l.put(poll.f8226a, poll);
                        cVar.m.b.schedule(new Runnable(cVar, poll) { // from class: com.google.android.gms.cloudmessaging.i
                            public final c h;
                            public final m i;

                            {
                                this.h = cVar;
                                this.i = poll;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.h.b(this.i.f8226a);
                            }
                        }, 30L, TimeUnit.SECONDS);
                    }
                    if (Log.isLoggable("MessengerIpcClient", 3)) {
                        String valueOf = String.valueOf(poll);
                        StringBuilder sb = new StringBuilder(valueOf.length() + 8);
                        sb.append("Sending ");
                        sb.append(valueOf);
                        Log.d("MessengerIpcClient", sb.toString());
                    }
                    Context context = cVar.m.f8232a;
                    Messenger messenger = cVar.i;
                    Message obtain = Message.obtain();
                    obtain.what = poll.c;
                    obtain.arg1 = poll.f8226a;
                    obtain.replyTo = messenger;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("oneWay", poll.d());
                    bundle.putString("pkg", context.getPackageName());
                    bundle.putBundle("data", poll.d);
                    obtain.setData(bundle);
                    try {
                        cVar.j.a(obtain);
                    } catch (RemoteException e) {
                        cVar.c(2, e.getMessage());
                    }
                }
            }
        });
    }

    public final synchronized void b(int i) {
        m<?> mVar = this.l.get(i);
        if (mVar != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i);
            Log.w("MessengerIpcClient", sb.toString());
            this.l.remove(i);
            mVar.b(new zzp(3, "Timed out waiting for response"));
            f();
        }
    }

    public final synchronized void c(int i, @Nullable String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", valueOf.length() != 0 ? "Disconnected: ".concat(valueOf) : new String("Disconnected: "));
        }
        int i2 = this.h;
        if (i2 == 0) {
            throw new IllegalStateException();
        }
        if (i2 != 1 && i2 != 2) {
            if (i2 == 3) {
                this.h = 4;
                return;
            } else if (i2 == 4) {
                return;
            } else {
                int i3 = this.h;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i3);
                throw new IllegalStateException(sb.toString());
            }
        }
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Unbinding service");
        }
        this.h = 4;
        ConnectionTracker.getInstance().unbindService(this.m.f8232a, this);
        zzp zzpVar = new zzp(i, str);
        for (m<?> mVar : this.k) {
            mVar.b(zzpVar);
        }
        this.k.clear();
        for (int i4 = 0; i4 < this.l.size(); i4++) {
            this.l.valueAt(i4).b(zzpVar);
        }
        this.l.clear();
    }

    public final boolean d(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Received response to request: ");
            sb.append(i);
            Log.d("MessengerIpcClient", sb.toString());
        }
        synchronized (this) {
            m<?> mVar = this.l.get(i);
            if (mVar == null) {
                StringBuilder sb2 = new StringBuilder(50);
                sb2.append("Received response for unknown request: ");
                sb2.append(i);
                Log.w("MessengerIpcClient", sb2.toString());
                return true;
            }
            this.l.remove(i);
            f();
            Bundle data = message.getData();
            if (data.getBoolean("unsupported", false)) {
                mVar.b(new zzp(4, "Not supported by GmsCore"));
            } else {
                mVar.a(data);
            }
            return true;
        }
    }

    public final synchronized boolean e(m<?> mVar) {
        int i = this.h;
        if (i == 0) {
            this.k.add(mVar);
            Preconditions.checkState(this.h == 0);
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Starting bind to GmsCore");
            }
            this.h = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            if (ConnectionTracker.getInstance().bindService(this.m.f8232a, intent, this, 1)) {
                this.m.b.schedule(new Runnable(this) { // from class: com.google.android.gms.cloudmessaging.e
                    public final c h;

                    {
                        this.h = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.h.g();
                    }
                }, 30L, TimeUnit.SECONDS);
            } else {
                c(0, "Unable to bind to service");
            }
            return true;
        } else if (i == 1) {
            this.k.add(mVar);
            return true;
        } else if (i == 2) {
            this.k.add(mVar);
            a();
            return true;
        } else {
            if (i != 3 && i != 4) {
                int i2 = this.h;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i2);
                throw new IllegalStateException(sb.toString());
            }
            return false;
        }
    }

    public final synchronized void f() {
        if (this.h == 2 && this.k.isEmpty() && this.l.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.h = 3;
            ConnectionTracker.getInstance().unbindService(this.m.f8232a, this);
        }
    }

    public final synchronized void g() {
        if (this.h == 1) {
            c(1, "Timed out while binding");
        }
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        this.m.b.execute(new Runnable(this, iBinder) { // from class: com.google.android.gms.cloudmessaging.h
            public final c h;
            public final IBinder i;

            {
                this.h = this;
                this.i = iBinder;
            }

            @Override // java.lang.Runnable
            public final void run() {
                c cVar = this.h;
                IBinder iBinder2 = this.i;
                synchronized (cVar) {
                    try {
                        if (iBinder2 == null) {
                            cVar.c(0, "Null service connection");
                            return;
                        }
                        try {
                            cVar.j = new l(iBinder2);
                            cVar.h = 2;
                            cVar.a();
                        } catch (RemoteException e) {
                            cVar.c(0, e.getMessage());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        this.m.b.execute(new Runnable(this) { // from class: com.google.android.gms.cloudmessaging.j
            public final c h;

            {
                this.h = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.h.c(2, "Service disconnected");
            }
        });
    }
}
