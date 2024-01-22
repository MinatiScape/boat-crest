package com.jieli.bluetooth_connect.interfaces;

import com.jieli.bluetooth_connect.bean.BluetoothOption;
/* loaded from: classes11.dex */
public interface IBluetoothBase<T> {
    void addListener(T t);

    void destroy();

    void removeListener(T t);

    void setBluetoothOption(BluetoothOption bluetoothOption);
}
