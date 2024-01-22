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
public abstract class ItemBadgeBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clBadge;
    @NonNull
    public final ImageView ivBadge;
    @NonNull
    public final TextView tvBadgeDesc;
    @NonNull
    public final TextView tvBadgeName;

    public ItemBadgeBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.clBadge = constraintLayout;
        this.ivBadge = imageView;
        this.tvBadgeDesc = textView;
        this.tvBadgeName = textView2;
    }

    public static ItemBadgeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemBadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBadgeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemBadgeBinding) ViewDataBinding.bind(obj, view, R.layout.item_badge);
    }

    @NonNull
    @Deprecated
    public static ItemBadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemBadgeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_badge, viewGroup, z, obj);
    }

    @NonNull
    public static ItemBadgeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemBadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemBadgeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_badge, null, false, obj);
    }
}
