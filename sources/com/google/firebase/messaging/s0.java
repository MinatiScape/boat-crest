package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.io.IOException;
/* loaded from: classes10.dex */
public class s0 implements Runnable {
    public static final Object m = new Object();
    @GuardedBy("TOPIC_SYNC_TASK_LOCK")
    public static Boolean n;
    @GuardedBy("TOPIC_SYNC_TASK_LOCK")
    public static Boolean o;
    public final Context h;
    public final h0 i;
    public final PowerManager.WakeLock j;
    public final r0 k;
    public final long l;

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public class a extends BroadcastReceiver {
        @Nullable
        @GuardedBy("this")

        /* renamed from: a  reason: collision with root package name */
        public s0 f11362a;

        public a(s0 s0Var) {
            this.f11362a = s0Var;
        }

        public void a() {
            if (s0.b()) {
                Log.d(Constants.TAG, "Connectivity change received registered");
            }
            s0.this.h.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }

        @Override // android.content.BroadcastReceiver
        public synchronized void onReceive(Context context, Intent intent) {
            s0 s0Var = this.f11362a;
            if (s0Var == null) {
                return;
            }
            if (s0Var.i()) {
                if (s0.b()) {
                    Log.d(Constants.TAG, "Connectivity changed. Starting background sync.");
                }
                this.f11362a.k.l(this.f11362a, 0L);
                context.unregisterReceiver(this);
                this.f11362a = null;
            }
        }
    }

    public s0(r0 r0Var, Context context, h0 h0Var, long j) {
        this.k = r0Var;
        this.h = context;
        this.l = j;
        this.i = h0Var;
        this.j = ((PowerManager) context.getSystemService("power")).newWakeLock(1, Constants.FCM_WAKE_LOCK);
    }

    public static /* synthetic */ boolean b() {
        return j();
    }

    public static String e(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 142);
        sb.append("Missing Permission: ");
        sb.append(str);
        sb.append(". This permission should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        return sb.toString();
    }

    public static boolean f(Context context) {
        boolean booleanValue;
        boolean booleanValue2;
        synchronized (m) {
            Boolean bool = o;
            if (bool == null) {
                booleanValue = g(context, "android.permission.ACCESS_NETWORK_STATE", bool);
            } else {
                booleanValue = bool.booleanValue();
            }
            Boolean valueOf = Boolean.valueOf(booleanValue);
            o = valueOf;
            booleanValue2 = valueOf.booleanValue();
        }
        return booleanValue2;
    }

    public static boolean g(Context context, String str, Boolean bool) {
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean z = context.checkCallingOrSelfPermission(str) == 0;
        if (z || !Log.isLoggable(Constants.TAG, 3)) {
            return z;
        }
        Log.d(Constants.TAG, e(str));
        return false;
    }

    public static boolean h(Context context) {
        boolean booleanValue;
        boolean booleanValue2;
        synchronized (m) {
            Boolean bool = n;
            if (bool == null) {
                booleanValue = g(context, "android.permission.WAKE_LOCK", bool);
            } else {
                booleanValue = bool.booleanValue();
            }
            Boolean valueOf = Boolean.valueOf(booleanValue);
            n = valueOf;
            booleanValue2 = valueOf.booleanValue();
        }
        return booleanValue2;
    }

    public static boolean j() {
        return Log.isLoggable(Constants.TAG, 3) || (Build.VERSION.SDK_INT == 23 && Log.isLoggable(Constants.TAG, 3));
    }

    public final synchronized boolean i() {
        boolean z;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.h.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        if (activeNetworkInfo != null) {
            z = activeNetworkInfo.isConnected();
        }
        return z;
    }

    @Override // java.lang.Runnable
    @SuppressLint({"Wakelock"})
    public void run() {
        if (h(this.h)) {
            this.j.acquire(Constants.WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS);
        }
        try {
            try {
                this.k.n(true);
                if (!this.i.g()) {
                    this.k.n(false);
                    if (h(this.h)) {
                        try {
                            this.j.release();
                        } catch (RuntimeException unused) {
                            Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
                        }
                    }
                } else if (f(this.h) && !i()) {
                    new a(this).a();
                    if (h(this.h)) {
                        try {
                            this.j.release();
                        } catch (RuntimeException unused2) {
                            Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
                        }
                    }
                } else {
                    if (this.k.r()) {
                        this.k.n(false);
                    } else {
                        this.k.s(this.l);
                    }
                    if (h(this.h)) {
                        try {
                            this.j.release();
                        } catch (RuntimeException unused3) {
                            Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
                        }
                    }
                }
            } catch (IOException e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e(Constants.TAG, valueOf.length() != 0 ? "Failed to sync topics. Won't retry sync. ".concat(valueOf) : new String("Failed to sync topics. Won't retry sync. "));
                this.k.n(false);
                if (h(this.h)) {
                    try {
                        this.j.release();
                    } catch (RuntimeException unused4) {
                        Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
                    }
                }
            }
        } catch (Throwable th) {
            if (h(this.h)) {
                try {
                    this.j.release();
                } catch (RuntimeException unused5) {
                    Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
                }
            }
            throw th;
        }
    }
}
