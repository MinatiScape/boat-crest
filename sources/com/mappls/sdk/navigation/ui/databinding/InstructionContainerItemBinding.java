package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.plugin.directions.view.ManeuverView;
/* loaded from: classes11.dex */
public abstract class InstructionContainerItemBinding extends ViewDataBinding {
    @NonNull
    public final RelativeLayout laneGuidanceContainer;
    @NonNull
    public final TextView maneuverIdTextView;
    @NonNull
    public final ManeuverView maneuverImageView;
    @NonNull
    public final TextView navigationStripDist;
    @NonNull
    public final TextView navigationStripShortText;
    @NonNull
    public final TextView navigationStripText;
    @NonNull
    public final LinearLayout repeatCurrentInstructionsLayout;
    @NonNull
    public final RecyclerView rvTurnLanes;
    @NonNull
    public final LinearLayout stripItemContainer;

    public InstructionContainerItemBinding(Object obj, View view, int i, RelativeLayout relativeLayout, TextView textView, ManeuverView maneuverView, TextView textView2, TextView textView3, TextView textView4, LinearLayout linearLayout, RecyclerView recyclerView, LinearLayout linearLayout2) {
        super(obj, view, i);
        this.laneGuidanceContainer = relativeLayout;
        this.maneuverIdTextView = textView;
        this.maneuverImageView = maneuverView;
        this.navigationStripDist = textView2;
        this.navigationStripShortText = textView3;
        this.navigationStripText = textView4;
        this.repeatCurrentInstructionsLayout = linearLayout;
        this.rvTurnLanes = recyclerView;
        this.stripItemContainer = linearLayout2;
    }

    public static InstructionContainerItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static InstructionContainerItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (InstructionContainerItemBinding) ViewDataBinding.bind(obj, view, R.layout.instruction_container_item);
    }

    @NonNull
    public static InstructionContainerItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static InstructionContainerItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static InstructionContainerItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (InstructionContainerItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.instruction_container_item, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static InstructionContainerItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (InstructionContainerItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.instruction_container_item, null, false, obj);
    }
}
