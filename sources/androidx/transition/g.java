package androidx.transition;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class g extends ViewGroup implements d {
    public ViewGroup h;
    public View i;
    public final View j;
    public int k;
    @Nullable
    public Matrix l;
    public final ViewTreeObserver.OnPreDrawListener m;

    /* loaded from: classes.dex */
    public class a implements ViewTreeObserver.OnPreDrawListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            View view;
            ViewCompat.postInvalidateOnAnimation(g.this);
            g gVar = g.this;
            ViewGroup viewGroup = gVar.h;
            if (viewGroup == null || (view = gVar.i) == null) {
                return true;
            }
            viewGroup.endViewTransition(view);
            ViewCompat.postInvalidateOnAnimation(g.this.h);
            g gVar2 = g.this;
            gVar2.h = null;
            gVar2.i = null;
            return true;
        }
    }

    public g(View view) {
        super(view.getContext());
        this.m = new a();
        this.j = view;
        setWillNotDraw(false);
        setLayerType(2, null);
    }

    public static g b(View view, ViewGroup viewGroup, Matrix matrix) {
        e eVar;
        if (view.getParent() instanceof ViewGroup) {
            e b = e.b(viewGroup);
            g e = e(view);
            int i = 0;
            if (e != null && (eVar = (e) e.getParent()) != b) {
                i = e.k;
                eVar.removeView(e);
                e = null;
            }
            if (e == null) {
                if (matrix == null) {
                    matrix = new Matrix();
                    c(view, viewGroup, matrix);
                }
                e = new g(view);
                e.h(matrix);
                if (b == null) {
                    b = new e(viewGroup);
                } else {
                    b.g();
                }
                d(viewGroup, b);
                d(viewGroup, e);
                b.a(e);
                e.k = i;
            } else if (matrix != null) {
                e.h(matrix);
            }
            e.k++;
            return e;
        }
        throw new IllegalArgumentException("Ghosted views must be parented by a ViewGroup");
    }

    public static void c(View view, ViewGroup viewGroup, Matrix matrix) {
        ViewGroup viewGroup2 = (ViewGroup) view.getParent();
        matrix.reset();
        b0.j(viewGroup2, matrix);
        matrix.preTranslate(-viewGroup2.getScrollX(), -viewGroup2.getScrollY());
        b0.k(viewGroup, matrix);
    }

    public static void d(View view, View view2) {
        b0.g(view2, view2.getLeft(), view2.getTop(), view2.getLeft() + view.getWidth(), view2.getTop() + view.getHeight());
    }

    public static g e(View view) {
        return (g) view.getTag(R.id.ghost_view);
    }

    public static void f(View view) {
        g e = e(view);
        if (e != null) {
            int i = e.k - 1;
            e.k = i;
            if (i <= 0) {
                ((e) e.getParent()).removeView(e);
            }
        }
    }

    public static void g(@NonNull View view, @Nullable g gVar) {
        view.setTag(R.id.ghost_view, gVar);
    }

    @Override // androidx.transition.d
    public void a(ViewGroup viewGroup, View view) {
        this.h = viewGroup;
        this.i = view;
    }

    public void h(@NonNull Matrix matrix) {
        this.l = matrix;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        g(this.j, this);
        this.j.getViewTreeObserver().addOnPreDrawListener(this.m);
        b0.i(this.j, 4);
        if (this.j.getParent() != null) {
            ((View) this.j.getParent()).invalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.j.getViewTreeObserver().removeOnPreDrawListener(this.m);
        b0.i(this.j, 0);
        g(this.j, null);
        if (this.j.getParent() != null) {
            ((View) this.j.getParent()).invalidate();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        b.a(canvas, true);
        canvas.setMatrix(this.l);
        b0.i(this.j, 0);
        this.j.invalidate();
        b0.i(this.j, 4);
        drawChild(canvas, this.j, getDrawingTime());
        b.a(canvas, false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View, androidx.transition.d
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (e(this.j) == this) {
            b0.i(this.j, i == 0 ? 4 : 0);
        }
    }
}
