package com.coveiot.android.fitnessbuddies.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnessbuddies.R;
/* loaded from: classes4.dex */
public class BuddiesRequestItemBindingImpl extends BuddiesRequestItemBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.cl_profile_details, 1);
        sparseIntArray.put(R.id.iv_profile, 2);
        sparseIntArray.put(R.id.tv_name, 3);
        sparseIntArray.put(R.id.tv_rank, 4);
        sparseIntArray.put(R.id.tv_badge, 5);
        sparseIntArray.put(R.id.tv_date, 6);
        sparseIntArray.put(R.id.view1, 7);
        sparseIntArray.put(R.id.tv_requests_content, 8);
        sparseIntArray.put(R.id.btn_decline, 9);
        sparseIntArray.put(R.id.btn_accept, 10);
    }

    public BuddiesRequestItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 11, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.i = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.i != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public BuddiesRequestItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[10], (Button) objArr[9], (ConstraintLayout) objArr[1], (ImageView) objArr[2], (TextView) objArr[5], (TextView) objArr[6], (TextView) objArr[3], (TextView) objArr[4], (TextView) objArr[8], (View) objArr[7]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
