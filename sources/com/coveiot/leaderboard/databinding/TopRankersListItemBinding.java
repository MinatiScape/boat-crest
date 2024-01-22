package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class TopRankersListItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout badgeBaseLayout;
    @NonNull
    public final ImageView ivRankerProfile;
    @NonNull
    public final LinearLayout llList;
    @NonNull
    public final TextView rankerName;
    @NonNull
    public final TextView rankerSteps;
    @NonNull
    public final TextView tvPreviousRank;
    @NonNull
    public final TextView tvRank;
    @NonNull
    public final View view;

    public TopRankersListItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view2) {
        super(obj, view, i);
        this.badgeBaseLayout = constraintLayout;
        this.ivRankerProfile = imageView;
        this.llList = linearLayout;
        this.rankerName = textView;
        this.rankerSteps = textView2;
        this.tvPreviousRank = textView3;
        this.tvRank = textView4;
        this.view = view2;
    }

    public static TopRankersListItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static TopRankersListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TopRankersListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (TopRankersListItemBinding) ViewDataBinding.bind(obj, view, R.layout.top_rankers_list_item);
    }

    @NonNull
    @Deprecated
    public static TopRankersListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (TopRankersListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.top_rankers_list_item, viewGroup, z, obj);
    }

    @NonNull
    public static TopRankersListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static TopRankersListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (TopRankersListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.top_rankers_list_item, null, false, obj);
    }
}
