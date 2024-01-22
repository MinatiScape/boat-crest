package com.coveiot.android.fitnesschallenges.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.coveiot.android.fitnesschallenges.BR;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.theme.databinding.InfoDetailsBinding;
/* loaded from: classes2.dex */
public class ActivityFitnessDetailsBindingImpl extends ActivityFitnessDetailsBinding {
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
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(38);
        k = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"fitness_details_edit_dialog"}, new int[]{6}, new int[]{R.layout.fitness_details_edit_dialog});
        includedLayouts.setIncludes(1, new String[]{"fitness_challenge_participants"}, new int[]{5}, new int[]{R.layout.fitness_challenge_participants});
        includedLayouts.setIncludes(2, new String[]{"info_details"}, new int[]{7}, new int[]{com.coveiot.android.theme.R.layout.info_details});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 3);
        sparseIntArray.put(R.id.share_challenge_error, 4);
        sparseIntArray.put(R.id.leaderboardContainer, 8);
        sparseIntArray.put(R.id.ivShare, 9);
        sparseIntArray.put(R.id.ivEdit, 10);
        sparseIntArray.put(R.id.nestedScrollView, 11);
        sparseIntArray.put(R.id.clTitle, 12);
        sparseIntArray.put(R.id.cvImage, 13);
        sparseIntArray.put(R.id.ivBuddyImage, 14);
        sparseIntArray.put(R.id.tv_image_title, 15);
        sparseIntArray.put(R.id.ivGlobalImage, 16);
        sparseIntArray.put(R.id.tvTitle, 17);
        sparseIntArray.put(R.id.tvDesc, 18);
        sparseIntArray.put(R.id.clCompletionProgress, 19);
        sparseIntArray.put(R.id.tvCompletionProgress, 20);
        sparseIntArray.put(R.id.tvRank, 21);
        sparseIntArray.put(R.id.tvGoalCompletion, 22);
        sparseIntArray.put(R.id.clProgressBar, 23);
        sparseIntArray.put(R.id.pbCompletion, 24);
        sparseIntArray.put(R.id.tvProgressValue, 25);
        sparseIntArray.put(R.id.tvLeaderboard, 26);
        sparseIntArray.put(R.id.tvDuration, 27);
        sparseIntArray.put(R.id.tvDaysLeft, 28);
        sparseIntArray.put(R.id.tvCreator, 29);
        sparseIntArray.put(R.id.tvTargetGoal, 30);
        sparseIntArray.put(R.id.tvFitCrew, 31);
        sparseIntArray.put(R.id.tvNoParticipant, 32);
        sparseIntArray.put(R.id.tvViewAllParticipants, 33);
        sparseIntArray.put(R.id.clBottomButtons, 34);
        sparseIntArray.put(R.id.btnAddParticipants, 35);
        sparseIntArray.put(R.id.btnJoinChallenge, 36);
        sparseIntArray.put(R.id.btnLeaveChallenge, 37);
    }

    public ActivityFitnessDetailsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 38, k, l));
    }

    public final boolean a(FitnessChallengeParticipantsBinding fitnessChallengeParticipantsBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 4;
            }
            return true;
        }
        return false;
    }

    public final boolean b(InfoDetailsBinding infoDetailsBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 2;
            }
            return true;
        }
        return false;
    }

    public final boolean c(FitnessDetailsEditDialogBinding fitnessDetailsEditDialogBinding, int i) {
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
        ViewDataBinding.executeBindingsOn(this.fitnessChallengeParticipants);
        ViewDataBinding.executeBindingsOn(this.myChallengeEdit);
        ViewDataBinding.executeBindingsOn(this.infoDetails);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.j != 0) {
                return true;
            }
            return this.fitnessChallengeParticipants.hasPendingBindings() || this.myChallengeEdit.hasPendingBindings() || this.infoDetails.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 8L;
        }
        this.fitnessChallengeParticipants.invalidateAll();
        this.myChallengeEdit.invalidateAll();
        this.infoDetails.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return false;
                }
                return a((FitnessChallengeParticipantsBinding) obj, i2);
            }
            return b((InfoDetailsBinding) obj, i2);
        }
        return c((FitnessDetailsEditDialogBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.fitnessChallengeParticipants.setLifecycleOwner(lifecycleOwner);
        this.myChallengeEdit.setLifecycleOwner(lifecycleOwner);
        this.infoDetails.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public ActivityFitnessDetailsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, (Button) objArr[35], (Button) objArr[36], (Button) objArr[37], (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[34], (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[23], (ConstraintLayout) objArr[12], (CardView) objArr[13], (FitnessChallengeParticipantsBinding) objArr[5], (InfoDetailsBinding) objArr[7], (ImageView) objArr[14], (ImageView) objArr[10], (ImageView) objArr[16], (ImageView) objArr[9], (FrameLayout) objArr[8], (FitnessDetailsEditDialogBinding) objArr[6], (NestedScrollView) objArr[11], (ProgressBar) objArr[24], (View) objArr[4], (View) objArr[3], (TextView) objArr[20], (TextView) objArr[29], (TextView) objArr[28], (TextView) objArr[18], (TextView) objArr[27], (TextView) objArr[31], (TextView) objArr[22], (TextView) objArr[15], (TextView) objArr[26], (TextView) objArr[32], (TextView) objArr[25], (TextView) objArr[21], (TextView) objArr[30], (TextView) objArr[17], (TextView) objArr[33]);
        this.j = -1L;
        this.clBottom.setTag(null);
        setContainedBinding(this.fitnessChallengeParticipants);
        setContainedBinding(this.infoDetails);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) objArr[1];
        this.i = constraintLayout2;
        constraintLayout2.setTag(null);
        setContainedBinding(this.myChallengeEdit);
        setRootTag(view);
        invalidateAll();
    }
}
