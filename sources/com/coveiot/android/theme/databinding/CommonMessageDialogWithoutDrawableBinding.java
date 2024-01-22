package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class CommonMessageDialogWithoutDrawableBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clMain;
    @NonNull
    public final CardView searchLayout;
    @NonNull
    public final TextView tvMessage;

    public CommonMessageDialogWithoutDrawableBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, CardView cardView, TextView textView) {
        super(obj, view, i);
        this.clMain = constraintLayout;
        this.searchLayout = cardView;
        this.tvMessage = textView;
    }

    public static CommonMessageDialogWithoutDrawableBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CommonMessageDialogWithoutDrawableBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CommonMessageDialogWithoutDrawableBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CommonMessageDialogWithoutDrawableBinding) ViewDataBinding.bind(obj, view, R.layout.common_message_dialog_without_drawable);
    }

    @NonNull
    @Deprecated
    public static CommonMessageDialogWithoutDrawableBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CommonMessageDialogWithoutDrawableBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.common_message_dialog_without_drawable, viewGroup, z, obj);
    }

    @NonNull
    public static CommonMessageDialogWithoutDrawableBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CommonMessageDialogWithoutDrawableBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CommonMessageDialogWithoutDrawableBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.common_message_dialog_without_drawable, null, false, obj);
    }
}
