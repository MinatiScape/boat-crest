package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class FiltedAppliedItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivClose;
    @NonNull
    public final TextView tvRankTypeValue;

    public FiltedAppliedItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.ivClose = imageView;
        this.tvRankTypeValue = textView;
    }

    public static FiltedAppliedItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FiltedAppliedItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FiltedAppliedItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FiltedAppliedItemBinding) ViewDataBinding.bind(obj, view, R.layout.filted_applied_item);
    }

    @NonNull
    @Deprecated
    public static FiltedAppliedItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FiltedAppliedItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.filted_applied_item, viewGroup, z, obj);
    }

    @NonNull
    public static FiltedAppliedItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FiltedAppliedItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FiltedAppliedItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.filted_applied_item, null, false, obj);
    }
}
