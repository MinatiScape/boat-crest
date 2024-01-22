package com.coveiot.android.leonardo.dashboard.home.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.coveiot.android.leonardo.dashboard.home.fragments.PagerViewFragment;
/* loaded from: classes3.dex */
public class RemotePagerAdapter extends FragmentStatePagerAdapter {
    public Context j;
    public int[] k;
    public int[] l;

    public RemotePagerAdapter(FragmentManager fragmentManager, Context context, int[] iArr, int[] iArr2) {
        super(fragmentManager);
        this.j = context;
        this.k = iArr;
        this.l = iArr2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        int[] iArr = this.k;
        if (iArr == null) {
            return 0;
        }
        return iArr.length;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        if (this.l != null) {
            return PagerViewFragment.newInstance(this.j.getString(this.k[i]), this.l[i], this.j);
        }
        return PagerViewFragment.newInstance(this.j.getString(this.k[i]));
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.j.getString(this.k[i]);
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        return super.instantiateItem(viewGroup, i);
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return obj != null && ((Fragment) obj).getView() == view;
    }

    public RemotePagerAdapter(FragmentManager fragmentManager, Context context, int[] iArr) {
        super(fragmentManager);
        this.j = context;
        this.k = iArr;
    }
}
