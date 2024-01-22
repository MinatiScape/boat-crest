package com.google.android.material.transition.platform;

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
import android.transition.ArcMotion;
import android.transition.PathMotion;
import android.transition.Transition;
import android.transition.TransitionValues;
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
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.core.util.Preconditions;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.transition.platform.j;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@RequiresApi(21)
/* loaded from: classes10.dex */
public final class MaterialContainerTransform extends Transition {
    public static final int FADE_MODE_CROSS = 2;
    public static final int FADE_MODE_IN = 0;
    public static final int FADE_MODE_OUT = 1;
    public static final int FADE_MODE_THROUGH = 3;
    public static final int FIT_MODE_AUTO = 0;
    public static final int FIT_MODE_HEIGHT = 2;
    public static final int FIT_MODE_WIDTH = 1;
    public static final String G = MaterialContainerTransform.class.getSimpleName();
    public static final String[] H = {"materialContainerTransition:bounds", "materialContainerTransition:shapeAppearance"};
    public static final c I = new c(new ProgressThresholds(0.0f, 0.25f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.75f), null);
    public static final c J = new c(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.3f, 0.9f), null);
    public static final c K = new c(new ProgressThresholds(0.1f, 0.4f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 0.9f), null);
    public static final c L = new c(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.2f, 0.9f), null);
    public static final int TRANSITION_DIRECTION_AUTO = 0;
    public static final int TRANSITION_DIRECTION_ENTER = 1;
    public static final int TRANSITION_DIRECTION_RETURN = 2;
    @Nullable
    public ProgressThresholds A;
    @Nullable
    public ProgressThresholds B;
    @Nullable
    public ProgressThresholds C;
    public boolean D;
    public float E;
    public float F;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    @IdRes
    public int l;
    @IdRes
    public int m;
    @IdRes
    public int n;
    @ColorInt
    public int o;
    @ColorInt
    public int p;
    @ColorInt
    public int q;
    @ColorInt
    public int r;
    public int s;
    public int t;
    public int u;
    @Nullable
    public View v;
    @Nullable
    public View w;
    @Nullable
    public ShapeAppearanceModel x;
    @Nullable
    public ShapeAppearanceModel y;
    @Nullable
    public ProgressThresholds z;

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
        public final float f10419a;
        @FloatRange(from = 0.0d, to = 1.0d)
        public final float b;

        public ProgressThresholds(@FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
            this.f10419a = f;
            this.b = f2;
        }

        @FloatRange(from = 0.0d, to = 1.0d)
        public float getEnd() {
            return this.b;
        }

        @FloatRange(from = 0.0d, to = 1.0d)
        public float getStart() {
            return this.f10419a;
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

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ View f10420a;
        public final /* synthetic */ d b;
        public final /* synthetic */ View c;
        public final /* synthetic */ View d;

        public b(View view, d dVar, View view2, View view3) {
            this.f10420a = view;
            this.b = dVar;
            this.c = view2;
            this.d = view3;
        }

        @Override // com.google.android.material.transition.platform.i, android.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            MaterialContainerTransform.this.removeListener(this);
            if (MaterialContainerTransform.this.i) {
                return;
            }
            this.c.setAlpha(1.0f);
            this.d.setAlpha(1.0f);
            ViewUtils.getOverlay(this.f10420a).remove(this.b);
        }

        @Override // com.google.android.material.transition.platform.i, android.transition.Transition.TransitionListener
        public void onTransitionStart(@NonNull Transition transition) {
            ViewUtils.getOverlay(this.f10420a).add(this.b);
            this.c.setAlpha(0.0f);
            this.d.setAlpha(0.0f);
        }
    }

    /* loaded from: classes10.dex */
    public static class c {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final ProgressThresholds f10421a;
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
            this.f10421a = progressThresholds;
            this.b = progressThresholds2;
            this.c = progressThresholds3;
            this.d = progressThresholds4;
        }
    }

    /* loaded from: classes10.dex */
    public static final class d extends Drawable {
        public final c A;
        public final com.google.android.material.transition.platform.a B;
        public final com.google.android.material.transition.platform.d C;
        public final boolean D;
        public final Paint E;
        public final Path F;
        public com.google.android.material.transition.platform.c G;
        public f H;
        public RectF I;
        public float J;
        public float K;
        public float L;

        /* renamed from: a  reason: collision with root package name */
        public final View f10422a;
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

            @Override // com.google.android.material.transition.platform.j.c
            public void a(Canvas canvas) {
                d.this.f10422a.draw(canvas);
            }
        }

        /* loaded from: classes10.dex */
        public class b implements j.c {
            public b() {
            }

            @Override // com.google.android.material.transition.platform.j.c
            public void a(Canvas canvas) {
                d.this.e.draw(canvas);
            }
        }

        public /* synthetic */ d(PathMotion pathMotion, View view, RectF rectF, ShapeAppearanceModel shapeAppearanceModel, float f, View view2, RectF rectF2, ShapeAppearanceModel shapeAppearanceModel2, float f2, int i, int i2, int i3, int i4, boolean z, boolean z2, com.google.android.material.transition.platform.a aVar, com.google.android.material.transition.platform.d dVar, c cVar, boolean z3, a aVar2) {
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
            j.u(canvas, bounds, rectF.left, rectF.top, this.H.b, this.G.b, new b());
        }

        public final void l(Canvas canvas) {
            n(canvas, this.j);
            Rect bounds = getBounds();
            RectF rectF = this.w;
            j.u(canvas, bounds, rectF.left, rectF.top, this.H.f10434a, this.G.f10432a, new a());
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
            this.m.setAlpha((int) (this.r ? j.k(0.0f, 255.0f, f) : j.k(255.0f, 0.0f, f)));
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
            f a2 = this.C.a(f, ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.b.f10419a))).floatValue(), ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.b.b))).floatValue(), this.b.width(), this.b.height(), this.f.width(), this.f.height());
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
            float floatValue = ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.c.f10419a))).floatValue();
            float floatValue2 = ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.c.b))).floatValue();
            boolean b2 = this.C.b(this.H);
            RectF rectF3 = b2 ? this.x : this.z;
            float l = j.l(0.0f, 1.0f, floatValue, floatValue2, f);
            if (!b2) {
                l = 1.0f - l;
            }
            this.C.c(rectF3, l, this.H);
            this.I = new RectF(Math.min(this.x.left, this.z.left), Math.min(this.x.top, this.z.top), Math.max(this.x.right, this.z.right), Math.max(this.x.bottom, this.z.bottom));
            this.n.b(f, this.c, this.g, this.w, this.x, this.z, this.A.d);
            this.J = j.k(this.d, this.h, f);
            float d = d(this.I, this.s);
            float e = e(this.I, this.t);
            float f10 = this.J;
            float f11 = (int) (e * f10);
            this.K = f11;
            this.l.setShadowLayer(f10, (int) (d * f10), f11, 754974720);
            this.G = this.B.a(f, ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.f10421a.f10419a))).floatValue(), ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.f10421a.b))).floatValue(), 0.35f);
            if (this.j.getColor() != 0) {
                this.j.setAlpha(this.G.f10432a);
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

        public d(PathMotion pathMotion, View view, RectF rectF, ShapeAppearanceModel shapeAppearanceModel, float f, View view2, RectF rectF2, ShapeAppearanceModel shapeAppearanceModel2, float f2, @ColorInt int i, @ColorInt int i2, @ColorInt int i3, int i4, boolean z, boolean z2, com.google.android.material.transition.platform.a aVar, com.google.android.material.transition.platform.d dVar, c cVar, boolean z3) {
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
            this.f10422a = view;
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
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = 16908290;
        this.m = -1;
        this.n = -1;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 1375731712;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.D = Build.VERSION.SDK_INT >= 28;
        this.E = -1.0f;
        this.F = -1.0f;
    }

    public static RectF c(View view, @Nullable View view2, float f, float f2) {
        if (view2 != null) {
            RectF g = j.g(view2);
            g.offset(f, f2);
            return g;
        }
        return new RectF(0.0f, 0.0f, view.getWidth(), view.getHeight());
    }

    public static ShapeAppearanceModel d(@NonNull View view, @NonNull RectF rectF, @Nullable ShapeAppearanceModel shapeAppearanceModel) {
        return j.b(g(view, shapeAppearanceModel), rectF);
    }

    public static void e(@NonNull TransitionValues transitionValues, @Nullable View view, @IdRes int i, @Nullable ShapeAppearanceModel shapeAppearanceModel) {
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
        transitionValues.values.put("materialContainerTransition:shapeAppearance", d(view3, h, shapeAppearanceModel));
    }

    public static float f(float f, View view) {
        return f != -1.0f ? f : ViewCompat.getElevation(view);
    }

    public static ShapeAppearanceModel g(@NonNull View view, @Nullable ShapeAppearanceModel shapeAppearanceModel) {
        if (shapeAppearanceModel != null) {
            return shapeAppearanceModel;
        }
        int i = R.id.mtrl_motion_snapshot_view;
        if (view.getTag(i) instanceof ShapeAppearanceModel) {
            return (ShapeAppearanceModel) view.getTag(i);
        }
        Context context = view.getContext();
        int i2 = i(context);
        if (i2 != -1) {
            return ShapeAppearanceModel.builder(context, i2, 0).build();
        }
        if (view instanceof Shapeable) {
            return ((Shapeable) view).getShapeAppearanceModel();
        }
        return ShapeAppearanceModel.builder().build();
    }

    @StyleRes
    public static int i(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.transitionShapeAppearance});
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public final c b(boolean z) {
        PathMotion pathMotion = getPathMotion();
        if (!(pathMotion instanceof ArcMotion) && !(pathMotion instanceof MaterialArcMotion)) {
            return h(z, I, J);
        }
        return h(z, K, L);
    }

    @Override // android.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        e(transitionValues, this.w, this.n, this.y);
    }

    @Override // android.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        e(transitionValues, this.v, this.m, this.x);
    }

    @Override // android.transition.Transition
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
                    if (this.l == view4.getId()) {
                        e = (View) view4.getParent();
                        view = view4;
                    } else {
                        e = j.e(view4, this.l);
                        view = null;
                    }
                    RectF g = j.g(e);
                    float f = -g.left;
                    float f2 = -g.top;
                    RectF c2 = c(e, view, f, f2);
                    rectF.offset(f, f2);
                    rectF2.offset(f, f2);
                    boolean j = j(rectF, rectF2);
                    if (!this.k) {
                        k(view4.getContext(), j);
                    }
                    d dVar = new d(getPathMotion(), view2, rectF, shapeAppearanceModel, f(this.E, view2), view3, rectF2, shapeAppearanceModel2, f(this.F, view3), this.o, this.p, this.q, this.r, j, this.D, com.google.android.material.transition.platform.b.a(this.t, j), e.a(this.u, j, rectF, rectF2), b(j), this.h, null);
                    dVar.setBounds(Math.round(c2.left), Math.round(c2.top), Math.round(c2.right), Math.round(c2.bottom));
                    ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                    ofFloat.addUpdateListener(new a(this, dVar));
                    addListener(new b(e, dVar, view2, view3));
                    return ofFloat;
                }
                Log.w(G, "Skipping due to null end bounds. Ensure end view is laid out and measured.");
                return null;
            }
            Log.w(G, "Skipping due to null start bounds. Ensure start view is laid out and measured.");
        }
        return null;
    }

    @ColorInt
    public int getContainerColor() {
        return this.o;
    }

    @IdRes
    public int getDrawingViewId() {
        return this.l;
    }

    @ColorInt
    public int getEndContainerColor() {
        return this.q;
    }

    public float getEndElevation() {
        return this.F;
    }

    @Nullable
    public ShapeAppearanceModel getEndShapeAppearanceModel() {
        return this.y;
    }

    @Nullable
    public View getEndView() {
        return this.w;
    }

    @IdRes
    public int getEndViewId() {
        return this.n;
    }

    public int getFadeMode() {
        return this.t;
    }

    @Nullable
    public ProgressThresholds getFadeProgressThresholds() {
        return this.z;
    }

    public int getFitMode() {
        return this.u;
    }

    @Nullable
    public ProgressThresholds getScaleMaskProgressThresholds() {
        return this.B;
    }

    @Nullable
    public ProgressThresholds getScaleProgressThresholds() {
        return this.A;
    }

    @ColorInt
    public int getScrimColor() {
        return this.r;
    }

    @Nullable
    public ProgressThresholds getShapeMaskProgressThresholds() {
        return this.C;
    }

    @ColorInt
    public int getStartContainerColor() {
        return this.p;
    }

    public float getStartElevation() {
        return this.E;
    }

    @Nullable
    public ShapeAppearanceModel getStartShapeAppearanceModel() {
        return this.x;
    }

    @Nullable
    public View getStartView() {
        return this.v;
    }

    @IdRes
    public int getStartViewId() {
        return this.m;
    }

    public int getTransitionDirection() {
        return this.s;
    }

    @Override // android.transition.Transition
    @Nullable
    public String[] getTransitionProperties() {
        return H;
    }

    public final c h(boolean z, c cVar, c cVar2) {
        if (!z) {
            cVar = cVar2;
        }
        return new c((ProgressThresholds) j.d(this.z, cVar.f10421a), (ProgressThresholds) j.d(this.A, cVar.b), (ProgressThresholds) j.d(this.B, cVar.c), (ProgressThresholds) j.d(this.C, cVar.d), null);
    }

    public boolean isDrawDebugEnabled() {
        return this.h;
    }

    public boolean isElevationShadowEnabled() {
        return this.D;
    }

    public boolean isHoldAtEndEnabled() {
        return this.i;
    }

    public final boolean j(@NonNull RectF rectF, @NonNull RectF rectF2) {
        int i = this.s;
        if (i == 0) {
            return j.a(rectF2) > j.a(rectF);
        } else if (i != 1) {
            if (i == 2) {
                return false;
            }
            throw new IllegalArgumentException("Invalid transition direction: " + this.s);
        } else {
            return true;
        }
    }

    public final void k(Context context, boolean z) {
        j.q(this, context, R.attr.motionEasingStandard, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        j.p(this, context, z ? R.attr.motionDurationLong1 : R.attr.motionDurationMedium2);
        if (this.j) {
            return;
        }
        j.r(this, context, R.attr.motionPath);
    }

    public void setAllContainerColors(@ColorInt int i) {
        this.o = i;
        this.p = i;
        this.q = i;
    }

    public void setContainerColor(@ColorInt int i) {
        this.o = i;
    }

    public void setDrawDebugEnabled(boolean z) {
        this.h = z;
    }

    public void setDrawingViewId(@IdRes int i) {
        this.l = i;
    }

    public void setElevationShadowEnabled(boolean z) {
        this.D = z;
    }

    public void setEndContainerColor(@ColorInt int i) {
        this.q = i;
    }

    public void setEndElevation(float f) {
        this.F = f;
    }

    public void setEndShapeAppearanceModel(@Nullable ShapeAppearanceModel shapeAppearanceModel) {
        this.y = shapeAppearanceModel;
    }

    public void setEndView(@Nullable View view) {
        this.w = view;
    }

    public void setEndViewId(@IdRes int i) {
        this.n = i;
    }

    public void setFadeMode(int i) {
        this.t = i;
    }

    public void setFadeProgressThresholds(@Nullable ProgressThresholds progressThresholds) {
        this.z = progressThresholds;
    }

    public void setFitMode(int i) {
        this.u = i;
    }

    public void setHoldAtEndEnabled(boolean z) {
        this.i = z;
    }

    @Override // android.transition.Transition
    public void setPathMotion(@Nullable PathMotion pathMotion) {
        super.setPathMotion(pathMotion);
        this.j = true;
    }

    public void setScaleMaskProgressThresholds(@Nullable ProgressThresholds progressThresholds) {
        this.B = progressThresholds;
    }

    public void setScaleProgressThresholds(@Nullable ProgressThresholds progressThresholds) {
        this.A = progressThresholds;
    }

    public void setScrimColor(@ColorInt int i) {
        this.r = i;
    }

    public void setShapeMaskProgressThresholds(@Nullable ProgressThresholds progressThresholds) {
        this.C = progressThresholds;
    }

    public void setStartContainerColor(@ColorInt int i) {
        this.p = i;
    }

    public void setStartElevation(float f) {
        this.E = f;
    }

    public void setStartShapeAppearanceModel(@Nullable ShapeAppearanceModel shapeAppearanceModel) {
        this.x = shapeAppearanceModel;
    }

    public void setStartView(@Nullable View view) {
        this.v = view;
    }

    public void setStartViewId(@IdRes int i) {
        this.m = i;
    }

    public void setTransitionDirection(int i) {
        this.s = i;
    }

    public MaterialContainerTransform(@NonNull Context context, boolean z) {
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = 16908290;
        this.m = -1;
        this.n = -1;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 1375731712;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.D = Build.VERSION.SDK_INT >= 28;
        this.E = -1.0f;
        this.F = -1.0f;
        k(context, z);
        this.k = true;
    }
}
