package com.coveiot.android.qrtray.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentContainerView;
import com.coveiot.android.qrtray.R;
/* loaded from: classes5.dex */
public abstract class ActivityQrtrayBinding extends ViewDataBinding {
    @NonNull
    public final FragmentContainerView qrContainer;

    public ActivityQrtrayBinding(Object obj, View view, int i, FragmentContainerView fragmentContainerView) {
        super(obj, view, i);
        this.qrContainer = fragmentContainerView;
    }

    public static ActivityQrtrayBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityQrtrayBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityQrtrayBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityQrtrayBinding) ViewDataBinding.bind(obj, view, R.layout.activity_qrtray);
    }

    @NonNull
    @Deprecated
    public static ActivityQrtrayBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityQrtrayBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_qrtray, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityQrtrayBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityQrtrayBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityQrtrayBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_qrtray, null, false, obj);
    }
}
