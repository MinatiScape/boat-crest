package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.coveiot.coveaccess.leaderboard.model.TopRankModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.databinding.TopRankersListItemBinding;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class TopRankersListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7252a;
    @NotNull
    public final List<TopRankModel.DataBean.TopRanksBean> b;

    /* loaded from: classes9.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TopRankersListItemBinding f7253a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final ImageView d;
        @NotNull
        public final TextView e;
        @NotNull
        public final TextView f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull TopRankersListAdapter topRankersListAdapter, TopRankersListItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f7253a = binding;
            TextView textView = binding.tvRank;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvRank");
            this.b = textView;
            TextView textView2 = binding.tvPreviousRank;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvPreviousRank");
            this.c = textView2;
            ImageView imageView = binding.ivRankerProfile;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivRankerProfile");
            this.d = imageView;
            TextView textView3 = binding.rankerName;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.rankerName");
            this.e = textView3;
            TextView textView4 = binding.rankerSteps;
            Intrinsics.checkNotNullExpressionValue(textView4, "binding.rankerSteps");
            this.f = textView4;
        }

        @NotNull
        public final TextView getPreviousRank() {
            return this.c;
        }

        @NotNull
        public final ImageView getProfileImage() {
            return this.d;
        }

        @NotNull
        public final TextView getRank() {
            return this.b;
        }

        @NotNull
        public final TextView getRankerName() {
            return this.e;
        }

        @NotNull
        public final TextView getRankerSteps() {
            return this.f;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TopRankersListAdapter(@NotNull Context mContext, @NotNull List<? extends TopRankModel.DataBean.TopRanksBean> ranksList) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(ranksList, "ranksList");
        this.f7252a = mContext;
        this.b = ranksList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final List<TopRankModel.DataBean.TopRanksBean> getRanksList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getRank().setBackground(this.f7252a.getDrawable(R.drawable.rounded_corners));
        holder.getRank().setPadding(24, 8, 24, 8);
        if (this.b.get(i).getRank() != null) {
            holder.getRank().setText(String.valueOf(this.b.get(i).getRank()));
        } else {
            holder.getRank().setText("--");
        }
        holder.getRankerName().setText(this.b.get(i).getUserName().toString());
        holder.getRankerSteps().setText(String.valueOf(this.b.get(i).getSteps()));
        if (this.b.get(i).getDpUrl() != null) {
            Glide.with(this.f7252a).m30load(this.b.get(i).getDpUrl()).apply((BaseRequestOptions<?>) new RequestOptions().circleCrop().placeholder(R.drawable.default_avatar)).into(holder.getProfileImage());
        } else {
            holder.getProfileImage().setImageDrawable(this.f7252a.getDrawable(R.drawable.default_avatar));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        TopRankersListItemBinding inflate = TopRankersListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
