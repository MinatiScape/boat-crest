package com.ido.ble.bluetooth.connect;

import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
class n extends d {
    private static h C;
    private com.ido.ble.bluetooth.connect.r.b A;
    private boolean B;

    private n(i iVar) {
        super(iVar);
        this.B = false;
        this.A = new com.ido.ble.bluetooth.connect.r.c();
    }

    public static h a(i iVar) {
        if (C == null) {
            C = new n(iVar);
        }
        return C;
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.c, com.ido.ble.bluetooth.connect.b
    public void a(int i, int i2) {
        super.a(i, i2);
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[StraightConnectPresenter] callOnConnectBreakByGATT ");
        this.z.a(i, i2, b());
        this.A.b(i, i2);
        if (!com.ido.ble.bluetooth.a.g() || this.B || !CustomConfig.getConfig().isAutoConnectIfBreak()) {
            String str = com.ido.ble.bluetooth.f.b.f12116a;
            LogTool.d(str, "[StraightConnectPresenter] connection break, will not to connect auto " + com.ido.ble.bluetooth.a.d());
            return;
        }
        String str2 = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str2, "[StraightConnectPresenter] connection break, auto to connect " + com.ido.ble.bluetooth.a.d());
        com.ido.ble.bluetooth.a.a();
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public void a(BLEDevice bLEDevice) {
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[StraightConnectPresenter] to connect device, bleDevice is " + bLEDevice.toString());
        d(bLEDevice);
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public void a(BLEDevice bLEDevice, long j) {
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[StraightConnectPresenter] to connect device, bleDevice is " + bLEDevice.toString());
        b(bLEDevice, j);
    }

    @Override // com.ido.ble.bluetooth.connect.h
    public boolean a() {
        return t();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void b(int i, int i2) {
        this.z.a(i, i2);
        this.A.a(i, i2);
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.b
    public void b(BLEDevice bLEDevice) {
        super.b(bLEDevice);
        this.A.a();
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public void d() {
        this.B = true;
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[StraightConnectPresenter] to disconnect.");
        super.d();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void f() {
        this.z.c();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void g() {
        this.z.d();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void h() {
        this.z.j();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void i() {
        this.B = false;
        this.z.onConnectStart(b());
        this.A.b();
    }

    @Override // com.ido.ble.bluetooth.connect.c, com.ido.ble.bluetooth.connect.b
    public void j() {
        super.j();
        this.z.l();
        this.A.c();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void k() {
        this.z.onConnecting(b());
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void l() {
        this.z.h();
        this.A.d();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void m() {
        this.z.f();
        this.A.e();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void n() {
        this.z.f();
        this.A.f();
    }
}
