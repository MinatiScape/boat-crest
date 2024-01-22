package com.ido.ble.bluetooth.connect;

import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.handler.u;
/* loaded from: classes11.dex */
abstract class d extends c implements h {
    public i z;

    public d(i iVar) {
        this.z = iVar;
    }

    @Override // com.ido.ble.bluetooth.connect.c, com.ido.ble.bluetooth.connect.b
    public void a(int i, int i2) {
        super.a(i, i2);
        u.b((int) com.veryfit.multi.nativeprotocol.b.t5, 2);
    }

    @Override // com.ido.ble.bluetooth.connect.h
    public void a(BLEDevice bLEDevice) {
    }

    @Override // com.ido.ble.bluetooth.connect.h
    public void a(BLEDevice bLEDevice, long j) {
    }

    public void a(String str, boolean z) {
    }

    @Override // com.ido.ble.bluetooth.connect.h
    public void a(byte[] bArr) {
        a(bArr, false);
    }

    public String b() {
        BLEDevice p = p();
        return p == null ? "" : p.mDeviceAddress;
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void b(BLEDevice bLEDevice) {
        com.ido.ble.bluetooth.b.b(bLEDevice);
        this.z.c(bLEDevice.mDeviceAddress);
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void c(BLEDevice bLEDevice) {
        this.z.c(bLEDevice);
    }

    @Override // com.ido.ble.bluetooth.connect.h
    public boolean c() {
        return s();
    }

    public void d() {
        u.b((int) com.veryfit.multi.nativeprotocol.b.t5, 2);
        o();
    }
}
