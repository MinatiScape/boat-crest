package com.ido.ble.dfu.e.b;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.common.e;
import com.ido.ble.logs.LogTool;
import java.util.List;
/* loaded from: classes11.dex */
public class c {
    private static final long l = 30000;
    private static boolean m = false;
    private static final int n = 3;

    /* renamed from: a  reason: collision with root package name */
    private BluetoothAdapter f12200a;
    private Handler b;
    private d c;
    private String d;
    private String h;
    private String i;
    private boolean e = false;
    private int f = 0;
    private int g = 0;
    private BluetoothAdapter.LeScanCallback j = new a();
    private Runnable k = new b();

    /* loaded from: classes11.dex */
    public class a implements BluetoothAdapter.LeScanCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            if (c.this.d.equals(bluetoothDevice.getAddress()) || c.this.h.equals(bluetoothDevice.getAddress()) || c.this.i.equals(bluetoothDevice.getAddress())) {
                LogTool.d(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] -------onLeScan :" + bluetoothDevice.getAddress());
                if (!com.ido.ble.bluetooth.e.a.a(bArr)) {
                    BLEDevice a2 = c.this.a(bluetoothDevice, i, bArr);
                    if (a2 == null) {
                        LogTool.b(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] has find target device, but device para is null");
                        return;
                    }
                    c.this.e = true;
                    LogTool.d(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] has find target device, is not in dfu mode");
                    c.this.c();
                    c.this.c.c(a2);
                    return;
                }
                c.this.e = true;
                LogTool.d(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] has find target device, is in dfu mode:" + bluetoothDevice.getAddress());
                c.this.c();
                BLEDevice bLEDevice = new BLEDevice();
                bLEDevice.mDeviceAddress = bluetoothDevice.getAddress();
                c.this.c.b(bLEDevice);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.f();
        }
    }

    /* renamed from: com.ido.ble.dfu.e.b.c$c  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class RunnableC0593c implements Runnable {
        public RunnableC0593c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.h();
        }
    }

    /* loaded from: classes11.dex */
    public interface d {
        void a();

        void a(BLEDevice bLEDevice);

        void b(BLEDevice bLEDevice);

        void c(BLEDevice bLEDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BLEDevice a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        byte[] b2 = com.ido.ble.bluetooth.e.b.b(bArr);
        BLEDevice bLEDevice = new BLEDevice();
        if (b2 != null && b2.length > 2) {
            bLEDevice.mDeviceId = ((b2[1] & 255) << 8) | (b2[0] & 255);
        }
        String name = bluetoothDevice.getName();
        if (TextUtils.isEmpty(name)) {
            name = com.ido.ble.bluetooth.e.b.a(bArr);
            if (TextUtils.isEmpty(name)) {
                return null;
            }
        }
        String address = bluetoothDevice.getAddress();
        bLEDevice.mDeviceName = name;
        bLEDevice.mDeviceAddress = address;
        bLEDevice.mRssi = i;
        if (!com.ido.ble.bluetooth.e.b.d(bArr)) {
            LogTool.b(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] has find target device, but broadcast data is invalid");
        }
        return bLEDevice;
    }

    private boolean b() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.d(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] task finished.");
        g();
    }

    private void d() {
        this.f12200a = BluetoothAdapter.getDefaultAdapter();
        this.b = new Handler(Looper.getMainLooper());
    }

    private boolean e() {
        BLEDevice c;
        List<BluetoothDevice> connectedDevices = ((BluetoothManager) e.a().getSystemService("bluetooth")).getConnectedDevices(7);
        if (connectedDevices != null && connectedDevices.size() != 0) {
            for (BluetoothDevice bluetoothDevice : connectedDevices) {
                if (this.d.equals(bluetoothDevice.getAddress()) && (c = com.ido.ble.f.a.f.b.e().c()) != null && this.d.equals(c.mDeviceAddress)) {
                    LogTool.b(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] target device is connected by other app");
                    this.c.a(c);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogTool.d(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] a scan work finished.");
        this.b.removeCallbacks(this.k);
        this.f12200a.stopLeScan(this.j);
        if (this.e) {
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] not find target device,  wait for restart....");
        this.b.postDelayed(new RunnableC0593c(), 3000L);
    }

    private void g() {
        this.b.removeCallbacksAndMessages(null);
        this.f12200a.stopLeScan(this.j);
        m = false;
        this.f = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            c();
            this.c.a();
            LogTool.b(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] bluetooth switch is closed, task finished!");
            return;
        }
        this.f++;
        LogTool.b(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] restart times is " + this.f);
        if (this.f < this.g) {
            i();
            return;
        }
        c();
        LogTool.d(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] out of max retry times, task finished!");
        this.c.a();
    }

    private void i() {
        LogTool.d(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] startScanDevices()");
        String str = Build.MANUFACTURER;
        if (!str.equalsIgnoreCase("xiaomi")) {
            str.equalsIgnoreCase("meizu");
        }
        this.b.removeCallbacks(this.k);
        this.b.postDelayed(this.k, 30000L);
        this.f12200a.startLeScan(this.j);
    }

    public void a() {
        if (m) {
            LogTool.d(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] stop task");
            g();
        }
    }

    public void a(d dVar, String str) {
        a(dVar, str, 3);
    }

    public void a(d dVar, String str, int i) {
        this.g = i;
        LogTool.d(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] start");
        if (m) {
            LogTool.b(com.ido.ble.dfu.a.b, "[ScanTargetDFUDeviceTask] at state of scanning, ignore this action");
            return;
        }
        this.c = dVar;
        this.d = str;
        this.h = com.ido.ble.bluetooth.f.d.a(str);
        this.i = com.ido.ble.bluetooth.f.d.b(this.d);
        if (!b()) {
            m = false;
            return;
        }
        d();
        if (e()) {
            return;
        }
        i();
        m = true;
    }
}
