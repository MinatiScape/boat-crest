package com.coveiot.android.activitymodes.activity1k.adapter;

import android.content.Context;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class DragManageAdapter extends ItemTouchHelper.SimpleCallback {
    @NotNull
    public ActivityReArrangeAdapter f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DragManageAdapter(@NotNull ActivityReArrangeAdapter adapter, @NotNull Context context, int i, int i2) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(context, "context");
        this.f = adapter;
    }

    @NotNull
    public final ActivityReArrangeAdapter getActivitiesAdapter() {
        return this.f;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback, androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, @NotNull RecyclerView.ViewHolder target) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(target, "target");
        this.f.swapItems(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(@NotNull RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
    }

    public final void setActivitiesAdapter(@NotNull ActivityReArrangeAdapter activityReArrangeAdapter) {
        Intrinsics.checkNotNullParameter(activityReArrangeAdapter, "<set-?>");
        this.f = activityReArrangeAdapter;
    }
}
