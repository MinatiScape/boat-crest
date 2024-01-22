package com.coveiot.sdk.ble.utils;

import com.szabh.smable3.entity.BleNotificationSettings;
/* loaded from: classes9.dex */
public enum CommonPreference implements BleSavedPreference {
    USER_DATA_MODEL_JSON("userdataModelJson"),
    BLE_DEVICE_INFO("bleDeviceInfo"),
    BLE_DEVICE_ADDRESS("ble_address"),
    BLE_DEVICE("ble_device"),
    BLE_CONNECTION_TYPE("ble_connection_type"),
    USER_WEIGHT("user_weight"),
    USER_HEIGHT("user_height"),
    NOTIFICATION_CH_INFO("notification_channel_info"),
    INCOMING_CALL("incoming_call"),
    SMS(BleNotificationSettings.SMS),
    STRIDE_LENGTH("stride_length"),
    SLEEP_TRACK_INTERVAL("sleep_track_interval"),
    IS_PHONE_LOCATOR_ENABLED("is_phone_locator_enabled"),
    IS_BAND_UNPAIRED("is_band_unpaired"),
    SHOULD_UPDATE_SETTINGS_BAND("should_update_settings_band"),
    HR_INTERVAL_VALUE("hr_interval_value"),
    TEMPERATURE_INTERVAL_VALUE("temperature_interval_value"),
    SPO2_INTERVAL_VALUE("spo2_interval_value"),
    FIRMWARE_CAPABILITY("firmware_capability");
    

    /* renamed from: a  reason: collision with root package name */
    private String f7587a;

    CommonPreference(String str) {
        this.f7587a = str;
    }

    @Override // com.coveiot.sdk.ble.utils.BleSavedPreference
    public String getName() {
        return this.f7587a;
    }

    @Override // com.coveiot.sdk.ble.utils.BleSavedPreference
    public BlePreferenceType getPreferenceType() {
        return BlePreferenceType.CLOVE_COMMON_APP;
    }
}
