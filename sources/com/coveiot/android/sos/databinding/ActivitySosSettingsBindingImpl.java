package com.coveiot.android.sos.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.sos.R;
/* loaded from: classes7.dex */
public class ActivitySosSettingsBindingImpl extends ActivitySosSettingsBinding {
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
        sparseIntArray.put(R.id.clEmptyLayout, 2);
        sparseIntArray.put(R.id.tvSOSInfo, 3);
        sparseIntArray.put(R.id.tvTNC, 4);
        sparseIntArray.put(R.id.btnAddEmergencyContact, 5);
        sparseIntArray.put(R.id.clNoContacts, 6);
        sparseIntArray.put(R.id.tvNoContacts, 7);
        sparseIntArray.put(R.id.clContacts, 8);
        sparseIntArray.put(R.id.tvChooseContact, 9);
        sparseIntArray.put(R.id.search_layout, 10);
        sparseIntArray.put(R.id.search, 11);
        sparseIntArray.put(R.id.rvActiveContactsList, 12);
        sparseIntArray.put(R.id.btnSaveContact, 13);
    }

    public ActivitySosSettingsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 14, j, k));
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

    public ActivitySosSettingsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[5], (Button) objArr[13], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[6], (RecyclerView) objArr[12], (SearchView) objArr[11], (LinearLayout) objArr[10], (View) objArr[1], (TextView) objArr[9], (TextView) objArr[7], (TextView) objArr[3], (TextView) objArr[4]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
