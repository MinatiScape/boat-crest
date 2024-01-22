package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class GenericDialogTwoButtonsBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivClose;
    @NonNull
    public final TextView message;
    @NonNull
    public final Button negativeBtn;
    @NonNull
    public final Button positiveBtn;
    @NonNull
    public final TextView title;

    public GenericDialogTwoButtonsBinding(Object obj, View view, int i, ImageView imageView, TextView textView, Button button, Button button2, TextView textView2) {
        super(obj, view, i);
        this.ivClose = imageView;
        this.message = textView;
        this.negativeBtn = button;
        this.positiveBtn = button2;
        this.title = textView2;
    }

    public static GenericDialogTwoButtonsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static GenericDialogTwoButtonsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static GenericDialogTwoButtonsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (GenericDialogTwoButtonsBinding) ViewDataBinding.bind(obj, view, R.layout.generic_dialog_two_buttons);
    }

    @NonNull
    @Deprecated
    public static GenericDialogTwoButtonsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (GenericDialogTwoButtonsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.generic_dialog_two_buttons, viewGroup, z, obj);
    }

    @NonNull
    public static GenericDialogTwoButtonsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static GenericDialogTwoButtonsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (GenericDialogTwoButtonsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.generic_dialog_two_buttons, null, false, obj);
    }
}
