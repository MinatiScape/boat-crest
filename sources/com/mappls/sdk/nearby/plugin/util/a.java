package com.mappls.sdk.nearby.plugin.util;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class a {
    public static void a(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        if (fragment.shouldShowRequestPermissionRationale("android.permission.ACCESS_COARSE_LOCATION") || fragment.shouldShowRequestPermissionRationale("android.permission.ACCESS_FINE_LOCATION")) {
            fragment.requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, 0);
        } else {
            fragment.requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, 0);
        }
    }

    public static boolean a(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            return true;
        }
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }
}
