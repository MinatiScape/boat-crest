package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class RankFilterItemBinding extends ViewDataBinding {
    @NonNull
    public final TextView tvFilter;

    public RankFilterItemBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.tvFilter = textView;
    }

    public static RankFilterItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RankFilterItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RankFilterItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (RankFilterItemBinding) ViewDataBinding.bind(obj, view, R.layout.rank_filter_item);
    }

    @NonNull
    @Deprecated
    public static RankFilterItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (RankFilterItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.rank_filter_item, viewGroup, z, obj);
    }

    @NonNull
    public static RankFilterItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static RankFilterItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (RankFilterItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.rank_filter_item, null, false, obj);
    }
}
