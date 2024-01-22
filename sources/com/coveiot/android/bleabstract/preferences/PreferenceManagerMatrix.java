package com.coveiot.android.bleabstract.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.htsmart.wristband2.bean.data.TodayTotalData;
/* loaded from: classes2.dex */
public class PreferenceManagerMatrix {
    public static final String AUTO_HR_INTERVAL_PREFERENCE = "auto_hr_interval_preference";

    /* renamed from: a  reason: collision with root package name */
    public static PreferenceManagerMatrix f3468a;
    public static SharedPreferences b;
    public static SharedPreferences.Editor c;

    public static PreferenceManagerMatrix getInstance(Context context) {
        if (f3468a == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("PREFERENCE_MANAGER_MATRIX", 0);
            b = sharedPreferences;
            c = sharedPreferences.edit();
            f3468a = new PreferenceManagerMatrix();
        }
        return f3468a;
    }

    public String getConnectedDeviceMacAddress() {
        return b.getString("ble_address", "");
    }

    public String getConnectionType() {
        return b.getString("ble_connection_type", "");
    }

    public TodayTotalData getTodayTotalData() {
        String string = b.getString("TodayTotalData", null);
        Gson gson = new Gson();
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return (TodayTotalData) gson.fromJson(string, (Class<Object>) TodayTotalData.class);
    }

    public Boolean isBandUnpaired() {
        return Boolean.valueOf(b.getBoolean("is_band_unpaired", false));
    }

    public Boolean isBindOrLogin() {
        return Boolean.valueOf(b.getBoolean("is_bind_or_login", false));
    }

    public void saveBandUnpaired(boolean z) {
        c.putBoolean("is_band_unpaired", z).commit();
    }

    public void saveBindOrLogin(boolean z) {
        c.putBoolean("is_bind_or_login", z).commit();
    }

    public void saveConnectedDeviceMAcAddress(String str) {
        c.putString("ble_address", str).commit();
    }

    public void saveConnectionType(String str) {
        c.putString("ble_connection_type", str).commit();
    }

    public void setTodayTotalData(TodayTotalData todayTotalData) {
        c.putString("TodayTotalData", todayTotalData != null ? new Gson().toJson(todayTotalData) : null).apply();
    }
}
