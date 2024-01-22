package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ListItemAdditionalActivitiesBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clSelect;
    @NonNull
    public final ConstraintLayout clSelect2;
    @NonNull
    public final ImageView ivActivityIcon;
    @NonNull
    public final TextView tvActivityName;

    public ListItemAdditionalActivitiesBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.clSelect = constraintLayout;
        this.clSelect2 = constraintLayout2;
        this.ivActivityIcon = imageView;
        this.tvActivityName = textView;
    }

    public static ListItemAdditionalActivitiesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemAdditionalActivitiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ListItemAdditionalActivitiesBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemAdditionalActivitiesBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_additional_activities);
    }

    @NonNull
    @Deprecated
    public static ListItemAdditionalActivitiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemAdditionalActivitiesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_additional_activities, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemAdditionalActivitiesBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemAdditionalActivitiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemAdditionalActivitiesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_additional_activities, null, false, obj);
    }
}
