package com.coveiot.android.watchfaceui.listener;

import androidx.viewpager.widget.ViewPager;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes8.dex */
public final class ViewPagerListenerFTU implements ViewPager.OnPageChangeListener {
    @NotNull
    public final Function1<Integer, Unit> h;

    /* JADX WARN: Multi-variable type inference failed */
    public ViewPagerListenerFTU(@NotNull Function1<? super Integer, Unit> closure) {
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
