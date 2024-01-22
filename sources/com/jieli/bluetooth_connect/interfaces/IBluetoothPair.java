package com.jieli.bluetooth_connect.interfaces;

import android.bluetooth.BluetoothDevice;
import androidx.annotation.IntRange;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener;
/* loaded from: classes11.dex */
public interface IBluetoothPair extends IBluetoothBase<OnBtDevicePairListener> {
    boolean isPaired(BluetoothDevice bluetoothDevice);

    boolean isPairing(BluetoothDevice bluetoothDevice);

    boolean pair(BluetoothDevice bluetoothDevice);

    boolean pair(BluetoothDevice bluetoothDevice, @IntRange(from = 0, to = 2) int i);

    boolean tryToPair(BluetoothDevice bluetoothDevice);

    boolean tryToPair(BluetoothDevice bluetoothDevice, @IntRange(from = 0, to = 2) int i);

    boolean tryToUnPair(BluetoothDevice bluetoothDevice);

    boolean unPair(BluetoothDevice bluetoothDevice);
}
