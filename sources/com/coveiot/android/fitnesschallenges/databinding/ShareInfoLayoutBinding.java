package com.coveiot.android.fitnesschallenges.databinding;

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
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public abstract class ShareInfoLayoutBinding extends ViewDataBinding {
    @NonNull
    public final TextView desc;
    @NonNull
    public final ConstraintLayout durationLayout1;
    @NonNull
    public final ImageView icon;
    @NonNull
    public final TextView title;

    public ShareInfoLayoutBinding(Object obj, View view, int i, TextView textView, ConstraintLayout constraintLayout, ImageView imageView, TextView textView2) {
        super(obj, view, i);
        this.desc = textView;
        this.durationLayout1 = constraintLayout;
        this.icon = imageView;
        this.title = textView2;
    }

    public static ShareInfoLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ShareInfoLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ShareInfoLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ShareInfoLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.share_info_layout);
    }

    @NonNull
    @Deprecated
    public static ShareInfoLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ShareInfoLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.share_info_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ShareInfoLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ShareInfoLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ShareInfoLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.share_info_layout, null, false, obj);
    }
}
