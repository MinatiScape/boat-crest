package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.TroubleshootingModel;
/* loaded from: classes3.dex */
public abstract class ListItemNewDesignTitleBinding extends ViewDataBinding {
    @Bindable
    public TroubleshootingModel mTroubleshootingModel;
    @NonNull
    public final TextView troubleshootTitle;

    public ListItemNewDesignTitleBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.troubleshootTitle = textView;
    }

    public static ListItemNewDesignTitleBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemNewDesignTitleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public TroubleshootingModel getTroubleshootingModel() {
        return this.mTroubleshootingModel;
    }

    public abstract void setTroubleshootingModel(@Nullable TroubleshootingModel troubleshootingModel);

    @Deprecated
    public static ListItemNewDesignTitleBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemNewDesignTitleBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_new_design_title);
    }

    @NonNull
    @Deprecated
    public static ListItemNewDesignTitleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemNewDesignTitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_new_design_title, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemNewDesignTitleBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemNewDesignTitleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemNewDesignTitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_new_design_title, null, false, obj);
    }
}
