package com.coveiot.android.sleepenergyscore;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.coveiot.android.sleepenergyscore.SleepEnergyScoreInfoFrag;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SleepEnergyScoreInfoAdapter extends FragmentPagerAdapter {
    @NotNull
    public int[] h;
    @Nullable
    public int[] i;
    @NotNull
    public Context j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SleepEnergyScoreInfoAdapter(@NotNull Context context, @NotNull FragmentManager fm, @NotNull int[] images, @Nullable int[] iArr) {
        super(fm);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(images, "images");
        this.h = images;
        this.i = iArr;
        this.j = context;
    }

    @NotNull
    public final Context getContext() {
        return this.j;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.h.length;
    }

    @Nullable
    public final int[] getDescriptionArray() {
        return this.i;
    }

    @NotNull
    public final int[] getImagesArray() {
        return this.h;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.j = context;
    }

    public final void setDescriptionArray(@Nullable int[] iArr) {
        this.i = iArr;
    }

    public final void setImagesArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.h = iArr;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    @NotNull
    public SleepEnergyScoreInfoFrag getItem(int i) {
        String str;
        SleepEnergyScoreInfoFrag.Companion companion = SleepEnergyScoreInfoFrag.Companion;
        int i2 = this.h[i];
        int[] iArr = this.i;
        if (iArr != null) {
            Context context = this.j;
            Intrinsics.checkNotNull(iArr);
            str = context.getString(iArr[i]);
        } else {
            str = null;
        }
        return companion.newInstance(i2, str);
    }
}
