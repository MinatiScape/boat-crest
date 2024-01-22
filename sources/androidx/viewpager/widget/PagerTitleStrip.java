package androidx.viewpager.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.ViewPager;
import java.lang.ref.WeakReference;
import java.util.Locale;
@ViewPager.DecorView
/* loaded from: classes.dex */
public class PagerTitleStrip extends ViewGroup {
    public static final int[] v = {16842804, 16842901, 16842904, 16842927};
    public static final int[] w = {16843660};
    public ViewPager h;
    public TextView i;
    public TextView j;
    public TextView k;
    public int l;
    public float m;
    public int n;
    public int o;
    public boolean p;
    public boolean q;
    public final a r;
    public WeakReference<PagerAdapter> s;
    public int t;
    public int u;

    /* loaded from: classes.dex */
    public class a extends DataSetObserver implements ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {
        public int h;

        public a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
        public void onAdapterChanged(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
            PagerTitleStrip.this.a(pagerAdapter, pagerAdapter2);
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
            pagerTitleStrip.b(pagerTitleStrip.h.getCurrentItem(), PagerTitleStrip.this.h.getAdapter());
            PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
            float f = pagerTitleStrip2.m;
            if (f < 0.0f) {
                f = 0.0f;
            }
            pagerTitleStrip2.c(pagerTitleStrip2.h.getCurrentItem(), f, true);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            this.h = i;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            if (f > 0.5f) {
                i++;
            }
            PagerTitleStrip.this.c(i, f, false);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            if (this.h == 0) {
                PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
                pagerTitleStrip.b(pagerTitleStrip.h.getCurrentItem(), PagerTitleStrip.this.h.getAdapter());
                PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
                float f = pagerTitleStrip2.m;
                if (f < 0.0f) {
                    f = 0.0f;
                }
                pagerTitleStrip2.c(pagerTitleStrip2.h.getCurrentItem(), f, true);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b extends SingleLineTransformationMethod {
        public Locale h;

        public b(Context context) {
            this.h = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.h);
            }
            return null;
        }
    }

    public PagerTitleStrip(@NonNull Context context) {
        this(context, null);
    }

    private static void setSingleLineAllCaps(TextView textView) {
        textView.setTransformationMethod(new b(textView.getContext()));
    }

    public void a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
        if (pagerAdapter != null) {
            pagerAdapter.unregisterDataSetObserver(this.r);
            this.s = null;
        }
        if (pagerAdapter2 != null) {
            pagerAdapter2.registerDataSetObserver(this.r);
            this.s = new WeakReference<>(pagerAdapter2);
        }
        ViewPager viewPager = this.h;
        if (viewPager != null) {
            this.l = -1;
            this.m = -1.0f;
            b(viewPager.getCurrentItem(), pagerAdapter2);
            requestLayout();
        }
    }

    public void b(int i, PagerAdapter pagerAdapter) {
        int count = pagerAdapter != null ? pagerAdapter.getCount() : 0;
        this.p = true;
        CharSequence charSequence = null;
        this.i.setText((i < 1 || pagerAdapter == null) ? null : pagerAdapter.getPageTitle(i - 1));
        this.j.setText((pagerAdapter == null || i >= count) ? null : pagerAdapter.getPageTitle(i));
        int i2 = i + 1;
        if (i2 < count && pagerAdapter != null) {
            charSequence = pagerAdapter.getPageTitle(i2);
        }
        this.k.setText(charSequence);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.i.measure(makeMeasureSpec, makeMeasureSpec2);
        this.j.measure(makeMeasureSpec, makeMeasureSpec2);
        this.k.measure(makeMeasureSpec, makeMeasureSpec2);
        this.l = i;
        if (!this.q) {
            c(i, this.m, false);
        }
        this.p = false;
    }

    public void c(int i, float f, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (i != this.l) {
            b(i, this.h.getAdapter());
        } else if (!z && f == this.m) {
            return;
        }
        this.q = true;
        int measuredWidth = this.i.getMeasuredWidth();
        int measuredWidth2 = this.j.getMeasuredWidth();
        int measuredWidth3 = this.k.getMeasuredWidth();
        int i6 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i7 = paddingRight + i6;
        int i8 = (width - (paddingLeft + i6)) - i7;
        float f2 = 0.5f + f;
        if (f2 > 1.0f) {
            f2 -= 1.0f;
        }
        int i9 = ((width - i7) - ((int) (i8 * f2))) - i6;
        int i10 = measuredWidth2 + i9;
        int baseline = this.i.getBaseline();
        int baseline2 = this.j.getBaseline();
        int baseline3 = this.k.getBaseline();
        int max = Math.max(Math.max(baseline, baseline2), baseline3);
        int i11 = max - baseline;
        int i12 = max - baseline2;
        int i13 = max - baseline3;
        int max2 = Math.max(Math.max(this.i.getMeasuredHeight() + i11, this.j.getMeasuredHeight() + i12), this.k.getMeasuredHeight() + i13);
        int i14 = this.o & 112;
        if (i14 == 16) {
            i2 = (((height - paddingTop) - paddingBottom) - max2) / 2;
        } else if (i14 != 80) {
            i3 = i11 + paddingTop;
            i4 = i12 + paddingTop;
            i5 = paddingTop + i13;
            TextView textView = this.j;
            textView.layout(i9, i4, i10, textView.getMeasuredHeight() + i4);
            int min = Math.min(paddingLeft, (i9 - this.n) - measuredWidth);
            TextView textView2 = this.i;
            textView2.layout(min, i3, measuredWidth + min, textView2.getMeasuredHeight() + i3);
            int max3 = Math.max((width - paddingRight) - measuredWidth3, i10 + this.n);
            TextView textView3 = this.k;
            textView3.layout(max3, i5, max3 + measuredWidth3, textView3.getMeasuredHeight() + i5);
            this.m = f;
            this.q = false;
        } else {
            i2 = (height - paddingBottom) - max2;
        }
        i3 = i11 + i2;
        i4 = i12 + i2;
        i5 = i2 + i13;
        TextView textView4 = this.j;
        textView4.layout(i9, i4, i10, textView4.getMeasuredHeight() + i4);
        int min2 = Math.min(paddingLeft, (i9 - this.n) - measuredWidth);
        TextView textView22 = this.i;
        textView22.layout(min2, i3, measuredWidth + min2, textView22.getMeasuredHeight() + i3);
        int max32 = Math.max((width - paddingRight) - measuredWidth3, i10 + this.n);
        TextView textView32 = this.k;
        textView32.layout(max32, i5, max32 + measuredWidth3, textView32.getMeasuredHeight() + i5);
        this.m = f;
        this.q = false;
    }

