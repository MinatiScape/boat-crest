package com.jieli.bluetooth_connect.tool;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattService;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelUuid;
import com.jieli.bluetooth_connect.bean.ErrorInfo;
import com.jieli.bluetooth_connect.bean.ble.BleScanMessage;
import com.jieli.bluetooth_connect.bean.history.HistoryRecord;
import com.jieli.bluetooth_connect.interfaces.callback.BluetoothEventCallback;
import com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
/* loaded from: classes11.dex */
public class BluetoothEventCbManager implements IBluetoothEventCallback {
    private final List<BluetoothEventCallback> mBluetoothEventCbs = new ArrayList();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes11.dex */
    public static abstract class BtEventCbImpl {
        private BtEventCbImpl() {
        }

        public abstract void onCallback(BluetoothEventCallback bluetoothEventCallback);
    }

    /* loaded from: classes11.dex */
    public class OnBtEventCbRunnable implements Runnable {
        private final BtEventCbImpl mEventCb;

        public OnBtEventCbRunnable(BtEventCbImpl btEventCbImpl) {
            this.mEventCb = btEventCbImpl;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BluetoothEventCbManager.this.mBluetoothEventCbs.isEmpty() || this.mEventCb == null) {
                return;
            }
            Iterator it = new ArrayList(BluetoothEventCbManager.this.mBluetoothEventCbs).iterator();
            while (it.hasNext()) {
                BluetoothEventCallback bluetoothEventCallback = (BluetoothEventCallback) it.next();
                if (bluetoothEventCallback != null) {
                    this.mEventCb.onCallback(bluetoothEventCallback);
                }
            }
        }
    }

    private void callbackBtEvent(BtEventCbImpl btEventCbImpl) {
        OnBtEventCbRunnable onBtEventCbRunnable = new OnBtEventCbRunnable(btEventCbImpl);
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.mHandler.post(onBtEventCbRunnable);
        } else {
            onBtEventCbRunnable.run();
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onA2dpStatus(final BluetoothDevice bluetoothDevice, final int i) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onA2dpStatus(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onAdapterStatus(final boolean z, final boolean z2) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onAdapterStatus(z, z2);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBleConnection(final BluetoothDevice bluetoothDevice, final int i) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.12
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onBleConnection(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBleDataBlockChanged(final BluetoothDevice bluetoothDevice, final int i, final int i2) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.13
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onBleDataBlockChanged(bluetoothDevice, i, i2);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBleDataNotification(final BluetoothDevice bluetoothDevice, final UUID uuid, final UUID uuid2, final byte[] bArr) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.15
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onBleDataNotification(bluetoothDevice, uuid, uuid2, bArr);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBleNotificationStatus(final BluetoothDevice bluetoothDevice, final UUID uuid, final UUID uuid2, final boolean z) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.14
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onBleNotificationStatus(bluetoothDevice, uuid, uuid2, z);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBleServiceDiscovery(final BluetoothDevice bluetoothDevice, final int i, final List<BluetoothGattService> list) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.11
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onBleServiceDiscovery(bluetoothDevice, i, list);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBleWriteStatus(final BluetoothDevice bluetoothDevice, final UUID uuid, final UUID uuid2, final byte[] bArr, final int i) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.16
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onBleWriteStatus(bluetoothDevice, uuid, uuid2, bArr, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBondStatus(final BluetoothDevice bluetoothDevice, final int i) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onBondStatus(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBtDeviceConnectStatus(final BluetoothDevice bluetoothDevice, final int i) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.18
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onBtDeviceConnectStatus(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onConnection(final BluetoothDevice bluetoothDevice, final int i) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.19
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onConnection(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onConnectionUpdated(final BluetoothGatt bluetoothGatt, final int i, final int i2, final int i3, final int i4) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.17
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onConnectionUpdated(bluetoothGatt, i, i2, i3, i4);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onDeviceUuidsDiscovery(final BluetoothDevice bluetoothDevice, final ParcelUuid[] parcelUuidArr) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onDeviceUuidsDiscovery(bluetoothDevice, parcelUuidArr);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onDiscovery(final BluetoothDevice bluetoothDevice, final BleScanMessage bleScanMessage) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onDiscovery(bluetoothDevice, bleScanMessage);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onDiscoveryStatus(final boolean z, final boolean z2) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onDiscoveryStatus(z, z2);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onError(final ErrorInfo errorInfo) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.22
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onError(errorInfo);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onHfpStatus(final BluetoothDevice bluetoothDevice, final int i) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onHfpStatus(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onHistoryRecordChange(final int i, final HistoryRecord historyRecord) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.21
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onHistoryRecordChange(i, historyRecord);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onShowDialog(final BluetoothDevice bluetoothDevice, final BleScanMessage bleScanMessage) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onShowDialog(bluetoothDevice, bleScanMessage);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onSppDataNotification(final BluetoothDevice bluetoothDevice, final UUID uuid, final byte[] bArr) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onSppDataNotification(bluetoothDevice, uuid, bArr);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onSppStatus(final BluetoothDevice bluetoothDevice, final int i) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onSppStatus(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onSwitchConnectedDevice(final BluetoothDevice bluetoothDevice) {
        callbackBtEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.20
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BluetoothEventCbManager.BtEventCbImpl
            public void onCallback(BluetoothEventCallback bluetoothEventCallback) {
                bluetoothEventCallback.onSwitchConnectedDevice(bluetoothDevice);
            }
        });
    }

    public boolean registerBluetoothCallback(BluetoothEventCallback bluetoothEventCallback) {
        if (bluetoothEventCallback == null || this.mBluetoothEventCbs.contains(bluetoothEventCallback)) {
            return false;
        }
        return this.mBluetoothEventCbs.add(bluetoothEventCallback);
    }

    public void release() {
        this.mBluetoothEventCbs.clear();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public boolean unregisterBluetoothCallback(BluetoothEventCallback bluetoothEventCallback) {
        if (bluetoothEventCallback == null) {
            return false;
        }
        return this.mBluetoothEventCbs.remove(bluetoothEventCallback);
    }
}
