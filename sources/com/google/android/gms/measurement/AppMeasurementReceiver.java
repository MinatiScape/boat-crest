package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzfb;
/* loaded from: classes10.dex */
public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements zzfb.zza {
    public zzfb j;

    @NonNull
    public BroadcastReceiver.PendingResult doGoAsync() {
        return goAsync();
    }

    @Override // com.google.android.gms.measurement.internal.zzfb.zza
    @MainThread
    public void doStartService(@NonNull Context context, @NonNull Intent intent) {
        WakefulBroadcastReceiver.startWakefulService(context, intent);
    }

    @Override // android.content.BroadcastReceiver
    @MainThread
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (this.j == null) {
            this.j = new zzfb(this);
        }
        this.j.zza(context, intent);
    }
}
