package com.google.android.material.transition;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.transition.PathMotion;
/* loaded from: classes10.dex */
public final class MaterialArcMotion extends PathMotion {
    public static PointF a(float f, float f2, float f3, float f4) {
        if (f2 > f4) {
            return new PointF(f3, f2);
        }
        return new PointF(f, f4);
    }

    @Override // androidx.transition.PathMotion
    @NonNull
    public Path getPath(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(f, f2);
        PointF a2 = a(f, f2, f3, f4);
        path.quadTo(a2.x, a2.y, f3, f4);
        return path;
    }
}