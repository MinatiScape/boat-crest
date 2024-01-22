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
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.databinding.InfoDetailsBinding;
/* loaded from: classes2.dex */
public class FragmentCompletedBindingImpl extends FragmentCompletedBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(4);
        j = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"info_details", "empty_challenge_view_layout"}, new int[]{1, 2}, new int[]{R.layout.info_details, com.coveiot.android.fitnesschallenges.R.layout.empty_challenge_view_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.rv_challenges, 3);
    }

    public FragmentCompletedBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 4, j, k));
    }

    public final boolean a(EmptyChallengeViewLayoutBinding emptyChallengeViewLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 1;
            }
            return true;
        }
        return false;
    }

    public final boolean b(InfoDetailsBinding infoDetailsBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 2;
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
        ViewDataBinding.executeBindingsOn(this.emptyChallengeView);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.infoDetails.hasPendingBindings() || this.emptyChallengeView.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 4L;
        }
        this.infoDetails.invalidateAll();
        this.emptyChallengeView.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i != 1) {
                return false;
            }
            return b((InfoDetailsBinding) obj, i2);
        }
        return a((EmptyChallengeViewLayoutBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.infoDetails.setLifecycleOwner(lifecycleOwner);
        this.emptyChallengeView.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentCompletedBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (EmptyChallengeViewLayoutBinding) objArr[2], (InfoDetailsBinding) objArr[1], (RecyclerView) objArr[3]);
        this.i = -1L;
        setContainedBinding(this.emptyChallengeView);
        setContainedBinding(this.infoDetails);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
