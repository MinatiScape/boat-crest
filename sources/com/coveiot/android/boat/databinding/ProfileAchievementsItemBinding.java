package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.models.AchievementsModel;
/* loaded from: classes3.dex */
public abstract class ProfileAchievementsItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivAchievements;
    @Bindable
    public AchievementsModel mAchievementsData;
    @NonNull
    public final TextView tvAchievementsName;
    @NonNull
    public final TextView tvAchievementsValue;

    public ProfileAchievementsItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ivAchievements = imageView;
        this.tvAchievementsName = textView;
        this.tvAchievementsValue = textView2;
    }

    public static ProfileAchievementsItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ProfileAchievementsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public AchievementsModel getAchievementsData() {
        return this.mAchievementsData;
    }

    public abstract void setAchievementsData(@Nullable AchievementsModel achievementsModel);

    @Deprecated
    public static ProfileAchievementsItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ProfileAchievementsItemBinding) ViewDataBinding.bind(obj, view, R.layout.profile_achievements_item);
    }

    @NonNull
    @Deprecated
    public static ProfileAchievementsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ProfileAchievementsItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_achievements_item, viewGroup, z, obj);
    }

    @NonNull
    public static ProfileAchievementsItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ProfileAchievementsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ProfileAchievementsItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_achievements_item, null, false, obj);
    }
}
