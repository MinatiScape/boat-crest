package com.ido.ble.dfu.d.c;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.Constants;
import com.ido.ble.bluetooth.DeviceConnectService;
import com.ido.ble.common.e;
import com.ido.ble.common.n;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private d f12176a;
    private int b = -1;
    private BroadcastReceiver c = new C0587a();

    /* renamed from: com.ido.ble.dfu.d.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0587a extends BroadcastReceiver {
        public C0587a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
                if (intExtra == 10) {
                    LogTool.d(com.ido.ble.dfu.a.f12157a, "[ReOpenPhoneBluetoothSwitchTask] off");
                    a.this.b();
                } else if (intExtra == 12) {
                    LogTool.d(com.ido.ble.dfu.a.f12157a, "[ReOpenPhoneBluetoothSwitchTask] on");
                    a.this.c();
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements n.b {
        public b() {
        }

        @Override // com.ido.ble.common.n.b
        public void a() {
            LogTool.d(com.ido.ble.dfu.a.f12157a, "[ReOpenPhoneBluetoothSwitchTask] task time out.");
            a.this.c();
        }
    }

    /* loaded from: classes11.dex */
    public class c implements n.b {
        public c() {
        }

        @Override // com.ido.ble.common.n.b
        public void a() {
            LogTool.d(com.ido.ble.dfu.a.f12157a, "[ReOpenPhoneBluetoothSwitchTask] start enable....");
            BluetoothAdapter.getDefaultAdapter().enable();
        }
    }

    /* loaded from: classes11.dex */
    public interface d {
        void a();
    }

    private boolean a() {
        return ContextCompat.checkSelfPermission(e.a(), "android.permission.BLUETOOTH_ADMIN") == 0 && ContextCompat.checkSelfPermission(e.a(), "android.permission.BLUETOOTH") == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        n.a(new c(), 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.d(com.ido.ble.dfu.a.f12157a, "[ReOpenPhoneBluetoothSwitchTask] finished");
        n.a(this.b);
        e.a().unregisterReceiver(this.c);
        e.a().startService(new Intent(e.a(), DeviceConnectService.class));
        this.f12176a.a();
    }

    private void d() {
        if (!a()) {
            LogTool.d(com.ido.ble.dfu.a.f12157a, "[ReOpenPhoneBluetoothSwitchTask] no permission.");
            c();
        } else if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            BluetoothAdapter.getDefaultAdapter().disable();
        } else {
            BluetoothAdapter.getDefaultAdapter().enable();
        }
    }

    public void a(d dVar) {
        this.f12176a = dVar;
        e.a().stopService(new Intent(e.a(), DeviceConnectService.class));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        e.a().registerReceiver(this.c, intentFilter);
        this.b = n.a(new b(), Constants.ONE_MIN_IN_MILLIS);
        d();
    }
}
