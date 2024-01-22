package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.common.NonSwipeableViewPager;
/* loaded from: classes11.dex */
public abstract class LayoutInstructionContainerBinding extends ViewDataBinding {
    @NonNull
    public final NonSwipeableViewPager navigationInfoLayoutNew;
    @NonNull
    public final ImageButton navigationStripLeftImageButton;
    @NonNull
    public final ImageButton navigationStripRightImageButton;
    @NonNull
    public final TextView otherInfoTextView;
    @NonNull
    public final RelativeLayout topStripLayout;

    public LayoutInstructionContainerBinding(Object obj, View view, int i, NonSwipeableViewPager nonSwipeableViewPager, ImageButton imageButton, ImageButton imageButton2, TextView textView, RelativeLayout relativeLayout) {
        super(obj, view, i);
        this.navigationInfoLayoutNew = nonSwipeableViewPager;
        this.navigationStripLeftImageButton = imageButton;
        this.navigationStripRightImageButton = imageButton2;
        this.otherInfoTextView = textView;
        this.topStripLayout = relativeLayout;
    }

    public static LayoutInstructionContainerBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutInstructionContainerBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutInstructionContainerBinding) ViewDataBinding.bind(obj, view, R.layout.layout_instruction_container);
    }

    @NonNull
    public static LayoutInstructionContainerBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutInstructionContainerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutInstructionContainerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutInstructionContainerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_instruction_container, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutInstructionContainerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutInstructionContainerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_instruction_container, null, false, obj);
    }
}
