package com.jieli.bluetooth_connect.impl;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import androidx.annotation.NonNull;
import com.jieli.bluetooth_connect.bean.BluetoothOption;
import com.jieli.bluetooth_connect.bean.ErrorInfo;
import com.jieli.bluetooth_connect.bean.ble.BleScanMessage;
import com.jieli.bluetooth_connect.bean.history.HistoryRecord;
import com.jieli.bluetooth_connect.data.HistoryRecordDbHelper;
import com.jieli.bluetooth_connect.data.HistoryRecordObserver;
import com.jieli.bluetooth_connect.interfaces.IBluetoothOperation;
import com.jieli.bluetooth_connect.interfaces.callback.BluetoothEventCallback;
import com.jieli.bluetooth_connect.interfaces.callback.OnHistoryRecordCallback;
import com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtDiscoveryListener;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtSppListener;
import com.jieli.bluetooth_connect.interfaces.listener.OnWriteDataCallback;
import com.jieli.bluetooth_connect.tool.BluetoothEventCbManager;
import com.jieli.bluetooth_connect.tool.ReConnectHelper;
import com.jieli.bluetooth_connect.util.BluetoothUtil;
import com.jieli.bluetooth_connect.util.CHexConverter;
import com.jieli.bluetooth_connect.util.ConnectUtil;
import com.jieli.bluetooth_connect.util.JL_Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
/* loaded from: classes11.dex */
public class BluetoothManager implements IBluetoothOperation {
    private static final int ADJUST_BLE_MTU_TIMEOUT = 6000;
    private static final int MSG_ADJUST_BLE_MTU_TIMEOUT = 4113;
    private static final int MSG_CONNECT_DEVICE_TIMEOUT = 4112;
    private static final int REMOVE_HISTORY_RECORD_TIMEOUT = 10000;
    private static final String TAG = "BluetoothManager";
    private final BluetoothBle mBluetoothBle;
    private final BluetoothBrEdr mBluetoothBrEdr;
    private final BluetoothDiscovery mBluetoothDiscovery;
    private BluetoothOption mBluetoothOption;
    private final BluetoothPair mBluetoothPair;
    private final BluetoothSpp mBluetoothSpp;
    private volatile BluetoothDevice mConnectedDevice;
    private volatile BluetoothDevice mConnectingDevice;
    private final Context mContext;
    private final HistoryRecordDbHelper mHistoryRecordDbHelper;
    private final OnBrEdrListener mOnBrEdrListener;
    private final OnBtBleListener mOnBtBleListener;
    private final OnBtDevicePairListener mOnBtDevicePairListener;
    private final OnBtDiscoveryListener mOnBtDiscoveryListener;
    private final OnBtSppListener mOnBtSppListener;
    private final ReConnectHelper mReConnectHelper;
    private RemoveHistoryRecordTask mRemoveHistoryRecordTask;
    private final BluetoothEventCbManager mEventCbManager = new BluetoothEventCbManager();
    private final List<BluetoothDevice> mConnectedBtDevices = Collections.synchronizedList(new ArrayList());
    private final Set<String> mSkipRecordSet = new HashSet();
    private final Handler mHandler = new Handler(new Handler.Callback() { // from class: com.jieli.bluetooth_connect.impl.d
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            boolean lambda$new$0;
            lambda$new$0 = BluetoothManager.this.lambda$new$0(message);
            return lambda$new$0;
        }
    });

    /* loaded from: classes11.dex */
    public class RemoveHistoryRecordTask implements Runnable {
        public static final int STATE_END = 2;
        public static final int STATE_REMOVE_DEVICE = 0;
        public static final int STATE_REMOVE_MAPPED = 1;
        public static final int WAY_DISCONNECT = 1;
        public static final int WAY_REMOVE = 0;
        public String disconnectAddress;
        public final HistoryRecord mHistoryRecord;
        private final OnHistoryRecordCallback mOnHistoryRecordCallback;
        public String removeAddress;
        public int state = 0;

        public RemoveHistoryRecordTask(@NonNull HistoryRecord historyRecord, OnHistoryRecordCallback onHistoryRecordCallback) {
            this.mHistoryRecord = historyRecord;
            this.mOnHistoryRecordCallback = onHistoryRecordCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            BluetoothDevice bluetoothDevice;
            int i = this.state;
            if (i == 0) {
                bluetoothDevice = BluetoothUtil.getRemoteDevice(BluetoothManager.this.mContext, this.mHistoryRecord.getAddress());
            } else {
                if (i == 1) {
                    if (!this.mHistoryRecord.getAddress().equals(this.mHistoryRecord.getMappedAddress())) {
                        bluetoothDevice = BluetoothUtil.getRemoteDevice(BluetoothManager.this.mContext, this.mHistoryRecord.getMappedAddress());
                    } else {
                        JL_Log.d(BluetoothManager.TAG, "-RemoveHistoryRecordTask- same address, skip");
                        this.state++;
                    }
                }
                bluetoothDevice = null;
            }
            JL_Log.d(BluetoothManager.TAG, "-RemoveHistoryRecordTask- start :: state = " + this.state + ", device = " + BluetoothManager.this.printDeviceInfo(bluetoothDevice));
            if (bluetoothDevice != null) {
                this.removeAddress = bluetoothDevice.getAddress();
                if (BluetoothManager.this.isConnectedDevice(bluetoothDevice)) {
                    BluetoothManager.this.mHandler.removeCallbacks(this);
                    BluetoothManager.this.mHandler.postDelayed(this, 10000L);
                    this.disconnectAddress = bluetoothDevice.getAddress();
                    BluetoothManager.this.disconnectBtDevice(bluetoothDevice);
                    JL_Log.i(BluetoothManager.TAG, "-RemoveHistoryRecordTask- disconnectBtDevice >>> ");
                    return;
                }
                this.disconnectAddress = null;
                if (BluetoothManager.this.isPaired(bluetoothDevice)) {
                    boolean tryToUnPair = BluetoothManager.this.tryToUnPair(bluetoothDevice);
                    JL_Log.i(BluetoothManager.TAG, "-RemoveHistoryRecordTask- tryToUnPair >>> " + tryToUnPair);
                    if (tryToUnPair) {
                        this.state++;
                        BluetoothManager.this.mHandler.removeCallbacks(this);
                        BluetoothManager.this.mHandler.postDelayed(this, 10000L);
                        return;
                    }
                }
            }
            JL_Log.i(BluetoothManager.TAG, "-RemoveHistoryRecordTask- end :: state = " + this.state);
            int i2 = this.state;
            if (i2 > 1) {
                BluetoothManager.this.mHistoryRecordDbHelper.deleteHistoryRecord(this.mHistoryRecord);
                OnHistoryRecordCallback onHistoryRecordCallback = this.mOnHistoryRecordCallback;
                if (onHistoryRecordCallback != null) {
                    onHistoryRecordCallback.onSuccess(this.mHistoryRecord);
                }
                BluetoothManager.this.mRemoveHistoryRecordTask = null;
                return;
            }
            this.state = i2 + 1;
            this.removeAddress = null;
            BluetoothManager.this.mHandler.removeCallbacks(this);
            BluetoothManager.this.mHandler.post(this);
        }
    }

    public BluetoothManager(Context context, BluetoothOption bluetoothOption) {
        OnBtDiscoveryListener onBtDiscoveryListener = new OnBtDiscoveryListener() { // from class: com.jieli.bluetooth_connect.impl.BluetoothManager.2
            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDiscoveryListener
            public void onDiscoveryDevice(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage) {
                BluetoothManager.this.mEventCbManager.onDiscovery(bluetoothDevice, bleScanMessage);
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDiscoveryListener
            public void onDiscoveryError(ErrorInfo errorInfo) {
                String str = BluetoothManager.TAG;
                JL_Log.e(str, "-onDiscoveryError- " + errorInfo);
                BluetoothManager.this.mEventCbManager.onDiscoveryStatus(true, false);
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDiscoveryListener
            public void onDiscoveryStatusChange(boolean z, boolean z2) {
                BluetoothManager.this.mEventCbManager.onDiscoveryStatus(z, z2);
                if (z2) {
                    BluetoothManager.this.syncSystemBtDeviceList(z);
                }
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDiscoveryListener
            public void onShowProductDialog(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage) {
                BluetoothManager.this.mEventCbManager.onShowDialog(bluetoothDevice, bleScanMessage);
            }
        };
        this.mOnBtDiscoveryListener = onBtDiscoveryListener;
        OnBtDevicePairListener onBtDevicePairListener = new OnBtDevicePairListener() { // from class: com.jieli.bluetooth_connect.impl.BluetoothManager.3
            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
            public void onAdapterStatus(boolean z, boolean z2) {
                BluetoothManager.this.mEventCbManager.onAdapterStatus(z, z2);
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
            public void onBtDeviceBond(BluetoothDevice bluetoothDevice, int i) {
                HistoryRecord historyRecord;
                BluetoothManager.this.mEventCbManager.onBondStatus(bluetoothDevice, i);
                if (bluetoothDevice == null || i != 10 || BluetoothManager.this.notifyRemoveHistoryRecordTask(bluetoothDevice, 0) || (historyRecord = BluetoothManager.this.getHistoryRecord(bluetoothDevice.getAddress())) == null) {
                    return;
                }
                if (BluetoothManager.this.mBluetoothOption.isNotAssociatedEDR()) {
                    JL_Log.w(BluetoothManager.TAG, String.format(Locale.getDefault(), "Device[%s] is un-bonded.Skipping the progress of delete history record.", bluetoothDevice));
                    return;
                }
                BluetoothManager bluetoothManager = BluetoothManager.this;
                bluetoothManager.disconnectBtDevice(BluetoothUtil.getRemoteDevice(bluetoothManager.mContext, historyRecord.getAddress()));
                BluetoothManager.this.mHistoryRecordDbHelper.deleteHistoryRecord(historyRecord);
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
            public void onPairError(BluetoothDevice bluetoothDevice, ErrorInfo errorInfo) {
                if (errorInfo != null) {
                    errorInfo.setDevice(bluetoothDevice);
                }
                BluetoothManager.this.mEventCbManager.onError(errorInfo);
                BluetoothManager.this.notifyRemoveHistoryRecordTask(bluetoothDevice, 0);
            }
        };
        this.mOnBtDevicePairListener = onBtDevicePairListener;
        OnBrEdrListener onBrEdrListener = new OnBrEdrListener() { // from class: com.jieli.bluetooth_connect.impl.BluetoothManager.4
            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
            public void onA2dpStatus(BluetoothDevice bluetoothDevice, int i) {
                BluetoothManager.this.mEventCbManager.onA2dpStatus(bluetoothDevice, i);
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
            public void onBrEdrConnection(BluetoothDevice bluetoothDevice, int i) {
                HistoryRecord historyRecord;
                BluetoothManager.this.mEventCbManager.onBtDeviceConnectStatus(bluetoothDevice, i);
                if (i != 2 || (historyRecord = BluetoothManager.this.getHistoryRecord(bluetoothDevice.getAddress())) == null) {
                    return;
                }
                BluetoothDevice remoteDevice = BluetoothUtil.getRemoteDevice(BluetoothManager.this.mContext, historyRecord.getAddress());
                boolean z = !BluetoothManager.this.isConnectedDevice(remoteDevice);
                boolean z2 = false;
                if (z && !BluetoothManager.this.mBluetoothOption.isUseMultiDevice() && BluetoothManager.this.getConnectedDevice() != null) {
                    BluetoothManager bluetoothManager = BluetoothManager.this;
                    if (!bluetoothManager.isMatchDevice(bluetoothManager.getConnectedDevice(), bluetoothDevice)) {
                        z = false;
                    }
                }
                if (!z || BluetoothManager.this.mBluetoothOption.isReconnect()) {
                    z2 = z;
                }
                if (z2) {
                    BluetoothManager.this.connectBtDevice(remoteDevice, historyRecord.getConnectType());
                }
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
            public void onDeviceUuids(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr) {
                BluetoothManager.this.mEventCbManager.onDeviceUuidsDiscovery(bluetoothDevice, parcelUuidArr);
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
            public void onEdrService(boolean z, int i, BluetoothProfile bluetoothProfile) {
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
            public void onHfpStatus(BluetoothDevice bluetoothDevice, int i) {
                BluetoothManager.this.mEventCbManager.onHfpStatus(bluetoothDevice, i);
            }
        };
        this.mOnBrEdrListener = onBrEdrListener;
        OnBtSppListener onBtSppListener = new OnBtSppListener() { // from class: com.jieli.bluetooth_connect.impl.BluetoothManager.5
            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtSppListener
            public void onSppConnection(BluetoothDevice bluetoothDevice, UUID uuid, int i) {
                BluetoothManager.this.mEventCbManager.onSppStatus(bluetoothDevice, i);
                String str = BluetoothManager.TAG;
                JL_Log.i(str, "onSppConnection >>>>> " + BluetoothManager.this.printDeviceInfo(bluetoothDevice) + ", uuid = " + uuid + ", state : " + i);
                if (i != 1) {
                    BluetoothManager.this.notifyConnectionStatus(bluetoothDevice, i);
                }
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtSppListener
            public void onSppDataNotify(BluetoothDevice bluetoothDevice, UUID uuid, byte[] bArr) {
                BluetoothManager.this.mEventCbManager.onSppDataNotification(bluetoothDevice, uuid, bArr);
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtSppListener
            public void onSwitchSppDevice(BluetoothDevice bluetoothDevice) {
            }
        };
        this.mOnBtSppListener = onBtSppListener;
        OnBtBleListener onBtBleListener = new OnBtBleListener() { // from class: com.jieli.bluetooth_connect.impl.BluetoothManager.6
            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
            public void onBleBond(BluetoothDevice bluetoothDevice, int i) {
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
            public void onBleConnection(BluetoothDevice bluetoothDevice, int i) {
                BluetoothManager.this.mEventCbManager.onBleConnection(bluetoothDevice, i);
                String str = BluetoothManager.TAG;
                JL_Log.i(str, "OnBtBleListener : onBleConnection >> status ： " + i + ", " + BluetoothManager.this.printDeviceInfo(bluetoothDevice));
                if (i != 1) {
                    BluetoothManager.this.notifyConnectionStatus(bluetoothDevice, i);
                }
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
            public void onBleDataNotify(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr) {
                BluetoothManager.this.mEventCbManager.onBleDataNotification(bluetoothDevice, uuid, uuid2, bArr);
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
            public void onBleMtuChanged(BluetoothDevice bluetoothDevice, int i, int i2) {
                BluetoothManager.this.mEventCbManager.onBleDataBlockChanged(bluetoothDevice, i, i2);
                if (BluetoothManager.this.mHandler.hasMessages(4113)) {
                    BluetoothManager.this.mHandler.removeMessages(4113);
                    BluetoothManager.this.mEventCbManager.onConnection(bluetoothDevice, 2);
                }
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
            public void onBleNotificationStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, boolean z) {
                BluetoothManager.this.mEventCbManager.onBleNotificationStatus(bluetoothDevice, uuid, uuid2, z);
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
            public void onBleWriteStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr, int i) {
                BluetoothManager.this.mEventCbManager.onBleWriteStatus(bluetoothDevice, uuid, uuid2, bArr, i);
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
            public void onConnectionUpdatedCallback(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4) {
                BluetoothManager.this.mEventCbManager.onConnectionUpdated(bluetoothGatt, i, i2, i3, i4);
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
            public void onSwitchBleDevice(BluetoothDevice bluetoothDevice) {
            }
        };
        this.mOnBtBleListener = onBtBleListener;
        Context context2 = (Context) ConnectUtil.checkNotNull(context);
        this.mContext = context2;
        ConnectUtil.setContext(context2);
        bluetoothOption = bluetoothOption == null ? BluetoothOption.createDefaultOption() : bluetoothOption;
        this.mBluetoothOption = bluetoothOption;
        this.mBluetoothDiscovery = new BluetoothDiscovery(context, bluetoothOption, onBtDiscoveryListener);
        BluetoothPair bluetoothPair = new BluetoothPair(context, onBtDevicePairListener);
        this.mBluetoothPair = bluetoothPair;
        BluetoothBrEdr bluetoothBrEdr = new BluetoothBrEdr(context, bluetoothPair, onBrEdrListener);
        this.mBluetoothBrEdr = bluetoothBrEdr;
        this.mBluetoothSpp = new BluetoothSpp(context, bluetoothBrEdr, bluetoothOption, onBtSppListener);
        this.mBluetoothBle = new BluetoothBle(context, bluetoothPair, bluetoothOption, onBtBleListener);
        this.mReConnectHelper = new ReConnectHelper(context, this);
        HistoryRecordDbHelper historyRecordDbHelper = new HistoryRecordDbHelper(context, this.mBluetoothOption);
        this.mHistoryRecordDbHelper = historyRecordDbHelper;
        historyRecordDbHelper.addHistoryRecordObserver(new HistoryRecordObserver() { // from class: com.jieli.bluetooth_connect.impl.BluetoothManager.1
            @Override // com.jieli.bluetooth_connect.data.HistoryRecordObserver
            public void onDelete(HistoryRecord historyRecord) {
                BluetoothManager.this.mEventCbManager.onHistoryRecordChange(1, historyRecord);
            }

            @Override // com.jieli.bluetooth_connect.data.HistoryRecordObserver
            public void onInsert(HistoryRecord historyRecord) {
                BluetoothManager.this.mEventCbManager.onHistoryRecordChange(0, historyRecord);
            }

            @Override // com.jieli.bluetooth_connect.data.HistoryRecordObserver
            public void onModify(HistoryRecord historyRecord) {
                BluetoothManager.this.mEventCbManager.onHistoryRecordChange(2, historyRecord);
            }
        });
    }

    private void changeConnectedDevice(BluetoothDevice bluetoothDevice) {
        if (BluetoothUtil.deviceEquals(this.mConnectedDevice, bluetoothDevice)) {
            return;
        }
        BluetoothDevice bluetoothDevice2 = this.mConnectedDevice;
        this.mConnectedDevice = bluetoothDevice;
        if (bluetoothDevice2 != null && isConnectedDevice(bluetoothDevice)) {
            if (this.mBluetoothBle.isConnectedBleDevice(bluetoothDevice)) {
                this.mBluetoothBle.setConnectedBleDevice(bluetoothDevice);
            } else if (this.mBluetoothSpp.isConnectedSppDevice(bluetoothDevice)) {
                this.mBluetoothSpp.setConnectedSppDevice(bluetoothDevice);
            }
            HistoryRecord historyRecord = getHistoryRecord(bluetoothDevice.getAddress());
            historyRecord.setOnlineTime(System.currentTimeMillis());
            this.mHistoryRecordDbHelper.updateHistoryRecord(historyRecord);
            this.mEventCbManager.onSwitchConnectedDevice(bluetoothDevice);
        }
        String str = TAG;
        JL_Log.d(str, "-changeConnectedDevice- ConnectedDevice : " + printDeviceInfo(bluetoothDevice));
    }

    private boolean checkIsValidDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        return BluetoothUtil.deviceEquals(bluetoothDevice, getConnectingDevice()) || BluetoothUtil.deviceEquals(bluetoothDevice, getConnectingBrEdrDevice()) || isConnectedDevice(bluetoothDevice) || getHistoryRecord(bluetoothDevice.getAddress()) != null || this.mConnectedBtDevices.contains(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$0(Message message) {
        BluetoothDevice bluetoothDevice;
        int i = message.what;
        if (i != 4112) {
            if (i == 4113 && (bluetoothDevice = (BluetoothDevice) message.obj) != null && isConnectedDevice(bluetoothDevice)) {
                this.mEventCbManager.onConnection(bluetoothDevice, 2);
                return true;
            }
            return true;
        }
        BluetoothDevice bluetoothDevice2 = (BluetoothDevice) message.obj;
        if (bluetoothDevice2 != null) {
            if (!isConnectedDevice(bluetoothDevice2)) {
                String str = TAG;
                JL_Log.i(str, "-MSG_CONNECT_DEVICE_TIMEOUT- connect timeout, mDevice : " + printDeviceInfo(bluetoothDevice2));
                notifyConnectionStatus(bluetoothDevice2, 0);
                if (isConnecting()) {
                    disconnectBtDevice(bluetoothDevice2);
                }
            }
            setConnectingDevice(null);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$writeDataToBLEDevice$1(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, boolean z, byte[] bArr) {
        JL_Log.d(TAG, String.format(Locale.getDefault(), "-writeDataToBLEDevice- device : %s, serviceUUID:[%s], characteristicUUID:[%s], data:%s, result:%s", bluetoothDevice, uuid, uuid2, CHexConverter.byte2HexStr(bArr), Boolean.valueOf(z)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyConnectionStatus(BluetoothDevice bluetoothDevice, int i) {
        HistoryRecord historyRecord;
        boolean checkIsValidDevice = checkIsValidDevice(bluetoothDevice);
        String str = TAG;
        JL_Log.e(str, "-notifyConnectionStatus- device : " + printDeviceInfo(bluetoothDevice) + ", status : " + i + ", checkIsValidDevice = " + checkIsValidDevice);
        if (checkIsValidDevice) {
            if (i != 1) {
                if (BluetoothUtil.deviceEquals(bluetoothDevice, getConnectingDevice())) {
                    setConnectingDevice(null);
                    this.mHandler.removeMessages(4112);
                }
                if (i == 0) {
                    removeDevice(bluetoothDevice);
                    notifyRemoveHistoryRecordTask(bluetoothDevice, 1);
                } else if (i == 2) {
                    if (!this.mConnectedBtDevices.contains(bluetoothDevice)) {
                        this.mConnectedBtDevices.add(bluetoothDevice);
                    }
                    if (this.mConnectedDevice == null) {
                        changeConnectedDevice(bluetoothDevice);
                    }
                    boolean isConnectedSppDevice = isConnectedSppDevice(bluetoothDevice);
                    if (!this.mSkipRecordSet.contains(bluetoothDevice.getAddress())) {
                        this.mHistoryRecordDbHelper.saveHistoryRecord(bluetoothDevice, isConnectedSppDevice ? 1 : 0);
                        if (this.mConnectedDevice != null && !BluetoothUtil.deviceEquals(this.mConnectedDevice, bluetoothDevice) && (historyRecord = getHistoryRecord(this.mConnectedDevice.getAddress())) != null) {
                            historyRecord.setOnlineTime(System.currentTimeMillis() + 200);
                            this.mHistoryRecordDbHelper.updateHistoryRecord(historyRecord);
                        }
                    } else {
                        this.mSkipRecordSet.remove(bluetoothDevice.getAddress());
                    }
                    JL_Log.i(str, "-notifyConnectionStatus- CONNECTION_OK, connected List : " + getConnectedDeviceList().size() + ", connectWay : " + (isConnectedSppDevice ? 1 : 0));
                    if (!isConnectedSppDevice && getBluetoothOption().isNeedChangeBleMtu() && getBluetoothOption().getMtu() > 20 && this.mBluetoothBle.requestBleMtu(bluetoothDevice, getBluetoothOption().getMtu())) {
                        this.mHandler.removeMessages(4113);
                        Handler handler = this.mHandler;
                        handler.sendMessageDelayed(handler.obtainMessage(4113, bluetoothDevice), 6000L);
                        return;
                    }
                }
            }
            JL_Log.d(str, "-notifyConnectionStatus- onConnection >>> device : " + printDeviceInfo(bluetoothDevice) + ", status : " + i);
            this.mEventCbManager.onConnection(bluetoothDevice, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean notifyRemoveHistoryRecordTask(BluetoothDevice bluetoothDevice, int i) {
        boolean z = false;
        if (this.mRemoveHistoryRecordTask != null && bluetoothDevice != null) {
            if ((i == 1 && bluetoothDevice.getAddress().equals(this.mRemoveHistoryRecordTask.disconnectAddress)) || (i != 1 && bluetoothDevice.getAddress().equals(this.mRemoveHistoryRecordTask.removeAddress))) {
                z = true;
            }
            if (z) {
                JL_Log.i(TAG, "-RemoveHistoryRecordTask- notifyRemoveHistoryRecordTask.... device = " + bluetoothDevice + ", way = " + i);
                this.mHandler.removeCallbacks(this.mRemoveHistoryRecordTask);
                this.mHandler.post(this.mRemoveHistoryRecordTask);
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String printDeviceInfo(BluetoothDevice bluetoothDevice) {
        return BluetoothUtil.printBtDeviceInfo(this.mContext, bluetoothDevice);
    }

    private void removeDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null) {
            this.mConnectedBtDevices.remove(bluetoothDevice);
            if (getConnectedDeviceList().isEmpty()) {
                changeConnectedDevice(null);
            } else if (BluetoothUtil.deviceEquals(this.mConnectedDevice, bluetoothDevice)) {
                List<BluetoothDevice> list = this.mConnectedBtDevices;
                changeConnectedDevice(list.get(list.size() - 1));
            }
        }
    }

    private void setConnectingDevice(BluetoothDevice bluetoothDevice) {
        this.mConnectingDevice = bluetoothDevice;
    }

    private void startRemoveHistoryRecordTask(HistoryRecord historyRecord, OnHistoryRecordCallback onHistoryRecordCallback) {
        if (this.mRemoveHistoryRecordTask != null) {
            if (onHistoryRecordCallback != null) {
                onHistoryRecordCallback.onFailed(7, "Removing History Record in progress.");
                return;
            }
            return;
        }
        RemoveHistoryRecordTask removeHistoryRecordTask = new RemoveHistoryRecordTask(historyRecord, onHistoryRecordCallback);
        this.mRemoveHistoryRecordTask = removeHistoryRecordTask;
        this.mHandler.post(removeHistoryRecordTask);
    }

    private void stopRemoveHistoryRecordTask() {
        RemoveHistoryRecordTask removeHistoryRecordTask = this.mRemoveHistoryRecordTask;
        if (removeHistoryRecordTask != null) {
            this.mHandler.removeCallbacks(removeHistoryRecordTask);
            this.mRemoveHistoryRecordTask = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public void syncSystemBtDeviceList(boolean z) {
        if (ConnectUtil.isHasConnectPermission(this.mContext)) {
            Context context = this.mContext;
            List<BluetoothDevice> connectedBleDeviceList = z ? BluetoothUtil.getConnectedBleDeviceList(context) : BluetoothUtil.getSystemConnectedBtDeviceList(context);
            if (connectedBleDeviceList == null) {
                return;
            }
            String scanFilterData = this.mBluetoothOption.getScanFilterData();
            for (BluetoothDevice bluetoothDevice : connectedBleDeviceList) {
                if (!isConnectedDevice(bluetoothDevice) && !getDiscoveredBluetoothDevices().contains(bluetoothDevice) && ((z && (bluetoothDevice.getType() == 2 || bluetoothDevice.getType() == 3)) || (bluetoothDevice.getName() != null && scanFilterData != null && bluetoothDevice.getName().startsWith(scanFilterData)))) {
                    BleScanMessage bleScanMessage = new BleScanMessage();
                    bleScanMessage.setEnableConnect(true);
                    this.mEventCbManager.onDiscovery(bluetoothDevice, bleScanMessage);
                }
            }
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public void clearHistoryRecords() {
        this.mHistoryRecordDbHelper.clearAllHistoryRecord();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean connectBLEDevice(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothBle.connectBLEDevice(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    @SuppressLint({"MissingPermission"})
    public boolean connectBtDevice(BluetoothDevice bluetoothDevice, int i) {
        String str = TAG;
        JL_Log.i(str, "-connectBtDevice-- device : " + printDeviceInfo(bluetoothDevice) + ", connectWay = " + i);
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext)) {
            if (!BluetoothUtil.isBluetoothEnable()) {
                JL_Log.i(str, "-connectBtDevice- isBluetoothEnabled : false.");
                return false;
            } else if (isConnecting()) {
                JL_Log.i(str, "-connectBtDevice- device is connecting. device : " + printDeviceInfo(getConnectingDevice()));
                return false;
            } else if (isConnectedDevice(bluetoothDevice)) {
                JL_Log.i(str, "-connectBtDevice- isConnectedDevice >>>>");
                notifyConnectionStatus(bluetoothDevice, 2);
                return true;
            } else {
                setConnectingDevice(bluetoothDevice);
                notifyConnectionStatus(bluetoothDevice, 1);
                this.mHandler.removeMessages(4112);
                Handler handler = this.mHandler;
                handler.sendMessageDelayed(handler.obtainMessage(4112, bluetoothDevice), 40000L);
                int type = bluetoothDevice.getType();
                if (type != 0) {
                    if (type == 1) {
                        JL_Log.w(str, "-connect- connectSPPDevice by device type");
                        return connectSPPDevice(bluetoothDevice);
                    } else if (type == 2) {
                        JL_Log.w(str, "-connect- connectBLEDevice by device type");
                        return connectBLEDevice(bluetoothDevice);
                    } else if (type != 3) {
                        return true;
                    }
                }
                if (i == 0) {
                    JL_Log.w(str, "-connect- connectBLEDevice by connectWay");
                    return connectBLEDevice(bluetoothDevice);
                }
                JL_Log.w(str, "-connect- connectSPPDevice by connectWay");
                return connectSPPDevice(bluetoothDevice);
            }
        }
        JL_Log.i(str, "-connectBtDevice- device is null");
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean connectBtDeviceWithoutRecord(BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice != null) {
            this.mSkipRecordSet.add(bluetoothDevice.getAddress());
        }
        return connectBtDevice(bluetoothDevice, i);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean connectByProfiles(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothBrEdr.connectByProfiles(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public void connectHistoryRecord(HistoryRecord historyRecord, OnHistoryRecordCallback onHistoryRecordCallback) {
        if (historyRecord == null) {
            return;
        }
        this.mReConnectHelper.reconnectDevice((historyRecord.getConnectType() == 1 && historyRecord.getDevType() == 5) ? historyRecord.getMappedAddress() : historyRecord.getAddress(), historyRecord.getDevType() == 5 ? 0 : historyRecord.getConnectType(), onHistoryRecordCallback);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean connectSPPDevice(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothSpp.connectSPPDevice(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public void destroy() {
        JL_Log.w(TAG, "destroy : >>>>>>>>>>>>");
        stopRemoveHistoryRecordTask();
        this.mBluetoothBle.removeListener(this.mOnBtBleListener);
        this.mBluetoothBle.destroy();
        this.mBluetoothSpp.removeListener(this.mOnBtSppListener);
        this.mBluetoothSpp.destroy();
        this.mBluetoothBrEdr.removeListener(this.mOnBrEdrListener);
        this.mBluetoothBrEdr.destroy();
        this.mBluetoothPair.removeListener(this.mOnBtDevicePairListener);
        this.mBluetoothPair.destroy();
        this.mBluetoothDiscovery.removeListener(this.mOnBtDiscoveryListener);
        this.mBluetoothDiscovery.destroy();
        this.mEventCbManager.release();
        this.mHandler.removeCallbacksAndMessages(null);
        this.mConnectedBtDevices.clear();
        this.mSkipRecordSet.clear();
        this.mReConnectHelper.destroy();
        this.mHistoryRecordDbHelper.destroy();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean disconnectBLEDevice(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothBle.disconnectBLEDevice(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public void disconnectBtDevice(BluetoothDevice bluetoothDevice) {
        String str = TAG;
        JL_Log.d(str, "-disconnectBtDevice-- device : " + printDeviceInfo(bluetoothDevice));
        if (bluetoothDevice == null) {
            JL_Log.i(str, "----disconnectBtDevice--- device not allow null object.....................");
        } else if (isConnectedBLEDevice(bluetoothDevice)) {
            disconnectBLEDevice(bluetoothDevice);
        } else if (isConnectedSppDevice(bluetoothDevice)) {
            disconnectSPPDevice(bluetoothDevice);
        } else {
            notifyConnectionStatus(bluetoothDevice, 0);
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean disconnectByProfiles(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothBrEdr.disconnectByProfiles(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean disconnectSPPDevice(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothSpp.disconnectSPPDevice(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public void fastConnect() {
        List<HistoryRecord> historyRecordList = getHistoryRecordList();
        if (historyRecordList != null) {
            for (HistoryRecord historyRecord : historyRecordList) {
                this.mReConnectHelper.reconnectHistory(historyRecord);
            }
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public BluetoothDevice getActivityBluetoothDevice() {
        return this.mBluetoothBrEdr.getActivityBluetoothDevice();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public int getBleMtu(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothBle.getBleMtu(bluetoothDevice);
    }

    public BluetoothBle getBluetoothBle() {
        return this.mBluetoothBle;
    }

    public BluetoothBrEdr getBluetoothBrEdr() {
        return this.mBluetoothBrEdr;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public BluetoothOption getBluetoothOption() {
        return this.mBluetoothOption;
    }

    public BluetoothPair getBluetoothPair() {
        return this.mBluetoothPair;
    }

    public BluetoothSpp getBluetoothSpp() {
        return this.mBluetoothSpp;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public BluetoothGatt getConnectedBluetoothGatt() {
        return this.mBluetoothBle.getConnectedBluetoothGatt();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public BluetoothDevice getConnectedDevice() {
        if (this.mConnectedDevice == null && !this.mConnectedBtDevices.isEmpty()) {
            this.mConnectedDevice = this.mConnectedBtDevices.get(0);
        }
        if (this.mConnectedDevice == null) {
            this.mConnectedDevice = this.mBluetoothBle.getConnectedBleDevice();
        }
        if (this.mConnectedDevice == null) {
            this.mConnectedDevice = this.mBluetoothSpp.getConnectedSPPDevice();
        }
        return this.mConnectedDevice;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public List<BluetoothDevice> getConnectedDeviceList() {
        return new ArrayList(this.mConnectedBtDevices);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public List<BluetoothDevice> getConnectedSppList() {
        return this.mBluetoothSpp.getConnectedSppDevices();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public BluetoothDevice getConnectingBrEdrDevice() {
        return this.mBluetoothBrEdr.getConnectingBrEdrDevice();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public BluetoothDevice getConnectingDevice() {
        if (this.mConnectingDevice == null) {
            this.mConnectingDevice = this.mBluetoothBle.getConnectingBleDevice();
        }
        if (this.mConnectingDevice == null) {
            this.mConnectingDevice = this.mBluetoothSpp.getSppConnectingDevice();
        }
        return this.mConnectingDevice;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public BluetoothGatt getDeviceGatt(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothBle.getDeviceGatt(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public ArrayList<BluetoothDevice> getDiscoveredBluetoothDevices() {
        return this.mBluetoothDiscovery.getDiscoveredBluetoothDevices();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public HistoryRecord getHistoryRecord(String str) {
        return this.mHistoryRecordDbHelper.getHistoryRecordByMac(str);
    }

    public HistoryRecordDbHelper getHistoryRecordHelper() {
        return this.mHistoryRecordDbHelper;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public List<HistoryRecord> getHistoryRecordList() {
        return this.mHistoryRecordDbHelper.getHistoryRecordList();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public String getMappedDeviceAddress(String str) {
        return this.mHistoryRecordDbHelper.getMappedAddress(str);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public List<BluetoothDevice> getPairedDevices() {
        return BluetoothUtil.getPairedDevices(this.mContext);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public int getScanType() {
        return this.mBluetoothDiscovery.getScanType();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean isBrEdrConnecting() {
        return this.mBluetoothBrEdr.isBrEdrConnecting();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean isConnectedBLEDevice(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothBle.isConnectedBleDevice(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public int isConnectedByA2dp(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothBrEdr.isConnectedByA2dp(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public int isConnectedByHfp(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothBrEdr.isConnectedByHfp(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public int isConnectedByProfile(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothBrEdr.isConnectedByProfile(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean isConnectedDevice(BluetoothDevice bluetoothDevice) {
        boolean isConnectedBleDevice = this.mBluetoothBle.isConnectedBleDevice(bluetoothDevice);
        boolean isConnectedSppDevice = this.mBluetoothSpp.isConnectedSppDevice(bluetoothDevice);
        String str = TAG;
        JL_Log.d(str, "isConnectedDevice : isBleConnected = " + isConnectedBleDevice + ", isSppConnected = " + isConnectedSppDevice);
        return isConnectedBleDevice || isConnectedSppDevice;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean isConnectedSppDevice(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothSpp.isConnectedSppDevice(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean isConnecting() {
        return this.mConnectingDevice != null || this.mBluetoothBle.isBleConnecting() || this.mBluetoothSpp.isSppConnecting();
    }

    public boolean isMatchDevice(BluetoothDevice bluetoothDevice, BluetoothDevice bluetoothDevice2) {
        if (bluetoothDevice == null || bluetoothDevice2 == null) {
            return false;
        }
        boolean deviceEquals = BluetoothUtil.deviceEquals(bluetoothDevice, bluetoothDevice2);
        if (deviceEquals) {
            return deviceEquals;
        }
        return bluetoothDevice2.getAddress().equals(getMappedDeviceAddress(bluetoothDevice.getAddress()));
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean isPaired(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothPair.isPaired(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean isScanning() {
        return this.mBluetoothDiscovery.isScanning();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean pair(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothPair.pair(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean registerBluetoothCallback(BluetoothEventCallback bluetoothEventCallback) {
        return this.mEventCbManager.registerBluetoothCallback(bluetoothEventCallback);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public void removeHistoryRecord(String str, OnHistoryRecordCallback onHistoryRecordCallback) {
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            if (onHistoryRecordCallback != null) {
                onHistoryRecordCallback.onFailed(1, "address is error.");
                return;
            }
            return;
        }
        HistoryRecord historyRecord = getHistoryRecord(str);
        if (historyRecord != null) {
            startRemoveHistoryRecordTask(historyRecord, onHistoryRecordCallback);
        } else if (onHistoryRecordCallback != null) {
            onHistoryRecordCallback.onSuccess(null);
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean requestBleMtu(BluetoothDevice bluetoothDevice, int i) {
        return this.mBluetoothBle.requestBleMtu(bluetoothDevice, i);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean sendDataToDevice(BluetoothDevice bluetoothDevice, byte[] bArr) {
        if (bluetoothDevice == null || bArr == null || !isConnectedDevice(bluetoothDevice)) {
            return false;
        }
        if (isConnectedBLEDevice(bluetoothDevice)) {
            return writeDataToBLEDevice(bluetoothDevice, getBluetoothOption().getBleServiceUUID(), getBluetoothOption().getBleWriteUUID(), bArr);
        }
        if (isConnectedSppDevice(bluetoothDevice)) {
            return writeDataToSppDevice(bluetoothDevice, bArr);
        }
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean setActivityBluetoothDevice(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothBrEdr.setActivityBluetoothDevice(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public void setBluetoothOption(BluetoothOption bluetoothOption) {
        if (bluetoothOption == null) {
            bluetoothOption = BluetoothOption.createDefaultOption();
        }
        this.mBluetoothOption = bluetoothOption;
        this.mBluetoothBle.setBluetoothOption(bluetoothOption);
        this.mBluetoothSpp.setBluetoothOption(bluetoothOption);
        this.mBluetoothBrEdr.setBluetoothOption(bluetoothOption);
        this.mBluetoothPair.setBluetoothOption(bluetoothOption);
        this.mBluetoothDiscovery.setBluetoothOption(bluetoothOption);
        this.mHistoryRecordDbHelper.syncSystemDeviceList(bluetoothOption);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public void setConnectedDevice(BluetoothDevice bluetoothDevice) {
        if (!isConnectedDevice(bluetoothDevice) || BluetoothUtil.deviceEquals(this.mConnectedDevice, bluetoothDevice)) {
            return;
        }
        changeConnectedDevice(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean startBLEScan(long j) {
        return this.mBluetoothDiscovery.startBLEScan(j);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean startConnectByBreProfiles(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothBrEdr.connectBrEdrDevice(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean startDeviceScan(long j) {
        return this.mBluetoothDiscovery.startDeviceScan(j);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean stopBLEScan() {
        return this.mBluetoothDiscovery.stopBLEScan();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean stopDeviceScan() {
        return this.mBluetoothDiscovery.stopDeviceScan();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public void stopReconnect() {
        this.mReConnectHelper.stopReconnect();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean tryToPair(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothPair.tryToPair(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean tryToUnPair(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothPair.tryToUnPair(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean unPair(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothPair.unPair(bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean unregisterBluetoothCallback(BluetoothEventCallback bluetoothEventCallback) {
        return this.mEventCbManager.unregisterBluetoothCallback(bluetoothEventCallback);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean writeDataToBLEDevice(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr) {
        boolean isConnectedBLEDevice = isConnectedBLEDevice(bluetoothDevice);
        if (isConnectedBLEDevice) {
            this.mBluetoothBle.writeDataByBleAsync(bluetoothDevice, uuid, uuid2, bArr, new OnWriteDataCallback() { // from class: com.jieli.bluetooth_connect.impl.e
                @Override // com.jieli.bluetooth_connect.interfaces.listener.OnWriteDataCallback
                public final void onBleResult(BluetoothDevice bluetoothDevice2, UUID uuid3, UUID uuid4, boolean z, byte[] bArr2) {
                    BluetoothManager.lambda$writeDataToBLEDevice$1(bluetoothDevice2, uuid3, uuid4, z, bArr2);
                }
            });
        } else {
            String str = TAG;
            JL_Log.d(str, "-writeDataToBLEDevice- device[" + printDeviceInfo(bluetoothDevice) + "] is disconnected.");
        }
        return isConnectedBLEDevice;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean writeDataToSppDevice(BluetoothDevice bluetoothDevice, byte[] bArr) {
        return this.mBluetoothSpp.writeDataToSppDevice(bluetoothDevice, bArr);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothOperation
    public boolean startDeviceScan(int i, long j) {
        return this.mBluetoothDiscovery.startDeviceScan(i, j);
    }
}
