package com.touchgui.sdk;

import com.clevertap.android.sdk.Constants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes12.dex */
public class TGLanguage {
    public static final int ar = 34;
    public static final int bg = 32;
    public static final int cs = 9;
    public static final int da = 18;
    public static final int de = 4;
    public static final int el = 30;
    public static final int en = 2;
    public static final int es = 6;
    public static final int fr = 3;
    public static final int hi = 22;
    public static final int hr = 19;
    public static final int hu = 14;
    public static final int id = 20;
    public static final int it = 5;
    public static final int ja = 7;
    public static final int ko = 21;
    public static final int lt = 11;
    public static final int ms = 31;
    public static final int my = 27;
    public static final int nl = 12;
    public static final int pl = 8;
    public static final int pt = 23;
    public static final int ro = 10;
    public static final int ru = 15;
    private static final Map<String, Integer> sLanguageMap;
    public static final int sk = 17;
    public static final int sl = 13;
    public static final int sr = 33;
    public static final int th = 25;
    public static final int tl = 28;
    public static final int tr = 24;
    public static final int uk = 16;
    public static final int vi = 26;
    public static final int zh = 1;
    public static final int zh_t = 29;

    static {
        HashMap hashMap = new HashMap();
        sLanguageMap = hashMap;
        hashMap.put("fr", 3);
        hashMap.put("de", 4);
        hashMap.put("it", 5);
        hashMap.put("es", 6);
        hashMap.put("ja", 7);
        hashMap.put("pl", 8);
        hashMap.put("cs", 9);
        hashMap.put("ro", 10);
        hashMap.put("lt", 11);
        hashMap.put("nl", 12);
        hashMap.put("sl", 13);
        hashMap.put("hu", 14);
        hashMap.put("ru", 15);
        hashMap.put("uk", 16);
        hashMap.put("sk", 17);
        hashMap.put("da", 18);
        hashMap.put("hr", 19);
        hashMap.put("id", 20);
        hashMap.put("ko", 21);
        hashMap.put("hi", 22);
        hashMap.put("pt", 23);
        hashMap.put("tr", 24);
        hashMap.put("th", 25);
        hashMap.put("vi", 26);
        hashMap.put("my", 27);
        hashMap.put("tl", 28);
        hashMap.put("el", 30);
        hashMap.put("ms", 31);
        hashMap.put(Constants.KEY_BG, 32);
        hashMap.put("sr", 33);
        hashMap.put("ar", 34);
        hashMap.put("in", 20);
    }

    public static int getCurrentLanguage() {
        return getLanguage(Locale.getDefault());
    }

    public static int getLanguage(Locale locale) {
        if (Locale.CHINESE == locale || Locale.SIMPLIFIED_CHINESE == locale || locale.toString().contains("zh_CN") || locale.toString().contains("zh_BR_#Hans")) {
            return 1;
        }
        if (Locale.TRADITIONAL_CHINESE == locale || locale.toString().contains("zh_HK") || locale.toString().contains("zh_MO") || locale.toString().contains("zh_BR_#Hant") || locale.toString().contains("zh_TW") || locale.toString().contains("zh_MO")) {
            return 29;
        }
        if (Locale.JAPAN == locale || Locale.JAPANESE == locale || locale.toString().contains("ja")) {
            return 7;
        }
        if (Locale.FRANCE == locale || Locale.FRENCH == locale || locale.toString().contains("fr")) {
            return 3;
        }
        if (Locale.GERMAN == locale || Locale.GERMANY == locale || locale.toString().contains("de")) {
            return 4;
        }
        if (Locale.ITALIAN == locale || Locale.ITALY == locale || locale.toString().contains("it")) {
            return 5;
        }
        if (Locale.KOREAN == locale || Locale.KOREA == locale || locale.toString().contains("ko")) {
            return 21;
        }
        for (Map.Entry<String, Integer> entry : sLanguageMap.entrySet()) {
            if (locale.toString().startsWith(entry.getKey())) {
                return entry.getValue().intValue();
            }
        }
        return 2;
    }
}
