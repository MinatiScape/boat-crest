package com.apex.bluetooth.listener;
/* loaded from: classes.dex */
public interface EABleConnectListener {
    void connectError(int i);

    void connectTimeOut();

    void deviceConnected();

    void deviceDisconnect();

    void deviceNotFind();

    void notOpenLocation();

    void unopenedBluetooth();

    void unsupportedBLE();
}
