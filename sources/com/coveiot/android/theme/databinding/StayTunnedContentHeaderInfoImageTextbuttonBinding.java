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
public abstract class StayTunnedContentHeaderInfoImageTextbuttonBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivIcon;
    @NonNull
    public final TextView tvActionButton;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvInfo;

    public StayTunnedContentHeaderInfoImageTextbuttonBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.ivIcon = imageView;
        this.tvActionButton = textView;
        this.tvHeader = textView2;
        this.tvInfo = textView3;
    }

    public static StayTunnedContentHeaderInfoImageTextbuttonBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static StayTunnedContentHeaderInfoImageTextbuttonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static StayTunnedContentHeaderInfoImageTextbuttonBinding bind(@NonNull View view, @Nullable Object obj) {
        return (StayTunnedContentHeaderInfoImageTextbuttonBinding) ViewDataBinding.bind(obj, view, R.layout.stay_tunned_content_header_info_image_textbutton);
    }

    @NonNull
    @Deprecated
    public static StayTunnedContentHeaderInfoImageTextbuttonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (StayTunnedContentHeaderInfoImageTextbuttonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.stay_tunned_content_header_info_image_textbutton, viewGroup, z, obj);
    }

    @NonNull
    public static StayTunnedContentHeaderInfoImageTextbuttonBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static StayTunnedContentHeaderInfoImageTextbuttonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (StayTunnedContentHeaderInfoImageTextbuttonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.stay_tunned_content_header_info_image_textbutton, null, false, obj);
    }
}
