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
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public class FragmentChallengesLeaderboardBindingImpl extends FragmentChallengesLeaderboardBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.clRankDetails, 1);
        sparseIntArray.put(R.id.iv_rank_bg, 2);
        sparseIntArray.put(R.id.clRankOne, 3);
        sparseIntArray.put(R.id.ivRankOneBg, 4);
        sparseIntArray.put(R.id.ivRankOneUserProfilePic, 5);
        sparseIntArray.put(R.id.rank_one_value, 6);
        sparseIntArray.put(R.id.tv_rank_one, 7);
        sparseIntArray.put(R.id.clRankTwo, 8);
        sparseIntArray.put(R.id.ivRankTwoBg, 9);
        sparseIntArray.put(R.id.ivRankTwoUserProfilePic, 10);
        sparseIntArray.put(R.id.rank_two_value, 11);
        sparseIntArray.put(R.id.tv_rank_two, 12);
        sparseIntArray.put(R.id.clRankThree, 13);
        sparseIntArray.put(R.id.ivRankThreeBg, 14);
        sparseIntArray.put(R.id.ivRankThreeUserProfilePic, 15);
        sparseIntArray.put(R.id.rank_three_value, 16);
        sparseIntArray.put(R.id.tv_rank_three, 17);
        sparseIntArray.put(R.id.clRankOneDetails, 18);
        sparseIntArray.put(R.id.tvRankOneUserName, 19);
        sparseIntArray.put(R.id.tvRankOneUserCalories, 20);
        sparseIntArray.put(R.id.tvRankOneUserAchievedDate, 21);
        sparseIntArray.put(R.id.clRankTwoDetails, 22);
        sparseIntArray.put(R.id.tvRankTwoUserName, 23);
        sparseIntArray.put(R.id.tvRankTwoUserCalories, 24);
        sparseIntArray.put(R.id.tvRankTwoUserAchievedDate, 25);
        sparseIntArray.put(R.id.clRankThreeDetails, 26);
        sparseIntArray.put(R.id.tvRankThreeUserName, 27);
        sparseIntArray.put(R.id.tvRankThreeUserCalories, 28);
        sparseIntArray.put(R.id.tvRankThreeUserAchievedDate, 29);
    }

    public FragmentChallengesLeaderboardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 30, j, k));
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

    public FragmentChallengesLeaderboardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[18], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[26], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[22], (ImageView) objArr[2], (ImageView) objArr[4], (ImageView) objArr[5], (ImageView) objArr[14], (ImageView) objArr[15], (ImageView) objArr[9], (ImageView) objArr[10], (ImageView) objArr[6], (ImageView) objArr[16], (ImageView) objArr[11], (TextView) objArr[7], (TextView) objArr[21], (TextView) objArr[20], (TextView) objArr[19], (TextView) objArr[17], (TextView) objArr[29], (TextView) objArr[28], (TextView) objArr[27], (TextView) objArr[12], (TextView) objArr[25], (TextView) objArr[24], (TextView) objArr[23]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
