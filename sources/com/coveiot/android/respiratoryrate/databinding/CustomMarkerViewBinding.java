package com.coveiot.android.respiratoryrate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.respiratoryrate.R;
/* loaded from: classes6.dex */
public abstract class CustomMarkerViewBinding extends ViewDataBinding {
    @NonNull
    public final TextView tvContent;

    public CustomMarkerViewBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.tvContent = textView;
    }

    public static CustomMarkerViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CustomMarkerViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CustomMarkerViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CustomMarkerViewBinding) ViewDataBinding.bind(obj, view, R.layout.custom_marker_view);
    }

    @NonNull
    @Deprecated
    public static CustomMarkerViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CustomMarkerViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.custom_marker_view, viewGroup, z, obj);
    }

    @NonNull
    public static CustomMarkerViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CustomMarkerViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CustomMarkerViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.custom_marker_view, null, false, obj);
    }
}
