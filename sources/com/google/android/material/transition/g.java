package com.google.android.material.transition;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.transition.MaterialContainerTransform;
/* loaded from: classes10.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    public final Path f10413a = new Path();
    public final Path b = new Path();
    public final Path c = new Path();
    public final ShapeAppearancePathProvider d = ShapeAppearancePathProvider.getInstance();
    public ShapeAppearanceModel e;

    public void a(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= 23) {
            canvas.clipPath(this.f10413a);
            return;
        }
        canvas.clipPath(this.b);
        canvas.clipPath(this.c, Region.Op.UNION);
    }

    public void b(float f, ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, RectF rectF, RectF rectF2, RectF rectF3, MaterialContainerTransform.ProgressThresholds progressThresholds) {
        ShapeAppearanceModel n = j.n(shapeAppearanceModel, shapeAppearanceModel2, rectF, rectF3, progressThresholds.getStart(), progressThresholds.getEnd(), f);
        this.e = n;
        this.d.calculatePath(n, 1.0f, rectF2, this.b);
        this.d.calculatePath(this.e, 1.0f, rectF3, this.c);
        if (Build.VERSION.SDK_INT >= 23) {
            this.f10413a.op(this.b, this.c, Path.Op.UNION);
        }
    }

    public ShapeAppearanceModel c() {
        return this.e;
    }

    public Path d() {
        return this.f10413a;
    }
}
