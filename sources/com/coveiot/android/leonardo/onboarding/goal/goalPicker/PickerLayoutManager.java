package com.coveiot.android.leonardo.onboarding.goal.goalPicker;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class PickerLayoutManager extends LinearLayoutManager {
    @Nullable
    public OnItemSelectedListener P;
    public RecyclerView Q;

    /* loaded from: classes5.dex */
    public interface OnItemSelectedListener {
        void onItemSelected(int i);
    }

    public PickerLayoutManager(@Nullable Context context) {
        super(context);
        setOrientation(0);
    }

    @Nullable
    public final OnItemSelectedListener getCallback() {
        return this.P;
    }

    public final int k0() {
        RecyclerView recyclerView = this.Q;
        RecyclerView recyclerView2 = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        int right = recyclerView.getRight();
        RecyclerView recyclerView3 = this.Q;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView3 = null;
        }
        int left = (right - recyclerView3.getLeft()) / 2;
        RecyclerView recyclerView4 = this.Q;
        if (recyclerView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        } else {
            recyclerView2 = recyclerView4;
        }
        return left + recyclerView2.getLeft();
    }

    public final void l0() {
        float width = getWidth() / 2.0f;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            Intrinsics.checkNotNull(childAt);
            float sqrt = 1 - (((float) Math.sqrt(Math.abs(width - ((getDecoratedLeft(childAt) + getDecoratedRight(childAt)) / 2.0f)) / getWidth())) * 0.66f);
            childAt.setScaleX(sqrt);
            childAt.setScaleY(sqrt);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(@Nullable RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        Intrinsics.checkNotNull(recyclerView);
        this.Q = recyclerView;
        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        RecyclerView recyclerView2 = this.Q;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView2 = null;
        }
        linearSnapHelper.attachToRecyclerView(recyclerView2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(@Nullable RecyclerView.Recycler recycler, @NotNull RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onLayoutChildren(recycler, state);
        l0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int i) {
        super.onScrollStateChanged(i);
        if (i == 0) {
            int k0 = k0();
            RecyclerView recyclerView = this.Q;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                recyclerView = null;
            }
            int width = recyclerView.getWidth();
            int i2 = -1;
            RecyclerView recyclerView2 = this.Q;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                recyclerView2 = null;
            }
            int childCount = recyclerView2.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                RecyclerView recyclerView3 = this.Q;
                if (recyclerView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                    recyclerView3 = null;
                }
                View childAt = recyclerView3.getChildAt(i3);
                int abs = Math.abs((getDecoratedLeft(childAt) + ((getDecoratedRight(childAt) - getDecoratedLeft(childAt)) / 2)) - k0);
                if (abs < width) {
                    RecyclerView recyclerView4 = this.Q;
                    if (recyclerView4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                        recyclerView4 = null;
                    }
                    i2 = recyclerView4.getChildLayoutPosition(childAt);
                    width = abs;
                }
            }
            OnItemSelectedListener onItemSelectedListener = this.P;
            if (onItemSelectedListener != null) {
                onItemSelectedListener.onItemSelected(i2);
            }
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, @Nullable RecyclerView.Recycler recycler, @Nullable RecyclerView.State state) {
        if (getOrientation() == 0) {
            int scrollHorizontallyBy = super.scrollHorizontallyBy(i, recycler, state);
            l0();
            return scrollHorizontallyBy;
        }
        return 0;
    }

    public final void setCallback(@Nullable OnItemSelectedListener onItemSelectedListener) {
        this.P = onItemSelectedListener;
    }
}
