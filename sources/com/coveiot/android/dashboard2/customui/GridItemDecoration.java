package com.coveiot.android.dashboard2.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class GridItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public final int f4227a;
    @Nullable
    public final Drawable b;

    public GridItemDecoration(@NotNull Context context, int i, int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4227a = i2;
        this.b = ContextCompat.getDrawable(context, i);
    }

    public final void a(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
            int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
            int left = childAt.getLeft() - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) layoutParams)).leftMargin;
            Drawable drawable = this.b;
            Intrinsics.checkNotNull(drawable);
            int intrinsicWidth = drawable.getIntrinsicWidth() + left;
            int i2 = this.f4227a;
            if (childAdapterPosition % i2 != i2 - 1) {
                this.b.setBounds(left, childAt.getTop(), intrinsicWidth, childAt.getBottom());
                this.b.draw(canvas);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(outRect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        int i = childAdapterPosition % this.f4227a;
        Drawable drawable = this.b;
        Intrinsics.checkNotNull(drawable);
        outRect.left = (drawable.getIntrinsicWidth() * i) / this.f4227a;
        int intrinsicWidth = this.b.getIntrinsicWidth();
        int intrinsicWidth2 = (i + 1) * this.b.getIntrinsicWidth();
        int i2 = this.f4227a;
        outRect.right = intrinsicWidth - (intrinsicWidth2 / i2);
        if (childAdapterPosition >= i2) {
            outRect.top = this.b.getIntrinsicHeight();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(@NotNull Canvas canvas, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        a(canvas, parent);
    }
}
