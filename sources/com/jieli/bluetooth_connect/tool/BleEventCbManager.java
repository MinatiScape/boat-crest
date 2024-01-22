package com.jieli.bluetooth_connect.tool;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
/* loaded from: classes11.dex */
public class BleEventCbManager extends OnBtBleListener {
    private final List<OnBtBleListener> mOnBtBleListeners = new ArrayList();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes11.dex */
    public static abstract class BtEventCbImpl {
        private BtEventCbImpl() {
        }

        public abstract void notifyBleEvent(OnBtBleListener onBtBleListener);
    }

    /* loaded from: classes11.dex */
    public class BtEventCbRunnable implements Runnable {
        private final BtEventCbImpl mImpl;

        public BtEventCbRunnable(BtEventCbImpl btEventCbImpl) {
            this.mImpl = btEventCbImpl;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mImpl == null || BleEventCbManager.this.mOnBtBleListeners.isEmpty()) {
                return;
            }
            Iterator it = new ArrayList(BleEventCbManager.this.mOnBtBleListeners).iterator();
            while (it.hasNext()) {
                this.mImpl.notifyBleEvent((OnBtBleListener) it.next());
            }
        }
    }

    private void notifyBleEvent(BtEventCbImpl btEventCbImpl) {
        BtEventCbRunnable btEventCbRunnable = new BtEventCbRunnable(btEventCbImpl);
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.mHandler.post(btEventCbRunnable);
        } else {
            btEventCbRunnable.run();
        }
    }

    public void addListener(OnBtBleListener onBtBleListener) {
        if (onBtBleListener != null) {
            this.mOnBtBleListeners.add(onBtBleListener);
        }
    }

    public void destroy() {
        this.mOnBtBleListeners.clear();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
    public void onBleBond(final BluetoothDevice bluetoothDevice, final int i) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.15
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onBleBond(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
    public void onBleConnection(final BluetoothDevice bluetoothDevice, final int i) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onBleConnection(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
    public void onBleDataNotify(final BluetoothDevice bluetoothDevice, final UUID uuid, final UUID uuid2, final byte[] bArr) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.11
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onBleDataNotify(bluetoothDevice, uuid, uuid2, bArr);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
    public void onBleMtuChanged(final BluetoothDevice bluetoothDevice, final int i, final int i2) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.14
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onBleMtuChanged(bluetoothDevice, i, i2);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
    public void onBleNotificationStatus(final BluetoothDevice bluetoothDevice, final UUID uuid, final UUID uuid2, final boolean z) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.12
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onBleNotificationStatus(bluetoothDevice, uuid, uuid2, z);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
    public void onBleWriteStatus(final BluetoothDevice bluetoothDevice, final UUID uuid, final UUID uuid2, final byte[] bArr, final int i) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.13
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onBleWriteStatus(bluetoothDevice, uuid, uuid2, bArr, i);
            }
        });
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicChanged(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            }
        });
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicRead(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final int i) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        });
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicWrite(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final int i) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        });
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onConnectionStateChange(final BluetoothGatt bluetoothGatt, final int i, final int i2) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onConnectionStateChange(bluetoothGatt, i, i2);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
    public void onConnectionUpdatedCallback(final BluetoothGatt bluetoothGatt, final int i, final int i2, final int i3, final int i4) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onConnectionUpdatedCallback(bluetoothGatt, i, i2, i3, i4);
            }
        });
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onDescriptorRead(final BluetoothGatt bluetoothGatt, final BluetoothGattDescriptor bluetoothGattDescriptor, final int i) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.18
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            }
        });
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onDescriptorWrite(final BluetoothGatt bluetoothGatt, final BluetoothGattDescriptor bluetoothGattDescriptor, final int i) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            }
        });
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onMtuChanged(final BluetoothGatt bluetoothGatt, final int i, final int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.8
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
                public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                    onBtBleListener.onMtuChanged(bluetoothGatt, i, i2);
                }
            });
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    @RequiresApi(api = 26)
    public void onPhyRead(final BluetoothGatt bluetoothGatt, final int i, final int i2, final int i3) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.19
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onPhyRead(bluetoothGatt, i, i2, i3);
            }
        });
    }

    @Override // android.bluetooth.BluetoothGattCallback
    @RequiresApi(api = 26)
    public void onPhyUpdate(final BluetoothGatt bluetoothGatt, final int i, final int i2, final int i3) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.20
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onPhyUpdate(bluetoothGatt, i, i2, i3);
            }
        });
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onReadRemoteRssi(final BluetoothGatt bluetoothGatt, final int i, final int i2) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.17
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onReadRemoteRssi(bluetoothGatt, i, i2);
            }
        });
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onReliableWriteCompleted(final BluetoothGatt bluetoothGatt, final int i) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onReliableWriteCompleted(bluetoothGatt, i);
            }
        });
    }

    @Override // android.bluetooth.BluetoothGattCallback
    @RequiresApi(api = 31)
    public void onServiceChanged(@NonNull final BluetoothGatt bluetoothGatt) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.21
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onServiceChanged(bluetoothGatt);
            }
        });
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onServicesDiscovered(final BluetoothGatt bluetoothGatt, final int i) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onServicesDiscovered(bluetoothGatt, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtBleListener
    public void onSwitchBleDevice(final BluetoothDevice bluetoothDevice) {
        notifyBleEvent(new BtEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BleEventCbManager.16
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BleEventCbManager.BtEventCbImpl
            public void notifyBleEvent(OnBtBleListener onBtBleListener) {
                onBtBleListener.onSwitchBleDevice(bluetoothDevice);
            }
        });
    }

    public void removeListener(OnBtBleListener onBtBleListener) {
        if (onBtBleListener != null) {
            this.mOnBtBleListeners.remove(onBtBleListener);
        }
    }
}
