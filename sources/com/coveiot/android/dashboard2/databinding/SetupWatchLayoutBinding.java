package com.coveiot.android.dashboard2.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.model.SetupYourWatchDataModel;
/* loaded from: classes4.dex */
public abstract class SetupWatchLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clBgContainer;
    @NonNull
    public final ConstraintLayout clMain;
    @NonNull
    public final CardView cvBackground;
    @NonNull
    public final ImageView ivWatchBackground;
    @Bindable
    public SetupYourWatchDataModel mData;
    @NonNull
    public final TextView tvInfo;
    @NonNull
    public final TextView tvWatchSettingHeader;
    @NonNull
    public final TextView tvWatchSettingRightHeader;

    public SetupWatchLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, CardView cardView, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.clBgContainer = constraintLayout;
        this.clMain = constraintLayout2;
        this.cvBackground = cardView;
        this.ivWatchBackground = imageView;
        this.tvInfo = textView;
        this.tvWatchSettingHeader = textView2;
        this.tvWatchSettingRightHeader = textView3;
    }

    public static SetupWatchLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SetupWatchLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public SetupYourWatchDataModel getData() {
        return this.mData;
    }

    public abstract void setData(@Nullable SetupYourWatchDataModel setupYourWatchDataModel);

    @Deprecated
    public static SetupWatchLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SetupWatchLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.setup_watch_layout);
    }

    @NonNull
    @Deprecated
    public static SetupWatchLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SetupWatchLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.setup_watch_layout, viewGroup, z, obj);
    }

    @NonNull
    public static SetupWatchLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SetupWatchLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SetupWatchLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.setup_watch_layout, null, false, obj);
    }
}
