package com.ido.ble.bluetooth.connect;

import android.bluetooth.BluetoothAdapter;
import android.text.TextUtils;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class e implements i {
    private static i b;

    /* renamed from: a  reason: collision with root package name */
    private h f12067a;

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.a(com.ido.ble.bluetooth.a.d(), true);
        }
    }

    private e() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, boolean z) {
        if (com.ido.ble.dfu.c.b()) {
            LogTool.b("ConnectStateHelper", "[toAutoConnect] ignore, dfu task is doing.");
            ConnectCallBack.a(ConnectFailedReason.IN_UPGRADING_STATUS, str);
            return;
        }
        h a2 = com.ido.ble.bluetooth.connect.a.a(this);
        this.f12067a = a2;
        a2.a(str, z);
    }

    public static i n() {
        if (b == null) {
            b = new e();
        }
        return b;
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void a(int i, int i2) {
        ConnectCallBack.a(ConnectFailedReason.SYSTEM_GATT_ERROR, b());
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void a(int i, int i2, String str) {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ConnectStateHelper] onConnectBreakByGATT");
        ConnectCallBack.a(str);
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void a(BLEDevice bLEDevice) {
        h a2 = f.a(this);
        this.f12067a = a2;
        a2.a(bLEDevice);
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void a(BLEDevice bLEDevice, long j) {
        h a2 = n.a(this);
        this.f12067a = a2;
        a2.a(bLEDevice, j);
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void a(String str) {
        a(str, false);
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void a(byte[] bArr) {
        h hVar = this.f12067a;
        if (hVar != null) {
            hVar.a(bArr);
        }
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public boolean a() {
        h hVar = this.f12067a;
        if (hVar == null) {
            return false;
        }
        return hVar.a();
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public String b() {
        h hVar = this.f12067a;
        return hVar != null ? hVar.b() : "";
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void b(BLEDevice bLEDevice) {
        h a2 = n.a(this);
        this.f12067a = a2;
        a2.a(bLEDevice);
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void b(String str) {
        ConnectCallBack.b(str);
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void c() {
        ConnectCallBack.a(ConnectFailedReason.BLUETOOTH_SWITCH_CLOSED, b());
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void c(BLEDevice bLEDevice) {
        ConnectCallBack.a(bLEDevice);
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void c(String str) {
        if (com.ido.ble.bluetooth.f.c.g(str).f()) {
            return;
        }
        ConnectCallBack.c(str);
        ConnectCallBack.f(str);
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void d() {
        ConnectCallBack.a(ConnectFailedReason.ENCRYPTED_FAILED, b());
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void e() {
        ConnectCallBack.a(ConnectFailedReason.NOT_IN_BIND_STATUS, b());
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void f() {
        ConnectCallBack.a(ConnectFailedReason.ENABLE_NOTIFY_FAILED, b());
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void g() {
        h hVar = this.f12067a;
        if (hVar != null) {
            hVar.d();
        }
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void h() {
        ConnectCallBack.a(ConnectFailedReason.DISCOVER_SERVICE_FAILED, b());
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void i() {
        if (this.f12067a instanceof f) {
            return;
        }
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b("ConnectStateHelper", "[onPhoneBlueToothSwitchOpen()] phone bluetooth switch is closed.");
        } else if (!com.ido.ble.bluetooth.a.g()) {
            LogTool.b("ConnectStateHelper", "[onPhoneBlueToothSwitchOpen()] failed, is not bind!");
        } else if (TextUtils.isEmpty(com.ido.ble.bluetooth.a.d())) {
            LogTool.b("ConnectStateHelper", "[onPhoneBlueToothSwitchOpen()] failed, mac address is empty");
        } else {
            com.ido.ble.common.e.a(new a());
        }
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public boolean isConnectedAndReady() {
        h hVar = this.f12067a;
        if (hVar == null) {
            return false;
        }
        return hVar.c();
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void j() {
        ConnectCallBack.a(ConnectFailedReason.MAC_ADDRESS_INVALID, b());
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void k() {
        h hVar = this.f12067a;
        if (hVar == null) {
            return;
        }
        if (hVar.a() || this.f12067a.c()) {
            this.f12067a.d();
        }
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void l() {
        ConnectCallBack.a(ConnectFailedReason.ERROR_OTHER, b());
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void m() {
        ConnectCallBack.a(ConnectFailedReason.ERROR_OTHER, b());
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void onConnectStart(String str) {
        ConnectCallBack.b(str);
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void onConnecting(String str) {
        ConnectCallBack.d(str);
    }

    @Override // com.ido.ble.bluetooth.connect.i
    public void onRetry(int i, String str) {
        ConnectCallBack.a(i, str);
    }
}
