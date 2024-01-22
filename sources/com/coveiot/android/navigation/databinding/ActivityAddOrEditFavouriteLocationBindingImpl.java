package com.coveiot.android.navigation.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public class ActivityAddOrEditFavouriteLocationBindingImpl extends ActivityAddOrEditFavouriteLocationBinding {
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
        sparseIntArray.put(R.id.tvFavouritePlaceTitle, 2);
        sparseIntArray.put(R.id.ivEditFavouritePlaceTitle, 3);
        sparseIntArray.put(R.id.clSearchLayout, 4);
        sparseIntArray.put(R.id.ivSearchIcon, 5);
        sparseIntArray.put(R.id.etFavouritePlaceAddress, 6);
        sparseIntArray.put(R.id.ivClearIcon, 7);
        sparseIntArray.put(R.id.rvFavouritePlaces, 8);
        sparseIntArray.put(R.id.btnSave, 9);
    }

    public ActivityAddOrEditFavouriteLocationBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 10, j, k));
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

    public ActivityAddOrEditFavouriteLocationBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[9], (ConstraintLayout) objArr[4], (EditText) objArr[6], (ImageView) objArr[7], (ImageView) objArr[3], (ImageView) objArr[5], (RecyclerView) objArr[8], (View) objArr[1], (EditText) objArr[2]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
