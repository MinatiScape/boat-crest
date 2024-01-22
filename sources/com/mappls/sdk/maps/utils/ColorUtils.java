package com.mappls.sdk.maps.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.TypedValue;
import android.widget.ImageView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.widget.ImageViewCompat;
import com.mappls.sdk.maps.R;
import com.mappls.sdk.maps.exceptions.ConversionException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes11.dex */
public class ColorUtils {
    public static int a(@NonNull Context context, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getResources().getColor(i, context.getTheme());
        }
        return context.getResources().getColor(i);
    }

    public static float[] colorToGlRgbaArray(@ColorInt int i) {
        return new float[]{((i >> 16) & 255) / 255.0f, ((i >> 8) & 255) / 255.0f, (i & 255) / 255.0f, ((i >> 24) & 255) / 255.0f};
    }

    public static float[] colorToRgbaArray(@ColorInt int i) {
        return new float[]{(i >> 16) & 255, (i >> 8) & 255, i & 255, ((i >> 24) & 255) / 255.0f};
    }

    public static String colorToRgbaString(@ColorInt int i) {
        Locale locale = Locale.US;
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern("#.###");
        return String.format(locale, "rgba(%d, %d, %d, %s)", Integer.valueOf((i >> 16) & 255), Integer.valueOf((i >> 8) & 255), Integer.valueOf(i & 255), decimalFormat.format(((i >> 24) & 255) / 255.0f));
    }

    @ColorInt
    public static int getAccentColor(@NonNull Context context) {
        try {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(context.getResources().getIdentifier("colorAccent", "attrs", context.getPackageName()), typedValue, true);
            return typedValue.data;
        } catch (Exception unused) {
            return a(context, R.color.mappls_maps_gray);
        }
    }

    @ColorInt
    public static int getPrimaryColor(@NonNull Context context) {
        try {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(context.getResources().getIdentifier("colorPrimary", "attrs", context.getPackageName()), typedValue, true);
            return typedValue.data;
        } catch (Exception unused) {
            return a(context, R.color.mappls_maps_blue);
        }
    }

    @ColorInt
    public static int getPrimaryDarkColor(@NonNull Context context) {
        try {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(context.getResources().getIdentifier("colorPrimaryDark", "attrs", context.getPackageName()), typedValue, true);
            return typedValue.data;
        } catch (Exception unused) {
            return a(context, R.color.mappls_maps_blue);
        }
    }

    @NonNull
    public static ColorStateList getSelector(@ColorInt int i) {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[0]}, new int[]{i, i});
    }

    @ColorInt
    public static int rgbaToColor(@NonNull String str) {
        Matcher matcher = Pattern.compile("rgba?\\s*\\(\\s*(\\d+\\.?\\d*)\\s*,\\s*(\\d+\\.?\\d*)\\s*,\\s*(\\d+\\.?\\d*)\\s*,?\\s*(\\d+\\.?\\d*)?\\s*\\)").matcher(str);
        if (matcher.matches() && matcher.groupCount() == 3) {
            return Color.rgb((int) Float.parseFloat(matcher.group(1)), (int) Float.parseFloat(matcher.group(2)), (int) Float.parseFloat(matcher.group(3)));
        }
        if (matcher.matches() && matcher.groupCount() == 4) {
            return Color.argb((int) (Float.parseFloat(matcher.group(4)) * 255.0f), (int) Float.parseFloat(matcher.group(1)), (int) Float.parseFloat(matcher.group(2)), (int) Float.parseFloat(matcher.group(3)));
        }
        throw new ConversionException("Not a valid rgb/rgba value");
    }

    public static void setTintList(@NonNull ImageView imageView, @ColorInt int i) {
        ImageViewCompat.setImageTintList(imageView, getSelector(i));
    }
}
