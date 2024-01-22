package com.ido.ble.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.connect.e;
import com.ido.ble.bluetooth.connect.l;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.BindCallBack;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import java.lang.reflect.Method;
import java.util.Set;
/* loaded from: classes11.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final BroadcastReceiver f12044a = new a();

    /* loaded from: classes11.dex */
    public static class a extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action) && action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
                if (intExtra == 10) {
                    LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "bluetooth switch is turn off");
                    e.n().k();
                    l.g().d();
                } else if (intExtra == 12) {
                    LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "bluetooth switch is turn on");
                    e.n().i();
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class b implements BindCallBack.ICallBack {
        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onCancel() {
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onFailed(BindCallBack.BindFailedError bindFailedError) {
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onNeedAuth(int i) {
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onReject() {
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onSuccess() {
            c.b();
        }
    }

    /* renamed from: com.ido.ble.bluetooth.c$c  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0568c implements ConnectCallBack.ICallBack {
        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectStart(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            if (com.ido.ble.bluetooth.f.c.g(str).f()) {
                c.b();
            }
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnecting(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onDeviceInNotBindStatus(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInitCompleted(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onRetry(int i, String str) {
        }
    }

    public static void a(Context context) {
        b(context);
        d();
        c();
    }

    public static boolean a() {
        String m = com.ido.ble.f.a.f.a.g0().m();
        LogTool.a("btconnect", "devices:" + m);
        if (TextUtils.isEmpty(m)) {
            return false;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            Method declaredMethod = BluetoothAdapter.class.getDeclaredMethod("getConnectionState", null);
            declaredMethod.setAccessible(true);
            if (((Integer) declaredMethod.invoke(defaultAdapter, null)).intValue() == 2) {
                Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
                LogTool.a("btconnect", "devices:" + bondedDevices.size());
                for (BluetoothDevice bluetoothDevice : bondedDevices) {
                    Method declaredMethod2 = BluetoothDevice.class.getDeclaredMethod("isConnected", null);
                    declaredMethod.setAccessible(true);
                    if (!((Boolean) declaredMethod2.invoke(bluetoothDevice, null)).booleanValue()) {
                        LogTool.a("btconnect", bluetoothDevice.getName() + " connect false(" + bluetoothDevice.getAddress() + ")");
                    } else if (m.equals(bluetoothDevice.getAddress())) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void b() {
        String str;
        SupportFunctionInfo Z = com.ido.ble.f.a.f.a.g0().Z();
        if (Z == null) {
            str = "supportFunctionInfo = null";
        } else if (Z.v2_get_bt_addr) {
            com.ido.ble.bluetooth.d.c.a();
            return;
        } else {
            str = "v2_get_bt_addr_02_04 is falese";
        }
        LogTool.d(com.ido.ble.logs.a.q, str);
    }

    private static void b(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        context.registerReceiver(f12044a, intentFilter);
    }

    private static void c() {
        com.ido.ble.callback.b.N().a(new C0568c());
    }

    public static void c(Context context) {
        context.unregisterReceiver(f12044a);
    }

    private static void d() {
        com.ido.ble.callback.b.N().a(new b());
    }
}
