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
public interface IBluetoothEventCallback {
    void onA2dpStatus(BluetoothDevice bluetoothDevice, int i);

    void onAdapterStatus(boolean z, boolean z2);

    void onBleConnection(BluetoothDevice bluetoothDevice, int i);

    void onBleDataBlockChanged(BluetoothDevice bluetoothDevice, int i, int i2);

    void onBleDataNotification(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr);

    void onBleNotificationStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, boolean z);

    void onBleServiceDiscovery(BluetoothDevice bluetoothDevice, int i, List<BluetoothGattService> list);

    void onBleWriteStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr, int i);

    void onBondStatus(BluetoothDevice bluetoothDevice, int i);

    void onBtDeviceConnectStatus(BluetoothDevice bluetoothDevice, int i);

    void onConnection(BluetoothDevice bluetoothDevice, int i);

    void onConnectionUpdated(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4);

    void onDeviceUuidsDiscovery(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr);

    void onDiscovery(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage);

    void onDiscoveryStatus(boolean z, boolean z2);

    void onError(ErrorInfo errorInfo);

    void onHfpStatus(BluetoothDevice bluetoothDevice, int i);

    void onHistoryRecordChange(int i, HistoryRecord historyRecord);

    void onShowDialog(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage);

    void onSppDataNotification(BluetoothDevice bluetoothDevice, UUID uuid, byte[] bArr);

    void onSppStatus(BluetoothDevice bluetoothDevice, int i);

    void onSwitchConnectedDevice(BluetoothDevice bluetoothDevice);
}
