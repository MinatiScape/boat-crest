package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.plugin.directions.view.ManeuverView;
/* loaded from: classes11.dex */
public abstract class LayoutNextInstructionViewBinding extends ViewDataBinding {
    @NonNull
    public final TextView nextAdviceDuration;
    @NonNull
    public final LinearLayout nextAdviseContainer;
    @NonNull
    public final ManeuverView nextInstructionImageView;
    @NonNull
    public final TextView tvThen;

    public LayoutNextInstructionViewBinding(Object obj, View view, int i, TextView textView, LinearLayout linearLayout, ManeuverView maneuverView, TextView textView2) {
        super(obj, view, i);
        this.nextAdviceDuration = textView;
        this.nextAdviseContainer = linearLayout;
        this.nextInstructionImageView = maneuverView;
        this.tvThen = textView2;
    }

    public static LayoutNextInstructionViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutNextInstructionViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutNextInstructionViewBinding) ViewDataBinding.bind(obj, view, R.layout.layout_next_instruction_view);
    }

    @NonNull
    public static LayoutNextInstructionViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutNextInstructionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutNextInstructionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutNextInstructionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_next_instruction_view, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutNextInstructionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutNextInstructionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_next_instruction_view, null, false, obj);
    }
}
