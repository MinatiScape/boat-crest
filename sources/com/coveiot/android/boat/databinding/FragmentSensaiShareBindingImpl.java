package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class FragmentSensaiShareBindingImpl extends FragmentSensaiShareBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final NestedScrollView h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.root_layout, 1);
        sparseIntArray.put(R.id.iv_app_logo, 2);
        sparseIntArray.put(R.id.iv_powered_cove, 3);
        sparseIntArray.put(R.id.guideline1, 4);
        sparseIntArray.put(R.id.title_text, 5);
        sparseIntArray.put(R.id.guideline3, 6);
        sparseIntArray.put(R.id.image_layout, 7);
        sparseIntArray.put(R.id.img_activity, 8);
        sparseIntArray.put(R.id.type_text, 9);
        sparseIntArray.put(R.id.date_time_text, 10);
        sparseIntArray.put(R.id.contentRecyclerView, 11);
        sparseIntArray.put(R.id.clProfile, 12);
        sparseIntArray.put(R.id.img_profile, 13);
        sparseIntArray.put(R.id.tv_name, 14);
        sparseIntArray.put(R.id.disclaimer_info, 15);
    }

    public FragmentSensaiShareBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 16, j, k));
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

    public FragmentSensaiShareBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[12], (RecyclerView) objArr[11], (TextView) objArr[10], (TextView) objArr[15], (Guideline) objArr[4], (Guideline) objArr[6], (LinearLayout) objArr[7], (ImageView) objArr[8], (ImageView) objArr[13], (ImageView) objArr[2], (ImageView) objArr[3], (ConstraintLayout) objArr[1], (TextView) objArr[5], (TextView) objArr[14], (TextView) objArr[9]);
        this.i = -1L;
        NestedScrollView nestedScrollView = (NestedScrollView) objArr[0];
        this.h = nestedScrollView;
        nestedScrollView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
