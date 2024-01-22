package com.coveiot.android.theme.compundview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class CircleView extends View {
    public static final int i = Color.parseColor("#FF0000");
    public boolean filled;
    public int h;
    public Paint paint;
    public float radius;
    public float strokeWidth;

    public CircleView(Context context) {
        this(context, null);
    }

    public int getColor() {
        return this.h;
    }

    public float getRadius() {
        return this.radius;
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public boolean isFilled() {
        return this.filled;
    }

    public int measureHeight(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            return size;
        }
        int paddingTop = ((int) (this.radius * 2.0f)) + getPaddingTop() + getPaddingBottom() + ((int) (this.strokeWidth * 2.0f));
        return mode == Integer.MIN_VALUE ? Math.min(paddingTop, size) : paddingTop;
    }

    public int measureWidth(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            return size;
        }
        int paddingLeft = ((int) (this.radius * 2.0f)) + getPaddingLeft() + getPaddingRight() + ((int) (this.strokeWidth * 2.0f));
        return mode == Integer.MIN_VALUE ? Math.min(paddingLeft, size) : paddingLeft;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth() >> 1, getHeight() >> 1, this.radius - this.strokeWidth, this.paint);
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(measureWidth(i2), measureHeight(i3));
    }

    public void setColor(int i2, boolean z) {
        setFilled(z);
        this.h = i2;
        this.paint.setColor(i2);
        invalidate();
    }

    public void setFilled(boolean z) {
        this.filled = z;
        this.paint.setStyle(z ? Paint.Style.FILL : Paint.Style.STROKE);
        invalidate();
    }

    public void setRadius(int i2) {
        this.radius = i2;
        invalidate();
    }

    public CircleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public CircleView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.strokeWidth = 3.0f;
        int i3 = i;
        this.h = i3;
        this.filled = true;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleView);
            try {
                this.radius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CircleView_cv___radius, 20);
                this.h = obtainStyledAttributes.getInt(R.styleable.CircleView_cv___color, i3);
                this.filled = obtainStyledAttributes.getBoolean(R.styleable.CircleView_cv___filled, false);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        Paint paint = new Paint(1);
        this.paint = paint;
        paint.setStrokeWidth(this.strokeWidth);
        this.paint.setColor(this.h);
        if (this.filled) {
            this.paint.setStyle(Paint.Style.FILL);
        } else {
            this.paint.setStyle(Paint.Style.STROKE);
        }
    }

    public void setFilled(int i2) {
        setFilled(i2 == 1);
    }
}
