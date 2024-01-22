package com.htsmart.wristband2.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.szabh.smable3.entity.Languages;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes11.dex */
public class Utils {

    /* renamed from: a  reason: collision with root package name */
    public static Map<Locale, Byte> f12033a = new HashMap(5);

    public static byte a(Locale locale) {
        String language = locale.getLanguage();
        String country = locale.getCountry();
        if (language.equals(Locale.CHINESE.getLanguage())) {
            return country.equals(Locale.SIMPLIFIED_CHINESE.getCountry()) ? (byte) 1 : (byte) 2;
        }
        Map<String, Byte> c = c();
        Byte b = c.get(language);
        if (b != null) {
            WristbandLog.i("getSystemLanguageType in map direct, value=%d", b);
            return b.byteValue();
        }
        for (String str : c.keySet()) {
            if (language.equals(new Locale(str).getLanguage())) {
                WristbandLog.i("getSystemLanguageType in map local, value=%d", c.get(str));
                return c.get(str).byteValue();
            }
        }
        return (byte) 0;
    }

    public static int addFlag(int i, int i2) {
        return b(i, i2, i2);
    }

    public static int b(int i, int i2, int i3) {
        return (i & (~i3)) | (i2 & i3);
    }

    public static Map<String, Byte> c() {
        HashMap hashMap = new HashMap(80);
        hashMap.put(Languages.DEFAULT_LANGUAGE, (byte) 3);
        hashMap.put("de", (byte) 4);
        hashMap.put("ru", (byte) 5);
        hashMap.put("es", (byte) 6);
        hashMap.put("pt", (byte) 7);
        hashMap.put("fr", (byte) 8);
        hashMap.put("ja", (byte) 9);
        hashMap.put("ar", (byte) 10);
        hashMap.put("nl", (byte) 11);
        hashMap.put("it", (byte) 12);
        hashMap.put("bn", (byte) 13);
        hashMap.put("hr", (byte) 14);
        hashMap.put("cs", (byte) 15);
        hashMap.put("da", (byte) 16);
        hashMap.put("el", (byte) 17);
        hashMap.put("he", (byte) 18);
        hashMap.put("hi", (byte) 19);
        hashMap.put("hu", (byte) 20);
        hashMap.put("in", (byte) 21);
        hashMap.put("ko", (byte) 22);
        hashMap.put("ms", (byte) 23);
        hashMap.put("fa", (byte) 24);
        hashMap.put("pl", (byte) 25);
        hashMap.put("ro", (byte) 26);
        hashMap.put("sr", (byte) 27);
        hashMap.put("sv", (byte) 28);
        hashMap.put("th", (byte) 29);
        hashMap.put("tr", (byte) 30);
        hashMap.put("ur", (byte) 31);
        hashMap.put("vi", (byte) 32);
        hashMap.put("ca", (byte) 33);
        hashMap.put("lv", (byte) 34);
        hashMap.put("lt", (byte) 35);
        hashMap.put("nb", (byte) 36);
        hashMap.put("sk", (byte) 37);
        hashMap.put("sl", (byte) 38);
        hashMap.put(Constants.KEY_BG, (byte) 39);
        hashMap.put("uk", (byte) 40);
        hashMap.put("tl", (byte) 41);
        hashMap.put("fi", (byte) 42);
        hashMap.put("af", (byte) 43);
        hashMap.put("rm", (byte) 44);
        hashMap.put("my", (byte) 45);
        hashMap.put("km", (byte) 46);
        hashMap.put("am", (byte) 47);
        hashMap.put("be", (byte) 48);
        hashMap.put("et", (byte) 49);
        hashMap.put("sw", (byte) 50);
        hashMap.put("zu", (byte) 51);
        hashMap.put("az", (byte) 52);
        hashMap.put("hy", (byte) 53);
        hashMap.put("ka", (byte) 54);
        hashMap.put("lo", (byte) 55);
        hashMap.put("mn", (byte) 56);
        hashMap.put("ne", (byte) 57);
        hashMap.put("kk", (byte) 58);
        hashMap.put("gl", (byte) 59);
        hashMap.put("is", (byte) 60);
        hashMap.put("kn", (byte) 61);
        hashMap.put("ky", (byte) 62);
        hashMap.put("ml", (byte) 63);
        hashMap.put("mr", (byte) 64);
        hashMap.put("ta", (byte) 65);
        hashMap.put("mk", (byte) 66);
        hashMap.put("te", (byte) 67);
        hashMap.put("uz", (byte) 68);
        hashMap.put("eu", Byte.valueOf((byte) com.crrepa.c.a.E0));
        hashMap.put("si", Byte.valueOf((byte) com.htsmart.wristband2.a.a.a.U0));
        hashMap.put("sq", (byte) 71);
        return hashMap;
    }

    public static int clearFlag(int i, int i2) {
        return b(i, 0, i2);
    }

    @NonNull
    public static File[] getExternalFilesDirs(@NonNull Context context, @Nullable String str) {
        return Build.VERSION.SDK_INT >= 19 ? context.getExternalFilesDirs(str) : new File[]{context.getExternalFilesDir(str)};
    }

    public static byte getSystemLanguageType(Context context) {
        int i = Build.VERSION.SDK_INT;
        Configuration configuration = context.getResources().getConfiguration();
        Locale locale = i >= 24 ? configuration.getLocales().get(0) : configuration.locale;
        if (locale == null) {
            return (byte) 0;
        }
        Byte b = f12033a.get(locale);
        if (b != null) {
            WristbandLog.i("getSystemLanguageType in cache, value=%d", b);
        } else {
            b = Byte.valueOf(a(locale));
            f12033a.put(locale, b);
        }
        return b.byteValue();
    }

    public static boolean isFlagEnable(int i, int i2) {
        return (i & i2) > 0;
    }
}
