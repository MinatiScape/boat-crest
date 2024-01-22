package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ProfileCompletionCardDashboardBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clCompleteProfile;
    @NonNull
    public final ConstraintLayout clMain;
    @NonNull
    public final ConstraintLayout clProgress;
    @NonNull
    public final ImageView ivProfile;
    @NonNull
    public final ProgressBar progressBar;
    @NonNull
    public final TextView tvCompleteProfile;
    @NonNull
    public final TextView tvContent;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvProgressValue;

    public ProfileCompletionCardDashboardBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ImageView imageView, ProgressBar progressBar, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.clCompleteProfile = constraintLayout;
        this.clMain = constraintLayout2;
        this.clProgress = constraintLayout3;
        this.ivProfile = imageView;
        this.progressBar = progressBar;
        this.tvCompleteProfile = textView;
        this.tvContent = textView2;
        this.tvHeader = textView3;
        this.tvProgressValue = textView4;
    }

    public static ProfileCompletionCardDashboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ProfileCompletionCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ProfileCompletionCardDashboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ProfileCompletionCardDashboardBinding) ViewDataBinding.bind(obj, view, R.layout.profile_completion_card_dashboard);
    }

    @NonNull
    @Deprecated
    public static ProfileCompletionCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ProfileCompletionCardDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_completion_card_dashboard, viewGroup, z, obj);
    }

    @NonNull
    public static ProfileCompletionCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ProfileCompletionCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ProfileCompletionCardDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_completion_card_dashboard, null, false, obj);
    }
}
