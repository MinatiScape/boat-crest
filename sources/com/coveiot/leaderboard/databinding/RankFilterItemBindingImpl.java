package com.coveiot.leaderboard.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public class RankFilterItemBindingImpl extends RankFilterItemBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final LinearLayout h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.tv_filter, 1);
    }

    public RankFilterItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 2, j, k));
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

    public RankFilterItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[1]);
        this.i = -1L;
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.h = linearLayout;
        linearLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}