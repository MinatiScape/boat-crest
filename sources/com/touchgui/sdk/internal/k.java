package com.touchgui.sdk.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.touchgui.sdk.TGLogger;
import java.util.Locale;
/* loaded from: classes12.dex */
public final class k extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ q f13786a;

    public k(q qVar) {
        this.f13786a = qVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String str;
        String str2;
        String str3;
        if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
            synchronized (this.f13786a.l) {
                this.f13786a.m = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
                TGLogger.d(this.f13786a.n, String.format(Locale.getDefault(), "bluetoothState=%d", Integer.valueOf(this.f13786a.m)));
                q qVar = this.f13786a;
                switch (qVar.m) {
                    case 10:
                        TGLogger.d(qVar.n, "Bluetooth is off");
                        this.f13786a.a();
                        break;
                    case 11:
                        str = qVar.n;
                        str2 = "Turning on Bluetooth";
                        TGLogger.d(str, str2);
                        break;
                    case 12:
                        TGLogger.d(qVar.n, "Bluetooth is turned on");
                        q qVar2 = this.f13786a;
                        if (qVar2.d && (str3 = qVar2.n) != null) {
                            qVar2.b(str3, true);
                            break;
                        }
                        break;
                    case 13:
                        TGLogger.d(qVar.n, "Turning off Bluetooth");
                        this.f13786a.b(false);
                        break;
                    default:
                        str = qVar.n;
                        str2 = "Unknown status";
                        TGLogger.d(str, str2);
                        break;
                }
            }
        }
    }
}
