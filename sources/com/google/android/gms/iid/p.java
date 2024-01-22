package com.google.android.gms.iid;

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
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.gcm.zzj;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class p implements ServiceConnection {
    @GuardedBy("this")
    public int h;
    public final Messenger i;
    public v j;
    @GuardedBy("this")
    public final Queue<w<?>> k;
    @GuardedBy("this")
    public final SparseArray<w<?>> l;
    public final /* synthetic */ zzr m;

    public p(zzr zzrVar) {
        this.m = zzrVar;
        this.h = 0;
        this.i = new Messenger(new zzj(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.android.gms.iid.q
            public final p h;

            {
                this.h = this;
            }

            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.h.b(message);
            }
        }));
        this.k = new ArrayDeque();
        this.l = new SparseArray<>();
    }

    public final synchronized void a(int i, String str) {
        Context context;
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
        ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
        context = this.m.f8496a;
        connectionTracker.unbindService(context, this);
        zzaa zzaaVar = new zzaa(i, str);
        for (w<?> wVar : this.k) {
            wVar.a(zzaaVar);
        }
        this.k.clear();
        for (int i4 = 0; i4 < this.l.size(); i4++) {
            this.l.valueAt(i4).a(zzaaVar);
        }
        this.l.clear();
    }

    public final boolean b(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Received response to request: ");
            sb.append(i);
            Log.d("MessengerIpcClient", sb.toString());
        }
        synchronized (this) {
            w<?> wVar = this.l.get(i);
            if (wVar == null) {
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
                wVar.a(new zzaa(4, "Not supported by GmsCore"));
            } else {
                wVar.b(data);
            }
            return true;
        }
    }

    public final synchronized boolean c(w wVar) {
        Context context;
        ScheduledExecutorService scheduledExecutorService;
        int i = this.h;
        if (i == 0) {
            this.k.add(wVar);
            Preconditions.checkState(this.h == 0);
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Starting bind to GmsCore");
            }
            this.h = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
            context = this.m.f8496a;
            if (connectionTracker.bindService(context, intent, this, 1)) {
                scheduledExecutorService = this.m.b;
                scheduledExecutorService.schedule(new Runnable(this) { // from class: com.google.android.gms.iid.s
                    public final p h;

                    {
                        this.h = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.h.g();
                    }
                }, 30L, TimeUnit.SECONDS);
            } else {
                a(0, "Unable to bind to service");
            }
            return true;
        } else if (i == 1) {
            this.k.add(wVar);
            return true;
        } else if (i == 2) {
            this.k.add(wVar);
            e();
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

    public final synchronized void d(int i) {
        w<?> wVar = this.l.get(i);
        if (wVar != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i);
            Log.w("MessengerIpcClient", sb.toString());
            this.l.remove(i);
            wVar.a(new zzaa(3, "Timed out waiting for response"));
            f();
        }
    }

    public final void e() {
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = this.m.b;
        scheduledExecutorService.execute(new Runnable(this) { // from class: com.google.android.gms.iid.t
            public final p h;

            {
                this.h = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ScheduledExecutorService scheduledExecutorService2;
                Context context;
                final p pVar = this.h;
                while (true) {
                    synchronized (pVar) {
                        if (pVar.h != 2) {
                            return;
                        }
                        if (pVar.k.isEmpty()) {
                            pVar.f();
                            return;
                        }
                        final w<?> poll = pVar.k.poll();
                        pVar.l.put(poll.f8491a, poll);
                        scheduledExecutorService2 = pVar.m.b;
                        scheduledExecutorService2.schedule(new Runnable(pVar, poll) { // from class: com.google.android.gms.iid.u
                            public final p h;
                            public final w i;

                            {
                                this.h = pVar;
                                this.i = poll;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.h.d(this.i.f8491a);
                            }
                        }, 30L, TimeUnit.SECONDS);
                        if (Log.isLoggable("MessengerIpcClient", 3)) {
                            String valueOf = String.valueOf(poll);
                            StringBuilder sb = new StringBuilder(valueOf.length() + 8);
                            sb.append("Sending ");
                            sb.append(valueOf);
                            Log.d("MessengerIpcClient", sb.toString());
                        }
                        context = pVar.m.f8496a;
                        Messenger messenger = pVar.i;
                        Message obtain = Message.obtain();
                        obtain.what = poll.c;
                        obtain.arg1 = poll.f8491a;
                        obtain.replyTo = messenger;
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("oneWay", poll.c());
                        bundle.putString("pkg", context.getPackageName());
                        bundle.putBundle("data", poll.d);
                        obtain.setData(bundle);
                        try {
                            v vVar = pVar.j;
                            Messenger messenger2 = vVar.f8490a;
                            if (messenger2 != null) {
                                messenger2.send(obtain);
                            } else {
                                MessengerCompat messengerCompat = vVar.b;
                                if (messengerCompat != null) {
                                    messengerCompat.send(obtain);
                                } else {
                                    throw new IllegalStateException("Both messengers are null");
                                    break;
                                }
                            }
                        } catch (RemoteException e) {
                            pVar.a(2, e.getMessage());
                        }
                    }
                }
            }
        });
    }

    public final synchronized void f() {
        Context context;
        if (this.h == 2 && this.k.isEmpty() && this.l.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.h = 3;
            ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
            context = this.m.f8496a;
            connectionTracker.unbindService(context, this);
        }
    }

    public final synchronized void g() {
        if (this.h == 1) {
            a(1, "Timed out while binding");
        }
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            a(0, "Null service connection");
            return;
        }
        try {
            this.j = new v(iBinder);
            this.h = 2;
            e();
        } catch (RemoteException e) {
            a(0, e.getMessage());
        }
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        a(2, "Service disconnected");
    }
}
