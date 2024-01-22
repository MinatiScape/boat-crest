package com.coveiot.android.fitnesschallenges.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.fitnesschallenges.BR;
import com.coveiot.android.fitnesschallenges.R;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes2.dex */
public class ActivityFitnessChallengesHomeBindingImpl extends ActivityFitnessChallengesHomeBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k;
    @Nullable
    public static final SparseIntArray l;
    @NonNull
    public final ConstraintLayout h;
    @NonNull
    public final ConstraintLayout i;
    public long j;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(8);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"create_challenge_banner"}, new int[]{3}, new int[]{R.layout.create_challenge_banner});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 2);
        sparseIntArray.put(R.id.tvCreateChallengeTitle, 4);
        sparseIntArray.put(R.id.challenge_tabLayout, 5);
        sparseIntArray.put(R.id.tabLayout, 6);
        sparseIntArray.put(R.id.viewPager, 7);
    }

    public ActivityFitnessChallengesHomeBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 8, k, l));
    }

    public final boolean a(CreateChallengeBannerBinding createChallengeBannerBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.j = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.creteChallengeLayout);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.j != 0) {
                return true;
            }
            return this.creteChallengeLayout.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 2L;
        }
        this.creteChallengeLayout.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((CreateChallengeBannerBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.creteChallengeLayout.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public ActivityFitnessChallengesHomeBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ConstraintLayout) objArr[5], (CreateChallengeBannerBinding) objArr[3], (TabLayout) objArr[6], (View) objArr[2], (TextView) objArr[4], (ViewPager) objArr[7]);
        this.j = -1L;
        setContainedBinding(this.creteChallengeLayout);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) objArr[1];
        this.i = constraintLayout2;
        constraintLayout2.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
