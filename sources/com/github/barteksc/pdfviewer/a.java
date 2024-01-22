package com.github.barteksc.pdfviewer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.animation.DecelerateInterpolator;
import android.widget.OverScroller;
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public PDFView f7908a;
    public ValueAnimator b;
    public OverScroller c;
    public boolean d = false;
    public boolean e = false;

    /* renamed from: com.github.barteksc.pdfviewer.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public class C0365a extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        public C0365a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            a.this.f7908a.loadPages();
            a.this.e = false;
            a.this.e();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a.this.f7908a.loadPages();
            a.this.e = false;
            a.this.e();
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            a.this.f7908a.moveTo(((Float) valueAnimator.getAnimatedValue()).floatValue(), a.this.f7908a.getCurrentYOffset());
            a.this.f7908a.w();
        }
    }

    /* loaded from: classes9.dex */
    public class b extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            a.this.f7908a.loadPages();
            a.this.e = false;
            a.this.e();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a.this.f7908a.loadPages();
            a.this.e = false;
            a.this.e();
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            a.this.f7908a.moveTo(a.this.f7908a.getCurrentXOffset(), ((Float) valueAnimator.getAnimatedValue()).floatValue());
            a.this.f7908a.w();
        }
    }

    /* loaded from: classes9.dex */
    public class c implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
        public final float h;
        public final float i;

        public c(float f, float f2) {
            this.h = f;
            this.i = f2;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            a.this.f7908a.loadPages();
            a.this.e();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a.this.f7908a.loadPages();
            a.this.f7908a.performPageSnap();
            a.this.e();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            a.this.f7908a.zoomCenteredTo(((Float) valueAnimator.getAnimatedValue()).floatValue(), new PointF(this.h, this.i));
        }
    }

    public a(PDFView pDFView) {
        this.f7908a = pDFView;
        this.c = new OverScroller(pDFView.getContext());
    }

    public void d() {
        if (this.c.computeScrollOffset()) {
            this.f7908a.moveTo(this.c.getCurrX(), this.c.getCurrY());
            this.f7908a.w();
        } else if (this.d) {
            this.d = false;
            this.f7908a.loadPages();
            e();
            this.f7908a.performPageSnap();
        }
    }

    public final void e() {
        if (this.f7908a.getScrollHandle() != null) {
            this.f7908a.getScrollHandle().hideDelayed();
        }
    }

    public boolean f() {
        return this.d || this.e;
    }

    public void g(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        l();
        this.d = true;
        this.c.fling(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public void h(float f) {
        if (this.f7908a.isSwipeVertical()) {
            j(this.f7908a.getCurrentYOffset(), f);
        } else {
            i(this.f7908a.getCurrentXOffset(), f);
        }
        this.e = true;
    }

    public void i(float f, float f2) {
        l();
        this.b = ValueAnimator.ofFloat(f, f2);
        C0365a c0365a = new C0365a();
        this.b.setInterpolator(new DecelerateInterpolator());
        this.b.addUpdateListener(c0365a);
        this.b.addListener(c0365a);
        this.b.setDuration(400L);
        this.b.start();
    }

    public void j(float f, float f2) {
        l();
        this.b = ValueAnimator.ofFloat(f, f2);
        b bVar = new b();
        this.b.setInterpolator(new DecelerateInterpolator());
        this.b.addUpdateListener(bVar);
        this.b.addListener(bVar);
        this.b.setDuration(400L);
        this.b.start();
    }

    public void k(float f, float f2, float f3, float f4) {
        l();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f3, f4);
        this.b = ofFloat;
        ofFloat.setInterpolator(new DecelerateInterpolator());
        c cVar = new c(f, f2);
        this.b.addUpdateListener(cVar);
        this.b.addListener(cVar);
        this.b.setDuration(400L);
        this.b.start();
    }

    public void l() {
        ValueAnimator valueAnimator = this.b;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.b = null;
        }
        m();
    }

    public void m() {
        this.d = false;
        this.c.forceFinished(true);
    }
}
