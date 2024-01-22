package com.coveiot.android.fitnesschallenges.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnesschallenges.BR;
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public class FragmentParticipantsListBindingImpl extends FragmentParticipantsListBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(3);
        i = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"empty_challenge_view_layout"}, new int[]{1}, new int[]{R.layout.empty_challenge_view_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.rvParticipantList, 2);
    }

    public FragmentParticipantsListBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 3, i, j));
    }

    public final boolean a(EmptyChallengeViewLayoutBinding emptyChallengeViewLayoutBinding, int i2) {
        if (i2 == BR._all) {
            synchronized (this) {
                this.h |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.h = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.emptyChallengeView);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.h != 0) {
                return true;
            }
            return this.emptyChallengeView.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.h = 2L;
        }
        this.emptyChallengeView.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        if (i2 != 0) {
            return false;
        }
        return a((EmptyChallengeViewLayoutBinding) obj, i3);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.emptyChallengeView.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }

    public FragmentParticipantsListBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ConstraintLayout) objArr[0], (EmptyChallengeViewLayoutBinding) objArr[1], (RecyclerView) objArr[2]);
        this.h = -1L;
        this.clRootLayout.setTag(null);
        setContainedBinding(this.emptyChallengeView);
        setRootTag(view);
        invalidateAll();
    }
}
