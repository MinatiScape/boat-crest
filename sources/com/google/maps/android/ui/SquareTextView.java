package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
/* loaded from: classes10.dex */
public class SquareTextView extends AppCompatTextView {
    public int o;
    public int p;

    public SquareTextView(Context context) {
        super(context);
        this.o = 0;
        this.p = 0;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        canvas.translate(this.p / 2, this.o / 2);
        super.draw(canvas);
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int max = Math.max(measuredWidth, measuredHeight);
        if (measuredWidth > measuredHeight) {
            this.o = measuredWidth - measuredHeight;
            this.p = 0;
        } else {
            this.o = 0;
            this.p = measuredHeight - measuredWidth;
        }
        setMeasuredDimension(max, max);
    }

    public SquareTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = 0;
        this.p = 0;
    }

    public SquareTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.o = 0;
        this.p = 0;
    }
}
