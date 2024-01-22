package com.coveiot.sdk.ble.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.utils.utility.LogHelper;
/* loaded from: classes9.dex */
public class BlePreferenceManager {
    public static void a(Context context, BlePreferenceType blePreferenceType, String str, Object obj) {
        String fileName;
        Boolean parseBoolean;
        if (blePreferenceType != null) {
            try {
                fileName = blePreferenceType.getFileName();
            } catch (Exception e) {
                LogHelper.d("TAG", "Exception :" + e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                return;
            }
        } else {
            fileName = str;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(fileName, 0).edit();
        if (obj instanceof String) {
            edit.putString(str, obj.toString());
        } else if (obj instanceof Integer) {
            Integer valueOf = Integer.valueOf(BleUtils.parseInt(obj.toString(), -1));
            if (valueOf.intValue() != -1) {
                edit.putInt(str, valueOf.intValue());
            }
        } else if (obj instanceof Long) {
            Long valueOf2 = Long.valueOf(BleUtils.parseLong(obj.toString(), -1L));
            if (valueOf2.longValue() != -1) {
                edit.putLong(str, valueOf2.longValue());
            }
        } else if ((obj instanceof Boolean) && (parseBoolean = BleUtils.parseBoolean(obj.toString(), null)) != null) {
            edit.putBoolean(str, parseBoolean.booleanValue());
        }
        edit.commit();
    }

    public static void b(Context context, String str, BlePreferenceType blePreferenceType) {
        SharedPreferences.Editor edit = context.getSharedPreferences(blePreferenceType != null ? blePreferenceType.getFileName() : str, 0).edit();
        edit.remove(str);
        edit.commit();
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        return ((Boolean) getPreference(context, null, str, Boolean.valueOf(z))).booleanValue();
    }

    public static int getInt(Context context, String str) {
        return getInt(context, str, Integer.MIN_VALUE);
    }

    public static long getLong(Context context, String str) {
        return getLong(context, str, Long.MIN_VALUE);
    }

    public static <T> T getPreference(Context context, BleSavedPreference bleSavedPreference, T t) {
        return (T) getPreference(context, bleSavedPreference.getPreferenceType(), bleSavedPreference.getName(), t);
    }

    public static String getString(Context context, String str) {
        return getString(context, str, "");
    }

    public static void remove(Context context, String str) {
        b(context, str, null);
    }

    public static void saveBoolean(Context context, String str, boolean z) {
        a(context, null, str, Boolean.valueOf(z));
    }

    public static void saveInt(Context context, String str, int i) {
        a(context, null, str, Integer.valueOf(i));
    }

    public static void saveLong(Context context, String str, long j) {
        a(context, null, str, Long.valueOf(j));
    }

    public static void savePreference(Context context, BleSavedPreference bleSavedPreference, Object obj) {
        a(context, bleSavedPreference.getPreferenceType(), bleSavedPreference.getName(), obj);
    }

    public static void saveString(Context context, String str, String str2) {
        a(context, null, str, str2);
    }

    public static int getInt(Context context, String str, int i) {
        return ((Integer) getPreference(context, null, str, Integer.valueOf(i))).intValue();
    }

    public static long getLong(Context context, String str, long j) {
        return ((Long) getPreference(context, null, str, Long.valueOf(j))).longValue();
    }

    public static <T> T getPreference(Context context, BlePreferenceType blePreferenceType, String str, T t) {
        String fileName;
        if (blePreferenceType != null) {
            try {
                fileName = blePreferenceType.getFileName();
            } catch (Exception e) {
                LogHelper.d("TAG", "Exception : " + e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        } else {
            fileName = str;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, 0);
        if (t instanceof String) {
            return (T) sharedPreferences.getString(str, t.toString());
        }
        if (t instanceof Integer) {
            return (T) Integer.valueOf(sharedPreferences.getInt(str, Integer.valueOf(BleUtils.parseInt(t.toString(), -1)).intValue()));
        }
        if (t instanceof Long) {
            return (T) Long.valueOf(sharedPreferences.getLong(str, Long.valueOf(BleUtils.parseLong(t.toString(), -1L)).longValue()));
        }
        if (t instanceof Boolean) {
            return (T) Boolean.valueOf(sharedPreferences.getBoolean(str, BleUtils.parseBoolean(t.toString(), null).booleanValue()));
        }
        return t;
    }

    public static String getString(Context context, String str, String str2) {
        return (String) getPreference(context, null, str, str2);
    }

    public static void remove(Context context, BleSavedPreference bleSavedPreference) {
        b(context, bleSavedPreference.getName(), bleSavedPreference.getPreferenceType());
    }
}
