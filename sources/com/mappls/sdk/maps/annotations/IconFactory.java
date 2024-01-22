package com.mappls.sdk.maps.annotations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.MapStrictMode;
import com.mappls.sdk.maps.R;
import com.mappls.sdk.maps.exceptions.TooManyIconsException;
import com.mappls.sdk.maps.utils.BitmapUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
@Deprecated
/* loaded from: classes11.dex */
public final class IconFactory {
    @SuppressLint({"StaticFieldLeak"})
    public static IconFactory e;

    /* renamed from: a  reason: collision with root package name */
    public Context f12667a;
    public Icon b;
    public BitmapFactory.Options c;
    public int d = 0;

    public IconFactory(@NonNull Context context) {
        DisplayMetrics displayMetrics;
        this.f12667a = context;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 17) {
            displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            displayMetrics = null;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics2);
        BitmapFactory.Options options = new BitmapFactory.Options();
        this.c = options;
        options.inScaled = true;
        options.inDensity = 160;
        options.inTargetDensity = displayMetrics2.densityDpi;
        if (displayMetrics != null) {
            options.inScreenDensity = displayMetrics.densityDpi;
        }
    }

    public static synchronized IconFactory getInstance(@NonNull Context context) {
        IconFactory iconFactory;
        synchronized (IconFactory.class) {
            if (e == null) {
                e = new IconFactory(context.getApplicationContext());
            }
            iconFactory = e;
        }
        return iconFactory;
    }

    public static Icon recreate(@NonNull String str, @NonNull Bitmap bitmap) {
        return new Icon(str, bitmap);
    }

    public final Icon a(@NonNull InputStream inputStream) {
        return fromBitmap(BitmapFactory.decodeStream(inputStream, null, this.c));
    }

    public Icon defaultMarker() {
        if (this.b == null) {
            this.b = fromResource(R.drawable.mappls_maps_marker_icon_default);
        }
        return this.b;
    }

    public Icon fromAsset(@NonNull String str) {
        try {
            return a(this.f12667a.getAssets().open(str));
        } catch (IOException e2) {
            MapStrictMode.strictModeViolation(e2);
            return null;
        }
    }

    public Icon fromBitmap(@NonNull Bitmap bitmap) {
        if (this.d >= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("com.mappls.icons.icon_");
            int i = this.d + 1;
            this.d = i;
            sb.append(i);
            return new Icon(sb.toString(), bitmap);
        }
        throw new TooManyIconsException();
    }

    public Icon fromFile(@NonNull String str) {
        try {
            return a(this.f12667a.openFileInput(str));
        } catch (FileNotFoundException e2) {
            MapStrictMode.strictModeViolation(e2);
            return null;
        }
    }

    public Icon fromPath(@NonNull String str) {
        return fromBitmap(BitmapFactory.decodeFile(str, this.c));
    }

    public Icon fromResource(@DrawableRes int i) {
        Drawable drawableFromRes = BitmapUtils.getDrawableFromRes(this.f12667a, i);
        if (drawableFromRes instanceof BitmapDrawable) {
            return fromBitmap(((BitmapDrawable) drawableFromRes).getBitmap());
        }
        throw new IllegalArgumentException("Failed to decode image. The resource provided must be a Bitmap.");
    }
}
