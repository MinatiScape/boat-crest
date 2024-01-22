package com.coveiot.android.remotecommandframework.alexa.adpter;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.coveiot.android.remotecommandframework.alexa.fragment.FragmentAlexaFirstTimeUser;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class PagerAdapterAlexaFirstTimeUser extends FragmentPagerAdapter {
    @NotNull
    public int[] h;
    @NotNull
    public int[] i;
    @NotNull
    public int[] j;
    @NotNull
    public Context k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagerAdapterAlexaFirstTimeUser(@NotNull Context context, @NotNull FragmentManager fm, @NotNull int[] images, @NotNull int[] contents, @NotNull int[] description) {
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

    @NotNull
    public final int[] getImagesArray() {
        return this.h;
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

    @Override // androidx.fragment.app.FragmentPagerAdapter
    @NotNull
    public FragmentAlexaFirstTimeUser getItem(int i) {
        FragmentAlexaFirstTimeUser.Companion companion = FragmentAlexaFirstTimeUser.Companion;
        int i2 = this.h[i];
        String string = this.k.getString(this.i[i]);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(contentsArray[position])");
        String string2 = this.k.getString(this.j[i]);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(descriptionArray[position])");
        return companion.newInstance(i2, string, string2);
    }
}
