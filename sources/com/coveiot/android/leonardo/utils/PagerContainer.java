package com.coveiot.android.leonardo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
/* loaded from: classes5.dex */
public class PagerContainer extends ViewPager implements ViewPager.PageTransformer {
    public static final String TAG = "PagerContainer";
    public float h;
    public int i;
    public boolean j;
    public boolean k;
    public float l;

    public PagerContainer(Context context) {
        this(context, null);
    }

    public int dp2px(Resources resources, int i) {
        return (int) TypedValue.applyDimension(1, i, resources.getDisplayMetrics());
    }

    public void setAnimationEnabled(boolean z) {
        this.j = z;
    }

    public void setFadeEnabled(boolean z) {
        this.k = z;
    }

    public void setFadeFactor(float f) {
        this.l = f;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setPageMargin(int i) {
        this.i = i;
        setPadding(i, i, i, i);
    }

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        int i = this.i;
        if (i <= 0 || !this.j) {
            return;
        }
        view.setPadding(i / 3, i / 3, i / 3, i / 3);
        if (this.h == 0.0f && f > 0.0f && f < 1.0f) {
            this.h = f;
        }
        float f2 = f - this.h;
        float abs = Math.abs(f2);
        if (f2 <= -1.0f || f2 >= 1.0f) {
            if (this.k) {
                view.setAlpha(this.l);
            }
        } else if (f2 == 0.0f) {
            view.setScaleX(this.h + 1.0f);
            view.setScaleY(this.h + 1.0f);
            view.setAlpha(1.0f);
        } else {
            float f3 = 1.0f - abs;
            view.setScaleX((this.h * f3) + 1.0f);
            view.setScaleY((this.h * f3) + 1.0f);
            if (this.k) {
                view.setAlpha(Math.max(this.l, f3));
            }
        }
    }

    public PagerContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 0.0f;
        this.j = true;
        this.k = false;
        this.l = 0.1f;
        setClipChildren(false);
        setClipToPadding(false);
        setOverScrollMode(2);
        setPageTransformer(false, this);
        setOffscreenPageLimit(3);
        int dp2px = dp2px(context.getResources(), 40);
        this.i = dp2px;
        setPadding(dp2px, 0, dp2px, 0);
    }
}
