package com.coveiot.android.theme.databinding;

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
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ListItemImageTitleSubtitleBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clRoot;
    @NonNull
    public final ImageView ivRightArrow;
    @NonNull
    public final ImageView settingsIcon;
    @NonNull
    public final TextView tvDesc;
    @NonNull
    public final TextView tvTitle;

    public ListItemImageTitleSubtitleBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.clRoot = constraintLayout;
        this.ivRightArrow = imageView;
        this.settingsIcon = imageView2;
        this.tvDesc = textView;
        this.tvTitle = textView2;
    }

    public static ListItemImageTitleSubtitleBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemImageTitleSubtitleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ListItemImageTitleSubtitleBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemImageTitleSubtitleBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_image_title_subtitle);
    }

    @NonNull
    @Deprecated
    public static ListItemImageTitleSubtitleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemImageTitleSubtitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_image_title_subtitle, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemImageTitleSubtitleBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemImageTitleSubtitleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemImageTitleSubtitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_image_title_subtitle, null, false, obj);
    }
}
