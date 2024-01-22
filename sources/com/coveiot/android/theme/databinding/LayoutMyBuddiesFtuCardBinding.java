package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class LayoutMyBuddiesFtuCardBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivIcon;
    @NonNull
    public final TextView tvActionButton;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvInfo;

    public LayoutMyBuddiesFtuCardBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.ivIcon = imageView;
        this.tvActionButton = textView;
        this.tvHeader = textView2;
        this.tvInfo = textView3;
    }

    public static LayoutMyBuddiesFtuCardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutMyBuddiesFtuCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutMyBuddiesFtuCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutMyBuddiesFtuCardBinding) ViewDataBinding.bind(obj, view, R.layout.layout_my_buddies_ftu_card);
    }

    @NonNull
    @Deprecated
    public static LayoutMyBuddiesFtuCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutMyBuddiesFtuCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_my_buddies_ftu_card, viewGroup, z, obj);
    }

    @NonNull
    public static LayoutMyBuddiesFtuCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutMyBuddiesFtuCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutMyBuddiesFtuCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_my_buddies_ftu_card, null, false, obj);
    }
}
