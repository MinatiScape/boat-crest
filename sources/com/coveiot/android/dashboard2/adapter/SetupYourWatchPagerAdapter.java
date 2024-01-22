package com.coveiot.android.dashboard2.adapter;

import android.content.Context;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.coveiot.android.dashboard2.fragment.SetupYourWatchItemFragment;
import com.coveiot.android.dashboard2.model.SetupYourWatchDataModel;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class SetupYourWatchPagerAdapter extends FragmentPagerAdapter {
    @NotNull
    public List<SetupYourWatchDataModel> h;
    @NotNull
    public SetupYourWatchItemFragment.SetupYourWatchItemSelectedListener i;
    @NotNull
    public Context j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetupYourWatchPagerAdapter(@NotNull Context context, @NotNull FragmentManager fm, @NotNull List<SetupYourWatchDataModel> setupYourWatchDataModels, @NotNull SetupYourWatchItemFragment.SetupYourWatchItemSelectedListener setupYourWatchItemSelectedListener) {
        super(fm);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(setupYourWatchDataModels, "setupYourWatchDataModels");
        Intrinsics.checkNotNullParameter(setupYourWatchItemSelectedListener, "setupYourWatchItemSelectedListener");
        this.h = setupYourWatchDataModels;
        this.i = setupYourWatchItemSelectedListener;
        this.j = context;
    }

    @NotNull
    public final Context getContext() {
        return this.j;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.h.size();
    }

    @NotNull
    public final List<SetupYourWatchDataModel> getSetupYourWatchDataModels() {
        return this.h;
    }

    @NotNull
    public final SetupYourWatchItemFragment.SetupYourWatchItemSelectedListener getSetupYourWatchItemSelectedListener() {
        return this.i;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter, androidx.viewpager.widget.PagerAdapter
    @NotNull
    public Object instantiateItem(@NotNull ViewGroup container, int i) {
        Intrinsics.checkNotNullParameter(container, "container");
        Object instantiateItem = super.instantiateItem(container, i);
        Intrinsics.checkNotNullExpressionValue(instantiateItem, "super.instantiateItem(container, position)");
        return instantiateItem;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.j = context;
    }

    public final void setSetupYourWatchDataModels(@NotNull List<SetupYourWatchDataModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.h = list;
    }

    public final void setSetupYourWatchItemSelectedListener(@NotNull SetupYourWatchItemFragment.SetupYourWatchItemSelectedListener setupYourWatchItemSelectedListener) {
        Intrinsics.checkNotNullParameter(setupYourWatchItemSelectedListener, "<set-?>");
        this.i = setupYourWatchItemSelectedListener;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    @NotNull
    public SetupYourWatchItemFragment getItem(int i) {
        return SetupYourWatchItemFragment.Companion.newInstance(this.h.get(i), this.i);
    }
}
