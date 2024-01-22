package com.coveiot.android.fitnessbuddies.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.BR;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.databinding.InfoDetailsBinding;
/* loaded from: classes4.dex */
public class ActivityAddBuddiesNewBindingImpl extends ActivityAddBuddiesNewBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(18);
        i = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"info_details"}, new int[]{3}, new int[]{R.layout.info_details});
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.toolbar, 2);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.clMenu, 4);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.tvAddParticipant, 5);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.view, 6);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.tvRemoveParticipant, 7);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.search_layout, 8);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.search, 9);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.refresh, 10);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.cl_active_contacts, 11);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.tv_active_header, 12);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.rvActiveContactsList, 13);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.cl_inactive_contacts, 14);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.tv_inactive_header, 15);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.rvInActiveContactsList, 16);
        sparseIntArray.put(com.coveiot.android.fitnessbuddies.R.id.btnCreateChallenge, 17);
    }

    public ActivityAddBuddiesNewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 18, i, j));
    }

    public final boolean a(InfoDetailsBinding infoDetailsBinding, int i2) {
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
        ViewDataBinding.executeBindingsOn(this.infoDetails);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.h != 0) {
                return true;
            }
            return this.infoDetails.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.h = 2L;
        }
        this.infoDetails.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        if (i2 != 0) {
            return false;
        }
        return a((InfoDetailsBinding) obj, i3);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.infoDetails.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }

    public ActivityAddBuddiesNewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (Button) objArr[17], (ConstraintLayout) objArr[11], (ConstraintLayout) objArr[14], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[0], (ConstraintLayout) objArr[1], (InfoDetailsBinding) objArr[3], (ImageButton) objArr[10], (RecyclerView) objArr[13], (RecyclerView) objArr[16], (SearchView) objArr[9], (LinearLayout) objArr[8], (View) objArr[2], (TextView) objArr[12], (TextView) objArr[5], (TextView) objArr[15], (TextView) objArr[7], (View) objArr[6]);
        this.h = -1L;
        this.clRootLayout.setTag(null);
        this.fitnessChallengeViews.setTag(null);
        setContainedBinding(this.infoDetails);
        setRootTag(view);
        invalidateAll();
    }
}
