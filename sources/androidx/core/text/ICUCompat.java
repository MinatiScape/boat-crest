package androidx.core.text;

import android.annotation.SuppressLint;
import android.icu.util.ULocale;
import android.os.Build;
import android.util.Log;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
/* loaded from: classes.dex */
public final class ICUCompat {

    /* renamed from: a  reason: collision with root package name */
    public static Method f1094a;
    public static Method b;

    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class a {
        @DoNotInline
        public static String a(Locale locale) {
            return locale.getScript();
        }
    }

    @RequiresApi(24)
    /* loaded from: classes.dex */
    public static class b {
        @DoNotInline
        public static ULocale a(Object obj) {
            return ULocale.addLikelySubtags((ULocale) obj);
        }

        @DoNotInline
        public static ULocale b(Locale locale) {
            return ULocale.forLocale(locale);
        }

        @DoNotInline
        public static String c(Object obj) {
            return ((ULocale) obj).getScript();
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            if (i < 24) {
                try {
                    b = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
                    return;
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
            return;
        }
        try {
            Class<?> cls = Class.forName("libcore.icu.ICU");
            f1094a = cls.getMethod("getScript", String.class);
            b = cls.getMethod("addLikelySubtags", String.class);
        } catch (Exception e2) {
            f1094a = null;
            b = null;
            Log.w("ICUCompat", e2);
        }
    }

    @SuppressLint({"BanUncheckedReflection"})
    public static String a(Locale locale) {
        String locale2 = locale.toString();
        try {
            Method method = b;
            if (method != null) {
                return (String) method.invoke(null, locale2);
            }
        } catch (IllegalAccessException e) {
            Log.w("ICUCompat", e);
        } catch (InvocationTargetException e2) {
            Log.w("ICUCompat", e2);
        }
        return locale2;
    }

    @SuppressLint({"BanUncheckedReflection"})
    public static String b(String str) {
        try {
            Method method = f1094a;
            if (method != null) {
                return (String) method.invoke(null, str);
            }
        } catch (IllegalAccessException e) {
            Log.w("ICUCompat", e);
        } catch (InvocationTargetException e2) {
            Log.w("ICUCompat", e2);
        }
        return null;
    }

    @Nullable
    public static String maximizeAndGetScript(@NonNull Locale locale) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            return b.c(b.a(b.b(locale)));
        }
        if (i >= 21) {
            try {
                return a.a((Locale) b.invoke(null, locale));
            } catch (IllegalAccessException e) {
                Log.w("ICUCompat", e);
                return a.a(locale);
            } catch (InvocationTargetException e2) {
                Log.w("ICUCompat", e2);
                return a.a(locale);
            }
        }
        String a2 = a(locale);
        if (a2 != null) {
            return b(a2);
        }
        return null;
    }
}
