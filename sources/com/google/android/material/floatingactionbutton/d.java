package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.ImageMatrixProperty;
import com.google.android.material.animation.MatrixEvaluator;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.internal.StateListAnimator;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.ripple.RippleDrawableCompat;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes10.dex */
public class d {
    public static final TimeInterpolator D = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
    public static final int[] E = {16842919, 16842910};
    public static final int[] F = {16843623, 16842908, 16842910};
    public static final int[] G = {16842908, 16842910};
    public static final int[] H = {16843623, 16842910};
    public static final int[] I = {16842910};
    public static final int[] J = new int[0];
    @Nullable
    public ViewTreeObserver.OnPreDrawListener C;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public ShapeAppearanceModel f10306a;
    @Nullable
    public MaterialShapeDrawable b;
    @Nullable
    public Drawable c;
    @Nullable
    public com.google.android.material.floatingactionbutton.c d;
    @Nullable
    public Drawable e;
    public boolean f;
    public float h;
    public float i;
    public float j;
    public int k;
    @NonNull
    public final StateListAnimator l;
    @Nullable
    public Animator m;
    @Nullable
    public MotionSpec n;
    @Nullable
    public MotionSpec o;
    public float p;
    public int r;
    public ArrayList<Animator.AnimatorListener> t;
    public ArrayList<Animator.AnimatorListener> u;
    public ArrayList<j> v;
    public final FloatingActionButton w;
    public final ShadowViewDelegate x;
    public boolean g = true;
    public float q = 1.0f;
    public int s = 0;
    public final Rect y = new Rect();
    public final RectF z = new RectF();
    public final RectF A = new RectF();
    public final Matrix B = new Matrix();

    /* loaded from: classes10.dex */
    public class a extends AnimatorListenerAdapter {
        public boolean h;
        public final /* synthetic */ boolean i;
        public final /* synthetic */ k j;

        public a(boolean z, k kVar) {
            this.i = z;
            this.j = kVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.h = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.s = 0;
            d.this.m = null;
            if (this.h) {
                return;
            }
            FloatingActionButton floatingActionButton = d.this.w;
            boolean z = this.i;
            floatingActionButton.internalSetVisibility(z ? 8 : 4, z);
            k kVar = this.j;
            if (kVar != null) {
                kVar.b();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            d.this.w.internalSetVisibility(0, this.i);
            d.this.s = 1;
            d.this.m = animator;
            this.h = false;
        }
    }

    /* loaded from: classes10.dex */
    public class b extends AnimatorListenerAdapter {
        public final /* synthetic */ boolean h;
        public final /* synthetic */ k i;

        public b(boolean z, k kVar) {
            this.h = z;
            this.i = kVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.s = 0;
            d.this.m = null;
            k kVar = this.i;
            if (kVar != null) {
                kVar.a();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            d.this.w.internalSetVisibility(0, this.h);
            d.this.s = 2;
            d.this.m = animator;
        }
    }

    /* loaded from: classes10.dex */
    public class c extends MatrixEvaluator {
        public c() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.android.material.animation.MatrixEvaluator, android.animation.TypeEvaluator
        public Matrix evaluate(float f, @NonNull Matrix matrix, @NonNull Matrix matrix2) {
            d.this.q = f;
            return super.evaluate(f, matrix, matrix2);
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.d$d  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0429d implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ float h;
        public final /* synthetic */ float i;
        public final /* synthetic */ float j;
        public final /* synthetic */ float k;
        public final /* synthetic */ float l;
        public final /* synthetic */ float m;
        public final /* synthetic */ float n;
        public final /* synthetic */ Matrix o;

        public C0429d(float f, float f2, float f3, float f4, float f5, float f6, float f7, Matrix matrix) {
            this.h = f;
            this.i = f2;
            this.j = f3;
            this.k = f4;
            this.l = f5;
            this.m = f6;
            this.n = f7;
            this.o = matrix;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            d.this.w.setAlpha(AnimationUtils.lerp(this.h, this.i, 0.0f, 0.2f, floatValue));
            d.this.w.setScaleX(AnimationUtils.lerp(this.j, this.k, floatValue));
            d.this.w.setScaleY(AnimationUtils.lerp(this.l, this.k, floatValue));
            d.this.q = AnimationUtils.lerp(this.m, this.n, floatValue);
            d.this.h(AnimationUtils.lerp(this.m, this.n, floatValue), this.o);
            d.this.w.setImageMatrix(this.o);
        }
    }

