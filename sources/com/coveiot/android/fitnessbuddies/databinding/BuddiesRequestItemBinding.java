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
import com.coveiot.android.fitnessbuddies.R;
/* loaded from: classes4.dex */
public abstract class BuddiesRequestItemBinding extends ViewDataBinding {
    @NonNull
    public final Button btnAccept;
    @NonNull
    public final Button btnDecline;
    @NonNull
    public final ConstraintLayout clProfileDetails;
    @NonNull
    public final ImageView ivProfile;
    @NonNull
    public final TextView tvBadge;
    @NonNull
    public final TextView tvDate;
    @NonNull
    public final TextView tvName;
    @NonNull
    public final TextView tvRank;
    @NonNull
    public final TextView tvRequestsContent;
    @NonNull
    public final View view1;

    public BuddiesRequestItemBinding(Object obj, View view, int i, Button button, Button button2, ConstraintLayout constraintLayout, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, View view2) {
        super(obj, view, i);
        this.btnAccept = button;
        this.btnDecline = button2;
        this.clProfileDetails = constraintLayout;
        this.ivProfile = imageView;
        this.tvBadge = textView;
        this.tvDate = textView2;
        this.tvName = textView3;
        this.tvRank = textView4;
        this.tvRequestsContent = textView5;
        this.view1 = view2;
    }

    public static BuddiesRequestItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static BuddiesRequestItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BuddiesRequestItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (BuddiesRequestItemBinding) ViewDataBinding.bind(obj, view, R.layout.buddies_request_item);
    }

    @NonNull
    @Deprecated
    public static BuddiesRequestItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (BuddiesRequestItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.buddies_request_item, viewGroup, z, obj);
    }

    @NonNull
    public static BuddiesRequestItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BuddiesRequestItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (BuddiesRequestItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.buddies_request_item, null, false, obj);
    }
}
