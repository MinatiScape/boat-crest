package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.covepreferences.data.AppNotificationData;
/* loaded from: classes3.dex */
public class TroubleshootActivityNotiItemBindingImpl extends TroubleshootActivityNotiItemBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.noti_item_icon, 1);
        sparseIntArray.put(R.id.noti_item_tv_content, 2);
        sparseIntArray.put(R.id.noti_item_ImgV, 3);
        sparseIntArray.put(R.id.divider, 4);
    }

    public TroubleshootActivityNotiItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, i, j));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.h = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.h != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.h = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // com.coveiot.android.boat.databinding.TroubleshootActivityNotiItemBinding
    public void setAppNotificationData(@Nullable AppNotificationData appNotificationData) {
        this.mAppNotificationData = appNotificationData;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        if (6 == i2) {
            setAppNotificationData((AppNotificationData) obj);
            return true;
        }
        return false;
    }

    public TroubleshootActivityNotiItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[0], (View) objArr[4], (ImageView) objArr[1], (ImageView) objArr[3], (TextView) objArr[2]);
        this.h = -1L;
        this.clMainItem.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
