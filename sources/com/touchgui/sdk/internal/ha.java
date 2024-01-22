package com.touchgui.sdk.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.touchgui.sdk.TGLogger;
import java.util.Locale;
/* loaded from: classes12.dex */
public final class ha extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.n f13772a;

    public ha(com.touchgui.sdk.n nVar) {
        this.f13772a = nVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
            TGLogger.d(String.format(Locale.getDefault(), "bluetoothState=%d", Integer.valueOf(intExtra)));
            if (intExtra == 10) {
                this.f13772a.stopScan();
            } else if (intExtra != 12) {
            } else {
                this.f13772a.startScan();
            }
        }
    }
}
