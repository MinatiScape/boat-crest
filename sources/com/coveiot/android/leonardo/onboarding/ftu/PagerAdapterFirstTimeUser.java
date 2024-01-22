package com.coveiot.android.leonardo.onboarding.ftu;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.coveiot.android.leonardo.onboarding.ftu.FragmentFirstTimeUser;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class PagerAdapterFirstTimeUser extends FragmentPagerAdapter {
    @NotNull
    public int[] h;
    @NotNull
    public int[] i;
    @NotNull
    public int[] j;
    @NotNull
    public Context k;
    @NotNull
    public final ArrayList<Fragment> l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagerAdapterFirstTimeUser(@NotNull Context context, @NotNull FragmentManager fm, @NotNull int[] images, @NotNull int[] contents, @NotNull int[] description) {
        super(fm);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(images, "images");
        Intrinsics.checkNotNullParameter(contents, "contents");
        Intrinsics.checkNotNullParameter(description, "description");
        this.h = images;
        this.i = contents;
        this.j = description;
        this.k = context;
        this.l = new ArrayList<>();
    }

    @NotNull
    public final int[] getContentsArray() {
        return this.i;
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
    public final int[] getDescriptionArray() {
        return this.j;
    }

    @Nullable
    public final Fragment getFragmentBy(int i) {
        if (i < this.l.size()) {
            return this.l.get(i);
        }
        return null;
    }

    @NotNull
    public final ArrayList<Fragment> getFragmentList() {
        return this.l;
    }

    @NotNull
    public final int[] getImagesArray() {
        return this.h;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    @NotNull
    public Fragment getItem(int i) {
        FragmentFirstTimeUser.Companion companion = FragmentFirstTimeUser.Companion;
        int i2 = this.h[i];
        String string = this.k.getString(this.i[i]);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(contentsArray[position])");
        String string2 = this.k.getString(this.j[i]);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(descriptionArray[position])");
        FragmentFirstTimeUser newInstance = companion.newInstance(i2, string, string2, i);
        this.l.add(newInstance);
        return newInstance;
    }

    public final void setContentsArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.i = iArr;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.k = context;
    }

    public final void setDescriptionArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.j = iArr;
    }

    public final void setImagesArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.h = iArr;
    }
}
