package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class SensAiProfileItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivProfile;
    @NonNull
    public final TextView tvAdd;
    @NonNull
    public final TextView tvProfile;

    public SensAiProfileItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ivProfile = imageView;
        this.tvAdd = textView;
        this.tvProfile = textView2;
    }

    public static SensAiProfileItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SensAiProfileItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SensAiProfileItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SensAiProfileItemBinding) ViewDataBinding.bind(obj, view, R.layout.sens_ai_profile_item);
    }

    @NonNull
    @Deprecated
    public static SensAiProfileItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SensAiProfileItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_profile_item, viewGroup, z, obj);
    }

    @NonNull
    public static SensAiProfileItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SensAiProfileItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SensAiProfileItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_profile_item, null, false, obj);
    }
}
