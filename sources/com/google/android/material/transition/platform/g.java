package com.google.android.material.transition.platform;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.transition.platform.MaterialContainerTransform;
@RequiresApi(21)
/* loaded from: classes10.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    public final Path f10435a = new Path();
    public final Path b = new Path();
    public final Path c = new Path();
    public final ShapeAppearancePathProvider d = ShapeAppearancePathProvider.getInstance();
    public ShapeAppearanceModel e;

    public void a(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= 23) {
            canvas.clipPath(this.f10435a);
            return;
        }
        canvas.clipPath(this.b);
        canvas.clipPath(this.c, Region.Op.UNION);
    }

    public void b(float f, ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, RectF rectF, RectF rectF2, RectF rectF3, MaterialContainerTransform.ProgressThresholds progressThresholds) {
        ShapeAppearanceModel o = j.o(shapeAppearanceModel, shapeAppearanceModel2, rectF, rectF3, progressThresholds.getStart(), progressThresholds.getEnd(), f);
        this.e = o;
        this.d.calculatePath(o, 1.0f, rectF2, this.b);
        this.d.calculatePath(this.e, 1.0f, rectF3, this.c);
        if (Build.VERSION.SDK_INT >= 23) {
            this.f10435a.op(this.b, this.c, Path.Op.UNION);
        }
    }

    public ShapeAppearanceModel c() {
        return this.e;
    }

    public Path d() {
        return this.f10435a;
    }
}
