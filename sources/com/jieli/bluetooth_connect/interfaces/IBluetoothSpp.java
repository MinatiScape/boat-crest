package com.jieli.bluetooth_connect.interfaces;

import android.bluetooth.BluetoothDevice;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtSppListener;
import java.util.List;
/* loaded from: classes11.dex */
public interface IBluetoothSpp extends IBluetoothBase<OnBtSppListener> {
    boolean connectSPPDevice(BluetoothDevice bluetoothDevice);

    boolean disconnectSPPDevice(BluetoothDevice bluetoothDevice);

    BluetoothDevice getConnectedSPPDevice();

    List<BluetoothDevice> getConnectedSppDevices();

    BluetoothDevice getSppConnectingDevice();

    boolean isConnectedSppDevice(BluetoothDevice bluetoothDevice);

    boolean isSppConnecting();

    void setConnectedSppDevice(BluetoothDevice bluetoothDevice);

    void setConnectingSppDevice(BluetoothDevice bluetoothDevice);

    boolean writeDataToSppDevice(BluetoothDevice bluetoothDevice, byte[] bArr);
}
