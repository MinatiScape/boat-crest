package com.coveiot.android.activitymodes.decorator;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ActivityParamShareCardItemDecorator extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public final int f2829a;
    public final int b;
    public final int c;

    public /* synthetic */ ActivityParamShareCardItemDecorator(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i4 & 2) != 0 ? 3 : i2, (i4 & 4) != 0 ? 1 : i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(outRect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        if (this.c == 1) {
            if (parent.getChildAdapterPosition(view) < this.b) {
                outRect.top = this.f2829a;
            }
            if (parent.getChildAdapterPosition(view) % this.b == 0) {
                outRect.left = this.f2829a;
            }
        } else {
            if (parent.getChildAdapterPosition(view) < this.b) {
                outRect.left = this.f2829a;
            }
            if (parent.getChildAdapterPosition(view) % this.b == 0) {
                outRect.top = this.f2829a;
            }
        }
        int i = this.f2829a;
        outRect.right = i;
        outRect.bottom = i;
    }

    public ActivityParamShareCardItemDecorator(int i, int i2, int i3) {
        this.f2829a = i;
        this.b = i2;
        this.c = i3;
    }
}
