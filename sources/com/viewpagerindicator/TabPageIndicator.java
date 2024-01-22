package com.viewpagerindicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
/* loaded from: classes12.dex */
public class TabPageIndicator extends HorizontalScrollView implements PageIndicator {
    public static final CharSequence p = "";
    public Runnable h;
    public final View.OnClickListener i;
    public final com.viewpagerindicator.a j;
    public ViewPager k;
    public ViewPager.OnPageChangeListener l;
    public int m;
    public int n;
    public OnTabReselectedListener o;

    /* loaded from: classes12.dex */
    public interface OnTabReselectedListener {
        void onTabReselected(int i);
    }

    /* loaded from: classes12.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int currentItem = TabPageIndicator.this.k.getCurrentItem();
            int b = ((c) view).b();
            TabPageIndicator.this.k.setCurrentItem(b);
            if (currentItem != b || TabPageIndicator.this.o == null) {
                return;
            }
            TabPageIndicator.this.o.onTabReselected(b);
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Runnable {
        public final /* synthetic */ View h;

        public b(View view) {
            this.h = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            TabPageIndicator.this.smoothScrollTo(this.h.getLeft() - ((TabPageIndicator.this.getWidth() - this.h.getWidth()) / 2), 0);
            TabPageIndicator.this.h = null;
        }
    }

    /* loaded from: classes12.dex */
    public class c extends TextView {
        public int h;

        public c(Context context) {
            super(context, null, R.attr.vpiTabPageIndicatorStyle);
        }

        public int b() {
            return this.h;
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (TabPageIndicator.this.m <= 0 || getMeasuredWidth() <= TabPageIndicator.this.m) {
                return;
            }
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(TabPageIndicator.this.m, 1073741824), i2);
        }
    }

    public TabPageIndicator(Context context) {
        this(context, null);
    }

    public final void e(int i, CharSequence charSequence, int i2) {
        c cVar = new c(getContext());
        cVar.h = i;
        cVar.setFocusable(true);
        cVar.setOnClickListener(this.i);
        cVar.setText(charSequence);
        if (i2 != 0) {
            cVar.setCompoundDrawablesWithIntrinsicBounds(i2, 0, 0, 0);
        }
        this.j.addView(cVar, new LinearLayout.LayoutParams(0, -1, 1.0f));
    }

    public final void f(int i) {
        View childAt = this.j.getChildAt(i);
        Runnable runnable = this.h;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        b bVar = new b(childAt);
        this.h = bVar;
        post(bVar);
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void notifyDataSetChanged() {
        this.j.removeAllViews();
        PagerAdapter adapter = this.k.getAdapter();
        IconPagerAdapter iconPagerAdapter = adapter instanceof IconPagerAdapter ? (IconPagerAdapter) adapter : null;
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            CharSequence pageTitle = adapter.getPageTitle(i);
            if (pageTitle == null) {
                pageTitle = p;
            }
            e(i, pageTitle, iconPagerAdapter != null ? iconPagerAdapter.getIconResId(i) : 0);
        }
        if (this.n > count) {
            this.n = count - 1;
        }
        setCurrentItem(this.n);
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.h;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.h;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        int childCount = this.j.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.m = -1;
        } else if (childCount > 2) {
            this.m = (int) (View.MeasureSpec.getSize(i) * 0.4f);
        } else {
            this.m = View.MeasureSpec.getSize(i) / 2;
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, i2);
        int measuredWidth2 = getMeasuredWidth();
        if (!z || measuredWidth == measuredWidth2) {
            return;
        }
        setCurrentItem(this.n);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.l;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.l;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        setCurrentItem(i);
        ViewPager.OnPageChangeListener onPageChangeListener = this.l;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i);
        }
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setCurrentItem(int i) {
        ViewPager viewPager = this.k;
        if (viewPager != null) {
            this.n = i;
            viewPager.setCurrentItem(i);
            int childCount = this.j.getChildCount();
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = this.j.getChildAt(i2);
                boolean z = i2 == i;
                childAt.setSelected(z);
                if (z) {
                    f(i);
                }
                i2++;
            }
            return;
        }
        throw new IllegalStateException("ViewPager has not been bound.");
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.l = onPageChangeListener;
    }

    public void setOnTabReselectedListener(OnTabReselectedListener onTabReselectedListener) {
        this.o = onTabReselectedListener;
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager) {
        ViewPager viewPager2 = this.k;
        if (viewPager2 == viewPager) {
            return;
        }
        if (viewPager2 != null) {
            viewPager2.setOnPageChangeListener(null);
        }
        if (viewPager.getAdapter() != null) {
            this.k = viewPager;
            viewPager.setOnPageChangeListener(this);
            notifyDataSetChanged();
            return;
        }
        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    public TabPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = new a();
        setHorizontalScrollBarEnabled(false);
        com.viewpagerindicator.a aVar = new com.viewpagerindicator.a(context, R.attr.vpiTabPageIndicatorStyle);
        this.j = aVar;
        addView(aVar, new ViewGroup.LayoutParams(-2, -1));
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }
}
