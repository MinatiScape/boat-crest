package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityAdditionalActivitiesBinding extends ViewDataBinding {
    @NonNull
    public final Button btnDone;
    @NonNull
    public final RecyclerView rvAdditionalActivities;
    @NonNull
    public final View toolbar;

    public ActivityAdditionalActivitiesBinding(Object obj, View view, int i, Button button, RecyclerView recyclerView, View view2) {
        super(obj, view, i);
        this.btnDone = button;
        this.rvAdditionalActivities = recyclerView;
        this.toolbar = view2;
    }

    public static ActivityAdditionalActivitiesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAdditionalActivitiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAdditionalActivitiesBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityAdditionalActivitiesBinding) ViewDataBinding.bind(obj, view, R.layout.activity_additional_activities);
    }

    @NonNull
    @Deprecated
    public static ActivityAdditionalActivitiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityAdditionalActivitiesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_additional_activities, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityAdditionalActivitiesBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityAdditionalActivitiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityAdditionalActivitiesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_additional_activities, null, false, obj);
    }
}
