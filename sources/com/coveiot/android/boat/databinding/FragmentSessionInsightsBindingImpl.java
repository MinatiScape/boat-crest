package com.coveiot.android.boat.databinding;

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
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class FragmentSessionInsightsBindingImpl extends FragmentSessionInsightsBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(12);
        j = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"sens_ai_sort_by_dialog"}, new int[]{1}, new int[]{R.layout.sens_ai_sort_by_dialog});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.cl_batting_bowling, 2);
        sparseIntArray.put(R.id.tvBatting, 3);
        sparseIntArray.put(R.id.tvBowling, 4);
        sparseIntArray.put(R.id.cl_filter, 5);
        sparseIntArray.put(R.id.tvSortBy, 6);
        sparseIntArray.put(R.id.rv_filter_list, 7);
        sparseIntArray.put(R.id.cl_session, 8);
        sparseIntArray.put(R.id.rv_session_insights_list, 9);
        sparseIntArray.put(R.id.btn_compare, 10);
        sparseIntArray.put(R.id.tv_no_sessions, 11);
    }

    public FragmentSessionInsightsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 12, j, k));
    }

    public final boolean a(SensAiSortByDialogBinding sensAiSortByDialogBinding, int i) {
        if (i == 0) {
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
        ViewDataBinding.executeBindingsOn(this.sortByDialog);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.sortByDialog.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 2L;
        }
        this.sortByDialog.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((SensAiSortByDialogBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.sortByDialog.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentSessionInsightsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (Button) objArr[10], (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[8], (RecyclerView) objArr[7], (RecyclerView) objArr[9], (SensAiSortByDialogBinding) objArr[1], (TextView) objArr[3], (TextView) objArr[4], (TextView) objArr[11], (TextView) objArr[6]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setContainedBinding(this.sortByDialog);
        setRootTag(view);
        invalidateAll();
    }
}
