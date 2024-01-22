package com.google.android.gms.internal.base;

import android.os.Build;
import androidx.annotation.ChecksSdkIntAtLeast;
/* loaded from: classes6.dex */
public final class d {
    @ChecksSdkIntAtLeast(api = 33, codename = "Tiramisu")
    public static boolean a() {
        return Build.VERSION.SDK_INT >= 33 || Build.VERSION.CODENAME.charAt(0) == 'T';
    }
}
