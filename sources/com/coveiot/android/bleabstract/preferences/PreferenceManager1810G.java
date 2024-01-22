package com.coveiot.android.bleabstract.preferences;

import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes2.dex */
public class PreferenceManager1810G {

    /* renamed from: a  reason: collision with root package name */
    public static PreferenceManager1810G f3461a;
    public static SharedPreferences b;
    public static SharedPreferences.Editor c;

    public static PreferenceManager1810G getInstance(Context context) {
        if (f3461a == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("PREFERENCE_MANAGER_1810G", 0);
            b = sharedPreferences;
            c = sharedPreferences.edit();
            f3461a = new PreferenceManager1810G();
        }
        return f3461a;
    }

    public String getConnectedDeviceMacAddress() {
        return b.getString("ble_address", "");
    }

    public String getConnectionType() {
        return b.getString("ble_connection_type", "");
    }

    public Boolean isBandUnpaired() {
        return Boolean.valueOf(b.getBoolean("is_band_unpaired", false));
    }

    public void saveBandUnpaired(boolean z) {
        c.putBoolean("is_band_unpaired", z).commit();
    }

    public void saveConnectedDeviceMAcAddress(String str) {
        c.putString("ble_address", str).commit();
    }

    public void saveConnectionType(String str) {
        c.putString("ble_connection_type", str).commit();
    }
}
