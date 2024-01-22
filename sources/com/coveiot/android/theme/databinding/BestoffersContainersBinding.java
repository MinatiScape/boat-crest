package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class BestoffersContainersBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView rcvBestOffers;

    public BestoffersContainersBinding(Object obj, View view, int i, RecyclerView recyclerView) {
        super(obj, view, i);
        this.rcvBestOffers = recyclerView;
    }

    public static BestoffersContainersBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static BestoffersContainersBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BestoffersContainersBinding bind(@NonNull View view, @Nullable Object obj) {
        return (BestoffersContainersBinding) ViewDataBinding.bind(obj, view, R.layout.bestoffers_containers);
    }

    @NonNull
    @Deprecated
    public static BestoffersContainersBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (BestoffersContainersBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bestoffers_containers, viewGroup, z, obj);
    }

    @NonNull
    public static BestoffersContainersBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BestoffersContainersBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (BestoffersContainersBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bestoffers_containers, null, false, obj);
    }
}
