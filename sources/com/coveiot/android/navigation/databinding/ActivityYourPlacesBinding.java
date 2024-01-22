package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class ActivityYourPlacesBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView rvYourPlaces;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvYourPlacesLabel;
    @NonNull
    public final TextView tvYourPlacesLabelInstructions;

    public ActivityYourPlacesBinding(Object obj, View view, int i, RecyclerView recyclerView, View view2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.rvYourPlaces = recyclerView;
        this.toolbar = view2;
        this.tvYourPlacesLabel = textView;
        this.tvYourPlacesLabelInstructions = textView2;
    }

    public static ActivityYourPlacesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityYourPlacesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityYourPlacesBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityYourPlacesBinding) ViewDataBinding.bind(obj, view, R.layout.activity_your_places);
    }

    @NonNull
    @Deprecated
    public static ActivityYourPlacesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityYourPlacesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_your_places, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityYourPlacesBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityYourPlacesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityYourPlacesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_your_places, null, false, obj);
    }
}
