package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public enum BleWatchDiagnosticFeatureType {
    RESERVED(0),
    CHARGING_TEST(1),
    DISPLAY_TEST(2),
    VIBRATION_TEST(3),
    BUTTON_TEST(4),
    TOUCHSCREEN_TEST(5),
    SENSOR_TEST(6);
    
    private int id;

    BleWatchDiagnosticFeatureType(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }
}
