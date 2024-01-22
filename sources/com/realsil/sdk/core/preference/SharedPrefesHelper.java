package com.realsil.sdk.core.preference;

import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes12.dex */
public final class SharedPrefesHelper {
    public static void clear(Context context, String str) {
        SharedPreferences.Editor editor = getEditor(context, str);
        editor.clear();
        editor.commit();
    }

    public static boolean getBoolean(Context context, String str, String str2, boolean z) {
        return getSharedPreferences(context, str).getBoolean(str2, z);
    }

    public static SharedPreferences.Editor getEditor(Context context, String str) {
        return getSharedPreferences(context, str).edit();
    }

    public static int getInt(Context context, String str, String str2, int i) {
        return getSharedPreferences(context, str).getInt(str2, i);
    }

    public static Long getLong(Context context, String str, String str2, Long l) {
        return Long.valueOf(getSharedPreferences(context, str).getLong(str2, l.longValue()));
    }

    public static SharedPreferences getSharedPreferences(Context context, String str) {
        return context.getSharedPreferences(str, 0);
    }

    public static String getString(Context context, String str, String str2, String str3) {
        return getSharedPreferences(context, str).getString(str2, str3);
    }

    public static void set(Context context, String str, String str2, boolean z) {
        SharedPreferences.Editor editor = getEditor(context, str);
        editor.putBoolean(str2, z);
        editor.commit();
    }

    public static boolean getBoolean(SharedPreferences sharedPreferences, String str, boolean z) {
        return sharedPreferences.getBoolean(str, z);
    }

    public static int getInt(SharedPreferences sharedPreferences, String str, int i) {
        return sharedPreferences.getInt(str, i);
    }

    public static Long getLong(SharedPreferences sharedPreferences, String str, Long l) {
        return Long.valueOf(sharedPreferences.getLong(str, l.longValue()));
    }

    public static String getString(SharedPreferences sharedPreferences, String str, String str2) {
        return sharedPreferences.getString(str, str2);
    }

    public static void clear(SharedPreferences.Editor editor) {
        editor.clear();
        editor.commit();
    }

    public static void set(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor editor = getEditor(context, str);
        editor.putString(str2, str3);
        editor.commit();
    }

    public static void set(Context context, String str, String str2, int i) {
        SharedPreferences.Editor editor = getEditor(context, str);
        editor.putInt(str2, i);
        editor.commit();
    }

    public static void set(Context context, String str, String str2, long j) {
        SharedPreferences.Editor editor = getEditor(context, str);
        editor.putLong(str2, j);
        editor.commit();
    }

    public static void set(SharedPreferences.Editor editor, String str, boolean z) {
        editor.putBoolean(str, z);
        editor.commit();
    }

    public static void set(SharedPreferences.Editor editor, String str, String str2) {
        editor.putString(str, str2);
        editor.commit();
    }

    public static void set(SharedPreferences.Editor editor, String str, int i) {
        editor.putInt(str, i);
        editor.commit();
    }

    public static void set(SharedPreferences.Editor editor, String str, long j) {
        editor.putLong(str, j);
        editor.commit();
    }
}
