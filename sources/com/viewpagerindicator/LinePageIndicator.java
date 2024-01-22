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
public class LinePageIndicator extends View implements PageIndicator {
    public final Paint h;
    public final Paint i;
    public ViewPager j;
    public ViewPager.OnPageChangeListener k;
    public int l;
    public boolean m;
    public float n;
    public float o;
    public int p;
    public float q;
    public int r;
    public boolean s;

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

    public LinePageIndicator(Context context) {
        this(context, null);
    }

    public final int a(int i) {
        float min;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            min = size;
        } else {
            float strokeWidth = this.i.getStrokeWidth() + getPaddingTop() + getPaddingBottom();
            min = mode == Integer.MIN_VALUE ? Math.min(strokeWidth, size) : strokeWidth;
        }
        return (int) Math.ceil(min);
    }

    public final int b(int i) {
        float f;
        ViewPager viewPager;
        int count;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824 || (viewPager = this.j) == null) {
            f = size;
        } else {
            f = getPaddingLeft() + getPaddingRight() + (viewPager.getAdapter().getCount() * this.n) + ((count - 1) * this.o);
            if (mode == Integer.MIN_VALUE) {
                f = Math.min(f, size);
            }
        }
        return (int) Math.ceil(f);
    }

    public float getGapWidth() {
        return this.o;
    }

    public float getLineWidth() {
        return this.n;
    }

    public int getSelectedColor() {
        return this.i.getColor();
    }

    public float getStrokeWidth() {
        return this.i.getStrokeWidth();
    }

    public int getUnselectedColor() {
        return this.h.getColor();
    }

    public boolean isCentered() {
        return this.m;
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void notifyDataSetChanged() {
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int count;
        super.onDraw(canvas);
        ViewPager viewPager = this.j;
        if (viewPager == null || (count = viewPager.getAdapter().getCount()) == 0) {
            return;
        }
        if (this.l >= count) {
            setCurrentItem(count - 1);
            return;
        }
        float f = this.n;
        float f2 = this.o;
        float f3 = f + f2;
        float f4 = (count * f3) - f2;
        float paddingTop = getPaddingTop();
        float paddingLeft = getPaddingLeft();
        float paddingRight = getPaddingRight();
        float height = paddingTop + (((getHeight() - paddingTop) - getPaddingBottom()) / 2.0f);
        if (this.m) {
            paddingLeft += (((getWidth() - paddingLeft) - paddingRight) / 2.0f) - (f4 / 2.0f);
        }
        int i = 0;
        while (i < count) {
            float f5 = paddingLeft + (i * f3);
            canvas.drawLine(f5, height, f5 + this.n, height, i == this.l ? this.i : this.h);
            i++;
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(b(i), a(i2));
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.k;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.k;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.l = i;
        invalidate();
        ViewPager.OnPageChangeListener onPageChangeListener = this.k;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.l = savedState.h;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.h = this.l;
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        ViewPager viewPager = this.j;
        if (viewPager == null || viewPager.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    float x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.r));
                    float f = x - this.q;
                    if (!this.s && Math.abs(f) > this.p) {
                        this.s = true;
                    }
                    if (this.s) {
                        this.q = x;
                        if (this.j.isFakeDragging() || this.j.beginFakeDrag()) {
                            this.j.fakeDragBy(f);
                        }
                    }
                } else if (action != 3) {
                    if (action == 5) {
                        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                        this.q = MotionEventCompat.getX(motionEvent, actionIndex);
                        this.r = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    } else if (action == 6) {
                        int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                        if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.r) {
                            this.r = MotionEventCompat.getPointerId(motionEvent, actionIndex2 == 0 ? 1 : 0);
                        }
                        this.q = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.r));
                    }
                }
            }
            if (!this.s) {
                int count = this.j.getAdapter().getCount();
                float width = getWidth();
                float f2 = width / 2.0f;
                float f3 = width / 6.0f;
                if (this.l > 0 && motionEvent.getX() < f2 - f3) {
                    if (action != 3) {
                        this.j.setCurrentItem(this.l - 1);
                    }
                    return true;
                } else if (this.l < count - 1 && motionEvent.getX() > f2 + f3) {
                    if (action != 3) {
                        this.j.setCurrentItem(this.l + 1);
                    }
                    return true;
                }
            }
            this.s = false;
            this.r = -1;
            if (this.j.isFakeDragging()) {
                this.j.endFakeDrag();
            }
        } else {
            this.r = MotionEventCompat.getPointerId(motionEvent, 0);
            this.q = motionEvent.getX();
        }
        return true;
    }

    public void setCentered(boolean z) {
        this.m = z;
        invalidate();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setCurrentItem(int i) {
        ViewPager viewPager = this.j;
        if (viewPager != null) {
            viewPager.setCurrentItem(i);
            this.l = i;
            invalidate();
            return;
        }
        throw new IllegalStateException("ViewPager has not been bound.");
    }

    public void setGapWidth(float f) {
        this.o = f;
        invalidate();
    }

    public void setLineWidth(float f) {
        this.n = f;
        invalidate();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.k = onPageChangeListener;
    }

    public void setSelectedColor(int i) {
        this.i.setColor(i);
        invalidate();
    }

    public void setStrokeWidth(float f) {
        this.i.setStrokeWidth(f);
        this.h.setStrokeWidth(f);
        invalidate();
    }

    public void setUnselectedColor(int i) {
        this.h.setColor(i);
        invalidate();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager) {
        ViewPager viewPager2 = this.j;
        if (viewPager2 == viewPager) {
            return;
        }
        if (viewPager2 != null) {
            viewPager2.setOnPageChangeListener(null);
        }
        if (viewPager.getAdapter() != null) {
            this.j = viewPager;
            viewPager.setOnPageChangeListener(this);
            invalidate();
            return;
        }
        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    public LinePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiLinePageIndicatorStyle);
    }

    public LinePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Paint paint = new Paint(1);
        this.h = paint;
        Paint paint2 = new Paint(1);
        this.i = paint2;
        this.q = -1.0f;
        this.r = -1;
        if (isInEditMode()) {
            return;
        }
        Resources resources = getResources();
        int color = resources.getColor(R.color.default_line_indicator_selected_color);
        int color2 = resources.getColor(R.color.default_line_indicator_unselected_color);
        float dimension = resources.getDimension(R.dimen.default_line_indicator_line_width);
        float dimension2 = resources.getDimension(R.dimen.default_line_indicator_gap_width);
        float dimension3 = resources.getDimension(R.dimen.default_line_indicator_stroke_width);
        boolean z = resources.getBoolean(R.bool.default_line_indicator_centered);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinePageIndicator, i, 0);
        this.m = obtainStyledAttributes.getBoolean(R.styleable.LinePageIndicator_centered, z);
        this.n = obtainStyledAttributes.getDimension(R.styleable.LinePageIndicator_lineWidth, dimension);
        this.o = obtainStyledAttributes.getDimension(R.styleable.LinePageIndicator_gapWidth, dimension2);
        setStrokeWidth(obtainStyledAttributes.getDimension(R.styleable.LinePageIndicator_strokeWidth, dimension3));
        paint.setColor(obtainStyledAttributes.getColor(R.styleable.LinePageIndicator_unselectedColor, color2));
        paint2.setColor(obtainStyledAttributes.getColor(R.styleable.LinePageIndicator_selectedColor, color));
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.LinePageIndicator_android_background);
        if (drawable != null) {
            setBackgroundDrawable(drawable);
        }
        obtainStyledAttributes.recycle();
        this.p = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }
}
