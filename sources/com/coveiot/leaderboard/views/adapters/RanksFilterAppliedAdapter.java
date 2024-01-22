package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.leaderboard.databinding.FiltedAppliedItemBinding;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class RanksFilterAppliedAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<String> f7245a;
    @NotNull
    public final OnItemClickListener b;

    /* loaded from: classes9.dex */
    public interface OnItemClickListener {
        void onItemClicked(@NotNull String str);
    }

    /* loaded from: classes9.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final FiltedAppliedItemBinding f7246a;
        @NotNull
        public final TextView b;
        @NotNull
        public final ImageView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull RanksFilterAppliedAdapter ranksFilterAppliedAdapter, FiltedAppliedItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f7246a = binding;
            TextView textView = binding.tvRankTypeValue;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvRankTypeValue");
            this.b = textView;
            ImageView imageView = binding.ivClose;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivClose");
            this.c = imageView;
        }

        @NotNull
        public final ImageView getIvClose() {
            return this.c;
        }

        @NotNull
        public final TextView getTvFilter() {
            return this.b;
        }
    }

    public RanksFilterAppliedAdapter(@NotNull Context mContext, @NotNull List<String> rankFilterList, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(rankFilterList, "rankFilterList");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f7245a = rankFilterList;
        this.b = onItemClickListener;
    }

    public static final void b(RanksFilterAppliedAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.b.onItemClicked(this$0.f7245a.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f7245a.size();
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.b;
    }

    @NotNull
    public final List<String> getRankFilterList() {
        return this.f7245a;
    }

    public final void setData(@NotNull List<String> rankFilterList) {
        Intrinsics.checkNotNullParameter(rankFilterList, "rankFilterList");
        this.f7245a = rankFilterList;
        notifyDataSetChanged();
    }

    public final void setRankFilterList(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f7245a = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTvFilter().setText(this.f7245a.get(i));
        holder.getIvClose().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.adapters.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RanksFilterAppliedAdapter.b(RanksFilterAppliedAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        FiltedAppliedItemBinding inflate = FiltedAppliedItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
