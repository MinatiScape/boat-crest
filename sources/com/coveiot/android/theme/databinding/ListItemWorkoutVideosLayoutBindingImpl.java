package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class ListItemWorkoutVideosLayoutBindingImpl extends ListItemWorkoutVideosLayoutBinding {
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
        sparseIntArray.put(R.id.clWorkoutVideo0, 1);
        sparseIntArray.put(R.id.cvWorkoutImagePlay, 2);
        sparseIntArray.put(R.id.ivVideo0, 3);
        sparseIntArray.put(R.id.tvWorkoutTitle0, 4);
        sparseIntArray.put(R.id.tvWorkoutDuration0, 5);
        sparseIntArray.put(R.id.clMain, 6);
        sparseIntArray.put(R.id.cvWorkoutImage, 7);
        sparseIntArray.put(R.id.ivVideo, 8);
        sparseIntArray.put(R.id.tvWorkoutTitle, 9);
        sparseIntArray.put(R.id.tvWorkoutDuration, 10);
        sparseIntArray.put(R.id.view, 11);
    }

    public ListItemWorkoutVideosLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 12, j, k));
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

    public ListItemWorkoutVideosLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[1], (CardView) objArr[7], (CardView) objArr[2], (ImageView) objArr[8], (ImageView) objArr[3], (TextView) objArr[10], (TextView) objArr[5], (TextView) objArr[9], (TextView) objArr[4], (View) objArr[11]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
