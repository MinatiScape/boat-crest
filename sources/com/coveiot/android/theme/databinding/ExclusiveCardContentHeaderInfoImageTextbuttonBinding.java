package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ExclusiveCardContentHeaderInfoImageTextbuttonBinding extends ViewDataBinding {
    @NonNull
    public final CardView cvMain;
    @NonNull
    public final ImageView ivIcon;
    @NonNull
    public final TextView tvActionButton;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvInfo;

    public ExclusiveCardContentHeaderInfoImageTextbuttonBinding(Object obj, View view, int i, CardView cardView, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.cvMain = cardView;
        this.ivIcon = imageView;
        this.tvActionButton = textView;
        this.tvHeader = textView2;
        this.tvInfo = textView3;
    }

    public static ExclusiveCardContentHeaderInfoImageTextbuttonBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ExclusiveCardContentHeaderInfoImageTextbuttonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ExclusiveCardContentHeaderInfoImageTextbuttonBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ExclusiveCardContentHeaderInfoImageTextbuttonBinding) ViewDataBinding.bind(obj, view, R.layout.exclusive_card_content_header_info_image_textbutton);
    }

    @NonNull
    @Deprecated
    public static ExclusiveCardContentHeaderInfoImageTextbuttonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ExclusiveCardContentHeaderInfoImageTextbuttonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.exclusive_card_content_header_info_image_textbutton, viewGroup, z, obj);
    }

    @NonNull
    public static ExclusiveCardContentHeaderInfoImageTextbuttonBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ExclusiveCardContentHeaderInfoImageTextbuttonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ExclusiveCardContentHeaderInfoImageTextbuttonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.exclusive_card_content_header_info_image_textbutton, null, false, obj);
    }
}
