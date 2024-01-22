package com.viewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.viewpager.widget.ViewPager;
/* loaded from: classes12.dex */
public class CirclePageIndicator extends View implements PageIndicator {
    public float h;
    public final Paint i;
    public final Paint j;
    public final Paint k;
    public ViewPager l;
    public ViewPager.OnPageChangeListener m;
    public int n;
    public int o;
    public float p;
    public int q;
    public int r;
    public boolean s;
    public boolean t;
    public int u;
    public float v;
    public int w;
    public boolean x;

    /* loaded from: classes12.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int h;

        /* loaded from: classes12.dex */
        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.h);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.h = parcel.readInt();
        }
    }

    public CirclePageIndicator(Context context) {
        this(context, null);
    }

    public final int a(int i) {
        ViewPager viewPager;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824 || (viewPager = this.l) == null) {
            return size;
        }
        int count = viewPager.getAdapter().getCount();
        float f = this.h;
        int paddingLeft = (int) (getPaddingLeft() + getPaddingRight() + (count * 2 * f) + ((count - 1) * f) + 1.0f);
        return mode == Integer.MIN_VALUE ? Math.min(paddingLeft, size) : paddingLeft;
    }

    public final int b(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int paddingTop = (int) ((this.h * 2.0f) + getPaddingTop() + getPaddingBottom() + 1.0f);
        return mode == Integer.MIN_VALUE ? Math.min(paddingTop, size) : paddingTop;
    }

    public int getFillColor() {
        return this.k.getColor();
    }

    public int getOrientation() {
        return this.r;
    }

    public int getPageColor() {
        return this.i.getColor();
    }

    public float getRadius() {
        return this.h;
    }

    public int getStrokeColor() {
        return this.j.getColor();
    }

    public float getStrokeWidth() {
        return this.j.getStrokeWidth();
    }

    public boolean isCentered() {
        return this.s;
    }

    public boolean isSnap() {
        return this.t;
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void notifyDataSetChanged() {
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int count;
        int height;
        int paddingTop;
        int paddingBottom;
        int paddingLeft;
        float f;
        float f2;
        super.onDraw(canvas);
        ViewPager viewPager = this.l;
        if (viewPager == null || (count = viewPager.getAdapter().getCount()) == 0) {
            return;
        }
        if (this.n >= count) {
            setCurrentItem(count - 1);
            return;
        }
        if (this.r == 0) {
            height = getWidth();
            paddingTop = getPaddingLeft();
            paddingBottom = getPaddingRight();
            paddingLeft = getPaddingTop();
        } else {
            height = getHeight();
            paddingTop = getPaddingTop();
            paddingBottom = getPaddingBottom();
            paddingLeft = getPaddingLeft();
        }
        float f3 = this.h;
        float f4 = 3.0f * f3;
        float f5 = paddingLeft + f3;
        float f6 = paddingTop + f3;
        if (this.s) {
            f6 += (((height - paddingTop) - paddingBottom) / 2.0f) - ((count * f4) / 2.0f);
        }
        if (this.j.getStrokeWidth() > 0.0f) {
            f3 -= this.j.getStrokeWidth() / 2.0f;
        }
        for (int i = 0; i < count; i++) {
            float f7 = (i * f4) + f6;
            if (this.r == 0) {
                f2 = f5;
            } else {
                f2 = f7;
                f7 = f5;
            }
            if (this.i.getAlpha() > 0) {
                canvas.drawCircle(f7, f2, f3, this.i);
            }
            float f8 = this.h;
            if (f3 != f8) {
                canvas.drawCircle(f7, f2, f8, this.j);
            }
        }
        boolean z = this.t;
        float f9 = (z ? this.o : this.n) * f4;
        if (!z) {
            f9 += this.p * f4;
        }
        if (this.r == 0) {
            float f10 = f6 + f9;
            f = f5;
            f5 = f10;
        } else {
            f = f6 + f9;
        }
        canvas.drawCircle(f5, f, this.h, this.k);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        if (this.r == 0) {
            setMeasuredDimension(a(i), b(i2));
        } else {
            setMeasuredDimension(b(i), a(i2));
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        this.q = i;
        ViewPager.OnPageChangeListener onPageChangeListener = this.m;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.n = i;
        this.p = f;
        invalidate();
        ViewPager.OnPageChangeListener onPageChangeListener = this.m;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (this.t || this.q == 0) {
            this.n = i;
            this.o = i;
            invalidate();
        }
        ViewPager.OnPageChangeListener onPageChangeListener = this.m;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        int i = savedState.h;
        this.n = i;
        this.o = i;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.h = this.n;
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        ViewPager viewPager = this.l;
        if (viewPager == null || viewPager.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    float x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.w));
                    float f = x - this.v;
                    if (!this.x && Math.abs(f) > this.u) {
                        this.x = true;
                    }
                    if (this.x) {
                        this.v = x;
                        if (this.l.isFakeDragging() || this.l.beginFakeDrag()) {
                            this.l.fakeDragBy(f);
                        }
                    }
                } else if (action != 3) {
                    if (action == 5) {
                        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                        this.v = MotionEventCompat.getX(motionEvent, actionIndex);
                        this.w = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    } else if (action == 6) {
                        int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                        if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.w) {
                            this.w = MotionEventCompat.getPointerId(motionEvent, actionIndex2 == 0 ? 1 : 0);
                        }
                        this.v = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.w));
                    }
                }
            }
            if (!this.x) {
                int count = this.l.getAdapter().getCount();
                float width = getWidth();
                float f2 = width / 2.0f;
                float f3 = width / 6.0f;
                if (this.n > 0 && motionEvent.getX() < f2 - f3) {
                    if (action != 3) {
                        this.l.setCurrentItem(this.n - 1);
                    }
                    return true;
                } else if (this.n < count - 1 && motionEvent.getX() > f2 + f3) {
                    if (action != 3) {
                        this.l.setCurrentItem(this.n + 1);
                    }
                    return true;
                }
            }
            this.x = false;
            this.w = -1;
            if (this.l.isFakeDragging()) {
                this.l.endFakeDrag();
            }
        } else {
            this.w = MotionEventCompat.getPointerId(motionEvent, 0);
            this.v = motionEvent.getX();
        }
        return true;
    }

    public void setCentered(boolean z) {
        this.s = z;
        invalidate();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setCurrentItem(int i) {
        ViewPager viewPager = this.l;
        if (viewPager != null) {
            viewPager.setCurrentItem(i);
            this.n = i;
            invalidate();
            return;
        }
        throw new IllegalStateException("ViewPager has not been bound.");
    }

    public void setFillColor(int i) {
        this.k.setColor(i);
        invalidate();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.m = onPageChangeListener;
    }

    public void setOrientation(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Orientation must be either HORIZONTAL or VERTICAL.");
        }
        this.r = i;
        requestLayout();
    }

    public void setPageColor(int i) {
        this.i.setColor(i);
        invalidate();
    }

    public void setRadius(float f) {
        this.h = f;
        invalidate();
    }

    public void setSnap(boolean z) {
        this.t = z;
        invalidate();
    }

    public void setStrokeColor(int i) {
        this.j.setColor(i);
        invalidate();
    }

    public void setStrokeWidth(float f) {
        this.j.setStrokeWidth(f);
        invalidate();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager) {
        ViewPager viewPager2 = this.l;
        if (viewPager2 == viewPager) {
            return;
        }
        if (viewPager2 != null) {
            viewPager2.setOnPageChangeListener(null);
        }
        if (viewPager.getAdapter() != null) {
            this.l = viewPager;
            viewPager.setOnPageChangeListener(this);
            invalidate();
            return;
        }
        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiCirclePageIndicatorStyle);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Paint paint = new Paint(1);
        this.i = paint;
        Paint paint2 = new Paint(1);
        this.j = paint2;
        Paint paint3 = new Paint(1);
        this.k = paint3;
        this.v = -1.0f;
        this.w = -1;
        if (isInEditMode()) {
            return;
        }
        Resources resources = getResources();
        int color = resources.getColor(R.color.default_circle_indicator_page_color);
        int color2 = resources.getColor(R.color.default_circle_indicator_fill_color);
        int integer = resources.getInteger(R.integer.default_circle_indicator_orientation);
        int color3 = resources.getColor(R.color.default_circle_indicator_stroke_color);
        float dimension = resources.getDimension(R.dimen.default_circle_indicator_stroke_width);
        float dimension2 = resources.getDimension(R.dimen.default_circle_indicator_radius);
        boolean z = resources.getBoolean(R.bool.default_circle_indicator_centered);
        boolean z2 = resources.getBoolean(R.bool.default_circle_indicator_snap);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CirclePageIndicator, i, 0);
        this.s = obtainStyledAttributes.getBoolean(R.styleable.CirclePageIndicator_centered, z);
        this.r = obtainStyledAttributes.getInt(R.styleable.CirclePageIndicator_android_orientation, integer);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(obtainStyledAttributes.getColor(R.styleable.CirclePageIndicator_pageColor, color));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(obtainStyledAttributes.getColor(R.styleable.CirclePageIndicator_strokeColor, color3));
        paint2.setStrokeWidth(obtainStyledAttributes.getDimension(R.styleable.CirclePageIndicator_strokeWidth, dimension));
        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(obtainStyledAttributes.getColor(R.styleable.CirclePageIndicator_fillColor, color2));
        this.h = obtainStyledAttributes.getDimension(R.styleable.CirclePageIndicator_radius, dimension2);
        this.t = obtainStyledAttributes.getBoolean(R.styleable.CirclePageIndicator_snap, z2);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.CirclePageIndicator_android_background);
        if (drawable != null) {
            setBackgroundDrawable(drawable);
        }
        obtainStyledAttributes.recycle();
        this.u = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }
}
