package com.ido.ble.bluetooth.connect;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import com.coveiot.android.leonardo.performance.Constants;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ScanCallBack;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
class m {
    private static final int j = 30000;
    private static m k;
    private String c;
    private String d;
    private String e;
    private b g;

    /* renamed from: a  reason: collision with root package name */
    private boolean f12077a = false;
    private boolean b = false;
    private int f = 0;
    private Handler h = new Handler(Looper.getMainLooper());
    private ScanCallBack.ICallBack i = new a();

    /* loaded from: classes11.dex */
    public class a implements ScanCallBack.ICallBack {

        /* renamed from: com.ido.ble.bluetooth.connect.m$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class RunnableC0572a implements Runnable {
            public RunnableC0572a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                m.this.h();
            }
        }

        public a() {
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onFindDevice(BLEDevice bLEDevice) {
            String str;
            String str2;
            if (m.this.c()) {
                if (m.this.c.equals(bLEDevice.mDeviceAddress)) {
                    str = com.ido.ble.bluetooth.f.b.f12116a;
                    str2 = "[ScanTargetDeviceTask] has find target device, start to connect";
                } else if (!com.ido.ble.bluetooth.f.d.a(m.this.d, m.this.e, bLEDevice)) {
                    return;
                } else {
                    str = com.ido.ble.bluetooth.f.b.f12116a;
                    str2 = "[ScanTargetDeviceTask] has find target device(mac + 1)";
                }
                LogTool.d(str, str2);
                m.this.f12077a = true;
                l.g().d();
                m.this.g.a(bLEDevice);
            }
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onScanFinished() {
            if (m.this.c()) {
                if (m.this.f12077a) {
                    m.this.g();
                    return;
                }
                m.j(m.this);
                int i = m.this.f < 5 ? 10000 : m.this.f < 30 ? 15000 : Constants.GENERAL_NUDGE_IMAGE_ID;
                LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanTargetDeviceTask] not find target device");
                m.this.g.a();
                if (!m.this.d() && m.this.g.a(m.this.c)) {
                    m.this.g();
                    return;
                }
                String str = com.ido.ble.bluetooth.f.b.f12116a;
                LogTool.d(str, "[ScanTargetDeviceTask] will retry after " + i + "ms, retry times = " + m.this.f);
                m.this.h.postDelayed(new RunnableC0572a(), (long) i);
            }
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onStart() {
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a();

        void a(BLEDevice bLEDevice);

        boolean a(String str);

        void b();

        void c();
    }

    private m(String str, b bVar) {
        this.c = "";
        this.d = "";
        this.e = "";
        this.c = str;
        this.d = com.ido.ble.bluetooth.f.d.a(str);
        this.e = com.ido.ble.bluetooth.f.d.b(str);
        this.g = bVar;
    }

    public static void a(String str, b bVar) {
        f();
        m mVar = new m(str, bVar);
        k = mVar;
        mVar.e();
    }

    public static boolean a() {
        m mVar = k;
        if (mVar != null) {
            mVar.b();
            return true;
        }
        return false;
    }

    private void b() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanTargetDeviceTask] cancelDelayTimer. ");
        this.h.removeCallbacksAndMessages(null);
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        if (this.b) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[ScanTargetDeviceTask] check not allowed, mIsStopTask = true.");
        } else if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[ScanTargetDeviceTask] check not allowed, phone bluetooth switch is closed.");
            this.g.b();
        } else if (!com.ido.ble.bluetooth.a.h()) {
            return true;
        } else {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[ScanTargetDeviceTask] check not allowed, isConnected = true.");
            this.g.c();
        }
        g();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        int i = this.f;
        if (i != 0 && i % 2 == 0) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanTargetDeviceTask] isNeedScan1 = false");
            return false;
        }
        PowerManager powerManager = (PowerManager) com.ido.ble.b.b().getSystemService("power");
        if (powerManager == null) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanTargetDeviceTask] isNeedScan2 = true");
            return true;
        }
        boolean isInteractive = Build.VERSION.SDK_INT >= 20 ? powerManager.isInteractive() : powerManager.isScreenOn();
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[ScanTargetDeviceTask] isNeedScan3 = " + isInteractive);
        return isInteractive;
    }

    private void e() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanTargetDeviceTask] startTask()");
        l.g().d();
        if (!d()) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanTargetDeviceTask] startTask():isNeedScan = false");
            if (this.g.a(this.c)) {
                g();
                return;
            }
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanTargetDeviceTask] startTask(): try connect direct failed, will start scan task.");
        }
        com.ido.ble.callback.b.N().b(this.i);
        com.ido.ble.callback.b.N().a(this.i);
        l.g().a(30000L);
    }

    public static void f() {
        m mVar = k;
        if (mVar != null) {
            mVar.g();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.b) {
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanTargetDeviceTask] stopTask()");
        this.b = true;
        this.h.removeCallbacksAndMessages(null);
        l.g().d();
        com.ido.ble.callback.b.N().b(this.i);
        this.f = 0;
        k = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (c()) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanTargetDeviceTask] start again ...");
            l.g().a(30000L);
        }
    }

    public static /* synthetic */ int j(m mVar) {
        int i = mVar.f;
        mVar.f = i + 1;
        return i;
    }
}
