package com.ido.ble.bluetooth;

import android.text.TextUtils;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
/* loaded from: classes11.dex */
public class b {
    public static void a() {
        com.ido.ble.event.stat.one.c.c();
        a.j();
        u.b(1);
        BLEDevice c = com.ido.ble.f.a.f.b.e().c();
        if (c != null && !TextUtils.isEmpty(c.mDeviceAddress)) {
            com.ido.ble.f.a.f.b.e().c(c.mDeviceAddress);
        }
        u.n();
    }

    private static void a(BLEDevice bLEDevice) {
        BLEDevice c = com.ido.ble.f.a.f.b.e().c();
        if (c != null && !bLEDevice.mDeviceAddress.equals(c.mDeviceAddress)) {
            u.n();
        }
        com.ido.ble.f.a.f.b.e().a(bLEDevice);
    }

    public static void a(String str) {
        com.ido.ble.event.stat.one.c.e();
        a.d(str);
        com.ido.ble.f.a.f.b.e().d(str);
        com.ido.ble.f.a.f.a.f(str).c();
        u.b(0);
        u.D();
        u.n();
    }

    public static void b() {
        com.ido.ble.event.stat.one.c.e();
        a.l();
        BLEDevice c = com.ido.ble.f.a.f.b.e().c();
        if (c != null && !TextUtils.isEmpty(c.mDeviceAddress)) {
            com.ido.ble.f.a.f.b.e().d(c.mDeviceAddress);
        }
        com.ido.ble.f.a.f.a.g0().c();
        u.b(0);
        u.D();
        u.n();
    }

    public static void b(BLEDevice bLEDevice) {
        if (bLEDevice != null && !TextUtils.isEmpty(bLEDevice.mDeviceAddress) && ("tlwbootota".equals(bLEDevice.mDeviceAddress) || "tlwota".equals(bLEDevice.mDeviceAddress))) {
            LogTool.d(com.ido.ble.logs.a.b, "[onConnectSuccess] dfu mode not switch device");
            return;
        }
        a(bLEDevice);
        c(bLEDevice);
        com.ido.ble.bluetooth.f.c.g().f(bLEDevice.mDeviceAddress);
        com.ido.ble.f.a.f.a.g0().e(bLEDevice.mDeviceAddress);
        if (a.g()) {
            LogTool.d(com.ido.ble.logs.a.b, "[onConnectSuccess] setMode(SYS_MODE_SET_BIND)");
            u.b(1);
        } else {
            LogTool.d(com.ido.ble.logs.a.b, "[onConnectSuccess] setMode(SYS_MODE_SET_NOBIND)");
            u.b(0);
        }
        u.b((int) com.veryfit.multi.nativeprotocol.b.t5, 1);
    }

    public static void c() {
        com.ido.ble.event.stat.one.c.e();
        a.l();
        LogTool.d(com.ido.ble.logs.a.b, "[onUnbindSuccess] to disconnect.");
        a.b();
        com.ido.ble.f.a.f.a.g0().c();
        BLEDevice c = com.ido.ble.f.a.f.b.e().c();
        if (c != null && !TextUtils.isEmpty(c.mDeviceAddress)) {
            com.ido.ble.f.a.f.b.e().d(c.mDeviceAddress);
        }
        u.b(0);
        u.D();
        u.n();
    }

    private static void c(BLEDevice bLEDevice) {
        int i;
        int i2;
        if (bLEDevice == null) {
            return;
        }
        LogTool.d(com.ido.ble.logs.a.h, "[saveCurrentDeviceInfo] bleDevice=" + bLEDevice.toString());
        BLEDevice o = com.ido.ble.f.a.f.a.g0().o();
        if (o == null) {
            LogTool.d(com.ido.ble.logs.a.h, "[saveCurrentDeviceInfo] exist=null");
            com.ido.ble.f.a.f.a.g0().a(bLEDevice);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.h, "[saveCurrentDeviceInfo] exist=" + o.toString());
        boolean z = false;
        boolean z2 = true;
        if (!bLEDevice.mDeviceAddress.equals(o.mDeviceAddress)) {
            LogTool.b(com.ido.ble.logs.a.h, "[saveCurrentDeviceInfo] macAddress is not equals");
            o.mDeviceName = bLEDevice.mDeviceName;
            o.mDeviceAddress = bLEDevice.mDeviceAddress;
            o.mDeviceId = bLEDevice.mDeviceId;
            o.version = bLEDevice.version;
            z = true;
        }
        if (TextUtils.isEmpty(o.mDeviceName) && !TextUtils.isEmpty(bLEDevice.mDeviceName)) {
            o.mDeviceName = bLEDevice.mDeviceName;
            z = true;
        }
        if (!TextUtils.isEmpty(o.mDeviceName) && !TextUtils.isEmpty(bLEDevice.mDeviceName) && !o.mDeviceName.equals(bLEDevice.mDeviceName)) {
            LogTool.b(com.ido.ble.logs.a.h, "[saveCurrentDeviceInfo] mDeviceName is not equals");
            o.mDeviceName = bLEDevice.mDeviceName;
            z = true;
        }
        if (o.mDeviceId <= 10 && (i2 = bLEDevice.mDeviceId) >= 10) {
            o.mDeviceId = i2;
            z = true;
        }
        int i3 = o.type;
        if ((i3 != 0 && i3 != -1) || (i = bLEDevice.type) == 0 || i == -1) {
            z2 = z;
        } else {
            o.type = i;
        }
        if (z2) {
            LogTool.d(com.ido.ble.logs.a.h, "[saveCurrentDeviceInfo] update.");
            com.ido.ble.f.a.f.a.g0().a(o);
        }
    }
}
