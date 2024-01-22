package com.mappls.sdk.maps.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class BitmapUtils {
    public static Bitmap createBitmapFromView(@NonNull View view) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(524288);
        view.buildDrawingCache();
        if (view.getDrawingCache() == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
        return createBitmap;
    }

    @VisibleForTesting
    public static boolean equals(Bitmap bitmap, Bitmap bitmap2) {
        ByteBuffer allocate = ByteBuffer.allocate(bitmap.getHeight() * bitmap.getRowBytes());
        bitmap.copyPixelsToBuffer(allocate);
        ByteBuffer allocate2 = ByteBuffer.allocate(bitmap2.getHeight() * bitmap2.getRowBytes());
        bitmap2.copyPixelsToBuffer(allocate2);
        return Arrays.equals(allocate.array(), allocate2.array());
    }

    @Nullable
    public static Bitmap getBitmapFromDrawable(@Nullable Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return null;
        }
        Drawable mutate = constantState.newDrawable().mutate();
        Bitmap createBitmap = Bitmap.createBitmap(mutate.getIntrinsicWidth(), mutate.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        mutate.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        mutate.draw(canvas);
        return createBitmap;
    }

    @Nullable
    public static byte[] getByteArrayFromDrawable(@Nullable Drawable drawable) {
        Bitmap bitmapFromDrawable;
        if (drawable == null || (bitmapFromDrawable = getBitmapFromDrawable(drawable)) == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapFromDrawable.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Nullable
    public static Drawable getDrawableFromByteArray(@NonNull Context context, @Nullable byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
    }

    @Nullable
    public static Drawable getDrawableFromRes(@NonNull Context context, @DrawableRes int i) {
        return getDrawableFromRes(context, i, null);
    }

    public static Bitmap mergeBitmap(@NonNull Bitmap bitmap, @NonNull Bitmap bitmap2) {
        return mergeBitmap(bitmap, bitmap2, 10.0f, 10.0f);
    }

    @Nullable
    public static Drawable getDrawableFromRes(@NonNull Context context, @DrawableRes int i, @Nullable @ColorInt Integer num) {
        Drawable drawable;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            drawable = context.getDrawable(i);
        } else {
            drawable = context.getResources().getDrawable(i);
        }
        if (drawable == null) {
            return null;
        }
        if (num == null) {
            return drawable;
        }
        if (i2 >= 21) {
            drawable.setTint(num.intValue());
        } else {
            drawable.mutate().setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
        return drawable;
    }

    public static Bitmap mergeBitmap(@NonNull Bitmap bitmap, @NonNull Bitmap bitmap2, float f, float f2) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(bitmap2, f, f2, (Paint) null);
        return createBitmap;
    }
}
