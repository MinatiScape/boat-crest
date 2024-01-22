package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import com.google.android.material.color.MaterialColors;
/* loaded from: classes10.dex */
public final class a extends d<CircularProgressIndicatorSpec> {
    public int c;
    public float d;
    public float e;
    public float f;

    public a(@NonNull CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(circularProgressIndicatorSpec);
        this.c = 1;
    }

    @Override // com.google.android.material.progressindicator.d
    public void a(@NonNull Canvas canvas, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        S s;
        S s2 = this.f10334a;
        float f2 = (((CircularProgressIndicatorSpec) s2).indicatorSize / 2.0f) + ((CircularProgressIndicatorSpec) s2).indicatorInset;
        canvas.translate(f2, f2);
        canvas.rotate(-90.0f);
        float f3 = -f2;
        canvas.clipRect(f3, f3, f2, f2);
        this.c = ((CircularProgressIndicatorSpec) this.f10334a).indicatorDirection == 0 ? 1 : -1;
        this.d = ((CircularProgressIndicatorSpec) s).trackThickness * f;
        this.e = ((CircularProgressIndicatorSpec) s).trackCornerRadius * f;
        this.f = (((CircularProgressIndicatorSpec) s).indicatorSize - ((CircularProgressIndicatorSpec) s).trackThickness) / 2.0f;
        if ((this.b.isShowing() && ((CircularProgressIndicatorSpec) this.f10334a).showAnimationBehavior == 2) || (this.b.isHiding() && ((CircularProgressIndicatorSpec) this.f10334a).hideAnimationBehavior == 1)) {
            this.f += ((1.0f - f) * ((CircularProgressIndicatorSpec) this.f10334a).trackThickness) / 2.0f;
        } else if ((this.b.isShowing() && ((CircularProgressIndicatorSpec) this.f10334a).showAnimationBehavior == 1) || (this.b.isHiding() && ((CircularProgressIndicatorSpec) this.f10334a).hideAnimationBehavior == 2)) {
            this.f -= ((1.0f - f) * ((CircularProgressIndicatorSpec) this.f10334a).trackThickness) / 2.0f;
        }
    }

    @Override // com.google.android.material.progressindicator.d
    public void b(@NonNull Canvas canvas, @NonNull Paint paint, @FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2, @ColorInt int i) {
        if (f == f2) {
            return;
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(i);
        paint.setStrokeWidth(this.d);
        int i2 = this.c;
        float f3 = f * 360.0f * i2;
        float f4 = (f2 >= f ? f2 - f : (1.0f + f2) - f) * 360.0f * i2;
        float f5 = this.f;
        canvas.drawArc(new RectF(-f5, -f5, f5, f5), f3, f4, false, paint);
        if (this.e <= 0.0f || Math.abs(f4) >= 360.0f) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        h(canvas, paint, this.d, this.e, f3);
        h(canvas, paint, this.d, this.e, f3 + f4);
    }

    @Override // com.google.android.material.progressindicator.d
    public void c(@NonNull Canvas canvas, @NonNull Paint paint) {
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(((CircularProgressIndicatorSpec) this.f10334a).trackColor, this.b.getAlpha());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(compositeARGBWithAlpha);
        paint.setStrokeWidth(this.d);
        float f = this.f;
        canvas.drawArc(new RectF(-f, -f, f, f), 0.0f, 360.0f, false, paint);
    }

    @Override // com.google.android.material.progressindicator.d
    public int d() {
        return i();
    }

    @Override // com.google.android.material.progressindicator.d
    public int e() {
        return i();
    }

    public final void h(Canvas canvas, Paint paint, float f, float f2, float f3) {
        canvas.save();
        canvas.rotate(f3);
        float f4 = this.f;
        float f5 = f / 2.0f;
        canvas.drawRoundRect(new RectF(f4 - f5, f2, f4 + f5, -f2), f2, f2, paint);
        canvas.restore();
    }

    public final int i() {
        S s = this.f10334a;
        return ((CircularProgressIndicatorSpec) s).indicatorSize + (((CircularProgressIndicatorSpec) s).indicatorInset * 2);
    }
}
