package com.jieli.jl_bt_ota.impl;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.annotation.RequiresApi;
import com.jieli.jl_bt_ota.model.BleScanMessage;
import com.jieli.jl_bt_ota.model.OTAError;
import com.jieli.jl_bt_ota.util.BluetoothUtil;
import com.jieli.jl_bt_ota.util.CommonUtil;
import com.jieli.jl_bt_ota.util.JL_Log;
import com.szabh.smable3.component.BleMessenger;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public abstract class BluetoothDiscovery extends BluetoothBase {
    private static final int p = 4660;
    private static final int q = 4661;
    private final List<BluetoothDevice> e;
    private final List<BluetoothDevice> f;
    private BluetoothDiscoveryReceiver g;
    private BluetoothLeScanner h;
    private volatile int i;
    private volatile boolean j;
    private volatile boolean k;
    private long l;
    private final Handler m;
    private final BluetoothAdapter.LeScanCallback n;
    @RequiresApi(21)
    private final ScanCallback o;

    /* loaded from: classes11.dex */
    public class BluetoothDiscoveryReceiver extends BroadcastReceiver {
        private BluetoothDiscoveryReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        @SuppressLint({"MissingPermission"})
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice;
            String action = intent.getAction();
            if ("android.bluetooth.adapter.action.DISCOVERY_STARTED".equals(action)) {
                BluetoothDiscovery.this.k = true;
                BluetoothDiscovery.this.m.removeMessages(BluetoothDiscovery.p);
                BluetoothDiscovery.this.m.sendEmptyMessageDelayed(BluetoothDiscovery.p, BluetoothDiscovery.this.l);
                BluetoothDiscovery.this.a(true);
            } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                BluetoothDiscovery.this.k = false;
                BluetoothDiscovery.this.m.removeMessages(BluetoothDiscovery.p);
                BluetoothDiscovery.this.b();
                BluetoothDiscovery.this.a(false);
            } else if ("android.bluetooth.device.action.FOUND".equals(action) && (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) != null && BluetoothDiscovery.this.c() && CommonUtil.checkHasConnectPermission(BluetoothDiscovery.this.context)) {
                boolean z = (BluetoothDiscovery.this.i == 1 && bluetoothDevice.getType() != 2) || (BluetoothDiscovery.this.i == 0 && bluetoothDevice.getType() != 1) || BluetoothDiscovery.this.i == 2;
                short shortExtra = intent.getShortExtra("android.bluetooth.device.extra.RSSI", (short) 0);
                if (!z || BluetoothDiscovery.this.f.contains(bluetoothDevice)) {
                    return;
                }
                BluetoothDiscovery.this.f.add(bluetoothDevice);
                BluetoothDiscovery.this.mBtEventCbHelper.onDiscovery(bluetoothDevice, new BleScanMessage().setRssi(shortExtra).setEnableConnect(true));
            }
        }
    }

    public BluetoothDiscovery(Context context) {
        super(context);
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.j = false;
        this.k = false;
        this.l = BleMessenger.TIMEOUT;
        this.m = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jieli.jl_bt_ota.impl.b
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                boolean a2;
                a2 = BluetoothDiscovery.this.a(message);
                return a2;
            }
        });
        this.n = new BluetoothAdapter.LeScanCallback() { // from class: com.jieli.jl_bt_ota.impl.a
            @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
            public final void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                BluetoothDiscovery.this.a(bluetoothDevice, i, bArr);
            }
        };
        this.o = new ScanCallback() { // from class: com.jieli.jl_bt_ota.impl.BluetoothDiscovery.1
            @Override // android.bluetooth.le.ScanCallback
            public void onBatchScanResults(List<ScanResult> list) {
                super.onBatchScanResults(list);
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanFailed(int i) {
                super.onScanFailed(i);
                BluetoothDiscovery.this.onError(OTAError.buildError(8194, i, "Scan ble error."));
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanResult(int i, ScanResult scanResult) {
                super.onScanResult(i, scanResult);
                if (scanResult == null || scanResult.getScanRecord() == null) {
                    return;
                }
                BluetoothDiscovery.this.a(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes(), Build.VERSION.SDK_INT >= 26 ? scanResult.isConnectable() : true);
            }
        };
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null || Build.VERSION.SDK_INT < 21) {
            return;
        }
        this.h = bluetoothAdapter.getBluetoothLeScanner();
    }

    public ArrayList<BluetoothDevice> getDiscoveredBluetoothDevices() {
        if (this.i == 0) {
            return new ArrayList<>(this.e);
        }
        return new ArrayList<>(this.f);
    }

    public int getScanType() {
        return this.i;
    }

    public boolean isBleScanning() {
        return this.j;
    }

    public boolean isDeviceScanning() {
        return this.k;
    }

    public boolean isScanning() {
        return this.k || this.j;
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothBase
    public void onAdapterStatus(boolean z, boolean z2) {
        super.onAdapterStatus(z, z2);
        if (z) {
            return;
        }
        if (isDeviceScanning()) {
            a(false);
        }
        d();
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothBase, com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public void release() {
        super.release();
        b();
        stopDeviceScan();
        stopBLEScan();
        d();
        this.m.removeCallbacksAndMessages(null);
    }

    public void setScanType(int i) {
        this.i = i;
    }

    @SuppressLint({"MissingPermission"})
    public int startBLEScan(long j) {
        ScanSettings build;
        if (!CommonUtil.checkHasScanPermission(this.context)) {
            JL_Log.e(this.TAG, "startBLEScan : no scan permission");
            return 4113;
        } else if (!CommonUtil.checkHasLocationPermission(this.context)) {
            JL_Log.e(this.TAG, "startBLEScan : no location permission");
            return 4113;
        } else if (this.mBluetoothAdapter == null) {
            JL_Log.e(this.TAG, "startBLEScan : this device is not supported bluetooth.");
            return 4113;
        } else if (!c()) {
            JL_Log.e(this.TAG, "startBLEScan : bluetooth is close.");
            return 4099;
        } else {
            if (this.k) {
                JL_Log.d(this.TAG, "startBLEScan : stopDeviceScan");
                if (stopDeviceScan() == 0) {
                    int i = 0;
                    do {
                        SystemClock.sleep(30L);
                        i += 30;
                        if (i > 300) {
                            break;
                        }
                    } while (this.mBluetoothAdapter.isDiscovering());
                }
            }
            setScanType(0);
            if (j <= 0) {
                j = BleMessenger.TIMEOUT;
            }
            if (this.j) {
                JL_Log.i(this.TAG, "scanning ble ..... timeout = " + j);
                BluetoothLeScanner bluetoothLeScanner = this.h;
                if (bluetoothLeScanner != null && Build.VERSION.SDK_INT >= 21) {
                    bluetoothLeScanner.flushPendingScanResults(this.o);
                }
                this.m.removeMessages(q);
                this.m.sendEmptyMessageDelayed(q, j);
                a(true);
                return 0;
            }
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 21 && this.h != null) {
                if (i2 >= 23) {
                    build = new ScanSettings.Builder().setScanMode(this.mBluetoothOption.getBleScanMode()).setMatchMode(1).build();
                } else {
                    build = new ScanSettings.Builder().setScanMode(this.mBluetoothOption.getBleScanMode()).build();
                }
                this.h.startScan(new ArrayList(), build, this.o);
                JL_Log.w(this.TAG, "-startBLEScan- >>>>>> startScan :>> timeout = " + j);
            } else {
                boolean startLeScan = this.mBluetoothAdapter.startLeScan(this.n);
                JL_Log.w(this.TAG, "-startBLEScan- >>>>>> startLeScan : " + startLeScan);
                if (!startLeScan) {
                    return 8194;
                }
            }
            JL_Log.i(this.TAG, "-startBLEScan- timeout = " + j);
            this.m.removeMessages(q);
            this.m.sendEmptyMessageDelayed(q, j);
            a(true);
            return 0;
        }
    }

    @SuppressLint({"MissingPermission"})
    public int startDeviceScan(long j, int i) {
        if (i == 0) {
            return startBLEScan(j);
        }
        if (!CommonUtil.checkHasScanPermission(this.context)) {
            JL_Log.e(this.TAG, "startDeviceScan :: no scan permission");
            return 4113;
        } else if (this.mBluetoothAdapter == null) {
            JL_Log.e(this.TAG, "startDeviceScan :: this device is not supported bluetooth.");
            return 4098;
        } else if (!c()) {
            JL_Log.e(this.TAG, "startDeviceScan :: bluetooth is close.");
            return 4099;
        } else {
            if (this.j) {
                JL_Log.w(this.TAG, "startDeviceScan :: stopBLEScan: ");
                stopBLEScan();
            }
            setScanType(i);
            this.l = j <= 0 ? BleMessenger.TIMEOUT : j;
            if (this.k) {
                String str = this.TAG;
                JL_Log.i(str, "scanning br/edr ..... timeout = " + j);
                this.m.removeMessages(p);
                this.m.sendEmptyMessageDelayed(p, this.l);
                a(true);
                return 0;
            }
            a();
            boolean startDiscovery = this.mBluetoothAdapter.startDiscovery();
            String str2 = this.TAG;
            JL_Log.w(str2, "-startDiscovery- >>>>>> bRet : " + startDiscovery);
            if (!startDiscovery) {
                b();
                return 8194;
            }
            this.m.removeMessages(p);
            this.m.sendEmptyMessageDelayed(p, this.l);
            return 0;
        }
    }

    @SuppressLint({"MissingPermission"})
    public int stopBLEScan() {
        BluetoothLeScanner bluetoothLeScanner;
        if (!CommonUtil.checkHasScanPermission(this.context)) {
            JL_Log.e(this.TAG, "stopBLEScan : no scan permission.");
            return 4113;
        } else if (this.mBluetoothAdapter == null) {
            JL_Log.e(this.TAG, "stopBLEScan : this device is not supported bluetooth.");
            return 4113;
        } else if (this.j) {
            String str = this.TAG;
            JL_Log.w(str, "-stopBLEScan- >>>>>> " + this.i);
            if (this.i != 0) {
                setScanType(0);
            }
            if (c()) {
                try {
                    if (Build.VERSION.SDK_INT >= 21 && (bluetoothLeScanner = this.h) != null) {
                        bluetoothLeScanner.stopScan(this.o);
                    } else {
                        this.mBluetoothAdapter.stopLeScan(this.n);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.m.removeMessages(q);
            a(false);
            return 0;
        } else {
            return 0;
        }
    }

    @SuppressLint({"MissingPermission"})
    public int stopDeviceScan() {
        if (!CommonUtil.checkHasScanPermission(this.context)) {
            JL_Log.e(this.TAG, "stopDeviceScan : no scan permission");
            return 4113;
        } else if (this.mBluetoothAdapter == null) {
            JL_Log.e(this.TAG, "stopDeviceScan : this device is not supported bluetooth.");
            return 4113;
        } else if (this.k) {
            boolean cancelDiscovery = this.mBluetoothAdapter.cancelDiscovery();
            String str = this.TAG;
            JL_Log.w(str, "stopDeviceScan : cancelDiscovery = " + cancelDiscovery);
            if (cancelDiscovery) {
                this.m.removeMessages(p);
                return 0;
            }
            return 8194;
        } else {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        return BluetoothUtil.isBluetoothEnable();
    }

    private void d() {
        setScanType(0);
        this.j = false;
        this.k = false;
        this.e.clear();
        this.f.clear();
    }

    @SuppressLint({"MissingPermission"})
    private boolean b(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || !CommonUtil.checkHasConnectPermission(this.context)) {
            return false;
        }
        return bluetoothDevice.getType() == 2 || bluetoothDevice.getType() == 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean a(Message message) {
        int i = message.what;
        if (i != p) {
            if (i == q && this.j) {
                JL_Log.w(this.TAG, "-mBleTimeOut- stopBLEScan");
                stopBLEScan();
            }
        } else if (this.k) {
            JL_Log.w(this.TAG, "-MSG_STOP_EDR- stopDeviceScan");
            stopDeviceScan();
            this.k = false;
        }
        return false;
    }

    private void b(boolean z) {
        List<BluetoothDevice> systemConnectedBtDeviceList = BluetoothUtil.getSystemConnectedBtDeviceList(this.context);
        if (systemConnectedBtDeviceList == null || systemConnectedBtDeviceList.isEmpty()) {
            return;
        }
        for (BluetoothDevice bluetoothDevice : systemConnectedBtDeviceList) {
            if (z && b(bluetoothDevice)) {
                if (!this.e.contains(bluetoothDevice)) {
                    this.e.add(bluetoothDevice);
                    this.mBtEventCbHelper.onDiscovery(bluetoothDevice, new BleScanMessage());
                }
            } else if (!z && !b(bluetoothDevice) && !this.f.contains(bluetoothDevice)) {
                this.f.add(bluetoothDevice);
                this.mBtEventCbHelper.onDiscovery(bluetoothDevice, new BleScanMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        a(bluetoothDevice, i, bArr, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public void a(BluetoothDevice bluetoothDevice, int i, byte[] bArr, boolean z) {
        if (bluetoothDevice == null || !CommonUtil.checkHasConnectPermission(this.context) || !c() || this.e.contains(bluetoothDevice)) {
            return;
        }
        this.e.add(bluetoothDevice);
        this.mBtEventCbHelper.onDiscovery(bluetoothDevice, new BleScanMessage().setRawData(bArr).setRssi(i).setEnableConnect(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Context context;
        BluetoothDiscoveryReceiver bluetoothDiscoveryReceiver = this.g;
        if (bluetoothDiscoveryReceiver == null || (context = this.context) == null) {
            return;
        }
        context.unregisterReceiver(bluetoothDiscoveryReceiver);
        this.g = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        boolean z2 = this.i == 0;
        String str = this.TAG;
        JL_Log.i(str, "-notifyDiscoveryStatus- scanType : " + this.i + " ,bStart : " + z);
        if (z) {
            if (z2) {
                this.j = true;
                this.e.clear();
            } else {
                this.k = true;
                this.f.clear();
            }
        } else if (z2) {
            this.j = false;
        } else {
            this.k = false;
        }
        this.mBtEventCbHelper.onDiscoveryStatus(z2, z);
        if (!z) {
            setScanType(0);
        } else {
            b(z2);
        }
    }

    private void a() {
        if (this.g != null || this.context == null) {
            return;
        }
        this.g = new BluetoothDiscoveryReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        this.context.registerReceiver(this.g, intentFilter);
    }
}
