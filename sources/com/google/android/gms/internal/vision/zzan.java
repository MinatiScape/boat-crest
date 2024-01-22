package com.google.android.gms.internal.vision;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.RequiresApi;
/* loaded from: classes10.dex */
public class zzan {
    @GuardedBy("DirectBootUtils.class")

    /* renamed from: a  reason: collision with root package name */
    public static UserManager f10008a;
    public static volatile boolean b = !zzs();

    @RequiresApi(24)
    @TargetApi(24)
    @GuardedBy("DirectBootUtils.class")
    public static boolean a(Context context) {
        boolean z;
        boolean z2 = true;
        int i = 1;
        while (true) {
            z = false;
            if (i > 2) {
                break;
            }
            if (f10008a == null) {
                f10008a = (UserManager) context.getSystemService(UserManager.class);
            }
            UserManager userManager = f10008a;
            if (userManager == null) {
                return true;
            }
            try {
                if (userManager.isUserUnlocked()) {
                    break;
                } else if (userManager.isUserRunning(Process.myUserHandle())) {
                    z2 = false;
                }
            } catch (NullPointerException e) {
                Log.w("DirectBootUtils", "Failed to check if user is unlocked.", e);
                f10008a = null;
                i++;
            }
        }
        z = z2;
        if (z) {
            f10008a = null;
        }
        return z;
    }

    @RequiresApi(24)
    @TargetApi(24)
    public static boolean b(Context context) {
        if (b) {
            return true;
        }
        synchronized (zzan.class) {
            if (b) {
                return true;
            }
            boolean a2 = a(context);
            if (a2) {
                b = a2;
            }
            return a2;
        }
    }

    public static boolean isUserUnlocked(Context context) {
        return !zzs() || b(context);
    }

    public static boolean zzs() {
        return Build.VERSION.SDK_INT >= 24;
    }
}
