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
public abstract class FragmentWatchFaceDefaultNew2Binding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout rootLayout;
    @NonNull
    public final RecyclerView watchFaceRecycler;

    public FragmentWatchFaceDefaultNew2Binding(Object obj, View view, int i, ConstraintLayout constraintLayout, RecyclerView recyclerView) {
        super(obj, view, i);
        this.rootLayout = constraintLayout;
        this.watchFaceRecycler = recyclerView;
    }

    public static FragmentWatchFaceDefaultNew2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentWatchFaceDefaultNew2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWatchFaceDefaultNew2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentWatchFaceDefaultNew2Binding) ViewDataBinding.bind(obj, view, R.layout.fragment_watch_face_default_new2);
    }

    @NonNull
    @Deprecated
    public static FragmentWatchFaceDefaultNew2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentWatchFaceDefaultNew2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_watch_face_default_new2, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentWatchFaceDefaultNew2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentWatchFaceDefaultNew2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentWatchFaceDefaultNew2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_watch_face_default_new2, null, false, obj);
    }
}
