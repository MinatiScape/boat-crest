package com.coveiot.android.watchfaceui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.fragments.FragmentMyDesigns;
import com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceCloudNew;
import com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceDefaultNew;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class SectionsPagerAdapterWatchFace extends FragmentPagerAdapter {
    @NotNull
    public final Context h;
    @NotNull
    public ArrayList<Integer> i;
    @Nullable
    public Fragment j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SectionsPagerAdapterWatchFace(@NotNull Context context, @NotNull FragmentManager fm, @NotNull ArrayList<Integer> titleList) {
        super(fm);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(titleList, "titleList");
        this.h = context;
        this.i = titleList;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.i.size();
    }

    @Nullable
    public final Fragment getCurrentFragment() {
        return this.j;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    @NotNull
    public Fragment getItem(int i) {
        if (this.i.size() == 1) {
            return FragmentWatchFaceCloudNew.Companion.newInstance();
        }
        if (i == 0) {
            if (this.i.get(0).equals(Integer.valueOf(R.string.my_watch_face))) {
                return FragmentMyDesigns.Companion.newInstance();
            }
            return FragmentWatchFaceCloudNew.Companion.newInstance();
        } else if (i != 1) {
            if (i != 2) {
                return FragmentWatchFaceCloudNew.Companion.newInstance();
            }
            return FragmentWatchFaceDefaultNew.Companion.newInstance();
        } else if (this.i.get(1).equals(Integer.valueOf(R.string.cloud_text))) {
            return FragmentWatchFaceCloudNew.Companion.newInstance();
        } else {
            return FragmentWatchFaceDefaultNew.Companion.newInstance();
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @Nullable
    public CharSequence getPageTitle(int i) {
        Resources resources = this.h.getResources();
        Integer num = this.i.get(i);
        Intrinsics.checkNotNullExpressionValue(num, "titleList[position]");
        return resources.getString(num.intValue());
    }

    @NotNull
    public final ArrayList<Integer> getTitleList() {
        return this.i;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(@NotNull ViewGroup container, int i, @NotNull Object object) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(object, "object");
        if (getCurrentFragment() != object) {
            this.j = (Fragment) object;
        }
        super.setPrimaryItem(container, i, object);
    }

    public final void setTitleList(@NotNull ArrayList<Integer> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.i = arrayList;
    }
}
