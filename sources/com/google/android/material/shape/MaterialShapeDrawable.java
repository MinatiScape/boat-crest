package com.google.android.material.shape;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.util.ObjectsCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.ShapePath;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.BitSet;
/* loaded from: classes10.dex */
public class MaterialShapeDrawable extends Drawable implements TintAwareDrawable, Shapeable {
    public static final String E = MaterialShapeDrawable.class.getSimpleName();
    public static final Paint F;
    public static final int SHADOW_COMPAT_MODE_ALWAYS = 2;
    public static final int SHADOW_COMPAT_MODE_DEFAULT = 0;
    public static final int SHADOW_COMPAT_MODE_NEVER = 1;
    @Nullable
    public PorterDuffColorFilter A;
    public int B;
    @NonNull
    public final RectF C;
    public boolean D;
    public c h;
    public final ShapePath.d[] i;
    public final ShapePath.d[] j;
    public final BitSet k;
    public boolean l;
    public final Matrix m;
    public final Path n;
    public final Path o;
    public final RectF p;
    public final RectF q;
    public final Region r;
    public final Region s;
    public ShapeAppearanceModel t;
    public final Paint u;
    public final Paint v;
    public final ShadowRenderer w;
    @NonNull
    public final ShapeAppearancePathProvider.PathListener x;
    public final ShapeAppearancePathProvider y;
    @Nullable
    public PorterDuffColorFilter z;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface CompatibilityShadowMode {
    }

    /* loaded from: classes10.dex */
    public class a implements ShapeAppearancePathProvider.PathListener {
        public a() {
        }

        @Override // com.google.android.material.shape.ShapeAppearancePathProvider.PathListener
        public void onCornerPathCreated(@NonNull ShapePath shapePath, Matrix matrix, int i) {
            MaterialShapeDrawable.this.k.set(i, shapePath.c());
            MaterialShapeDrawable.this.i[i] = shapePath.d(matrix);
        }

