package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.models.ProfileMoreModel;
/* loaded from: classes3.dex */
public abstract class ProfileMoreItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivArrow;
    @NonNull
    public final ImageView ivMore;
    @NonNull
    public final View listItemDivider;
    @Bindable
    public ProfileMoreModel mMoreData;
    @NonNull
    public final TextView tvMore;

    public ProfileMoreItemBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, View view2, TextView textView) {
        super(obj, view, i);
        this.ivArrow = imageView;
        this.ivMore = imageView2;
        this.listItemDivider = view2;
        this.tvMore = textView;
    }

    public static ProfileMoreItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ProfileMoreItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public ProfileMoreModel getMoreData() {
        return this.mMoreData;
    }

    public abstract void setMoreData(@Nullable ProfileMoreModel profileMoreModel);

    @Deprecated
    public static ProfileMoreItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ProfileMoreItemBinding) ViewDataBinding.bind(obj, view, R.layout.profile_more_item);
    }

    @NonNull
    @Deprecated
    public static ProfileMoreItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ProfileMoreItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_more_item, viewGroup, z, obj);
    }

    @NonNull
    public static ProfileMoreItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ProfileMoreItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ProfileMoreItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_more_item, null, false, obj);
    }
}
