package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentSensAiShareDataBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clActivity1;
    @NonNull
    public final ConstraintLayout clActivity2;
    @NonNull
    public final ConstraintLayout clActivity3;
    @NonNull
    public final ConstraintLayout clActivity4;
    @NonNull
    public final ConstraintLayout clActivityDetails;
    @NonNull
    public final ConstraintLayout clDetails;
    @NonNull
    public final ConstraintLayout clHandSpeed1;
    @NonNull
    public final ConstraintLayout clHandSpeed2;
    @NonNull
    public final ConstraintLayout clHandSpeed3;
    @NonNull
    public final ConstraintLayout clProfileDetails;
    @NonNull
    public final ConstraintLayout clVitals1;
    @NonNull
    public final ConstraintLayout clVitals2;
    @NonNull
    public final ConstraintLayout clVitals3;
    @NonNull
    public final TextView disclaimerInfo;
    @NonNull
    public final ImageView ivActivity1;
    @NonNull
    public final ImageView ivActivity2;
    @NonNull
    public final ImageView ivActivity3;
    @NonNull
    public final ImageView ivActivity4;
    @NonNull
    public final ImageView ivAppLogo;
    @NonNull
    public final ImageView ivCenterVitalBg;
    @NonNull
    public final ImageView ivHandSpeed1;
    @NonNull
    public final ImageView ivHandSpeed2;
    @NonNull
    public final ImageView ivHandSpeed3;
    @NonNull
    public final ImageView ivPoweredCove;
    @NonNull
    public final ImageView ivSensAIBg;
    @NonNull
    public final ImageView ivVitals1;
    @NonNull
    public final ImageView ivVitals2;
    @NonNull
    public final ImageView ivVitals3;
    @NonNull
    public final LinearLayout llHandSpeed;
    @NonNull
    public final LinearLayout llVitals;
    @NonNull
    public final ConstraintLayout rootLayout;
    @NonNull
    public final NestedScrollView sensAiShareView;
    @NonNull
    public final TextView tvActivity;
    @NonNull
    public final TextView tvActivity1;
    @NonNull
    public final TextView tvActivity1Value;
    @NonNull
    public final TextView tvActivity2;
    @NonNull
    public final TextView tvActivity2Value;
    @NonNull
    public final TextView tvActivity3;
    @NonNull
    public final TextView tvActivity3Value;
    @NonNull
    public final TextView tvActivity4;
    @NonNull
    public final TextView tvActivity4Value;
    @NonNull
    public final TextView tvHandSpeed;
    @NonNull
    public final TextView tvHandSpeed1;
    @NonNull
    public final TextView tvHandSpeed1Txt;
    @NonNull
    public final TextView tvHandSpeed2;
    @NonNull
    public final TextView tvHandSpeed2Txt;
    @NonNull
    public final TextView tvHandSpeed3;
    @NonNull
    public final TextView tvHandSpeed3Txt;
    @NonNull
    public final TextView tvTitle;
    @NonNull
    public final TextView tvVitals;
    @NonNull
    public final TextView tvVitals1;
    @NonNull
    public final TextView tvVitals1Txt;
    @NonNull
    public final TextView tvVitals2;
    @NonNull
    public final TextView tvVitals2Txt;
    @NonNull
    public final TextView tvVitals3;
    @NonNull
    public final TextView tvVitals3Txt;
    @NonNull
    public final TextView userName;
    @NonNull
    public final ImageView userPic;
    @NonNull
    public final View view1;
    @NonNull
    public final View view2;
    @NonNull
    public final View viewActivity;
    @NonNull
    public final View viewHandSpeed2;
    @NonNull
    public final View viewHandSpeed3;
    @NonNull
    public final View viewVertical;
    @NonNull
    public final View viewVitals2;
    @NonNull
    public final View viewVitals3;
    @NonNull
    public final TextView week;

    public FragmentSensAiShareDataBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, ConstraintLayout constraintLayout12, ConstraintLayout constraintLayout13, TextView textView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, ImageView imageView10, ImageView imageView11, ImageView imageView12, ImageView imageView13, ImageView imageView14, LinearLayout linearLayout, LinearLayout linearLayout2, ConstraintLayout constraintLayout14, NestedScrollView nestedScrollView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, TextView textView25, TextView textView26, ImageView imageView15, View view2, View view3, View view4, View view5, View view6, View view7, View view8, View view9, TextView textView27) {
        super(obj, view, i);
        this.clActivity1 = constraintLayout;
        this.clActivity2 = constraintLayout2;
        this.clActivity3 = constraintLayout3;
        this.clActivity4 = constraintLayout4;
        this.clActivityDetails = constraintLayout5;
        this.clDetails = constraintLayout6;
        this.clHandSpeed1 = constraintLayout7;
        this.clHandSpeed2 = constraintLayout8;
        this.clHandSpeed3 = constraintLayout9;
        this.clProfileDetails = constraintLayout10;
        this.clVitals1 = constraintLayout11;
        this.clVitals2 = constraintLayout12;
        this.clVitals3 = constraintLayout13;
        this.disclaimerInfo = textView;
        this.ivActivity1 = imageView;
        this.ivActivity2 = imageView2;
        this.ivActivity3 = imageView3;
        this.ivActivity4 = imageView4;
        this.ivAppLogo = imageView5;
        this.ivCenterVitalBg = imageView6;
        this.ivHandSpeed1 = imageView7;
        this.ivHandSpeed2 = imageView8;
        this.ivHandSpeed3 = imageView9;
        this.ivPoweredCove = imageView10;
        this.ivSensAIBg = imageView11;
        this.ivVitals1 = imageView12;
        this.ivVitals2 = imageView13;
        this.ivVitals3 = imageView14;
        this.llHandSpeed = linearLayout;
        this.llVitals = linearLayout2;
        this.rootLayout = constraintLayout14;
        this.sensAiShareView = nestedScrollView;
        this.tvActivity = textView2;
        this.tvActivity1 = textView3;
        this.tvActivity1Value = textView4;
        this.tvActivity2 = textView5;
        this.tvActivity2Value = textView6;
        this.tvActivity3 = textView7;
        this.tvActivity3Value = textView8;
        this.tvActivity4 = textView9;
        this.tvActivity4Value = textView10;
        this.tvHandSpeed = textView11;
        this.tvHandSpeed1 = textView12;
        this.tvHandSpeed1Txt = textView13;
        this.tvHandSpeed2 = textView14;
        this.tvHandSpeed2Txt = textView15;
        this.tvHandSpeed3 = textView16;
        this.tvHandSpeed3Txt = textView17;
        this.tvTitle = textView18;
        this.tvVitals = textView19;
        this.tvVitals1 = textView20;
        this.tvVitals1Txt = textView21;
        this.tvVitals2 = textView22;
        this.tvVitals2Txt = textView23;
        this.tvVitals3 = textView24;
        this.tvVitals3Txt = textView25;
        this.userName = textView26;
        this.userPic = imageView15;
        this.view1 = view2;
        this.view2 = view3;
        this.viewActivity = view4;
        this.viewHandSpeed2 = view5;
        this.viewHandSpeed3 = view6;
        this.viewVertical = view7;
        this.viewVitals2 = view8;
        this.viewVitals3 = view9;
        this.week = textView27;
    }

    public static FragmentSensAiShareDataBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentSensAiShareDataBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSensAiShareDataBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentSensAiShareDataBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_sens_ai_share_data);
    }

    @NonNull
    @Deprecated
    public static FragmentSensAiShareDataBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentSensAiShareDataBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sens_ai_share_data, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentSensAiShareDataBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentSensAiShareDataBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentSensAiShareDataBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sens_ai_share_data, null, false, obj);
    }
}
