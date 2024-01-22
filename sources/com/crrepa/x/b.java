package com.crrepa.x;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener;
import com.crrepa.i0.c;
import com.example.dfulibrary.SifliDfuService;
import java.io.File;
import java.lang.ref.WeakReference;
/* loaded from: classes9.dex */
public class b {
    public static final String e;
    public static final String f;

    /* renamed from: a  reason: collision with root package name */
    public Context f7857a;
    public CRPBleFirmwareUpgradeListener b;
    public a c = new a(this);
    public boolean d = false;

    /* loaded from: classes9.dex */
    public static class a extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        public WeakReference<b> f7858a;

        public a(b bVar) {
            this.f7858a = new WeakReference<>(bVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra;
            b bVar = this.f7858a.get();
            String action = intent.getAction();
            if (bVar == null || bVar.b == null || TextUtils.isEmpty(action)) {
                return;
            }
            action.hashCode();
            if (action.equals(SifliDfuService.Y)) {
                String stringExtra = intent.getStringExtra(SifliDfuService.c0);
                c.a("Sifli dfu log: " + stringExtra);
            } else if (action.equals(SifliDfuService.Z)) {
                int intExtra2 = intent.getIntExtra(SifliDfuService.e0, -1);
                int intExtra3 = intent.getIntExtra(SifliDfuService.f0, -1);
                c.a("Sifli dfu state: " + intExtra2);
                c.a("Sifli dfu state result: " + intExtra3);
                if (intExtra2 == 0) {
                    bVar.b.onUpgradeProgressStarting();
                } else if (intExtra2 == 1) {
                    if (intExtra3 == 11) {
                        bVar.d();
                    }
                } else if (intExtra2 == 12) {
                    bVar.b.onUpgradeCompleted();
                    bVar.d = false;
                } else if (intExtra2 == 19) {
                    if (bVar.d) {
                        bVar.b.onError(23, "file trans failed");
                    }
                } else if (intExtra2 == 20 && (intExtra = intent.getIntExtra(SifliDfuService.y0, -1)) > 0) {
                    bVar.b.onUpgradeProgressChanged(intExtra, 0.0f);
                }
            }
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        String str = File.separator;
        sb.append(str);
        sb.append("crrepa");
        sb.append(str);
        sb.append("ota");
        sb.append(str);
        sb.append("Sifli");
        sb.append(str);
        sb.append("ctrl_packet.bin");
        e = sb.toString();
        f = Environment.getExternalStorageDirectory().getAbsolutePath() + str + "crrepa" + str + "ota" + str + "Sifli" + str + "outcom_app.bin";
    }

    public b(Context context) {
        this.f7857a = context;
        b();
    }

    public void a(CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener) {
        this.b = cRPBleFirmwareUpgradeListener;
    }

    public void a(String str) {
        this.d = true;
        Intent intent = new Intent(this.f7857a, SifliDfuService.class);
        intent.putExtra(SifliDfuService.g0, str);
        intent.putExtra(SifliDfuService.A0, 2);
        intent.putExtra(SifliDfuService.B0, 23);
        intent.putExtra(SifliDfuService.C0, 1);
        intent.putExtra(SifliDfuService.z0, 0);
        intent.putExtra(SifliDfuService.w0, 0);
        intent.putExtra(SifliDfuService.h0, e);
        intent.putExtra(SifliDfuService.j0, f);
        this.f7857a.startService(intent);
    }

    public final void b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SifliDfuService.Y);
        intentFilter.addAction(SifliDfuService.Z);
        LocalBroadcastManager.getInstance(this.f7857a).registerReceiver(this.c, intentFilter);
    }

    public final void d() {
        this.d = false;
    }
}
