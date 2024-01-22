package com.coveiot.android.smartalert.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.smartalert.R;
/* loaded from: classes6.dex */
public abstract class ActivitySmartAlertBinding extends ViewDataBinding {
    @NonNull
    public final Button btnSave;
    @NonNull
    public final ConstraintLayout clInfo;
    @NonNull
    public final TextView header;
    @NonNull
    public final ImageView ivWatch;
    @NonNull
    public final RecyclerView rvApplications;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvInfo;

    public ActivitySmartAlertBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, TextView textView, ImageView imageView, RecyclerView recyclerView, View view2, TextView textView2) {
        super(obj, view, i);
        this.btnSave = button;
        this.clInfo = constraintLayout;
        this.header = textView;
        this.ivWatch = imageView;
        this.rvApplications = recyclerView;
        this.toolbar = view2;
        this.tvInfo = textView2;
    }

    public static ActivitySmartAlertBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySmartAlertBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySmartAlertBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivitySmartAlertBinding) ViewDataBinding.bind(obj, view, R.layout.activity_smart_alert);
    }

    @NonNull
    @Deprecated
    public static ActivitySmartAlertBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivitySmartAlertBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_smart_alert, viewGroup, z, obj);
    }

    @NonNull
    public static ActivitySmartAlertBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivitySmartAlertBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivitySmartAlertBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_smart_alert, null, false, obj);
    }
}
