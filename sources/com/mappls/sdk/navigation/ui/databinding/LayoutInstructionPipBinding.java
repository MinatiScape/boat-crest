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
public abstract class LayoutInstructionPipBinding extends ViewDataBinding {
    @NonNull
    public final ManeuverView maneuverImageView;
    @NonNull
    public final TextView navigationStripDist;
    @NonNull
    public final TextView navigationStripText;
    @NonNull
    public final TextView otherInfoTextView;
    @NonNull
    public final LinearLayout repeatCurrentInstructionsLayout;
    @NonNull
    public final LinearLayout stripItemContainer;

    public LayoutInstructionPipBinding(Object obj, View view, int i, ManeuverView maneuverView, TextView textView, TextView textView2, TextView textView3, LinearLayout linearLayout, LinearLayout linearLayout2) {
        super(obj, view, i);
        this.maneuverImageView = maneuverView;
        this.navigationStripDist = textView;
        this.navigationStripText = textView2;
        this.otherInfoTextView = textView3;
        this.repeatCurrentInstructionsLayout = linearLayout;
        this.stripItemContainer = linearLayout2;
    }

    public static LayoutInstructionPipBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutInstructionPipBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutInstructionPipBinding) ViewDataBinding.bind(obj, view, R.layout.layout_instruction_pip);
    }

    @NonNull
    public static LayoutInstructionPipBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutInstructionPipBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutInstructionPipBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutInstructionPipBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_instruction_pip, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutInstructionPipBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutInstructionPipBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_instruction_pip, null, false, obj);
    }
}
