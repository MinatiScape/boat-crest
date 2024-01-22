package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
@RequiresApi(21)
/* loaded from: classes.dex */
public class b implements e {
    @Override // androidx.cardview.widget.e
    public void a(d dVar, float f) {
        p(dVar).h(f);
    }

    @Override // androidx.cardview.widget.e
    public float b(d dVar) {
        return p(dVar).d();
    }

    @Override // androidx.cardview.widget.e
    public void c(d dVar, float f) {
        dVar.f().setElevation(f);
    }

    @Override // androidx.cardview.widget.e
    public float d(d dVar) {
        return p(dVar).c();
    }

    @Override // androidx.cardview.widget.e
    public ColorStateList e(d dVar) {
        return p(dVar).b();
    }

    @Override // androidx.cardview.widget.e
    public float f(d dVar) {
        return b(dVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.e
    public void g(d dVar) {
        o(dVar, d(dVar));
    }

    @Override // androidx.cardview.widget.e
    public void h(d dVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        dVar.a(new f(colorStateList, f));
        View f4 = dVar.f();
        f4.setClipToOutline(true);
        f4.setElevation(f2);
        o(dVar, f3);
    }

    @Override // androidx.cardview.widget.e
    public float i(d dVar) {
        return dVar.f().getElevation();
    }

    @Override // androidx.cardview.widget.e
    public void j(d dVar) {
        o(dVar, d(dVar));
    }

    @Override // androidx.cardview.widget.e
    public void k(d dVar) {
        if (!dVar.b()) {
            dVar.setShadowPadding(0, 0, 0, 0);
            return;
        }
        float d = d(dVar);
        float b = b(dVar);
        int ceil = (int) Math.ceil(g.c(d, b, dVar.e()));
        int ceil2 = (int) Math.ceil(g.d(d, b, dVar.e()));
        dVar.setShadowPadding(ceil, ceil2, ceil, ceil2);
    }

    @Override // androidx.cardview.widget.e
    public void l() {
    }

    @Override // androidx.cardview.widget.e
    public float m(d dVar) {
        return b(dVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.e
    public void n(d dVar, @Nullable ColorStateList colorStateList) {
        p(dVar).f(colorStateList);
    }

    @Override // androidx.cardview.widget.e
    public void o(d dVar, float f) {
        p(dVar).g(f, dVar.b(), dVar.e());
        k(dVar);
    }

    public final f p(d dVar) {
        return (f) dVar.c();
    }
}
