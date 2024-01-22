package com.mappls.sdk.maps.location;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.location.Location;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Projection;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.log.Logger;
/* loaded from: classes11.dex */
public final class Utils {
    public static float a(@NonNull MapplsMap mapplsMap, @Nullable Location location) {
        if (location == null) {
            return 0.0f;
        }
        return (float) (location.getAccuracy() * (1.0d / mapplsMap.getProjection().getMetersPerPixelAtLatitude(location.getLatitude())));
    }

    public static void b(Drawable drawable) {
        if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable).setGradientRadius(1.0f);
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            for (int i = 0; i < layerDrawable.getNumberOfLayers(); i++) {
                Drawable drawable2 = layerDrawable.getDrawable(i);
                if (drawable2 instanceof GradientDrawable) {
                    ((GradientDrawable) drawable2).setGradientRadius(1.0f);
                }
            }
        }
    }

    public static Bitmap c(Drawable drawable, float f) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        try {
            drawable.draw(canvas);
            return Bitmap.createScaledBitmap(createBitmap, e(intrinsicWidth + f), e(intrinsicHeight + f), false);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("radius must be > 0") && Build.VERSION.SDK_INT < 21) {
                Logger.w("Mbgl-com.mappls.sdk.maps.location.Utils", "Location's shadow gradient drawable has a radius <= 0px, resetting to 1px in order to avoid crashing");
                b(drawable);
                return c(drawable, f);
            }
            throw e;
        }
    }

    public static boolean d(@NonNull Projection projection, @NonNull LatLng latLng, @NonNull LatLng latLng2) {
        return latLng.distanceTo(latLng2) / projection.getMetersPerPixelAtLatitude((latLng.getLatitude() + latLng2.getLatitude()) / 2.0d) > 50000.0d;
    }

    public static int e(float f) {
        int i = (int) (f + 0.5f);
        return i % 2 == 1 ? i - 1 : i;
    }

    public static float normalize(float f) {
        return ((f % 360.0f) + 360.0f) % 360.0f;
    }

    public static float shortestRotation(float f, float f2) {
        double d = f2 - f;
        return d > 180.0d ? f + 360.0f : d < -180.0d ? f - 360.0f : f;
    }
}
