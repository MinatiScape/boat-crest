package com.coveiot.android.dashboard2.customui;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class DashboardFitnessVitalsItemDecorator extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public final int f4224a;

    public /* synthetic */ DashboardFitnessVitalsItemDecorator(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i4 & 2) != 0 ? 2 : i2, (i4 & 4) != 0 ? 1 : i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(outRect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        outRect.top = this.f4224a;
        if (parent.getChildAdapterPosition(view) % 2 == 0) {
            outRect.right = this.f4224a / 2;
        } else {
            outRect.left = this.f4224a / 2;
        }
    }

    public DashboardFitnessVitalsItemDecorator(int i, int i2, int i3) {
        this.f4224a = i;
    }
}
