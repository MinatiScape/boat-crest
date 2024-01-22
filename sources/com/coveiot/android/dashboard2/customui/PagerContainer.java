package com.coveiot.android.dashboard2.customui;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
/* loaded from: classes4.dex */
public class PagerContainer extends ViewPager implements ViewPager.PageTransformer {
    public static final String TAG = "PagerContainer";
    public float h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public boolean n;
    public boolean o;
    public float p;

    public PagerContainer(Context context) {
        this(context, null);
    }

    public int dp2px(Resources resources, int i) {
        return (int) TypedValue.applyDimension(1, i, resources.getDisplayMetrics());
    }

    public void setAnimationEnabled(boolean z) {
        this.n = z;
    }

    public void setFadeEnabled(boolean z) {
        this.o = z;
    }

    public void setFadeFactor(float f) {
        this.p = f;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setPageMargin(int i) {
        this.i = i;
        setPadding(this.j, this.l, this.k, this.m);
    }

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        if (this.i <= 0 || !this.n) {
            return;
        }
        view.setPadding(this.j / 3, this.l / 3, this.k / 3, this.m / 3);
        if (this.h == 0.0f && f > 0.0f && f < 1.0f) {
            this.h = f;
        }
        float f2 = f - this.h;
        float abs = Math.abs(f2);
        if (f2 <= -1.0f || f2 >= 1.0f) {
            if (this.o) {
                view.setAlpha(this.p);
            }
        } else if (f2 == 0.0f) {
            view.setScaleX(this.h + 1.0f);
            view.setScaleY(this.h + 1.0f);
            view.setAlpha(1.0f);
        } else {
            float f3 = 1.0f - abs;
            view.setScaleX((this.h * f3) + 1.0f);
            view.setScaleY((this.h * f3) + 1.0f);
            if (this.o) {
                view.setAlpha(Math.max(this.p, f3));
            }
        }
    }

    public PagerContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 0.0f;
        this.n = true;
        this.o = false;
        this.p = 0.1f;
        setClipChildren(false);
        setClipToPadding(false);
        setOverScrollMode(2);
        setPageTransformer(false, this);
        setOffscreenPageLimit(3);
        this.i = dp2px(context.getResources(), 0);
        this.j = dp2px(context.getResources(), 0);
        if (getResources().getDisplayMetrics().density < 3.0f) {
            this.k = dp2px(context.getResources(), 100);
        } else if (getResources().getDisplayMetrics().density < 4.0f) {
            this.k = dp2px(context.getResources(), 60);
        } else {
            this.k = dp2px(context.getResources(), 40);
        }
        this.l = dp2px(context.getResources(), 0);
        int dp2px = dp2px(context.getResources(), 0);
        this.m = dp2px;
        setPadding(this.j, this.l, this.k, dp2px);
    }
}
