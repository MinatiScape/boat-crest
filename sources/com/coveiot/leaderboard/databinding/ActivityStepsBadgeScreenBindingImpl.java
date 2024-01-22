package com.coveiot.leaderboard.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public class ActivityStepsBadgeScreenBindingImpl extends ActivityStepsBadgeScreenBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.badgeIv, 1);
        sparseIntArray.put(R.id.ic_image_share, 2);
        sparseIntArray.put(R.id.badgeName, 3);
        sparseIntArray.put(R.id.description, 4);
        sparseIntArray.put(R.id.earnedBadge, 5);
        sparseIntArray.put(R.id.unLock, 6);
        sparseIntArray.put(R.id.gl_button_only, 7);
        sparseIntArray.put(R.id.done, 8);
    }

    public ActivityStepsBadgeScreenBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 9, i, j));
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

    public ActivityStepsBadgeScreenBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[1], (TextView) objArr[3], (TextView) objArr[4], (Button) objArr[8], (TextView) objArr[5], (Guideline) objArr[7], (ImageView) objArr[2], (ConstraintLayout) objArr[0], (TextView) objArr[6]);
        this.h = -1L;
        this.rootLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
