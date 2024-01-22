package androidx.core.view.animation;

import android.graphics.Path;
import android.os.Build;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
/* loaded from: classes.dex */
public final class PathInterpolatorCompat {

    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class a {
        @DoNotInline
        public static PathInterpolator a(float f, float f2) {
            return new PathInterpolator(f, f2);
        }

        @DoNotInline
        public static PathInterpolator b(float f, float f2, float f3, float f4) {
            return new PathInterpolator(f, f2, f3, f4);
        }

        @DoNotInline
        public static PathInterpolator c(Path path) {
            return new PathInterpolator(path);
        }
    }

    @NonNull
    public static Interpolator create(@NonNull Path path) {
        if (Build.VERSION.SDK_INT >= 21) {
            return a.c(path);
        }
        return new androidx.core.view.animation.a(path);
    }

    @NonNull
    public static Interpolator create(float f, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return a.a(f, f2);
        }
        return new androidx.core.view.animation.a(f, f2);
    }

    @NonNull
    public static Interpolator create(float f, float f2, float f3, float f4) {
        if (Build.VERSION.SDK_INT >= 21) {
            return a.b(f, f2, f3, f4);
        }
        return new androidx.core.view.animation.a(f, f2, f3, f4);
    }
}
