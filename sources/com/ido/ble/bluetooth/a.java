package com.ido.ble.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.text.TextUtils;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.connect.g;
import com.ido.ble.bluetooth.connect.l;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.callback.ScanCallBack;
import com.ido.ble.common.m;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.custom.connect.CustomBytesDataSendManager;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
/* loaded from: classes11.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f12038a = false;

    /* renamed from: com.ido.ble.bluetooth.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class RunnableC0567a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BLEDevice f12039a;
        public final /* synthetic */ long b;

        public RunnableC0567a(BLEDevice bLEDevice, long j) {
            this.f12039a = bLEDevice;
            this.b = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.ido.ble.f.a.f.a.g0().e(this.f12039a.mDeviceAddress);
            com.ido.ble.bluetooth.f.c.g().f(this.f12039a.mDeviceAddress);
            if (this.b > 0) {
                com.ido.ble.bluetooth.connect.e.n().a(this.f12039a, this.b);
            } else {
                com.ido.ble.bluetooth.connect.e.n().b(this.f12039a);
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BLEDevice f12040a;

        public b(BLEDevice bLEDevice) {
            this.f12040a = bLEDevice;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.ido.ble.f.a.f.a.g0().e(this.f12040a.mDeviceAddress);
            com.ido.ble.bluetooth.f.c.g().f(this.f12040a.mDeviceAddress);
            com.ido.ble.bluetooth.connect.e.n().a(this.f12040a);
        }
    }

    /* loaded from: classes11.dex */
    public static class c implements g.c {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f12041a;

        public c(String str) {
            this.f12041a = str;
        }

        @Override // com.ido.ble.bluetooth.connect.g.c
        public void a() {
            a.e(this.f12041a);
        }
    }

    /* loaded from: classes11.dex */
    public static class d implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f12042a;

        public d(String str) {
            this.f12042a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.ido.ble.bluetooth.connect.e.n().a(this.f12042a);
        }
    }

    /* loaded from: classes11.dex */
    public static class e implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            com.ido.ble.bluetooth.connect.e.n().g();
        }
    }

    /* loaded from: classes11.dex */
    public static class f implements g.c {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Runnable f12043a;

        public f(Runnable runnable) {
            this.f12043a = runnable;
        }

        @Override // com.ido.ble.bluetooth.connect.g.c
        public void a() {
            boolean unused = a.f12038a = false;
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[disconnect] finished.");
            this.f12043a.run();
        }
    }

    public static void a() {
        if (!h()) {
            e(d());
            return;
        }
        LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[DeviceManager] autoConnect. in connect state, ignore");
        ConnectCallBack.c(d());
    }

    public static void a(long j) {
        l.g().a(j);
    }

    public static void a(long j, String str) {
        l.g().a(j, str);
    }

    public static void a(BLEDevice bLEDevice) {
        com.ido.ble.common.e.a(new b(bLEDevice));
    }

    public static void a(BLEDevice bLEDevice, long j) {
        if (e().equals(bLEDevice.mDeviceAddress)) {
            if (h()) {
                LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[DeviceManager] straightConnect. in connect state, ignore");
                ConnectCallBack.c(bLEDevice.mDeviceAddress);
                return;
            } else if (i()) {
                LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[DeviceManager] straightConnect. in connecting state, ignore");
                ConnectCallBack.d(e());
                return;
            }
        }
        com.ido.ble.common.e.a(new RunnableC0567a(bLEDevice, j));
    }

    public static void a(Runnable runnable) {
        if (f12038a) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[disconnect] isDisconnecting = true.");
            return;
        }
        f12038a = true;
        new g().a(new f(runnable));
    }

    public static void a(String str, String str2) {
        String[] split = str.split(str2);
        byte[] bArr = new byte[split.length];
        for (int i = 0; i < split.length; i++) {
            bArr[i] = (byte) Integer.parseInt(split[i], 16);
        }
        b(bArr);
    }

    public static void a(byte[] bArr) {
        if (CustomConfig.getConfig().isCustomManageConnection()) {
            CustomBytesDataSendManager.getManager().addCmdData(bArr, false);
        } else {
            com.ido.ble.bluetooth.connect.e.n().a(bArr);
        }
    }

    public static void b() {
        com.ido.ble.common.e.a(new e());
    }

    public static void b(BLEDevice bLEDevice) {
        a(bLEDevice, 0L);
    }

    public static void b(String str) {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[DeviceManager] autoconnect:");
        com.ido.ble.f.a.f.a.g0().e(str);
        com.ido.ble.bluetooth.f.c.g().f(str);
        new g().a(new c(str));
    }

    public static void b(boolean z) {
        com.ido.ble.bluetooth.f.c.g().a(z);
    }

    public static void b(byte[] bArr) {
        com.ido.ble.bluetooth.connect.e.n().a(bArr);
    }

    public static String c() {
        return com.ido.ble.bluetooth.f.c.g().c();
    }

    public static boolean c(String str) {
        if (h() && !TextUtils.isEmpty(str)) {
            return str.equals(e());
        }
        return false;
    }

    public static String d() {
        return com.ido.ble.bluetooth.f.c.g().d();
    }

    public static void d(String str) {
        com.ido.ble.bluetooth.f.c.g(str).b();
    }

    public static String e() {
        return com.ido.ble.bluetooth.connect.e.n().b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[DeviceManager] autoConnect. mac == null");
            return;
        }
        com.ido.ble.f.a.f.a.g0().e(str);
        com.ido.ble.bluetooth.f.c.g().f(str);
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[DeviceManager] autoConnect.");
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[autoConnect()] phone bluetooth switch is closed.");
            ConnectCallBack.a(ConnectFailedReason.BLUETOOTH_SWITCH_CLOSED, str);
        } else if (!m.j()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[ScanManager] hasPhoneBluetoothPermission false.");
            String str2 = com.ido.ble.bluetooth.f.b.f12116a;
            LogTool.b(str2, "[ScanManager] " + m.b());
            ScanCallBack.a();
        } else if (!g()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[autoConnect()] failed, is not bind!");
            ConnectCallBack.a(ConnectFailedReason.NOT_IN_BIND_STATUS, str);
        } else if (TextUtils.isEmpty(str)) {
            ConnectCallBack.a(ConnectFailedReason.MAC_ADDRESS_INVALID, str);
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[autoConnect()] failed, mac address is empty");
        } else {
            u.b(1);
            com.ido.ble.common.e.a(new d(str));
        }
    }

    public static String f() {
        return com.ido.ble.bluetooth.f.c.g().e();
    }

    public static void f(String str) {
        if (e().equals(str)) {
            if (h()) {
                LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[DeviceManager] scanAndConnect. in connect state, ignore");
                ConnectCallBack.c(str);
                return;
            } else if (i()) {
                LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[DeviceManager] scanAndConnect. in connecting state, ignore");
                ConnectCallBack.d(e());
                return;
            }
        }
        com.ido.ble.e.b.c().a(str);
    }

    public static void g(String str) {
        com.ido.ble.bluetooth.f.c.g().c(str);
    }

    public static boolean g() {
        return com.ido.ble.bluetooth.f.c.g().f();
    }

    public static void h(String str) {
        com.ido.ble.bluetooth.f.c.g().d(str);
    }

    public static boolean h() {
        return CustomConfig.getConfig().isCustomManageConnection() ? CustomConfig.getConfig().getIEnableNotifyCallback().isConnectedAndReady() : com.ido.ble.bluetooth.connect.e.n().isConnectedAndReady();
    }

    public static void i(String str) {
        com.ido.ble.bluetooth.f.c.g().e(str);
    }

    public static boolean i() {
        return com.ido.ble.bluetooth.connect.e.n().a();
    }

    public static void j() {
        com.ido.ble.bluetooth.f.c.g().f(e());
        b(true);
        h(e());
    }

    public static void j(String str) {
        a(-1L, str);
    }

    public static void k() {
    }

    public static void l() {
        com.ido.ble.bluetooth.f.c.g().b();
    }

    public static void m() {
        a(-1L);
    }

    public static void n() {
        l.g().d();
    }
}
