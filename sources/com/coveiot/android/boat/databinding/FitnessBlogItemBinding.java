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
public abstract class FitnessBlogItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clFitnessBlog;
    @NonNull
    public final ConstraintLayout clMain;
    @NonNull
    public final ImageView ivBlog;
    @NonNull
    public final TextView tvBlogDate;
    @NonNull
    public final TextView tvBlogDuration;
    @NonNull
    public final TextView tvBlogTitle;
    @NonNull
    public final TextView tvBlogType;

    public FitnessBlogItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.clFitnessBlog = constraintLayout;
        this.clMain = constraintLayout2;
        this.ivBlog = imageView;
        this.tvBlogDate = textView;
        this.tvBlogDuration = textView2;
        this.tvBlogTitle = textView3;
        this.tvBlogType = textView4;
    }

    public static FitnessBlogItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FitnessBlogItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FitnessBlogItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FitnessBlogItemBinding) ViewDataBinding.bind(obj, view, R.layout.fitness_blog_item);
    }

    @NonNull
    @Deprecated
    public static FitnessBlogItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FitnessBlogItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_blog_item, viewGroup, z, obj);
    }

    @NonNull
    public static FitnessBlogItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FitnessBlogItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FitnessBlogItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_blog_item, null, false, obj);
    }
}
