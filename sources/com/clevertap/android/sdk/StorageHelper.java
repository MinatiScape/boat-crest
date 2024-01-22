package com.clevertap.android.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public final class StorageHelper {
    public static boolean a(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str) {
        if (cleverTapInstanceConfig.isDefaultInstance()) {
            boolean z = getBoolean(context, storageKeyWithSuffix(cleverTapInstanceConfig, str), false);
            return !z ? getBoolean(context, str, false) : z;
        }
        return getBoolean(context, storageKeyWithSuffix(cleverTapInstanceConfig, str), false);
    }

    public static long b(Context context, String str, String str2, long j) {
        return getPreferences(context, str).getLong(str2, j);
    }

    public static String c(Context context, String str, String str2, String str3) {
        return getPreferences(context, str).getString(str2, str3);
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        return getPreferences(context).getBoolean(str, z);
    }

    public static int getInt(Context context, String str, int i) {
        return getPreferences(context).getInt(str, i);
    }

    public static int getIntFromPrefs(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str, int i) {
        if (cleverTapInstanceConfig.isDefaultInstance()) {
            int i2 = getInt(context, storageKeyWithSuffix(cleverTapInstanceConfig, str), -1000);
            return i2 != -1000 ? i2 : getInt(context, str, i);
        }
        return getInt(context, storageKeyWithSuffix(cleverTapInstanceConfig, str), i);
    }

    public static long getLongFromPrefs(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str, int i, String str2) {
        if (cleverTapInstanceConfig.isDefaultInstance()) {
            long b = b(context, str2, storageKeyWithSuffix(cleverTapInstanceConfig, str), -1000L);
            return b != -1000 ? b : b(context, str2, str, i);
        }
        return b(context, str2, storageKeyWithSuffix(cleverTapInstanceConfig, str), i);
    }

    public static SharedPreferences getPreferences(@NonNull Context context, String str) {
        String str2 = Constants.CLEVERTAP_STORAGE_TAG;
        if (str != null) {
            str2 = Constants.CLEVERTAP_STORAGE_TAG + "_" + str;
        }
        return context.getSharedPreferences(str2, 0);
    }

    public static String getString(@NonNull Context context, @NonNull String str, String str2) {
        return getPreferences(context).getString(str, str2);
    }

    public static String getStringFromPrefs(@NonNull Context context, @NonNull CleverTapInstanceConfig cleverTapInstanceConfig, String str, String str2) {
        if (cleverTapInstanceConfig.isDefaultInstance()) {
            String string = getString(context, storageKeyWithSuffix(cleverTapInstanceConfig, str), str2);
            return string != null ? string : getString(context, str, str2);
        }
        return getString(context, storageKeyWithSuffix(cleverTapInstanceConfig, str), str2);
    }

    public static void persist(SharedPreferences.Editor editor) {
        try {
            editor.apply();
        } catch (Throwable th) {
            Logger.v("CRITICAL: Failed to persist shared preferences!", th);
        }
    }

    @WorkerThread
    public static void persistImmediately(SharedPreferences.Editor editor) {
        try {
            editor.commit();
        } catch (Throwable th) {
            Logger.v("CRITICAL: Failed to persist shared preferences!", th);
        }
    }

    public static void putBoolean(Context context, String str, boolean z) {
        persist(getPreferences(context).edit().putBoolean(str, z));
    }

    public static void putBooleanImmediate(Context context, String str, boolean z) {
        persistImmediately(getPreferences(context).edit().putBoolean(str, z));
    }

    public static void putInt(Context context, String str, int i) {
        persist(getPreferences(context).edit().putInt(str, i));
    }

    public static void putIntImmediate(Context context, String str, int i) {
        persistImmediately(getPreferences(context).edit().putInt(str, i));
    }

    public static void putString(Context context, String str, String str2) {
        persist(getPreferences(context).edit().putString(str, str2));
    }

    public static void putStringImmediate(Context context, String str, String str2) {
        persistImmediately(getPreferences(context).edit().putString(str, str2));
    }

    public static void remove(Context context, String str) {
        persist(getPreferences(context).edit().remove(str));
    }

    public static void removeImmediate(Context context, String str) {
        persistImmediately(getPreferences(context).edit().remove(str));
    }

    public static String storageKeyWithSuffix(@NonNull CleverTapInstanceConfig cleverTapInstanceConfig, @NonNull String str) {
        return str + ":" + cleverTapInstanceConfig.getAccountId();
    }

    public static SharedPreferences getPreferences(@NonNull Context context) {
        return getPreferences(context, null);
    }

    public static void putString(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str, String str2) {
        persist(getPreferences(context).edit().putString(storageKeyWithSuffix(cleverTapInstanceConfig, str), str2));
    }
}
