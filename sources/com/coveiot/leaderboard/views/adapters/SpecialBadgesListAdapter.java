package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.coveiot.coveaccess.leaderboard.model.AllBadgesModel;
import com.coveiot.leaderboard.databinding.LeaderHomeMyBadgesItemBinding;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class SpecialBadgesListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7247a;
    @NotNull
    public final List<AllBadgesModel.DataBean.BadgesBean> b;
    @NotNull
    public final OnItemClickListener c;

    /* loaded from: classes9.dex */
    public interface OnItemClickListener {
        void onItemClicked(@NotNull AllBadgesModel.DataBean.BadgesBean badgesBean);
    }

    /* loaded from: classes9.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final LeaderHomeMyBadgesItemBinding f7248a;
        @NotNull
        public final ImageView b;
        @NotNull
        public final ProgressBar c;
        @NotNull
        public final TextView d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SpecialBadgesListAdapter specialBadgesListAdapter, LeaderHomeMyBadgesItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f7248a = binding;
            ImageView imageView = binding.ivAchievements;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivAchievements");
            this.b = imageView;
            ProgressBar progressBar = binding.progressBar;
            Intrinsics.checkNotNullExpressionValue(progressBar, "binding.progressBar");
            this.c = progressBar;
            TextView textView = binding.tvBadgeName;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvBadgeName");
            this.d = textView;
        }

        @NotNull
        public final ImageView getIvBadge() {
            return this.b;
        }

        @NotNull
        public final ProgressBar getProgressBar() {
            return this.c;
        }

        @NotNull
        public final TextView getTvBadgeName() {
            return this.d;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SpecialBadgesListAdapter(@NotNull Context mContext, @NotNull List<? extends AllBadgesModel.DataBean.BadgesBean> specialBadgesList, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(specialBadgesList, "specialBadgesList");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f7247a = mContext;
        this.b = specialBadgesList;
        this.c = onItemClickListener;
    }

    public static final void b(SpecialBadgesListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.onItemClicked(this$0.b.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.c;
    }

    @NotNull
    public final List<AllBadgesModel.DataBean.BadgesBean> getSpecialBadgesList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.adapters.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpecialBadgesListAdapter.b(SpecialBadgesListAdapter.this, i, view);
            }
        });
        int i2 = 0;
        for (AllBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean : this.b.get(i).getBadgeLevels()) {
            try {
                i2 += kotlin.math.c.roundToInt((badgeLevelsBean.getUserCriteria() / badgeLevelsBean.getLevelCriteria()) * 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (i2 == 0) {
            holder.getProgressBar().setVisibility(8);
        } else {
            holder.getProgressBar().setVisibility(0);
        }
        holder.getTvBadgeName().setText(this.b.get(i).getBadgeName());
        Glide.with(this.f7247a).m30load(this.b.get(i).getBadgeImageUrl()).into(holder.getIvBadge());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LeaderHomeMyBadgesItemBinding inflate = LeaderHomeMyBadgesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
