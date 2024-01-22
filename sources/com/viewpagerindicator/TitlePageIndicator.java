package com.viewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
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
import java.util.ArrayList;
/* loaded from: classes12.dex */
public class TitlePageIndicator extends View implements PageIndicator {
    public float A;
    public float B;
    public float C;
    public int D;
    public float E;
    public int F;
    public boolean G;
    public OnCenterItemClickListener H;
    public ViewPager h;
    public ViewPager.OnPageChangeListener i;
    public int j;
    public float k;
    public int l;
    public final Paint m;
    public boolean n;
    public int o;
    public int p;
    public Path q;
    public final Rect r;
    public final Paint s;
    public IndicatorStyle t;
    public LinePosition u;
    public final Paint v;
    public float w;
    public float x;
    public float y;
    public float z;

    /* loaded from: classes12.dex */
    public enum IndicatorStyle {
        None(0),
        Triangle(1),
        Underline(2);
        
        public final int value;

        IndicatorStyle(int i) {
            this.value = i;
        }

        public static IndicatorStyle fromValue(int i) {
            IndicatorStyle[] values;
            for (IndicatorStyle indicatorStyle : values()) {
                if (indicatorStyle.value == i) {
                    return indicatorStyle;
                }
            }
            return null;
        }
    }

    /* loaded from: classes12.dex */
    public enum LinePosition {
        Bottom(0),
        Top(1);
        
        public final int value;

        LinePosition(int i) {
            this.value = i;
        }

        public static LinePosition fromValue(int i) {
            LinePosition[] values;
            for (LinePosition linePosition : values()) {
                if (linePosition.value == i) {
                    return linePosition;
                }
            }
            return null;
        }
    }

    /* loaded from: classes12.dex */
    public interface OnCenterItemClickListener {
        void onCenterItemClick(int i);
    }

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
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f13865a;

