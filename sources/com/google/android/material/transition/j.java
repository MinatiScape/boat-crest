package com.google.android.material.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.PathParser;
import androidx.transition.PathMotion;
import androidx.transition.PatternPathMotion;
import androidx.transition.Transition;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
/* loaded from: classes10.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    public static final RectF f10414a = new RectF();

    /* loaded from: classes10.dex */
    public class a implements ShapeAppearanceModel.CornerSizeUnaryOperator {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ RectF f10415a;

        public a(RectF rectF) {
            this.f10415a = rectF;
        }

        @Override // com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator
        @NonNull
        public CornerSize apply(@NonNull CornerSize cornerSize) {
            return cornerSize instanceof RelativeCornerSize ? cornerSize : new RelativeCornerSize(cornerSize.getCornerSize(this.f10415a) / this.f10415a.height());
        }
    }

    /* loaded from: classes10.dex */
    public class b implements d {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ RectF f10416a;
        public final /* synthetic */ RectF b;
        public final /* synthetic */ float c;
        public final /* synthetic */ float d;
        public final /* synthetic */ float e;

        public b(RectF rectF, RectF rectF2, float f, float f2, float f3) {
            this.f10416a = rectF;
            this.b = rectF2;
            this.c = f;
            this.d = f2;
            this.e = f3;
        }

        @Override // com.google.android.material.transition.j.d
        @NonNull
        public CornerSize a(@NonNull CornerSize cornerSize, @NonNull CornerSize cornerSize2) {
            return new AbsoluteCornerSize(j.k(cornerSize.getCornerSize(this.f10416a), cornerSize2.getCornerSize(this.b), this.c, this.d, this.e));
        }
    }

    /* loaded from: classes10.dex */
    public interface c {
        void a(Canvas canvas);
    }

    /* loaded from: classes10.dex */
    public interface d {
        @NonNull
        CornerSize a(@NonNull CornerSize cornerSize, @NonNull CornerSize cornerSize2);
    }

    public static float a(@NonNull RectF rectF) {
        return rectF.width() * rectF.height();
    }

    public static ShapeAppearanceModel b(ShapeAppearanceModel shapeAppearanceModel, RectF rectF) {
        return shapeAppearanceModel.withTransformedCornerSizes(new a(rectF));
    }

    public static Shader c(@ColorInt int i) {
        return new LinearGradient(0.0f, 0.0f, 0.0f, 0.0f, i, i, Shader.TileMode.CLAMP);
    }

    @NonNull
    public static <T> T d(@Nullable T t, @NonNull T t2) {
        return t != null ? t : t2;
    }

    public static View e(View view, @IdRes int i) {
        String resourceName = view.getResources().getResourceName(i);
        while (view != null) {
            if (view.getId() != i) {
                ViewParent parent = view.getParent();
                if (!(parent instanceof View)) {
                    break;
                }
                view = (View) parent;
            } else {
                return view;
            }
        }
        throw new IllegalArgumentException(resourceName + " is not a valid ancestor");
    }

    public static View f(View view, @IdRes int i) {
        View findViewById = view.findViewById(i);
        return findViewById != null ? findViewById : e(view, i);
    }

    public static RectF g(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new RectF(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }

    public static RectF h(View view) {
        return new RectF(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    public static boolean i(ShapeAppearanceModel shapeAppearanceModel, RectF rectF) {
        return (shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(rectF) == 0.0f && shapeAppearanceModel.getTopRightCornerSize().getCornerSize(rectF) == 0.0f && shapeAppearanceModel.getBottomRightCornerSize().getCornerSize(rectF) == 0.0f && shapeAppearanceModel.getBottomLeftCornerSize().getCornerSize(rectF) == 0.0f) ? false : true;
    }

    public static float j(float f, float f2, float f3) {
        return f + (f3 * (f2 - f));
    }

    public static float k(float f, float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, @FloatRange(from = 0.0d, to = 1.0d) float f4, @FloatRange(from = 0.0d, to = 1.0d) float f5) {
        return l(f, f2, f3, f4, f5, false);
    }

    public static float l(float f, float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, @FloatRange(from = 0.0d, to = 1.0d) float f4, @FloatRange(from = 0.0d) float f5, boolean z) {
        if (!z || (f5 >= 0.0f && f5 <= 1.0f)) {
            return f5 < f3 ? f : f5 > f4 ? f2 : j(f, f2, (f5 - f3) / (f4 - f3));
        }
        return j(f, f2, f5);
    }

    public static int m(int i, int i2, @FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3) {
        return f3 < f ? i : f3 > f2 ? i2 : (int) j(i, i2, (f3 - f) / (f2 - f));
    }

    public static ShapeAppearanceModel n(ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, RectF rectF, RectF rectF2, @FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3) {
        return f3 < f ? shapeAppearanceModel : f3 > f2 ? shapeAppearanceModel2 : u(shapeAppearanceModel, shapeAppearanceModel2, rectF, new b(rectF, rectF2, f, f2, f3));
    }

    public static boolean o(Transition transition, Context context, @AttrRes int i) {
        int resolveThemeDuration;
        if (i == 0 || transition.getDuration() != -1 || (resolveThemeDuration = MotionUtils.resolveThemeDuration(context, i, -1)) == -1) {
            return false;
        }
        transition.setDuration(resolveThemeDuration);
        return true;
    }

    public static boolean p(Transition transition, Context context, @AttrRes int i, TimeInterpolator timeInterpolator) {
        if (i == 0 || transition.getInterpolator() != null) {
            return false;
        }
        transition.setInterpolator(MotionUtils.resolveThemeInterpolator(context, i, timeInterpolator));
        return true;
    }

    public static boolean q(Transition transition, Context context, @AttrRes int i) {
        PathMotion r;
        if (i == 0 || (r = r(context, i)) == null) {
            return false;
        }
        transition.setPathMotion(r);
        return true;
    }

    @Nullable
    public static PathMotion r(Context context, @AttrRes int i) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i, typedValue, true)) {
            int i2 = typedValue.type;
            if (i2 != 16) {
                if (i2 == 3) {
                    return new PatternPathMotion(PathParser.createPathFromPathData(String.valueOf(typedValue.string)));
                }
                throw new IllegalArgumentException("Motion path theme attribute must either be an enum value or path data string");
            }
            int i3 = typedValue.data;
            if (i3 == 0) {
                return null;
            }
            if (i3 == 1) {
                return new MaterialArcMotion();
            }
            throw new IllegalArgumentException("Invalid motion path type: " + i3);
        }
        return null;
    }

    public static int s(Canvas canvas, Rect rect, int i) {
        RectF rectF = f10414a;
        rectF.set(rect);
        if (Build.VERSION.SDK_INT >= 21) {
            return canvas.saveLayerAlpha(rectF, i);
        }
        return canvas.saveLayerAlpha(rectF.left, rectF.top, rectF.right, rectF.bottom, i, 31);
    }

    public static void t(Canvas canvas, Rect rect, float f, float f2, float f3, int i, c cVar) {
        if (i <= 0) {
            return;
        }
        int save = canvas.save();
        canvas.translate(f, f2);
        canvas.scale(f3, f3);
        if (i < 255) {
            s(canvas, rect, i);
        }
        cVar.a(canvas);
        canvas.restoreToCount(save);
    }

    public static ShapeAppearanceModel u(ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, RectF rectF, d dVar) {
        return (i(shapeAppearanceModel, rectF) ? shapeAppearanceModel : shapeAppearanceModel2).toBuilder().setTopLeftCornerSize(dVar.a(shapeAppearanceModel.getTopLeftCornerSize(), shapeAppearanceModel2.getTopLeftCornerSize())).setTopRightCornerSize(dVar.a(shapeAppearanceModel.getTopRightCornerSize(), shapeAppearanceModel2.getTopRightCornerSize())).setBottomLeftCornerSize(dVar.a(shapeAppearanceModel.getBottomLeftCornerSize(), shapeAppearanceModel2.getBottomLeftCornerSize())).setBottomRightCornerSize(dVar.a(shapeAppearanceModel.getBottomRightCornerSize(), shapeAppearanceModel2.getBottomRightCornerSize())).build();
    }
}
