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
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.coveiot.android.fitnesschallenges.BR;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.coveaccess.fitnesschallenge.model.GetAllParticipantsFitnessChallengeRes;
/* loaded from: classes2.dex */
public class ListItemFitnessChallengeLeaderboardLayoutBindingImpl extends ListItemFitnessChallengeLeaderboardLayoutBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.view, 2);
        sparseIntArray.put(R.id.clMainData, 3);
        sparseIntArray.put(R.id.clRank, 4);
        sparseIntArray.put(R.id.tvUserRank, 5);
        sparseIntArray.put(R.id.tvUserPreviousRank, 6);
        sparseIntArray.put(R.id.tvUserProfilePic, 7);
        sparseIntArray.put(R.id.tvAchievedDate, 8);
        sparseIntArray.put(R.id.tvUserCalories, 9);
    }

    public ListItemFitnessChallengeLeaderboardLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 10, i, j));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        synchronized (this) {
            j2 = this.h;
            this.h = 0L;
        }
        String str = null;
        GetAllParticipantsFitnessChallengeRes.ParticipantsDetails participantsDetails = this.mParticipantData;
        int i2 = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        if (i2 != 0 && participantsDetails != null) {
            str = participantsDetails.getName();
        }
        if (i2 != 0) {
            TextViewBindingAdapter.setText(this.tvUserName, str);
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
            this.h = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // com.coveiot.android.fitnesschallenges.databinding.ListItemFitnessChallengeLeaderboardLayoutBinding
    public void setParticipantData(@Nullable GetAllParticipantsFitnessChallengeRes.ParticipantsDetails participantsDetails) {
        this.mParticipantData = participantsDetails;
        synchronized (this) {
            this.h |= 1;
        }
        notifyPropertyChanged(BR.participantData);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        if (BR.participantData == i2) {
            setParticipantData((GetAllParticipantsFitnessChallengeRes.ParticipantsDetails) obj);
            return true;
        }
        return false;
    }

    public ListItemFitnessChallengeLeaderboardLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[0], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[4], (TextView) objArr[8], (TextView) objArr[9], (TextView) objArr[1], (TextView) objArr[6], (ImageView) objArr[7], (TextView) objArr[5], (View) objArr[2]);
        this.h = -1L;
        this.badgeBaseLayout.setTag(null);
        this.tvUserName.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
