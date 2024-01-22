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
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
/* loaded from: classes10.dex */
public class n0 implements Runnable {
    public final long h;
    public final PowerManager.WakeLock i;
    public final FirebaseMessaging j;

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static class a extends BroadcastReceiver {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public n0 f11352a;

        public a(n0 n0Var) {
            this.f11352a = n0Var;
        }

        public void a() {
            if (n0.c()) {
                Log.d(Constants.TAG, "Connectivity change received registered");
            }
            this.f11352a.b().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            n0 n0Var = this.f11352a;
            if (n0Var != null && n0Var.d()) {
                if (n0.c()) {
                    Log.d(Constants.TAG, "Connectivity changed. Starting background sync.");
                }
                this.f11352a.j.e(this.f11352a, 0L);
                this.f11352a.b().unregisterReceiver(this);
                this.f11352a = null;
            }
        }
    }

    @VisibleForTesting
    @SuppressLint({"InvalidWakeLockTag"})
    public n0(FirebaseMessaging firebaseMessaging, long j) {
        new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new NamedThreadFactory("firebase-iid-executor"));
        this.j = firebaseMessaging;
        this.h = j;
        PowerManager.WakeLock newWakeLock = ((PowerManager) b().getSystemService("power")).newWakeLock(1, "fiid-sync");
        this.i = newWakeLock;
        newWakeLock.setReferenceCounted(false);
    }

    public static boolean c() {
        return Log.isLoggable(Constants.TAG, 3) || (Build.VERSION.SDK_INT == 23 && Log.isLoggable(Constants.TAG, 3));
    }

    public Context b() {
        return this.j.f();
    }

    public boolean d() {
        ConnectivityManager connectivityManager = (ConnectivityManager) b().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @VisibleForTesting
    public boolean e() throws IOException {
        try {
            if (this.j.c() == null) {
                Log.e(Constants.TAG, "Token retrieval failed: null");
                return false;
            } else if (Log.isLoggable(Constants.TAG, 3)) {
                Log.d(Constants.TAG, "Token successfully retrieved");
                return true;
            } else {
                return true;
            }
        } catch (IOException e) {
            if (d0.g(e.getMessage())) {
                String message = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 52);
                sb.append("Token retrieval failed: ");
                sb.append(message);
                sb.append(". Will retry token retrieval");
                Log.w(Constants.TAG, sb.toString());
                return false;
            } else if (e.getMessage() == null) {
                Log.w(Constants.TAG, "Token retrieval failed without exception message. Will retry token retrieval");
                return false;
            } else {
                throw e;
            }
        } catch (SecurityException unused) {
            Log.w(Constants.TAG, "Token retrieval failed with SecurityException. Will retry token retrieval");
            return false;
        }
    }

    @Override // java.lang.Runnable
    @SuppressLint({"WakelockTimeout"})
    public void run() {
        if (ServiceStarter.b().e(b())) {
            this.i.acquire();
        }
        try {
            try {
                this.j.u(true);
                if (!this.j.j()) {
                    this.j.u(false);
                    if (!ServiceStarter.b().e(b())) {
                        return;
                    }
                } else if (ServiceStarter.b().d(b()) && !d()) {
                    new a(this).a();
                    if (!ServiceStarter.b().e(b())) {
                        return;
                    }
                } else {
                    if (e()) {
                        this.j.u(false);
                    } else {
                        this.j.x(this.h);
                    }
                    if (!ServiceStarter.b().e(b())) {
                        return;
                    }
                }
            } catch (IOException e) {
                String message = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 93);
                sb.append("Topic sync or token retrieval failed on hard failure exceptions: ");
                sb.append(message);
                sb.append(". Won't retry the operation.");
                Log.e(Constants.TAG, sb.toString());
                this.j.u(false);
                if (!ServiceStarter.b().e(b())) {
                    return;
                }
            }
            this.i.release();
        } catch (Throwable th) {
            if (ServiceStarter.b().e(b())) {
                this.i.release();
            }
            throw th;
        }
    }
}