        @Override // com.google.android.material.shape.ShapeAppearancePathProvider.PathListener
        public void onEdgePathCreated(@NonNull ShapePath shapePath, Matrix matrix, int i) {
            MaterialShapeDrawable.this.k.set(i + 4, shapePath.c());
            MaterialShapeDrawable.this.j[i] = shapePath.d(matrix);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements ShapeAppearanceModel.CornerSizeUnaryOperator {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ float f10351a;

        public b(MaterialShapeDrawable materialShapeDrawable, float f) {
            this.f10351a = f;
        }

        @Override // com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator
        @NonNull
        public CornerSize apply(@NonNull CornerSize cornerSize) {
            return cornerSize instanceof RelativeCornerSize ? cornerSize : new AdjustedCornerSize(this.f10351a, cornerSize);
        }
    }

    static {
        Paint paint = new Paint(1);
        F = paint;
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public /* synthetic */ MaterialShapeDrawable(c cVar, a aVar) {
        this(cVar);
    }

    @NonNull
    public static MaterialShapeDrawable createWithElevationOverlay(Context context) {
        return createWithElevationOverlay(context, 0.0f);
    }

    public static int t(int i, int i2) {
        return (i * (i2 + (i2 >>> 7))) >>> 8;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void calculatePathForSize(@NonNull RectF rectF, @NonNull Path path) {
        ShapeAppearancePathProvider shapeAppearancePathProvider = this.y;
        c cVar = this.h;
        shapeAppearancePathProvider.calculatePath(cVar.f10352a, cVar.k, rectF, this.x, path);
    }

    @ColorInt
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int compositeElevationOverlayIfNeeded(@ColorInt int i) {
        float z = getZ() + getParentAbsoluteElevation();
        ElevationOverlayProvider elevationOverlayProvider = this.h.b;
        return elevationOverlayProvider != null ? elevationOverlayProvider.compositeOverlayIfNeeded(i, z) : i;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        this.u.setColorFilter(this.z);
        int alpha = this.u.getAlpha();
        this.u.setAlpha(t(alpha, this.h.m));
        this.v.setColorFilter(this.A);
        this.v.setStrokeWidth(this.h.l);
        int alpha2 = this.v.getAlpha();
        this.v.setAlpha(t(alpha2, this.h.m));
        if (this.l) {
            g();
            f(getBoundsAsRectF(), this.n);
            this.l = false;
        }
        s(canvas);
        if (p()) {
            k(canvas);
        }
        if (q()) {
            drawStrokeShape(canvas);
        }
        this.u.setAlpha(alpha);
        this.v.setAlpha(alpha2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void drawShape(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull Path path, @NonNull RectF rectF) {
        l(canvas, paint, path, this.h.f10352a, rectF);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void drawStrokeShape(@NonNull Canvas canvas) {
        l(canvas, this.v, this.o, this.t, m());
    }

    @Nullable
    public final PorterDuffColorFilter e(@NonNull Paint paint, boolean z) {
        if (z) {
            int color = paint.getColor();
            int compositeElevationOverlayIfNeeded = compositeElevationOverlayIfNeeded(color);
            this.B = compositeElevationOverlayIfNeeded;
            if (compositeElevationOverlayIfNeeded != color) {
                return new PorterDuffColorFilter(compositeElevationOverlayIfNeeded, PorterDuff.Mode.SRC_IN);
            }
            return null;
        }
        return null;
    }

    public final void f(@NonNull RectF rectF, @NonNull Path path) {
        calculatePathForSize(rectF, path);
        if (this.h.j != 1.0f) {
            this.m.reset();
            Matrix matrix = this.m;
            float f = this.h.j;
            matrix.setScale(f, f, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.m);
        }
        path.computeBounds(this.C, true);
    }

    public final void g() {
        ShapeAppearanceModel withTransformedCornerSizes = getShapeAppearanceModel().withTransformedCornerSizes(new b(this, -n()));
        this.t = withTransformedCornerSizes;
        this.y.calculatePath(withTransformedCornerSizes, this.h.k, m(), this.o);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.h.m;
    }

    public float getBottomLeftCornerResolvedSize() {
        return this.h.f10352a.getBottomLeftCornerSize().getCornerSize(getBoundsAsRectF());
    }

    public float getBottomRightCornerResolvedSize() {
        return this.h.f10352a.getBottomRightCornerSize().getCornerSize(getBoundsAsRectF());
    }

    @NonNull
    public RectF getBoundsAsRectF() {
        this.p.set(getBounds());
        return this.p;
    }

    @Override // android.graphics.drawable.Drawable
    @Nullable
    public Drawable.ConstantState getConstantState() {
        return this.h;
    }

    public float getElevation() {
        return this.h.o;
    }

    @Nullable
    public ColorStateList getFillColor() {
        return this.h.d;
    }

    public float getInterpolation() {
        return this.h.k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(@NonNull Outline outline) {
        if (this.h.q == 2) {
            return;
        }
        if (isRoundRect()) {
            outline.setRoundRect(getBounds(), getTopLeftCornerResolvedSize() * this.h.k);
            return;
        }
        f(getBoundsAsRectF(), this.n);
        if (this.n.isConvex() || Build.VERSION.SDK_INT >= 29) {
            try {
                outline.setConvexPath(this.n);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(@NonNull Rect rect) {
        Rect rect2 = this.h.i;
        if (rect2 != null) {
            rect.set(rect2);
            return true;
        }
        return super.getPadding(rect);
    }

    public Paint.Style getPaintStyle() {
        return this.h.v;
    }

    public float getParentAbsoluteElevation() {
        return this.h.n;
    }

    @Deprecated
    public void getPathForSize(int i, int i2, @NonNull Path path) {
        calculatePathForSize(new RectF(0.0f, 0.0f, i, i2), path);
    }

    @ColorInt
    public int getResolvedTintColor() {
        return this.B;
    }

    public float getScale() {
        return this.h.j;
    }

    public int getShadowCompatRotation() {
        return this.h.t;
    }

    public int getShadowCompatibilityMode() {
        return this.h.q;
    }

    @Deprecated
    public int getShadowElevation() {
        return (int) getElevation();
    }

    public int getShadowOffsetX() {
        c cVar = this.h;
        return (int) (cVar.s * Math.sin(Math.toRadians(cVar.t)));
    }

    public int getShadowOffsetY() {
        c cVar = this.h;
        return (int) (cVar.s * Math.cos(Math.toRadians(cVar.t)));
    }

    public int getShadowRadius() {
        return this.h.r;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getShadowVerticalOffset() {
        return this.h.s;
    }

    @Override // com.google.android.material.shape.Shapeable
    @NonNull
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.h.f10352a;
    }

    @Nullable
    @Deprecated
    public ShapePathModel getShapedViewModel() {
        ShapeAppearanceModel shapeAppearanceModel = getShapeAppearanceModel();
        if (shapeAppearanceModel instanceof ShapePathModel) {
            return (ShapePathModel) shapeAppearanceModel;
        }
        return null;
    }

    @Nullable
    public ColorStateList getStrokeColor() {
        return this.h.e;
    }

    @Nullable
    public ColorStateList getStrokeTintList() {
        return this.h.f;
    }

    public float getStrokeWidth() {
        return this.h.l;
    }

    @Nullable
    public ColorStateList getTintList() {
        return this.h.g;
    }

    public float getTopLeftCornerResolvedSize() {
        return this.h.f10352a.getTopLeftCornerSize().getCornerSize(getBoundsAsRectF());
    }

    public float getTopRightCornerResolvedSize() {
        return this.h.f10352a.getTopRightCornerSize().getCornerSize(getBoundsAsRectF());
    }

    public float getTranslationZ() {
        return this.h.p;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        this.r.set(getBounds());
        f(getBoundsAsRectF(), this.n);
        this.s.setPath(this.n, this.r);
        this.r.op(this.s, Region.Op.DIFFERENCE);
        return this.r;
    }

    public float getZ() {
        return getElevation() + getTranslationZ();
    }

    @NonNull
    public final PorterDuffColorFilter h(@NonNull ColorStateList colorStateList, @NonNull PorterDuff.Mode mode, boolean z) {
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = compositeElevationOverlayIfNeeded(colorForState);
        }
        this.B = colorForState;
        return new PorterDuffColorFilter(colorForState, mode);
    }

    @NonNull
    public final PorterDuffColorFilter i(@Nullable ColorStateList colorStateList, @Nullable PorterDuff.Mode mode, @NonNull Paint paint, boolean z) {
        if (colorStateList != null && mode != null) {
            return h(colorStateList, mode, z);
        }
        return e(paint, z);
    }

    public void initializeElevationOverlay(Context context) {
        this.h.b = new ElevationOverlayProvider(context);
        x();
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.l = true;
        super.invalidateSelf();
    }

    public boolean isElevationOverlayEnabled() {
        ElevationOverlayProvider elevationOverlayProvider = this.h.b;
        return elevationOverlayProvider != null && elevationOverlayProvider.isThemeElevationOverlayEnabled();
    }

    public boolean isElevationOverlayInitialized() {
        return this.h.b != null;
    }

    public boolean isPointInTransparentRegion(int i, int i2) {
        return getTransparentRegion().contains(i, i2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isRoundRect() {
        return this.h.f10352a.isRoundRect(getBoundsAsRectF());
    }

    @Deprecated
    public boolean isShadowEnabled() {
        int i = this.h.q;
        return i == 0 || i == 2;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.h.g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.h.f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.h.e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.h.d) != null && colorStateList4.isStateful())));
    }

    public final void j(@NonNull Canvas canvas) {
        if (this.k.cardinality() > 0) {
            Log.w(E, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.h.s != 0) {
            canvas.drawPath(this.n, this.w.getShadowPaint());
        }
        for (int i = 0; i < 4; i++) {
            this.i[i].b(this.w, this.h.r, canvas);
            this.j[i].b(this.w, this.h.r, canvas);
        }
        if (this.D) {
            int shadowOffsetX = getShadowOffsetX();
            int shadowOffsetY = getShadowOffsetY();
            canvas.translate(-shadowOffsetX, -shadowOffsetY);
            canvas.drawPath(this.n, F);
            canvas.translate(shadowOffsetX, shadowOffsetY);
        }
    }

    public final void k(@NonNull Canvas canvas) {
        l(canvas, this.u, this.n, this.h.f10352a, getBoundsAsRectF());
    }

    public final void l(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull Path path, @NonNull ShapeAppearanceModel shapeAppearanceModel, @NonNull RectF rectF) {
        if (shapeAppearanceModel.isRoundRect(rectF)) {
            float cornerSize = shapeAppearanceModel.getTopRightCornerSize().getCornerSize(rectF) * this.h.k;
            canvas.drawRoundRect(rectF, cornerSize, cornerSize, paint);
            return;
        }
        canvas.drawPath(path, paint);
    }

    @NonNull
    public final RectF m() {
        this.q.set(getBoundsAsRectF());
        float n = n();
        this.q.inset(n, n);
        return this.q;
    }

    @Override // android.graphics.drawable.Drawable
    @NonNull
    public Drawable mutate() {
        this.h = new c(this.h);
        return this;
    }

    public final float n() {
        if (q()) {
            return this.v.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    public final boolean o() {
        c cVar = this.h;
        int i = cVar.q;
        return i != 1 && cVar.r > 0 && (i == 2 || requiresCompatShadow());
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.l = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        boolean z = v(iArr) || w();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    public final boolean p() {
        Paint.Style style = this.h.v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    public final boolean q() {
        Paint.Style style = this.h.v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.v.getStrokeWidth() > 0.0f;
    }

    public final void r() {
        super.invalidateSelf();
    }

    public boolean requiresCompatShadow() {
        int i = Build.VERSION.SDK_INT;
        return i < 21 || !(isRoundRect() || this.n.isConvex() || i >= 29);
    }

    public final void s(@NonNull Canvas canvas) {
        if (o()) {
            canvas.save();
            u(canvas);
            if (!this.D) {
                j(canvas);
                canvas.restore();
                return;
            }
            int width = (int) (this.C.width() - getBounds().width());
            int height = (int) (this.C.height() - getBounds().height());
            if (width >= 0 && height >= 0) {
                Bitmap createBitmap = Bitmap.createBitmap(((int) this.C.width()) + (this.h.r * 2) + width, ((int) this.C.height()) + (this.h.r * 2) + height, Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(createBitmap);
                float f = (getBounds().left - this.h.r) - width;
                float f2 = (getBounds().top - this.h.r) - height;
                canvas2.translate(-f, -f2);
                j(canvas2);
                canvas.drawBitmap(createBitmap, f, f2, (Paint) null);
                createBitmap.recycle();
                canvas.restore();
                return;
            }
            throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
        c cVar = this.h;
        if (cVar.m != i) {
            cVar.m = i;
            r();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.h.c = colorFilter;
        r();
    }

    public void setCornerSize(float f) {
        setShapeAppearanceModel(this.h.f10352a.withCornerSize(f));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setEdgeIntersectionCheckEnable(boolean z) {
        this.y.k(z);
    }

    public void setElevation(float f) {
        c cVar = this.h;
        if (cVar.o != f) {
            cVar.o = f;
            x();
        }
    }

    public void setFillColor(@Nullable ColorStateList colorStateList) {
        c cVar = this.h;
        if (cVar.d != colorStateList) {
            cVar.d = colorStateList;
            onStateChange(getState());
        }
    }

    public void setInterpolation(float f) {
        c cVar = this.h;
        if (cVar.k != f) {
            cVar.k = f;
            this.l = true;
            invalidateSelf();
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        c cVar = this.h;
        if (cVar.i == null) {
            cVar.i = new Rect();
        }
        this.h.i.set(i, i2, i3, i4);
        invalidateSelf();
    }

    public void setPaintStyle(Paint.Style style) {
        this.h.v = style;
        r();
    }

    public void setParentAbsoluteElevation(float f) {
        c cVar = this.h;
        if (cVar.n != f) {
            cVar.n = f;
            x();
        }
    }

    public void setScale(float f) {
        c cVar = this.h;
        if (cVar.j != f) {
            cVar.j = f;
            invalidateSelf();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setShadowBitmapDrawingEnable(boolean z) {
        this.D = z;
    }

    public void setShadowColor(int i) {
        this.w.setShadowColor(i);
        this.h.u = false;
        r();
    }

    public void setShadowCompatRotation(int i) {
        c cVar = this.h;
        if (cVar.t != i) {
            cVar.t = i;
            r();
        }
    }

    public void setShadowCompatibilityMode(int i) {
        c cVar = this.h;
        if (cVar.q != i) {
            cVar.q = i;
            r();
        }
    }

    @Deprecated
    public void setShadowElevation(int i) {
        setElevation(i);
    }

    @Deprecated
    public void setShadowEnabled(boolean z) {
        setShadowCompatibilityMode(!z ? 1 : 0);
    }

    @Deprecated
    public void setShadowRadius(int i) {
        this.h.r = i;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setShadowVerticalOffset(int i) {
        c cVar = this.h;
        if (cVar.s != i) {
            cVar.s = i;
            r();
        }
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.h.f10352a = shapeAppearanceModel;
        invalidateSelf();
    }

    @Deprecated
    public void setShapedViewModel(@NonNull ShapePathModel shapePathModel) {
        setShapeAppearanceModel(shapePathModel);
    }

    public void setStroke(float f, @ColorInt int i) {
        setStrokeWidth(f);
        setStrokeColor(ColorStateList.valueOf(i));
    }

    public void setStrokeColor(@Nullable ColorStateList colorStateList) {
        c cVar = this.h;
        if (cVar.e != colorStateList) {
            cVar.e = colorStateList;
            onStateChange(getState());
        }
    }

    public void setStrokeTint(ColorStateList colorStateList) {
        this.h.f = colorStateList;
        w();
        r();
    }

    public void setStrokeWidth(float f) {
        this.h.l = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTint(@ColorInt int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(@Nullable ColorStateList colorStateList) {
        this.h.g = colorStateList;
        w();
        r();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(@Nullable PorterDuff.Mode mode) {
        c cVar = this.h;
        if (cVar.h != mode) {
            cVar.h = mode;
            w();
            r();
        }
    }

    public void setTranslationZ(float f) {
        c cVar = this.h;
        if (cVar.p != f) {
            cVar.p = f;
            x();
        }
    }

    public void setUseTintColorForShadow(boolean z) {
        c cVar = this.h;
        if (cVar.u != z) {
            cVar.u = z;
            invalidateSelf();
        }
    }

    public void setZ(float f) {
        setTranslationZ(f - getElevation());
    }

    public final void u(@NonNull Canvas canvas) {
        int shadowOffsetX = getShadowOffsetX();
        int shadowOffsetY = getShadowOffsetY();
        if (Build.VERSION.SDK_INT < 21 && this.D) {
            Rect clipBounds = canvas.getClipBounds();
            int i = this.h.r;
            clipBounds.inset(-i, -i);
            clipBounds.offset(shadowOffsetX, shadowOffsetY);
            canvas.clipRect(clipBounds, Region.Op.REPLACE);
        }
        canvas.translate(shadowOffsetX, shadowOffsetY);
    }

    public final boolean v(int[] iArr) {
        boolean z;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.h.d == null || color2 == (colorForState2 = this.h.d.getColorForState(iArr, (color2 = this.u.getColor())))) {
            z = false;
        } else {
            this.u.setColor(colorForState2);
            z = true;
        }
        if (this.h.e == null || color == (colorForState = this.h.e.getColorForState(iArr, (color = this.v.getColor())))) {
            return z;
        }
        this.v.setColor(colorForState);
        return true;
    }

    public final boolean w() {
        PorterDuffColorFilter porterDuffColorFilter = this.z;
        PorterDuffColorFilter porterDuffColorFilter2 = this.A;
        c cVar = this.h;
        this.z = i(cVar.g, cVar.h, this.u, true);
        c cVar2 = this.h;
        this.A = i(cVar2.f, cVar2.h, this.v, false);
        c cVar3 = this.h;
        if (cVar3.u) {
            this.w.setShadowColor(cVar3.g.getColorForState(getState(), 0));
        }
        return (ObjectsCompat.equals(porterDuffColorFilter, this.z) && ObjectsCompat.equals(porterDuffColorFilter2, this.A)) ? false : true;
    }

    public final void x() {
        float z = getZ();
        this.h.r = (int) Math.ceil(0.75f * z);
        this.h.s = (int) Math.ceil(z * 0.25f);
        w();
        r();
    }

    public MaterialShapeDrawable() {
        this(new ShapeAppearanceModel());
    }

    @NonNull
    public static MaterialShapeDrawable createWithElevationOverlay(Context context, float f) {
        int color = MaterialColors.getColor(context, R.attr.colorSurface, MaterialShapeDrawable.class.getSimpleName());
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        materialShapeDrawable.initializeElevationOverlay(context);
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(color));
        materialShapeDrawable.setElevation(f);
        return materialShapeDrawable;
    }

    public void setCornerSize(@NonNull CornerSize cornerSize) {
        setShapeAppearanceModel(this.h.f10352a.withCornerSize(cornerSize));
    }

    public MaterialShapeDrawable(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        this(ShapeAppearanceModel.builder(context, attributeSet, i, i2).build());
    }

    public void setStroke(float f, @Nullable ColorStateList colorStateList) {
        setStrokeWidth(f);
        setStrokeColor(colorStateList);
    }

    @Deprecated
    public MaterialShapeDrawable(@NonNull ShapePathModel shapePathModel) {
        this((ShapeAppearanceModel) shapePathModel);
    }

    public void setStrokeTint(@ColorInt int i) {
        setStrokeTint(ColorStateList.valueOf(i));
    }

    public MaterialShapeDrawable(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this(new c(shapeAppearanceModel, null));
    }

    public MaterialShapeDrawable(@NonNull c cVar) {
        ShapeAppearancePathProvider shapeAppearancePathProvider;
        this.i = new ShapePath.d[4];
        this.j = new ShapePath.d[4];
        this.k = new BitSet(8);
        this.m = new Matrix();
        this.n = new Path();
        this.o = new Path();
        this.p = new RectF();
        this.q = new RectF();
        this.r = new Region();
        this.s = new Region();
        Paint paint = new Paint(1);
        this.u = paint;
        Paint paint2 = new Paint(1);
        this.v = paint2;
        this.w = new ShadowRenderer();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            shapeAppearancePathProvider = ShapeAppearancePathProvider.getInstance();
        } else {
            shapeAppearancePathProvider = new ShapeAppearancePathProvider();
        }
        this.y = shapeAppearancePathProvider;
        this.C = new RectF();
        this.D = true;
        this.h = cVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        w();
        v(getState());
        this.x = new a();
    }

    /* loaded from: classes10.dex */
    public static final class c extends Drawable.ConstantState {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public ShapeAppearanceModel f10352a;
        @Nullable
        public ElevationOverlayProvider b;
        @Nullable
        public ColorFilter c;
        @Nullable
        public ColorStateList d;
        @Nullable
        public ColorStateList e;
        @Nullable
        public ColorStateList f;
        @Nullable
        public ColorStateList g;
        @Nullable
        public PorterDuff.Mode h;
        @Nullable
        public Rect i;
        public float j;
        public float k;
        public float l;
        public int m;
        public float n;
        public float o;
        public float p;
        public int q;
        public int r;
        public int s;
        public int t;
        public boolean u;
        public Paint.Style v;

        public c(ShapeAppearanceModel shapeAppearanceModel, ElevationOverlayProvider elevationOverlayProvider) {
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = PorterDuff.Mode.SRC_IN;
            this.i = null;
            this.j = 1.0f;
            this.k = 1.0f;
            this.m = 255;
            this.n = 0.0f;
            this.o = 0.0f;
            this.p = 0.0f;
            this.q = 0;
            this.r = 0;
            this.s = 0;
            this.t = 0;
            this.u = false;
            this.v = Paint.Style.FILL_AND_STROKE;
            this.f10352a = shapeAppearanceModel;
            this.b = elevationOverlayProvider;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this, null);
            materialShapeDrawable.l = true;
            return materialShapeDrawable;
        }

        public c(@NonNull c cVar) {
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = PorterDuff.Mode.SRC_IN;
            this.i = null;
            this.j = 1.0f;
            this.k = 1.0f;
            this.m = 255;
            this.n = 0.0f;
            this.o = 0.0f;
            this.p = 0.0f;
            this.q = 0;
            this.r = 0;
            this.s = 0;
            this.t = 0;
            this.u = false;
            this.v = Paint.Style.FILL_AND_STROKE;
            this.f10352a = cVar.f10352a;
            this.b = cVar.b;
            this.l = cVar.l;
            this.c = cVar.c;
            this.d = cVar.d;
            this.e = cVar.e;
            this.h = cVar.h;
            this.g = cVar.g;
            this.m = cVar.m;
            this.j = cVar.j;
            this.s = cVar.s;
            this.q = cVar.q;
            this.u = cVar.u;
            this.k = cVar.k;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
            this.r = cVar.r;
            this.t = cVar.t;
            this.f = cVar.f;
            this.v = cVar.v;
            if (cVar.i != null) {
                this.i = new Rect(cVar.i);
            }
        }
    }
}
