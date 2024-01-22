package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityTroubleshootingNewBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout tfragmentContainer;

    public ActivityTroubleshootingNewBinding(Object obj, View view, int i, FrameLayout frameLayout) {
        super(obj, view, i);
        this.tfragmentContainer = frameLayout;
    }

    public static ActivityTroubleshootingNewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityTroubleshootingNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTroubleshootingNewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityTroubleshootingNewBinding) ViewDataBinding.bind(obj, view, R.layout.activity_troubleshooting_new);
    }

    @NonNull
    @Deprecated
    public static ActivityTroubleshootingNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityTroubleshootingNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_troubleshooting_new, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityTroubleshootingNewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityTroubleshootingNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityTroubleshootingNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_troubleshooting_new, null, false, obj);
    }
}
