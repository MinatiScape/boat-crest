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
public class UnderlinePageIndicator extends View implements PageIndicator {
    public final Paint h;
    public boolean i;
    public int j;
    public int k;
    public int l;
    public ViewPager m;
    public ViewPager.OnPageChangeListener n;
    public int o;
    public int p;
    public float q;
    public int r;
    public float s;
    public int t;
    public boolean u;
    public final Runnable v;

    /* loaded from: classes12.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int h;

        /* loaded from: classes12.dex */
        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public /* synthetic */ SavedState(Parcel parcel, a aVar) {
            this(parcel);
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

    /* loaded from: classes12.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (UnderlinePageIndicator.this.i) {
                int max = Math.max(UnderlinePageIndicator.this.h.getAlpha() - UnderlinePageIndicator.this.l, 0);
                UnderlinePageIndicator.this.h.setAlpha(max);
                UnderlinePageIndicator.this.invalidate();
                if (max > 0) {
                    UnderlinePageIndicator.this.postDelayed(this, 30L);
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (UnderlinePageIndicator.this.i) {
                UnderlinePageIndicator underlinePageIndicator = UnderlinePageIndicator.this;
                underlinePageIndicator.post(underlinePageIndicator.v);
            }
        }
    }

    public UnderlinePageIndicator(Context context) {
        this(context, null);
    }

    public int getFadeDelay() {
        return this.j;
    }

    public int getFadeLength() {
        return this.k;
    }

    public boolean getFades() {
        return this.i;
    }

    public int getSelectedColor() {
        return this.h.getColor();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void notifyDataSetChanged() {
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int count;
        super.onDraw(canvas);
        ViewPager viewPager = this.m;
        if (viewPager == null || (count = viewPager.getAdapter().getCount()) == 0) {
            return;
        }
        if (this.p >= count) {
            setCurrentItem(count - 1);
            return;
        }
        int paddingLeft = getPaddingLeft();
        float width = ((getWidth() - paddingLeft) - getPaddingRight()) / (count * 1.0f);
        float f = paddingLeft + ((this.p + this.q) * width);
        canvas.drawRect(f, getPaddingTop(), f + width, getHeight() - getPaddingBottom(), this.h);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        this.o = i;
        ViewPager.OnPageChangeListener onPageChangeListener = this.n;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.p = i;
        this.q = f;
        if (this.i) {
            if (i2 > 0) {
                removeCallbacks(this.v);
                this.h.setAlpha(255);
            } else if (this.o != 1) {
                postDelayed(this.v, this.j);
            }
        }
        invalidate();
        ViewPager.OnPageChangeListener onPageChangeListener = this.n;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (this.o == 0) {
            this.p = i;
            this.q = 0.0f;
            invalidate();
            this.v.run();
        }
        ViewPager.OnPageChangeListener onPageChangeListener = this.n;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.p = savedState.h;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.h = this.p;
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        ViewPager viewPager = this.m;
        if (viewPager == null || viewPager.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    float x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.t));
                    float f = x - this.s;
                    if (!this.u && Math.abs(f) > this.r) {
                        this.u = true;
                    }
                    if (this.u) {
                        this.s = x;
                        if (this.m.isFakeDragging() || this.m.beginFakeDrag()) {
                            this.m.fakeDragBy(f);
                        }
                    }
                } else if (action != 3) {
                    if (action == 5) {
                        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                        this.s = MotionEventCompat.getX(motionEvent, actionIndex);
                        this.t = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    } else if (action == 6) {
                        int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                        if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.t) {
                            this.t = MotionEventCompat.getPointerId(motionEvent, actionIndex2 == 0 ? 1 : 0);
                        }
                        this.s = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.t));
                    }
                }
            }
            if (!this.u) {
                int count = this.m.getAdapter().getCount();
                float width = getWidth();
                float f2 = width / 2.0f;
                float f3 = width / 6.0f;
                if (this.p > 0 && motionEvent.getX() < f2 - f3) {
                    if (action != 3) {
                        this.m.setCurrentItem(this.p - 1);
                    }
                    return true;
                } else if (this.p < count - 1 && motionEvent.getX() > f2 + f3) {
                    if (action != 3) {
                        this.m.setCurrentItem(this.p + 1);
                    }
                    return true;
                }
            }
            this.u = false;
            this.t = -1;
            if (this.m.isFakeDragging()) {
                this.m.endFakeDrag();
            }
        } else {
            this.t = MotionEventCompat.getPointerId(motionEvent, 0);
            this.s = motionEvent.getX();
        }
        return true;
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setCurrentItem(int i) {
        ViewPager viewPager = this.m;
        if (viewPager != null) {
            viewPager.setCurrentItem(i);
            this.p = i;
            invalidate();
            return;
        }
        throw new IllegalStateException("ViewPager has not been bound.");
    }

    public void setFadeDelay(int i) {
        this.j = i;
    }

    public void setFadeLength(int i) {
        this.k = i;
        this.l = 255 / (i / 30);
    }

    public void setFades(boolean z) {
        if (z != this.i) {
            this.i = z;
            if (z) {
                post(this.v);
                return;
            }
            removeCallbacks(this.v);
            this.h.setAlpha(255);
            invalidate();
        }
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.n = onPageChangeListener;
    }

    public void setSelectedColor(int i) {
        this.h.setColor(i);
        invalidate();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager) {
        ViewPager viewPager2 = this.m;
        if (viewPager2 == viewPager) {
            return;
        }
        if (viewPager2 != null) {
            viewPager2.setOnPageChangeListener(null);
        }
        if (viewPager.getAdapter() != null) {
            this.m = viewPager;
            viewPager.setOnPageChangeListener(this);
            invalidate();
            post(new b());
            return;
        }
        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    public UnderlinePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiUnderlinePageIndicatorStyle);
    }

    public UnderlinePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = new Paint(1);
        this.s = -1.0f;
        this.t = -1;
        this.v = new a();
        if (isInEditMode()) {
            return;
        }
        Resources resources = getResources();
        boolean z = resources.getBoolean(R.bool.default_underline_indicator_fades);
        int integer = resources.getInteger(R.integer.default_underline_indicator_fade_delay);
        int integer2 = resources.getInteger(R.integer.default_underline_indicator_fade_length);
        int color = resources.getColor(R.color.default_underline_indicator_selected_color);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.UnderlinePageIndicator, i, 0);
        setFades(obtainStyledAttributes.getBoolean(R.styleable.UnderlinePageIndicator_fades, z));
        setSelectedColor(obtainStyledAttributes.getColor(R.styleable.UnderlinePageIndicator_selectedColor, color));
        setFadeDelay(obtainStyledAttributes.getInteger(R.styleable.UnderlinePageIndicator_fadeDelay, integer));
        setFadeLength(obtainStyledAttributes.getInteger(R.styleable.UnderlinePageIndicator_fadeLength, integer2));
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.UnderlinePageIndicator_android_background);
        if (drawable != null) {
            setBackgroundDrawable(drawable);
        }
        obtainStyledAttributes.recycle();
        this.r = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }
}
