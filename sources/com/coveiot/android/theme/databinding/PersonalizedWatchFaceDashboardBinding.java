package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class PersonalizedWatchFaceDashboardBinding extends ViewDataBinding {
    @NonNull
    public final TextView tvChangeYourWatchFace;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final View view;

    public PersonalizedWatchFaceDashboardBinding(Object obj, View view, int i, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.tvChangeYourWatchFace = textView;
        this.tvHeader = textView2;
        this.view = view2;
    }

    public static PersonalizedWatchFaceDashboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static PersonalizedWatchFaceDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PersonalizedWatchFaceDashboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PersonalizedWatchFaceDashboardBinding) ViewDataBinding.bind(obj, view, R.layout.personalized_watch_face_dashboard);
    }

    @NonNull
    @Deprecated
    public static PersonalizedWatchFaceDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (PersonalizedWatchFaceDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.personalized_watch_face_dashboard, viewGroup, z, obj);
    }

    @NonNull
    public static PersonalizedWatchFaceDashboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static PersonalizedWatchFaceDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (PersonalizedWatchFaceDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.personalized_watch_face_dashboard, null, false, obj);
    }
}
