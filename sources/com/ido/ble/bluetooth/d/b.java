package com.ido.ble.bluetooth.d;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class b {
    private static b d;

    /* renamed from: a  reason: collision with root package name */
    private e f12103a;
    private String b;
    private BroadcastReceiver c = new a();

    /* loaded from: classes11.dex */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            char c = 65535;
            switch (action.hashCode()) {
                case -1780914469:
                    if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                        c = 0;
                        break;
                    }
                    break;
                case 6759640:
                    if (action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1167529923:
                    if (action.equals("android.bluetooth.device.action.FOUND")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] scanReceiver. discovery finished");
                    b.this.f12103a.a();
                    b.this.a();
                    return;
                case 1:
                    LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] scanReceiver. discovery start");
                    return;
                case 2:
                    b.this.a((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
                    return;
                default:
                    return;
            }
        }
    }

    private b() {
    }

    private BluetoothDevice a(String str) {
        for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
            if (str.equals(bluetoothDevice.getAddress())) {
                return bluetoothDevice;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] finished.");
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice) {
        LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] find one device[" + bluetoothDevice.getAddress() + "--" + bluetoothDevice.getName() + "]");
        if (this.b.equals(bluetoothDevice.getAddress())) {
            LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] has find device.");
            this.f12103a.a(bluetoothDevice);
            a();
        }
    }

    public static b b() {
        if (d == null) {
            d = new b();
        }
        return d;
    }

    private void c() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        com.ido.ble.b.b().registerReceiver(this.c, intentFilter);
    }

    private void d() {
        LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] release.");
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        com.ido.ble.b.b().unregisterReceiver(this.c);
    }

    private void e() {
        if (BluetoothAdapter.getDefaultAdapter().isDiscovering()) {
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        }
        boolean startDiscovery = BluetoothAdapter.getDefaultAdapter().startDiscovery();
        LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] startDiscovery. result=" + startDiscovery);
    }

    public void a(String str, e eVar) {
        LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] scan " + str);
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] bluetooth is disEnable ");
            eVar.a();
            return;
        }
        BluetoothDevice a2 = a(str);
        if (a2 != null) {
            LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] has find device from bondList.");
            eVar.a(a2);
            return;
        }
        this.b = str;
        this.f12103a = eVar;
        c();
        e();
    }
}
