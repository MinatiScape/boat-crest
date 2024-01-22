package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ItemPermssionsRequiredBinding extends ViewDataBinding {
    @NonNull
    public final TextView textviewPermissionsDescription;
    @NonNull
    public final TextView textviewPermissionsTitle;

    public ItemPermssionsRequiredBinding(Object obj, View view, int i, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.textviewPermissionsDescription = textView;
        this.textviewPermissionsTitle = textView2;
    }

    public static ItemPermssionsRequiredBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemPermssionsRequiredBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPermssionsRequiredBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemPermssionsRequiredBinding) ViewDataBinding.bind(obj, view, R.layout.item_permssions_required);
    }

    @NonNull
    @Deprecated
    public static ItemPermssionsRequiredBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemPermssionsRequiredBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_permssions_required, viewGroup, z, obj);
    }

    @NonNull
    public static ItemPermssionsRequiredBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemPermssionsRequiredBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemPermssionsRequiredBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_permssions_required, null, false, obj);
    }
}
