package com.coveiot.android.tappy.adapter;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.coveiot.android.tappy.fragment.FragmentAddNfcStrap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class PagerAdapterAddNfcStrap extends FragmentPagerAdapter {
    @NotNull
    public Map<Integer, int[]> h;
    @NotNull
    public Context i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagerAdapterAddNfcStrap(@NotNull Context context, @NotNull FragmentManager fm, @NotNull Map<Integer, int[]> imagesMap) {
        super(fm);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(imagesMap, "imagesMap");
        this.h = imagesMap;
        this.i = context;
    }

    @NotNull
    public final Context getContext() {
        return this.i;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.h.size();
    }

    @NotNull
    public final Map<Integer, int[]> getMapImages() {
        return this.h;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.i = context;
    }

    public final void setMapImages(@NotNull Map<Integer, int[]> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.h = map;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    @NotNull
    public FragmentAddNfcStrap getItem(int i) {
        FragmentAddNfcStrap.Companion companion = FragmentAddNfcStrap.Companion;
        int[] iArr = this.h.get(Integer.valueOf(i));
        Intrinsics.checkNotNull(iArr);
        return companion.newInstance(iArr, i);
    }
}
