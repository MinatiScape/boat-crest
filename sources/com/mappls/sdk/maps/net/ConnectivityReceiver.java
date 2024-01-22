package com.mappls.sdk.maps.net;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.mappls.sdk.maps.log.Logger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes11.dex */
public class ConnectivityReceiver extends BroadcastReceiver {
    @SuppressLint({"StaticFieldLeak"})
    public static ConnectivityReceiver e;
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public List<ConnectivityListener> f12794a = new CopyOnWriteArrayList();
    public Context b;
    public int c;
    @Nullable
    public Boolean d;

    public ConnectivityReceiver(@NonNull Context context) {
        this.b = context;
    }

    public static synchronized ConnectivityReceiver instance(@NonNull Context context) {
        ConnectivityReceiver connectivityReceiver;
        synchronized (ConnectivityReceiver.class) {
            if (e == null) {
                ConnectivityReceiver connectivityReceiver2 = new ConnectivityReceiver(context.getApplicationContext());
                e = connectivityReceiver2;
                connectivityReceiver2.addListener(new NativeConnectivityListener());
            }
            connectivityReceiver = e;
        }
        return connectivityReceiver;
    }

    public final boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.b.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @UiThread
    public void activate() {
        if (this.c == 0) {
            this.b.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        this.c++;
    }

    public void addListener(@NonNull ConnectivityListener connectivityListener) {
        this.f12794a.add(connectivityListener);
    }

    public final void b(boolean z) {
        Logger.v("Mbgl-ConnectivityReceiver", z ? "connected - true" : "connected - false");
        for (ConnectivityListener connectivityListener : this.f12794a) {
            connectivityListener.onNetworkStateChanged(z);
        }
    }

    @UiThread
    public void deactivate() {
        int i = this.c - 1;
        this.c = i;
        if (i == 0) {
            this.b.unregisterReceiver(e);
        }
    }

    public boolean isConnected() {
        Boolean bool = this.d;
        return bool != null ? bool.booleanValue() : a();
    }

    public boolean isOffline() {
        Boolean bool = this.d;
        return (bool == null || bool.booleanValue()) ? false : true;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, Intent intent) {
        if (this.d != null) {
            return;
        }
        b(a());
    }

    public void removeListener(@NonNull ConnectivityListener connectivityListener) {
        this.f12794a.remove(connectivityListener);
    }

    public void setConnected(Boolean bool) {
        boolean a2;
        this.d = bool;
        if (bool != null) {
            a2 = bool.booleanValue();
        } else {
            a2 = a();
        }
        b(a2);
    }
}
