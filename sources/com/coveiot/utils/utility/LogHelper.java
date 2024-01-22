package com.coveiot.utils.utility;

import com.coveiot.android.dashboard2.util.Dashboard2Constants;
import io.shipbook.shipbooksdk.Log;
/* loaded from: classes9.dex */
public class LogHelper {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f7626a = false;
    public static RemoteConfig b;

    public static void d(String str, String str2) {
        if (f7626a) {
            Log.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (f7626a) {
            Log.e(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (f7626a) {
            Log.i(str, str2);
        }
    }

    public static void initialize(boolean z) {
        f7626a = z;
    }

    public static void w(String str, String str2) {
        if (f7626a) {
            Log.w(str, str2);
        }
    }

    public static void initialize(RemoteConfig remoteConfig) {
        b = remoteConfig;
    }

    public static void d(String str, String str2, Exception exc) {
        if (f7626a) {
            Log.d(str, str2, exc);
        }
    }

    public static void e(String str, String str2, Exception exc) {
        if (f7626a) {
            Log.e(str, str2, exc);
        }
    }

    public static void i(String str, String str2, String str3) {
        RemoteConfig remoteConfig = b;
        if (remoteConfig != null && (remoteConfig.getData().getAllowState().equalsIgnoreCase(Dashboard2Constants.ALL_ON) || (b.getData().getAllowState().equalsIgnoreCase("SPECIFIC_IDS") && b.getData().getAllowedIds().contains(str3)))) {
            Log.i(str, str2);
        }
        if (f7626a) {
            Log.e(str, str2);
        }
    }

    public static void w(String str, String str2, String str3) {
        RemoteConfig remoteConfig = b;
        if (remoteConfig != null && (remoteConfig.getData().getAllowState().equalsIgnoreCase(Dashboard2Constants.ALL_ON) || (b.getData().getAllowState().equalsIgnoreCase("SPECIFIC_IDS") && b.getData().getAllowedIds().contains(str3)))) {
            Log.w(str, str2);
        }
        if (f7626a) {
            Log.e(str, str2);
        }
    }

    public static void d(String str, String str2, String str3) {
        RemoteConfig remoteConfig = b;
        if (remoteConfig != null && (remoteConfig.getData().getAllowState().equalsIgnoreCase(Dashboard2Constants.ALL_ON) || (b.getData().getAllowState().equalsIgnoreCase("SPECIFIC_IDS") && b.getData().getAllowedIds().contains(str3)))) {
            Log.d(str, str2);
        }
        if (f7626a) {
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, String str3) {
        RemoteConfig remoteConfig = b;
        if (remoteConfig != null && (remoteConfig.getData().getAllowState().equalsIgnoreCase(Dashboard2Constants.ALL_ON) || (b.getData().getAllowState().equalsIgnoreCase("SPECIFIC_IDS") && b.getData().getAllowedIds().contains(str3)))) {
            Log.e(str, str2);
        }
        if (f7626a) {
            Log.e(str, str2);
        }
    }

    public static void d(String str, String str2, Exception exc, String str3) {
        RemoteConfig remoteConfig = b;
        if (remoteConfig != null && (remoteConfig.getData().getAllowState().equalsIgnoreCase(Dashboard2Constants.ALL_ON) || (b.getData().getAllowState().equalsIgnoreCase("SPECIFIC_IDS") && b.getData().getAllowedIds().contains(str3)))) {
            Log.d(str, str2, exc);
        }
        if (f7626a) {
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Exception exc, String str3) {
        RemoteConfig remoteConfig = b;
        if (remoteConfig != null && (remoteConfig.getData().getAllowState().equalsIgnoreCase(Dashboard2Constants.ALL_ON) || (b.getData().getAllowState().equalsIgnoreCase("SPECIFIC_IDS") && b.getData().getAllowedIds().contains(str3)))) {
            Log.e(str, str2, exc);
        }
        if (f7626a) {
            Log.e(str, str2);
        }
    }
}
