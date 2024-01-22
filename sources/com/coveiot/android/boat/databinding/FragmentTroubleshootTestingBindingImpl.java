package com.coveiot.android.boat.databinding;

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
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class FragmentTroubleshootTestingBindingImpl extends FragmentTroubleshootTestingBinding {
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
        sparseIntArray.put(R.id.rcv_troubleshoot_items, 2);
        sparseIntArray.put(R.id.trouble_shoot_send_layout, 3);
        sparseIntArray.put(R.id.send_tv_title_layout, 4);
        sparseIntArray.put(R.id.send_tv_no, 5);
        sparseIntArray.put(R.id.troubleshoot_send_tv, 6);
        sparseIntArray.put(R.id.test_ok_btn, 7);
        sparseIntArray.put(R.id.trouble_shoot_send_inst_tv, 8);
        sparseIntArray.put(R.id.troubleshoot_fail_tv, 9);
        sparseIntArray.put(R.id.ivPoweredBy, 10);
    }

    public FragmentTroubleshootTestingBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
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

    public FragmentTroubleshootTestingBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[10], (RecyclerView) objArr[2], (TextView) objArr[5], (ConstraintLayout) objArr[4], (Button) objArr[7], (View) objArr[1], (TextView) objArr[8], (ConstraintLayout) objArr[3], (TextView) objArr[9], (TextView) objArr[6]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
