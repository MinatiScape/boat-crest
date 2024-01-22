package com.coveiot.android.activitymodes.adapters;

import androidx.viewpager.widget.ViewPager;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class OneKFtuListener implements ViewPager.OnPageChangeListener {
    @NotNull
    public final Function1<Integer, Unit> h;

    /* JADX WARN: Multi-variable type inference failed */
    public OneKFtuListener(@NotNull Function1<? super Integer, Unit> closure) {
        Intrinsics.checkNotNullParameter(closure, "closure");
        this.h = closure;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.h.invoke(Integer.valueOf(i));
    }
}
