package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class VirtualCardInfoPopupBinding extends ViewDataBinding {
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final ImageButton imgvClose;
    @NonNull
    public final ImageView imgvVirtualCard;
    @NonNull
    public final TextView tvVirtualCard;
    @NonNull
    public final TextView tvVirtualCardInfo;

    public VirtualCardInfoPopupBinding(Object obj, View view, int i, Guideline guideline, ImageButton imageButton, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.guideline1 = guideline;
        this.imgvClose = imageButton;
        this.imgvVirtualCard = imageView;
        this.tvVirtualCard = textView;
        this.tvVirtualCardInfo = textView2;
    }

    public static VirtualCardInfoPopupBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static VirtualCardInfoPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VirtualCardInfoPopupBinding bind(@NonNull View view, @Nullable Object obj) {
        return (VirtualCardInfoPopupBinding) ViewDataBinding.bind(obj, view, R.layout.virtual_card_info_popup);
    }

    @NonNull
    @Deprecated
    public static VirtualCardInfoPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (VirtualCardInfoPopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.virtual_card_info_popup, viewGroup, z, obj);
    }

    @NonNull
    public static VirtualCardInfoPopupBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static VirtualCardInfoPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (VirtualCardInfoPopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.virtual_card_info_popup, null, false, obj);
    }
}
