package com.coveiot.android.activitymodes;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes2.dex */
public class EqualSpacingItemDecoration extends RecyclerView.ItemDecoration {
    public static final int GRID = 2;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    /* renamed from: a  reason: collision with root package name */
    public final int f2702a;
    public int b;

    public EqualSpacingItemDecoration(int i) {
        this(i, -1);
    }

    public final int a(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            return 2;
        }
        return layoutManager.canScrollHorizontally() ? 0 : 1;
    }

    public final void b(Rect rect, RecyclerView.LayoutManager layoutManager, int i, int i2) {
        if (this.b == -1) {
            this.b = a(layoutManager);
        }
        int i3 = this.b;
        if (i3 == 0) {
            int i4 = this.f2702a;
            rect.left = i4;
            rect.right = i == i2 - 1 ? i4 : 0;
            rect.top = i4;
            rect.bottom = i4;
        } else if (i3 != 1) {
            if (i3 == 2 && (layoutManager instanceof GridLayoutManager)) {
                int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
                int i5 = i2 / spanCount;
                int i6 = this.f2702a;
                rect.left = i6;
                rect.right = i % spanCount == spanCount + (-1) ? i6 : 0;
                rect.top = i6;
                rect.bottom = i / spanCount == i5 - 1 ? i6 : 0;
            }
        } else {
            int i7 = this.f2702a;
            rect.left = i7;
            rect.right = i7;
            rect.top = i7;
            rect.bottom = i == i2 - 1 ? i7 : 0;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        b(rect, recyclerView.getLayoutManager(), recyclerView.getChildViewHolder(view).getAdapterPosition(), state.getItemCount());
    }

    public EqualSpacingItemDecoration(int i, int i2) {
        this.f2702a = i;
        this.b = i2;
    }
}
