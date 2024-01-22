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
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.TroubleshootingTestModel;
import com.coveiot.android.theme.compundview.DottedCircleProgressBarCustom;
/* loaded from: classes3.dex */
public abstract class ListItemTroubleshootTestingBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clAppNotificationList;
    @NonNull
    public final ConstraintLayout clMainLayout;
    @NonNull
    public final ConstraintLayout clTroubleShootProgress;
    @NonNull
    public final ConstraintLayout clTroubleshootProgress;
    @NonNull
    public final TextView fixThisTv;
    @Bindable
    public TroubleshootingTestModel mTroubleshootingTestModel;
    @NonNull
    public final RecyclerView notificationSettingList;
    @NonNull
    public final TextView troubleShootFailureMsg;
    @NonNull
    public final TextView troubleShootItemNo;
    @NonNull
    public final ImageView troubleShootProgressImg;
    @NonNull
    public final TextView troubleShootProgressTv;
    @NonNull
    public final DottedCircleProgressBarCustom troubleShootProgressV;
    @NonNull
    public final TextView troubleShootTitleTv;

    public ListItemTroubleshootTestingBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, TextView textView, RecyclerView recyclerView, TextView textView2, TextView textView3, ImageView imageView, TextView textView4, DottedCircleProgressBarCustom dottedCircleProgressBarCustom, TextView textView5) {
        super(obj, view, i);
        this.clAppNotificationList = constraintLayout;
        this.clMainLayout = constraintLayout2;
        this.clTroubleShootProgress = constraintLayout3;
        this.clTroubleshootProgress = constraintLayout4;
        this.fixThisTv = textView;
        this.notificationSettingList = recyclerView;
        this.troubleShootFailureMsg = textView2;
        this.troubleShootItemNo = textView3;
        this.troubleShootProgressImg = imageView;
        this.troubleShootProgressTv = textView4;
        this.troubleShootProgressV = dottedCircleProgressBarCustom;
        this.troubleShootTitleTv = textView5;
    }

    public static ListItemTroubleshootTestingBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemTroubleshootTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public TroubleshootingTestModel getTroubleshootingTestModel() {
        return this.mTroubleshootingTestModel;
    }

    public abstract void setTroubleshootingTestModel(@Nullable TroubleshootingTestModel troubleshootingTestModel);

    @Deprecated
    public static ListItemTroubleshootTestingBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemTroubleshootTestingBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_troubleshoot_testing);
    }

    @NonNull
    @Deprecated
    public static ListItemTroubleshootTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemTroubleshootTestingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_troubleshoot_testing, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemTroubleshootTestingBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemTroubleshootTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemTroubleshootTestingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_troubleshoot_testing, null, false, obj);
    }
}
