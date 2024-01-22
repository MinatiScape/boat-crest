package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import androidx.cardview.widget.g;
/* loaded from: classes.dex */
public class c implements e {

    /* renamed from: a  reason: collision with root package name */
    public final RectF f836a = new RectF();

    /* loaded from: classes.dex */
    public class a implements g.a {
        public a() {
        }

        @Override // androidx.cardview.widget.g.a
        public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
            float f2 = 2.0f * f;
            float width = (rectF.width() - f2) - 1.0f;
            float height = (rectF.height() - f2) - 1.0f;
            if (f >= 1.0f) {
                float f3 = f + 0.5f;
                float f4 = -f3;
                c.this.f836a.set(f4, f4, f3, f3);
                int save = canvas.save();
                canvas.translate(rectF.left + f3, rectF.top + f3);
                canvas.drawArc(c.this.f836a, 180.0f, 90.0f, true, paint);
                canvas.translate(width, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f836a, 180.0f, 90.0f, true, paint);
                canvas.translate(height, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f836a, 180.0f, 90.0f, true, paint);
                canvas.translate(width, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f836a, 180.0f, 90.0f, true, paint);
                canvas.restoreToCount(save);
                float f5 = rectF.top;
                canvas.drawRect((rectF.left + f3) - 1.0f, f5, (rectF.right - f3) + 1.0f, f5 + f3, paint);
                float f6 = rectF.bottom;
                canvas.drawRect((rectF.left + f3) - 1.0f, f6 - f3, (rectF.right - f3) + 1.0f, f6, paint);
            }
            canvas.drawRect(rectF.left, rectF.top + f, rectF.right, rectF.bottom - f, paint);
        }
    }

    @Override // androidx.cardview.widget.e
    public void a(d dVar, float f) {
        q(dVar).p(f);
        k(dVar);
    }

    @Override // androidx.cardview.widget.e
    public float b(d dVar) {
        return q(dVar).g();
    }

    @Override // androidx.cardview.widget.e
    public void c(d dVar, float f) {
        q(dVar).r(f);
    }

    @Override // androidx.cardview.widget.e
    public float d(d dVar) {
        return q(dVar).i();
    }

    @Override // androidx.cardview.widget.e
    public ColorStateList e(d dVar) {
        return q(dVar).f();
    }

    @Override // androidx.cardview.widget.e
    public float f(d dVar) {
        return q(dVar).j();
    }

    @Override // androidx.cardview.widget.e
    public void g(d dVar) {
        q(dVar).m(dVar.e());
        k(dVar);
    }

    @Override // androidx.cardview.widget.e
    public void h(d dVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        g p = p(context, colorStateList, f, f2, f3);
        p.m(dVar.e());
        dVar.a(p);
        k(dVar);
    }

    @Override // androidx.cardview.widget.e
    public float i(d dVar) {
        return q(dVar).l();
    }

    @Override // androidx.cardview.widget.e
    public void j(d dVar) {
    }

    @Override // androidx.cardview.widget.e
    public void k(d dVar) {
        Rect rect = new Rect();
        q(dVar).h(rect);
        dVar.d((int) Math.ceil(m(dVar)), (int) Math.ceil(f(dVar)));
        dVar.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // androidx.cardview.widget.e
    public void l() {
        g.r = new a();
    }

    @Override // androidx.cardview.widget.e
    public float m(d dVar) {
        return q(dVar).k();
    }

    @Override // androidx.cardview.widget.e
    public void n(d dVar, @Nullable ColorStateList colorStateList) {
        q(dVar).o(colorStateList);
    }

    @Override // androidx.cardview.widget.e
    public void o(d dVar, float f) {
        q(dVar).q(f);
        k(dVar);
    }

    public final g p(Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        return new g(context.getResources(), colorStateList, f, f2, f3);
    }

    public final g q(d dVar) {
        return (g) dVar.c();
    }
}
