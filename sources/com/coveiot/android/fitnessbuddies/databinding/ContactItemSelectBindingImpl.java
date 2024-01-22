package com.coveiot.android.fitnessbuddies.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnessbuddies.R;
/* loaded from: classes4.dex */
public class ContactItemSelectBindingImpl extends ContactItemSelectBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.buddies_display_pic, 1);
        sparseIntArray.put(R.id.display_name, 2);
        sparseIntArray.put(R.id.display_number, 3);
        sparseIntArray.put(R.id.clEnd, 4);
        sparseIntArray.put(R.id.buddies_text, 5);
        sparseIntArray.put(R.id.buddies_icon, 6);
        sparseIntArray.put(R.id.tv_buddies_invite, 7);
    }

    public ContactItemSelectBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 8, i, j));
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
            this.h = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }

    public ContactItemSelectBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[1], (ImageView) objArr[6], (TextView) objArr[5], (ConstraintLayout) objArr[4], (TextView) objArr[2], (TextView) objArr[3], (RelativeLayout) objArr[0], (TextView) objArr[7]);
        this.h = -1L;
        this.rootLayoutGeneric.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
