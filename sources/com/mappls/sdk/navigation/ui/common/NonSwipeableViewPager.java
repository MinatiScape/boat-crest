package com.mappls.sdk.navigation.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.annotation.Keep;
import androidx.viewpager.widget.ViewPager;
import java.lang.reflect.Field;
@Keep
/* loaded from: classes11.dex */
public class NonSwipeableViewPager extends ViewPager {
    private Boolean mOverrideSmoothScroll;

    /* loaded from: classes11.dex */
    public class a extends Scroller {
        public a(NonSwipeableViewPager nonSwipeableViewPager, Context context) {
            super(context, new DecelerateInterpolator());
        }

        @Override // android.widget.Scroller
        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            super.startScroll(i, i2, i3, i4, 350);
        }
    }

    public NonSwipeableViewPager(Context context) {
        super(context);
        this.mOverrideSmoothScroll = null;
        setMyScroller();
    }

    public NonSwipeableViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOverrideSmoothScroll = null;
        setMyScroller();
    }

    private void setMyScroller() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, new a(this, getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i) {
        Boolean bool = this.mOverrideSmoothScroll;
        super.setCurrentItem(i, bool != null ? bool.booleanValue() : true);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i, boolean z) {
        Boolean bool = this.mOverrideSmoothScroll;
        if (bool != null) {
            z = bool.booleanValue();
        }
        super.setCurrentItem(i, z);
    }

    public void setOverrideSmoothScroll(Boolean bool) {
        this.mOverrideSmoothScroll = bool;
    }
}
