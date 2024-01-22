package com.coveiot.android.activitymodes.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class PlanFtuPagerAdapter extends FragmentPagerAdapter {
    @NotNull
    public final List<Fragment> h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PlanFtuPagerAdapter(@NotNull FragmentManager fm, @NotNull List<? extends Fragment> fragmentsList) {
        super(fm);
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(fragmentsList, "fragmentsList");
        this.h = fragmentsList;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.h.size();
    }

    @NotNull
    public final List<Fragment> getFragmentsList() {
        return this.h;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    @NotNull
    public Fragment getItem(int i) {
        return this.h.get(i);
    }
}
