package com.mappls.sdk.maps.annotations;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.R;
@Deprecated
/* loaded from: classes11.dex */
public class BubbleLayout extends LinearLayout {
    public static final float DEFAULT_STROKE_WIDTH = -1.0f;
    public a h;
    public float i;
    public float j;
    public float k;
    public float l;
    public b m;
    public int n;
    public float o;
    public int p;

    public BubbleLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public static float a(float f, Context context) {
        return f * (context.getResources().getDisplayMetrics().densityDpi / 160);
    }

    public final void b(int i, int i2, int i3, int i4) {
        if (i2 < i || i4 < i3) {
            return;
        }
        this.m = new b(new RectF(i, i3, i2, i4), this.h, this.i, this.j, this.k, this.l, this.n, this.o, this.p);
    }

    public final void c() {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int a2 = this.h.a();
        if (a2 == 0) {
            paddingLeft = (int) (paddingLeft + this.i);
        } else if (a2 == 1) {
            paddingRight = (int) (paddingRight + this.i);
        } else if (a2 == 2) {
            paddingTop = (int) (paddingTop + this.j);
        } else if (a2 == 3) {
            paddingBottom = (int) (paddingBottom + this.j);
        }
        float f = this.o;
        if (f > 0.0f) {
            paddingLeft = (int) (paddingLeft + f);
            paddingRight = (int) (paddingRight + f);
            paddingTop = (int) (paddingTop + f);
            paddingBottom = (int) (paddingBottom + f);
        }
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public final void d() {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int a2 = this.h.a();
        if (a2 == 0) {
            paddingLeft = (int) (paddingLeft - this.i);
        } else if (a2 == 1) {
            paddingRight = (int) (paddingRight - this.i);
        } else if (a2 == 2) {
            paddingTop = (int) (paddingTop - this.j);
        } else if (a2 == 3) {
            paddingBottom = (int) (paddingBottom - this.j);
        }
        float f = this.o;
        if (f > 0.0f) {
            paddingLeft = (int) (paddingLeft - f);
            paddingRight = (int) (paddingRight - f);
            paddingTop = (int) (paddingTop - f);
            paddingBottom = (int) (paddingBottom - f);
        }
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(@NonNull Canvas canvas) {
        b bVar = this.m;
        if (bVar != null) {
            bVar.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    public a getArrowDirection() {
        return this.h;
    }

    public float getArrowHeight() {
        return this.j;
    }

    public float getArrowPosition() {
        return this.k;
    }

    public float getArrowWidth() {
        return this.i;
    }

    public int getBubbleColor() {
        return this.n;
    }

    public float getCornersRadius() {
        return this.l;
    }

    public int getStrokeColor() {
        return this.p;
    }

    public float getStrokeWidth() {
        return this.o;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        b(0, getWidth(), 0, getHeight());
    }

    @NonNull
    public BubbleLayout setArrowDirection(a aVar) {
        d();
        this.h = aVar;
        c();
        return this;
    }

    @NonNull
    public BubbleLayout setArrowHeight(float f) {
        d();
        this.j = f;
        c();
        return this;
    }

    @NonNull
    public BubbleLayout setArrowPosition(float f) {
        d();
        this.k = f;
        c();
        return this;
    }

    @NonNull
    public BubbleLayout setArrowWidth(float f) {
        d();
        this.i = f;
        c();
        return this;
    }

    @NonNull
    public BubbleLayout setBubbleColor(int i) {
        this.n = i;
        requestLayout();
        return this;
    }

    @NonNull
    public BubbleLayout setCornersRadius(float f) {
        this.l = f;
        requestLayout();
        return this;
    }

    @NonNull
    public BubbleLayout setStrokeColor(int i) {
        this.p = i;
        requestLayout();
        return this;
    }

    @NonNull
    public BubbleLayout setStrokeWidth(float f) {
        d();
        this.o = f;
        c();
        return this;
    }

    public BubbleLayout(@NonNull Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BubbleLayout(@NonNull Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.mappls_maps_BubbleLayout);
        this.h = new a(obtainStyledAttributes.getInt(R.styleable.mappls_maps_BubbleLayout_mappls_maps_bl_arrowDirection, 0));
        this.i = obtainStyledAttributes.getDimension(R.styleable.mappls_maps_BubbleLayout_mappls_maps_bl_arrowWidth, a(8.0f, context));
        this.j = obtainStyledAttributes.getDimension(R.styleable.mappls_maps_BubbleLayout_mappls_maps_bl_arrowHeight, a(8.0f, context));
        this.k = obtainStyledAttributes.getDimension(R.styleable.mappls_maps_BubbleLayout_mappls_maps_bl_arrowPosition, a(12.0f, context));
        this.l = obtainStyledAttributes.getDimension(R.styleable.mappls_maps_BubbleLayout_mappls_maps_bl_cornersRadius, 0.0f);
        this.n = obtainStyledAttributes.getColor(R.styleable.mappls_maps_BubbleLayout_mappls_maps_bl_bubbleColor, -1);
        this.o = obtainStyledAttributes.getDimension(R.styleable.mappls_maps_BubbleLayout_mappls_maps_bl_strokeWidth, -1.0f);
        this.p = obtainStyledAttributes.getColor(R.styleable.mappls_maps_BubbleLayout_mappls_maps_bl_strokeColor, -7829368);
        obtainStyledAttributes.recycle();
        c();
    }
}
