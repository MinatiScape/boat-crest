package com.google.android.libraries.places.internal;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.ColorInt;
/* loaded from: classes10.dex */
public final class zzej {
    @ColorInt
    public static int zza(@ColorInt int i, @ColorInt int i2, @ColorInt int i3) {
        return zzb(i, i2, i3) ? i3 : i2;
    }

    public static boolean zzb(@ColorInt int i, @ColorInt int i2, @ColorInt int i3) {
        double zza = zza(i);
        double zza2 = zza(zza(i2), zza);
        return zza2 <= 3.0d && zza2 <= zza(zza(i3), zza);
    }

    public static void zza(ImageView imageView, @ColorInt int i) {
        Drawable drawable = imageView.getDrawable();
        int rgb = Color.rgb(Color.red(i), Color.green(i), Color.blue(i));
        Drawable mutate = drawable.mutate();
        mutate.setColorFilter(rgb, PorterDuff.Mode.SRC_ATOP);
        mutate.setAlpha(Color.alpha(i));
    }

    private static double zza(double d, double d2) {
        return Math.round(((Math.max(d, d2) + 0.05d) / (Math.min(d, d2) + 0.05d)) * 100.0d) / 100.0d;
    }

    private static double zza(@ColorInt int i) {
        return (zza(Color.red(i) / 255.0d) * 0.2126d) + (zza(Color.green(i) / 255.0d) * 0.7152d) + (zza(Color.blue(i) / 255.0d) * 0.0722d);
    }

    private static double zza(double d) {
        return d <= 0.03928d ? d / 12.92d : Math.pow((d + 0.055d) / 1.055d, 2.4d);
    }
}