    /* loaded from: classes10.dex */
    public class e implements TypeEvaluator<Float> {

        /* renamed from: a  reason: collision with root package name */
        public FloatEvaluator f10307a = new FloatEvaluator();

        public e(d dVar) {
        }

        @Override // android.animation.TypeEvaluator
        /* renamed from: a */
        public Float evaluate(float f, Float f2, Float f3) {
            float floatValue = this.f10307a.evaluate(f, (Number) f2, (Number) f3).floatValue();
            if (floatValue < 0.1f) {
                floatValue = 0.0f;
            }
            return Float.valueOf(floatValue);
        }
    }

    /* loaded from: classes10.dex */
    public class f implements ViewTreeObserver.OnPreDrawListener {
        public f() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            d.this.H();
            return true;
        }
    }

    /* loaded from: classes10.dex */
    public class g extends m {
        public g(d dVar) {
            super(dVar, null);
        }

        @Override // com.google.android.material.floatingactionbutton.d.m
        public float a() {
            return 0.0f;
        }
    }

    /* loaded from: classes10.dex */
    public class h extends m {
        public h() {
            super(d.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.d.m
        public float a() {
            d dVar = d.this;
            return dVar.h + dVar.i;
        }
    }

    /* loaded from: classes10.dex */
    public class i extends m {
        public i() {
            super(d.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.d.m
        public float a() {
            d dVar = d.this;
            return dVar.h + dVar.j;
        }
    }

    /* loaded from: classes10.dex */
    public interface j {
        void a();

        void b();
    }

    /* loaded from: classes10.dex */
    public interface k {
        void a();

        void b();
    }

    /* loaded from: classes10.dex */
    public class l extends m {
        public l() {
            super(d.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.d.m
        public float a() {
            return d.this.h;
        }
    }

    /* loaded from: classes10.dex */
    public abstract class m extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        public boolean h;
        public float i;
        public float j;

        public m() {
        }

        public abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.j0((int) this.j);
            this.h = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            if (!this.h) {
                MaterialShapeDrawable materialShapeDrawable = d.this.b;
                this.i = materialShapeDrawable == null ? 0.0f : materialShapeDrawable.getElevation();
                this.j = a();
                this.h = true;
            }
            d dVar = d.this;
            float f = this.i;
            dVar.j0((int) (f + ((this.j - f) * valueAnimator.getAnimatedFraction())));
        }

        public /* synthetic */ m(d dVar, a aVar) {
            this();
        }
    }

    public d(FloatingActionButton floatingActionButton, ShadowViewDelegate shadowViewDelegate) {
        this.w = floatingActionButton;
        this.x = shadowViewDelegate;
        StateListAnimator stateListAnimator = new StateListAnimator();
        this.l = stateListAnimator;
        stateListAnimator.addState(E, k(new i()));
        stateListAnimator.addState(F, k(new h()));
        stateListAnimator.addState(G, k(new h()));
        stateListAnimator.addState(H, k(new h()));
        stateListAnimator.addState(I, k(new l()));
        stateListAnimator.addState(J, k(new g(this)));
        this.p = floatingActionButton.getRotation();
    }

    public void A() {
        this.l.jumpToCurrentState();
    }

    public void B() {
        MaterialShapeDrawable materialShapeDrawable = this.b;
        if (materialShapeDrawable != null) {
            MaterialShapeUtils.setParentAbsoluteElevation(this.w, materialShapeDrawable);
        }
        if (N()) {
            this.w.getViewTreeObserver().addOnPreDrawListener(r());
        }
    }

    public void C() {
    }

    public void D() {
        ViewTreeObserver viewTreeObserver = this.w.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener onPreDrawListener = this.C;
        if (onPreDrawListener != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            this.C = null;
        }
    }

    public void E(int[] iArr) {
        this.l.setState(iArr);
    }

    public void F(float f2, float f3, float f4) {
        i0();
        j0(f2);
    }

    public void G(@NonNull Rect rect) {
        Preconditions.checkNotNull(this.e, "Didn't initialize content background");
        if (c0()) {
            this.x.setBackgroundDrawable(new InsetDrawable(this.e, rect.left, rect.top, rect.right, rect.bottom));
            return;
        }
        this.x.setBackgroundDrawable(this.e);
    }

    public void H() {
        float rotation = this.w.getRotation();
        if (this.p != rotation) {
            this.p = rotation;
            g0();
        }
    }

    public void I() {
        ArrayList<j> arrayList = this.v;
        if (arrayList != null) {
            Iterator<j> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }
    }

    public void J() {
        ArrayList<j> arrayList = this.v;
        if (arrayList != null) {
            Iterator<j> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    public void K(@NonNull Animator.AnimatorListener animatorListener) {
        ArrayList<Animator.AnimatorListener> arrayList = this.u;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(animatorListener);
    }

    public void L(@NonNull Animator.AnimatorListener animatorListener) {
        ArrayList<Animator.AnimatorListener> arrayList = this.t;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(animatorListener);
    }

    public void M(@NonNull j jVar) {
        ArrayList<j> arrayList = this.v;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(jVar);
    }

    public boolean N() {
        return true;
    }

    public void O(@Nullable ColorStateList colorStateList) {
        MaterialShapeDrawable materialShapeDrawable = this.b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setTintList(colorStateList);
        }
        com.google.android.material.floatingactionbutton.c cVar = this.d;
        if (cVar != null) {
            cVar.c(colorStateList);
        }
    }

    public void P(@Nullable PorterDuff.Mode mode) {
        MaterialShapeDrawable materialShapeDrawable = this.b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setTintMode(mode);
        }
    }

    public final void Q(float f2) {
        if (this.h != f2) {
            this.h = f2;
            F(f2, this.i, this.j);
        }
    }

    public void R(boolean z) {
        this.f = z;
    }

    public final void S(@Nullable MotionSpec motionSpec) {
        this.o = motionSpec;
    }

    public final void T(float f2) {
        if (this.i != f2) {
            this.i = f2;
            F(this.h, f2, this.j);
        }
    }

    public final void U(float f2) {
        this.q = f2;
        Matrix matrix = this.B;
        h(f2, matrix);
        this.w.setImageMatrix(matrix);
    }

    public final void V(int i2) {
        if (this.r != i2) {
            this.r = i2;
            h0();
        }
    }

    public void W(int i2) {
        this.k = i2;
    }

    public final void X(float f2) {
        if (this.j != f2) {
            this.j = f2;
            F(this.h, this.i, f2);
        }
    }

    public void Y(@Nullable ColorStateList colorStateList) {
        Drawable drawable = this.c;
        if (drawable != null) {
            DrawableCompat.setTintList(drawable, RippleUtils.sanitizeRippleDrawableColor(colorStateList));
        }
    }

    public void Z(boolean z) {
        this.g = z;
        i0();
    }

    public final void a0(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.f10306a = shapeAppearanceModel;
        MaterialShapeDrawable materialShapeDrawable = this.b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
        Drawable drawable = this.c;
        if (drawable instanceof Shapeable) {
            ((Shapeable) drawable).setShapeAppearanceModel(shapeAppearanceModel);
        }
        com.google.android.material.floatingactionbutton.c cVar = this.d;
        if (cVar != null) {
            cVar.f(shapeAppearanceModel);
        }
    }

    public final void b0(@Nullable MotionSpec motionSpec) {
        this.n = motionSpec;
    }

    public boolean c0() {
        return true;
    }

    public final boolean d0() {
        return ViewCompat.isLaidOut(this.w) && !this.w.isInEditMode();
    }

    public void e(@NonNull Animator.AnimatorListener animatorListener) {
        if (this.u == null) {
            this.u = new ArrayList<>();
        }
        this.u.add(animatorListener);
    }

    public final boolean e0() {
        return !this.f || this.w.getSizeDimension() >= this.k;
    }

    public void f(@NonNull Animator.AnimatorListener animatorListener) {
        if (this.t == null) {
            this.t = new ArrayList<>();
        }
        this.t.add(animatorListener);
    }

    public void f0(@Nullable k kVar, boolean z) {
        AnimatorSet j2;
        if (z()) {
            return;
        }
        Animator animator = this.m;
        if (animator != null) {
            animator.cancel();
        }
        boolean z2 = this.n == null;
        if (d0()) {
            if (this.w.getVisibility() != 0) {
                this.w.setAlpha(0.0f);
                this.w.setScaleY(z2 ? 0.4f : 0.0f);
                this.w.setScaleX(z2 ? 0.4f : 0.0f);
                U(z2 ? 0.4f : 0.0f);
            }
            MotionSpec motionSpec = this.n;
            if (motionSpec != null) {
                j2 = i(motionSpec, 1.0f, 1.0f, 1.0f);
            } else {
                j2 = j(1.0f, 1.0f, 1.0f);
            }
            j2.addListener(new b(z, kVar));
            ArrayList<Animator.AnimatorListener> arrayList = this.t;
            if (arrayList != null) {
                Iterator<Animator.AnimatorListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    j2.addListener(it.next());
                }
            }
            j2.start();
            return;
        }
        this.w.internalSetVisibility(0, z);
        this.w.setAlpha(1.0f);
        this.w.setScaleY(1.0f);
        this.w.setScaleX(1.0f);
        U(1.0f);
        if (kVar != null) {
            kVar.a();
        }
    }

    public void g(@NonNull j jVar) {
        if (this.v == null) {
            this.v = new ArrayList<>();
        }
        this.v.add(jVar);
    }

    public void g0() {
        if (Build.VERSION.SDK_INT == 19) {
            if (this.p % 90.0f != 0.0f) {
                if (this.w.getLayerType() != 1) {
                    this.w.setLayerType(1, null);
                }
            } else if (this.w.getLayerType() != 0) {
                this.w.setLayerType(0, null);
            }
        }
        MaterialShapeDrawable materialShapeDrawable = this.b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShadowCompatRotation((int) this.p);
        }
    }

    public final void h(float f2, @NonNull Matrix matrix) {
        matrix.reset();
        Drawable drawable = this.w.getDrawable();
        if (drawable == null || this.r == 0) {
            return;
        }
        RectF rectF = this.z;
        RectF rectF2 = this.A;
        rectF.set(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        int i2 = this.r;
        rectF2.set(0.0f, 0.0f, i2, i2);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        int i3 = this.r;
        matrix.postScale(f2, f2, i3 / 2.0f, i3 / 2.0f);
    }

    public final void h0() {
        U(this.q);
    }

    @NonNull
    public final AnimatorSet i(@NonNull MotionSpec motionSpec, float f2, float f3, float f4) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.w, View.ALPHA, f2);
        motionSpec.getTiming("opacity").apply(ofFloat);
        arrayList.add(ofFloat);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.w, View.SCALE_X, f3);
        motionSpec.getTiming("scale").apply(ofFloat2);
        k0(ofFloat2);
        arrayList.add(ofFloat2);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.w, View.SCALE_Y, f3);
        motionSpec.getTiming("scale").apply(ofFloat3);
        k0(ofFloat3);
        arrayList.add(ofFloat3);
        h(f4, this.B);
        ObjectAnimator ofObject = ObjectAnimator.ofObject(this.w, new ImageMatrixProperty(), new c(), new Matrix(this.B));
        motionSpec.getTiming("iconScale").apply(ofObject);
        arrayList.add(ofObject);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    public final void i0() {
        Rect rect = this.y;
        s(rect);
        G(rect);
        this.x.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    public final AnimatorSet j(float f2, float f3, float f4) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new C0429d(this.w.getAlpha(), f2, this.w.getScaleX(), f3, this.w.getScaleY(), this.q, f4, new Matrix(this.B)));
        arrayList.add(ofFloat);
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        animatorSet.setDuration(MotionUtils.resolveThemeDuration(this.w.getContext(), R.attr.motionDurationLong1, this.w.getContext().getResources().getInteger(R.integer.material_motion_duration_long_1)));
        animatorSet.setInterpolator(MotionUtils.resolveThemeInterpolator(this.w.getContext(), R.attr.motionEasingStandard, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet;
    }

    public void j0(float f2) {
        MaterialShapeDrawable materialShapeDrawable = this.b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setElevation(f2);
        }
    }

