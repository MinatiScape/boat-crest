package com.coveiot.android.spo2sdk.utils;
/* loaded from: classes7.dex */
public enum Spo2PrefConstants {
    BLE_DEVICE_INFO("bleDeviceInfo"),
    BLE_DEVICE_ADDRESS("ble_address"),
    BLE_DEVICE("ble_device"),
    BLE_CONNECTION_TYPE("ble_connection_type"),
    IS_BAND_UNPAIRED("is_band_unpaired"),
    SPO2_MODULE_NAME("bleabstract");
    
    private String name;

    Spo2PrefConstants(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
