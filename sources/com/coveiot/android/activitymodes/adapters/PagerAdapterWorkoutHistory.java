package com.coveiot.android.activitymodes.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class PagerAdapterWorkoutHistory extends FragmentStatePagerAdapter {
    public List<? extends Fragment> fragments;
    public String[] titles;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagerAdapterWorkoutHistory(@NotNull FragmentManager fm) {
        super(fm);
        Intrinsics.checkNotNullParameter(fm, "fm");
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return getFragments().size();
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
