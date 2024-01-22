package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class ActivityDirectionsBinding extends ViewDataBinding {
    @NonNull
    public final Button btnExit;
    @NonNull
    public final Button btnNavigationOngoing;
    @NonNull
    public final ConstraintLayout clAddressDetails;
    @NonNull
    public final ImageView ivSetting;
    @NonNull
    public final LinearLayout llDirectionsDistanceArrival;
    @NonNull
    public final RecyclerView rvDirection;
    @NonNull
    public final TextView tvDirectionTitle;
    @NonNull
    public final TextView tvEta;
    @NonNull
    public final TextView tvTotalDistance;
    @NonNull
    public final TextView tvTotalDuration;

    public ActivityDirectionsBinding(Object obj, View view, int i, Button button, Button button2, ConstraintLayout constraintLayout, ImageView imageView, LinearLayout linearLayout, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.btnExit = button;
        this.btnNavigationOngoing = button2;
        this.clAddressDetails = constraintLayout;
        this.ivSetting = imageView;
        this.llDirectionsDistanceArrival = linearLayout;
        this.rvDirection = recyclerView;
        this.tvDirectionTitle = textView;
        this.tvEta = textView2;
        this.tvTotalDistance = textView3;
        this.tvTotalDuration = textView4;
    }

    public static ActivityDirectionsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDirectionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDirectionsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityDirectionsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_directions);
    }

    @NonNull
    @Deprecated
    public static ActivityDirectionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityDirectionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_directions, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityDirectionsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityDirectionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityDirectionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_directions, null, false, obj);
    }
}
