package com.coveiot.android.leonardo.boatcoin.ftu.adapter;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.coveiot.android.leonardo.boatcoin.ftu.fragments.FragmentBoatCoinsFTU;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ViewPagerFTUAdapter extends FragmentPagerAdapter {
    @NotNull
    public int[] h;
    @NotNull
    public int[] i;
    @NotNull
    public int[] j;
    @NotNull
    public Context k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewPagerFTUAdapter(@NotNull Context context, @NotNull FragmentManager fm, @NotNull int[] images, @NotNull int[] description, @NotNull int[] description2) {
        super(fm);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(images, "images");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(description2, "description2");
        this.h = images;
        this.i = description;
        this.j = description2;
        this.k = context;
    }

    @NotNull
    public final Context getContext() {
        return this.k;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.h.length;
    }

    @NotNull
    public final int[] getImagesArray() {
        return this.h;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.k = context;
    }

    public final void setImagesArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.h = iArr;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    @NotNull
    public FragmentBoatCoinsFTU getItem(int i) {
        FragmentBoatCoinsFTU.Companion companion = FragmentBoatCoinsFTU.Companion;
        int i2 = this.h[i];
        String string = this.k.getString(this.i[i]);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(descriptionArray[position])");
        String string2 = this.k.getString(this.j[i]);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(descriptionArray2[position])");
        return companion.newInstance(i2, string, string2);
    }
}
