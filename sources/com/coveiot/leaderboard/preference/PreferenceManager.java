package com.coveiot.leaderboard.preference;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.leaderboard.utils.LeaderboardUtils;
/* loaded from: classes9.dex */
public class PreferenceManager {
    public static void a(Context context, String str, PreferenceType preferenceType) {
        SharedPreferences.Editor edit = context.getSharedPreferences(preferenceType != null ? preferenceType.getFileName() : str, 0).edit();
        edit.remove(str);
        edit.commit();
    }

    public static void clearData(Context context) {
        for (CloveCommonPreference cloveCommonPreference : CloveCommonPreference.values()) {
            remove(context, cloveCommonPreference);
        }
    }

    public static boolean containsPreference(Context context, SavedPreference savedPreference) {
        PreferenceType preferenceType = savedPreference.getPreferenceType();
        if (preferenceType == null) {
            return false;
        }
        String fileName = preferenceType.getFileName();
        return context.getSharedPreferences(fileName, 0).contains(savedPreference.getName());
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

    public static <T> T getPreference(Context context, SavedPreference savedPreference, T t) {
        return (T) getPreference(context, savedPreference.getPreferenceType(), savedPreference.getName(), t);
    }

    public static String getString(Context context, String str) {
        return getString(context, str, "");
    }

    public static void remove(Context context, String str) {
        a(context, str, null);
    }

    public static void saveBoolean(Context context, String str, boolean z) {
        savePreference(context, null, str, Boolean.valueOf(z));
    }

    public static void saveInt(Context context, String str, int i) {
        savePreference(context, null, str, Integer.valueOf(i));
    }

    public static void saveLong(Context context, String str, long j) {
        savePreference(context, null, str, Long.valueOf(j));
    }

    public static void savePreference(Context context, SavedPreference savedPreference, Object obj) {
        savePreference(context, savedPreference.getPreferenceType(), savedPreference.getName(), obj);
    }

    public static void saveString(Context context, String str, String str2) {
        savePreference(context, null, str, str2);
    }

    public static int getInt(Context context, String str, int i) {
        return ((Integer) getPreference(context, null, str, Integer.valueOf(i))).intValue();
    }

    public static long getLong(Context context, String str, long j) {
        return ((Long) getPreference(context, null, str, Long.valueOf(j))).longValue();
    }

    public static <T> T getPreference(Context context, PreferenceType preferenceType, String str, T t) {
        String fileName;
        if (preferenceType != null) {
            try {
                fileName = preferenceType.getFileName();
            } catch (Exception unused) {
            }
        } else {
            fileName = str;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, 0);
        if (t instanceof String) {
            return (T) sharedPreferences.getString(str, t.toString());
        }
        if (t instanceof Integer) {
            return (T) Integer.valueOf(sharedPreferences.getInt(str, Integer.valueOf(LeaderboardUtils.parseInt(t.toString(), -1)).intValue()));
        }
        if (t instanceof Long) {
            return (T) Long.valueOf(sharedPreferences.getLong(str, Long.valueOf(LeaderboardUtils.parseLong(t.toString(), -1L)).longValue()));
        }
        if (t instanceof Boolean) {
            return (T) Boolean.valueOf(sharedPreferences.getBoolean(str, LeaderboardUtils.parseBoolean(t.toString(), null).booleanValue()));
        }
        return t;
    }

    public static String getString(Context context, String str, String str2) {
        return (String) getPreference(context, null, str, str2);
    }

    public static void remove(Context context, SavedPreference savedPreference) {
        a(context, savedPreference.getName(), savedPreference.getPreferenceType());
    }

    public static void savePreference(Context context, PreferenceType preferenceType, String str, Object obj) {
        String fileName;
        Boolean parseBoolean;
        if (preferenceType != null) {
            try {
                fileName = preferenceType.getFileName();
            } catch (Exception unused) {
                return;
            }
        } else {
            fileName = str;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(fileName, 0).edit();
        if (obj instanceof String) {
            edit.putString(str, obj.toString());
        } else if (obj instanceof Integer) {
            Integer valueOf = Integer.valueOf(LeaderboardUtils.parseInt(obj.toString(), -1));
            if (valueOf.intValue() != -1) {
                edit.putInt(str, valueOf.intValue());
            }
        } else if (obj instanceof Long) {
            Long valueOf2 = Long.valueOf(LeaderboardUtils.parseLong(obj.toString(), -1L));
            if (valueOf2.longValue() != -1) {
                edit.putLong(str, valueOf2.longValue());
            }
        } else if ((obj instanceof Boolean) && (parseBoolean = LeaderboardUtils.parseBoolean(obj.toString(), null)) != null) {
            edit.putBoolean(str, parseBoolean.booleanValue());
        }
        edit.commit();
    }
}
