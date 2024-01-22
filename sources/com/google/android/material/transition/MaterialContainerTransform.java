package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.core.util.Preconditions;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import androidx.transition.ArcMotion;
import androidx.transition.PathMotion;
import androidx.transition.Transition;
import androidx.transition.TransitionValues;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.transition.j;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes10.dex */
public final class MaterialContainerTransform extends Transition {
    public static final int FADE_MODE_CROSS = 2;
    public static final int FADE_MODE_IN = 0;
    public static final int FADE_MODE_OUT = 1;
    public static final int FADE_MODE_THROUGH = 3;
    public static final int FIT_MODE_AUTO = 0;
    public static final int FIT_MODE_HEIGHT = 2;
    public static final int FIT_MODE_WIDTH = 1;
    public static final int TRANSITION_DIRECTION_AUTO = 0;
    public static final int TRANSITION_DIRECTION_ENTER = 1;
    public static final int TRANSITION_DIRECTION_RETURN = 2;
    public static final String s0 = MaterialContainerTransform.class.getSimpleName();
    public static final String[] t0 = {"materialContainerTransition:bounds", "materialContainerTransition:shapeAppearance"};
    public static final c u0 = new c(new ProgressThresholds(0.0f, 0.25f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.75f), null);
    public static final c v0 = new c(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.3f, 0.9f), null);
    public static final c w0 = new c(new ProgressThresholds(0.1f, 0.4f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 0.9f), null);
    public static final c x0 = new c(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.2f, 0.9f), null);
    public boolean Q;
    public boolean R;
    public boolean S;
    public boolean T;
    @IdRes
    public int U;
    @IdRes
    public int V;
    @IdRes
    public int W;
    @ColorInt
    public int a0;
    @ColorInt
    public int b0;
    @ColorInt
    public int c0;
    @ColorInt
    public int d0;
    public int e0;
    public int f0;
    public int g0;
    @Nullable
    public View h0;
    @Nullable
    public View i0;
    @Nullable
    public ShapeAppearanceModel j0;
    @Nullable
    public ShapeAppearanceModel k0;
    @Nullable
    public ProgressThresholds l0;
    @Nullable
    public ProgressThresholds m0;
    @Nullable
    public ProgressThresholds n0;
    @Nullable
    public ProgressThresholds o0;
    public boolean p0;
    public float q0;
    public float r0;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface FadeMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface FitMode {
    }

    /* loaded from: classes10.dex */
    public static class ProgressThresholds {
        @FloatRange(from = 0.0d, to = 1.0d)

        /* renamed from: a  reason: collision with root package name */
        public final float f10402a;
        @FloatRange(from = 0.0d, to = 1.0d)
        public final float b;

        public ProgressThresholds(@FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
            this.f10402a = f;
            this.b = f2;
        }

        @FloatRange(from = 0.0d, to = 1.0d)
        public float getEnd() {
            return this.b;
        }

        @FloatRange(from = 0.0d, to = 1.0d)
        public float getStart() {
            return this.f10402a;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface TransitionDirection {
    }

    /* loaded from: classes10.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ d h;

        public a(MaterialContainerTransform materialContainerTransform, d dVar) {
            this.h = dVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.h.o(valueAnimator.getAnimatedFraction());
        }
    }

    /* loaded from: classes10.dex */
    public class b extends i {
        public final /* synthetic */ View h;
        public final /* synthetic */ d i;
        public final /* synthetic */ View j;
        public final /* synthetic */ View k;

        public b(View view, d dVar, View view2, View view3) {
            this.h = view;
            this.i = dVar;
            this.j = view2;
            this.k = view3;
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            MaterialContainerTransform.this.removeListener(this);
            if (MaterialContainerTransform.this.R) {
                return;
            }
            this.j.setAlpha(1.0f);
            this.k.setAlpha(1.0f);
            ViewUtils.getOverlay(this.h).remove(this.i);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionStart(@NonNull Transition transition) {
            ViewUtils.getOverlay(this.h).add(this.i);
            this.j.setAlpha(0.0f);
            this.k.setAlpha(0.0f);
        }
    }

    /* loaded from: classes10.dex */
    public static class c {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final ProgressThresholds f10403a;
        @NonNull
        public final ProgressThresholds b;
        @NonNull
        public final ProgressThresholds c;
        @NonNull
        public final ProgressThresholds d;

        public /* synthetic */ c(ProgressThresholds progressThresholds, ProgressThresholds progressThresholds2, ProgressThresholds progressThresholds3, ProgressThresholds progressThresholds4, a aVar) {
            this(progressThresholds, progressThresholds2, progressThresholds3, progressThresholds4);
        }

        public c(@NonNull ProgressThresholds progressThresholds, @NonNull ProgressThresholds progressThresholds2, @NonNull ProgressThresholds progressThresholds3, @NonNull ProgressThresholds progressThresholds4) {
            this.f10403a = progressThresholds;
            this.b = progressThresholds2;
            this.c = progressThresholds3;
            this.d = progressThresholds4;
        }
    }

    /* loaded from: classes10.dex */
    public static final class d extends Drawable {
        public final c A;
        public final com.google.android.material.transition.a B;
        public final com.google.android.material.transition.d C;
        public final boolean D;
        public final Paint E;
        public final Path F;
        public com.google.android.material.transition.c G;
        public f H;
        public RectF I;
        public float J;
        public float K;
        public float L;

        /* renamed from: a  reason: collision with root package name */
        public final View f10404a;
        public final RectF b;
        public final ShapeAppearanceModel c;
        public final float d;
        public final View e;
        public final RectF f;
        public final ShapeAppearanceModel g;
        public final float h;
        public final Paint i;
        public final Paint j;
        public final Paint k;
        public final Paint l;
        public final Paint m;
        public final g n;
        public final PathMeasure o;
        public final float p;
        public final float[] q;
        public final boolean r;
        public final float s;
        public final float t;
        public final boolean u;
        public final MaterialShapeDrawable v;
        public final RectF w;
        public final RectF x;
        public final RectF y;
        public final RectF z;

        /* loaded from: classes10.dex */
        public class a implements j.c {
            public a() {
            }

            @Override // com.google.android.material.transition.j.c
            public void a(Canvas canvas) {
                d.this.f10404a.draw(canvas);
            }
        }

        /* loaded from: classes10.dex */
        public class b implements j.c {
            public b() {
            }

            @Override // com.google.android.material.transition.j.c
            public void a(Canvas canvas) {
                d.this.e.draw(canvas);
            }
        }

        public /* synthetic */ d(PathMotion pathMotion, View view, RectF rectF, ShapeAppearanceModel shapeAppearanceModel, float f, View view2, RectF rectF2, ShapeAppearanceModel shapeAppearanceModel2, float f2, int i, int i2, int i3, int i4, boolean z, boolean z2, com.google.android.material.transition.a aVar, com.google.android.material.transition.d dVar, c cVar, boolean z3, a aVar2) {
            this(pathMotion, view, rectF, shapeAppearanceModel, f, view2, rectF2, shapeAppearanceModel2, f2, i, i2, i3, i4, z, z2, aVar, dVar, cVar, z3);
        }

        public static float d(RectF rectF, float f) {
            return ((rectF.centerX() / (f / 2.0f)) - 1.0f) * 0.3f;
        }

        public static float e(RectF rectF, float f) {
            return (rectF.centerY() / f) * 1.5f;
        }

        public static PointF m(RectF rectF) {
            return new PointF(rectF.centerX(), rectF.top);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(@NonNull Canvas canvas) {
            if (this.m.getAlpha() > 0) {
                canvas.drawRect(getBounds(), this.m);
            }
            int save = this.D ? canvas.save() : -1;
            if (this.u && this.J > 0.0f) {
                h(canvas);
            }
            this.n.a(canvas);
            n(canvas, this.i);
            if (this.G.c) {
                l(canvas);
                k(canvas);
            } else {
                k(canvas);
                l(canvas);
            }
            if (this.D) {
                canvas.restoreToCount(save);
                f(canvas, this.w, this.F, -65281);
                g(canvas, this.x, InputDeviceCompat.SOURCE_ANY);
                g(canvas, this.w, -16711936);
                g(canvas, this.z, -16711681);
                g(canvas, this.y, -16776961);
            }
        }

        public final void f(Canvas canvas, RectF rectF, Path path, @ColorInt int i) {
            PointF m = m(rectF);
            if (this.L == 0.0f) {
                path.reset();
                path.moveTo(m.x, m.y);
                return;
            }
            path.lineTo(m.x, m.y);
            this.E.setColor(i);
            canvas.drawPath(path, this.E);
        }

        public final void g(Canvas canvas, RectF rectF, @ColorInt int i) {
            this.E.setColor(i);
            canvas.drawRect(rectF, this.E);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        public final void h(Canvas canvas) {
            canvas.save();
            canvas.clipPath(this.n.d(), Region.Op.DIFFERENCE);
            if (Build.VERSION.SDK_INT > 28) {
                j(canvas);
            } else {
                i(canvas);
            }
            canvas.restore();
        }

        public final void i(Canvas canvas) {
            MaterialShapeDrawable materialShapeDrawable = this.v;
            RectF rectF = this.I;
            materialShapeDrawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            this.v.setElevation(this.J);
            this.v.setShadowVerticalOffset((int) this.K);
            this.v.setShapeAppearanceModel(this.n.c());
            this.v.draw(canvas);
        }

        public final void j(Canvas canvas) {
            ShapeAppearanceModel c = this.n.c();
            if (c.isRoundRect(this.I)) {
                float cornerSize = c.getTopLeftCornerSize().getCornerSize(this.I);
                canvas.drawRoundRect(this.I, cornerSize, cornerSize, this.l);
                return;
            }
            canvas.drawPath(this.n.d(), this.l);
        }

        public final void k(Canvas canvas) {
            n(canvas, this.k);
            Rect bounds = getBounds();
            RectF rectF = this.y;
            j.t(canvas, bounds, rectF.left, rectF.top, this.H.b, this.G.b, new b());
        }

        public final void l(Canvas canvas) {
            n(canvas, this.j);
            Rect bounds = getBounds();
            RectF rectF = this.w;
            j.t(canvas, bounds, rectF.left, rectF.top, this.H.f10412a, this.G.f10410a, new a());
        }

        public final void n(Canvas canvas, Paint paint) {
            if (paint.getColor() == 0 || paint.getAlpha() <= 0) {
                return;
            }
            canvas.drawRect(getBounds(), paint);
        }

        public final void o(float f) {
            if (this.L != f) {
                p(f);
            }
        }

        public final void p(float f) {
            float f2;
            float f3;
            this.L = f;
            this.m.setAlpha((int) (this.r ? j.j(0.0f, 255.0f, f) : j.j(255.0f, 0.0f, f)));
            this.o.getPosTan(this.p * f, this.q, null);
            float[] fArr = this.q;
            float f4 = fArr[0];
            float f5 = fArr[1];
            int i = (f > 1.0f ? 1 : (f == 1.0f ? 0 : -1));
            if (i > 0 || f < 0.0f) {
                if (i > 0) {
                    f2 = 0.99f;
                    f3 = (f - 1.0f) / 0.00999999f;
                } else {
                    f2 = 0.01f;
                    f3 = (f / 0.01f) * (-1.0f);
                }
                this.o.getPosTan(this.p * f2, fArr, null);
                float[] fArr2 = this.q;
                f4 += (f4 - fArr2[0]) * f3;
                f5 += (f5 - fArr2[1]) * f3;
            }
            float f6 = f4;
            float f7 = f5;
            f a2 = this.C.a(f, ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.b.f10402a))).floatValue(), ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.b.b))).floatValue(), this.b.width(), this.b.height(), this.f.width(), this.f.height());
            this.H = a2;
            RectF rectF = this.w;
            float f8 = a2.c;
            rectF.set(f6 - (f8 / 2.0f), f7, (f8 / 2.0f) + f6, a2.d + f7);
            RectF rectF2 = this.y;
            f fVar = this.H;
            float f9 = fVar.e;
            rectF2.set(f6 - (f9 / 2.0f), f7, f6 + (f9 / 2.0f), fVar.f + f7);
            this.x.set(this.w);
            this.z.set(this.y);
            float floatValue = ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.c.f10402a))).floatValue();
            float floatValue2 = ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.c.b))).floatValue();
            boolean b2 = this.C.b(this.H);
            RectF rectF3 = b2 ? this.x : this.z;
            float k = j.k(0.0f, 1.0f, floatValue, floatValue2, f);
            if (!b2) {
                k = 1.0f - k;
            }
            this.C.c(rectF3, k, this.H);
            this.I = new RectF(Math.min(this.x.left, this.z.left), Math.min(this.x.top, this.z.top), Math.max(this.x.right, this.z.right), Math.max(this.x.bottom, this.z.bottom));
            this.n.b(f, this.c, this.g, this.w, this.x, this.z, this.A.d);
            this.J = j.j(this.d, this.h, f);
            float d = d(this.I, this.s);
            float e = e(this.I, this.t);
            float f10 = this.J;
            float f11 = (int) (e * f10);
            this.K = f11;
            this.l.setShadowLayer(f10, (int) (d * f10), f11, 754974720);
            this.G = this.B.a(f, ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.f10403a.f10402a))).floatValue(), ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.f10403a.b))).floatValue(), 0.35f);
            if (this.j.getColor() != 0) {
                this.j.setAlpha(this.G.f10410a);
            }
            if (this.k.getColor() != 0) {
                this.k.setAlpha(this.G.b);
            }
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            throw new UnsupportedOperationException("Setting alpha on is not supported");
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(@Nullable ColorFilter colorFilter) {
            throw new UnsupportedOperationException("Setting a color filter is not supported");
        }

        public d(PathMotion pathMotion, View view, RectF rectF, ShapeAppearanceModel shapeAppearanceModel, float f, View view2, RectF rectF2, ShapeAppearanceModel shapeAppearanceModel2, float f2, @ColorInt int i, @ColorInt int i2, @ColorInt int i3, int i4, boolean z, boolean z2, com.google.android.material.transition.a aVar, com.google.android.material.transition.d dVar, c cVar, boolean z3) {
            Paint paint = new Paint();
            this.i = paint;
            Paint paint2 = new Paint();
            this.j = paint2;
            Paint paint3 = new Paint();
            this.k = paint3;
            this.l = new Paint();
            Paint paint4 = new Paint();
            this.m = paint4;
            this.n = new g();
            this.q = r7;
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
            this.v = materialShapeDrawable;
            Paint paint5 = new Paint();
            this.E = paint5;
            this.F = new Path();
            this.f10404a = view;
            this.b = rectF;
            this.c = shapeAppearanceModel;
            this.d = f;
            this.e = view2;
            this.f = rectF2;
            this.g = shapeAppearanceModel2;
            this.h = f2;
            this.r = z;
            this.u = z2;
            this.B = aVar;
            this.C = dVar;
            this.A = cVar;
            this.D = z3;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) view.getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            this.s = displayMetrics.widthPixels;
            this.t = displayMetrics.heightPixels;
            paint.setColor(i);
            paint2.setColor(i2);
            paint3.setColor(i3);
            materialShapeDrawable.setFillColor(ColorStateList.valueOf(0));
            materialShapeDrawable.setShadowCompatibilityMode(2);
            materialShapeDrawable.setShadowBitmapDrawingEnable(false);
            materialShapeDrawable.setShadowColor(-7829368);
            RectF rectF3 = new RectF(rectF);
            this.w = rectF3;
            this.x = new RectF(rectF3);
            RectF rectF4 = new RectF(rectF3);
            this.y = rectF4;
            this.z = new RectF(rectF4);
            PointF m = m(rectF);
            PointF m2 = m(rectF2);
            PathMeasure pathMeasure = new PathMeasure(pathMotion.getPath(m.x, m.y, m2.x, m2.y), false);
            this.o = pathMeasure;
            this.p = pathMeasure.getLength();
            float[] fArr = {rectF.centerX(), rectF.top};
            paint4.setStyle(Paint.Style.FILL);
            paint4.setShader(j.c(i4));
            paint5.setStyle(Paint.Style.STROKE);
            paint5.setStrokeWidth(10.0f);
            p(0.0f);
        }
    }

    public MaterialContainerTransform() {
        this.Q = false;
        this.R = false;
        this.S = false;
        this.T = false;
        this.U = 16908290;
        this.V = -1;
        this.W = -1;
        this.a0 = 0;
        this.b0 = 0;
        this.c0 = 0;
        this.d0 = 1375731712;
        this.e0 = 0;
        this.f0 = 0;
        this.g0 = 0;
        this.p0 = Build.VERSION.SDK_INT >= 28;
        this.q0 = -1.0f;
        this.r0 = -1.0f;
    }

    public static RectF E(View view, @Nullable View view2, float f, float f2) {
        if (view2 != null) {
            RectF g = j.g(view2);
            g.offset(f, f2);
            return g;
        }
        return new RectF(0.0f, 0.0f, view.getWidth(), view.getHeight());
    }

    public static ShapeAppearanceModel F(@NonNull View view, @NonNull RectF rectF, @Nullable ShapeAppearanceModel shapeAppearanceModel) {
        return j.b(I(view, shapeAppearanceModel), rectF);
    }

    public static void G(@NonNull TransitionValues transitionValues, @Nullable View view, @IdRes int i, @Nullable ShapeAppearanceModel shapeAppearanceModel) {
        if (i != -1) {
            transitionValues.view = j.f(transitionValues.view, i);
        } else if (view != null) {
            transitionValues.view = view;
        } else {
            View view2 = transitionValues.view;
            int i2 = R.id.mtrl_motion_snapshot_view;
            if (view2.getTag(i2) instanceof View) {
                transitionValues.view.setTag(i2, null);
                transitionValues.view = (View) transitionValues.view.getTag(i2);
            }
        }
        View view3 = transitionValues.view;
        if (!ViewCompat.isLaidOut(view3) && view3.getWidth() == 0 && view3.getHeight() == 0) {
            return;
        }
        RectF h = view3.getParent() == null ? j.h(view3) : j.g(view3);
        transitionValues.values.put("materialContainerTransition:bounds", h);
        transitionValues.values.put("materialContainerTransition:shapeAppearance", F(view3, h, shapeAppearanceModel));
    }

    public static float H(float f, View view) {
        return f != -1.0f ? f : ViewCompat.getElevation(view);
    }

    public static ShapeAppearanceModel I(@NonNull View view, @Nullable ShapeAppearanceModel shapeAppearanceModel) {
        if (shapeAppearanceModel != null) {
            return shapeAppearanceModel;
        }
        int i = R.id.mtrl_motion_snapshot_view;
        if (view.getTag(i) instanceof ShapeAppearanceModel) {
            return (ShapeAppearanceModel) view.getTag(i);
        }
        Context context = view.getContext();
        int K = K(context);
        if (K != -1) {
            return ShapeAppearanceModel.builder(context, K, 0).build();
        }
        if (view instanceof Shapeable) {
            return ((Shapeable) view).getShapeAppearanceModel();
        }
        return ShapeAppearanceModel.builder().build();
    }

    @StyleRes
    public static int K(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.transitionShapeAppearance});
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public final c D(boolean z) {
        PathMotion pathMotion = getPathMotion();
        if (!(pathMotion instanceof ArcMotion) && !(pathMotion instanceof MaterialArcMotion)) {
            return J(z, u0, v0);
        }
        return J(z, w0, x0);
    }

    public final c J(boolean z, c cVar, c cVar2) {
        if (!z) {
            cVar = cVar2;
        }
        return new c((ProgressThresholds) j.d(this.l0, cVar.f10403a), (ProgressThresholds) j.d(this.m0, cVar.b), (ProgressThresholds) j.d(this.n0, cVar.c), (ProgressThresholds) j.d(this.o0, cVar.d), null);
    }

    public final boolean L(@NonNull RectF rectF, @NonNull RectF rectF2) {
        int i = this.e0;
        if (i == 0) {
            return j.a(rectF2) > j.a(rectF);
        } else if (i != 1) {
            if (i == 2) {
                return false;
            }
            throw new IllegalArgumentException("Invalid transition direction: " + this.e0);
        } else {
            return true;
        }
    }

    public final void M(Context context, boolean z) {
        j.p(this, context, R.attr.motionEasingStandard, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        j.o(this, context, z ? R.attr.motionDurationLong1 : R.attr.motionDurationMedium2);
        if (this.S) {
            return;
        }
        j.q(this, context, R.attr.motionPath);
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        G(transitionValues, this.i0, this.W, this.k0);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        G(transitionValues, this.h0, this.V, this.j0);
    }

    @Override // androidx.transition.Transition
    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        View e;
        View view;
        if (transitionValues != null && transitionValues2 != null) {
            RectF rectF = (RectF) transitionValues.values.get("materialContainerTransition:bounds");
            ShapeAppearanceModel shapeAppearanceModel = (ShapeAppearanceModel) transitionValues.values.get("materialContainerTransition:shapeAppearance");
            if (rectF != null && shapeAppearanceModel != null) {
                RectF rectF2 = (RectF) transitionValues2.values.get("materialContainerTransition:bounds");
                ShapeAppearanceModel shapeAppearanceModel2 = (ShapeAppearanceModel) transitionValues2.values.get("materialContainerTransition:shapeAppearance");
                if (rectF2 != null && shapeAppearanceModel2 != null) {
                    View view2 = transitionValues.view;
                    View view3 = transitionValues2.view;
                    View view4 = view3.getParent() != null ? view3 : view2;
                    if (this.U == view4.getId()) {
                        e = (View) view4.getParent();
                        view = view4;
                    } else {
                        e = j.e(view4, this.U);
                        view = null;
                    }
                    RectF g = j.g(e);
                    float f = -g.left;
                    float f2 = -g.top;
                    RectF E = E(e, view, f, f2);
                    rectF.offset(f, f2);
                    rectF2.offset(f, f2);
                    boolean L = L(rectF, rectF2);
                    if (!this.T) {
                        M(view4.getContext(), L);
                    }
                    d dVar = new d(getPathMotion(), view2, rectF, shapeAppearanceModel, H(this.q0, view2), view3, rectF2, shapeAppearanceModel2, H(this.r0, view3), this.a0, this.b0, this.c0, this.d0, L, this.p0, com.google.android.material.transition.b.a(this.f0, L), e.a(this.g0, L, rectF, rectF2), D(L), this.Q, null);
                    dVar.setBounds(Math.round(E.left), Math.round(E.top), Math.round(E.right), Math.round(E.bottom));
                    ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                    ofFloat.addUpdateListener(new a(this, dVar));
                    addListener(new b(e, dVar, view2, view3));
                    return ofFloat;
                }
                Log.w(s0, "Skipping due to null end bounds. Ensure end view is laid out and measured.");
                return null;
            }
            Log.w(s0, "Skipping due to null start bounds. Ensure start view is laid out and measured.");
        }
        return null;
    }

    @ColorInt
    public int getContainerColor() {
        return this.a0;
    }

    @IdRes
    public int getDrawingViewId() {
        return this.U;
    }

    @ColorInt
    public int getEndContainerColor() {
        return this.c0;
    }

    public float getEndElevation() {
        return this.r0;
    }

    @Nullable
    public ShapeAppearanceModel getEndShapeAppearanceModel() {
        return this.k0;
    }

    @Nullable
    public View getEndView() {
        return this.i0;
    }

    @IdRes
    public int getEndViewId() {
        return this.W;
    }

    public int getFadeMode() {
        return this.f0;
    }

    @Nullable
    public ProgressThresholds getFadeProgressThresholds() {
        return this.l0;
    }

    public int getFitMode() {
        return this.g0;
    }

    @Nullable
    public ProgressThresholds getScaleMaskProgressThresholds() {
        return this.n0;
    }

    @Nullable
    public ProgressThresholds getScaleProgressThresholds() {
        return this.m0;
    }

    @ColorInt
    public int getScrimColor() {
        return this.d0;
    }

    @Nullable
    public ProgressThresholds getShapeMaskProgressThresholds() {
        return this.o0;
    }

    @ColorInt
    public int getStartContainerColor() {
        return this.b0;
    }

    public float getStartElevation() {
        return this.q0;
    }

    @Nullable
    public ShapeAppearanceModel getStartShapeAppearanceModel() {
        return this.j0;
    }

    @Nullable
    public View getStartView() {
        return this.h0;
    }

    @IdRes
    public int getStartViewId() {
        return this.V;
    }

    public int getTransitionDirection() {
        return this.e0;
    }

    @Override // androidx.transition.Transition
    @Nullable
    public String[] getTransitionProperties() {
        return t0;
    }

    public boolean isDrawDebugEnabled() {
        return this.Q;
    }

    public boolean isElevationShadowEnabled() {
        return this.p0;
    }

    public boolean isHoldAtEndEnabled() {
        return this.R;
    }

    public void setAllContainerColors(@ColorInt int i) {
        this.a0 = i;
        this.b0 = i;
        this.c0 = i;
    }

    public void setContainerColor(@ColorInt int i) {
        this.a0 = i;
    }

    public void setDrawDebugEnabled(boolean z) {
        this.Q = z;
    }

    public void setDrawingViewId(@IdRes int i) {
        this.U = i;
    }

    public void setElevationShadowEnabled(boolean z) {
        this.p0 = z;
    }

    public void setEndContainerColor(@ColorInt int i) {
        this.c0 = i;
    }

    public void setEndElevation(float f) {
        this.r0 = f;
    }

    public void setEndShapeAppearanceModel(@Nullable ShapeAppearanceModel shapeAppearanceModel) {
        this.k0 = shapeAppearanceModel;
    }

    public void setEndView(@Nullable View view) {
        this.i0 = view;
    }

    public void setEndViewId(@IdRes int i) {
        this.W = i;
    }

    public void setFadeMode(int i) {
        this.f0 = i;
    }

    public void setFadeProgressThresholds(@Nullable ProgressThresholds progressThresholds) {
        this.l0 = progressThresholds;
    }

    public void setFitMode(int i) {
        this.g0 = i;
    }

    public void setHoldAtEndEnabled(boolean z) {
        this.R = z;
    }

    @Override // androidx.transition.Transition
    public void setPathMotion(@Nullable PathMotion pathMotion) {
        super.setPathMotion(pathMotion);
        this.S = true;
    }

    public void setScaleMaskProgressThresholds(@Nullable ProgressThresholds progressThresholds) {
        this.n0 = progressThresholds;
    }

    public void setScaleProgressThresholds(@Nullable ProgressThresholds progressThresholds) {
        this.m0 = progressThresholds;
    }

    public void setScrimColor(@ColorInt int i) {
        this.d0 = i;
    }

    public void setShapeMaskProgressThresholds(@Nullable ProgressThresholds progressThresholds) {
        this.o0 = progressThresholds;
    }

    public void setStartContainerColor(@ColorInt int i) {
        this.b0 = i;
    }

    public void setStartElevation(float f) {
        this.q0 = f;
    }

    public void setStartShapeAppearanceModel(@Nullable ShapeAppearanceModel shapeAppearanceModel) {
        this.j0 = shapeAppearanceModel;
    }

    public void setStartView(@Nullable View view) {
        this.h0 = view;
    }

    public void setStartViewId(@IdRes int i) {
        this.V = i;
    }

    public void setTransitionDirection(int i) {
        this.e0 = i;
    }

    public MaterialContainerTransform(@NonNull Context context, boolean z) {
        this.Q = false;
        this.R = false;
        this.S = false;
        this.T = false;
        this.U = 16908290;
        this.V = -1;
        this.W = -1;
        this.a0 = 0;
        this.b0 = 0;
        this.c0 = 0;
        this.d0 = 1375731712;
        this.e0 = 0;
        this.f0 = 0;
        this.g0 = 0;
        this.p0 = Build.VERSION.SDK_INT >= 28;
        this.q0 = -1.0f;
        this.r0 = -1.0f;
        M(context, z);
        this.T = true;
    }
}
