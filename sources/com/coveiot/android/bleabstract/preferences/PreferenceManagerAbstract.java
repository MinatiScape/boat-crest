package com.coveiot.android.bleabstract.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.android.bleabstract.models.AppCapabilityData;
import com.coveiot.android.bleabstract.models.FirmwareCapabilityData;
import com.coveiot.android.bleabstract.models.ManualHrReading;
import com.coveiot.android.bleabstract.models.UserProfileDetails;
import com.google.gson.Gson;
/* loaded from: classes2.dex */
public class PreferenceManagerAbstract {
    public static final String KEY_PROFILE_OBJECT = "profile_object";
    public static final String KEY_VERSION_NUMBER = "key_version_number";

    /* renamed from: a  reason: collision with root package name */
    public static PreferenceManagerAbstract f3466a;
    public static SharedPreferences b;
    public static SharedPreferences.Editor c;

    public static PreferenceManagerAbstract getInstance(Context context) {
        if (f3466a == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("PREFERENCE_MANAGER_ABSTRACT", 0);
            b = sharedPreferences;
            c = sharedPreferences.edit();
            f3466a = new PreferenceManagerAbstract();
        }
        return f3466a;
    }

    public AppCapabilityData getAppCapability(String str) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = b;
        return (AppCapabilityData) gson.fromJson(sharedPreferences.getString("app_capability_obj" + str, gson.toJson(new AppCapabilityData())), (Class<Object>) AppCapabilityData.class);
    }

    public String getConnectedDeviceBT3MacAddress() {
        return b.getString("bt3_mac_address", "");
    }

    public String getConnectedDeviceMacAddress() {
        return b.getString("ble_address", "");
    }

    public String getConnectionType() {
        return b.getString("ble_connection_type", "");
    }

    public String getDeviceVersionNumber() {
        return b.getString(KEY_VERSION_NUMBER, null);
    }

    public FirmwareCapabilityData getFirmwareCapability(String str) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = b;
        return (FirmwareCapabilityData) gson.fromJson(sharedPreferences.getString("firmware_capability_obj" + str, null), (Class<Object>) FirmwareCapabilityData.class);
    }

    public ManualHrReading getLastManualHrReading() {
        return (ManualHrReading) new Gson().fromJson(b.getString("last_manual_hr_reading", null), (Class<Object>) ManualHrReading.class);
    }

    public boolean getShouldDoScanConnect() {
        return b.getBoolean("should_do_scan_connect", false);
    }

    public UserProfileDetails getUserDetails() {
        return (UserProfileDetails) new Gson().fromJson(b.getString("profile_object", null), (Class<Object>) UserProfileDetails.class);
    }

    public boolean isExistingUserFirstTime() {
        return b.getBoolean("KEY_EXISTING_USER_FIRST_TIME", false);
    }

    public void saveAppCapability(String str, AppCapabilityData appCapabilityData) {
        String json = new Gson().toJson(appCapabilityData);
        SharedPreferences.Editor editor = c;
        editor.putString("app_capability_obj" + str, json);
        c.commit();
    }

    public void saveConnectedDeviceBT3MacAddress(String str) {
        c.putString("bt3_mac_address", str).commit();
    }

    public void saveConnectedDeviceMAcAddress(String str) {
        c.putString("ble_address", str).commit();
    }

    public void saveConnectionType(String str) {
        c.putString("ble_connection_type", str).commit();
    }

    public void saveDeviceVersionNumber(String str) {
        c.putString(KEY_VERSION_NUMBER, str).commit();
    }

    public void saveFirmwareCapability(String str, FirmwareCapabilityData firmwareCapabilityData) {
        String json = new Gson().toJson(firmwareCapabilityData);
        SharedPreferences.Editor editor = c;
        editor.putString("firmware_capability_obj" + str, json);
        c.commit();
    }

    public void saveLastManualHrReading(ManualHrReading manualHrReading) {
        c.putString("last_manual_hr_reading", new Gson().toJson(manualHrReading));
        c.commit();
    }

    public void saveProfileData(UserProfileDetails userProfileDetails) {
        c.putString("profile_object", new Gson().toJson(userProfileDetails));
        c.commit();
    }

    public void saveShouldDoScanConnect(boolean z) {
        c.putBoolean("should_do_scan_connect", z).commit();
    }

    public void setExistingUserFirstTime(boolean z) {
        c.putBoolean("KEY_EXISTING_USER_FIRST_TIME", z).commit();
    }
}
