package com.coveiot.android.watchfaceui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.watchfaceui.R;
/* loaded from: classes8.dex */
public abstract class FragmentWatchFaceCloudNewBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView catogoriesRecycler;
    @NonNull
    public final ConstraintLayout rootLayout;
    @NonNull
    public final RecyclerView watchFaceRecycler;

    public FragmentWatchFaceCloudNewBinding(Object obj, View view, int i, RecyclerView recyclerView, ConstraintLayout constraintLayout, RecyclerView recyclerView2) {
        super(obj, view, i);
        this.catogoriesRecycler = recyclerView;
        this.rootLayout = constraintLayout;
        this.watchFaceRecycler = recyclerView2;
    }

    public static FragmentWatchFaceCloudNewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentWatchFaceCloudNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWatchFaceCloudNewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentWatchFaceCloudNewBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_watch_face_cloud_new);
    }

    @NonNull
    @Deprecated
    public static FragmentWatchFaceCloudNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentWatchFaceCloudNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_watch_face_cloud_new, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentWatchFaceCloudNewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentWatchFaceCloudNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentWatchFaceCloudNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_watch_face_cloud_new, null, false, obj);
    }
}
