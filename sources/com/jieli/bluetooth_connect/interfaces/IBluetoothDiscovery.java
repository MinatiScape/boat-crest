package com.jieli.bluetooth_connect.interfaces;

import android.bluetooth.BluetoothDevice;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtDiscoveryListener;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public interface IBluetoothDiscovery extends IBluetoothBase<OnBtDiscoveryListener> {
    ArrayList<BluetoothDevice> getDiscoveredBluetoothDevices();

    int getScanType();

    boolean isBleScanning();

    boolean isDeviceScanning();

    boolean isScanning();

    boolean startBLEScan(long j);

    boolean startDeviceScan(int i, long j);

    boolean startDeviceScan(long j);

    boolean stopBLEScan();

    boolean stopDeviceScan();
}
