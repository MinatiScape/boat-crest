package com.google.firebase.iid;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;
/* loaded from: classes10.dex */
public class s implements Runnable {
    public final long h;
    public final PowerManager.WakeLock i;
    public final FirebaseInstanceId j;
    @VisibleForTesting
    public ExecutorService k = b.b();

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static class a extends BroadcastReceiver {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public s f11303a;

        public a(s sVar) {
            this.f11303a = sVar;
        }

        public void a() {
            if (FirebaseInstanceId.n()) {
                Log.d("FirebaseInstanceId", "Connectivity change received registered");
            }
            this.f11303a.b().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            s sVar = this.f11303a;
            if (sVar != null && sVar.c()) {
                if (FirebaseInstanceId.n()) {
                    Log.d("FirebaseInstanceId", "Connectivity changed. Starting background sync.");
                }
                this.f11303a.j.f(this.f11303a, 0L);
                this.f11303a.b().unregisterReceiver(this);
                this.f11303a = null;
            }
        }
    }

    @VisibleForTesting
    public s(FirebaseInstanceId firebaseInstanceId, long j) {
        this.j = firebaseInstanceId;
        this.h = j;
        PowerManager.WakeLock newWakeLock = ((PowerManager) b().getSystemService("power")).newWakeLock(1, "fiid-sync");
        this.i = newWakeLock;
        newWakeLock.setReferenceCounted(false);
    }

    public Context b() {
        return this.j.g().getApplicationContext();
    }

    public boolean c() {
        ConnectivityManager connectivityManager = (ConnectivityManager) b().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @VisibleForTesting
    public boolean d() throws IOException {
        if (this.j.B(this.j.l())) {
            try {
                if (this.j.d() == null) {
                    Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                    return false;
                }
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    Log.d("FirebaseInstanceId", "Token successfully retrieved");
                }
                return true;
            } catch (IOException e) {
                if (GmsRpc.e(e.getMessage())) {
                    String message = e.getMessage();
                    StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 52);
                    sb.append("Token retrieval failed: ");
                    sb.append(message);
                    sb.append(". Will retry token retrieval");
                    Log.w("FirebaseInstanceId", sb.toString());
                    return false;
                } else if (e.getMessage() == null) {
                    Log.w("FirebaseInstanceId", "Token retrieval failed without exception message. Will retry token retrieval");
                    return false;
                } else {
                    throw e;
                }
            } catch (SecurityException unused) {
                Log.w("FirebaseInstanceId", "Token retrieval failed with SecurityException. Will retry token retrieval");
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.Runnable
    @SuppressLint({"Wakelock"})
    public void run() {
        if (ServiceStarter.getInstance().b(b())) {
            this.i.acquire();
        }
        try {
            try {
                this.j.x(true);
                if (!this.j.isGmsCorePresent()) {
                    this.j.x(false);
                    if (!ServiceStarter.getInstance().b(b())) {
                        return;
                    }
                } else if (ServiceStarter.getInstance().a(b()) && !c()) {
                    new a(this).a();
                    if (!ServiceStarter.getInstance().b(b())) {
                        return;
                    }
                } else {
                    if (d()) {
                        this.j.x(false);
                    } else {
                        this.j.A(this.h);
                    }
                    if (!ServiceStarter.getInstance().b(b())) {
                        return;
                    }
                }
            } catch (IOException e) {
                String message = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 93);
                sb.append("Topic sync or token retrieval failed on hard failure exceptions: ");
                sb.append(message);
                sb.append(". Won't retry the operation.");
                Log.e("FirebaseInstanceId", sb.toString());
                this.j.x(false);
                if (!ServiceStarter.getInstance().b(b())) {
                    return;
                }
            }
            this.i.release();
        } catch (Throwable th) {
            if (ServiceStarter.getInstance().b(b())) {
                this.i.release();
            }
            throw th;
        }
    }
}
