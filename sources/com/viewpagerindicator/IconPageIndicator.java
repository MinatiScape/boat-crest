package com.viewpagerindicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import androidx.viewpager.widget.ViewPager;
/* loaded from: classes12.dex */
public class IconPageIndicator extends HorizontalScrollView implements PageIndicator {
    public final com.viewpagerindicator.a h;
    public ViewPager i;
    public ViewPager.OnPageChangeListener j;
    public Runnable k;
    public int l;

    /* loaded from: classes12.dex */
    public class a implements Runnable {
        public final /* synthetic */ View h;

        public a(View view) {
            this.h = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            IconPageIndicator.this.smoothScrollTo(this.h.getLeft() - ((IconPageIndicator.this.getWidth() - this.h.getWidth()) / 2), 0);
            IconPageIndicator.this.k = null;
        }
    }

    public IconPageIndicator(Context context) {
        this(context, null);
    }

    public final void b(int i) {
        View childAt = this.h.getChildAt(i);
        Runnable runnable = this.k;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        a aVar = new a(childAt);
        this.k = aVar;
        post(aVar);
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void notifyDataSetChanged() {
        this.h.removeAllViews();
        IconPagerAdapter iconPagerAdapter = (IconPagerAdapter) this.i.getAdapter();
        int count = iconPagerAdapter.getCount();
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(getContext(), null, R.attr.vpiIconPageIndicatorStyle);
            imageView.setImageResource(iconPagerAdapter.getIconResId(i));
            this.h.addView(imageView);
        }
        if (this.l > count) {
            this.l = count - 1;
        }
        setCurrentItem(this.l);
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.k;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.k;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.j;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.j;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        setCurrentItem(i);
        ViewPager.OnPageChangeListener onPageChangeListener = this.j;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i);
        }
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setCurrentItem(int i) {
        ViewPager viewPager = this.i;
        if (viewPager != null) {
            this.l = i;
            viewPager.setCurrentItem(i);
            int childCount = this.h.getChildCount();
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = this.h.getChildAt(i2);
                boolean z = i2 == i;
                childAt.setSelected(z);
                if (z) {
                    b(i);
                }
                i2++;
            }
            return;
        }
        throw new IllegalStateException("ViewPager has not been bound.");
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.j = onPageChangeListener;
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager) {
        ViewPager viewPager2 = this.i;
        if (viewPager2 == viewPager) {
            return;
        }
        if (viewPager2 != null) {
            viewPager2.setOnPageChangeListener(null);
        }
        if (viewPager.getAdapter() != null) {
            this.i = viewPager;
            viewPager.setOnPageChangeListener(this);
            notifyDataSetChanged();
            return;
        }
        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    public IconPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setHorizontalScrollBarEnabled(false);
        com.viewpagerindicator.a aVar = new com.viewpagerindicator.a(context, R.attr.vpiIconPageIndicatorStyle);
        this.h = aVar;
        addView(aVar, new FrameLayout.LayoutParams(-2, -1, 17));
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }
}
