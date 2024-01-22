package com.coveiot.android.theme.compundview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;
/* loaded from: classes7.dex */
public class ScrollDisabledViewpager extends ViewPager {
    public boolean h;

    public ScrollDisabledViewpager(Context context) {
        super(context);
        this.h = false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.h && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.h && super.onTouchEvent(motionEvent);
    }

    public void setPagingEnabled(boolean z) {
        this.h = z;
    }

    public ScrollDisabledViewpager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = false;
    }
}