    public int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.n;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) parent;
            PagerAdapter adapter = viewPager.getAdapter();
            viewPager.setInternalPageChangeListener(this.r);
            viewPager.addOnAdapterChangeListener(this.r);
            this.h = viewPager;
            WeakReference<PagerAdapter> weakReference = this.s;
            a(weakReference != null ? weakReference.get() : null, adapter);
            return;
        }
        throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.h;
        if (viewPager != null) {
            a(viewPager.getAdapter(), null);
            this.h.setInternalPageChangeListener(null);
            this.h.removeOnAdapterChangeListener(this.r);
            this.h = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.h != null) {
            float f = this.m;
            if (f < 0.0f) {
                f = 0.0f;
            }
            c(this.l, f, true);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int max;
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingTop, -2);
            int size = View.MeasureSpec.getSize(i);
            int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i, (int) (size * 0.2f), -2);
            this.i.measure(childMeasureSpec2, childMeasureSpec);
            this.j.measure(childMeasureSpec2, childMeasureSpec);
            this.k.measure(childMeasureSpec2, childMeasureSpec);
            if (View.MeasureSpec.getMode(i2) == 1073741824) {
                max = View.MeasureSpec.getSize(i2);
            } else {
                max = Math.max(getMinHeight(), this.j.getMeasuredHeight() + paddingTop);
            }
            setMeasuredDimension(size, View.resolveSizeAndState(max, i2, this.j.getMeasuredState() << 16));
            return;
        }
        throw new IllegalStateException("Must measure with an exact width");
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.p) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i) {
        this.o = i;
        requestLayout();
    }

    public void setNonPrimaryAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        int i = ((int) (f * 255.0f)) & 255;
        this.t = i;
        int i2 = (i << 24) | (this.u & 16777215);
        this.i.setTextColor(i2);
        this.k.setTextColor(i2);
    }

    public void setTextColor(@ColorInt int i) {
        this.u = i;
        this.j.setTextColor(i);
        int i2 = (this.t << 24) | (this.u & 16777215);
        this.i.setTextColor(i2);
        this.k.setTextColor(i2);
    }

    public void setTextSize(int i, float f) {
        this.i.setTextSize(i, f);
        this.j.setTextSize(i, f);
        this.k.setTextSize(i, f);
    }

    public void setTextSpacing(int i) {
        this.n = i;
        requestLayout();
    }

    public PagerTitleStrip(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = -1;
        this.m = -1.0f;
        this.r = new a();
        TextView textView = new TextView(context);
        this.i = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.j = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.k = textView3;
        addView(textView3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, v);
        boolean z = false;
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            TextViewCompat.setTextAppearance(this.i, resourceId);
            TextViewCompat.setTextAppearance(this.j, resourceId);
            TextViewCompat.setTextAppearance(this.k, resourceId);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            setTextSize(0, dimensionPixelSize);
        }
        if (obtainStyledAttributes.hasValue(2)) {
            int color = obtainStyledAttributes.getColor(2, 0);
            this.i.setTextColor(color);
            this.j.setTextColor(color);
            this.k.setTextColor(color);
        }
        this.o = obtainStyledAttributes.getInteger(3, 80);
        obtainStyledAttributes.recycle();
        this.u = this.j.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.i.setEllipsize(TextUtils.TruncateAt.END);
        this.j.setEllipsize(TextUtils.TruncateAt.END);
        this.k.setEllipsize(TextUtils.TruncateAt.END);
        if (resourceId != 0) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, w);
            z = obtainStyledAttributes2.getBoolean(0, false);
            obtainStyledAttributes2.recycle();
        }
        if (z) {
            setSingleLineAllCaps(this.i);
            setSingleLineAllCaps(this.j);
            setSingleLineAllCaps(this.k);
        } else {
            this.i.setSingleLine();
            this.j.setSingleLine();
            this.k.setSingleLine();
        }
        this.n = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }
}
