package com.coveiot.android.sleepenergyscore.feedback;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class SleepEnergyScoreFeedbackAdap extends FragmentPagerAdapter {
    @NotNull
    public List<Fragment> h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SleepEnergyScoreFeedbackAdap(@NotNull FragmentManager fm) {
        super(fm);
        Intrinsics.checkNotNullParameter(fm, "fm");
        this.h = new ArrayList();
    }

    public final boolean addFragment(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        return this.h.add(fragment);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.h.size();
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    @NotNull
    public Fragment getItem(int i) {
        return this.h.get(i);
    }
}
