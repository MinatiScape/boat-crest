package com.jieli.jl_rcsp.util;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import java.io.PrintStream;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes11.dex */
public class TestLog {
    private static void a(String str, String str2, String str3) {
        PrintStream printStream = System.out;
        printStream.println(str + "--->" + str2 + ":" + str3);
    }

    public static void d(String str, String str2) {
        a("d", str, str2);
    }

    public static void e(String str, String str2) {
        a(RsaJsonWebKey.EXPONENT_MEMBER_NAME, str, str2);
    }

    public static void i(String str, String str2) {
        a("i", str, str2);
    }

    public static void v(String str, String str2) {
        a(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, str, str2);
    }

    public static void w(String str, String str2) {
        a(Constants.INAPP_WINDOW, str, str2);
    }
}
