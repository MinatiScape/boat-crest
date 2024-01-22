package com.coveiot.leaderboard.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.fitnesschallenges.databinding.EmptyChallengeViewLayoutBinding;
import com.coveiot.leaderboard.BR;
/* loaded from: classes9.dex */
public class FragmentLeaderboardHomeBindingImpl extends FragmentLeaderboardHomeBinding {
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
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(62);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"empty_challenge_view_layout"}, new int[]{2}, new int[]{R.layout.empty_challenge_view_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rank_main, 3);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.cl_rank_details, 4);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.ll_tab, 5);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tab_challenge, 6);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tab_buddies, 7);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tab_global, 8);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.cl_toppers_rank, 9);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tv_rank_details, 10);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.clRankDetails, 11);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.iv_rank_bg, 12);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.clRankOne, 13);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.ivRankOneBg, 14);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rankOneIv, 15);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rank_one_value, 16);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tv_rank_one, 17);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.clRankTwo, 18);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.ivRankTwoBg, 19);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rankTwoIv, 20);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rank_two_value, 21);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tv_rank_two, 22);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.clRankThree, 23);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.ivRankThreeBg, 24);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rankThreeIv, 25);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rank_three_value, 26);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tv_rank_three, 27);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.clRankOneDetails, 28);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tvRankOneName, 29);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tvRankOneSteps, 30);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tvRankOneUserAchievedDate, 31);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.clRankTwoDetails, 32);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tvRankTwoName, 33);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tvRankTwoSteps, 34);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tvRankTwoUserAchievedDate, 35);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.clRankThreeDetails, 36);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tvRankThreeName, 37);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tvRankThreeSteps, 38);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tvRankThreeUserAchievedDate, 39);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rvChallenges, 40);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.cl_rank_list, 41);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tv_filter, 42);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.iv_share, 43);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.cl_filter, 44);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rv_filtered_list, 45);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.ll_list, 46);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rank, 47);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rankerName, 48);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rankerSteps, 49);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.view, 50);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.rv_rank_list, 51);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.ll_my_rank, 52);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.my_rank, 53);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tv_previous_rank, 54);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.iv_ranker_profile, 55);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.myName, 56);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.mySteps, 57);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.cl_empty_buddies, 58);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.iv_empty_buddies, 59);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.tv_empty_rank_list, 60);
        sparseIntArray.put(com.coveiot.leaderboard.R.id.btnAddBuddies, 61);
    }

    public FragmentLeaderboardHomeBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 62, k, l));
    }

    public final boolean a(EmptyChallengeViewLayoutBinding emptyChallengeViewLayoutBinding, int i) {
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
        ViewDataBinding.executeBindingsOn(this.emptyChallengeView);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.j != 0) {
                return true;
            }
            return this.emptyChallengeView.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 2L;
        }
        this.emptyChallengeView.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((EmptyChallengeViewLayoutBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.emptyChallengeView.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentLeaderboardHomeBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (Button) objArr[61], (ConstraintLayout) objArr[58], (ConstraintLayout) objArr[44], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[11], (ConstraintLayout) objArr[41], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[28], (ConstraintLayout) objArr[23], (ConstraintLayout) objArr[36], (ConstraintLayout) objArr[18], (ConstraintLayout) objArr[32], (ConstraintLayout) objArr[9], (EmptyChallengeViewLayoutBinding) objArr[2], (ImageView) objArr[59], (ImageView) objArr[12], (ImageView) objArr[14], (ImageView) objArr[24], (ImageView) objArr[19], (ImageView) objArr[55], (ImageView) objArr[43], (LinearLayout) objArr[46], (LinearLayout) objArr[52], (LinearLayout) objArr[5], (TextView) objArr[56], (TextView) objArr[53], (TextView) objArr[57], (TextView) objArr[47], (NestedScrollView) objArr[3], (ImageView) objArr[15], (ImageView) objArr[16], (ImageView) objArr[25], (ImageView) objArr[26], (ImageView) objArr[20], (ImageView) objArr[21], (TextView) objArr[48], (TextView) objArr[49], (RecyclerView) objArr[40], (RecyclerView) objArr[45], (RecyclerView) objArr[51], (TextView) objArr[7], (TextView) objArr[6], (TextView) objArr[8], (TextView) objArr[60], (TextView) objArr[42], (TextView) objArr[54], (TextView) objArr[10], (TextView) objArr[17], (TextView) objArr[29], (TextView) objArr[30], (TextView) objArr[31], (TextView) objArr[27], (TextView) objArr[37], (TextView) objArr[38], (TextView) objArr[39], (TextView) objArr[22], (TextView) objArr[33], (TextView) objArr[34], (TextView) objArr[35], (View) objArr[50]);
        this.j = -1L;
        setContainedBinding(this.emptyChallengeView);
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
