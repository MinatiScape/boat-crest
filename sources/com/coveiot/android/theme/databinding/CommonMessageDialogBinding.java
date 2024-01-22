package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class CommonMessageDialogBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clMain;
    @NonNull
    public final ImageView ivClose;
    @NonNull
    public final CardView searchLayout;
    @NonNull
    public final TextView tvMessage;

    public CommonMessageDialogBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, CardView cardView, TextView textView) {
        super(obj, view, i);
        this.clMain = constraintLayout;
        this.ivClose = imageView;
        this.searchLayout = cardView;
        this.tvMessage = textView;
    }

    public static CommonMessageDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CommonMessageDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CommonMessageDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CommonMessageDialogBinding) ViewDataBinding.bind(obj, view, R.layout.common_message_dialog);
    }

    @NonNull
    @Deprecated
    public static CommonMessageDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CommonMessageDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.common_message_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static CommonMessageDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CommonMessageDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CommonMessageDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.common_message_dialog, null, false, obj);
    }
}
