package com.coveiot.android.leonardo.more.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import com.coveiot.android.boat.R;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class TroubleshootPagerAdapterFirstTimeUser extends PagerAdapter {
    @NotNull
    public final Context c;
    @NotNull
    public final int[] d;
    @NotNull
    public final int[] e;
    @NotNull
    public final int[] f;
    @NotNull
    public final LayoutInflater g;

    public TroubleshootPagerAdapterFirstTimeUser(@NotNull Context context, @NotNull int[] titles, @NotNull int[] descriptions, @NotNull int[] images) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(titles, "titles");
        Intrinsics.checkNotNullParameter(descriptions, "descriptions");
        Intrinsics.checkNotNullParameter(images, "images");
        this.c = context;
        this.d = titles;
        this.e = descriptions;
        this.f = images;
        LayoutInflater from = LayoutInflater.from(context);
        Intrinsics.checkNotNullExpressionValue(from, "from(context)");
        this.g = from;
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
        View view = this.g.inflate(R.layout.troubleshootingftu_viewpager_layout, container, false);
        View findViewById = view.findViewById(R.id.ftu_img);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.ftu_img)");
        View findViewById2 = view.findViewById(R.id.troubleshoot_title_tv);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.troubleshoot_title_tv)");
        View findViewById3 = view.findViewById(R.id.troubleshoot_desc_tv);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.troubleshoot_desc_tv)");
        ((ImageView) findViewById).setImageResource(this.f[i]);
        ((TextView) findViewById2).setText(this.d[i]);
        ((TextView) findViewById3).setText(this.e[i]);
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
