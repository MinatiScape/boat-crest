package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import androidx.annotation.AnimRes;
import androidx.annotation.NonNull;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.R;
import androidx.fragment.app.j;
/* loaded from: classes.dex */
public class c {

    /* loaded from: classes.dex */
    public class a implements CancellationSignal.OnCancelListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Fragment f1325a;

        public a(Fragment fragment) {
            this.f1325a = fragment;
        }

        @Override // androidx.core.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            if (this.f1325a.getAnimatingAway() != null) {
                View animatingAway = this.f1325a.getAnimatingAway();
                this.f1325a.setAnimatingAway(null);
                animatingAway.clearAnimation();
            }
            this.f1325a.setAnimator(null);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Animation.AnimationListener {
        public final /* synthetic */ ViewGroup h;
        public final /* synthetic */ Fragment i;
        public final /* synthetic */ j.g j;
        public final /* synthetic */ CancellationSignal k;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (b.this.i.getAnimatingAway() != null) {
                    b.this.i.setAnimatingAway(null);
                    b bVar = b.this;
                    bVar.j.a(bVar.i, bVar.k);
                }
            }
        }

        public b(ViewGroup viewGroup, Fragment fragment, j.g gVar, CancellationSignal cancellationSignal) {
            this.h = viewGroup;
            this.i = fragment;
            this.j = gVar;
            this.k = cancellationSignal;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.h.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* renamed from: androidx.fragment.app.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0147c extends AnimatorListenerAdapter {
        public final /* synthetic */ ViewGroup h;
        public final /* synthetic */ View i;
        public final /* synthetic */ Fragment j;
        public final /* synthetic */ j.g k;
        public final /* synthetic */ CancellationSignal l;

        public C0147c(ViewGroup viewGroup, View view, Fragment fragment, j.g gVar, CancellationSignal cancellationSignal) {
            this.h = viewGroup;
            this.i = view;
            this.j = fragment;
            this.k = gVar;
            this.l = cancellationSignal;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.h.endViewTransition(this.i);
            Animator animator2 = this.j.getAnimator();
            this.j.setAnimator(null);
            if (animator2 == null || this.h.indexOfChild(this.i) >= 0) {
                return;
            }
            this.k.a(this.j, this.l);
        }
    }

    public static void a(@NonNull Fragment fragment, @NonNull d dVar, @NonNull j.g gVar) {
        View view = fragment.mView;
        ViewGroup viewGroup = fragment.mContainer;
        viewGroup.startViewTransition(view);
        CancellationSignal cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(new a(fragment));
        gVar.b(fragment, cancellationSignal);
        if (dVar.f1326a != null) {
            e eVar = new e(dVar.f1326a, viewGroup, view);
            fragment.setAnimatingAway(fragment.mView);
            eVar.setAnimationListener(new b(viewGroup, fragment, gVar, cancellationSignal));
            fragment.mView.startAnimation(eVar);
            return;
        }
        Animator animator = dVar.b;
        fragment.setAnimator(animator);
        animator.addListener(new C0147c(viewGroup, view, fragment, gVar, cancellationSignal));
        animator.setTarget(fragment.mView);
        animator.start();
    }

    public static int b(Fragment fragment, boolean z, boolean z2) {
        if (z2) {
            if (z) {
                return fragment.getPopEnterAnim();
            }
            return fragment.getPopExitAnim();
        } else if (z) {
            return fragment.getEnterAnim();
        } else {
            return fragment.getExitAnim();
        }
    }

    public static d c(@NonNull Context context, @NonNull Fragment fragment, boolean z, boolean z2) {
        int nextTransition = fragment.getNextTransition();
        int b2 = b(fragment, z, z2);
        boolean z3 = false;
        fragment.setAnimations(0, 0, 0, 0);
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null) {
            int i = R.id.visible_removing_fragment_view_tag;
            if (viewGroup.getTag(i) != null) {
                fragment.mContainer.setTag(i, null);
            }
        }
        ViewGroup viewGroup2 = fragment.mContainer;
        if (viewGroup2 == null || viewGroup2.getLayoutTransition() == null) {
            Animation onCreateAnimation = fragment.onCreateAnimation(nextTransition, z, b2);
            if (onCreateAnimation != null) {
                return new d(onCreateAnimation);
            }
            Animator onCreateAnimator = fragment.onCreateAnimator(nextTransition, z, b2);
            if (onCreateAnimator != null) {
                return new d(onCreateAnimator);
            }
            if (b2 == 0 && nextTransition != 0) {
                b2 = d(nextTransition, z);
            }
            if (b2 != 0) {
                boolean equals = "anim".equals(context.getResources().getResourceTypeName(b2));
                if (equals) {
                    try {
                        Animation loadAnimation = AnimationUtils.loadAnimation(context, b2);
                        if (loadAnimation != null) {
                            return new d(loadAnimation);
                        }
                        z3 = true;
                    } catch (Resources.NotFoundException e2) {
                        throw e2;
                    } catch (RuntimeException unused) {
                    }
                }
                if (!z3) {
                    try {
                        Animator loadAnimator = AnimatorInflater.loadAnimator(context, b2);
                        if (loadAnimator != null) {
                            return new d(loadAnimator);
                        }
                    } catch (RuntimeException e3) {
                        if (!equals) {
                            Animation loadAnimation2 = AnimationUtils.loadAnimation(context, b2);
                            if (loadAnimation2 != null) {
                                return new d(loadAnimation2);
                            }
                        } else {
                            throw e3;
                        }
                    }
                }
            }
            return null;
        }
        return null;
    }

    @AnimRes
    public static int d(int i, boolean z) {
        if (i == 4097) {
            return z ? R.animator.fragment_open_enter : R.animator.fragment_open_exit;
        } else if (i == 4099) {
            return z ? R.animator.fragment_fade_enter : R.animator.fragment_fade_exit;
        } else if (i != 8194) {
            return -1;
        } else {
            return z ? R.animator.fragment_close_enter : R.animator.fragment_close_exit;
        }
    }

    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public final Animation f1326a;
        public final Animator b;

        public d(Animation animation) {
            this.f1326a = animation;
            this.b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        public d(Animator animator) {
            this.f1326a = null;
            this.b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* loaded from: classes.dex */
    public static class e extends AnimationSet implements Runnable {
        public final ViewGroup h;
        public final View i;
        public boolean j;
        public boolean k;
        public boolean l;

        public e(@NonNull Animation animation, @NonNull ViewGroup viewGroup, @NonNull View view) {
            super(false);
            this.l = true;
            this.h = viewGroup;
            this.i = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, @NonNull Transformation transformation) {
            this.l = true;
            if (this.j) {
                return !this.k;
            }
            if (!super.getTransformation(j, transformation)) {
                this.j = true;
                OneShotPreDrawListener.add(this.h, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.j && this.l) {
                this.l = false;
                this.h.post(this);
                return;
            }
            this.h.endViewTransition(this.i);
            this.k = true;
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, @NonNull Transformation transformation, float f) {
            this.l = true;
            if (this.j) {
                return !this.k;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.j = true;
                OneShotPreDrawListener.add(this.h, this);
            }
            return true;
        }
    }
}
