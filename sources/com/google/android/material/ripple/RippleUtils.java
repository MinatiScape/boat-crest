package com.google.android.material.ripple;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.util.StateSet;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.graphics.ColorUtils;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public class RippleUtils {
    public static final boolean USE_FRAMEWORK_RIPPLE;

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f10342a;
    public static final int[] b;
    public static final int[] c;
    public static final int[] d;
    public static final int[] e;
    public static final int[] f;
    public static final int[] g;
    public static final int[] h;
    public static final int[] i;
    public static final int[] j;
    @VisibleForTesting
    public static final String k;

    static {
        USE_FRAMEWORK_RIPPLE = Build.VERSION.SDK_INT >= 21;
        f10342a = new int[]{16842919};
        b = new int[]{16843623, 16842908};
        c = new int[]{16842908};
        d = new int[]{16843623};
        e = new int[]{16842913, 16842919};
        f = new int[]{16842913, 16843623, 16842908};
        g = new int[]{16842913, 16842908};
        h = new int[]{16842913, 16843623};
        i = new int[]{16842913};
        j = new int[]{16842910, 16842919};
        k = RippleUtils.class.getSimpleName();
    }

    @ColorInt
    @TargetApi(21)
    public static int a(@ColorInt int i2) {
        return ColorUtils.setAlphaComponent(i2, Math.min(Color.alpha(i2) * 2, 255));
    }

    @ColorInt
    public static int b(@Nullable ColorStateList colorStateList, int[] iArr) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, colorStateList.getDefaultColor()) : 0;
        return USE_FRAMEWORK_RIPPLE ? a(colorForState) : colorForState;
    }

    @NonNull
    public static ColorStateList convertToRippleDrawableColor(@Nullable ColorStateList colorStateList) {
        if (USE_FRAMEWORK_RIPPLE) {
            return new ColorStateList(new int[][]{i, StateSet.NOTHING}, new int[]{b(colorStateList, e), b(colorStateList, f10342a)});
        }
        int[] iArr = e;
        int[] iArr2 = f;
        int[] iArr3 = g;
        int[] iArr4 = h;
        int[] iArr5 = f10342a;
        int[] iArr6 = b;
        int[] iArr7 = c;
        int[] iArr8 = d;
        return new ColorStateList(new int[][]{iArr, iArr2, iArr3, iArr4, i, iArr5, iArr6, iArr7, iArr8, StateSet.NOTHING}, new int[]{b(colorStateList, iArr), b(colorStateList, iArr2), b(colorStateList, iArr3), b(colorStateList, iArr4), 0, b(colorStateList, iArr5), b(colorStateList, iArr6), b(colorStateList, iArr7), b(colorStateList, iArr8), 0});
    }

    @NonNull
    public static ColorStateList sanitizeRippleDrawableColor(@Nullable ColorStateList colorStateList) {
        if (colorStateList != null) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 22 && i2 <= 27 && Color.alpha(colorStateList.getDefaultColor()) == 0 && Color.alpha(colorStateList.getColorForState(j, 0)) != 0) {
                Log.w(k, "Use a non-transparent color for the default color as it will be used to finish ripple animations.");
            }
            return colorStateList;
        }
        return ColorStateList.valueOf(0);
    }

    public static boolean shouldDrawRippleCompat(@NonNull int[] iArr) {
        boolean z = false;
        boolean z2 = false;
        for (int i2 : iArr) {
            if (i2 == 16842910) {
                z = true;
            } else if (i2 == 16842908 || i2 == 16842919 || i2 == 16843623) {
                z2 = true;
            }
        }
        return z && z2;
    }
}