        static {
            int[] iArr = new int[IndicatorStyle.values().length];
            f13865a = iArr;
            try {
                iArr[IndicatorStyle.Triangle.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13865a[IndicatorStyle.Underline.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public TitlePageIndicator(Context context) {
        this(context, null);
    }

    public final Rect a(int i, Paint paint) {
        Rect rect = new Rect();
        CharSequence e = e(i);
        rect.right = (int) paint.measureText(e, 0, e.length());
        rect.bottom = (int) (paint.descent() - paint.ascent());
        return rect;
    }

    public final ArrayList<Rect> b(Paint paint) {
        ArrayList<Rect> arrayList = new ArrayList<>();
        int count = this.h.getAdapter().getCount();
        int width = getWidth();
        int i = width / 2;
        for (int i2 = 0; i2 < count; i2++) {
            Rect a2 = a(i2, paint);
            int i3 = a2.right - a2.left;
            int i4 = a2.bottom - a2.top;
            int i5 = (int) ((i - (i3 / 2.0f)) + (((i2 - this.j) - this.k) * width));
            a2.left = i5;
            a2.right = i5 + i3;
            a2.top = 0;
            a2.bottom = i4;
            arrayList.add(a2);
        }
        return arrayList;
    }

    public final void c(Rect rect, float f, int i) {
        float f2 = this.B;
        rect.left = (int) (i + f2);
        rect.right = (int) (f2 + f);
    }

    public final void d(Rect rect, float f, int i) {
        int i2 = (int) (i - this.B);
        rect.right = i2;
        rect.left = (int) (i2 - f);
    }

    public final CharSequence e(int i) {
        CharSequence pageTitle = this.h.getAdapter().getPageTitle(i);
        return pageTitle == null ? "" : pageTitle;
    }

    public float getClipPadding() {
        return this.B;
    }

    public int getFooterColor() {
        return this.s.getColor();
    }

    public float getFooterIndicatorHeight() {
        return this.w;
    }

    public float getFooterIndicatorPadding() {
        return this.y;
    }

    public IndicatorStyle getFooterIndicatorStyle() {
        return this.t;
    }

    public float getFooterLineHeight() {
        return this.C;
    }

    public LinePosition getLinePosition() {
        return this.u;
    }

    public int getSelectedColor() {
        return this.p;
    }

    public int getTextColor() {
        return this.o;
    }

    public float getTextSize() {
        return this.m.getTextSize();
    }

    public float getTitlePadding() {
        return this.z;
    }

    public float getTopPadding() {
        return this.A;
    }

    public Typeface getTypeface() {
        return this.m.getTypeface();
    }

    public boolean isSelectedBold() {
        return this.n;
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void notifyDataSetChanged() {
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int count;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        float f;
        int i8;
        int i9;
        ViewPager viewPager;
        super.onDraw(canvas);
        ViewPager viewPager2 = this.h;
        if (viewPager2 == null || (count = viewPager2.getAdapter().getCount()) == 0) {
            return;
        }
        if (this.j == -1 && (viewPager = this.h) != null) {
            this.j = viewPager.getCurrentItem();
        }
        ArrayList<Rect> b = b(this.m);
        int size = b.size();
        if (this.j >= size) {
            setCurrentItem(size - 1);
            return;
        }
        int i10 = count - 1;
        float width = getWidth() / 2.0f;
        int left = getLeft();
        float f2 = left + this.B;
        int width2 = getWidth();
        int height = getHeight();
        int i11 = left + width2;
        float f3 = i11 - this.B;
        int i12 = this.j;
        float f4 = this.k;
        if (f4 <= 0.5d) {
            i = i12;
        } else {
            i = i12 + 1;
            f4 = 1.0f - f4;
        }
        boolean z = f4 <= 0.25f;
        boolean z2 = f4 <= 0.05f;
        float f5 = (0.25f - f4) / 0.25f;
        Rect rect = b.get(i12);
        int i13 = rect.right;
        int i14 = rect.left;
        float f6 = i13 - i14;
        if (i14 < f2) {
            c(rect, f6, left);
        }
        if (rect.right > f3) {
            d(rect, f6, i11);
        }
        int i15 = this.j;
        if (i15 > 0) {
            int i16 = i15 - 1;
            while (i16 >= 0) {
                Rect rect2 = b.get(i16);
                int i17 = rect2.left;
                int i18 = width2;
                if (i17 < f2) {
                    int i19 = rect2.right - i17;
                    c(rect2, i19, left);
                    f = f2;
                    float f7 = this.z;
                    i8 = size;
                    if (rect2.right + f7 > b.get(i16 + 1).left) {
                        int i20 = (int) ((i9 - i19) - f7);
                        rect2.left = i20;
                        rect2.right = i20 + i19;
                    }
                } else {
                    f = f2;
                    i8 = size;
                }
                i16--;
                width2 = i18;
                f2 = f;
                size = i8;
            }
        }
        int i21 = width2;
        int i22 = size;
        int i23 = this.j;
        if (i23 < i10) {
            for (int i24 = i23 + 1; i24 < count; i24++) {
                Rect rect3 = b.get(i24);
                int i25 = rect3.right;
                if (i25 > f3) {
                    int i26 = i25 - rect3.left;
                    d(rect3, i26, i11);
                    float f8 = this.z;
                    int i27 = b.get(i24 - 1).right;
                    if (rect3.left - f8 < i27) {
                        int i28 = (int) (i27 + f8);
                        rect3.left = i28;
                        rect3.right = i28 + i26;
                    }
                }
            }
        }
        int i29 = this.o >>> 24;
        int i30 = 0;
        while (i30 < count) {
            Rect rect4 = b.get(i30);
            int i31 = rect4.left;
            if ((i31 <= left || i31 >= i11) && ((i3 = rect4.right) <= left || i3 >= i11)) {
                i4 = i11;
                i5 = i29;
                i6 = i21;
            } else {
                boolean z3 = i30 == i;
                CharSequence e = e(i30);
                this.m.setFakeBoldText(z3 && z2 && this.n);
                this.m.setColor(this.o);
                if (z3 && z) {
                    this.m.setAlpha(i29 - ((int) (i29 * f5)));
                }
                if (i30 < i22 - 1) {
                    int i32 = rect4.right;
                    float f9 = this.z;
                    if (i32 + f9 > b.get(i30 + 1).left) {
                        int i33 = i32 - rect4.left;
                        int i34 = (int) ((i7 - i33) - f9);
                        rect4.left = i34;
                        rect4.right = i34 + i33;
                    }
                }
                i4 = i11;
                i5 = i29;
                i6 = i21;
                canvas.drawText(e, 0, e.length(), rect4.left, rect4.bottom + this.A, this.m);
                if (z3 && z) {
                    this.m.setColor(this.p);
                    this.m.setAlpha((int) ((this.p >>> 24) * f5));
                    canvas.drawText(e, 0, e.length(), rect4.left, rect4.bottom + this.A, this.m);
                }
            }
            i30++;
            i21 = i6;
            i11 = i4;
            i29 = i5;
        }
        int i35 = i21;
        float f10 = this.C;
        float f11 = this.w;
        if (this.u == LinePosition.Top) {
            f10 = -f10;
            f11 = -f11;
            i2 = 0;
        } else {
            i2 = height;
        }
        this.q.reset();
        float f12 = i2;
        float f13 = f12 - (f10 / 2.0f);
        this.q.moveTo(0.0f, f13);
        this.q.lineTo(i35, f13);
        this.q.close();
        canvas.drawPath(this.q, this.s);
        float f14 = f12 - f10;
        int i36 = a.f13865a[this.t.ordinal()];
        if (i36 == 1) {
            this.q.reset();
            this.q.moveTo(width, f14 - f11);
            this.q.lineTo(width + f11, f14);
            this.q.lineTo(width - f11, f14);
            this.q.close();
            canvas.drawPath(this.q, this.v);
        } else if (i36 == 2 && z && i < i22) {
            Rect rect5 = b.get(i);
            float f15 = this.x;
            float f16 = rect5.right + f15;
            float f17 = rect5.left - f15;
            float f18 = f14 - f11;
            this.q.reset();
            this.q.moveTo(f17, f14);
            this.q.lineTo(f16, f14);
            this.q.lineTo(f16, f18);
            this.q.lineTo(f17, f18);
            this.q.close();
            this.v.setAlpha((int) (f5 * 255.0f));
            canvas.drawPath(this.q, this.v);
            this.v.setAlpha(255);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        float f;
        int size = View.MeasureSpec.getSize(i);
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            f = View.MeasureSpec.getSize(i2);
        } else {
            this.r.setEmpty();
            this.r.bottom = (int) (this.m.descent() - this.m.ascent());
            Rect rect = this.r;
            f = (rect.bottom - rect.top) + this.C + this.y + this.A;
            if (this.t != IndicatorStyle.None) {
                f += this.w;
            }
        }
        setMeasuredDimension(size, (int) f);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        this.l = i;
        ViewPager.OnPageChangeListener onPageChangeListener = this.i;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.j = i;
        this.k = f;
        invalidate();
        ViewPager.OnPageChangeListener onPageChangeListener = this.i;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (this.l == 0) {
            this.j = i;
            invalidate();
        }
        ViewPager.OnPageChangeListener onPageChangeListener = this.i;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.j = savedState.h;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.h = this.j;
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        ViewPager viewPager = this.h;
        if (viewPager == null || viewPager.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    float x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.F));
                    float f = x - this.E;
                    if (!this.G && Math.abs(f) > this.D) {
                        this.G = true;
                    }
                    if (this.G) {
                        this.E = x;
                        if (this.h.isFakeDragging() || this.h.beginFakeDrag()) {
                            this.h.fakeDragBy(f);
                        }
                    }
                } else if (action != 3) {
                    if (action == 5) {
                        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                        this.E = MotionEventCompat.getX(motionEvent, actionIndex);
                        this.F = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    } else if (action == 6) {
                        int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                        if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.F) {
                            this.F = MotionEventCompat.getPointerId(motionEvent, actionIndex2 == 0 ? 1 : 0);
                        }
                        this.E = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.F));
                    }
                }
            }
            if (!this.G) {
                int count = this.h.getAdapter().getCount();
                float width = getWidth();
                float f2 = width / 2.0f;
                float f3 = width / 6.0f;
                float f4 = f2 - f3;
                float f5 = f2 + f3;
                float x2 = motionEvent.getX();
                if (x2 < f4) {
                    int i = this.j;
                    if (i > 0) {
                        if (action != 3) {
                            this.h.setCurrentItem(i - 1);
                        }
                        return true;
                    }
                } else if (x2 > f5) {
                    int i2 = this.j;
                    if (i2 < count - 1) {
                        if (action != 3) {
                            this.h.setCurrentItem(i2 + 1);
                        }
                        return true;
                    }
                } else {
                    OnCenterItemClickListener onCenterItemClickListener = this.H;
                    if (onCenterItemClickListener != null && action != 3) {
                        onCenterItemClickListener.onCenterItemClick(this.j);
                    }
                }
            }
            this.G = false;
            this.F = -1;
            if (this.h.isFakeDragging()) {
                this.h.endFakeDrag();
            }
        } else {
            this.F = MotionEventCompat.getPointerId(motionEvent, 0);
            this.E = motionEvent.getX();
        }
        return true;
    }

    public void setClipPadding(float f) {
        this.B = f;
        invalidate();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setCurrentItem(int i) {
        ViewPager viewPager = this.h;
        if (viewPager != null) {
            viewPager.setCurrentItem(i);
            this.j = i;
            invalidate();
            return;
        }
        throw new IllegalStateException("ViewPager has not been bound.");
    }

    public void setFooterColor(int i) {
        this.s.setColor(i);
        this.v.setColor(i);
        invalidate();
    }

    public void setFooterIndicatorHeight(float f) {
        this.w = f;
        invalidate();
    }

    public void setFooterIndicatorPadding(float f) {
        this.y = f;
        invalidate();
    }

    public void setFooterIndicatorStyle(IndicatorStyle indicatorStyle) {
        this.t = indicatorStyle;
        invalidate();
    }

    public void setFooterLineHeight(float f) {
        this.C = f;
        this.s.setStrokeWidth(f);
        invalidate();
    }

    public void setLinePosition(LinePosition linePosition) {
        this.u = linePosition;
        invalidate();
    }

    public void setOnCenterItemClickListener(OnCenterItemClickListener onCenterItemClickListener) {
        this.H = onCenterItemClickListener;
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.i = onPageChangeListener;
    }

    public void setSelectedBold(boolean z) {
        this.n = z;
        invalidate();
    }

    public void setSelectedColor(int i) {
        this.p = i;
        invalidate();
    }

    public void setTextColor(int i) {
        this.m.setColor(i);
        this.o = i;
        invalidate();
    }

    public void setTextSize(float f) {
        this.m.setTextSize(f);
        invalidate();
    }

    public void setTitlePadding(float f) {
        this.z = f;
        invalidate();
    }

    public void setTopPadding(float f) {
        this.A = f;
        invalidate();
    }

    public void setTypeface(Typeface typeface) {
        this.m.setTypeface(typeface);
        invalidate();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager) {
        ViewPager viewPager2 = this.h;
        if (viewPager2 == viewPager) {
            return;
        }
        if (viewPager2 != null) {
            viewPager2.setOnPageChangeListener(null);
        }
        if (viewPager.getAdapter() != null) {
            this.h = viewPager;
            viewPager.setOnPageChangeListener(this);
            invalidate();
            return;
        }
        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    public TitlePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiTitlePageIndicatorStyle);
    }

    public TitlePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = -1;
        Paint paint = new Paint();
        this.m = paint;
        this.q = new Path();
        this.r = new Rect();
        Paint paint2 = new Paint();
        this.s = paint2;
        Paint paint3 = new Paint();
        this.v = paint3;
        this.E = -1.0f;
        this.F = -1;
        if (isInEditMode()) {
            return;
        }
        Resources resources = getResources();
        int color = resources.getColor(R.color.default_title_indicator_footer_color);
        float dimension = resources.getDimension(R.dimen.default_title_indicator_footer_line_height);
        int integer = resources.getInteger(R.integer.default_title_indicator_footer_indicator_style);
        float dimension2 = resources.getDimension(R.dimen.default_title_indicator_footer_indicator_height);
        float dimension3 = resources.getDimension(R.dimen.default_title_indicator_footer_indicator_underline_padding);
        float dimension4 = resources.getDimension(R.dimen.default_title_indicator_footer_padding);
        int integer2 = resources.getInteger(R.integer.default_title_indicator_line_position);
        int color2 = resources.getColor(R.color.default_title_indicator_selected_color);
        boolean z = resources.getBoolean(R.bool.default_title_indicator_selected_bold);
        int color3 = resources.getColor(R.color.default_title_indicator_text_color);
        float dimension5 = resources.getDimension(R.dimen.default_title_indicator_text_size);
        float dimension6 = resources.getDimension(R.dimen.default_title_indicator_title_padding);
        float dimension7 = resources.getDimension(R.dimen.default_title_indicator_clip_padding);
        float dimension8 = resources.getDimension(R.dimen.default_title_indicator_top_padding);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TitlePageIndicator, i, 0);
        this.C = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_footerLineHeight, dimension);
        this.t = IndicatorStyle.fromValue(obtainStyledAttributes.getInteger(R.styleable.TitlePageIndicator_footerIndicatorStyle, integer));
        this.w = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_footerIndicatorHeight, dimension2);
        this.x = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_footerIndicatorUnderlinePadding, dimension3);
        this.y = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_footerPadding, dimension4);
        this.u = LinePosition.fromValue(obtainStyledAttributes.getInteger(R.styleable.TitlePageIndicator_linePosition, integer2));
        this.A = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_topPadding, dimension8);
        this.z = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_titlePadding, dimension6);
        this.B = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_clipPadding, dimension7);
        this.p = obtainStyledAttributes.getColor(R.styleable.TitlePageIndicator_selectedColor, color2);
        this.o = obtainStyledAttributes.getColor(R.styleable.TitlePageIndicator_android_textColor, color3);
        this.n = obtainStyledAttributes.getBoolean(R.styleable.TitlePageIndicator_selectedBold, z);
        float dimension9 = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_android_textSize, dimension5);
        int color4 = obtainStyledAttributes.getColor(R.styleable.TitlePageIndicator_footerColor, color);
        paint.setTextSize(dimension9);
        paint.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setStrokeWidth(this.C);
        paint2.setColor(color4);
        paint3.setStyle(Paint.Style.FILL_AND_STROKE);
        paint3.setColor(color4);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.TitlePageIndicator_android_background);
        if (drawable != null) {
            setBackgroundDrawable(drawable);
        }
        obtainStyledAttributes.recycle();
        this.D = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }
}
