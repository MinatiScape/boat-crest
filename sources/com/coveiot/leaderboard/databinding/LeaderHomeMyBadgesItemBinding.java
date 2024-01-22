package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class LeaderHomeMyBadgesItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout baseLayout;
    @NonNull
    public final CardView cvBadges;
    @NonNull
    public final ImageView ivAchievements;
    @NonNull
    public final ProgressBar progressBar;
    @NonNull
    public final TextView tvBadgeName;

    public LeaderHomeMyBadgesItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, CardView cardView, ImageView imageView, ProgressBar progressBar, TextView textView) {
        super(obj, view, i);
        this.baseLayout = constraintLayout;
        this.cvBadges = cardView;
        this.ivAchievements = imageView;
        this.progressBar = progressBar;
        this.tvBadgeName = textView;
    }

    public static LeaderHomeMyBadgesItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LeaderHomeMyBadgesItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LeaderHomeMyBadgesItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LeaderHomeMyBadgesItemBinding) ViewDataBinding.bind(obj, view, R.layout.leader_home_my_badges_item);
    }

    @NonNull
    @Deprecated
    public static LeaderHomeMyBadgesItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LeaderHomeMyBadgesItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.leader_home_my_badges_item, viewGroup, z, obj);
    }

    @NonNull
    public static LeaderHomeMyBadgesItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LeaderHomeMyBadgesItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LeaderHomeMyBadgesItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.leader_home_my_badges_item, null, false, obj);
    }
}
