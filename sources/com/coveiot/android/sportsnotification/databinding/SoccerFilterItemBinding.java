package com.coveiot.android.sportsnotification.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.sportsnotification.R;
/* loaded from: classes7.dex */
public abstract class SoccerFilterItemBinding extends ViewDataBinding {
    @NonNull
    public final TextView filterName;
    @NonNull
    public final View selectedDiv;

    public SoccerFilterItemBinding(Object obj, View view, int i, TextView textView, View view2) {
        super(obj, view, i);
        this.filterName = textView;
        this.selectedDiv = view2;
    }

    public static SoccerFilterItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SoccerFilterItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SoccerFilterItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SoccerFilterItemBinding) ViewDataBinding.bind(obj, view, R.layout.soccer_filter_item);
    }

    @NonNull
    @Deprecated
    public static SoccerFilterItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SoccerFilterItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.soccer_filter_item, viewGroup, z, obj);
    }

    @NonNull
    public static SoccerFilterItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SoccerFilterItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SoccerFilterItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.soccer_filter_item, null, false, obj);
    }
}
