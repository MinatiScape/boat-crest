package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.databinding.RankFilterItemBinding;
import com.coveiot.leaderboard.views.model.RankFilterModel;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class RanksFilterAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7243a;
    @NotNull
    public final List<RankFilterModel> b;
    public final boolean c;
    @NotNull
    public final OnItemClickListener d;
    @Nullable
    public RankFilterModel e;

    /* loaded from: classes9.dex */
    public interface OnItemClickListener {
        void onItemClicked(@NotNull RankFilterModel rankFilterModel, boolean z);
    }

    /* loaded from: classes9.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final RankFilterItemBinding f7244a;
        @NotNull
        public final TextView b;
        public final /* synthetic */ RanksFilterAdapter c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull RanksFilterAdapter ranksFilterAdapter, RankFilterItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.c = ranksFilterAdapter;
            this.f7244a = binding;
            TextView textView = binding.tvFilter;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvFilter");
            this.b = textView;
        }

        @NotNull
        public final TextView getTvFilter() {
            return this.b;
        }

        public final void selected(boolean z) {
            if (z) {
                this.b.setBackgroundResource(R.drawable.filter_bg);
                this.b.setTextAppearance(R.style.semi_bold);
                this.b.setTextColor(this.c.f7243a.getResources().getColor(R.color.main_text_color));
                return;
            }
            this.b.setBackgroundResource(R.drawable.rounded_corners_grey_button);
            this.b.setTextAppearance(R.style.regular);
            this.b.setTextColor(this.c.f7243a.getResources().getColor(R.color.secondary_text_color));
        }
    }

    public RanksFilterAdapter(@NotNull Context mContext, @NotNull List<RankFilterModel> rankFilterList, boolean z, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(rankFilterList, "rankFilterList");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f7243a = mContext;
        this.b = rankFilterList;
        this.c = z;
        this.d = onItemClickListener;
    }

    public static final void b(RanksFilterAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.b.get(i).setSelected(!this$0.b.get(i).isSelected());
        this$0.e = this$0.b.get(i);
        this$0.d.onItemClicked(this$0.b.get(i), this$0.c);
        this$0.notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.d;
    }

    @NotNull
    public final List<RankFilterModel> getRankFilterList() {
        return this.b;
    }

    @Nullable
    public final RankFilterModel getSelectedFilter() {
        return this.e;
    }

    public final boolean isRankType() {
        return this.c;
    }

    public final void setSelectedFilter(@Nullable RankFilterModel rankFilterModel) {
        this.e = rankFilterModel;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTvFilter().setText(this.b.get(i).getFilterName());
        holder.getTvFilter().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.adapters.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RanksFilterAdapter.b(RanksFilterAdapter.this, i, view);
            }
        });
        holder.selected(Intrinsics.areEqual(this.e, this.b.get(i)));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        RankFilterItemBinding inflate = RankFilterItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
