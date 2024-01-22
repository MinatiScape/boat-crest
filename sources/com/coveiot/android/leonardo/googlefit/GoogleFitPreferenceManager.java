package com.coveiot.android.leonardo.googlefit;

import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes2.dex */
public class GoogleFitPreferenceManager {
    public static final String KEY_LAST_HEART_RATE_SYNC_TIME_TO_FIT = "last_heart_rate_sync_time_to_fit";
    public static final String KEY_LAST_SLEEP_SYNC_TIME_TO_FIT = "last_sleep_sync_time_to_fit";
    public static final String KEY_LAST_SPO2_SYNC_TIME_TO_FIT = "last_spo2_sync_time_to_fit";
    public static final String KEY_LAST_STEPS_SYNC_TIME_TO_FIT = "last_steps_sync_time_to_fit";
    public static final String KEY_LAST_TEMPERATURE_SYNC_TIME_TO_FIT = "last_temperature_sync_time_to_fit";

    /* renamed from: a  reason: collision with root package name */
    public static SharedPreferences f4832a;
    public static SharedPreferences.Editor b;
    public static int c;
    public static GoogleFitPreferenceManager d;

    public static GoogleFitPreferenceManager getInstance(Context context) {
        if (d == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("GOOGLE_FIT_PREF_MANAGER", c);
            f4832a = sharedPreferences;
            b = sharedPreferences.edit();
            d = new GoogleFitPreferenceManager();
        }
        return d;
    }

    public void clearPreferences(Context context) {
        for (String str : f4832a.getAll().keySet()) {
            remove(context, str);
        }
    }

    public Long getLastHeartRateSyncTimeToFit() {
        return Long.valueOf(f4832a.getLong(KEY_LAST_HEART_RATE_SYNC_TIME_TO_FIT, -1L));
    }

    public Long getLastSPO2SyncTimeToFit() {
        return Long.valueOf(f4832a.getLong(KEY_LAST_SPO2_SYNC_TIME_TO_FIT, -1L));
    }

    public Long getLastSleepSyncTimeToFit() {
        return Long.valueOf(f4832a.getLong(KEY_LAST_SLEEP_SYNC_TIME_TO_FIT, -1L));
    }

    public Long getLastStepsSyncTimeToFit() {
        return Long.valueOf(f4832a.getLong(KEY_LAST_STEPS_SYNC_TIME_TO_FIT, -1L));
    }

    public Long getLastTemperatureSyncTimeToFit() {
        return Long.valueOf(f4832a.getLong(KEY_LAST_TEMPERATURE_SYNC_TIME_TO_FIT, -1L));
    }

    public void remove(Context context, String str) {
        if (f4832a.contains(str)) {
            b.remove(str);
            b.commit();
        }
    }

    public void saveLastHeartRateSyncTimeToFit(Long l) {
        b.putLong(KEY_LAST_HEART_RATE_SYNC_TIME_TO_FIT, l.longValue()).commit();
    }

    public void saveLastSPO2SyncTimeToFit(Long l) {
        b.putLong(KEY_LAST_SPO2_SYNC_TIME_TO_FIT, l.longValue()).commit();
    }

    public void saveLastSleepSyncTimeToFit(Long l) {
        b.putLong(KEY_LAST_SLEEP_SYNC_TIME_TO_FIT, l.longValue()).commit();
    }

    public void saveLastStepsSyncTimeToFit(Long l) {
        b.putLong(KEY_LAST_STEPS_SYNC_TIME_TO_FIT, l.longValue()).commit();
    }

    public void saveLastTemperatureSyncTimeToFit(Long l) {
        b.putLong(KEY_LAST_TEMPERATURE_SYNC_TIME_TO_FIT, l.longValue()).commit();
    }
}
