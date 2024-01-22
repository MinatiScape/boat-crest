package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class GenericDialogWithTwoOptionsBinding extends ViewDataBinding {
    @NonNull
    public final TextView tvIcon1;
    @NonNull
    public final TextView tvIcon2;
    @NonNull
    public final TextView tvIcon3;
    @NonNull
    public final View view1;
    @NonNull
    public final View view2;

    public GenericDialogWithTwoOptionsBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3, View view2, View view3) {
        super(obj, view, i);
        this.tvIcon1 = textView;
        this.tvIcon2 = textView2;
        this.tvIcon3 = textView3;
        this.view1 = view2;
        this.view2 = view3;
    }

    public static GenericDialogWithTwoOptionsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static GenericDialogWithTwoOptionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static GenericDialogWithTwoOptionsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (GenericDialogWithTwoOptionsBinding) ViewDataBinding.bind(obj, view, R.layout.generic_dialog_with_two_options);
    }

    @NonNull
    @Deprecated
    public static GenericDialogWithTwoOptionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (GenericDialogWithTwoOptionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.generic_dialog_with_two_options, viewGroup, z, obj);
    }

    @NonNull
    public static GenericDialogWithTwoOptionsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static GenericDialogWithTwoOptionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (GenericDialogWithTwoOptionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.generic_dialog_with_two_options, null, false, obj);
    }
}
