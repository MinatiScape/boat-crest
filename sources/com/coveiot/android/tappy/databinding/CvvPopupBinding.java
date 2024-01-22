package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class CvvPopupBinding extends ViewDataBinding {
    @NonNull
    public final TextView btnCancel;
    @NonNull
    public final Button btnSave;
    @NonNull
    public final EditText edittextCvv;
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final TextView tvGiveName;
    @NonNull
    public final TextView tvStrapName;

    public CvvPopupBinding(Object obj, View view, int i, TextView textView, Button button, EditText editText, Guideline guideline, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.btnCancel = textView;
        this.btnSave = button;
        this.edittextCvv = editText;
        this.guideline1 = guideline;
        this.tvGiveName = textView2;
        this.tvStrapName = textView3;
    }

    public static CvvPopupBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CvvPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CvvPopupBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CvvPopupBinding) ViewDataBinding.bind(obj, view, R.layout.cvv_popup);
    }

    @NonNull
    @Deprecated
    public static CvvPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CvvPopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.cvv_popup, viewGroup, z, obj);
    }

    @NonNull
    public static CvvPopupBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CvvPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CvvPopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.cvv_popup, null, false, obj);
    }
}
