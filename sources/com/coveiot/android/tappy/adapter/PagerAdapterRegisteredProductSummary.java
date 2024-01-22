package com.coveiot.android.tappy.adapter;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem;
import com.coveiot.android.tappy.model.RegStrapBeanInfo;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class PagerAdapterRegisteredProductSummary extends FragmentStatePagerAdapter {
    @NotNull
    public ArrayList<RegStrapBeanInfo> j;
    @NotNull
    public Context k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagerAdapterRegisteredProductSummary(@NotNull Context context, @NotNull FragmentManager fm, @NotNull ArrayList<RegStrapBeanInfo> registeredBeanInfoList) {
        super(fm);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(registeredBeanInfoList, "registeredBeanInfoList");
        this.j = registeredBeanInfoList;
        this.k = context;
    }

    @NotNull
    public final Context getContext() {
        return this.k;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.j.size();
    }

    @NotNull
    public final ArrayList<RegStrapBeanInfo> getRegisteredBeanInfos() {
        return this.j;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.k = context;
    }

    public final void setRegisteredBeanInfos(@NotNull ArrayList<RegStrapBeanInfo> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.j = arrayList;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    @NotNull
    public FragmentRegisteredProductItem getItem(int i) {
        FragmentRegisteredProductItem.Companion companion = FragmentRegisteredProductItem.Companion;
        RegStrapBeanInfo regStrapBeanInfo = this.j.get(i);
        Intrinsics.checkNotNull(regStrapBeanInfo);
        return companion.newInstance(regStrapBeanInfo);
    }
}
