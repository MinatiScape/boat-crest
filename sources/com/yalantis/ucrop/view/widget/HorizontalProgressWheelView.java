package com.yalantis.ucrop.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import com.yalantis.ucrop.R;
/* loaded from: classes12.dex */
public class HorizontalProgressWheelView extends View {
    public final Rect h;
    public ScrollingListener i;
    public float j;
    public Paint k;
    public Paint l;
    public int m;
    public int n;
    public int o;
    public boolean p;
    public float q;
    public int r;

    /* loaded from: classes12.dex */
    public interface ScrollingListener {
        void onScroll(float f, float f2);

        void onScrollEnd();

        void onScrollStart();
    }

    public HorizontalProgressWheelView(Context context) {
        this(context, null);
    }

    public final void a() {
        this.r = ContextCompat.getColor(getContext(), R.color.ucrop_color_widget_rotate_mid_line);
        this.m = getContext().getResources().getDimensionPixelSize(R.dimen.ucrop_width_horizontal_wheel_progress_line);
        this.n = getContext().getResources().getDimensionPixelSize(R.dimen.ucrop_height_horizontal_wheel_progress_line);
        this.o = getContext().getResources().getDimensionPixelSize(R.dimen.ucrop_margin_horizontal_wheel_progress_line);
        Paint paint = new Paint(1);
        this.k = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.k.setStrokeWidth(this.m);
        this.k.setColor(getResources().getColor(R.color.ucrop_color_progress_wheel_line));
        Paint paint2 = new Paint(this.k);
        this.l = paint2;
        paint2.setColor(this.r);
        this.l.setStrokeCap(Paint.Cap.ROUND);
        this.l.setStrokeWidth(getContext().getResources().getDimensionPixelSize(R.dimen.ucrop_width_middle_wheel_progress_line));
    }

    public final void b(MotionEvent motionEvent, float f) {
        this.q -= f;
        postInvalidate();
        this.j = motionEvent.getX();
        ScrollingListener scrollingListener = this.i;
        if (scrollingListener != null) {
            scrollingListener.onScroll(-f, this.q);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.getClipBounds(this.h);
        int width = this.h.width();
        int i = this.m;
        int i2 = this.o;
        int i3 = width / (i + i2);
        float f = this.q % (i2 + i);
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i3 / 4;
            if (i4 < i5) {
                this.k.setAlpha((int) ((i4 / i5) * 255.0f));
            } else if (i4 > (i3 * 3) / 4) {
                this.k.setAlpha((int) (((i3 - i4) / i5) * 255.0f));
            } else {
                this.k.setAlpha(255);
            }
            float f2 = -f;
            Rect rect = this.h;
            float f3 = rect.left + f2 + ((this.m + this.o) * i4);
            float centerY = rect.centerY() - (this.n / 4.0f);
            Rect rect2 = this.h;
            canvas.drawLine(f3, centerY, f2 + rect2.left + ((this.m + this.o) * i4), rect2.centerY() + (this.n / 4.0f), this.k);
        }
        canvas.drawLine(this.h.centerX(), this.h.centerY() - (this.n / 2.0f), this.h.centerX(), (this.n / 2.0f) + this.h.centerY(), this.l);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.j = motionEvent.getX();
        } else if (action == 1) {
            ScrollingListener scrollingListener = this.i;
            if (scrollingListener != null) {
                this.p = false;
                scrollingListener.onScrollEnd();
            }
        } else if (action == 2) {
            float x = motionEvent.getX() - this.j;
            if (x != 0.0f) {
                if (!this.p) {
                    this.p = true;
                    ScrollingListener scrollingListener2 = this.i;
                    if (scrollingListener2 != null) {
                        scrollingListener2.onScrollStart();
                    }
                }
                b(motionEvent, x);
            }
        }
        return true;
    }

    public void setMiddleLineColor(@ColorInt int i) {
        this.r = i;
        invalidate();
    }

    public void setScrollingListener(ScrollingListener scrollingListener) {
        this.i = scrollingListener;
    }

    public HorizontalProgressWheelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalProgressWheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = new Rect();
        a();
    }

    @TargetApi(21)
    public HorizontalProgressWheelView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.h = new Rect();
    }
}
