package com.coveiot.android.dashboard2.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes4.dex */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public final Drawable f4225a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;

    public DividerItemDecoration(Context context, int i, int i2, int i3, int i4, int i5) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i5;
        this.f4225a = ContextCompat.getDrawable(context, i4);
    }

    public final void a(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        int b = b(recyclerView);
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
            if (childAdapterPosition % b < b - 1 && childAdapterPosition < childCount - 1) {
                int right = childAt.getRight() + this.d;
                int top = childAt.getTop() + this.c;
                int bottom = childAt.getBottom() - this.c;
                this.f4225a.setBounds(right, top, this.b + right, bottom);
                this.f4225a.draw(canvas);
            }
        }
    }

    public final int b(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int b = b(recyclerView);
        int i = (childAdapterPosition + 1) % b;
        if (i != 0) {
            rect.right = this.d;
        }
        if (i != 0) {
            rect.left = this.d;
        }
        if (childAdapterPosition < recyclerView.getAdapter().getItemCount() - b) {
            rect.bottom = this.c - this.e;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        a(canvas, recyclerView);
    }
}
