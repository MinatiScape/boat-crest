package com.jieli.bluetooth_connect.interfaces.callback;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattService;
import android.os.ParcelUuid;
import com.jieli.bluetooth_connect.bean.ErrorInfo;
import com.jieli.bluetooth_connect.bean.ble.BleScanMessage;
import com.jieli.bluetooth_connect.bean.history.HistoryRecord;
import java.util.List;
import java.util.UUID;
/* loaded from: classes11.dex */
public abstract class BluetoothEventCallback implements IBluetoothEventCallback {
    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onA2dpStatus(BluetoothDevice bluetoothDevice, int i) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onAdapterStatus(boolean z, boolean z2) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBleConnection(BluetoothDevice bluetoothDevice, int i) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBleDataBlockChanged(BluetoothDevice bluetoothDevice, int i, int i2) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBleDataNotification(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBleNotificationStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, boolean z) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBleServiceDiscovery(BluetoothDevice bluetoothDevice, int i, List<BluetoothGattService> list) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBleWriteStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr, int i) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBondStatus(BluetoothDevice bluetoothDevice, int i) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onBtDeviceConnectStatus(BluetoothDevice bluetoothDevice, int i) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onConnection(BluetoothDevice bluetoothDevice, int i) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onConnectionUpdated(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onDeviceUuidsDiscovery(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onDiscovery(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onDiscoveryStatus(boolean z, boolean z2) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onError(ErrorInfo errorInfo) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onHfpStatus(BluetoothDevice bluetoothDevice, int i) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onHistoryRecordChange(int i, HistoryRecord historyRecord) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onShowDialog(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onSppDataNotification(BluetoothDevice bluetoothDevice, UUID uuid, byte[] bArr) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onSppStatus(BluetoothDevice bluetoothDevice, int i) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
    public void onSwitchConnectedDevice(BluetoothDevice bluetoothDevice) {
    }
}
