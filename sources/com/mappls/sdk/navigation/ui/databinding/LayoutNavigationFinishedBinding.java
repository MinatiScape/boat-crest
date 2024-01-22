package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public abstract class LayoutNavigationFinishedBinding extends ViewDataBinding {
    @NonNull
    public final LinearLayout containerButtons;
    @NonNull
    public final LinearLayout containerDestinationReached;
    @NonNull
    public final TextView destinationTextView;
    @NonNull
    public final ImageButton finishedButton;
    @NonNull
    public final TextView knowMoreButton;
    @NonNull
    public final RelativeLayout navigationFinishedLayout;
    @NonNull
    public final TextView okButton;
    @NonNull
    public final ImageButton okayButton;
    @NonNull
    public final RecyclerView tripStatsRecyclerView;
    @NonNull
    public final TextView tripStatsTextView;
    @NonNull
    public final View view;
    @NonNull
    public final TextView welcomeToAPlaceTextView;

    public LayoutNavigationFinishedBinding(Object obj, View view, int i, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, ImageButton imageButton, TextView textView2, RelativeLayout relativeLayout, TextView textView3, ImageButton imageButton2, RecyclerView recyclerView, TextView textView4, View view2, TextView textView5) {
        super(obj, view, i);
        this.containerButtons = linearLayout;
        this.containerDestinationReached = linearLayout2;
        this.destinationTextView = textView;
        this.finishedButton = imageButton;
        this.knowMoreButton = textView2;
        this.navigationFinishedLayout = relativeLayout;
        this.okButton = textView3;
        this.okayButton = imageButton2;
        this.tripStatsRecyclerView = recyclerView;
        this.tripStatsTextView = textView4;
        this.view = view2;
        this.welcomeToAPlaceTextView = textView5;
    }

    public static LayoutNavigationFinishedBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutNavigationFinishedBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutNavigationFinishedBinding) ViewDataBinding.bind(obj, view, R.layout.layout_navigation_finished);
    }

    @NonNull
    public static LayoutNavigationFinishedBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutNavigationFinishedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutNavigationFinishedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutNavigationFinishedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_navigation_finished, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutNavigationFinishedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutNavigationFinishedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_navigation_finished, null, false, obj);
    }
}
