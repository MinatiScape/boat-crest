package com.coveiot.leaderboard.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class PagerAdapterAchievementsHome extends FragmentStatePagerAdapter {
    @NotNull
    public ArrayList<Fragment> j;
    @NotNull
    public ArrayList<String> k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagerAdapterAchievementsHome(@NotNull FragmentManager fm) {
        super(fm);
        Intrinsics.checkNotNullParameter(fm, "fm");
        this.j = new ArrayList<>();
        this.k = new ArrayList<>();
    }

    public final void addFragment(@NotNull Fragment fragment, @NotNull String title) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(title, "title");
        this.j.add(fragment);
        this.k.add(title);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.j.size();
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    @NotNull
    public Fragment getItem(int i) {
        Fragment fragment = this.j.get(i);
        Intrinsics.checkNotNullExpressionValue(fragment, "fragments.get(position)");
        return fragment;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @Nullable
    public CharSequence getPageTitle(int i) {
        return this.k.get(i);
    }
}
