package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentTroubleshootTestingBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivPoweredBy;
    @NonNull
    public final RecyclerView rcvTroubleshootItems;
    @NonNull
    public final TextView sendTvNo;
    @NonNull
    public final ConstraintLayout sendTvTitleLayout;
    @NonNull
    public final Button testOkBtn;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView troubleShootSendInstTv;
    @NonNull
    public final ConstraintLayout troubleShootSendLayout;
    @NonNull
    public final TextView troubleshootFailTv;
    @NonNull
    public final TextView troubleshootSendTv;

    public FragmentTroubleshootTestingBinding(Object obj, View view, int i, ImageView imageView, RecyclerView recyclerView, TextView textView, ConstraintLayout constraintLayout, Button button, View view2, TextView textView2, ConstraintLayout constraintLayout2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.ivPoweredBy = imageView;
        this.rcvTroubleshootItems = recyclerView;
        this.sendTvNo = textView;
        this.sendTvTitleLayout = constraintLayout;
        this.testOkBtn = button;
        this.toolbar = view2;
        this.troubleShootSendInstTv = textView2;
        this.troubleShootSendLayout = constraintLayout2;
        this.troubleshootFailTv = textView3;
        this.troubleshootSendTv = textView4;
    }

    public static FragmentTroubleshootTestingBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentTroubleshootTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTroubleshootTestingBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentTroubleshootTestingBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_troubleshoot_testing);
    }

    @NonNull
    @Deprecated
    public static FragmentTroubleshootTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentTroubleshootTestingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_troubleshoot_testing, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentTroubleshootTestingBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentTroubleshootTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentTroubleshootTestingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_troubleshoot_testing, null, false, obj);
    }
}
