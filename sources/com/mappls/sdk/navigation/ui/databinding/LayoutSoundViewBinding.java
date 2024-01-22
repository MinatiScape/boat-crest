package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public abstract class LayoutSoundViewBinding extends ViewDataBinding {
    @NonNull
    public final ImageView soundBtn;
    @NonNull
    public final TextView soundText;

    public LayoutSoundViewBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.soundBtn = imageView;
        this.soundText = textView;
    }

    public static LayoutSoundViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutSoundViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutSoundViewBinding) ViewDataBinding.bind(obj, view, R.layout.layout_sound_view);
    }

    @NonNull
    public static LayoutSoundViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutSoundViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutSoundViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutSoundViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_sound_view, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutSoundViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutSoundViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_sound_view, null, false, obj);
    }
}
