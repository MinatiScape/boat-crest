package com.coveiot.android.watchfaceui.databinding;

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
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.watchfaceui.R;
/* loaded from: classes8.dex */
public abstract class FragmentMyDesignsBinding extends ViewDataBinding {
    @NonNull
    public final ImageView close;
    @NonNull
    public final ConstraintLayout deleteLayout;
    @NonNull
    public final ConstraintLayout infoLayout;
    @NonNull
    public final ConstraintLayout noWatchFace;
    @NonNull
    public final ConstraintLayout rootLayout;
    @NonNull
    public final ImageView toolTipInfo;
    @NonNull
    public final ConstraintLayout topLayout;
    @NonNull
    public final TextView tvDelete;
    @NonNull
    public final TextView tvSelectAll;
    @NonNull
    public final TextView tvToolTipFragment;
    @NonNull
    public final RecyclerView watchFaceRecycler;
    @NonNull
    public final ConstraintLayout watchFaceStudio;

    public FragmentMyDesignsBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ImageView imageView2, ConstraintLayout constraintLayout5, TextView textView, TextView textView2, TextView textView3, RecyclerView recyclerView, ConstraintLayout constraintLayout6) {
        super(obj, view, i);
        this.close = imageView;
        this.deleteLayout = constraintLayout;
        this.infoLayout = constraintLayout2;
        this.noWatchFace = constraintLayout3;
        this.rootLayout = constraintLayout4;
        this.toolTipInfo = imageView2;
        this.topLayout = constraintLayout5;
        this.tvDelete = textView;
        this.tvSelectAll = textView2;
        this.tvToolTipFragment = textView3;
        this.watchFaceRecycler = recyclerView;
        this.watchFaceStudio = constraintLayout6;
    }

    public static FragmentMyDesignsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentMyDesignsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMyDesignsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentMyDesignsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_my_designs);
    }

    @NonNull
    @Deprecated
    public static FragmentMyDesignsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentMyDesignsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_my_designs, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentMyDesignsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentMyDesignsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentMyDesignsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_my_designs, null, false, obj);
    }
}
