package com.jieli.bluetooth_connect.interfaces;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import com.jieli.bluetooth_connect.bean.BluetoothOption;
import com.jieli.bluetooth_connect.bean.history.HistoryRecord;
import com.jieli.bluetooth_connect.interfaces.callback.BluetoothEventCallback;
import com.jieli.bluetooth_connect.interfaces.callback.OnHistoryRecordCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/* loaded from: classes11.dex */
public interface IBluetoothOperation {
    void clearHistoryRecords();

    boolean connectBLEDevice(BluetoothDevice bluetoothDevice);

    boolean connectBtDevice(BluetoothDevice bluetoothDevice, int i);

    boolean connectBtDeviceWithoutRecord(BluetoothDevice bluetoothDevice, int i);

    boolean connectByProfiles(BluetoothDevice bluetoothDevice);

    void connectHistoryRecord(HistoryRecord historyRecord, OnHistoryRecordCallback onHistoryRecordCallback);

    boolean connectSPPDevice(BluetoothDevice bluetoothDevice);

    void destroy();

    boolean disconnectBLEDevice(BluetoothDevice bluetoothDevice);

    void disconnectBtDevice(BluetoothDevice bluetoothDevice);

    boolean disconnectByProfiles(BluetoothDevice bluetoothDevice);

    boolean disconnectSPPDevice(BluetoothDevice bluetoothDevice);

    void fastConnect();

    BluetoothDevice getActivityBluetoothDevice();

    int getBleMtu(BluetoothDevice bluetoothDevice);

    BluetoothOption getBluetoothOption();

    BluetoothGatt getConnectedBluetoothGatt();

    BluetoothDevice getConnectedDevice();

    List<BluetoothDevice> getConnectedDeviceList();

    List<BluetoothDevice> getConnectedSppList();

    BluetoothDevice getConnectingBrEdrDevice();

    BluetoothDevice getConnectingDevice();

    BluetoothGatt getDeviceGatt(BluetoothDevice bluetoothDevice);

    ArrayList<BluetoothDevice> getDiscoveredBluetoothDevices();

    HistoryRecord getHistoryRecord(String str);

    List<HistoryRecord> getHistoryRecordList();

    String getMappedDeviceAddress(String str);

    List<BluetoothDevice> getPairedDevices();

    int getScanType();

    boolean isBrEdrConnecting();

    boolean isConnectedBLEDevice(BluetoothDevice bluetoothDevice);

    int isConnectedByA2dp(BluetoothDevice bluetoothDevice);

    int isConnectedByHfp(BluetoothDevice bluetoothDevice);

    int isConnectedByProfile(BluetoothDevice bluetoothDevice);

    boolean isConnectedDevice(BluetoothDevice bluetoothDevice);

    boolean isConnectedSppDevice(BluetoothDevice bluetoothDevice);

    boolean isConnecting();

    boolean isPaired(BluetoothDevice bluetoothDevice);

    boolean isScanning();

    boolean pair(BluetoothDevice bluetoothDevice);

    boolean registerBluetoothCallback(BluetoothEventCallback bluetoothEventCallback);

    void removeHistoryRecord(String str, OnHistoryRecordCallback onHistoryRecordCallback);

    boolean requestBleMtu(BluetoothDevice bluetoothDevice, int i);

    boolean sendDataToDevice(BluetoothDevice bluetoothDevice, byte[] bArr);

    boolean setActivityBluetoothDevice(BluetoothDevice bluetoothDevice);

    void setBluetoothOption(BluetoothOption bluetoothOption);

    void setConnectedDevice(BluetoothDevice bluetoothDevice);

    boolean startBLEScan(long j);

    boolean startConnectByBreProfiles(BluetoothDevice bluetoothDevice);

    boolean startDeviceScan(int i, long j);

    boolean startDeviceScan(long j);

    boolean stopBLEScan();

    boolean stopDeviceScan();

    void stopReconnect();

    boolean tryToPair(BluetoothDevice bluetoothDevice);

    boolean tryToUnPair(BluetoothDevice bluetoothDevice);

    boolean unPair(BluetoothDevice bluetoothDevice);

    boolean unregisterBluetoothCallback(BluetoothEventCallback bluetoothEventCallback);

    boolean writeDataToBLEDevice(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr);

    boolean writeDataToSppDevice(BluetoothDevice bluetoothDevice, byte[] bArr);
}
