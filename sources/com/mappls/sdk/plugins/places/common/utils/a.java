package com.mappls.sdk.plugins.places.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/* loaded from: classes10.dex */
public final class a {
    public static boolean a(Context context) {
        NetworkInfo networkInfo;
        try {
            networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            networkInfo = null;
        }
        return networkInfo != null && networkInfo.isConnected();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(android.content.Context r3) {
        /*
            java.lang.String r0 = "connectivity"
            java.lang.Object r3 = r3.getSystemService(r0)     // Catch: java.lang.Exception -> Ld
            android.net.ConnectivityManager r3 = (android.net.ConnectivityManager) r3     // Catch: java.lang.Exception -> Ld
            android.net.NetworkInfo r3 = r3.getActiveNetworkInfo()     // Catch: java.lang.Exception -> Ld
            goto Le
        Ld:
            r3 = 0
        Le:
            r0 = 0
            r1 = 1
            if (r3 == 0) goto L2f
            boolean r2 = r3.isConnected()
            if (r2 == 0) goto L2f
            int r2 = r3.getType()
            int r3 = r3.getSubtype()
            if (r2 != r1) goto L23
            goto L29
        L23:
            if (r2 != 0) goto L2b
            switch(r3) {
                case 3: goto L29;
                case 4: goto L28;
                case 5: goto L29;
                case 6: goto L29;
                case 7: goto L28;
                case 8: goto L29;
                case 9: goto L29;
                case 10: goto L29;
                case 11: goto L28;
                case 12: goto L29;
                case 13: goto L29;
                case 14: goto L29;
                case 15: goto L29;
                default: goto L28;
            }
        L28:
            goto L2b
        L29:
            r3 = r1
            goto L2c
        L2b:
            r3 = r0
        L2c:
            if (r3 == 0) goto L2f
            r0 = r1
        L2f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.plugins.places.common.utils.a.b(android.content.Context):boolean");
    }
}
