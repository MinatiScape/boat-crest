package com.coveiot.sdk.ble.helper;

import com.coveiot.utils.utility.LogHelper;
/* loaded from: classes9.dex */
public class LogsHelper {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f7573a = false;

    public static void d(String str, String str2) {
        if (f7573a) {
            LogHelper.d(str, str2);
        }
    }

    public static void d(String str, String str2, Exception exc) {
    }

    public static void e(String str, String str2) {
        if (f7573a) {
            LogHelper.e(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (f7573a) {
            LogHelper.i(str, str2);
        }
    }

    public static void init(boolean z) {
        f7573a = z;
    }

    public static void w(String str, String str2) {
        if (f7573a) {
            LogHelper.w(str, str2);
        }
    }

    public static void e(String str, String str2, Exception exc) {
        if (f7573a) {
            LogHelper.e(str, str2);
        }
    }
}
