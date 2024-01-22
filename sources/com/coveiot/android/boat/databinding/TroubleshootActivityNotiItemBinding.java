package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.covepreferences.data.AppNotificationData;
/* loaded from: classes3.dex */
public abstract class TroubleshootActivityNotiItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clMainItem;
    @NonNull
    public final View divider;
    @Bindable
    public AppNotificationData mAppNotificationData;
    @NonNull
    public final ImageView notiItemIcon;
    @NonNull
    public final ImageView notiItemImgV;
    @NonNull
    public final TextView notiItemTvContent;

    public TroubleshootActivityNotiItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, View view2, ImageView imageView, ImageView imageView2, TextView textView) {
        super(obj, view, i);
        this.clMainItem = constraintLayout;
        this.divider = view2;
        this.notiItemIcon = imageView;
        this.notiItemImgV = imageView2;
        this.notiItemTvContent = textView;
    }

    public static TroubleshootActivityNotiItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static TroubleshootActivityNotiItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public AppNotificationData getAppNotificationData() {
        return this.mAppNotificationData;
    }

    public abstract void setAppNotificationData(@Nullable AppNotificationData appNotificationData);

    @Deprecated
    public static TroubleshootActivityNotiItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (TroubleshootActivityNotiItemBinding) ViewDataBinding.bind(obj, view, R.layout.troubleshoot_activity_noti_item);
    }

    @NonNull
    @Deprecated
    public static TroubleshootActivityNotiItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (TroubleshootActivityNotiItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.troubleshoot_activity_noti_item, viewGroup, z, obj);
    }

    @NonNull
    public static TroubleshootActivityNotiItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static TroubleshootActivityNotiItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (TroubleshootActivityNotiItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.troubleshoot_activity_noti_item, null, false, obj);
    }
}
