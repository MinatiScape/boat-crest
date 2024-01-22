package com.goodix.ble.libble.misc;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build;
import android.os.ParcelUuid;
import androidx.annotation.RequiresApi;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskOutput;
import com.realsil.sdk.dfu.DfuConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/* loaded from: classes5.dex */
public class FindDeviceTask extends Task {
    public String B;
    public String C;
    public UUID D;
    public BluetoothManager I;
    @TaskOutput
    public BluetoothDevice J;
    public BluetoothAdapter K;
    public b L;
    public a M;
    public int z = 30000;
    public int A = 10000;
    public int E = -127;
    public boolean F = false;
    public boolean G = false;
    public boolean H = false;

    /* loaded from: classes5.dex */
    public class a implements BluetoothAdapter.LeScanCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            String name;
            if (FindDeviceTask.this.taskState == 2 && i >= FindDeviceTask.this.E) {
                if (FindDeviceTask.this.B == null || FindDeviceTask.this.B.equals(bluetoothDevice.getAddress())) {
                    if (FindDeviceTask.this.C == null || ((name = bluetoothDevice.getName()) != null && name.contains(FindDeviceTask.this.C))) {
                        if (FindDeviceTask.this.logger != null) {
                            ILogger iLogger = FindDeviceTask.this.logger;
                            String name2 = FindDeviceTask.this.getName();
                            iLogger.v(name2, "Found device: " + bluetoothDevice);
                        }
                        FindDeviceTask.this.e(bluetoothDevice);
                    }
                }
            }
        }
    }

    @RequiresApi(api = 21)
    /* loaded from: classes5.dex */
    public class b extends ScanCallback {
        public b() {
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            ScanRecord scanRecord;
            String deviceName;
            if (list.size() <= 0 || FindDeviceTask.this.taskState != 2) {
                return;
            }
            ScanResult scanResult = list.get(0);
            if (scanResult.getRssi() < FindDeviceTask.this.E) {
                return;
            }
            if (FindDeviceTask.this.C == null || !((scanRecord = scanResult.getScanRecord()) == null || (deviceName = scanRecord.getDeviceName()) == null || !deviceName.contains(FindDeviceTask.this.C))) {
                BluetoothDevice device = scanResult.getDevice();
                if (FindDeviceTask.this.logger != null) {
                    ILogger iLogger = FindDeviceTask.this.logger;
                    String name = FindDeviceTask.this.getName();
                    iLogger.v(name, "Found device: " + device);
                }
                FindDeviceTask.this.e(device);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            String str;
            if (i == 1) {
                str = "Fails to start scan as BLE scan with the same settings is already started by the app.";
            } else if (i == 2) {
                str = "Fails to start scan as app cannot be registered.";
            } else if (i == 3) {
                str = "Fails to start scan due an internal error.";
            } else if (i != 4) {
                str = "UNKNOWN(" + i + ")";
            } else {
                str = "Fails to start power optimized scan as this feature is not supported.";
            }
            FindDeviceTask.this.finishedWithError("Scan Failed: [" + i + "] " + str);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            ScanRecord scanRecord;
            String deviceName;
            if (i == 1 && FindDeviceTask.this.taskState == 2 && scanResult.getRssi() >= FindDeviceTask.this.E) {
                if (FindDeviceTask.this.C == null || !((scanRecord = scanResult.getScanRecord()) == null || (deviceName = scanRecord.getDeviceName()) == null || !deviceName.contains(FindDeviceTask.this.C))) {
                    BluetoothDevice device = scanResult.getDevice();
                    if (FindDeviceTask.this.logger != null) {
                        ILogger iLogger = FindDeviceTask.this.logger;
                        String name = FindDeviceTask.this.getName();
                        iLogger.v(name, "Found device: " + device);
                    }
                    FindDeviceTask.this.e(device);
                }
            }
        }
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        this.K = BluetoothAdapter.getDefaultAdapter();
        if (h()) {
            return 0;
        }
        onTimeout(DfuConstants.PROGRESS_ABORT_PROCESSING);
        return this.z;
    }

    public final void e(BluetoothDevice bluetoothDevice) {
        this.J = bluetoothDevice;
        setParameter(BluetoothDevice.class, bluetoothDevice);
        if (!this.F || bluetoothDevice == null) {
            finishedWithDone();
            return;
        }
        finishedWithError("Found device: " + bluetoothDevice);
    }

    public BluetoothDevice getFoundDevice() {
        return this.J;
    }

    public final boolean h() {
        BluetoothManager bluetoothManager;
        if (this.B != null) {
            if (this.H) {
                for (BluetoothDevice bluetoothDevice : this.K.getBondedDevices()) {
                    if (this.B.equals(bluetoothDevice.getAddress())) {
                        e(bluetoothDevice);
                        return true;
                    }
                }
            }
            if (!this.G || (bluetoothManager = this.I) == null) {
                return false;
            }
            for (BluetoothDevice bluetoothDevice2 : bluetoothManager.getConnectedDevices(7)) {
                if (this.B.equals(bluetoothDevice2.getAddress())) {
                    e(bluetoothDevice2);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final void j() {
        int i = Build.VERSION.SDK_INT;
        if (i < 21) {
            if (this.M == null) {
                this.M = new a();
            }
            UUID uuid = this.D;
            if (uuid != null) {
                this.K.startLeScan(new UUID[]{uuid}, this.M);
                return;
            } else {
                this.K.startLeScan(this.M);
                return;
            }
        }
        ScanSettings.Builder builder = new ScanSettings.Builder();
        builder.setScanMode(2).setReportDelay(0L);
        if (i >= 26 && this.K.isLeCodedPhySupported()) {
            builder.setLegacy(false).setPhy(255);
        }
        if (this.L == null) {
            this.L = new b();
        }
        if (this.B == null && this.D == null) {
            this.K.getBluetoothLeScanner().startScan((List<ScanFilter>) null, builder.build(), this.L);
            return;
        }
        ScanFilter.Builder builder2 = new ScanFilter.Builder();
        ArrayList arrayList = new ArrayList(1);
        String str = this.B;
        if (str != null) {
            builder2.setDeviceAddress(str);
        }
        if (this.D != null) {
            builder2.setServiceUuid(new ParcelUuid(this.D));
        }
        arrayList.add(builder2.build());
        this.K.getBluetoothLeScanner().startScan(arrayList, builder.build(), this.L);
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        super.onCleanup();
        stopScan();
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onTaskExpired() {
        if (this.F) {
            finishedWithDone();
        } else {
            super.onTaskExpired();
        }
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onTimeout(int i) {
        if (i == 521) {
            stopScan();
            startTimer(DfuConstants.PROGRESS_ABORT_PROCESSING, 1000L);
        }
        if (i != 525 || h()) {
            return;
        }
        j();
        int i2 = this.A;
        if (i2 > 0) {
            startTimer(DfuConstants.PROGRESS_START_DFU_PROCESS, i2);
        }
    }

    public FindDeviceTask setCheckBond(boolean z) {
        this.H = z;
        return this;
    }

    public FindDeviceTask setCheckConnected(boolean z, Context context) {
        this.G = z;
        if (context == null) {
            this.G = false;
        } else {
            this.I = (BluetoothManager) context.getSystemService("bluetooth");
        }
        return this;
    }

    public FindDeviceTask setCheckNonExistent(boolean z) {
        this.F = z;
        return this;
    }

    public FindDeviceTask setNameFilter(String str) {
        this.C = str;
        return this;
    }

    public FindDeviceTask setRetryPeriod(int i) {
        this.A = i;
        return this;
    }

    public FindDeviceTask setScanFilter(int i) {
        this.E = i;
        return this;
    }

    public FindDeviceTask setScanFilter(String str) {
        this.B = str;
        return this;
    }

    public FindDeviceTask setScanFilter(UUID uuid) {
        this.D = uuid;
        return this;
    }

    public FindDeviceTask setTimeout(int i) {
        this.z = i;
        return this;
    }

    public void stopScan() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.K.getBluetoothLeScanner().stopScan(this.L);
        } else {
            this.K.stopLeScan(this.M);
        }
    }
}
