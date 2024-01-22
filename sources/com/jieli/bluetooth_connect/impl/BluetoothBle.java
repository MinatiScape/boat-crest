package com.jieli.bluetooth_connect.impl;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.clevertap.android.sdk.Constants;
import com.jieli.bluetooth_connect.bean.BluetoothOption;
import com.jieli.bluetooth_connect.bean.ErrorInfo;
import com.jieli.bluetooth_connect.constant.BluetoothConstant;
import com.jieli.bluetooth_connect.interfaces.IBluetoothBle;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener;
import com.jieli.bluetooth_connect.interfaces.listener.OnThreadStateListener;
import com.jieli.bluetooth_connect.interfaces.listener.OnWriteDataCallback;
import com.jieli.bluetooth_connect.tool.BleEventCbManager;
import com.jieli.bluetooth_connect.tool.SendBleDataThread;
import com.jieli.bluetooth_connect.util.BluetoothUtil;
import com.jieli.bluetooth_connect.util.CHexConverter;
import com.jieli.bluetooth_connect.util.ConnectUtil;
import com.jieli.bluetooth_connect.util.JL_Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes11.dex */
public class BluetoothBle implements IBluetoothBle {
    private static final int CALLBACK_TIMEOUT = 3000;
    private static final int MSG_BLE_DISCOVER_SERVICES_CALLBACK_TIMEOUT = 13641;
    private static final int MSG_CONNECT_BLE_TIMEOUT = 13639;
    private static final int MSG_DISCONNECT_BLE_CALLBACK_TIMEOUT = 13640;
    private static final int MSG_RECONNECT_BLE = 13642;
    private static final int RECONNECT_BLE_DELAY = 2000;
    private static final String TAG = "BluetoothBle";
    private long boundStartTime;
    private int failedCount;
    private final BleEventCbManager mBleEventCbManager;
    private int mBleNotificationCount;
    private BluetoothBleReceiver mBluetoothBleReceiver;
    private final BluetoothGattCallback mBluetoothGattCallback;
    private BluetoothOption mBluetoothOption;
    private final BluetoothPair mBluetoothPair;
    private volatile BluetoothDevice mBoundingBleDevice;
    private volatile BluetoothDevice mConnectedBleDevice;
    private volatile BluetoothDevice mConnectingBleDevice;
    private final Context mContext;
    private volatile BluetoothDevice mNeedReconnectBleDevice;
    private final OnBtDevicePairListener mOnBtDevicePairListener;
    private SendBleDataThread mSendBleDataThread;
    private final List<BluetoothGatt> mBluetoothGatts = Collections.synchronizedList(new ArrayList());
    private final List<BluetoothDevice> mConnectedBleDevices = Collections.synchronizedList(new ArrayList());
    private final Map<String, Integer> mBleMtuMap = Collections.synchronizedMap(new HashMap());
    private final Map<String, Long> startTimeMap = new HashMap();
    private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jieli.bluetooth_connect.impl.BluetoothBle.1
        @Override // android.os.Handler.Callback
        @SuppressLint({"MissingPermission"})
        public boolean handleMessage(@NonNull Message message) {
            BluetoothDevice bluetoothDevice;
            BluetoothGatt deviceGatt;
            List<BluetoothGattService> services;
            switch (message.what) {
                case BluetoothBle.MSG_CONNECT_BLE_TIMEOUT /* 13639 */:
                    BluetoothDevice bluetoothDevice2 = BluetoothBle.this.mConnectingBleDevice;
                    if (bluetoothDevice2 == null) {
                        Object obj = message.obj;
                        if (obj instanceof BluetoothDevice) {
                            bluetoothDevice2 = (BluetoothDevice) obj;
                        }
                    }
                    if (bluetoothDevice2 != null) {
                        if (!BluetoothUtil.isBleConnected(BluetoothBle.this.mContext, bluetoothDevice2)) {
                            BluetoothBle.this.notifyBleConnectStatus(bluetoothDevice2, 0);
                        }
                        BluetoothBle.this.setConnectingBleDevice(null);
                        break;
                    }
                    break;
                case BluetoothBle.MSG_DISCONNECT_BLE_CALLBACK_TIMEOUT /* 13640 */:
                    Object obj2 = message.obj;
                    if ((obj2 instanceof BluetoothDevice) && (deviceGatt = BluetoothBle.this.getDeviceGatt((bluetoothDevice = (BluetoothDevice) obj2))) != null && ConnectUtil.isHasConnectPermission(BluetoothBle.this.mContext)) {
                        BluetoothUtil.refreshBleDeviceCache(BluetoothBle.this.mContext, deviceGatt);
                        deviceGatt.close();
                        BluetoothBle.this.notifyBleConnectStatus(bluetoothDevice, 0);
                        BluetoothBle.this.removeDeviceFromRecord(bluetoothDevice, deviceGatt);
                        break;
                    }
                    break;
                case BluetoothBle.MSG_BLE_DISCOVER_SERVICES_CALLBACK_TIMEOUT /* 13641 */:
                    Object obj3 = message.obj;
                    if (obj3 instanceof BluetoothDevice) {
                        BluetoothDevice bluetoothDevice3 = (BluetoothDevice) obj3;
                        BluetoothGatt deviceGatt2 = BluetoothBle.this.getDeviceGatt(bluetoothDevice3);
                        boolean z = true;
                        if (deviceGatt2 != null && (services = deviceGatt2.getServices()) != null && services.size() > 0) {
                            BluetoothBle.this.onBleServiceDiscovery(deviceGatt2, 0);
                            z = false;
                        }
                        if (z) {
                            JL_Log.d(BluetoothBle.TAG, "discover services timeout.");
                            BluetoothBle.this.disconnectBLEDevice(bluetoothDevice3);
                            BluetoothBle.this.setNeedReconnectBleDevice(bluetoothDevice3);
                            break;
                        }
                    }
                    break;
                case BluetoothBle.MSG_RECONNECT_BLE /* 13642 */:
                    Object obj4 = message.obj;
                    if (obj4 instanceof BluetoothDevice) {
                        BluetoothDevice bluetoothDevice4 = (BluetoothDevice) obj4;
                        BluetoothBle.this.setConnectingBleDevice(null);
                        if (!BluetoothBle.this.connectBLEDevice(bluetoothDevice4)) {
                            BluetoothBle.this.notifyBleConnectStatus(bluetoothDevice4, 0);
                            break;
                        }
                    }
                    break;
            }
            return false;
        }
    });

    /* loaded from: classes11.dex */
    public class BluetoothBleReceiver extends BroadcastReceiver {
        private BluetoothBleReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                action.hashCode();
                if (action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    String str = BluetoothBle.TAG;
                    JL_Log.i(str, "-BluetoothBleReceiver- ACTION_ACL_CONNECTED, device : " + BluetoothBle.this.printDeviceInfo(bluetoothDevice));
                    BluetoothBle.this.handleACLConnection(bluetoothDevice, 2);
                } else if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                    BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    String str2 = BluetoothBle.TAG;
                    JL_Log.i(str2, "-BluetoothBleReceiver- ACTION_ACL_DISCONNECTED, device : " + BluetoothBle.this.printDeviceInfo(bluetoothDevice2));
                    BluetoothBle.this.handleACLConnection(bluetoothDevice2, 0);
                }
            }
        }
    }

    public BluetoothBle(Context context, BluetoothPair bluetoothPair, BluetoothOption bluetoothOption, OnBtBleListener onBtBleListener) {
        OnBtDevicePairListener onBtDevicePairListener = new OnBtDevicePairListener() { // from class: com.jieli.bluetooth_connect.impl.BluetoothBle.3
            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
            public void onAdapterStatus(boolean z, boolean z2) {
                if (z) {
                    return;
                }
                BluetoothBle.this.clearDevices();
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
            public void onBtDeviceBond(BluetoothDevice bluetoothDevice, int i) {
                if (BluetoothUtil.deviceEquals(BluetoothBle.this.mBoundingBleDevice, bluetoothDevice)) {
                    String str = BluetoothBle.TAG;
                    JL_Log.i(str, "-onBondStatus- >>>> status : " + i + ", mNeedConnectBleDevice : " + bluetoothDevice);
                    if (i == 12) {
                        BluetoothBle.this.onBleBond(bluetoothDevice, 12);
                    } else if (i == 10) {
                        long abs = Math.abs(ConnectUtil.getCurrentTime() - BluetoothBle.this.boundStartTime);
                        BluetoothBle.access$1408(BluetoothBle.this);
                        if (abs < 5000 && BluetoothBle.this.failedCount <= 3) {
                            SystemClock.sleep(500L);
                            if (BluetoothBle.this.startBleBond(bluetoothDevice)) {
                                String str2 = BluetoothBle.TAG;
                                JL_Log.i(str2, "-onBondStatus- restart bond ble device : " + bluetoothDevice + ", failedCount ： " + BluetoothBle.this.failedCount);
                                return;
                            }
                        }
                        BluetoothBle.this.onBleBond(bluetoothDevice, 10);
                    } else {
                        BluetoothBle.this.onBleBond(bluetoothDevice, i);
                    }
                }
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
            public void onPairError(BluetoothDevice bluetoothDevice, ErrorInfo errorInfo) {
                if (BluetoothUtil.deviceEquals(BluetoothBle.this.mBoundingBleDevice, bluetoothDevice)) {
                    BluetoothBle.this.onBleBond(bluetoothDevice, 10);
                }
            }
        };
        this.mOnBtDevicePairListener = onBtDevicePairListener;
        this.mBluetoothGattCallback = new BluetoothGattCallback() { // from class: com.jieli.bluetooth_connect.impl.BluetoothBle.4
            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
                BluetoothBle.this.mBleEventCbManager.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
                if (bluetoothGatt == null || bluetoothGattCharacteristic == null || bluetoothGattCharacteristic.getService() == null) {
                    return;
                }
                BluetoothDevice device = bluetoothGatt.getDevice();
                UUID uuid = bluetoothGattCharacteristic.getService().getUuid();
                UUID uuid2 = bluetoothGattCharacteristic.getUuid();
                byte[] value = bluetoothGattCharacteristic.getValue();
                JL_Log.d(BluetoothBle.TAG, String.format(Locale.getDefault(), "onCharacteristicChanged----> device = %s, characteristic = %s, data = %s ", BluetoothBle.this.printDeviceInfo(bluetoothGatt.getDevice(), false), bluetoothGattCharacteristic, CHexConverter.byte2HexStr(value)));
                BluetoothBle.this.mBleEventCbManager.onBleDataNotify(device, uuid, uuid2, value);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
                JL_Log.d(BluetoothBle.TAG, String.format(Locale.getDefault(), "onCharacteristicRead2----> device = %s, characteristic = %s, data = %s ", BluetoothBle.this.printDeviceInfo(bluetoothGatt.getDevice(), false), bluetoothGattCharacteristic, CHexConverter.byte2HexStr(bluetoothGattCharacteristic.getValue())));
                BluetoothBle.this.mBleEventCbManager.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
                if (bluetoothGatt == null) {
                    return;
                }
                JL_Log.w(BluetoothBle.TAG, String.format(Locale.getDefault(), "onCharacteristicWrite----> device = %s, characteristic = %s, status = %d ", BluetoothBle.this.printDeviceInfo(bluetoothGatt.getDevice(), false), bluetoothGattCharacteristic, Integer.valueOf(i)));
                BluetoothDevice device = bluetoothGatt.getDevice();
                BluetoothBle.this.mBleEventCbManager.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
                if (device == null || bluetoothGattCharacteristic == null || bluetoothGattCharacteristic.getService() == null) {
                    return;
                }
                UUID uuid = bluetoothGattCharacteristic.getService().getUuid();
                UUID uuid2 = bluetoothGattCharacteristic.getUuid();
                byte[] value = bluetoothGattCharacteristic.getValue();
                BluetoothBle.this.wakeUpSendDataThread(device, uuid, uuid2, i, value);
                BluetoothBle.this.mBleEventCbManager.onBleWriteStatus(bluetoothGatt.getDevice(), uuid, uuid2, value, i);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
                if (bluetoothGatt == null) {
                    JL_Log.w(BluetoothBle.TAG, "onConnectionStateChange........ gatt is null");
                    return;
                }
                BluetoothDevice device = bluetoothGatt.getDevice();
                if (device == null) {
                    JL_Log.w(BluetoothBle.TAG, "onConnectionStateChange........ device is null");
                    return;
                }
                BluetoothBle.this.mBleEventCbManager.onConnectionStateChange(bluetoothGatt, i, i2);
                String str = BluetoothBle.TAG;
                JL_Log.e(str, "ble ConnectionStateChange device :" + BluetoothBle.this.printDeviceInfo(device) + " , status:" + i + " newState:" + i2);
                BluetoothBle.this.handleBleConnection(bluetoothGatt, device, i, i2);
            }

            public void onConnectionUpdated(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4) {
                if (bluetoothGatt == null) {
                    return;
                }
                BluetoothDevice device = bluetoothGatt.getDevice();
                String str = BluetoothBle.TAG;
                JL_Log.e(str, "onConnectionUpdated :: device :" + BluetoothBle.this.printDeviceInfo(device) + " , interval:" + i + " latency:" + i2 + ",timeout = " + i3 + ", status = " + i4);
                BluetoothBle.this.mBleEventCbManager.onConnectionUpdatedCallback(bluetoothGatt, i, i2, i3, i4);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
                BluetoothBle.this.mBleEventCbManager.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
                UUID uuid;
                super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
                BluetoothBle.this.mBleEventCbManager.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
                BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
                UUID uuid2 = null;
                if (characteristic != null) {
                    uuid2 = characteristic.getUuid();
                    uuid = characteristic.getService().getUuid();
                } else {
                    uuid = null;
                }
                String str = BluetoothBle.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onDescriptorWrite UUID = ");
                sb.append(uuid2 != null ? uuid2.toString() : "");
                sb.append(", status = ");
                sb.append(i);
                JL_Log.i(str, sb.toString());
                BluetoothBle.this.onBleNotificationStatus(bluetoothGatt.getDevice(), uuid, uuid2, i == 0);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
                super.onMtuChanged(bluetoothGatt, i, i2);
                if (bluetoothGatt == null || bluetoothGatt.getDevice() == null) {
                    return;
                }
                BluetoothBle.this.mBleEventCbManager.onMtuChanged(bluetoothGatt, i, i2);
                if (i2 == 0) {
                    int i3 = i - 3;
                    BluetoothBle.this.addBleMtu(bluetoothGatt.getDevice(), i3);
                    JL_Log.e(BluetoothBle.TAG, "--onMtuChanged-- WRITE_DATA_BLOCK_SIZE : " + i3);
                }
                BluetoothBle.this.mBleEventCbManager.onBleMtuChanged(bluetoothGatt.getDevice(), BluetoothBle.this.getBleMtu(bluetoothGatt.getDevice()), i2);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            @RequiresApi(api = 26)
            public void onPhyRead(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
                JL_Log.d(BluetoothBle.TAG, String.format(Locale.getDefault(), "--onPhyRead-- txPhy = %d, rxPhy = %d, status = %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
                BluetoothBle.this.mBleEventCbManager.onPhyRead(bluetoothGatt, i, i2, i3);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            @RequiresApi(api = 26)
            public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
                JL_Log.d(BluetoothBle.TAG, String.format(Locale.getDefault(), "--onPhyUpdate-- txPhy = %d, rxPhy = %d, status = %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
                BluetoothBle.this.mBleEventCbManager.onPhyUpdate(bluetoothGatt, i, i2, i3);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
                BluetoothBle.this.mBleEventCbManager.onReadRemoteRssi(bluetoothGatt, i, i2);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
                super.onReliableWriteCompleted(bluetoothGatt, i);
                if (bluetoothGatt != null) {
                    String str = BluetoothBle.TAG;
                    JL_Log.i(str, "onReliableWriteCompleted device : " + BluetoothBle.this.printDeviceInfo(bluetoothGatt.getDevice(), false));
                }
                BluetoothBle.this.mBleEventCbManager.onReliableWriteCompleted(bluetoothGatt, i);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            @RequiresApi(api = 31)
            public void onServiceChanged(@NonNull BluetoothGatt bluetoothGatt) {
                BluetoothBle.this.mBleEventCbManager.onServiceChanged(bluetoothGatt);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
                super.onServicesDiscovered(bluetoothGatt, i);
                BluetoothBle.this.mHandler.removeMessages(BluetoothBle.MSG_BLE_DISCOVER_SERVICES_CALLBACK_TIMEOUT);
                BluetoothUtil.printBleGattServices(BluetoothBle.this.mContext, bluetoothGatt.getDevice(), bluetoothGatt, i);
                BluetoothBle.this.onBleServiceDiscovery(bluetoothGatt, i);
            }
        };
        this.mContext = (Context) ConnectUtil.checkNotNull(context);
        BluetoothPair bluetoothPair2 = (BluetoothPair) ConnectUtil.checkNotNull(bluetoothPair);
        this.mBluetoothPair = bluetoothPair2;
        bluetoothPair2.addListener(onBtDevicePairListener);
        this.mBleEventCbManager = new BleEventCbManager();
        this.mBluetoothOption = bluetoothOption == null ? BluetoothOption.createDefaultOption() : bluetoothOption;
        addListener(onBtBleListener);
        registerReceiver();
    }

    public static /* synthetic */ int access$1408(BluetoothBle bluetoothBle) {
        int i = bluetoothBle.failedCount;
        bluetoothBle.failedCount = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBleMtu(BluetoothDevice bluetoothDevice, int i) {
        int formatBleMtu = BluetoothUtil.formatBleMtu(i);
        if (bluetoothDevice == null || !isConnectedBleDevice(bluetoothDevice)) {
            return;
        }
        this.mBleMtuMap.put(bluetoothDevice.getAddress(), Integer.valueOf(formatBleMtu));
    }

    private void addSendTask(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr, OnWriteDataCallback onWriteDataCallback) {
        SendBleDataThread sendBleDataThread = this.mSendBleDataThread;
        if (sendBleDataThread != null ? sendBleDataThread.addSendTask(bluetoothDevice, uuid, uuid2, bArr, onWriteDataCallback) : false) {
            return;
        }
        onWriteDataCallback.onBleResult(bluetoothDevice, uuid, uuid2, false, bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public void clearDevices() {
        Iterator it = new ArrayList(this.mConnectedBleDevices).iterator();
        while (it.hasNext()) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) it.next();
            BluetoothGatt deviceGatt = getDeviceGatt(bluetoothDevice);
            if (deviceGatt != null) {
                if (ConnectUtil.isHasConnectPermission(this.mContext) && BluetoothUtil.isBluetoothEnable()) {
                    deviceGatt.disconnect();
                    deviceGatt.close();
                }
                this.mBleEventCbManager.onBleConnection(bluetoothDevice, 0);
            }
        }
        this.mConnectedBleDevices.clear();
        this.mBluetoothGatts.clear();
        this.mBleMtuMap.clear();
        setConnectedBleDevice(null);
        setConnectingBleDevice(null);
        stopSendDataThread();
    }

    @SuppressLint({"MissingPermission"})
    private void connectBluetoothGatt(BluetoothDevice bluetoothDevice) {
        BluetoothGatt connectGatt;
        long longValue;
        String str = TAG;
        JL_Log.i(str, "-connectBluetoothGatt- ");
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext)) {
            if (Build.VERSION.SDK_INT >= 23) {
                connectGatt = bluetoothDevice.connectGatt(this.mContext, this.mBluetoothOption.isAutoConnectBle(), this.mBluetoothGattCallback, 2);
            } else {
                connectGatt = bluetoothDevice.connectGatt(this.mContext, this.mBluetoothOption.isAutoConnectBle(), this.mBluetoothGattCallback);
            }
            if (connectGatt == null) {
                JL_Log.i(str, "-connectBluetoothGatt- bluetoothGatt is null.");
                notifyBleConnectStatus(bluetoothDevice, 0);
                return;
            }
            if (this.mBluetoothOption.isAutoConnectBle()) {
                connectGatt.connect();
            }
            Long l = this.startTimeMap.get(bluetoothDevice.getAddress());
            if (l == null) {
                longValue = ConnectUtil.getCurrentTime();
                this.startTimeMap.put(bluetoothDevice.getAddress(), Long.valueOf(longValue));
            } else {
                longValue = l.longValue();
            }
            if (!this.mBluetoothGatts.contains(connectGatt)) {
                this.mBluetoothGatts.add(connectGatt);
            }
            JL_Log.i(str, "-connectBluetoothGatt- start ble connect. startTime : " + longValue);
            return;
        }
        JL_Log.w(str, "-connectBluetoothGatt- device is null");
    }

    private void dealWithBleConnected(BluetoothDevice bluetoothDevice) {
        if (this.mBluetoothOption.isUseBleBondWay()) {
            startBleBond(bluetoothDevice);
        }
        notifyBleConnectStatus(bluetoothDevice, 2);
    }

    @SuppressLint({"MissingPermission"})
    private boolean discoverBLEDeviceServices(BluetoothDevice bluetoothDevice) {
        String str = TAG;
        JL_Log.i(str, "-discoverBLEDeviceServices- device ： " + bluetoothDevice);
        if (ConnectUtil.isHasConnectPermission(this.mContext)) {
            BluetoothGatt deviceGatt = getDeviceGatt(bluetoothDevice);
            if (deviceGatt == null) {
                JL_Log.i(str, "-discoverBLEDeviceServices- no bluetoothGatt");
                return false;
            }
            boolean discoverServices = deviceGatt.discoverServices();
            JL_Log.i(str, "-discoverBLEDeviceServices- discoverServices ret : " + discoverServices);
            return discoverServices;
        }
        return false;
    }

    @SuppressLint({"MissingPermission"})
    private boolean enableBLEDeviceNotification(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2) {
        if (!ConnectUtil.isHasConnectPermission(this.mContext)) {
            JL_Log.w(TAG, "enableBLEDeviceNotification : no connect permission.");
            return false;
        }
        BluetoothGatt deviceGatt = getDeviceGatt(bluetoothDevice);
        if (deviceGatt == null) {
            JL_Log.w(TAG, "bluetooth gatt is null....");
            return false;
        }
        BluetoothGattService service = deviceGatt.getService(uuid);
        if (service == null) {
            JL_Log.w(TAG, "bluetooth gatt service is null....");
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
        if (characteristic == null) {
            JL_Log.w(TAG, "bluetooth characteristic is null....");
            return false;
        }
        boolean characteristicNotification = deviceGatt.setCharacteristicNotification(characteristic, true);
        if (characteristicNotification) {
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(BluetoothConstant.UUID_CLIENT_CHARACTERISTIC_CONFIG);
            if (descriptor != null) {
                characteristicNotification = tryToWriteDescriptor(deviceGatt, descriptor, 0, false);
            }
        } else {
            JL_Log.w(TAG, "setCharacteristicNotification is failed....");
        }
        String str = TAG;
        JL_Log.w(str, "enableBLEDeviceNotification ret : " + characteristicNotification);
        return characteristicNotification;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleACLConnection(BluetoothDevice bluetoothDevice, int i) {
        if (BluetoothUtil.deviceEquals(this.mConnectingBleDevice, bluetoothDevice) && i == 2 && BluetoothUtil.isBleConnected(this.mContext, bluetoothDevice) && !isConnectedBleDevice(bluetoothDevice)) {
            connectBluetoothGatt(bluetoothDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public void handleBleConnection(BluetoothGatt bluetoothGatt, BluetoothDevice bluetoothDevice, int i, int i2) {
        boolean z = true;
        if (i == 0 && i2 != 0) {
            if (i2 != 2) {
                if (i2 == 1) {
                    notifyBleConnectStatus(bluetoothDevice, 1);
                    return;
                }
                return;
            }
            if (!this.mBluetoothGatts.contains(bluetoothGatt)) {
                this.mBluetoothGatts.add(bluetoothGatt);
            }
            if (!this.mConnectedBleDevices.contains(bluetoothDevice)) {
                this.mConnectedBleDevices.add(bluetoothDevice);
                addBleMtu(bluetoothDevice, 20);
            }
            if (!discoverBLEDeviceServices(bluetoothDevice)) {
                notifyBleConnectStatus(bluetoothDevice, 0);
                return;
            }
            this.mHandler.removeMessages(MSG_BLE_DISCOVER_SERVICES_CALLBACK_TIMEOUT);
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(MSG_BLE_DISCOVER_SERVICES_CALLBACK_TIMEOUT, bluetoothDevice), 3000L);
            return;
        }
        this.mHandler.removeMessages(MSG_DISCONNECT_BLE_CALLBACK_TIMEOUT);
        removeDeviceFromRecord(bluetoothDevice, bluetoothGatt);
        BluetoothUtil.refreshBleDeviceCache(this.mContext, bluetoothGatt);
        if (ConnectUtil.isHasConnectPermission(this.mContext)) {
            bluetoothGatt.close();
        }
        long currentTime = ConnectUtil.getCurrentTime();
        Long l = this.startTimeMap.get(bluetoothDevice.getAddress());
        long longValue = currentTime - (l == null ? 0L : l.longValue());
        long j = 30000 - longValue;
        if (longValue >= 30000 || longValue > j) {
            z = false;
        }
        String str = TAG;
        JL_Log.i(str, "handleBleConnection ： usedTime : " + longValue + ", leftConnectTime : " + j + ", isAllowReconnect : " + z);
        if (z && (i == 8 || i == 22 || i == 133 || i == 62 || BluetoothUtil.deviceEquals(bluetoothDevice, this.mNeedReconnectBleDevice))) {
            JL_Log.i(str, "handleBleConnection ： found connect device. retry...");
            reconnectBleDevice(bluetoothDevice);
            return;
        }
        JL_Log.w(str, "handleBleConnection ： callback device is disconnected.");
        notifyBleConnectStatus(bluetoothDevice, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBleNotificationStatus$1(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2) {
        int i = this.mBleNotificationCount + 1;
        this.mBleNotificationCount = i;
        if (i < 5) {
            if (enableBLEDeviceNotification(bluetoothDevice, uuid, uuid2)) {
                return;
            }
            disconnectBLEDevice(bluetoothDevice);
            return;
        }
        this.mBleNotificationCount = 0;
        disconnectBLEDevice(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBleServiceDiscovery$0(BluetoothGatt bluetoothGatt) {
        this.mBleNotificationCount = 0;
        if (enableBLEDeviceNotification(bluetoothGatt.getDevice(), this.mBluetoothOption.getBleServiceUUID(), this.mBluetoothOption.getBleNotificationUUID())) {
            return;
        }
        disconnectBLEDevice(bluetoothGatt.getDevice());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBleConnectStatus(BluetoothDevice bluetoothDevice, int i) {
        BluetoothGatt deviceGatt;
        if (i != 1) {
            if (BluetoothUtil.deviceEquals(bluetoothDevice, this.mConnectingBleDevice)) {
                setConnectingBleDevice(null);
                this.mHandler.removeMessages(MSG_CONNECT_BLE_TIMEOUT);
            }
            if (bluetoothDevice != null) {
                this.startTimeMap.remove(bluetoothDevice.getAddress());
            }
            if (BluetoothUtil.deviceEquals(bluetoothDevice, this.mNeedReconnectBleDevice)) {
                setNeedReconnectBleDevice(null);
                this.mHandler.removeMessages(MSG_RECONNECT_BLE);
            }
            this.mBleNotificationCount = 0;
            if (i == 2) {
                if (this.mConnectedBleDevice == null) {
                    setConnectedBleDevice(bluetoothDevice);
                }
                startSendDataThread();
            } else if (i == 0) {
                if (this.mConnectedBleDevices.remove(bluetoothDevice) && (deviceGatt = getDeviceGatt(bluetoothDevice)) != null) {
                    this.mBluetoothGatts.remove(deviceGatt);
                }
                if (this.mConnectedBleDevices.isEmpty()) {
                    setConnectedBleDevice(null);
                    stopSendDataThread();
                } else if (BluetoothUtil.deviceEquals(this.mConnectedBleDevice, bluetoothDevice)) {
                    List<BluetoothDevice> list = this.mConnectedBleDevices;
                    setConnectedBleDevice(list.get(list.size() - 1));
                }
            }
        }
        String str = TAG;
        JL_Log.i(str, "-notifyBleConnectStatus- status ： " + i);
        this.mBleEventCbManager.onBleConnection(bluetoothDevice, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBleBond(BluetoothDevice bluetoothDevice, int i) {
        if (i != 11 && BluetoothUtil.deviceEquals(bluetoothDevice, this.mBoundingBleDevice)) {
            this.failedCount = 0;
            this.boundStartTime = 0L;
            this.mBoundingBleDevice = null;
        }
        this.mBleEventCbManager.onBleBond(bluetoothDevice, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBleNotificationStatus(final BluetoothDevice bluetoothDevice, final UUID uuid, final UUID uuid2, boolean z) {
        this.mBleEventCbManager.onBleNotificationStatus(bluetoothDevice, uuid, uuid2, z);
        if (uuid == null || !uuid.equals(this.mBluetoothOption.getBleServiceUUID()) || uuid2 == null || !uuid2.equals(this.mBluetoothOption.getBleNotificationUUID())) {
            return;
        }
        if (!z) {
            JL_Log.w(TAG, String.format(Locale.getDefault(), "-onBleNotificationStatus- device : %s, serviceUuid : %s, characteristicUuid : %s, mBleNotificationCount : %d", bluetoothDevice, uuid, uuid2, Integer.valueOf(this.mBleNotificationCount)));
            this.mHandler.post(new Runnable() { // from class: com.jieli.bluetooth_connect.impl.a
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothBle.this.lambda$onBleNotificationStatus$1(bluetoothDevice, uuid, uuid2);
                }
            });
            return;
        }
        dealWithBleConnected(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBleServiceDiscovery(final BluetoothGatt bluetoothGatt, int i) {
        this.mBleEventCbManager.onServicesDiscovered(bluetoothGatt, i);
        if (bluetoothGatt == null || bluetoothGatt.getServices() == null || bluetoothGatt.getDevice() == null) {
            return;
        }
        if (this.mBluetoothOption.isOnlyConnect()) {
            JL_Log.w(TAG, "-onBleServiceDiscovery- isOnlyConnect : true, notify ble connected ok.");
            dealWithBleConnected(bluetoothGatt.getDevice());
            return;
        }
        boolean z = false;
        Iterator<BluetoothGattService> it = bluetoothGatt.getServices().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BluetoothGattService next = it.next();
            if (next.getUuid().equals(this.mBluetoothOption.getBleServiceUUID()) && next.getCharacteristic(this.mBluetoothOption.getBleWriteUUID()) != null && next.getCharacteristic(this.mBluetoothOption.getBleNotificationUUID()) != null) {
                z = true;
                break;
            }
        }
        String str = TAG;
        JL_Log.w(str, "-onBleServiceDiscovery- bServiceFound : " + z);
        if (!z) {
            if (bluetoothGatt.getServices().size() == 0) {
                setNeedReconnectBleDevice(bluetoothGatt.getDevice());
            }
            disconnectBLEDevice(bluetoothGatt.getDevice());
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.jieli.bluetooth_connect.impl.b
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothBle.this.lambda$onBleServiceDiscovery$0(bluetoothGatt);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String printDeviceInfo(BluetoothDevice bluetoothDevice, boolean z) {
        return BluetoothUtil.printBtDeviceInfo(this.mContext, bluetoothDevice, z);
    }

    private void reconnectBleDevice(BluetoothDevice bluetoothDevice) {
        this.mHandler.removeMessages(MSG_RECONNECT_BLE);
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(MSG_RECONNECT_BLE, bluetoothDevice), Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
        if (BluetoothUtil.deviceEquals(bluetoothDevice, this.mNeedReconnectBleDevice)) {
            setNeedReconnectBleDevice(null);
        }
    }

    private void registerReceiver() {
        if (this.mBluetoothBleReceiver == null) {
            this.mBluetoothBleReceiver = new BluetoothBleReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
            this.mContext.registerReceiver(this.mBluetoothBleReceiver, intentFilter);
        }
    }

    private void removeBleMtu(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null) {
            this.mBleMtuMap.remove(bluetoothDevice.getAddress());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean removeDeviceFromRecord(BluetoothDevice bluetoothDevice, BluetoothGatt bluetoothGatt) {
        boolean z = false;
        if (bluetoothDevice == null) {
            return false;
        }
        BluetoothDevice bluetoothDevice2 = null;
        Iterator it = new ArrayList(this.mConnectedBleDevices).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BluetoothDevice bluetoothDevice3 = (BluetoothDevice) it.next();
            if (bluetoothDevice3 != null && bluetoothDevice3.getAddress().equals(bluetoothDevice.getAddress())) {
                z = true;
                bluetoothDevice2 = bluetoothDevice3;
                break;
            }
        }
        if (bluetoothDevice2 != null) {
            removeBleMtu(bluetoothDevice2);
            this.mConnectedBleDevices.remove(bluetoothDevice2);
        }
        if (bluetoothGatt != null && this.mBluetoothGatts.contains(bluetoothGatt)) {
            String str = TAG;
            JL_Log.e(str, "ble ConnectionStateChange: close gatt 1 " + Thread.currentThread().getName());
            this.mBluetoothGatts.remove(bluetoothGatt);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConnectingBleDevice(BluetoothDevice bluetoothDevice) {
        this.mConnectingBleDevice = bluetoothDevice;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNeedReconnectBleDevice(BluetoothDevice bluetoothDevice) {
        this.mNeedReconnectBleDevice = bluetoothDevice;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startBleBond(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        boolean isPaired = this.mBluetoothPair.isPaired(bluetoothDevice);
        String str = TAG;
        JL_Log.i(str, "-startBleBond-  isPaired..." + isPaired);
        if (!isPaired) {
            boolean tryToPair = this.mBluetoothPair.tryToPair(bluetoothDevice);
            JL_Log.i(str, "-startBleBond-  isStartBond..." + tryToPair);
            if (tryToPair) {
                this.failedCount = 0;
                this.boundStartTime = ConnectUtil.getCurrentTime();
                this.mBoundingBleDevice = bluetoothDevice;
                return true;
            }
            onBleBond(bluetoothDevice, 10);
            return false;
        }
        onBleBond(bluetoothDevice, 12);
        return true;
    }

    private void startSendDataThread() {
        if (this.mSendBleDataThread == null) {
            SendBleDataThread sendBleDataThread = new SendBleDataThread(this, new OnThreadStateListener() { // from class: com.jieli.bluetooth_connect.impl.BluetoothBle.2
                @Override // com.jieli.bluetooth_connect.interfaces.listener.OnThreadStateListener
                public void onEnd(long j, String str) {
                    BluetoothBle.this.mSendBleDataThread = null;
                }

                @Override // com.jieli.bluetooth_connect.interfaces.listener.OnThreadStateListener
                public void onStart(long j, String str) {
                }
            });
            this.mSendBleDataThread = sendBleDataThread;
            sendBleDataThread.start();
        }
    }

    private void stopSendDataThread() {
        SendBleDataThread sendBleDataThread = this.mSendBleDataThread;
        if (sendBleDataThread != null) {
            sendBleDataThread.stopThread();
        }
    }

    @SuppressLint({"MissingPermission"})
    private boolean tryToWriteDescriptor(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i, boolean z) {
        if (ConnectUtil.isHasConnectPermission(this.mContext)) {
            if (!z) {
                z = bluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                String str = TAG;
                JL_Log.i(str, "..descriptor : .setValue  ret : " + z);
                if (z) {
                    i = 0;
                } else {
                    i++;
                    if (i >= 3) {
                        return false;
                    }
                    JL_Log.i(str, "-tryToWriteDescriptor- : retryCount : " + i + ", isSkipSetValue :  false");
                    SystemClock.sleep(50L);
                    tryToWriteDescriptor(bluetoothGatt, bluetoothGattDescriptor, i, false);
                }
            }
            if (z) {
                z = bluetoothGatt.writeDescriptor(bluetoothGattDescriptor);
                String str2 = TAG;
                JL_Log.i(str2, "..bluetoothGatt : .writeDescriptor  ret : " + z);
                if (!z) {
                    int i2 = i + 1;
                    if (i2 >= 3) {
                        return false;
                    }
                    JL_Log.i(str2, "-tryToWriteDescriptor- 2222 : retryCount : " + i2 + ", isSkipSetValue :  true");
                    SystemClock.sleep(50L);
                    tryToWriteDescriptor(bluetoothGatt, bluetoothGattDescriptor, i2, true);
                }
            }
            return z;
        }
        return false;
    }

    private void unregisterReceiver() {
        BluetoothBleReceiver bluetoothBleReceiver = this.mBluetoothBleReceiver;
        if (bluetoothBleReceiver != null) {
            this.mContext.unregisterReceiver(bluetoothBleReceiver);
            this.mBluetoothBleReceiver = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void wakeUpSendDataThread(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, int i, byte[] bArr) {
        if (this.mSendBleDataThread != null) {
            SendBleDataThread.BleSendTask bleSendTask = new SendBleDataThread.BleSendTask(bluetoothDevice, uuid, uuid2, bArr, null);
            bleSendTask.setStatus(i);
            this.mSendBleDataThread.wakeupSendThread(bleSendTask);
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBle
    @SuppressLint({"MissingPermission"})
    public boolean connectBLEDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext) && bluetoothDevice.getType() != 1) {
            String str = TAG;
            JL_Log.i(str, "-connectBLEDevice- device : " + printDeviceInfo(bluetoothDevice));
            if (this.mConnectingBleDevice != null) {
                JL_Log.w(str, "-connectBLEDevice- ConnectingBleDevice is connecting. ConnectingBleDevice : " + printDeviceInfo(this.mConnectingBleDevice));
                return false;
            } else if (isConnectedBleDevice(bluetoothDevice)) {
                JL_Log.w(str, "-connectBLEDevice- CONNECTION_CONNECTED ");
                notifyBleConnectStatus(bluetoothDevice, 2);
                return true;
            } else {
                if (!this.mBluetoothOption.isUseMultiDevice() && this.mConnectedBleDevice != null && !BluetoothUtil.deviceEquals(this.mConnectedBleDevice, bluetoothDevice)) {
                    disconnectBLEDevice(this.mConnectedBleDevice);
                    SystemClock.sleep(300L);
                }
                setConnectingBleDevice(bluetoothDevice);
                this.mHandler.removeMessages(MSG_CONNECT_BLE_TIMEOUT);
                Handler handler = this.mHandler;
                handler.sendMessageDelayed(handler.obtainMessage(MSG_CONNECT_BLE_TIMEOUT, bluetoothDevice), 30000L);
                connectBluetoothGatt(bluetoothDevice);
                return true;
            }
        }
        JL_Log.w(TAG, "-connectBLEDevice- connect to ble device is null");
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void destroy() {
        JL_Log.d(TAG, "destroy >>>>>");
        clearDevices();
        this.mBleEventCbManager.destroy();
        this.startTimeMap.clear();
        unregisterReceiver();
        this.mBluetoothPair.removeListener(this.mOnBtDevicePairListener);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBle
    @SuppressLint({"MissingPermission"})
    public boolean disconnectBLEDevice(BluetoothDevice bluetoothDevice) {
        if (ConnectUtil.isHasConnectPermission(this.mContext)) {
            String str = TAG;
            JL_Log.d(str, "-disconnectBLEDevice- device : " + printDeviceInfo(bluetoothDevice));
            try {
                if (!BluetoothUtil.isBluetoothEnable()) {
                    JL_Log.w(str, "-disconnectBLEDevice- bluetooth is close.");
                    return false;
                }
                BluetoothGatt deviceGatt = getDeviceGatt(bluetoothDevice);
                if (deviceGatt == null) {
                    JL_Log.w(str, "-disconnectBLEDevice- no bluetoothGatt");
                    return false;
                }
                JL_Log.e(str, "ble ConnectionStateChange: close gatt 3 " + Thread.currentThread().getName());
                deviceGatt.disconnect();
                this.mHandler.removeMessages(MSG_DISCONNECT_BLE_CALLBACK_TIMEOUT);
                Handler handler = this.mHandler;
                handler.sendMessageDelayed(handler.obtainMessage(MSG_DISCONNECT_BLE_CALLBACK_TIMEOUT, bluetoothDevice), 3000L);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBle
    public int getBleMtu(BluetoothDevice bluetoothDevice) {
        Integer num;
        if (bluetoothDevice == null || (num = this.mBleMtuMap.get(bluetoothDevice.getAddress())) == null) {
            return 0;
        }
        return num.intValue();
    }

    public BluetoothPair getBluetoothPair() {
        return this.mBluetoothPair;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBle
    public BluetoothDevice getConnectedBleDevice() {
        return this.mConnectedBleDevice;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBle
    public List<BluetoothDevice> getConnectedBleDevices() {
        return new ArrayList(this.mConnectedBleDevices);
    }

    public BluetoothGatt getConnectedBluetoothGatt() {
        return getDeviceGatt(getConnectedBleDevice());
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBle
    public BluetoothDevice getConnectingBleDevice() {
        return this.mConnectingBleDevice;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBle
    public BluetoothGatt getDeviceGatt(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return null;
        }
        Iterator it = new ArrayList(this.mBluetoothGatts).iterator();
        while (it.hasNext()) {
            BluetoothGatt bluetoothGatt = (BluetoothGatt) it.next();
            if (bluetoothDevice.getAddress().equals(bluetoothGatt.getDevice().getAddress())) {
                return bluetoothGatt;
            }
        }
        return null;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBle
    public boolean isBleConnecting() {
        return this.mConnectingBleDevice != null;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBle
    public boolean isConnectedBleDevice(BluetoothDevice bluetoothDevice) {
        return (bluetoothDevice == null || getDeviceGatt(bluetoothDevice) == null || !BluetoothUtil.isBleConnected(this.mContext, bluetoothDevice)) ? false : true;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBle
    @SuppressLint({"MissingPermission"})
    public boolean requestBleMtu(BluetoothDevice bluetoothDevice, int i) {
        if (ConnectUtil.isHasConnectPermission(this.mContext)) {
            BluetoothGatt deviceGatt = getDeviceGatt(bluetoothDevice);
            if (deviceGatt == null) {
                JL_Log.e(TAG, "--requestBleMtu-- Failed to get gatt.");
                return false;
            } else if (Build.VERSION.SDK_INT >= 21) {
                String str = TAG;
                JL_Log.i(str, "--requestBleMtu-- requestMtu is started.");
                if (deviceGatt.requestMtu(i + 3)) {
                    return true;
                }
                JL_Log.e(str, "--requestBleMtu-- requestMtu failed. callback old mtu.");
                addBleMtu(bluetoothDevice, 20);
                this.mBleEventCbManager.onBleMtuChanged(bluetoothDevice, 20, 257);
                return false;
            } else {
                JL_Log.w(TAG, "--requestBleMtu-- android sdk not support requestMtu method.");
                addBleMtu(bluetoothDevice, 20);
                this.mBleEventCbManager.onBleMtuChanged(bluetoothDevice, 20, 257);
                return true;
            }
        }
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void setBluetoothOption(BluetoothOption bluetoothOption) {
        if (bluetoothOption != null) {
            this.mBluetoothOption = bluetoothOption;
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBle
    public void setConnectedBleDevice(BluetoothDevice bluetoothDevice) {
        if (BluetoothUtil.deviceEquals(this.mConnectedBleDevice, bluetoothDevice)) {
            return;
        }
        this.mConnectedBleDevice = bluetoothDevice;
        if (bluetoothDevice != null) {
            this.mBleEventCbManager.onSwitchBleDevice(bluetoothDevice);
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBle
    public void writeDataByBleAsync(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr, OnWriteDataCallback onWriteDataCallback) {
        addSendTask(bluetoothDevice, uuid, uuid2, bArr, onWriteDataCallback);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBle
    @SuppressLint({"MissingPermission"})
    public synchronized boolean writeDataByBleSync(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr) {
        boolean z = false;
        if (bluetoothDevice != null) {
            if (ConnectUtil.isHasConnectPermission(this.mContext) && 1 != bluetoothDevice.getType() && uuid != null && uuid2 != null) {
                if (bArr != null && bArr.length != 0) {
                    BluetoothGatt deviceGatt = getDeviceGatt(bluetoothDevice);
                    if (deviceGatt == null) {
                        JL_Log.i(TAG, "-writeDataByBleSync- Bluetooth gatt is close.");
                        return false;
                    }
                    BluetoothGattService service = deviceGatt.getService(uuid);
                    if (service == null) {
                        JL_Log.i(TAG, "-writeDataByBleSync- gattService have not found.");
                        return false;
                    }
                    BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
                    if (characteristic == null) {
                        JL_Log.i(TAG, "-writeDataByBleSync- gattCharacteristic have not found.");
                        return false;
                    }
                    try {
                        characteristic.setValue(bArr);
                        z = deviceGatt.writeCharacteristic(characteristic);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String str = TAG;
                        JL_Log.e(str, "-writeDataByBleSync- have an exception : " + e);
                    }
                    String str2 = TAG;
                    JL_Log.d(str2, "-writeDataByBleSync- send data : " + z);
                    return z;
                }
                JL_Log.i(TAG, "-writeDataByBleSync- data is empty.");
                return false;
            }
        }
        JL_Log.i(TAG, "-writeDataByBleSync- param is error.");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String printDeviceInfo(BluetoothDevice bluetoothDevice) {
        return printDeviceInfo(bluetoothDevice, true);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void addListener(OnBtBleListener onBtBleListener) {
        this.mBleEventCbManager.addListener(onBtBleListener);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void removeListener(OnBtBleListener onBtBleListener) {
        this.mBleEventCbManager.removeListener(onBtBleListener);
    }
}
