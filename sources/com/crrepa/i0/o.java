package com.crrepa.i0;

import android.graphics.Color;
import androidx.annotation.ColorInt;
/* loaded from: classes9.dex */
public class o {
    public static int a(@ColorInt int i) {
        int b = (b(Color.red(i), 5) << 11) + (b(Color.green(i), 6) << 5) + b(Color.blue(i), 5);
        return b == 2081 ? b + 1 : b;
    }

    public static int b(int i, int i2) {
        return (i >> (8 - i2)) & 255;
    }
}
