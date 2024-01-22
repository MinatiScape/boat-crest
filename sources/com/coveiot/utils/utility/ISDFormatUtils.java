package com.coveiot.utils.utility;

import com.clevertap.android.sdk.Constants;
import java.util.Locale;
/* loaded from: classes9.dex */
public class ISDFormatUtils {
    public static String getCountryCode(String str) {
        return str.split(Constants.SEPARATOR_COMMA)[1].trim();
    }

    public static String getCountryFullName(String str) {
        return new Locale("", str.split(Constants.SEPARATOR_COMMA)[1].trim()).getDisplayCountry();
    }

    public static String getIsdCode(String str) {
        return str.split(Constants.SEPARATOR_COMMA)[0].trim();
    }

    public static String getThreeCharUNCode(String str) {
        return str.split(Constants.SEPARATOR_COMMA)[1].trim();
    }

    public static String getTwoCharISOCode(String str) {
        return str.split(Constants.SEPARATOR_COMMA)[1].trim();
    }
}
