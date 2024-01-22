package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class FragmentBadgeCardShareBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout badgeShareConstraintLayout;
    @NonNull
    public final ImageView ivBadgeShareAppLogo;
    @NonNull
    public final ImageView ivBadgeSharePoweredCove;
    @NonNull
    public final ImageView ivBsUserPic;
    @NonNull
    public final ImageView ivRsBadgeIcon;
    @NonNull
    public final TextView tvBadgeEarnedOn;
    @NonNull
    public final TextView tvBsUserName;
    @NonNull
    public final TextView tvRsBadgeName;
    @NonNull
    public final TextView tvRsBagdeDesc;
    @NonNull
    public final TextView tvRsEarnedOn;
    @NonNull
    public final TextView tvRsWhenEarned;

    public FragmentBadgeCardShareBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        super(obj, view, i);
        this.badgeShareConstraintLayout = constraintLayout;
        this.ivBadgeShareAppLogo = imageView;
        this.ivBadgeSharePoweredCove = imageView2;
        this.ivBsUserPic = imageView3;
        this.ivRsBadgeIcon = imageView4;
        this.tvBadgeEarnedOn = textView;
        this.tvBsUserName = textView2;
        this.tvRsBadgeName = textView3;
        this.tvRsBagdeDesc = textView4;
        this.tvRsEarnedOn = textView5;
        this.tvRsWhenEarned = textView6;
    }

    public static FragmentBadgeCardShareBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentBadgeCardShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBadgeCardShareBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentBadgeCardShareBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_badge_card_share);
    }

    @NonNull
    @Deprecated
    public static FragmentBadgeCardShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentBadgeCardShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_badge_card_share, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentBadgeCardShareBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentBadgeCardShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentBadgeCardShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_badge_card_share, null, false, obj);
    }
}
