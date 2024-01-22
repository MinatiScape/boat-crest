package com.realsil.sdk.core.b;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ParcelUuid;
import androidx.annotation.NonNull;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice;
import com.realsil.sdk.core.bluetooth.scanner.ScannerCallback;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.bluetooth.utils.BluetoothUuid;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public abstract class a {
    public static final int STATE_DISCOVERY_FINISHED = 3;
    public static final int STATE_DISCOVERY_STARTED = 2;
    public static final int STATE_DISCOVERY_START_PROCESS = 1;
    public static final int STATE_IDLE = 0;
    public Context c;
    public ScannerParams d;
    public ScannerCallback e;
    public Handler f;
    public BluetoothAdapter g;

    /* renamed from: a  reason: collision with root package name */
    public boolean f13545a = false;
    public boolean b = false;
    public int h = 0;
    public boolean i = false;
    public long j = 0;
    public final BroadcastReceiver k = new C0716a();
    public Runnable l = new b();
    public Runnable m = new c();
    public boolean n = false;
    public Runnable o = new d();

    /* renamed from: com.realsil.sdk.core.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class C0716a extends BroadcastReceiver {

        /* renamed from: com.realsil.sdk.core.b.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public class RunnableC0717a implements Runnable {
            public RunnableC0717a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.stopScan();
            }
        }

        public C0716a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                ZLogger.v(String.format(Locale.US, "[%s] %d -> %d", action, Integer.valueOf(intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1)), Integer.valueOf(intExtra)));
                if (intExtra == 10 && a.this.isScanning()) {
                    new Thread(new RunnableC0717a()).start();
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            a aVar = a.this;
            if (timeInMillis < aVar.j) {
                aVar.j = 0L;
            }
            long j = timeInMillis - aVar.j;
            int i = aVar.h;
            if (i == 1) {
                if (j > 30000) {
                    ZLogger.d(String.format(Locale.US, "no scan response received after start scan for %d ms", 30000L));
                    a.this.e();
                }
            } else if (i == 2) {
                if (j > 30000) {
                    ZLogger.d(String.format(Locale.US, "exceed %d ms , no scan response received since last time", 30000L));
                    a.this.e();
                    return;
                }
                a.a(aVar);
            } else {
                boolean z = aVar.b;
                ZLogger.v(z, "ignore state:" + a.this.h);
                a.a(a.this);
            }
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ZLogger.v(a.this.b, "scan delay time reached");
            a.this.e();
        }
    }

    /* loaded from: classes12.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            ScannerCallback scannerCallback = aVar.e;
            if (scannerCallback != null) {
                scannerCallback.onAutoScanTrigger();
            } else {
                ZLogger.v(aVar.b, "no callback registed");
            }
            a.this.startScan();
        }
    }

    public boolean a() {
        if (this.i) {
            ZLogger.d("please call onDestroy() method first");
            return false;
        }
        this.f13545a = RtkCore.DEBUG;
        this.b = RtkCore.VDBG;
        if (Build.VERSION.SDK_INT >= 18) {
            BluetoothManager bluetoothManager = (BluetoothManager) this.c.getSystemService("bluetooth");
            if (bluetoothManager != null) {
                this.g = bluetoothManager.getAdapter();
            }
        } else {
            this.g = BluetoothAdapter.getDefaultAdapter();
        }
        if (this.d == null) {
            ZLogger.v(this.b, "create new ScannerParams");
            this.d = new ScannerParams();
        }
        if (this.f == null) {
            HandlerThread handlerThread = new HandlerThread("ScannerPresenter");
            handlerThread.start();
            this.f = new Handler(handlerThread.getLooper());
        }
        if (this.e == null) {
            ZLogger.v(this.b, "callback is null");
        }
        this.c.registerReceiver(this.k, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        this.i = true;
        ZLogger.v("initialized");
        return true;
    }

    public abstract boolean a(@NonNull BluetoothDevice bluetoothDevice);

    public boolean a(ExtendedBluetoothDevice extendedBluetoothDevice) {
        return true;
    }

    public boolean b() {
        if (this.f != null) {
            ZLogger.v(this.b, String.format(Locale.US, "wait to check scan period(%d)", 30000L));
            this.f.removeCallbacks(this.l);
            return this.f.postDelayed(this.l, 30000L);
        }
        ZLogger.v(this.b, "mHandler == null");
        return false;
    }

    public boolean c() {
        int i = this.h;
        if (i != 1 && i != 2) {
            a(1);
            this.f.removeCallbacks(this.m);
            this.f.removeCallbacks(this.l);
            this.f.removeCallbacks(this.o);
            this.j = 0L;
            ScannerParams scannerParams = this.d;
            if (scannerParams != null) {
                this.n = scannerParams.isAutoDiscovery();
            } else {
                this.n = false;
            }
            return true;
        }
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (timeInMillis < this.j) {
            this.j = 0L;
        }
        if (timeInMillis - this.j > 30000) {
            ZLogger.d(String.format(Locale.US, "exceed %d ms , no scan response received since last time", 30000L));
            e();
        } else {
            b();
        }
        return false;
    }

    public boolean d() {
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacks(this.m);
            this.f.removeCallbacks(this.l);
            this.f.removeCallbacks(this.o);
            return true;
        }
        return true;
    }

    public abstract boolean e();

    public BluetoothAdapter getBluetoothAdapter() {
        return this.g;
    }

    public List<ExtendedBluetoothDevice> getPairedDevices() {
        ArrayList arrayList = new ArrayList();
        if (this.g == null) {
            return arrayList;
        }
        if (!this.d.isReusePaiedDeviceEnabled()) {
            ZLogger.v(this.f13545a, "don't reuse paired device");
            return arrayList;
        }
        for (BluetoothDevice bluetoothDevice : this.g.getBondedDevices()) {
            ZLogger.v(BluetoothHelper.dumpBluetoothDevice(bluetoothDevice));
            if (a(bluetoothDevice)) {
                arrayList.add(new ExtendedBluetoothDevice(bluetoothDevice, bluetoothDevice.getName(), -1000, bluetoothDevice.getBondState() == 12, false));
            }
        }
        return arrayList;
    }

    public List<ExtendedBluetoothDevice> getPairedDevicesByProfile(int i) {
        if (i != 1) {
            return getPairedDevices();
        }
        ParcelUuid[] parcelUuidArr = BluetoothUuid.HEADSET_PROFILE_UUIDS;
        ArrayList arrayList = new ArrayList();
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter != null) {
            for (BluetoothDevice bluetoothDevice : bluetoothAdapter.getBondedDevices()) {
                if (BluetoothUuid.containsAnyUuid(bluetoothDevice.getUuids(), parcelUuidArr)) {
                    arrayList.add(new ExtendedBluetoothDevice(bluetoothDevice, bluetoothDevice.getName(), -1000, bluetoothDevice.getBondState() == 12, BluetoothProfileManager.getInstance().getConnectionState(1, bluetoothDevice) == 2 || BluetoothProfileManager.getInstance().getConnectionState(2, bluetoothDevice) == 2));
                }
            }
        }
        return arrayList;
    }

    public int getState() {
        return this.h;
    }

    public boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = this.g;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    public boolean isBluetoothSupported() {
        return this.g != null;
    }

    public boolean isScanning() {
        int i = this.h;
        return i == 2 || i == 1;
    }

    public void onDestroy() {
        Context context = this.c;
        if (context != null) {
            try {
                context.unregisterReceiver(this.k);
            } catch (Exception e) {
                ZLogger.e(this.b, e.toString());
            }
        }
        this.e = null;
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacks(this.m);
            this.f.removeCallbacks(this.o);
            this.f.removeCallbacks(this.l);
            this.f = null;
        }
        stopScan();
        this.i = false;
    }

    public synchronized boolean scanDevice(boolean z) {
        if (z) {
            return startScan();
        }
        return stopScan();
    }

    public void setScanMode(int i) {
        ScannerParams scannerParams = this.d;
        if (scannerParams != null) {
            scannerParams.setScanMode(i);
        }
    }

    public void setScannerCallback(ScannerCallback scannerCallback) {
        this.e = scannerCallback;
        if (scannerCallback == null) {
            ZLogger.v(this.b, "callback is null");
        }
    }

    public void setScannerParams(ScannerParams scannerParams) {
        this.d = scannerParams;
    }

    public abstract boolean startScan();

    public abstract boolean stopScan();

    public synchronized boolean scanDevice(boolean z, boolean z2) {
        if (z) {
            return startScan();
        }
        return stopScan();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice> getPairedDevices(int r12) {
        /*
            r11 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.bluetooth.BluetoothAdapter r1 = r11.g
            if (r1 != 0) goto La
            return r0
        La:
            java.util.Set r1 = r1.getBondedDevices()
            java.util.Iterator r1 = r1.iterator()
        L12:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L91
            java.lang.Object r2 = r1.next()
            r4 = r2
            android.bluetooth.BluetoothDevice r4 = (android.bluetooth.BluetoothDevice) r4
            android.bluetooth.BluetoothClass r2 = r4.getBluetoothClass()
            int r2 = r2.getMajorDeviceClass()
            r3 = 1024(0x400, float:1.435E-42)
            r5 = 0
            if (r2 == r12) goto L39
            if (r3 != r12) goto L12
            android.bluetooth.BluetoothClass r2 = r4.getBluetoothClass()
            boolean r2 = com.realsil.sdk.core.bluetooth.impl.BluetoothClassImpl.doesClassMatch(r2, r5)
            if (r2 != 0) goto L39
            goto L12
        L39:
            r2 = 1
            if (r3 != r12) goto L73
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r3 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            int r3 = r3.getConnectionState(r2, r4)
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r6 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            r7 = 2
            int r6 = r6.getConnectionState(r7, r4)
            java.util.Locale r8 = java.util.Locale.US
            r9 = 3
            java.lang.Object[] r9 = new java.lang.Object[r9]
            java.lang.String r10 = r4.getAddress()
            r9[r5] = r10
            java.lang.Integer r10 = java.lang.Integer.valueOf(r3)
            r9[r2] = r10
            java.lang.Integer r10 = java.lang.Integer.valueOf(r6)
            r9[r7] = r10
            java.lang.String r10 = "%s, hfpState= %d,a2dpState= %d"
            java.lang.String r8 = java.lang.String.format(r8, r10, r9)
            com.realsil.sdk.core.logger.ZLogger.v(r8)
            if (r7 == r3) goto L71
            if (r7 != r6) goto L73
        L71:
            r8 = r2
            goto L74
        L73:
            r8 = r5
        L74:
            com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice r9 = new com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice
            java.lang.String r6 = r4.getName()
            r7 = -1000(0xfffffffffffffc18, float:NaN)
            int r3 = r4.getBondState()
            r10 = 12
            if (r3 != r10) goto L85
            goto L86
        L85:
            r2 = r5
        L86:
            r3 = r9
            r5 = r6
            r6 = r7
            r7 = r2
            r3.<init>(r4, r5, r6, r7, r8)
            r0.add(r9)
            goto L12
        L91:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.b.a.getPairedDevices(int):java.util.List");
    }

    public void a(int i) {
        int i2 = this.h;
        if (i2 != i) {
            if (this.f13545a) {
                ZLogger.d(String.format(Locale.US, "ScanState 0x%02X >> 0x%02X", Integer.valueOf(i2), Integer.valueOf(i)));
            }
            this.h = i;
            ScannerCallback scannerCallback = this.e;
            if (scannerCallback != null) {
                scannerCallback.onScanStateChanged(i);
            } else {
                ZLogger.v(this.b, "no callback registed");
            }
        }
        int i3 = this.h;
        if (i3 == 0 || i3 == 3) {
            Handler handler = this.f;
            if (handler != null) {
                handler.removeCallbacks(this.m);
                this.f.removeCallbacks(this.l);
                this.f.removeCallbacks(this.o);
            }
            boolean z = this.n;
            if (z) {
                if (this.f != null) {
                    ZLogger.v(this.f13545a, "wait to start auto scan");
                    this.f.postDelayed(this.o, this.d.getAutoScanDelay());
                }
            } else if (this.b) {
                ZLogger.v(String.format("continousScanEnabled=%b", Boolean.valueOf(z)));
            }
        }
    }

    public boolean a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        boolean a2;
        this.j = Calendar.getInstance().getTimeInMillis();
        int i2 = this.h;
        if (i2 == 1) {
            a(2);
        } else if (i2 != 2) {
            ZLogger.v(String.format("stop to calibration state: 0x%04X", Integer.valueOf(i2)));
            e();
            return false;
        }
        if (bluetoothDevice == null) {
            ZLogger.d("ignore, device is null");
            return false;
        }
        if (this.d.getRssiFilter() > -1000 && this.d.getRssiFilter() > i) {
            ZLogger.w("filter, low rssi:" + i);
            a2 = false;
        } else {
            a2 = a(bluetoothDevice);
        }
        if (a2) {
            ExtendedBluetoothDevice extendedBluetoothDevice = new ExtendedBluetoothDevice(bluetoothDevice, bluetoothDevice.getName(), i, bluetoothDevice.getBondState() == 12, false, bArr);
            if (a(extendedBluetoothDevice)) {
                ScannerCallback scannerCallback = this.e;
                if (scannerCallback != null) {
                    scannerCallback.onNewDevice(extendedBluetoothDevice);
                } else {
                    ZLogger.v(this.b, "no callback registed");
                }
                if (this.d.getScanMechanism() == 1) {
                    ZLogger.d("SCAN_MECHANISM_FILTER_ONE > scanDevice(false)");
                    e();
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public static void a(a aVar) {
        Handler handler = aVar.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages(aVar.m);
            aVar.f.postDelayed(aVar.m, aVar.d.getScanPeriod());
            return;
        }
        ZLogger.v(aVar.b, "mHandler == null");
    }
}
