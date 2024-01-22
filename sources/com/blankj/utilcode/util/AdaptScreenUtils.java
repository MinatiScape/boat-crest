package com.blankj.utilcode.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class AdaptScreenUtils {

    /* renamed from: a  reason: collision with root package name */
    public static List<Field> f2230a;

    /* loaded from: classes.dex */
    public static class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            AdaptScreenUtils.h();
        }
    }

    public AdaptScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @NonNull
    public static Resources adaptHeight(@NonNull Resources resources, int i) {
        Objects.requireNonNull(resources, "Argument 'resources' of type Resources (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Resources adaptHeight = adaptHeight(resources, i, false);
        Objects.requireNonNull(adaptHeight, "Detected an attempt to return null from a method com.blankj.utilcode.util.AdaptScreenUtils.adaptHeight() marked by @androidx.annotation.NonNull");
        return adaptHeight;
    }

    @NonNull
    public static Resources adaptWidth(@NonNull Resources resources, int i) {
        Objects.requireNonNull(resources, "Argument 'resources' of type Resources (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        b(resources, (resources.getDisplayMetrics().widthPixels * 72.0f) / i);
        return resources;
    }

    public static void b(@NonNull Resources resources, float f) {
        Objects.requireNonNull(resources, "Argument 'resources' of type Resources (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        resources.getDisplayMetrics().xdpi = f;
        Utils.getApp().getResources().getDisplayMetrics().xdpi = f;
        d(resources, f);
    }

    public static void c(Resources resources, float f) {
        for (Field field : f2230a) {
            try {
                DisplayMetrics displayMetrics = (DisplayMetrics) field.get(resources);
                if (displayMetrics != null) {
                    displayMetrics.xdpi = f;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    public static Resources closeAdapt(@NonNull Resources resources) {
        Objects.requireNonNull(resources, "Argument 'resources' of type Resources (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        b(resources, Resources.getSystem().getDisplayMetrics().density * 72.0f);
        return resources;
    }

    public static void d(Resources resources, float f) {
        if (f2230a == null) {
            f2230a = new ArrayList();
            Class<?> cls = resources.getClass();
            Field[] declaredFields = cls.getDeclaredFields();
            while (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.getType().isAssignableFrom(DisplayMetrics.class)) {
                        field.setAccessible(true);
                        DisplayMetrics e = e(resources, field);
                        if (e != null) {
                            f2230a.add(field);
                            e.xdpi = f;
                        }
                    }
                }
                cls = cls.getSuperclass();
                if (cls == null) {
                    return;
                }
                declaredFields = cls.getDeclaredFields();
            }
            return;
        }
        c(resources, f);
    }

    public static DisplayMetrics e(Resources resources, Field field) {
        try {
            return (DisplayMetrics) field.get(resources);
        } catch (Exception unused) {
            return null;
        }
    }

    public static int f(@NonNull Resources resources) {
        Objects.requireNonNull(resources, "Argument 'resources' of type Resources (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", Constants.KEY_ANDROID);
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static Runnable g() {
        return new a();
    }

    public static void h() {
        b(Resources.getSystem(), Resources.getSystem().getDisplayMetrics().xdpi);
    }

    public static int pt2Px(float f) {
        return (int) (((f * Utils.getApp().getResources().getDisplayMetrics().xdpi) / 72.0f) + 0.5d);
    }

    public static int px2Pt(float f) {
        return (int) (((f * 72.0f) / Utils.getApp().getResources().getDisplayMetrics().xdpi) + 0.5d);
    }

    @NonNull
    public static Resources adaptHeight(@NonNull Resources resources, int i, boolean z) {
        Objects.requireNonNull(resources, "Argument 'resources' of type Resources (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        b(resources, ((resources.getDisplayMetrics().heightPixels + (z ? f(resources) : 0)) * 72.0f) / i);
        return resources;
    }
}