    @NonNull
    public final ValueAnimator k(@NonNull m mVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(D);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(mVar);
        valueAnimator.addUpdateListener(mVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    public final void k0(ObjectAnimator objectAnimator) {
        if (Build.VERSION.SDK_INT != 26) {
            return;
        }
        objectAnimator.setEvaluator(new e(this));
    }

    public MaterialShapeDrawable l() {
        return new MaterialShapeDrawable((ShapeAppearanceModel) Preconditions.checkNotNull(this.f10306a));
    }

    @Nullable
    public final Drawable m() {
        return this.e;
    }

    public float n() {
        return this.h;
    }

    public boolean o() {
        return this.f;
    }

    @Nullable
    public final MotionSpec p() {
        return this.o;
    }

    public float q() {
        return this.i;
    }

    @NonNull
    public final ViewTreeObserver.OnPreDrawListener r() {
        if (this.C == null) {
            this.C = new f();
        }
        return this.C;
    }

    public void s(@NonNull Rect rect) {
        int sizeDimension = this.f ? (this.k - this.w.getSizeDimension()) / 2 : 0;
        float n = this.g ? n() + this.j : 0.0f;
        int max = Math.max(sizeDimension, (int) Math.ceil(n));
        int max2 = Math.max(sizeDimension, (int) Math.ceil(n * 1.5f));
        rect.set(max, max2, max, max2);
    }

    public float t() {
        return this.j;
    }

    @Nullable
    public final ShapeAppearanceModel u() {
        return this.f10306a;
    }

    @Nullable
    public final MotionSpec v() {
        return this.n;
    }

    public void w(@Nullable k kVar, boolean z) {
        AnimatorSet j2;
        if (y()) {
            return;
        }
        Animator animator = this.m;
        if (animator != null) {
            animator.cancel();
        }
        if (d0()) {
            MotionSpec motionSpec = this.o;
            if (motionSpec != null) {
                j2 = i(motionSpec, 0.0f, 0.0f, 0.0f);
            } else {
                j2 = j(0.0f, 0.4f, 0.4f);
            }
            j2.addListener(new a(z, kVar));
            ArrayList<Animator.AnimatorListener> arrayList = this.u;
            if (arrayList != null) {
                Iterator<Animator.AnimatorListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    j2.addListener(it.next());
                }
            }
            j2.start();
            return;
        }
        this.w.internalSetVisibility(z ? 8 : 4, z);
        if (kVar != null) {
            kVar.b();
        }
    }

    public void x(ColorStateList colorStateList, @Nullable PorterDuff.Mode mode, ColorStateList colorStateList2, int i2) {
        MaterialShapeDrawable l2 = l();
        this.b = l2;
        l2.setTintList(colorStateList);
        if (mode != null) {
            this.b.setTintMode(mode);
        }
        this.b.setShadowColor(-12303292);
        this.b.initializeElevationOverlay(this.w.getContext());
        RippleDrawableCompat rippleDrawableCompat = new RippleDrawableCompat(this.b.getShapeAppearanceModel());
        rippleDrawableCompat.setTintList(RippleUtils.sanitizeRippleDrawableColor(colorStateList2));
        this.c = rippleDrawableCompat;
        this.e = new LayerDrawable(new Drawable[]{(Drawable) Preconditions.checkNotNull(this.b), rippleDrawableCompat});
    }

    public boolean y() {
        return this.w.getVisibility() == 0 ? this.s == 1 : this.s != 2;
    }

    public boolean z() {
        return this.w.getVisibility() != 0 ? this.s == 2 : this.s != 1;
    }
}
