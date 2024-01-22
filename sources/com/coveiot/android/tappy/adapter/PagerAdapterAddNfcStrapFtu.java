package com.coveiot.android.tappy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import com.coveiot.android.tappy.R;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class PagerAdapterAddNfcStrapFtu extends PagerAdapter {
    @NotNull
    public final Context c;
    @NotNull
    public final int[] d;
    @NotNull
    public final LayoutInflater e;

    public PagerAdapterAddNfcStrapFtu(@NotNull Context context, @NotNull int[] images) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(images, "images");
        this.c = context;
        this.d = images;
        LayoutInflater from = LayoutInflater.from(context);
        Intrinsics.checkNotNullExpressionValue(from, "from(context)");
        this.e = from;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NotNull ViewGroup container, int i, @NotNull Object obj) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(obj, "obj");
        container.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.d.length;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NotNull
    public Object instantiateItem(@NotNull ViewGroup container, int i) {
        Intrinsics.checkNotNullParameter(container, "container");
        View view = this.e.inflate(R.layout.tappayftu_viewpager_layout, container, false);
        View findViewById = view.findViewById(R.id.ivFtu);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.ivFtu)");
        ((ImageView) findViewById).setImageResource(this.d[i]);
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        container.addView(view);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NotNull View view, @NotNull Object obj) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(obj, "obj");
        return Intrinsics.areEqual(view, obj);
    }
}
