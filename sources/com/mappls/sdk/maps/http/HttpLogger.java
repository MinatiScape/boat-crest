package com.mappls.sdk.maps.http;

import com.mappls.sdk.maps.log.Logger;
/* loaded from: classes11.dex */
public class HttpLogger {
    public static boolean logEnabled = false;
    public static boolean logRequestUrl = false;

    public static void log(int i, String str) {
        if (logEnabled) {
            Logger.log(i, "Mbgl-HttpRequest", str);
        }
    }

    public static void logFailure(int i, String str, String str2) {
        int i2 = i == 1 ? 3 : i == 0 ? 4 : 5;
        Object[] objArr = new Object[3];
        objArr[0] = i == 1 ? "temporary" : i == 0 ? "connection" : "permanent";
        objArr[1] = str;
        if (!logRequestUrl) {
            str2 = "";
        }
        objArr[2] = str2;
        log(i2, String.format("Request failed due to a %s error: %s %s", objArr));
    }
}
