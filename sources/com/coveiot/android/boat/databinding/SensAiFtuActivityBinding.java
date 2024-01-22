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
public abstract class SensAiFtuActivityBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout container;
    @NonNull
    public final View toolbar;

    public SensAiFtuActivityBinding(Object obj, View view, int i, FrameLayout frameLayout, View view2) {
        super(obj, view, i);
        this.container = frameLayout;
        this.toolbar = view2;
    }

    public static SensAiFtuActivityBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SensAiFtuActivityBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SensAiFtuActivityBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SensAiFtuActivityBinding) ViewDataBinding.bind(obj, view, R.layout.sens_ai_ftu_activity);
    }

    @NonNull
    @Deprecated
    public static SensAiFtuActivityBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SensAiFtuActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_ftu_activity, viewGroup, z, obj);
    }

    @NonNull
    public static SensAiFtuActivityBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SensAiFtuActivityBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SensAiFtuActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_ftu_activity, null, false, obj);
    }
}
