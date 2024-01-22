package com.coveiot.android.fitnesschallenges.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.coveiot.android.fitnesschallenges.BR;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
/* loaded from: classes2.dex */
public class FitnessChallengeItemBindingImpl extends FitnessChallengeItemBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.cvImage, 7);
        sparseIntArray.put(R.id.ivChallengeBg, 8);
        sparseIntArray.put(R.id.clMain, 9);
        sparseIntArray.put(R.id.tvNewChallenge, 10);
        sparseIntArray.put(R.id.clTitle, 11);
        sparseIntArray.put(R.id.clTopParticipant, 12);
        sparseIntArray.put(R.id.ivTopParticipant1, 13);
        sparseIntArray.put(R.id.ivTopParticipant2, 14);
        sparseIntArray.put(R.id.ivTopParticipant3, 15);
        sparseIntArray.put(R.id.tvNoParticipants, 16);
        sparseIntArray.put(R.id.ivChallengeImg, 17);
        sparseIntArray.put(R.id.barrierParticipants, 18);
        sparseIntArray.put(R.id.tvChallengeProgress, 19);
        sparseIntArray.put(R.id.challengeProgress, 20);
        sparseIntArray.put(R.id.barrier, 21);
    }

    public FitnessChallengeItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 22, i, j));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        String str;
        int i2;
        long j3;
        long j4;
        synchronized (this) {
            j2 = this.h;
            this.h = 0L;
        }
        String str2 = this.mTotalParticipantsCount;
        BuddiesChallengeRes.Item item = this.mChallengeItem;
        String str3 = this.mDaysLeft;
        Boolean bool = this.mChallengeJoined;
        String str4 = null;
        if ((j2 & 18) == 0 || item == null) {
            str = null;
        } else {
            str4 = item.getDescription();
            str = item.getName();
        }
        int i3 = ((j2 & 24) > 0L ? 1 : ((j2 & 24) == 0L ? 0 : -1));
        if (i3 != 0) {
            boolean safeUnbox = ViewDataBinding.safeUnbox(bool);
            if (i3 != 0) {
                if (safeUnbox) {
                    j3 = j2 | 64;
                    j4 = 256;
                } else {
                    j3 = j2 | 32;
                    j4 = 128;
                }
                j2 = j3 | j4;
            }
            int i4 = safeUnbox ? 8 : 0;
            i2 = safeUnbox ? 0 : 8;
            r15 = i4;
        } else {
            i2 = 0;
        }
        if ((24 & j2) != 0) {
            this.btnJoin.setVisibility(r15);
            this.clCompletedChallenge.setVisibility(i2);
        }
        if ((j2 & 18) != 0) {
            TextViewBindingAdapter.setText(this.tvChallengeDesc, str4);
            TextViewBindingAdapter.setText(this.tvChallengeTitle, str);
        }
        if ((20 & j2) != 0) {
            TextViewBindingAdapter.setText(this.tvDaysLeft, str3);
        }
        if ((j2 & 17) != 0) {
            TextViewBindingAdapter.setText(this.tvTotalParticipants, str2);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.h != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.h = 16L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // com.coveiot.android.fitnesschallenges.databinding.FitnessChallengeItemBinding
    public void setChallengeItem(@Nullable BuddiesChallengeRes.Item item) {
        this.mChallengeItem = item;
        synchronized (this) {
            this.h |= 2;
        }
        notifyPropertyChanged(BR.challengeItem);
        super.requestRebind();
    }

    @Override // com.coveiot.android.fitnesschallenges.databinding.FitnessChallengeItemBinding
    public void setChallengeJoined(@Nullable Boolean bool) {
        this.mChallengeJoined = bool;
        synchronized (this) {
            this.h |= 8;
        }
        notifyPropertyChanged(BR.challengeJoined);
        super.requestRebind();
    }

    @Override // com.coveiot.android.fitnesschallenges.databinding.FitnessChallengeItemBinding
    public void setDaysLeft(@Nullable String str) {
        this.mDaysLeft = str;
        synchronized (this) {
            this.h |= 4;
        }
        notifyPropertyChanged(BR.daysLeft);
        super.requestRebind();
    }

    @Override // com.coveiot.android.fitnesschallenges.databinding.FitnessChallengeItemBinding
    public void setTotalParticipantsCount(@Nullable String str) {
        this.mTotalParticipantsCount = str;
        synchronized (this) {
            this.h |= 1;
        }
        notifyPropertyChanged(BR.totalParticipantsCount);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        if (BR.totalParticipantsCount == i2) {
            setTotalParticipantsCount((String) obj);
        } else if (BR.challengeItem == i2) {
            setChallengeItem((BuddiesChallengeRes.Item) obj);
        } else if (BR.daysLeft == i2) {
            setDaysLeft((String) obj);
        } else if (BR.challengeJoined != i2) {
            return false;
        } else {
            setChallengeJoined((Boolean) obj);
        }
        return true;
    }

    public FitnessChallengeItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Barrier) objArr[21], (Barrier) objArr[18], (Button) objArr[4], (ProgressBar) objArr[20], (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[9], (ConstraintLayout) objArr[0], (ConstraintLayout) objArr[11], (ConstraintLayout) objArr[12], (CardView) objArr[7], (ImageView) objArr[8], (ImageView) objArr[17], (ImageView) objArr[13], (ImageView) objArr[14], (ImageView) objArr[15], (TextView) objArr[2], (TextView) objArr[19], (TextView) objArr[1], (TextView) objArr[6], (TextView) objArr[10], (TextView) objArr[16], (TextView) objArr[3]);
        this.h = -1L;
        this.btnJoin.setTag(null);
        this.clCompletedChallenge.setTag(null);
        this.clRoot.setTag(null);
        this.tvChallengeDesc.setTag(null);
        this.tvChallengeTitle.setTag(null);
        this.tvDaysLeft.setTag(null);
        this.tvTotalParticipants.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
