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
import com.aigestudio.wheelpicker.WheelPicker;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class PickerDialogBinding extends ViewDataBinding {
    @NonNull
    public final ImageView arrowImage;
    @NonNull
    public final TextView cancelPopup;
    @NonNull
    public final WheelPicker numberPicker;
    @NonNull
    public final TextView okPopup;
    @NonNull
    public final TextView pouupLabel;
    @NonNull
    public final TextView unitName;

    public PickerDialogBinding(Object obj, View view, int i, ImageView imageView, TextView textView, WheelPicker wheelPicker, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.arrowImage = imageView;
        this.cancelPopup = textView;
        this.numberPicker = wheelPicker;
        this.okPopup = textView2;
        this.pouupLabel = textView3;
        this.unitName = textView4;
    }

    public static PickerDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static PickerDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PickerDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PickerDialogBinding) ViewDataBinding.bind(obj, view, R.layout.picker_dialog);
    }

    @NonNull
    @Deprecated
    public static PickerDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (PickerDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.picker_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static PickerDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static PickerDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (PickerDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.picker_dialog, null, false, obj);
    }
}
