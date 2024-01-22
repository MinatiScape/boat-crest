package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityBoatCoinsFtuBinding extends ViewDataBinding {
    @NonNull
    public final Button btnBoatCoinsNext;
    @NonNull
    public final ConstraintLayout constraintLayoutBottom;
    @NonNull
    public final Guideline guideline26;
    @NonNull
    public final Guideline guidelineBottom;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvBoatCoinSkip;
    @NonNull
    public final ViewPager viewpagerBoatcoins;

    public ActivityBoatCoinsFtuBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, Guideline guideline, Guideline guideline2, View view2, TextView textView, ViewPager viewPager) {
        super(obj, view, i);
        this.btnBoatCoinsNext = button;
        this.constraintLayoutBottom = constraintLayout;
        this.guideline26 = guideline;
        this.guidelineBottom = guideline2;
        this.toolbar = view2;
        this.tvBoatCoinSkip = textView;
        this.viewpagerBoatcoins = viewPager;
    }

    public static ActivityBoatCoinsFtuBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBoatCoinsFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBoatCoinsFtuBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityBoatCoinsFtuBinding) ViewDataBinding.bind(obj, view, R.layout.activity_boat_coins_ftu);
    }

    @NonNull
    @Deprecated
    public static ActivityBoatCoinsFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityBoatCoinsFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_boat_coins_ftu, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityBoatCoinsFtuBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityBoatCoinsFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityBoatCoinsFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_boat_coins_ftu, null, false, obj);
    }
}
