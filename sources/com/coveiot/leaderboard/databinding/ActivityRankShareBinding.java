package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class ActivityRankShareBinding extends ViewDataBinding {
    @NonNull
    public final RelativeLayout containerLayout;
    @NonNull
    public final Button shareButton;
    @NonNull
    public final ImageView shareCloseImage;
    @NonNull
    public final LinearLayout shareCloseLayout;
    @NonNull
    public final LinearLayout shareLayout;

    public ActivityRankShareBinding(Object obj, View view, int i, RelativeLayout relativeLayout, Button button, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2) {
        super(obj, view, i);
        this.containerLayout = relativeLayout;
        this.shareButton = button;
        this.shareCloseImage = imageView;
        this.shareCloseLayout = linearLayout;
        this.shareLayout = linearLayout2;
    }

    public static ActivityRankShareBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRankShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRankShareBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityRankShareBinding) ViewDataBinding.bind(obj, view, R.layout.activity_rank_share);
    }

    @NonNull
    @Deprecated
    public static ActivityRankShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityRankShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_rank_share, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityRankShareBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityRankShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityRankShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_rank_share, null, false, obj);
    }
}
