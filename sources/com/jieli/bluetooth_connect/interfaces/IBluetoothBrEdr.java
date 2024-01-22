package com.jieli.bluetooth_connect.interfaces;

import android.bluetooth.BluetoothDevice;
import com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener;
/* loaded from: classes11.dex */
public interface IBluetoothBrEdr extends IBluetoothBase<OnBrEdrListener> {
    boolean connectBrEdrDevice(BluetoothDevice bluetoothDevice);

    boolean connectByA2dp(BluetoothDevice bluetoothDevice);

    boolean connectByHfp(BluetoothDevice bluetoothDevice);

    boolean connectByProfiles(BluetoothDevice bluetoothDevice);

    boolean disconnectByProfiles(BluetoothDevice bluetoothDevice);

    boolean disconnectFromA2dp(BluetoothDevice bluetoothDevice);

    boolean disconnectFromHfp(BluetoothDevice bluetoothDevice);

    BluetoothDevice getActivityBluetoothDevice();

    BluetoothDevice getConnectingBrEdrDevice();

    boolean isBrEdrConnecting();

    int isConnectedByA2dp(BluetoothDevice bluetoothDevice);

    int isConnectedByHfp(BluetoothDevice bluetoothDevice);

    int isConnectedByProfile(BluetoothDevice bluetoothDevice);

    boolean setActivityBluetoothDevice(BluetoothDevice bluetoothDevice);
}
