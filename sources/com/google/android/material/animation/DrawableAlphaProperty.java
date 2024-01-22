package com.google.android.material.animation;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Property;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.WeakHashMap;
/* loaded from: classes10.dex */
public class DrawableAlphaProperty extends Property<Drawable, Integer> {
    public static final Property<Drawable, Integer> DRAWABLE_ALPHA_COMPAT = new DrawableAlphaProperty();

    /* renamed from: a  reason: collision with root package name */
    public final WeakHashMap<Drawable, Integer> f10204a;

    public DrawableAlphaProperty() {
        super(Integer.class, "drawableAlphaCompat");
        this.f10204a = new WeakHashMap<>();
    }

    @Override // android.util.Property
    @Nullable
    public Integer get(@NonNull Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 19) {
            return Integer.valueOf(drawable.getAlpha());
        }
        if (this.f10204a.containsKey(drawable)) {
            return this.f10204a.get(drawable);
        }
        return 255;
    }

    @Override // android.util.Property
    public void set(@NonNull Drawable drawable, @NonNull Integer num) {
        if (Build.VERSION.SDK_INT < 19) {
            this.f10204a.put(drawable, num);
        }
        drawable.setAlpha(num.intValue());
    }
}
