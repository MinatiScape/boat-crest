package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class SensAiListItemBinding extends ViewDataBinding {
    @NonNull
    public final CheckBox cbAddToCompare;
    @NonNull
    public final ConstraintLayout clAvgHandSpeed;
    @NonNull
    public final ConstraintLayout clData1;
    @NonNull
    public final ConstraintLayout clDataheading;
    @NonNull
    public final LinearLayout clDetails;
    @NonNull
    public final ConstraintLayout clDuration;
    @NonNull
    public final ConstraintLayout clTotalShots;
    @NonNull
    public final Guideline firstGuideline;
    @NonNull
    public final ImageView ivAward;
    @NonNull
    public final ImageView ivDuration;
    @NonNull
    public final ImageView ivImage;
    @NonNull
    public final ImageView ivShots;
    @NonNull
    public final ImageView ivSpeed;
    @NonNull
    public final Guideline secondGuideline;
    @NonNull
    public final TextView tvAvgHandSpeed;
    @NonNull
    public final TextView tvAvgHandSpeedTxt;
    @NonNull
    public final TextView tvDuration;
    @NonNull
    public final TextView tvDurationTxt;
    @NonNull
    public final TextView tvTime;
    @NonNull
    public final TextView tvTitle;
    @NonNull
    public final TextView tvTotalShots;
    @NonNull
    public final TextView tvTotalShotsTxt;
    @NonNull
    public final View view;
    @NonNull
    public final View viewAddToCompare;
    @NonNull
    public final View viewShots;
    @NonNull
    public final View viewSpeed;

    public SensAiListItemBinding(Object obj, View view, int i, CheckBox checkBox, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, LinearLayout linearLayout, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, Guideline guideline, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, Guideline guideline2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, View view2, View view3, View view4, View view5) {
        super(obj, view, i);
        this.cbAddToCompare = checkBox;
        this.clAvgHandSpeed = constraintLayout;
        this.clData1 = constraintLayout2;
        this.clDataheading = constraintLayout3;
        this.clDetails = linearLayout;
        this.clDuration = constraintLayout4;
        this.clTotalShots = constraintLayout5;
        this.firstGuideline = guideline;
        this.ivAward = imageView;
        this.ivDuration = imageView2;
        this.ivImage = imageView3;
        this.ivShots = imageView4;
        this.ivSpeed = imageView5;
        this.secondGuideline = guideline2;
        this.tvAvgHandSpeed = textView;
        this.tvAvgHandSpeedTxt = textView2;
        this.tvDuration = textView3;
        this.tvDurationTxt = textView4;
        this.tvTime = textView5;
        this.tvTitle = textView6;
        this.tvTotalShots = textView7;
        this.tvTotalShotsTxt = textView8;
        this.view = view2;
        this.viewAddToCompare = view3;
        this.viewShots = view4;
        this.viewSpeed = view5;
    }

    public static SensAiListItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SensAiListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SensAiListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SensAiListItemBinding) ViewDataBinding.bind(obj, view, R.layout.sens_ai_list_item);
    }

    @NonNull
    @Deprecated
    public static SensAiListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SensAiListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_list_item, viewGroup, z, obj);
    }

    @NonNull
    public static SensAiListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SensAiListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SensAiListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_list_item, null, false, obj);
    }
}
