package com.coveiot.android.bleabstract.preferences;

import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes2.dex */
public class PreferenceManager2301 {

    /* renamed from: a  reason: collision with root package name */
    public static PreferenceManager2301 f3465a;
    public static SharedPreferences b;
    public static SharedPreferences.Editor c;

    public static PreferenceManager2301 getInstance(Context context) {
        if (f3465a == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("PreferenceManager2301", 0);
            b = sharedPreferences;
            c = sharedPreferences.edit();
            f3465a = new PreferenceManager2301();
        }
        return f3465a;
    }

    public String getAgpsFileLastUpdatedDate() {
        return b.getString("agps_last_updated_date", null);
    }

    public String getConnectedDeviceMacAddress() {
        return b.getString("ble_address", "");
    }

    public String getConnectionType() {
        return b.getString("ble_connection_type", "");
    }

    public Boolean isAgpsFileUpdated(String str) {
        return Boolean.valueOf(b.getBoolean(str, false));
    }

    public Boolean isBandUnpaired() {
        return Boolean.valueOf(b.getBoolean("is_band_unpaired", false));
    }

    public void saveAgpsFileLastUpdatedDate(String str) {
        c.putString("agps_last_updated_date", str).commit();
    }

    public void saveAgpsFileUpdated(String str, boolean z) {
        c.putBoolean(str, z).commit();
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
