package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class ReasonPopupBinding extends ViewDataBinding {
    @NonNull
    public final TextView btnCancel;
    @NonNull
    public final Button btnSave;
    @NonNull
    public final EditText edittextReason;
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final ImageView ivExpandEdTv;
    @NonNull
    public final TextView tvGiveName;
    @NonNull
    public final TextView tvStrapName;

    public ReasonPopupBinding(Object obj, View view, int i, TextView textView, Button button, EditText editText, Guideline guideline, ImageView imageView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.btnCancel = textView;
        this.btnSave = button;
        this.edittextReason = editText;
        this.guideline1 = guideline;
        this.ivExpandEdTv = imageView;
        this.tvGiveName = textView2;
        this.tvStrapName = textView3;
    }

    public static ReasonPopupBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ReasonPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ReasonPopupBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ReasonPopupBinding) ViewDataBinding.bind(obj, view, R.layout.reason_popup);
    }

    @NonNull
    @Deprecated
    public static ReasonPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ReasonPopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.reason_popup, viewGroup, z, obj);
    }

    @NonNull
    public static ReasonPopupBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ReasonPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ReasonPopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.reason_popup, null, false, obj);
    }
}
