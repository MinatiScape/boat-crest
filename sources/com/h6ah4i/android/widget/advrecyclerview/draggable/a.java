package com.h6ah4i.android.widget.advrecyclerview.draggable;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes11.dex */
public abstract class a extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public int f11908a = 200;
    public Interpolator b;
    public final RecyclerView c;
    public RecyclerView.ViewHolder d;

    /* renamed from: com.h6ah4i.android.widget.advrecyclerview.draggable.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0553a implements ViewPropertyAnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ float f11909a;

        public C0553a(a aVar, float f) {
            this.f11909a = f;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationCancel(View view) {
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            ViewCompat.animate(view).setListener(null);
            a.c(view, this.f11909a);
            if (view.getParent() instanceof RecyclerView) {
                ViewCompat.postInvalidateOnAnimation((RecyclerView) view.getParent());
            }
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart(View view) {
        }
    }

    public a(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        this.c = recyclerView;
        this.d = viewHolder;
        float f = recyclerView.getResources().getDisplayMetrics().density;
    }

    public static void c(View view, float f) {
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        ViewCompat.setTranslationZ(view, f);
        view.setAlpha(1.0f);
        view.setRotation(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
    }

    public static void d(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2) {
        RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.endAnimation(viewHolder);
        }
        viewHolder.itemView.setTranslationX(f);
        viewHolder.itemView.setTranslationY(f2);
    }

    public float a(View view, float f, float f2, float f3, float f4) {
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int width = view.getWidth() / 2;
        int height = view.getHeight() / 2;
        float abs = width > 0 ? Math.abs(translationX / width) : 0.0f;
        float abs2 = height > 0 ? Math.abs(translationY / height) : 0.0f;
        float abs3 = Math.abs(Math.max(f, f2) - 1.0f);
        return Math.min(Math.max(Math.max(Math.max(Math.max(Math.max(0.0f, abs), abs2), abs3), Math.abs(f3 * 0.033333335f)), Math.abs(f4 - 1.0f)), 1.0f);
    }

    public void b(View view, float f, float f2, float f3, float f4, boolean z) {
        float translationZ = ViewCompat.getTranslationZ(view);
        int a2 = (int) (this.f11908a * a(view, f, f2, f3, f4));
        if (z && a2 > 20) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(view);
            view.setScaleX(f);
            view.setScaleY(f2);
            view.setRotation(f3);
            view.setAlpha(f4);
            ViewCompat.setTranslationZ(view, translationZ + 1.0f);
            animate.cancel();
            animate.setDuration(a2);
            animate.setInterpolator(this.b);
            animate.translationX(0.0f);
            animate.translationY(0.0f);
            animate.translationZ(translationZ);
            animate.alpha(1.0f);
            animate.rotation(0.0f);
            animate.scaleX(1.0f);
            animate.scaleY(1.0f);
            animate.setListener(new C0553a(this, translationZ));
            animate.start();
            return;
        }
        c(view, translationZ);
    }

    public void e(int i) {
        this.f11908a = i;
    }

    public void f(Interpolator interpolator) {
        this.b = interpolator;
    }
}
