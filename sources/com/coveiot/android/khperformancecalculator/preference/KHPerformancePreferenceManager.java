package com.coveiot.android.khperformancecalculator.preference;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.android.khperformancecalculator.model.KHPActivityData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class KHPerformancePreferenceManager {
    public static final String BEST_ACTIVITIES = "best_activities";
    public static final String IS_ENERGY_SCORE_NUDGE_ENABLED = "is_energy_score_nudge_enabled";
    public static final String IS_NUDGE_1_ENABLED = "is_nudge_1_enabled";
    public static final String IS_NUDGE_2_ENABLED = "is_nudge_2_enabled";
    public static final String IS_NUDGE_3_ENABLED = "is_nudge_3_enabled";
    public static final String IS_PERFORMANCE_BASED_NOTIFICATION_ENABLED = "is_performance_based_notification_enabled";
    public static final String IS_PERFORMANCE_BASED_NOTIFICATION_VIBRATION_ENABLED = "is_performance_based_notification_vibration_enabled";
    public static final String IS_SLEEP_SCORE_NUDGE_ENABLED = "is_sleep_score_nudge_enabled";
    public static final String IS_STRESS_NUDGE_ENABLED = "is_stress_nudge_enabled";
    public static final String LAST_PERFORMANCE_WORKER_RUN_TIMESTAMP = "last_performance_worker_run_timestamp";
    public static final String LAST_SLEEP_TARGET = "last_sleep_target";
    public static final String PERFORMANCE_NOTIFICATION_PREFERENCE = "performance_notification_preference";

    /* renamed from: a  reason: collision with root package name */
    public static SharedPreferences f4642a;
    public static SharedPreferences.Editor b;
    public static int c;
    public static KHPerformancePreferenceManager d;

    /* loaded from: classes2.dex */
    public class a extends TypeToken<Map<String, KHPActivityData>> {
        public a(KHPerformancePreferenceManager kHPerformancePreferenceManager) {
        }
    }

    /* loaded from: classes2.dex */
    public class b extends TypeToken<Map<String, Integer>> {
        public b(KHPerformancePreferenceManager kHPerformancePreferenceManager) {
        }
    }

    /* loaded from: classes2.dex */
    public class c extends TypeToken<Map<String, Long>> {
        public c(KHPerformancePreferenceManager kHPerformancePreferenceManager) {
        }
    }

    public static KHPerformancePreferenceManager getInstance(Context context) {
        if (d == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("KH_PERFORMANCE_PREFERENCE", c);
            f4642a = sharedPreferences;
            b = sharedPreferences.edit();
            d = new KHPerformancePreferenceManager();
        }
        return d;
    }

    public void clearPreferences(Context context) {
        for (String str : f4642a.getAll().keySet()) {
            remove(context, str);
        }
    }

    public Map<String, KHPActivityData> getBestActivities() {
        HashMap hashMap = new HashMap();
        String string = f4642a.getString(BEST_ACTIVITIES, null);
        return string != null ? (Map) new Gson().fromJson(string, new a(this).getType()) : hashMap;
    }

    public long getLastPerformanceWorkerRunTimestamp() {
        return f4642a.getLong(LAST_PERFORMANCE_WORKER_RUN_TIMESTAMP, 0L);
    }

    public Map<String, Integer> getLastSleepTarget() {
        HashMap hashMap = new HashMap();
        String string = f4642a.getString(LAST_SLEEP_TARGET, null);
        return string != null ? (Map) new Gson().fromJson(string, new b(this).getType()) : hashMap;
    }

    public Map<String, Long> getPerformanceNotificationSentRecords() {
        HashMap hashMap = new HashMap();
        String string = f4642a.getString(PERFORMANCE_NOTIFICATION_PREFERENCE, null);
        return string != null ? (Map) new Gson().fromJson(string, new c(this).getType()) : hashMap;
    }

    public Boolean isEnergyScoreNudgeEnabled() {
        return Boolean.valueOf(f4642a.getBoolean(IS_ENERGY_SCORE_NUDGE_ENABLED, false));
    }

    public Boolean isNudge1Enabled() {
        return Boolean.valueOf(f4642a.getBoolean(IS_NUDGE_1_ENABLED, false));
    }

    public Boolean isNudge2Enabled() {
        return Boolean.valueOf(f4642a.getBoolean(IS_NUDGE_2_ENABLED, false));
    }

    public Boolean isNudge3Enabled() {
        return Boolean.valueOf(f4642a.getBoolean(IS_NUDGE_3_ENABLED, false));
    }

    public Boolean isPerformanceBasedNotificationEnabled() {
        return Boolean.valueOf(f4642a.getBoolean(IS_PERFORMANCE_BASED_NOTIFICATION_ENABLED, false));
    }

    public Boolean isPerformanceBasedNotificationVibrationEnabled() {
        return Boolean.valueOf(f4642a.getBoolean(IS_PERFORMANCE_BASED_NOTIFICATION_VIBRATION_ENABLED, false));
    }

    public Boolean isSleepScoreNudgeEnabled() {
        return Boolean.valueOf(f4642a.getBoolean(IS_SLEEP_SCORE_NUDGE_ENABLED, false));
    }

    public Boolean isStressNudgeEnabled() {
        return Boolean.valueOf(f4642a.getBoolean(IS_STRESS_NUDGE_ENABLED, false));
    }

    public void remove(Context context, String str) {
        if (f4642a.contains(str)) {
            b.remove(str);
            b.commit();
        }
    }

    public void saveLastPerformanceWorkerRunTimestamp(long j) {
        b.putLong(LAST_PERFORMANCE_WORKER_RUN_TIMESTAMP, j);
        b.commit();
    }

    public void savePerformanceNotificationSentRecords(Map<String, Long> map) {
        b.putString(PERFORMANCE_NOTIFICATION_PREFERENCE, new Gson().toJson(map));
        b.commit();
    }

    public void setBestActivities(Map<String, KHPActivityData> map) {
        b.putString(BEST_ACTIVITIES, new Gson().toJson(map));
        b.commit();
    }

    public void setEnergyScoreNudgeEnabled(Boolean bool) {
        b.putBoolean(IS_ENERGY_SCORE_NUDGE_ENABLED, bool.booleanValue());
        b.commit();
    }

    public void setIsNudge1Enabled(Boolean bool) {
        b.putBoolean(IS_NUDGE_1_ENABLED, bool.booleanValue());
        b.commit();
    }

    public void setIsNudge2Enabled(Boolean bool) {
        b.putBoolean(IS_NUDGE_2_ENABLED, bool.booleanValue());
        b.commit();
    }

    public void setIsNudge3Enabled(Boolean bool) {
        b.putBoolean(IS_NUDGE_3_ENABLED, bool.booleanValue());
        b.commit();
    }

    public void setIsPerformanceBasedNotificationEnabled(Boolean bool) {
        b.putBoolean(IS_PERFORMANCE_BASED_NOTIFICATION_ENABLED, bool.booleanValue());
        b.commit();
    }

    public void setIsPerformanceBasedNotificationVibrationEnabled(Boolean bool) {
        b.putBoolean(IS_PERFORMANCE_BASED_NOTIFICATION_VIBRATION_ENABLED, bool.booleanValue());
        b.commit();
    }

    public void setLastSleepTarget(Map<String, Integer> map) {
        b.putString(LAST_SLEEP_TARGET, new Gson().toJson(map));
        b.commit();
    }

    public void setSleepScoreNudgeEnabled(Boolean bool) {
        b.putBoolean(IS_SLEEP_SCORE_NUDGE_ENABLED, bool.booleanValue());
        b.commit();
    }

    public void setStressNudgeEnabled(Boolean bool) {
        b.putBoolean(IS_STRESS_NUDGE_ENABLED, bool.booleanValue());
        b.commit();
    }
}
