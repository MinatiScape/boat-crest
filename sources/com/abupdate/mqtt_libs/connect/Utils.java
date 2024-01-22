package com.abupdate.mqtt_libs.connect;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/* loaded from: classes.dex */
public class Utils {
    public static boolean isNetWorkAvailable(Context context) {
        NetworkInfo[] allNetworkInfo;
        if (context == null) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (allNetworkInfo = connectivityManager.getAllNetworkInfo()) == null) {
                return false;
            }
            for (int i = 0; i < allNetworkInfo.length && allNetworkInfo[i] != null; i++) {
                if (allNetworkInfo[i].isConnected() && allNetworkInfo[i].isAvailable()) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
