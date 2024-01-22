package com.ido.ble.bluetooth.connect;

import com.ido.ble.bluetooth.device.BLEDevice;
/* loaded from: classes11.dex */
public interface i {
    void a(int i, int i2);

    void a(int i, int i2, String str);

    void a(BLEDevice bLEDevice);

    void a(BLEDevice bLEDevice, long j);

    void a(String str);

    void a(byte[] bArr);

    boolean a();

    String b();

    void b(BLEDevice bLEDevice);

    void b(String str);

    void c();

    void c(BLEDevice bLEDevice);

    void c(String str);

    void d();

    void e();

    void f();

    void g();

    void h();

    void i();

    boolean isConnectedAndReady();

    void j();

    void k();

    void l();

    void m();

    void onConnectStart(String str);

    void onConnecting(String str);

    void onRetry(int i, String str);
}
