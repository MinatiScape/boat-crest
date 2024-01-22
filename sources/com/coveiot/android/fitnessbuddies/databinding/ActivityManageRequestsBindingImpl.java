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
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
/* loaded from: classes4.dex */
public class ActivityManageRequestsBindingImpl extends ActivityManageRequestsBinding {
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
        sparseIntArray.put(R.id.toolbar, 1);
        sparseIntArray.put(R.id.cl_requests, 2);
        sparseIntArray.put(R.id.cl_requests_received, 3);
        sparseIntArray.put(R.id.tv_requests_received, 4);
        sparseIntArray.put(R.id.rv_requests_received, 5);
        sparseIntArray.put(R.id.cl_requests_sent, 6);
        sparseIntArray.put(R.id.tv_requests_sent, 7);
        sparseIntArray.put(R.id.rv_requests_sent, 8);
        sparseIntArray.put(R.id.emptyLayout, 9);
        sparseIntArray.put(R.id.empty_image, 10);
        sparseIntArray.put(R.id.no_msg_txt, 11);
        sparseIntArray.put(R.id.add_buddies_message, 12);
    }

    public ActivityManageRequestsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 13, j, k));
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

    public ActivityManageRequestsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[12], (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[6], (ImageView) objArr[10], (ConstraintLayout) objArr[9], (TextView) objArr[11], (RecyclerView) objArr[5], (RecyclerView) objArr[8], (View) objArr[1], (TextView) objArr[4], (TextView) objArr[7]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
