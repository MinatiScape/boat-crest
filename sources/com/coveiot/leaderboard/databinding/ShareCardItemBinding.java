package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class ShareCardItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView badgeIV;
    @NonNull
    public final TextView badgeName;
    @NonNull
    public final LinearLayout baseLayout;

    public ShareCardItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView, LinearLayout linearLayout) {
        super(obj, view, i);
        this.badgeIV = imageView;
        this.badgeName = textView;
        this.baseLayout = linearLayout;
    }

    public static ShareCardItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ShareCardItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ShareCardItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ShareCardItemBinding) ViewDataBinding.bind(obj, view, R.layout.share_card_item);
    }

    @NonNull
    @Deprecated
    public static ShareCardItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ShareCardItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.share_card_item, viewGroup, z, obj);
    }

    @NonNull
    public static ShareCardItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ShareCardItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ShareCardItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.share_card_item, null, false, obj);
    }
}
