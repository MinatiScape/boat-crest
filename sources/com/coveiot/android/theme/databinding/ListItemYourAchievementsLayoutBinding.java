package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ListItemYourAchievementsLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivAchievement;
    @NonNull
    public final TextView tvAchievementName;

    public ListItemYourAchievementsLayoutBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.ivAchievement = imageView;
        this.tvAchievementName = textView;
    }

    public static ListItemYourAchievementsLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemYourAchievementsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ListItemYourAchievementsLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemYourAchievementsLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_your_achievements_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemYourAchievementsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemYourAchievementsLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_your_achievements_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemYourAchievementsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemYourAchievementsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemYourAchievementsLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_your_achievements_layout, null, false, obj);
    }
}
