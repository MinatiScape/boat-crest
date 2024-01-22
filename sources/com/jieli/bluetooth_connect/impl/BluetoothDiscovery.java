package com.jieli.bluetooth_connect.impl;

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
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.jieli.bluetooth_connect.bean.BluetoothOption;
import com.jieli.bluetooth_connect.bean.ErrorInfo;
import com.jieli.bluetooth_connect.bean.ble.BleScanMessage;
import com.jieli.bluetooth_connect.interfaces.IBluetoothDiscovery;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtDiscoveryListener;
import com.jieli.bluetooth_connect.tool.BtDiscoveryCbManager;
import com.jieli.bluetooth_connect.util.BluetoothUtil;
import com.jieli.bluetooth_connect.util.ConnectUtil;
import com.jieli.bluetooth_connect.util.JL_Log;
import com.jieli.bluetooth_connect.util.ParseDataUtil;
import com.szabh.smable3.component.BleMessenger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public class BluetoothDiscovery implements IBluetoothDiscovery {
    private static final int MSG_DISCOVERY_BLE_TIMEOUT = 1011;
    private static final int MSG_DISCOVERY_EDR_TIMEOUT = 1022;
    private static final String TAG = "BluetoothDiscovery";
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDiscoveryReceiver mBluetoothDiscoveryReceiver;
    private BluetoothLeScanner mBluetoothLeScanner;
    private final BluetoothOption mBluetoothOption;
    private BluetoothReceiver mBluetoothReceiver;
    private final BtDiscoveryCbManager mBtDiscoveryCbManager;
    private final Context mContext;
    private int mScanType;
    private final List<BluetoothDevice> mDiscoveredDevices = new ArrayList();
    private final List<BluetoothDevice> mDiscoveredEdrDevices = new ArrayList();
    private volatile boolean mIsBleScanning = false;
    private long mTimeout = BleMessenger.TIMEOUT;
    private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jieli.bluetooth_connect.impl.BluetoothDiscovery.1
        @Override // android.os.Handler.Callback
        @SuppressLint({"MissingPermission"})
        public boolean handleMessage(@NonNull Message message) {
            int i = message.what;
            if (i != 1011) {
                if (i == BluetoothDiscovery.MSG_DISCOVERY_EDR_TIMEOUT && ConnectUtil.isHasScanPermission(BluetoothDiscovery.this.mContext) && BluetoothDiscovery.this.mBluetoothAdapter.isDiscovering()) {
                    JL_Log.w(BluetoothDiscovery.TAG, "-MSG_DISCOVERY_EDR_TIMEOUT- stopDeviceScan: ");
                    BluetoothDiscovery.this.stopDeviceScan();
                    return true;
                }
                return true;
            } else if (BluetoothDiscovery.this.mIsBleScanning) {
                JL_Log.w(BluetoothDiscovery.TAG, "-MSG_DISCOVERY_BLE_TIMEOUT- stopBLEScan: ");
                BluetoothDiscovery.this.stopBLEScan();
                return true;
            } else {
                return true;
            }
        }
    });
    private final BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() { // from class: com.jieli.bluetooth_connect.impl.c
        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public final void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            BluetoothDiscovery.this.lambda$new$0(bluetoothDevice, i, bArr);
        }
    };
    @RequiresApi(21)
    private final ScanCallback mScanCallback = new ScanCallback() { // from class: com.jieli.bluetooth_connect.impl.BluetoothDiscovery.2
        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            String str = BluetoothDiscovery.TAG;
            JL_Log.e(str, "onScanFailed scan ble error. errorCode : " + i);
            BluetoothDiscovery.this.mBtDiscoveryCbManager.onDiscoveryError(new ErrorInfo(i, "scan ble error."));
            BluetoothDiscovery.this.notifyDiscoveryStatus(true, false);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            if (scanResult == null || scanResult.getScanRecord() == null) {
                return;
            }
            BluetoothDiscovery.this.filterDevice(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes(), Build.VERSION.SDK_INT >= 26 ? scanResult.isConnectable() : true);
        }
    };

    /* loaded from: classes11.dex */
    public class BluetoothDiscoveryReceiver extends BroadcastReceiver {
        private BluetoothDiscoveryReceiver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.content.BroadcastReceiver
        @SuppressLint({"MissingPermission"})
        public void onReceive(Context context, Intent intent) {
            boolean z;
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            Objects.requireNonNull(action);
            String str = action;
            boolean z2 = false;
            switch (str.hashCode()) {
                case -1780914469:
                    if (str.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                        z = false;
                        break;
                    }
                    z = true;
                    break;
                case 6759640:
                    if (str.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
                        z = true;
                        break;
                    }
                    z = true;
                    break;
                case 1167529923:
                    if (str.equals("android.bluetooth.device.action.FOUND")) {
                        z = true;
                        break;
                    }
                    z = true;
                    break;
                default:
                    z = true;
                    break;
            }
            switch (z) {
                case false:
                    JL_Log.i(BluetoothDiscovery.TAG, "ACTION_DISCOVERY_FINISHED : ");
                    BluetoothDiscovery.this.mHandler.removeMessages(BluetoothDiscovery.MSG_DISCOVERY_EDR_TIMEOUT);
                    BluetoothDiscovery.this.unregisterReceiver();
                    BluetoothDiscovery.this.notifyDiscoveryStatus(false, false);
                    return;
                case true:
                    JL_Log.i(BluetoothDiscovery.TAG, "ACTION_DISCOVERY_STARTED : ");
                    BluetoothDiscovery.this.mDiscoveredEdrDevices.clear();
                    BluetoothDiscovery.this.notifyDiscoveryStatus(false, true);
                    BluetoothDiscovery.this.mHandler.removeMessages(BluetoothDiscovery.MSG_DISCOVERY_EDR_TIMEOUT);
                    BluetoothDiscovery.this.mHandler.sendEmptyMessageDelayed(BluetoothDiscovery.MSG_DISCOVERY_EDR_TIMEOUT, BluetoothDiscovery.this.mTimeout);
                    return;
                case true:
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    short shortExtra = intent.getShortExtra("android.bluetooth.device.extra.RSSI", (short) -1);
                    if (bluetoothDevice != null && BluetoothUtil.isBluetoothEnable() && ConnectUtil.isHasConnectPermission(BluetoothDiscovery.this.mContext)) {
                        int type = bluetoothDevice.getType();
                        int i = BluetoothDiscovery.this.mScanType;
                        if (i == 0 ? 2 == type || 3 == type : i == 1 ? 1 == bluetoothDevice.getType() : i == 2) {
                            z2 = true;
                        }
                        if (!z2 || BluetoothDiscovery.this.mDiscoveredEdrDevices.contains(bluetoothDevice)) {
                            return;
                        }
                        BluetoothDiscovery.this.mDiscoveredEdrDevices.add(bluetoothDevice);
                        BluetoothDiscovery.this.mBtDiscoveryCbManager.onDiscoveryDevice(bluetoothDevice, new BleScanMessage().setEnableConnect(true).setRssi(shortExtra));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* loaded from: classes11.dex */
    public class BluetoothReceiver extends BroadcastReceiver {
        private BluetoothReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra;
            if (intent != null && "android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction()) && (intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0)) != 0 && intExtra == 10) {
                BluetoothDiscovery.this.mHandler.removeCallbacksAndMessages(null);
                BluetoothDiscovery.this.mIsBleScanning = false;
                BluetoothDiscovery.this.mDiscoveredDevices.clear();
                BluetoothDiscovery.this.mDiscoveredEdrDevices.clear();
                BluetoothDiscovery.this.unregisterReceiver();
            }
        }
    }

    public BluetoothDiscovery(Context context, BluetoothOption bluetoothOption, OnBtDiscoveryListener onBtDiscoveryListener) {
        this.mContext = (Context) ConnectUtil.checkNotNull(context);
        this.mBluetoothOption = bluetoothOption == null ? BluetoothOption.createDefaultOption() : bluetoothOption;
        this.mBtDiscoveryCbManager = new BtDiscoveryCbManager();
        addListener(onBtDiscoveryListener);
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        getBluetoothLeScanner();
        registerBtReceiver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public void filterDevice(BluetoothDevice bluetoothDevice, int i, byte[] bArr, boolean z) {
        if (bluetoothDevice == null || !ConnectUtil.isHasConnectPermission(this.mContext)) {
            return;
        }
        if (this.mBluetoothOption.getBleScanStrategy() == 0) {
            if (!BluetoothUtil.isBluetoothEnable() || TextUtils.isEmpty(bluetoothDevice.getName()) || this.mDiscoveredDevices.contains(bluetoothDevice)) {
                return;
            }
            BleScanMessage parseOTAFlagFilterWithBroad = ParseDataUtil.parseOTAFlagFilterWithBroad(bArr, this.mBluetoothOption.getOtaScanFilterData());
            if (parseOTAFlagFilterWithBroad == null) {
                parseOTAFlagFilterWithBroad = new BleScanMessage();
            }
            this.mDiscoveredDevices.add(bluetoothDevice);
            this.mBtDiscoveryCbManager.onDiscoveryDevice(bluetoothDevice, parseOTAFlagFilterWithBroad.setRawData(bArr).setRssi(i).setEnableConnect(z));
            return;
        }
        BleScanMessage isFilterBleDevice = ParseDataUtil.isFilterBleDevice(this.mBluetoothOption, bArr);
        if (isFilterBleDevice == null || TextUtils.isEmpty(bluetoothDevice.getName()) || !BluetoothUtil.isBluetoothEnable()) {
            return;
        }
        if (isFilterBleDevice.isEnableConnect() && !z) {
            isFilterBleDevice.setEnableConnect(false);
        }
        isFilterBleDevice.setRawData(bArr).setRssi(i);
        if (isFilterBleDevice.isShowDialog()) {
            this.mBtDiscoveryCbManager.onShowProductDialog(bluetoothDevice, isFilterBleDevice);
        }
        if (this.mDiscoveredDevices.contains(bluetoothDevice)) {
            return;
        }
        this.mDiscoveredDevices.add(bluetoothDevice);
        this.mBtDiscoveryCbManager.onDiscoveryDevice(bluetoothDevice, isFilterBleDevice);
    }

    private BluetoothLeScanner getBluetoothLeScanner() {
        BluetoothAdapter bluetoothAdapter;
        if (Build.VERSION.SDK_INT >= 21 && (bluetoothAdapter = this.mBluetoothAdapter) != null && this.mBluetoothLeScanner == null) {
            this.mBluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
        }
        return this.mBluetoothLeScanner;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        filterDevice(bluetoothDevice, i, bArr, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDiscoveryStatus(boolean z, boolean z2) {
        String str = TAG;
        JL_Log.i(str, "-notifyDiscoveryStatus- bBle : " + z + " ,bStart : " + z2);
        int i = this.mScanType;
        if (i == 0 && z) {
            this.mBtDiscoveryCbManager.onDiscoveryStatusChange(true, z2);
        } else if (i == 1 && !z) {
            this.mBtDiscoveryCbManager.onDiscoveryStatusChange(false, z2);
        }
        if (z2) {
            return;
        }
        this.mIsBleScanning = false;
        this.mScanType = 0;
    }

    private void registerBtReceiver() {
        if (this.mBluetoothReceiver == null) {
            this.mBluetoothReceiver = new BluetoothReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            this.mContext.registerReceiver(this.mBluetoothReceiver, intentFilter);
        }
    }

    private void registerReceiver() {
        if (this.mBluetoothDiscoveryReceiver == null) {
            this.mBluetoothDiscoveryReceiver = new BluetoothDiscoveryReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
            intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
            intentFilter.addAction("android.bluetooth.device.action.FOUND");
            JL_Log.d(TAG, "registerReceiverv >>>>>>>>>>>>>");
            this.mContext.registerReceiver(this.mBluetoothDiscoveryReceiver, intentFilter);
        }
    }

    @SuppressLint({"MissingPermission"})
    private void stopBleScanNoCallback() {
        if (ConnectUtil.isHasScanPermission(this.mContext)) {
            if (this.mIsBleScanning) {
                JL_Log.i(TAG, "-stopBLEScan- >>>>>> ");
                this.mIsBleScanning = false;
                if (!BluetoothUtil.isBluetoothEnable()) {
                    return;
                }
                if (Build.VERSION.SDK_INT >= 21 && getBluetoothLeScanner() != null) {
                    try {
                        this.mBluetoothLeScanner.stopScan(this.mScanCallback);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
                    if (bluetoothAdapter != null) {
                        try {
                            bluetoothAdapter.stopLeScan(this.leScanCallback);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            this.mHandler.removeMessages(1011);
        }
    }

    private void unregisterBtReceiver() {
        BluetoothReceiver bluetoothReceiver = this.mBluetoothReceiver;
        if (bluetoothReceiver != null) {
            this.mContext.unregisterReceiver(bluetoothReceiver);
            this.mBluetoothReceiver = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterReceiver() {
        if (this.mBluetoothDiscoveryReceiver != null) {
            JL_Log.d(TAG, "unregisterReceiver >>>>>>>>>>>>>");
            this.mContext.unregisterReceiver(this.mBluetoothDiscoveryReceiver);
            this.mBluetoothDiscoveryReceiver = null;
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void destroy() {
        this.mBtDiscoveryCbManager.destroy();
        this.mHandler.removeCallbacksAndMessages(null);
        stopDeviceScan();
        stopBLEScan();
        unregisterReceiver();
        unregisterBtReceiver();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothDiscovery
    public ArrayList<BluetoothDevice> getDiscoveredBluetoothDevices() {
        if (this.mScanType == 1) {
            return new ArrayList<>(this.mDiscoveredEdrDevices);
        }
        return new ArrayList<>(this.mDiscoveredDevices);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothDiscovery
    public int getScanType() {
        return this.mScanType;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothDiscovery
    public boolean isBleScanning() {
        return this.mIsBleScanning;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothDiscovery
    @SuppressLint({"MissingPermission"})
    public boolean isDeviceScanning() {
        BluetoothAdapter bluetoothAdapter;
        return ConnectUtil.isHasScanPermission(this.mContext) && (bluetoothAdapter = this.mBluetoothAdapter) != null && bluetoothAdapter.isDiscovering();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothDiscovery
    public boolean isScanning() {
        return isDeviceScanning() || this.mIsBleScanning;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void setBluetoothOption(BluetoothOption bluetoothOption) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothDiscovery
    @SuppressLint({"MissingPermission"})
    public boolean startBLEScan(long j) {
        ScanSettings build;
        if (ConnectUtil.isHasScanPermission(this.mContext) && ConnectUtil.isHasLocationPermission(this.mContext)) {
            if (this.mBluetoothAdapter == null) {
                this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            }
            if (this.mBluetoothAdapter == null) {
                JL_Log.e(TAG, "startBLEScan :: this device is not supported bluetooth.");
                return false;
            } else if (BluetoothUtil.isBluetoothEnable()) {
                this.mScanType = 0;
                if (j <= 0) {
                    j = BleMessenger.TIMEOUT;
                }
                if (this.mIsBleScanning) {
                    String str = TAG;
                    JL_Log.i(str, "scanning ble ..... timeout = " + j);
                    BluetoothLeScanner bluetoothLeScanner = this.mBluetoothLeScanner;
                    if (bluetoothLeScanner != null && Build.VERSION.SDK_INT >= 21) {
                        bluetoothLeScanner.flushPendingScanResults(this.mScanCallback);
                    }
                    this.mDiscoveredDevices.clear();
                    this.mHandler.removeMessages(1011);
                    this.mHandler.sendEmptyMessageDelayed(1011, j);
                    notifyDiscoveryStatus(true, true);
                    return true;
                }
                if (isDeviceScanning()) {
                    stopDeviceScan();
                }
                this.mHandler.removeMessages(1011);
                this.mHandler.sendEmptyMessageDelayed(1011, j);
                this.mIsBleScanning = true;
                notifyDiscoveryStatus(true, true);
                int i = Build.VERSION.SDK_INT;
                if (i >= 21 && getBluetoothLeScanner() != null) {
                    if (i >= 23) {
                        build = new ScanSettings.Builder().setScanMode(this.mBluetoothOption.getBleScanMode()).setMatchMode(1).build();
                    } else {
                        build = new ScanSettings.Builder().setScanMode(this.mBluetoothOption.getBleScanMode()).build();
                    }
                    this.mBluetoothLeScanner.startScan(new ArrayList(), build, this.mScanCallback);
                    JL_Log.i(TAG, "-startBLEScan- >>>>>> startScan :>> ");
                } else {
                    boolean startLeScan = this.mBluetoothAdapter.startLeScan(this.leScanCallback);
                    String str2 = TAG;
                    JL_Log.i(str2, "-startBLEScan- >>>>>> bRet : " + startLeScan);
                    if (!startLeScan) {
                        return false;
                    }
                }
                this.mDiscoveredDevices.clear();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothDiscovery
    public boolean startDeviceScan(long j) {
        return startDeviceScan(1, j);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothDiscovery
    public boolean stopBLEScan() {
        if (this.mBluetoothAdapter != null && ConnectUtil.isHasScanPermission(this.mContext)) {
            if (this.mIsBleScanning) {
                stopBleScanNoCallback();
                notifyDiscoveryStatus(true, false);
                return true;
            }
            return true;
        }
        JL_Log.e(TAG, "stopBLEScan :: this device is not supported bluetooth.");
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothDiscovery
    @SuppressLint({"MissingPermission"})
    public boolean stopDeviceScan() {
        if (ConnectUtil.isHasScanPermission(this.mContext)) {
            BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
            if (bluetoothAdapter == null) {
                JL_Log.e(TAG, "stopDeviceScan :: this device is not supported bluetooth.");
                return false;
            } else if (bluetoothAdapter.isDiscovering()) {
                if (!this.mBluetoothAdapter.isEnabled()) {
                    unregisterReceiver();
                    return true;
                }
                boolean cancelDiscovery = this.mBluetoothAdapter.cancelDiscovery();
                String str = TAG;
                JL_Log.w(str, "-cancelDiscovery- >>>>>> bRet = " + cancelDiscovery);
                if (cancelDiscovery) {
                    this.mHandler.removeMessages(MSG_DISCOVERY_EDR_TIMEOUT);
                    return true;
                }
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void addListener(OnBtDiscoveryListener onBtDiscoveryListener) {
        this.mBtDiscoveryCbManager.addListener(onBtDiscoveryListener);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void removeListener(OnBtDiscoveryListener onBtDiscoveryListener) {
        this.mBtDiscoveryCbManager.removeListener(onBtDiscoveryListener);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothDiscovery
    @SuppressLint({"MissingPermission"})
    public boolean startDeviceScan(int i, long j) {
        if (ConnectUtil.isHasScanPermission(this.mContext)) {
            this.mScanType = i;
            if (i == 0) {
                return startBLEScan(j);
            }
            if (this.mBluetoothAdapter == null) {
                this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            }
            if (this.mBluetoothAdapter == null) {
                JL_Log.e(TAG, "this device is not supported bluetooth.");
                return false;
            } else if (BluetoothUtil.isBluetoothEnable()) {
                if (this.mIsBleScanning) {
                    JL_Log.w(TAG, "-startDeviceScan- stopBLEScan: ");
                    stopBLEScan();
                }
                if (j <= 0) {
                    this.mTimeout = BleMessenger.TIMEOUT;
                } else {
                    this.mTimeout = j;
                }
                if (this.mBluetoothAdapter.isDiscovering()) {
                    this.mDiscoveredEdrDevices.clear();
                    JL_Log.i(TAG, "scanning edr .....");
                    this.mHandler.removeMessages(MSG_DISCOVERY_EDR_TIMEOUT);
                    this.mHandler.sendEmptyMessageDelayed(MSG_DISCOVERY_EDR_TIMEOUT, this.mTimeout);
                    notifyDiscoveryStatus(false, true);
                    return true;
                }
                registerReceiver();
                boolean startDiscovery = this.mBluetoothAdapter.startDiscovery();
                String str = TAG;
                JL_Log.i(str, "-startDiscovery- >>>>>> bRet : " + startDiscovery);
                return startDiscovery;
            } else {
                return false;
            }
        }
        return false;
    }
}
