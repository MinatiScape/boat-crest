package com.google.android.material.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.Window;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.google.android.material.color.MaterialColors;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public class EdgeToEdgeUtils {
    @TargetApi(21)
    public static int a(Context context, boolean z) {
        if (!z || Build.VERSION.SDK_INT >= 27) {
            if (z) {
                return 0;
            }
            return MaterialColors.getColor(context, 16843858, (int) ViewCompat.MEASURED_STATE_MASK);
        }
        return ColorUtils.setAlphaComponent(MaterialColors.getColor(context, 16843858, (int) ViewCompat.MEASURED_STATE_MASK), 128);
    }

    public static void applyEdgeToEdge(@NonNull Window window, boolean z) {
        applyEdgeToEdge(window, z, null, null);
    }

    @TargetApi(21)
    public static int b(Context context, boolean z) {
        if (!z || Build.VERSION.SDK_INT >= 23) {
            if (z) {
                return 0;
            }
            return MaterialColors.getColor(context, 16843857, (int) ViewCompat.MEASURED_STATE_MASK);
        }
        return ColorUtils.setAlphaComponent(MaterialColors.getColor(context, 16843857, (int) ViewCompat.MEASURED_STATE_MASK), 128);
    }

    public static boolean c(int i, boolean z) {
        return MaterialColors.isColorLight(i) || (i == 0 && z);
    }

    public static void applyEdgeToEdge(@NonNull Window window, boolean z, @Nullable @ColorInt Integer num, @Nullable @ColorInt Integer num2) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        boolean z2 = false;
        boolean z3 = num == null || num.intValue() == 0;
        if (num2 == null || num2.intValue() == 0) {
            z2 = true;
        }
        if (z3 || z2) {
            int color = MaterialColors.getColor(window.getContext(), 16842801, (int) ViewCompat.MEASURED_STATE_MASK);
            if (z3) {
                num = Integer.valueOf(color);
            }
            if (z2) {
                num2 = Integer.valueOf(color);
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, !z);
        int b = b(window.getContext(), z);
        int a2 = a(window.getContext(), z);
        window.setStatusBarColor(b);
        window.setNavigationBarColor(a2);
        boolean c = c(b, MaterialColors.isColorLight(num.intValue()));
        boolean c2 = c(a2, MaterialColors.isColorLight(num2.intValue()));
        WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(window, window.getDecorView());
        if (insetsController != null) {
            insetsController.setAppearanceLightStatusBars(c);
            insetsController.setAppearanceLightNavigationBars(c2);
        }
    }
}
