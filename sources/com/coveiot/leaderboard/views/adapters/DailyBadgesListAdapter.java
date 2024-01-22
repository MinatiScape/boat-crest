package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.coveiot.coveaccess.leaderboard.model.AllBadgesModel;
import com.coveiot.leaderboard.databinding.ItemBadgeBinding;
import com.coveiot.leaderboard.views.adapters.SpecialBadgesListAdapter;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class DailyBadgesListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7229a;
    @NotNull
    public final List<AllBadgesModel.DataBean.BadgesBean> b;
    @NotNull
    public final SpecialBadgesListAdapter.OnItemClickListener c;

    /* loaded from: classes9.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ItemBadgeBinding f7230a;
        @NotNull
        public final ImageView b;
        @NotNull
        public final TextView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull DailyBadgesListAdapter dailyBadgesListAdapter, ItemBadgeBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f7230a = binding;
            ImageView imageView = binding.ivBadge;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivBadge");
            this.b = imageView;
            TextView textView = binding.tvBadgeName;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvBadgeName");
            this.c = textView;
        }

        @NotNull
        public final ImageView getIvBadge() {
            return this.b;
        }

        @NotNull
        public final TextView getTvBadgeName() {
            return this.c;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DailyBadgesListAdapter(@NotNull Context mContext, @NotNull List<? extends AllBadgesModel.DataBean.BadgesBean> dailyBadgeList, @NotNull SpecialBadgesListAdapter.OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(dailyBadgeList, "dailyBadgeList");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f7229a = mContext;
        this.b = dailyBadgeList;
        this.c = onItemClickListener;
    }

    public static final void b(DailyBadgesListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.onItemClicked(this$0.b.get(i));
    }

    @NotNull
    public final List<AllBadgesModel.DataBean.BadgesBean> getDailyBadgeList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final SpecialBadgesListAdapter.OnItemClickListener getOnItemClickListener() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.adapters.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DailyBadgesListAdapter.b(DailyBadgesListAdapter.this, i, view);
            }
        });
        holder.getTvBadgeName().setText(this.b.get(i).getBadgeName());
        Glide.with(this.f7229a).m30load(this.b.get(i).getBadgeImageUrl()).into(holder.getIvBadge());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemBadgeBinding inflate = ItemBadgeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
