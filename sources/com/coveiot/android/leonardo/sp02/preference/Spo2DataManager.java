package com.coveiot.android.leonardo.sp02.preference;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.android.leonardo.model.SPO2LowDataModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.google.gson.Gson;
/* loaded from: classes5.dex */
public class Spo2DataManager {

    /* renamed from: a  reason: collision with root package name */
    public static SharedPreferences f5410a;
    public static SharedPreferences.Editor b;
    public static int c;
    public static Spo2DataManager d;

    public static Spo2DataManager getInstance(Context context) {
        if (d == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SPO2_DATA_PREF", c);
            f5410a = sharedPreferences;
            b = sharedPreferences.edit();
            d = new Spo2DataManager();
        }
        return d;
    }

    public int getConsecutiveLowCountLimit() {
        return f5410a.getInt("consecutive_low_count", Integer.parseInt(AppConstants.CONSECUTIVE_LOW_COUNT_LIMIT.getValue()));
    }

    public int getMinTimeOut() {
        return f5410a.getInt("spo2_threshold_timeout", Integer.parseInt(AppConstants.DEFAULT_SPO2_TMIN_IMEOUT_MANUAL.getValue()));
    }

    public int getReminderInterval() {
        return f5410a.getInt("spo2_reminder_interval", Integer.parseInt(AppConstants.DEFAULT_SPO2_REMINDER_INTERVAL.getValue()));
    }

    public SPO2LowDataModel getSpo2LowDataModel() {
        return (SPO2LowDataModel) new Gson().fromJson(f5410a.getString("spo2_low_data", null), (Class<Object>) SPO2LowDataModel.class);
    }

    public void setConsecutiveLowCountLimit(int i) {
        b.putInt("consecutive_low_count", i);
        b.commit();
    }

    public void setMinTimeOut(int i) {
        b.putInt("spo2_threshold_timeout", i);
        b.commit();
    }

    public void setReminderInterval(int i) {
        b.putInt("spo2_reminder_interval", i);
        b.commit();
    }

    public void setSpo2LowDataModel(SPO2LowDataModel sPO2LowDataModel) {
        b.putString("spo2_low_data", sPO2LowDataModel != null ? new Gson().toJson(sPO2LowDataModel) : null).commit();
    }
}
