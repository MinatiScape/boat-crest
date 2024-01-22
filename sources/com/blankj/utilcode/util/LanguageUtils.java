package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.Utils;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes.dex */
public class LanguageUtils {

    /* loaded from: classes.dex */
    public static class a implements Utils.Consumer<Boolean> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ boolean f2260a;

        public a(boolean z) {
            this.f2260a = z;
        }

        @Override // com.blankj.utilcode.util.Utils.Consumer
        /* renamed from: a */
        public void accept(Boolean bool) {
            if (bool.booleanValue()) {
                LanguageUtils.i(this.f2260a);
            } else {
                com.blankj.utilcode.util.b.P0();
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Runnable {
        public final /* synthetic */ Locale h;
        public final /* synthetic */ int i;
        public final /* synthetic */ Utils.Consumer j;

        public b(Locale locale, int i, Utils.Consumer consumer) {
            this.h = locale;
            this.i = i;
            this.j = consumer;
        }

        @Override // java.lang.Runnable
        public void run() {
            LanguageUtils.h(this.h, this.i + 1, this.j);
        }
    }

    public LanguageUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void applyLanguage(@NonNull Locale locale) {
        Objects.requireNonNull(locale, "Argument 'locale' of type Locale (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        applyLanguage(locale, false);
    }

    public static void applySystemLanguage() {
        applySystemLanguage(false);
    }

    public static Context attachBaseContext(Context context) {
        Locale k;
        String string = com.blankj.utilcode.util.b.d0().getString("KEY_LOCALE");
        if (TextUtils.isEmpty(string) || "VALUE_FOLLOW_SYSTEM".equals(string) || (k = k(string)) == null) {
            return context;
        }
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        j(configuration, k);
        if (Build.VERSION.SDK_INT >= 17) {
            return context.createConfigurationContext(configuration);
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    public static void b(Activity activity) {
        Locale k;
        String string = com.blankj.utilcode.util.b.d0().getString("KEY_LOCALE");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        if ("VALUE_FOLLOW_SYSTEM".equals(string)) {
            k = d(Resources.getSystem().getConfiguration());
        } else {
            k = k(string);
        }
        if (k == null) {
            return;
        }
        m(activity, k);
        m(Utils.getApp(), k);
    }

    public static void c(Locale locale, boolean z) {
        if (locale == null) {
            com.blankj.utilcode.util.b.d0().put("KEY_LOCALE", "VALUE_FOLLOW_SYSTEM", true);
        } else {
            com.blankj.utilcode.util.b.d0().put("KEY_LOCALE", g(locale), true);
        }
        if (locale == null) {
            locale = d(Resources.getSystem().getConfiguration());
        }
        updateAppContextLanguage(locale, new a(z));
    }

    public static Locale d(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 24) {
            return configuration.getLocales().get(0);
        }
        return configuration.locale;
    }

    public static boolean e(String str) {
        int i = 0;
        for (char c : str.toCharArray()) {
            if (c == '$') {
                if (i >= 1) {
                    return false;
                }
                i++;
            }
        }
        return i == 1;
    }

    public static boolean f(Locale locale, Locale locale2) {
        return com.blankj.utilcode.util.b.A(locale2.getLanguage(), locale.getLanguage()) && com.blankj.utilcode.util.b.A(locale2.getCountry(), locale.getCountry());
    }

    public static String g(Locale locale) {
        String language = locale.getLanguage();
        String country = locale.getCountry();
        return language + "$" + country;
    }

    public static Locale getAppContextLanguage() {
        return getContextLanguage(Utils.getApp());
    }

    public static Locale getAppliedLanguage() {
        String string = com.blankj.utilcode.util.b.d0().getString("KEY_LOCALE");
        if (TextUtils.isEmpty(string) || "VALUE_FOLLOW_SYSTEM".equals(string)) {
            return null;
        }
        return k(string);
    }

    public static Locale getContextLanguage(Context context) {
        return d(context.getResources().getConfiguration());
    }

    public static Locale getSystemLanguage() {
        return d(Resources.getSystem().getConfiguration());
    }

    public static void h(Locale locale, int i, Utils.Consumer<Boolean> consumer) {
        Resources resources = Utils.getApp().getResources();
        Configuration configuration = resources.getConfiguration();
        Locale d = d(configuration);
        j(configuration, locale);
        Utils.getApp().getResources().updateConfiguration(configuration, resources.getDisplayMetrics());
        if (consumer == null) {
            return;
        }
        if (f(d, locale)) {
            consumer.accept(Boolean.TRUE);
        } else if (i < 20) {
            com.blankj.utilcode.util.b.V0(new b(locale, i, consumer), 16L);
        } else {
            Log.e("LanguageUtils", "appLocal didn't update.");
            consumer.accept(Boolean.FALSE);
        }
    }

    public static void i(boolean z) {
        if (z) {
            com.blankj.utilcode.util.b.P0();
            return;
        }
        for (Activity activity : com.blankj.utilcode.util.b.J()) {
            activity.recreate();
        }
    }

    public static boolean isAppliedLanguage() {
        return getAppliedLanguage() != null;
    }

    public static void j(Configuration configuration, Locale locale) {
        if (Build.VERSION.SDK_INT >= 17) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
    }

    public static Locale k(String str) {
        Locale l = l(str);
        if (l == null) {
            Log.e("LanguageUtils", "The string of " + str + " is not in the correct format.");
            com.blankj.utilcode.util.b.d0().remove("KEY_LOCALE");
        }
        return l;
    }

    public static Locale l(String str) {
        if (e(str)) {
            try {
                int indexOf = str.indexOf("$");
                return new Locale(str.substring(0, indexOf), str.substring(indexOf + 1));
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    public static void m(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        j(configuration, locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public static void updateAppContextLanguage(@NonNull Locale locale, @Nullable Utils.Consumer<Boolean> consumer) {
        Objects.requireNonNull(locale, "Argument 'destLocale' of type Locale (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        h(locale, 0, consumer);
    }

    public static void applySystemLanguage(boolean z) {
        c(null, z);
    }

    public static boolean isAppliedLanguage(@NonNull Locale locale) {
        Objects.requireNonNull(locale, "Argument 'locale' of type Locale (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Locale appliedLanguage = getAppliedLanguage();
        if (appliedLanguage == null) {
            return false;
        }
        return f(locale, appliedLanguage);
    }

    public static void applyLanguage(@NonNull Locale locale, boolean z) {
        Objects.requireNonNull(locale, "Argument 'locale' of type Locale (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        c(locale, z);
    }
}
