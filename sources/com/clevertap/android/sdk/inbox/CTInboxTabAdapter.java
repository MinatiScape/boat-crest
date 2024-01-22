package com.clevertap.android.sdk.inbox;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CTInboxTabAdapter extends FragmentPagerAdapter {
    public final Fragment[] h;
    public final List<String> i;

    public CTInboxTabAdapter(FragmentManager fragmentManager, int i) {
        super(fragmentManager);
        this.i = new ArrayList();
        this.h = new Fragment[i];
    }

    public void c(Fragment fragment, String str, int i) {
        this.h[i] = fragment;
        this.i.add(str);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.h.length;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    @NonNull
    public Fragment getItem(int i) {
        return this.h[i];
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @Nullable
    public CharSequence getPageTitle(int i) {
        return this.i.get(i);
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter, androidx.viewpager.widget.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        Object instantiateItem = super.instantiateItem(viewGroup, i);
        this.h[i] = (Fragment) instantiateItem;
        return instantiateItem;
    }
}
