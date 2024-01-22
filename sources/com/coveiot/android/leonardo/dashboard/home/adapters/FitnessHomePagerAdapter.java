package com.coveiot.android.leonardo.dashboard.home.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FitnessHomePagerAdapter extends FragmentStatePagerAdapter {
    public List<? extends Fragment> fragments;
    public String[] titles;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessHomePagerAdapter(@NotNull FragmentManager fm) {
        super(fm);
        Intrinsics.checkNotNullParameter(fm, "fm");
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return getFragments().size();
    }

    @Nullable
    public final Fragment getFragmentATPosition(int i) {
        if (i < getFragments().size()) {
            return getFragments().get(i);
        }
        return null;
    }

    @NotNull
    public final List<Fragment> getFragments() {
        List list = this.fragments;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fragments");
        return null;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    @NotNull
    public Fragment getItem(int i) {
        return getFragments().get(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @Nullable
    public CharSequence getPageTitle(int i) {
        if (i > getTitles().length - 1) {
            i = getTitles().length - 1;
        }
        return getTitles()[i];
    }

    @NotNull
    public final String[] getTitles() {
        String[] strArr = this.titles;
        if (strArr != null) {
            return strArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("titles");
        return null;
    }

    public final void setFragments(@NotNull List<? extends Fragment> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.fragments = list;
    }

    public final void setTitles(@NotNull String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.titles = strArr;
    }
}
