package com.coveiot.android.fitnesschallenges.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnesschallenges.BR;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.databinding.InfoDetailsBinding;
/* loaded from: classes2.dex */
public class FragmentCreateChallengeBindingImpl extends FragmentCreateChallengeBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(43);
        j = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"info_details"}, new int[]{3}, new int[]{R.layout.info_details});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.toolbar, 2);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.constraint_layout1, 4);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.image_rv_layout, 5);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.imageRecyclerView, 6);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.edit_details_layout, 7);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvChallengeName, 8);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.editChallengeName, 9);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvEditChallengeNameCount, 10);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvChallengeDescription, 11);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.editDesc, 12);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvChallengeDescCount, 13);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.clChallengeGoalType, 14);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvGoal, 15);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.radioGroup, 16);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.clDistanceGoal, 17);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.rbGoalDistance, 18);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.pickerDistance, 19);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.clCalorieGoal, 20);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.rbCalorieGoal, 21);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.pickerCalorie, 22);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.reminderLayout, 23);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.clStartTime, 24);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvStartDateTitle, 25);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvStartDate, 26);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.clEndTime, 27);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvEndDateTitle, 28);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvEndDate, 29);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.participants_layout, 30);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.clTopParticipantLayout, 31);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.clTopParticipantImageView, 32);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.ivTopParticipant1, 33);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.ivTopParticipant2, 34);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.ivTopParticipant3, 35);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.totalParticipants_tv, 36);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvViewAllParticipants, 37);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.btnAddParticipants, 38);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.clConditionsLayout, 39);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.cb_AgreeTerms, 40);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvCondition, 41);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.btnCreateChallenge, 42);
    }

    public FragmentCreateChallengeBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 43, j, k));
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
        ViewDataBinding.executeBindingsOn(this.clInfo);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.clInfo.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 2L;
        }
        this.clInfo.invalidateAll();
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
        this.clInfo.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentCreateChallengeBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (Button) objArr[38], (Button) objArr[42], (CheckBox) objArr[40], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[20], (ConstraintLayout) objArr[14], (ConstraintLayout) objArr[39], (ConstraintLayout) objArr[17], (ConstraintLayout) objArr[27], (InfoDetailsBinding) objArr[3], (ConstraintLayout) objArr[24], (ConstraintLayout) objArr[32], (ConstraintLayout) objArr[31], (ConstraintLayout) objArr[4], (EditText) objArr[9], (EditText) objArr[12], (ConstraintLayout) objArr[7], (RecyclerView) objArr[6], (ConstraintLayout) objArr[5], (ImageView) objArr[33], (ImageView) objArr[34], (ImageView) objArr[35], (ConstraintLayout) objArr[30], (TextView) objArr[22], (TextView) objArr[19], (RadioGroup) objArr[16], (AppCompatRadioButton) objArr[21], (AppCompatRadioButton) objArr[18], (ConstraintLayout) objArr[23], (View) objArr[2], (TextView) objArr[36], (TextView) objArr[13], (TextView) objArr[11], (TextView) objArr[8], (TextView) objArr[41], (TextView) objArr[10], (TextView) objArr[29], (TextView) objArr[28], (TextView) objArr[15], (TextView) objArr[26], (TextView) objArr[25], (TextView) objArr[37]);
        this.i = -1L;
        this.clBottomViews.setTag(null);
        setContainedBinding(this.clInfo);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
