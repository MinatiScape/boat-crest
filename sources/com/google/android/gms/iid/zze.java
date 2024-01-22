package com.google.android.gms.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.internal.gcm.zzg;
import java.util.concurrent.ExecutorService;
/* loaded from: classes6.dex */
public abstract class zze extends Service {
    public Binder i;
    public int k;
    @VisibleForTesting
    public final ExecutorService h = zzg.zzaa().zzd(new NamedThreadFactory("EnhancedIntentService"), 9);
    public final Object j = new Object();
    public int l = 0;

    public final void b(Intent intent) {
        if (intent != null) {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
        synchronized (this.j) {
            int i = this.l - 1;
            this.l = i;
            if (i == 0) {
                stopSelfResult(this.k);
            }
        }
    }

    public abstract void handleIntent(Intent intent);

    @Override // android.app.Service
    public final synchronized IBinder onBind(Intent intent) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "Service received bind request");
        }
        if (this.i == null) {
            this.i = new zzi(this);
        }
        return this.i;
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        synchronized (this.j) {
            this.k = i2;
            this.l++;
        }
        if (intent == null) {
            b(intent);
            return 2;
        }
        this.h.execute(new g(this, intent, intent));
        return 3;
    }
}
