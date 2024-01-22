package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.TypedArrayUtils;
import androidx.transition.Transition;
import androidx.transition.a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public abstract class Visibility extends Transition {
    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 2;
    public static final String[] R = {"android:visibility:visibility", "android:visibility:parent"};
    public int Q;

    @SuppressLint({"UniqueConstants"})
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Mode {
    }

    /* loaded from: classes.dex */
    public class a extends TransitionListenerAdapter {
        public final /* synthetic */ ViewGroup h;
        public final /* synthetic */ View i;
        public final /* synthetic */ View j;

        public a(ViewGroup viewGroup, View view, View view2) {
            this.h = viewGroup;
            this.i = view;
            this.j = view2;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            this.j.setTag(R.id.save_overlay_view, null);
            w.b(this.h).b(this.i);
            transition.removeListener(this);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionPause(@NonNull Transition transition) {
            w.b(this.h).b(this.i);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionResume(@NonNull Transition transition) {
            if (this.i.getParent() == null) {
                w.b(this.h).a(this.i);
            } else {
                Visibility.this.cancel();
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b extends AnimatorListenerAdapter implements Transition.TransitionListener, a.InterfaceC0186a {
        public final View h;
        public final int i;
        public final ViewGroup j;
        public final boolean k;
        public boolean l;
        public boolean m = false;

        public b(View view, int i, boolean z) {
            this.h = view;
            this.i = i;
            this.j = (ViewGroup) view.getParent();
            this.k = z;
            b(true);
        }

        public final void a() {
            if (!this.m) {
                b0.i(this.h, this.i);
                ViewGroup viewGroup = this.j;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            b(false);
        }

        public final void b(boolean z) {
            ViewGroup viewGroup;
            if (!this.k || this.l == z || (viewGroup = this.j) == null) {
                return;
            }
            this.l = z;
            w.d(viewGroup, z);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.m = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, androidx.transition.a.InterfaceC0186a
        public void onAnimationPause(Animator animator) {
            if (this.m) {
                return;
            }
            b0.i(this.h, this.i);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, androidx.transition.a.InterfaceC0186a
        public void onAnimationResume(Animator animator) {
            if (this.m) {
                return;
            }
            b0.i(this.h, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(@NonNull Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            a();
            transition.removeListener(this);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionPause(@NonNull Transition transition) {
            b(false);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionResume(@NonNull Transition transition) {
            b(true);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionStart(@NonNull Transition transition) {
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public boolean f1706a;
        public boolean b;
        public int c;
        public int d;
        public ViewGroup e;
        public ViewGroup f;
    }

    public Visibility() {
        this.Q = 3;
    }

    public final void C(TransitionValues transitionValues) {
        transitionValues.values.put("android:visibility:visibility", Integer.valueOf(transitionValues.view.getVisibility()));
        transitionValues.values.put("android:visibility:parent", transitionValues.view.getParent());
        int[] iArr = new int[2];
        transitionValues.view.getLocationOnScreen(iArr);
        transitionValues.values.put("android:visibility:screenLocation", iArr);
    }

    public final c D(TransitionValues transitionValues, TransitionValues transitionValues2) {
        c cVar = new c();
        cVar.f1706a = false;
        cVar.b = false;
        if (transitionValues != null && transitionValues.values.containsKey("android:visibility:visibility")) {
            cVar.c = ((Integer) transitionValues.values.get("android:visibility:visibility")).intValue();
            cVar.e = (ViewGroup) transitionValues.values.get("android:visibility:parent");
        } else {
            cVar.c = -1;
            cVar.e = null;
        }
        if (transitionValues2 != null && transitionValues2.values.containsKey("android:visibility:visibility")) {
            cVar.d = ((Integer) transitionValues2.values.get("android:visibility:visibility")).intValue();
            cVar.f = (ViewGroup) transitionValues2.values.get("android:visibility:parent");
        } else {
            cVar.d = -1;
            cVar.f = null;
        }
        if (transitionValues != null && transitionValues2 != null) {
            int i = cVar.c;
            int i2 = cVar.d;
            if (i == i2 && cVar.e == cVar.f) {
                return cVar;
            }
            if (i != i2) {
                if (i == 0) {
                    cVar.b = false;
                    cVar.f1706a = true;
                } else if (i2 == 0) {
                    cVar.b = true;
                    cVar.f1706a = true;
                }
            } else if (cVar.f == null) {
                cVar.b = false;
                cVar.f1706a = true;
            } else if (cVar.e == null) {
                cVar.b = true;
                cVar.f1706a = true;
            }
        } else if (transitionValues == null && cVar.d == 0) {
            cVar.b = true;
            cVar.f1706a = true;
        } else if (transitionValues2 == null && cVar.c == 0) {
            cVar.b = false;
            cVar.f1706a = true;
        }
        return cVar;
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        C(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        C(transitionValues);
    }

    @Override // androidx.transition.Transition
    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        c D = D(transitionValues, transitionValues2);
        if (D.f1706a) {
            if (D.e == null && D.f == null) {
                return null;
            }
            if (D.b) {
                return onAppear(viewGroup, transitionValues, D.c, transitionValues2, D.d);
            }
            return onDisappear(viewGroup, transitionValues, D.c, transitionValues2, D.d);
        }
        return null;
    }

    public int getMode() {
        return this.Q;
    }

    @Override // androidx.transition.Transition
    @Nullable
    public String[] getTransitionProperties() {
        return R;
    }

    @Override // androidx.transition.Transition
    public boolean isTransitionRequired(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null && transitionValues2 == null) {
            return false;
        }
        if (transitionValues == null || transitionValues2 == null || transitionValues2.values.containsKey("android:visibility:visibility") == transitionValues.values.containsKey("android:visibility:visibility")) {
            c D = D(transitionValues, transitionValues2);
            if (D.f1706a) {
                return D.c == 0 || D.d == 0;
            }
            return false;
        }
        return false;
    }

    public boolean isVisible(TransitionValues transitionValues) {
        if (transitionValues == null) {
            return false;
        }
        return ((Integer) transitionValues.values.get("android:visibility:visibility")).intValue() == 0 && ((View) transitionValues.values.get("android:visibility:parent")) != null;
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    public Animator onAppear(ViewGroup viewGroup, TransitionValues transitionValues, int i, TransitionValues transitionValues2, int i2) {
        if ((this.Q & 1) != 1 || transitionValues2 == null) {
            return null;
        }
        if (transitionValues == null) {
            View view = (View) transitionValues2.view.getParent();
            if (D(m(view, false), getTransitionValues(view, false)).f1706a) {
                return null;
            }
        }
        return onAppear(viewGroup, transitionValues2.view, transitionValues, transitionValues2);
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0089, code lost:
        if (r17.C != false) goto L52;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.animation.Animator onDisappear(android.view.ViewGroup r18, androidx.transition.TransitionValues r19, int r20, androidx.transition.TransitionValues r21, int r22) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Visibility.onDisappear(android.view.ViewGroup, androidx.transition.TransitionValues, int, androidx.transition.TransitionValues, int):android.animation.Animator");
    }

    public void setMode(int i) {
        if ((i & (-4)) == 0) {
            this.Q = i;
            return;
        }
        throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
    }

    @SuppressLint({"RestrictedApi"})
    public Visibility(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Q = 3;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, o.e);
        int namedInt = TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionVisibilityMode", 0, 0);
        obtainStyledAttributes.recycle();
        if (namedInt != 0) {
            setMode(namedInt);
        }
    }
}
