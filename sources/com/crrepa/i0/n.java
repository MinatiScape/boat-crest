package com.crrepa.i0;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/* loaded from: classes9.dex */
public class n {
    public static boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) f.a().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isConnected();
        }
        return false;
    }
}
