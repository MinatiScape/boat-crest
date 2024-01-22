package com.coveiot.android.spo2sdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes7.dex */
public class Spo2PreferenceManager {

    /* renamed from: a  reason: collision with root package name */
    public static String f5793a = "";

    public static boolean getBoolean(Context context, String str, boolean z) {
        return ((Boolean) getPreference(context, str, Boolean.valueOf(z))).booleanValue();
    }

    public static int getInt(Context context, String str) {
        return getInt(context, str, Integer.MIN_VALUE);
    }

    public static int getInt(Context context, String str, int i) {
        return ((Integer) getPreference(context, str, Integer.valueOf(i))).intValue();
    }

    public static long getLong(Context context, String str) {
        return getLong(context, str, Long.MIN_VALUE);
    }

    public static long getLong(Context context, String str, long j) {
        return ((Long) getPreference(context, str, Long.valueOf(j))).longValue();
    }

    public static <T> T getPreference(Context context, String str, T t) {
        SharedPreferences sharedPreferences;
        try {
            sharedPreferences = context.getSharedPreferences("com.coveiot.android.spo2.prefs", 0);
        } catch (Exception unused) {
        }
        if (t instanceof String) {
            return (T) sharedPreferences.getString(str, t.toString());
        }
        if (t instanceof Integer) {
            return (T) Integer.valueOf(sharedPreferences.getInt(str, Integer.valueOf(Integer.parseInt(t.toString(), -1)).intValue()));
        }
        if (t instanceof Long) {
            return (T) Long.valueOf(sharedPreferences.getLong(str, Long.valueOf(Long.parseLong(t.toString(), -1)).longValue()));
        }
        if (t instanceof Boolean) {
            return (T) Boolean.valueOf(sharedPreferences.getBoolean(str, Boolean.valueOf(Boolean.parseBoolean(t.toString())).booleanValue()));
        }
        return t;
    }

    public static String getString(Context context, String str) {
        return getString(context, str, f5793a);
    }

    public static String getString(Context context, String str, String str2) {
        return (String) getPreference(context, str, str2);
    }

    public static void saveBoolean(Context context, String str, boolean z) {
        savePreference(context, str, Boolean.valueOf(z));
    }

    public static void saveInt(Context context, String str, int i) {
        savePreference(context, str, Integer.valueOf(i));
    }

    public static void saveLong(Context context, String str, long j) {
        savePreference(context, str, Long.valueOf(j));
    }

    public static void savePreference(Context context, String str, Object obj) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("com.coveiot.android.spo2.prefs", 0).edit();
            if (obj instanceof String) {
                edit.putString(str, obj.toString());
            } else if (obj instanceof Integer) {
                int parseInt = Integer.parseInt(obj.toString(), -1);
                if (parseInt != -1) {
                    edit.putInt(str, parseInt);
                }
            } else if (obj instanceof Long) {
                long parseLong = Long.parseLong(obj.toString(), -1);
                if (parseLong != -1) {
                    edit.putLong(str, parseLong);
                }
            } else if (obj instanceof Boolean) {
                edit.putBoolean(str, Boolean.parseBoolean(obj.toString()));
            }
            edit.commit();
        } catch (Exception unused) {
        }
    }

    public static void saveString(Context context, String str, String str2) {
        savePreference(context, str, str2);
    }
}
