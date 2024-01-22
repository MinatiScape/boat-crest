package com.ido.ble.e;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.connect.g;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.bluetooth.f.d;
import com.ido.ble.bluetooth.f.e;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.callback.ScanCallBack;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.logs.LogTool;
import java.util.List;
/* loaded from: classes11.dex */
public class b {
    private static final String k = "b";
    private static b l = null;
    private static final int m = 3;
    private String b;
    private BLEDevice c;
    private String d;
    private String e;
    private String f;

    /* renamed from: a  reason: collision with root package name */
    private boolean f12212a = false;
    private int g = 0;
    private boolean h = false;
    private ConnectCallBack.ICallBack i = new a();
    private ScanCallBack.ICallBack j = new C0595b();

    /* loaded from: classes11.dex */
    public class a implements ConnectCallBack.ICallBack {
        public a() {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            if (b.this.h) {
                b.this.b();
            }
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
            b.this.d();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectStart(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            b.this.f();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnecting(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onDeviceInNotBindStatus(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
            b.this.e();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInitCompleted(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onRetry(int i, String str) {
        }
    }

    /* renamed from: com.ido.ble.e.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0595b implements ScanCallBack.ICallBack {
        public C0595b() {
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onFindDevice(BLEDevice bLEDevice) {
            if (TextUtils.isEmpty(b.this.b)) {
                LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + b.k, "targetMacAddress null");
                BLEDevice c = com.ido.ble.f.a.f.b.e().c();
                if (c == null || TextUtils.isEmpty(c.mDeviceAddress)) {
                    LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + b.k, "get targetMacAddress is null");
                    return;
                }
                b.this.b = c.mDeviceAddress;
                LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + b.k, "targetMacAddress :" + b.this.b);
            }
            if (bLEDevice != null && !TextUtils.isEmpty(bLEDevice.mDeviceAddress) && bLEDevice.mDeviceAddress.endsWith(b.this.b)) {
                b.this.c = bLEDevice;
                LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + b.k, "find target device, mac =" + bLEDevice.mDeviceAddress);
            } else if (!d.a(b.this.d, b.this.e, bLEDevice)) {
                return;
            } else {
                b.this.c = bLEDevice;
                LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + b.k, "find target device(mac +1)");
            }
            b.this.j();
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onScanFinished() {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + b.k, "scan finished.");
            b.this.g();
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onStart() {
        }
    }

    /* loaded from: classes11.dex */
    public class c implements g.c {
        public c() {
        }

        @Override // com.ido.ble.bluetooth.connect.g.c
        public void a() {
            b.this.i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + k, "failed");
        e();
    }

    private void b(String str) {
        BluetoothDevice a2 = BluetoothAdapter.checkBluetoothAddress(str) ? e.a(str) : e.b(str);
        if (a2 == null) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + k, "not paired!");
            return;
        }
        boolean a3 = e.a(a2);
        StringBuilder sb = new StringBuilder();
        sb.append(com.ido.ble.bluetooth.f.b.f12116a);
        sb.append("_");
        String str2 = k;
        sb.append(str2);
        LogTool.d(sb.toString(), "has paired, isConnectedByPhone=" + a3);
        if (!CustomConfig.getConfig().isNeedRemoveBondBeforeConnect() || e.b()) {
            return;
        }
        String str3 = com.ido.ble.bluetooth.f.b.f12116a + "_" + str2;
        LogTool.d(str3, "remove bond status is " + e.b(a2));
    }

    public static b c() {
        if (l == null) {
            l = new b();
        }
        return l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.g++;
        StringBuilder sb = new StringBuilder();
        sb.append(com.ido.ble.bluetooth.f.b.f12116a);
        sb.append("_");
        String str = k;
        sb.append(str);
        LogTool.d(sb.toString(), "reconnect times is " + this.g);
        if (this.g <= 3) {
            h();
            return;
        }
        LogTool.b(com.ido.ble.bluetooth.f.b.f12116a + "_" + str, "out of max retry times.");
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + k, "release");
        this.f12212a = false;
        this.h = false;
        this.b = "";
        this.c = null;
        this.g = 0;
        com.ido.ble.callback.b.N().b(this.j);
        com.ido.ble.callback.b.N().b(this.i);
        l = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + k, FirebaseAnalytics.Param.SUCCESS);
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        BLEDevice bLEDevice;
        com.ido.ble.callback.b.N().b(this.i);
        if (this.c == null) {
            List<String> b = com.ido.ble.f.a.f.b.e().b();
            if (b == null || !b.contains(this.b)) {
                LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + k, "not find target device connect failed");
                ConnectCallBack.a(ConnectFailedReason.ERROR_OTHER, this.b);
                return;
            }
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + k, "not find target device,but it has binded ,toConnect");
            this.h = true;
            com.ido.ble.callback.b.N().a(this.i);
            bLEDevice = new BLEDevice();
            this.c = bLEDevice;
            bLEDevice.mDeviceAddress = this.b;
        } else {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + k, "toConnect");
            this.h = true;
            com.ido.ble.callback.b.N().a(this.i);
            bLEDevice = this.c;
        }
        com.ido.ble.bluetooth.a.b(bLEDevice);
    }

    private void h() {
        new g().a(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + k, "toStartScan");
        com.ido.ble.bluetooth.a.n();
        com.ido.ble.callback.b.N().b(this.j);
        com.ido.ble.callback.b.N().a(this.j);
        com.ido.ble.bluetooth.a.m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a + "_" + k, "toStopScan");
        com.ido.ble.bluetooth.a.n();
    }

    public void a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(com.ido.ble.bluetooth.f.b.f12116a);
        sb.append("_");
        String str2 = k;
        sb.append(str2);
        LogTool.d(sb.toString(), "[connect] macAddress = " + str);
        if (this.f12212a) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a + "_" + str2, "is doing...");
            return;
        }
        this.f12212a = true;
        this.h = false;
        this.b = str;
        this.d = d.a(str);
        this.e = d.b(str);
        LogTool.b(com.ido.ble.bluetooth.f.b.f12116a + "_" + str2, "targetMacAddressAdd1:" + this.d);
        LogTool.b(com.ido.ble.bluetooth.f.b.f12116a + "_" + str2, "targetMacAddressAdd2:" + this.e);
        b(str);
        h();
    }
}
