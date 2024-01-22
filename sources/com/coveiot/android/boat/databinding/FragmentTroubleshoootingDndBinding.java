package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentTroubleshoootingDndBinding extends ViewDataBinding {
    @NonNull
    public final AppCompatButton cBtnConfirm;
    @NonNull
    public final CheckBox cbDnd;
    @NonNull
    public final ConstraintLayout clWithDndDetail;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvDndSettingTurnedOff;
    @NonNull
    public final TextView tvDndTitle;

    public FragmentTroubleshoootingDndBinding(Object obj, View view, int i, AppCompatButton appCompatButton, CheckBox checkBox, ConstraintLayout constraintLayout, View view2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.cBtnConfirm = appCompatButton;
        this.cbDnd = checkBox;
        this.clWithDndDetail = constraintLayout;
        this.toolbar = view2;
        this.tvDndSettingTurnedOff = textView;
        this.tvDndTitle = textView2;
    }

    public static FragmentTroubleshoootingDndBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentTroubleshoootingDndBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTroubleshoootingDndBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentTroubleshoootingDndBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_troubleshoooting_dnd);
    }

    @NonNull
    @Deprecated
    public static FragmentTroubleshoootingDndBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentTroubleshoootingDndBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_troubleshoooting_dnd, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentTroubleshoootingDndBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentTroubleshoootingDndBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentTroubleshoootingDndBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_troubleshoooting_dnd, null, false, obj);
    }
}
