package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
/* loaded from: classes.dex */
public final class GravityCompat {
    public static final int END = 8388613;
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 8388615;
    public static final int RELATIVE_LAYOUT_DIRECTION = 8388608;
    public static final int START = 8388611;

    @RequiresApi(17)
    /* loaded from: classes.dex */
    public static class a {
        @DoNotInline
        public static void a(int i, int i2, int i3, Rect rect, int i4, int i5, Rect rect2, int i6) {
            Gravity.apply(i, i2, i3, rect, i4, i5, rect2, i6);
        }

        @DoNotInline
        public static void b(int i, int i2, int i3, Rect rect, Rect rect2, int i4) {
            Gravity.apply(i, i2, i3, rect, rect2, i4);
        }

        @DoNotInline
        public static void c(int i, Rect rect, Rect rect2, int i2) {
            Gravity.applyDisplay(i, rect, rect2, i2);
        }
    }

    public static void apply(int i, int i2, int i3, @NonNull Rect rect, @NonNull Rect rect2, int i4) {
        if (Build.VERSION.SDK_INT >= 17) {
            a.b(i, i2, i3, rect, rect2, i4);
        } else {
            Gravity.apply(i, i2, i3, rect, rect2);
        }
    }

    public static void applyDisplay(int i, @NonNull Rect rect, @NonNull Rect rect2, int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            a.c(i, rect, rect2, i2);
        } else {
            Gravity.applyDisplay(i, rect, rect2);
        }
    }

    public static int getAbsoluteGravity(int i, int i2) {
        return Build.VERSION.SDK_INT >= 17 ? Gravity.getAbsoluteGravity(i, i2) : i & (-8388609);
    }

    public static void apply(int i, int i2, int i3, @NonNull Rect rect, int i4, int i5, @NonNull Rect rect2, int i6) {
        if (Build.VERSION.SDK_INT >= 17) {
            a.a(i, i2, i3, rect, i4, i5, rect2, i6);
        } else {
            Gravity.apply(i, i2, i3, rect, i4, i5, rect2);
        }
    }
}
