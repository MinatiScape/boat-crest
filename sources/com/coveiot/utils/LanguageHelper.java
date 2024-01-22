package com.coveiot.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.preference.PreferenceManager;
import java.util.Locale;
/* loaded from: classes9.dex */
public class LanguageHelper {
    public static void a(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString("Language.Helper.Selected.Language", str);
        edit.apply();
    }

    @TargetApi(24)
    public static Context b(Context context, String str) {
        Locale locale = new Locale(str);
        Configuration configuration = context.getResources().getConfiguration();
        LocaleList localeList = new LocaleList(locale);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        return context.createConfigurationContext(configuration);
    }

    public static Context c(Context context, String str) {
        Locale locale = new Locale(str);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    public static String getLanguage(Context context) {
        return getPersistedData(context, Locale.getDefault().getLanguage());
    }

    public static String getPersistedData(Context context, String str) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("Language.Helper.Selected.Language", str);
    }

    public static Context onAttach(Context context) {
        return setLanguage(context, getPersistedData(context, Locale.getDefault().getLanguage()));
    }

    public static Context setLanguage(Context context, String str) {
        a(context, str);
        if (Build.VERSION.SDK_INT >= 24) {
            return b(context, str);
        }
        return c(context, str);
    }

    public static Context onAttach(Context context, String str) {
        return setLanguage(context, getPersistedData(context, str));
    }
}
