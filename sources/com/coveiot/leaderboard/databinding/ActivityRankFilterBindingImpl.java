package com.coveiot.leaderboard.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.databinding.InfoDetailsBinding;
import com.coveiot.leaderboard.BR;
/* loaded from: classes9.dex */
public class ActivityRankFilterBindingImpl extends ActivityRankFilterBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(11);
        j = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"info_details"}, new int[]{2}, new int[]{R.layout.info_details});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(com.coveiot.leaderboard.R.id.toolbar, 1);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.cl_days, 3);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tv_daily_badges, 4);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rv_day_filter, 5);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.view, 6);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.cl_location, 7);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tv_location, 8);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rv_locality_filter, 9);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.btnNext, 10);
    }

    public ActivityRankFilterBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 11, j, k));
    }

    public final boolean a(InfoDetailsBinding infoDetailsBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.i = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.infoDetails);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.infoDetails.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 2L;
        }
        this.infoDetails.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((InfoDetailsBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.infoDetails.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public ActivityRankFilterBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (Button) objArr[10], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[7], (InfoDetailsBinding) objArr[2], (RecyclerView) objArr[5], (RecyclerView) objArr[9], (View) objArr[1], (TextView) objArr[4], (TextView) objArr[8], (View) objArr[6]);
        this.i = -1L;
        setContainedBinding(this.infoDetails);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
