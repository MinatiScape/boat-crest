package com.ido.ble.bluetooth.connect;

import android.bluetooth.BluetoothGattCharacteristic;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class f extends d {
    private static h B;
    private com.ido.ble.bluetooth.connect.r.b A;

    private f(i iVar) {
        super(iVar);
        this.A = new com.ido.ble.bluetooth.connect.r.c();
    }

    public static h a(i iVar) {
        if (B == null) {
            B = new f(iVar);
        }
        return B;
    }

    @Override // com.ido.ble.bluetooth.connect.c
    public /* bridge */ /* synthetic */ Object a(Object obj, String str) {
        return super.a(obj, str);
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.c, com.ido.ble.bluetooth.connect.b
    public void a(int i, int i2) {
        super.a(i, i2);
        this.z.a(i, i2, b());
        this.A.b(i, i2);
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public void a(BLEDevice bLEDevice) {
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[DfuConnectPresenter] to connect device, bleDevice is " + bLEDevice.toString());
        d(bLEDevice);
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public /* bridge */ /* synthetic */ void a(BLEDevice bLEDevice, long j) {
        super.a(bLEDevice, j);
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public /* bridge */ /* synthetic */ void a(String str, boolean z) {
        super.a(str, z);
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public /* bridge */ /* synthetic */ void a(byte[] bArr) {
        super.a(bArr);
    }

    @Override // com.ido.ble.bluetooth.connect.h
    public boolean a() {
        return t();
    }

    @Override // com.ido.ble.bluetooth.connect.c
    public /* bridge */ /* synthetic */ boolean a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return super.a(bluetoothGattCharacteristic);
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public /* bridge */ /* synthetic */ String b() {
        return super.b();
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
    public /* bridge */ /* synthetic */ boolean c() {
        return super.c();
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public /* bridge */ /* synthetic */ void d() {
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
        this.z.onConnectStart(b());
        this.A.b();
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
