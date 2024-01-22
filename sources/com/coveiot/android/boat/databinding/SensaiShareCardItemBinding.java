package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class SensaiShareCardItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout itemRowCl;
    @NonNull
    public final TextView itemTitleText;
    @NonNull
    public final TextView itemValText;

    public SensaiShareCardItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.itemRowCl = constraintLayout;
        this.itemTitleText = textView;
        this.itemValText = textView2;
    }

    public static SensaiShareCardItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SensaiShareCardItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SensaiShareCardItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SensaiShareCardItemBinding) ViewDataBinding.bind(obj, view, R.layout.sensai_share_card_item);
    }

    @NonNull
    @Deprecated
    public static SensaiShareCardItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SensaiShareCardItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sensai_share_card_item, viewGroup, z, obj);
    }

    @NonNull
    public static SensaiShareCardItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SensaiShareCardItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SensaiShareCardItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sensai_share_card_item, null, false, obj);
    }
}
