package com.google.android.material.imageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.material.R;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.Shapeable;
/* loaded from: classes10.dex */
public class ShapeableImageView extends AppCompatImageView implements Shapeable {
    public static final int C = R.style.Widget_MaterialComponents_ShapeableImageView;
    @Dimension
    public int A;
    public boolean B;
    public final ShapeAppearancePathProvider k;
    public final RectF l;
    public final RectF m;
    public final Paint n;
    public final Paint o;
    public final Path p;
    @Nullable
    public ColorStateList q;
    @Nullable
    public MaterialShapeDrawable r;
    public ShapeAppearanceModel s;
    @Dimension
    public float t;
    public Path u;
    @Dimension
    public int v;
    @Dimension
    public int w;
    @Dimension
    public int x;
    @Dimension
    public int y;
    @Dimension
    public int z;

    @TargetApi(21)
    /* loaded from: classes10.dex */
    public class a extends ViewOutlineProvider {

        /* renamed from: a  reason: collision with root package name */
        public final Rect f10308a = new Rect();

        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (ShapeableImageView.this.s == null) {
                return;
            }
            if (ShapeableImageView.this.r == null) {
                ShapeableImageView.this.r = new MaterialShapeDrawable(ShapeableImageView.this.s);
            }
            ShapeableImageView.this.l.round(this.f10308a);
            ShapeableImageView.this.r.setBounds(this.f10308a);
            ShapeableImageView.this.r.getOutline(outline);
        }
    }

    public ShapeableImageView(Context context) {
        this(context, null, 0);
    }

    public final void e(Canvas canvas) {
        if (this.q == null) {
            return;
        }
        this.n.setStrokeWidth(this.t);
        int colorForState = this.q.getColorForState(getDrawableState(), this.q.getDefaultColor());
        if (this.t <= 0.0f || colorForState == 0) {
            return;
        }
        this.n.setColor(colorForState);
        canvas.drawPath(this.p, this.n);
    }

    public final boolean f() {
        return (this.z == Integer.MIN_VALUE && this.A == Integer.MIN_VALUE) ? false : true;
    }

    public final boolean g() {
        return Build.VERSION.SDK_INT >= 17 && getLayoutDirection() == 1;
    }

    @Dimension
    public int getContentPaddingBottom() {
        return this.y;
    }

    @Dimension
    public final int getContentPaddingEnd() {
        int i = this.A;
        return i != Integer.MIN_VALUE ? i : g() ? this.v : this.x;
    }

    @Dimension
    public int getContentPaddingLeft() {
        int i;
        int i2;
        if (f()) {
            if (g() && (i2 = this.A) != Integer.MIN_VALUE) {
                return i2;
            }
            if (!g() && (i = this.z) != Integer.MIN_VALUE) {
                return i;
            }
        }
        return this.v;
    }

    @Dimension
    public int getContentPaddingRight() {
        int i;
        int i2;
        if (f()) {
            if (g() && (i2 = this.z) != Integer.MIN_VALUE) {
                return i2;
            }
            if (!g() && (i = this.A) != Integer.MIN_VALUE) {
                return i;
            }
        }
        return this.x;
    }

    @Dimension
    public final int getContentPaddingStart() {
        int i = this.z;
        return i != Integer.MIN_VALUE ? i : g() ? this.x : this.v;
    }

    @Dimension
    public int getContentPaddingTop() {
        return this.w;
    }

    @Override // android.view.View
    @Dimension
    public int getPaddingBottom() {
        return super.getPaddingBottom() - getContentPaddingBottom();
    }

    @Override // android.view.View
    @Dimension
    public int getPaddingEnd() {
        return super.getPaddingEnd() - getContentPaddingEnd();
    }

    @Override // android.view.View
    @Dimension
    public int getPaddingLeft() {
        return super.getPaddingLeft() - getContentPaddingLeft();
    }

    @Override // android.view.View
    @Dimension
    public int getPaddingRight() {
        return super.getPaddingRight() - getContentPaddingRight();
    }

    @Override // android.view.View
    @Dimension
    public int getPaddingStart() {
        return super.getPaddingStart() - getContentPaddingStart();
    }

    @Override // android.view.View
    @Dimension
    public int getPaddingTop() {
        return super.getPaddingTop() - getContentPaddingTop();
    }

    @Override // com.google.android.material.shape.Shapeable
    @NonNull
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.s;
    }

    @Nullable
    public ColorStateList getStrokeColor() {
        return this.q;
    }

    @Dimension
    public float getStrokeWidth() {
        return this.t;
    }

    public final void h(int i, int i2) {
        this.l.set(getPaddingLeft(), getPaddingTop(), i - getPaddingRight(), i2 - getPaddingBottom());
        this.k.calculatePath(this.s, 1.0f, this.l, this.p);
        this.u.rewind();
        this.u.addPath(this.p);
        this.m.set(0.0f, 0.0f, i, i2);
        this.u.addRect(this.m, Path.Direction.CCW);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setLayerType(2, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        setLayerType(0, null);
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.u, this.o);
        e(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.B) {
            return;
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 <= 19 || isLayoutDirectionResolved()) {
            this.B = true;
            if (i3 >= 21 && (isPaddingRelative() || f())) {
                setPaddingRelative(super.getPaddingStart(), super.getPaddingTop(), super.getPaddingEnd(), super.getPaddingBottom());
            } else {
                setPadding(super.getPaddingLeft(), super.getPaddingTop(), super.getPaddingRight(), super.getPaddingBottom());
            }
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        h(i, i2);
    }

    public void setContentPadding(@Dimension int i, @Dimension int i2, @Dimension int i3, @Dimension int i4) {
        this.z = Integer.MIN_VALUE;
        this.A = Integer.MIN_VALUE;
        super.setPadding((super.getPaddingLeft() - this.v) + i, (super.getPaddingTop() - this.w) + i2, (super.getPaddingRight() - this.x) + i3, (super.getPaddingBottom() - this.y) + i4);
        this.v = i;
        this.w = i2;
        this.x = i3;
        this.y = i4;
    }

    @RequiresApi(17)
    public void setContentPaddingRelative(@Dimension int i, @Dimension int i2, @Dimension int i3, @Dimension int i4) {
        super.setPaddingRelative((super.getPaddingStart() - getContentPaddingStart()) + i, (super.getPaddingTop() - this.w) + i2, (super.getPaddingEnd() - getContentPaddingEnd()) + i3, (super.getPaddingBottom() - this.y) + i4);
        this.v = g() ? i3 : i;
        this.w = i2;
        if (!g()) {
            i = i3;
        }
        this.x = i;
        this.y = i4;
    }

    @Override // android.view.View
    public void setPadding(@Dimension int i, @Dimension int i2, @Dimension int i3, @Dimension int i4) {
        super.setPadding(i + getContentPaddingLeft(), i2 + getContentPaddingTop(), i3 + getContentPaddingRight(), i4 + getContentPaddingBottom());
    }

    @Override // android.view.View
    public void setPaddingRelative(@Dimension int i, @Dimension int i2, @Dimension int i3, @Dimension int i4) {
        super.setPaddingRelative(i + getContentPaddingStart(), i2 + getContentPaddingTop(), i3 + getContentPaddingEnd(), i4 + getContentPaddingBottom());
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.s = shapeAppearanceModel;
        MaterialShapeDrawable materialShapeDrawable = this.r;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
        h(getWidth(), getHeight());
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setStrokeColor(@Nullable ColorStateList colorStateList) {
        this.q = colorStateList;
        invalidate();
    }

    public void setStrokeColorResource(@ColorRes int i) {
        setStrokeColor(AppCompatResources.getColorStateList(getContext(), i));
    }

    public void setStrokeWidth(@Dimension float f) {
        if (this.t != f) {
            this.t = f;
            invalidate();
        }
    }

    public void setStrokeWidthResource(@DimenRes int i) {
        setStrokeWidth(getResources().getDimensionPixelSize(i));
    }

    public ShapeableImageView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ShapeableImageView(android.content.Context r7, @androidx.annotation.Nullable android.util.AttributeSet r8, int r9) {
        /*
            r6 = this;
            int r0 = com.google.android.material.imageview.ShapeableImageView.C
            android.content.Context r7 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r7, r8, r9, r0)
            r6.<init>(r7, r8, r9)
            com.google.android.material.shape.ShapeAppearancePathProvider r7 = com.google.android.material.shape.ShapeAppearancePathProvider.getInstance()
            r6.k = r7
            android.graphics.Path r7 = new android.graphics.Path
            r7.<init>()
            r6.p = r7
            r7 = 0
            r6.B = r7
            android.content.Context r1 = r6.getContext()
            android.graphics.Paint r2 = new android.graphics.Paint
            r2.<init>()
            r6.o = r2
            r3 = 1
            r2.setAntiAlias(r3)
            r4 = -1
            r2.setColor(r4)
            android.graphics.PorterDuffXfermode r4 = new android.graphics.PorterDuffXfermode
            android.graphics.PorterDuff$Mode r5 = android.graphics.PorterDuff.Mode.DST_OUT
            r4.<init>(r5)
            r2.setXfermode(r4)
            android.graphics.RectF r2 = new android.graphics.RectF
            r2.<init>()
            r6.l = r2
            android.graphics.RectF r2 = new android.graphics.RectF
            r2.<init>()
            r6.m = r2
            android.graphics.Path r2 = new android.graphics.Path
            r2.<init>()
            r6.u = r2
            int[] r2 = com.google.android.material.R.styleable.ShapeableImageView
            android.content.res.TypedArray r2 = r1.obtainStyledAttributes(r8, r2, r9, r0)
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_strokeColor
            android.content.res.ColorStateList r4 = com.google.android.material.resources.MaterialResources.getColorStateList(r1, r2, r4)
            r6.q = r4
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_strokeWidth
            int r4 = r2.getDimensionPixelSize(r4, r7)
            float r4 = (float) r4
            r6.t = r4
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_contentPadding
            int r7 = r2.getDimensionPixelSize(r4, r7)
            r6.v = r7
            r6.w = r7
            r6.x = r7
            r6.y = r7
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_contentPaddingLeft
            int r4 = r2.getDimensionPixelSize(r4, r7)
            r6.v = r4
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_contentPaddingTop
            int r4 = r2.getDimensionPixelSize(r4, r7)
            r6.w = r4
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_contentPaddingRight
            int r4 = r2.getDimensionPixelSize(r4, r7)
            r6.x = r4
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_contentPaddingBottom
            int r7 = r2.getDimensionPixelSize(r4, r7)
            r6.y = r7
            int r7 = com.google.android.material.R.styleable.ShapeableImageView_contentPaddingStart
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            int r7 = r2.getDimensionPixelSize(r7, r4)
            r6.z = r7
            int r7 = com.google.android.material.R.styleable.ShapeableImageView_contentPaddingEnd
            int r7 = r2.getDimensionPixelSize(r7, r4)
            r6.A = r7
            r2.recycle()
            android.graphics.Paint r7 = new android.graphics.Paint
            r7.<init>()
            r6.n = r7
            android.graphics.Paint$Style r2 = android.graphics.Paint.Style.STROKE
            r7.setStyle(r2)
            r7.setAntiAlias(r3)
            com.google.android.material.shape.ShapeAppearanceModel$Builder r7 = com.google.android.material.shape.ShapeAppearanceModel.builder(r1, r8, r9, r0)
            com.google.android.material.shape.ShapeAppearanceModel r7 = r7.build()
            r6.s = r7
            int r7 = android.os.Build.VERSION.SDK_INT
            r8 = 21
            if (r7 < r8) goto Lcc
            com.google.android.material.imageview.ShapeableImageView$a r7 = new com.google.android.material.imageview.ShapeableImageView$a
            r7.<init>()
            r6.setOutlineProvider(r7)
        Lcc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.imageview.ShapeableImageView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }
}
