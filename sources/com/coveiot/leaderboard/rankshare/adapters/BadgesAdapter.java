package com.coveiot.leaderboard.rankshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.base.BaseAdapter;
import com.coveiot.leaderboard.base.BaseViewHolder;
import com.coveiot.leaderboard.databinding.ShareCardItemBinding;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class BadgesAdapter extends BaseAdapter<MyBadgesModel.DataBean.BadgesBean, BadgesHolder> {

    /* loaded from: classes9.dex */
    public static final class BadgesHolder extends BaseViewHolder<MyBadgesModel.DataBean.BadgesBean> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ShareCardItemBinding f7209a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BadgesHolder(@NotNull ShareCardItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f7209a = binding;
        }

        @NotNull
        public final ShareCardItemBinding getBinding() {
            return this.f7209a;
        }

        @Override // com.coveiot.leaderboard.base.BaseViewHolder
        public void onBindView(@Nullable Context context, @Nullable MyBadgesModel.DataBean.BadgesBean badgesBean, int i) {
            if (context != null) {
                RequestManager with = Glide.with(context);
                Intrinsics.checkNotNull(badgesBean);
                with.m30load(badgesBean.getBadgeLevels().get(0).getLevelImageUrl()).placeholder(R.drawable.ic_rank_loader_badge).into(this.f7209a.badgeIV);
            }
            TextView textView = this.f7209a.badgeName;
            Intrinsics.checkNotNull(badgesBean);
            textView.setText(badgesBean.getBadgeName());
        }
    }

    public BadgesAdapter(@Nullable Context context) {
        super(context);
    }

    @Override // com.coveiot.leaderboard.base.BaseAdapter
    @NotNull
    public BadgesHolder getViewHolder(@NotNull LayoutInflater inflater, @NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Intrinsics.checkNotNullParameter(parent, "parent");
        ShareCardItemBinding inflate = ShareCardItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f…nt.context),parent,false)");
        return new BadgesHolder(inflate);
    }
}
