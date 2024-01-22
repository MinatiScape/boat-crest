package com.google.android.gms.tagmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;
/* loaded from: classes10.dex */
public final class zzdk extends BroadcastReceiver {
    @VisibleForTesting
    public static final String zza = zzdk.class.getName();
    public final zzey zzb;

    public zzdk(zzey zzeyVar) {
        this.zzb = zzeyVar;
    }

    public static void zza(Context context) {
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zza, true);
        context.sendBroadcast(intent);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            Bundle extras = intent.getExtras();
            Boolean bool = Boolean.FALSE;
            if (extras != null) {
                bool = Boolean.valueOf(intent.getExtras().getBoolean("noConnectivity"));
            }
            this.zzb.zzc(!bool.booleanValue());
        } else if (!"com.google.analytics.RADIO_POWERED".equals(action) || intent.hasExtra(zza)) {
        } else {
            this.zzb.zzb();
        }
    }
}
