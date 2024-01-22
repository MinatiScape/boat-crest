package com.coveiot.android.fitnesschallenges.databinding;

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
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.fitnesschallenges.BR;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.databinding.InfoDetailsBinding;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes2.dex */
public class ActivityViewAllParticipantsDetailsBindingImpl extends ActivityViewAllParticipantsDetailsBinding {
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
        includedLayouts.setIncludes(1, new String[]{"info_details"}, new int[]{3}, new int[]{R.layout.info_details});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.toolbar, 2);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.clMenu, 4);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvAddParticipant, 5);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.view, 6);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvRemoveParticipant, 7);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tabLayout, 8);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.viewPager, 9);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.btnRemove_participnats, 10);
    }

    public ActivityViewAllParticipantsDetailsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
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

    public ActivityViewAllParticipantsDetailsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (Button) objArr[10], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[1], (InfoDetailsBinding) objArr[3], (TabLayout) objArr[8], (View) objArr[2], (TextView) objArr[5], (TextView) objArr[7], (View) objArr[6], (ViewPager) objArr[9]);
        this.i = -1L;
        this.fitnessChallengeViews.setTag(null);
        setContainedBinding(this.infoDetails);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
