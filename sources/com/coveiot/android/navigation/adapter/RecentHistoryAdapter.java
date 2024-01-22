package com.coveiot.android.navigation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.databinding.ItemLayoutRecentHistoryBinding;
import com.coveiot.android.navigation.db.model.RecentSearchHistoryData;
import com.coveiot.android.navigation.interfaces.RecentHistorySearchListener;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class RecentHistoryAdapter extends RecyclerView.Adapter<RecentHistoryViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f5501a;
    @NotNull
    public final RecentHistorySearchListener b;
    @NotNull
    public List<RecentSearchHistoryData> c;

    /* loaded from: classes5.dex */
    public final class RecentHistoryViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ItemLayoutRecentHistoryBinding f5502a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RecentHistoryViewHolder(@NotNull RecentHistoryAdapter recentHistoryAdapter, ItemLayoutRecentHistoryBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f5502a = binding;
        }

        @NotNull
        public final ItemLayoutRecentHistoryBinding getBinding() {
            return this.f5502a;
        }
    }

    public RecentHistoryAdapter(@NotNull List<RecentSearchHistoryData> recentSearchHistoryData, boolean z, @NotNull RecentHistorySearchListener listener) {
        Intrinsics.checkNotNullParameter(recentSearchHistoryData, "recentSearchHistoryData");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5501a = z;
        this.b = listener;
        this.c = recentSearchHistoryData;
    }

    public static final void b(RecentHistoryAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.b.onRecentHistorySelected(this$0.c.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.f5501a) {
            return this.c.size();
        }
        if (this.c.size() > 5) {
            return 5;
        }
        return this.c.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull RecentHistoryViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getBinding().tvRecentHistoryAddressLine1.setText(this.c.get(i).getPlaceName());
        holder.getBinding().tvRecentHistoryAddressLine2.setText(this.c.get(i).getPlaceAddress());
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.adapter.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecentHistoryAdapter.b(RecentHistoryAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public RecentHistoryViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemLayoutRecentHistoryBinding inflate = ItemLayoutRecentHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new RecentHistoryViewHolder(this, inflate);
    }
}
