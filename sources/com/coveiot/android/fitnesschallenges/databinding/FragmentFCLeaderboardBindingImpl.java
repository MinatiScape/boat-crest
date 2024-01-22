package com.coveiot.android.fitnesschallenges.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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
public class FragmentFCLeaderboardBindingImpl extends FragmentFCLeaderboardBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(21);
        j = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"info_details"}, new int[]{5}, new int[]{R.layout.info_details});
        includedLayouts.setIncludes(2, new String[]{"fragment_challenges_leaderboard"}, new int[]{4}, new int[]{com.coveiot.android.fitnesschallenges.R.layout.fragment_challenges_leaderboard});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.toolbar, 3);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.leaderboardText, 6);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvViewMore, 7);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.rankingText, 8);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.clHeaders, 9);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.rank, 10);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.rankerName, 11);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.rankerSteps, 12);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.rvParticipantList, 13);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.clMyRank, 14);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.clMainData, 15);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvMyRank, 16);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.ivMyProfilePic, 17);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvMyName, 18);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvMyCalories, 19);
        sparseIntArray.put(com.coveiot.android.fitnesschallenges.R.id.tvRankAchieveDate, 20);
    }

    public FragmentFCLeaderboardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 21, j, k));
    }

    public final boolean a(FragmentChallengesLeaderboardBinding fragmentChallengesLeaderboardBinding, int i) {
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
        ViewDataBinding.executeBindingsOn(this.challengeRankData);
        ViewDataBinding.executeBindingsOn(this.clInfo);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.challengeRankData.hasPendingBindings() || this.clInfo.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 4L;
        }
        this.challengeRankData.invalidateAll();
        this.clInfo.invalidateAll();
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
        return a((FragmentChallengesLeaderboardBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.challengeRankData.setLifecycleOwner(lifecycleOwner);
        this.clInfo.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentFCLeaderboardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (FragmentChallengesLeaderboardBinding) objArr[4], (ConstraintLayout) objArr[9], (InfoDetailsBinding) objArr[5], (ConstraintLayout) objArr[15], (ConstraintLayout) objArr[14], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[2], (ImageView) objArr[17], (TextView) objArr[6], (TextView) objArr[10], (TextView) objArr[11], (TextView) objArr[12], (TextView) objArr[8], (RecyclerView) objArr[13], (View) objArr[3], (TextView) objArr[19], (TextView) objArr[18], (TextView) objArr[16], (TextView) objArr[20], (TextView) objArr[7]);
        this.i = -1L;
        setContainedBinding(this.challengeRankData);
        setContainedBinding(this.clInfo);
        this.clRootLayout.setTag(null);
        this.clTopRanks.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
