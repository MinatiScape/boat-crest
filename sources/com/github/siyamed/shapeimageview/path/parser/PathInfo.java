package com.github.siyamed.shapeimageview.path.parser;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
/* loaded from: classes9.dex */
public class PathInfo {

    /* renamed from: a  reason: collision with root package name */
    public final float f7968a;
    public final float b;
    public final Path c;

    public PathInfo(Path path, float f, float f2) {
        this.c = path;
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        if (f <= 0.0f && f2 <= 0.0f) {
            f = (float) Math.ceil(rectF.width());
            f2 = (float) Math.ceil(rectF.height());
            path.offset(((float) Math.floor(rectF.left)) * (-1.0f), Math.round(rectF.top) * (-1.0f));
        }
        this.f7968a = f;
        this.b = f2;
    }

    public float getHeight() {
        return this.b;
    }

    public float getWidth() {
        return this.f7968a;
    }

    public void transform(Matrix matrix, Path path) {
        this.c.transform(matrix, path);
    }
}
