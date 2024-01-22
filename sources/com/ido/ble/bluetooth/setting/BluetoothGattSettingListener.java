package com.ido.ble.bluetooth.setting;

import android.bluetooth.BluetoothGattCharacteristic;
/* loaded from: classes11.dex */
public class BluetoothGattSettingListener {
    private static IListener bluetoothGattSettingListener;

    /* loaded from: classes11.dex */
    public interface IListener {
        BluetoothGattCharacteristic addParaToCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic);
    }

    public static IListener getBluetoothGattSettingListener() {
        return bluetoothGattSettingListener;
    }

    public static void setBluetoothGattSettingListener(IListener iListener) {
        bluetoothGattSettingListener = iListener;
    }
}
