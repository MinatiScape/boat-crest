package com.coveiot.android.fitnessbuddies.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
/* loaded from: classes4.dex */
public abstract class ActivityManageRequestsBinding extends ViewDataBinding {
    @NonNull
    public final Button addBuddiesMessage;
    @NonNull
    public final ConstraintLayout clRequests;
    @NonNull
    public final ConstraintLayout clRequestsReceived;
    @NonNull
    public final ConstraintLayout clRequestsSent;
    @NonNull
    public final ImageView emptyImage;
    @NonNull
    public final ConstraintLayout emptyLayout;
    @NonNull
    public final TextView noMsgTxt;
    @NonNull
    public final RecyclerView rvRequestsReceived;
    @NonNull
    public final RecyclerView rvRequestsSent;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvRequestsReceived;
    @NonNull
    public final TextView tvRequestsSent;

    public ActivityManageRequestsBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ImageView imageView, ConstraintLayout constraintLayout4, TextView textView, RecyclerView recyclerView, RecyclerView recyclerView2, View view2, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.addBuddiesMessage = button;
        this.clRequests = constraintLayout;
        this.clRequestsReceived = constraintLayout2;
        this.clRequestsSent = constraintLayout3;
        this.emptyImage = imageView;
        this.emptyLayout = constraintLayout4;
        this.noMsgTxt = textView;
        this.rvRequestsReceived = recyclerView;
        this.rvRequestsSent = recyclerView2;
        this.toolbar = view2;
        this.tvRequestsReceived = textView2;
        this.tvRequestsSent = textView3;
    }

    public static ActivityManageRequestsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityManageRequestsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityManageRequestsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityManageRequestsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_manage_requests);
    }

    @NonNull
    @Deprecated
    public static ActivityManageRequestsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityManageRequestsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_manage_requests, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityManageRequestsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityManageRequestsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityManageRequestsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_manage_requests, null, false, obj);
    }
}
