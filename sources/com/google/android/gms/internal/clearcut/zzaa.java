package com.google.android.gms.internal.clearcut;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.UserManager;
/* loaded from: classes7.dex */
public class zzaa {

    /* renamed from: a  reason: collision with root package name */
    public static volatile UserManager f8610a;
    public static volatile boolean b = !a();

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 24;
    }

    @TargetApi(24)
    public static boolean b(Context context) {
        boolean z = b;
        if (!z) {
            UserManager userManager = f8610a;
            if (userManager == null) {
                synchronized (zzaa.class) {
                    userManager = f8610a;
                    if (userManager == null) {
                        UserManager userManager2 = (UserManager) context.getSystemService(UserManager.class);
                        f8610a = userManager2;
                        if (userManager2 == null) {
                            b = true;
                            return true;
                        }
                        userManager = userManager2;
                    }
                }
            }
            z = userManager.isUserUnlocked();
            b = z;
            if (z) {
                f8610a = null;
            }
        }
        return z;
    }

    public static boolean zze(Context context) {
        return a() && !b(context);
    }
}
