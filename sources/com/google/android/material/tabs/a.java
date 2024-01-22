package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import com.google.android.material.animation.AnimationUtils;
/* loaded from: classes10.dex */
public class a extends c {
    public static float e(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (float) (1.0d - Math.cos((f * 3.141592653589793d) / 2.0d));
    }

    public static float f(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (float) Math.sin((f * 3.141592653589793d) / 2.0d);
    }

    @Override // com.google.android.material.tabs.c
    public void d(TabLayout tabLayout, View view, View view2, float f, @NonNull Drawable drawable) {
        float f2;
        float e;
        RectF a2 = c.a(tabLayout, view);
        RectF a3 = c.a(tabLayout, view2);
        if (a2.left < a3.left) {
            f2 = e(f);
            e = f(f);
        } else {
            f2 = f(f);
            e = e(f);
        }
        drawable.setBounds(AnimationUtils.lerp((int) a2.left, (int) a3.left, f2), drawable.getBounds().top, AnimationUtils.lerp((int) a2.right, (int) a3.right, e), drawable.getBounds().bottom);
    }
}
