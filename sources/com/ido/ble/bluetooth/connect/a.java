package com.ido.ble.bluetooth.connect;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import com.ido.ble.bluetooth.connect.k;
import com.ido.ble.bluetooth.connect.m;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.AutoConnectErrorHappenListener;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.logs.LogTool;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class a extends d {
    private static h H;
    private String A;
    private boolean B;
    private com.ido.ble.bluetooth.connect.r.b C;
    private boolean D;
    private boolean E;
    private boolean F;
    private int G;

    /* renamed from: com.ido.ble.bluetooth.connect.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0569a implements k.b {
        public C0569a() {
        }

        @Override // com.ido.ble.bluetooth.connect.k.b
        public void a() {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter] failed, onOutOfMaxRtyTimes");
            a.this.z.m();
            a.this.v();
        }

        @Override // com.ido.ble.bluetooth.connect.k.b
        public void a(int i) {
            a aVar = a.this;
            aVar.z.onRetry(i, aVar.A);
            a.this.u();
        }
    }

    /* loaded from: classes11.dex */
    public class b implements m.b {
        public b() {
        }

        @Override // com.ido.ble.bluetooth.connect.m.b
        public void a() {
            a.this.C.g();
            AutoConnectErrorHappenListener.onErrorHappen(AutoConnectErrorHappenListener.ErrorHappenType.NOT_FIND_DEVICE, a.this.A);
        }

        @Override // com.ido.ble.bluetooth.connect.m.b
        public void a(BLEDevice bLEDevice) {
            if (bLEDevice.mIsInDfuMode) {
                a.this.c(bLEDevice);
            } else {
                a.this.a(bLEDevice, true);
            }
        }

        @Override // com.ido.ble.bluetooth.connect.m.b
        public boolean a(String str) {
            return a.this.y();
        }

        @Override // com.ido.ble.bluetooth.connect.m.b
        public void b() {
            a.this.v();
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter] failed, onStopByPhoneBluetoothSwitchClosed");
            a.this.z.c();
        }

        @Override // com.ido.ble.bluetooth.connect.m.b
        public void c() {
            a.this.v();
        }
    }

    private a(i iVar) {
        super(iVar);
        this.A = "";
        this.B = false;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = 0;
        this.C = new com.ido.ble.bluetooth.connect.r.a();
    }

    public static h a(i iVar) {
        if (H == null) {
            H = new a(iVar);
        }
        return H;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BLEDevice bLEDevice, boolean z) {
        this.D = z;
        d(bLEDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[AutoConnectPresenter] scanTargetDevice(), address is " + this.A);
        if (s()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter] scanTargetDevice() is refused, already connected to device");
            v();
        } else if (!com.ido.ble.bluetooth.a.g()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter]  failed, scanTargetDevice() is refused, not bind");
            v();
            this.z.e();
        } else if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter]  failed,   scanTargetDevice() is refused, bluetooth switch is off");
            v();
            this.z.c();
        } else if (BluetoothAdapter.checkBluetoothAddress(this.A)) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter] scanTargetDevice...");
            m.a(this.A, new b());
        } else {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter]  failed,   scanTargetDevice() is refused, address is invalid");
            v();
            this.z.j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter]  stop reconnect task");
        this.B = false;
        this.F = false;
        this.G = 0;
        k.e();
        m.f();
        l.g().d();
    }

    private boolean w() {
        if (this.G > 3 && BluetoothAdapter.getDefaultAdapter().isEnabled() && Build.VERSION.SDK_INT >= 28 && !this.E) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter] try To Connect not exist device...");
            this.E = true;
            BLEDevice bLEDevice = new BLEDevice();
            bLEDevice.mDeviceAddress = "AA:BB:CC:DD:EE:FF";
            a(bLEDevice, false);
            return true;
        }
        return false;
    }

    private void x() {
        if (this.F) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter]  tryReconnect() is refused, mIsInitiativeDisConnect = true");
            v();
        } else if (com.ido.ble.dfu.c.b()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter]  tryReconnect() is refused, dfu task is doing.");
            v();
        } else {
            this.z.b(this.A);
            k.b(new C0569a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean y() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter] try To Connect Direct");
        BLEDevice c = com.ido.ble.f.a.f.b.e().c();
        if (c == null || !this.A.equals(c.mDeviceAddress)) {
            c = new BLEDevice();
            c.mDeviceAddress = this.A;
        }
        a(c, false);
        return true;
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.c, com.ido.ble.bluetooth.connect.b
    public void a(int i, int i2) {
        super.a(i, i2);
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter]  callOnConnectBreakByGATT");
        this.z.a(i, i2, b());
        this.C.b(i, i2);
        if (CustomConfig.getConfig().isAutoConnectIfBreak()) {
            x();
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter]  isAutoConnectIfBreak = false");
        v();
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public void a(String str, boolean z) {
        this.F = false;
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter] toConnectDevice()");
        if (s()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter] is on connected state, ignore this action!");
            b(p());
            return;
        }
        if (z) {
            this.E = false;
        }
        if (this.B) {
            if (!m.a()) {
                k.b();
            }
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter] isAutoConnecting = true, ignore this action!");
            k();
            return;
        }
        String str2 = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str2, "[AutoConnectPresenter] to connect device, macAddress is " + str);
        this.z.onConnectStart(str);
        this.C.b();
        this.B = true;
        this.A = str;
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter] to scan target device.");
        k.e();
        l.g().d();
        u();
    }

    @Override // com.ido.ble.bluetooth.connect.h
    public boolean a() {
        return this.B || super.t();
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public String b() {
        return this.A;
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void b(int i, int i2) {
        AutoConnectErrorHappenListener.ErrorHappenType errorHappenType;
        this.C.a(i, i2);
        if (this.D) {
            this.G++;
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter]  mFindDeviceAndGattErrorTimes = " + this.G);
            errorHappenType = AutoConnectErrorHappenListener.ErrorHappenType.GATT_ERROR_FIND_DEVICE;
        } else {
            errorHappenType = AutoConnectErrorHappenListener.ErrorHappenType.GATT_ERROR_OTHER;
        }
        AutoConnectErrorHappenListener.onErrorHappen(errorHappenType, this.A);
        if (w()) {
            return;
        }
        x();
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.b
    public void b(BLEDevice bLEDevice) {
        super.b(bLEDevice);
        this.C.a();
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter]  callOnConnectedAndReady");
        v();
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.b
    public void c(BLEDevice bLEDevice) {
        super.c(bLEDevice);
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter]  callOnInDfuMode");
        v();
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public void d() {
        this.F = true;
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[AutoConnectPresenter] to disconnect.");
        super.d();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void f() {
        v();
        this.z.c();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void g() {
        this.z.d();
        v();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void h() {
        v();
        this.z.j();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void i() {
    }

    @Override // com.ido.ble.bluetooth.connect.c, com.ido.ble.bluetooth.connect.b
    public void j() {
        if (w()) {
            return;
        }
        x();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void k() {
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void l() {
        this.C.d();
        AutoConnectErrorHappenListener.onErrorHappen(AutoConnectErrorHappenListener.ErrorHappenType.DISCOVER_SERVICE_FAILED, this.A);
        x();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void m() {
        this.C.e();
        AutoConnectErrorHappenListener.onErrorHappen(AutoConnectErrorHappenListener.ErrorHappenType.ENABLE_NOTIFY_FAILED, this.A);
        x();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void n() {
        this.C.f();
        AutoConnectErrorHappenListener.onErrorHappen(AutoConnectErrorHappenListener.ErrorHappenType.ENABLE_NOTIFY_FAILED, this.A);
        x();
    }
}
