package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.util.GlideSuppliers;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public final class k {
    public static volatile k d;

    /* renamed from: a  reason: collision with root package name */
    public final c f2508a;
    @GuardedBy("this")
    public final Set<ConnectivityMonitor.ConnectivityListener> b = new HashSet();
    @GuardedBy("this")
    public boolean c;

    /* loaded from: classes2.dex */
    public class a implements GlideSuppliers.GlideSupplier<ConnectivityManager> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f2509a;

        public a(k kVar, Context context) {
            this.f2509a = context;
        }

        @Override // com.bumptech.glide.util.GlideSuppliers.GlideSupplier
        /* renamed from: a */
        public ConnectivityManager get() {
            return (ConnectivityManager) this.f2509a.getSystemService("connectivity");
        }
    }

    /* loaded from: classes2.dex */
    public class b implements ConnectivityMonitor.ConnectivityListener {
        public b() {
        }

        @Override // com.bumptech.glide.manager.ConnectivityMonitor.ConnectivityListener
        public void onConnectivityChanged(boolean z) {
            ArrayList<ConnectivityMonitor.ConnectivityListener> arrayList;
            Util.assertMainThread();
            synchronized (k.this) {
                arrayList = new ArrayList(k.this.b);
            }
            for (ConnectivityMonitor.ConnectivityListener connectivityListener : arrayList) {
                connectivityListener.onConnectivityChanged(z);
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface c {
        boolean a();

        void unregister();
    }

    @RequiresApi(24)
    /* loaded from: classes2.dex */
    public static final class d implements c {

        /* renamed from: a  reason: collision with root package name */
        public boolean f2511a;
        public final ConnectivityMonitor.ConnectivityListener b;
        public final GlideSuppliers.GlideSupplier<ConnectivityManager> c;
        public final ConnectivityManager.NetworkCallback d = new a();

        /* loaded from: classes2.dex */
        public class a extends ConnectivityManager.NetworkCallback {

            /* renamed from: com.bumptech.glide.manager.k$d$a$a  reason: collision with other inner class name */
            /* loaded from: classes2.dex */
            public class RunnableC0222a implements Runnable {
                public final /* synthetic */ boolean h;

                public RunnableC0222a(boolean z) {
                    this.h = z;
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.a(this.h);
                }
            }

            public a() {
            }

            public void a(boolean z) {
                Util.assertMainThread();
                d dVar = d.this;
                boolean z2 = dVar.f2511a;
                dVar.f2511a = z;
                if (z2 != z) {
                    dVar.b.onConnectivityChanged(z);
                }
            }

            public final void b(boolean z) {
                Util.postOnUiThread(new RunnableC0222a(z));
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(@NonNull Network network) {
                b(true);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(@NonNull Network network) {
                b(false);
            }
        }

        public d(GlideSuppliers.GlideSupplier<ConnectivityManager> glideSupplier, ConnectivityMonitor.ConnectivityListener connectivityListener) {
            this.c = glideSupplier;
            this.b = connectivityListener;
        }

        @Override // com.bumptech.glide.manager.k.c
        @SuppressLint({"MissingPermission"})
        public boolean a() {
            this.f2511a = this.c.get().getActiveNetwork() != null;
            try {
                this.c.get().registerDefaultNetworkCallback(this.d);
                return true;
            } catch (RuntimeException e) {
                if (Log.isLoggable("ConnectivityMonitor", 5)) {
                    Log.w("ConnectivityMonitor", "Failed to register callback", e);
                }
                return false;
            }
        }

        @Override // com.bumptech.glide.manager.k.c
        public void unregister() {
            this.c.get().unregisterNetworkCallback(this.d);
        }
    }

    /* loaded from: classes2.dex */
    public static final class e implements c {
        public static final Executor g = AsyncTask.SERIAL_EXECUTOR;

        /* renamed from: a  reason: collision with root package name */
        public final Context f2513a;
        public final ConnectivityMonitor.ConnectivityListener b;
        public final GlideSuppliers.GlideSupplier<ConnectivityManager> c;
        public volatile boolean d;
        public volatile boolean e;
        public final BroadcastReceiver f = new a();

        /* loaded from: classes2.dex */
        public class a extends BroadcastReceiver {
            public a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(@NonNull Context context, Intent intent) {
                e.this.d();
            }
        }

        /* loaded from: classes2.dex */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = e.this;
                eVar.d = eVar.b();
                try {
                    e eVar2 = e.this;
                    eVar2.f2513a.registerReceiver(eVar2.f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                    e.this.e = true;
                } catch (SecurityException e) {
                    if (Log.isLoggable("ConnectivityMonitor", 5)) {
                        Log.w("ConnectivityMonitor", "Failed to register", e);
                    }
                    e.this.e = false;
                }
            }
        }

        /* loaded from: classes2.dex */
        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (e.this.e) {
                    e.this.e = false;
                    e eVar = e.this;
                    eVar.f2513a.unregisterReceiver(eVar.f);
                }
            }
        }

        /* loaded from: classes2.dex */
        public class d implements Runnable {
            public d() {
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean z = e.this.d;
                e eVar = e.this;
                eVar.d = eVar.b();
                if (z != e.this.d) {
                    if (Log.isLoggable("ConnectivityMonitor", 3)) {
                        Log.d("ConnectivityMonitor", "connectivity changed, isConnected: " + e.this.d);
                    }
                    e eVar2 = e.this;
                    eVar2.c(eVar2.d);
                }
            }
        }

        /* renamed from: com.bumptech.glide.manager.k$e$e  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public class RunnableC0223e implements Runnable {
            public final /* synthetic */ boolean h;

            public RunnableC0223e(boolean z) {
                this.h = z;
            }

            @Override // java.lang.Runnable
            public void run() {
                e.this.b.onConnectivityChanged(this.h);
            }
        }

        public e(Context context, GlideSuppliers.GlideSupplier<ConnectivityManager> glideSupplier, ConnectivityMonitor.ConnectivityListener connectivityListener) {
            this.f2513a = context.getApplicationContext();
            this.c = glideSupplier;
            this.b = connectivityListener;
        }

        @Override // com.bumptech.glide.manager.k.c
        public boolean a() {
            g.execute(new b());
            return true;
        }

        @SuppressLint({"MissingPermission"})
        public boolean b() {
            try {
                NetworkInfo activeNetworkInfo = this.c.get().getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            } catch (RuntimeException e) {
                if (Log.isLoggable("ConnectivityMonitor", 5)) {
                    Log.w("ConnectivityMonitor", "Failed to determine connectivity status when connectivity changed", e);
                }
                return true;
            }
        }

        public void c(boolean z) {
            Util.postOnUiThread(new RunnableC0223e(z));
        }

        public void d() {
            g.execute(new d());
        }

        @Override // com.bumptech.glide.manager.k.c
        public void unregister() {
            g.execute(new c());
        }
    }

    public k(@NonNull Context context) {
        c eVar;
        GlideSuppliers.GlideSupplier memorize = GlideSuppliers.memorize(new a(this, context));
        b bVar = new b();
        if (Build.VERSION.SDK_INT >= 24) {
            eVar = new d(memorize, bVar);
        } else {
            eVar = new e(context, memorize, bVar);
        }
        this.f2508a = eVar;
    }

    public static k a(@NonNull Context context) {
        if (d == null) {
            synchronized (k.class) {
                if (d == null) {
                    d = new k(context.getApplicationContext());
                }
            }
        }
        return d;
    }

    @GuardedBy("this")
    public final void b() {
        if (this.c || this.b.isEmpty()) {
            return;
        }
        this.c = this.f2508a.a();
    }

    @GuardedBy("this")
    public final void c() {
        if (this.c && this.b.isEmpty()) {
            this.f2508a.unregister();
            this.c = false;
        }
    }

    public synchronized void d(ConnectivityMonitor.ConnectivityListener connectivityListener) {
        this.b.add(connectivityListener);
        b();
    }

    public synchronized void e(ConnectivityMonitor.ConnectivityListener connectivityListener) {
        this.b.remove(connectivityListener);
        c();
    }
}
