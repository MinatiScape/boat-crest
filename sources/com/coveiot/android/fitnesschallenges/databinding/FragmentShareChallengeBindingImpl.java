package com.coveiot.android.fitnesschallenges.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.coveiot.android.fitnesschallenges.BR;
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public class FragmentShareChallengeBindingImpl extends FragmentShareChallengeBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(28);
        j = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"challenge_first_rank_layout"}, new int[]{3}, new int[]{R.layout.challenge_first_rank_layout});
        int i = R.layout.share_info_layout;
        includedLayouts.setIncludes(2, new String[]{"share_info_layout", "share_info_layout", "share_info_layout"}, new int[]{4, 5, 6}, new int[]{i, i, i});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.share_fitness_challenge_card, 7);
        sparseIntArray.put(R.id.iv_app_logo, 8);
        sparseIntArray.put(R.id.iv_close, 9);
        sparseIntArray.put(R.id.iv_powered_cove, 10);
        sparseIntArray.put(R.id.user_pic_layout, 11);
        sparseIntArray.put(R.id.default_pic, 12);
        sparseIntArray.put(R.id.user_pic, 13);
        sparseIntArray.put(R.id.tvUserName, 14);
        sparseIntArray.put(R.id.tvUserDesc, 15);
        sparseIntArray.put(R.id.challenge_title_tv, 16);
        sparseIntArray.put(R.id.challenge_desc_tv, 17);
        sparseIntArray.put(R.id.clProgress, 18);
        sparseIntArray.put(R.id.challenge_progress_tv, 19);
        sparseIntArray.put(R.id.challenge_pgBar, 20);
        sparseIntArray.put(R.id.divider1, 21);
        sparseIntArray.put(R.id.divider2, 22);
        sparseIntArray.put(R.id.cldefaultuserdetails, 23);
        sparseIntArray.put(R.id.profile_user_pic, 24);
        sparseIntArray.put(R.id.user_name, 25);
        sparseIntArray.put(R.id.week, 26);
        sparseIntArray.put(R.id.share_btn, 27);
    }

    public FragmentShareChallengeBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 28, j, k));
    }

    public final boolean a(ShareInfoLayoutBinding shareInfoLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 4;
            }
            return true;
        }
        return false;
    }

    public final boolean b(ChallengeFirstRankLayoutBinding challengeFirstRankLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 2;
            }
            return true;
        }
        return false;
    }

    public final boolean c(ShareInfoLayoutBinding shareInfoLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 1;
            }
            return true;
        }
        return false;
    }

    public final boolean d(ShareInfoLayoutBinding shareInfoLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 8;
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
        ViewDataBinding.executeBindingsOn(this.firstRankHolderPic);
        ViewDataBinding.executeBindingsOn(this.duration);
        ViewDataBinding.executeBindingsOn(this.goal);
        ViewDataBinding.executeBindingsOn(this.participants);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.firstRankHolderPic.hasPendingBindings() || this.duration.hasPendingBindings() || this.goal.hasPendingBindings() || this.participants.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 16L;
        }
        this.firstRankHolderPic.invalidateAll();
        this.duration.invalidateAll();
        this.goal.invalidateAll();
        this.participants.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return false;
                    }
                    return d((ShareInfoLayoutBinding) obj, i2);
                }
                return a((ShareInfoLayoutBinding) obj, i2);
            }
            return b((ChallengeFirstRankLayoutBinding) obj, i2);
        }
        return c((ShareInfoLayoutBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.firstRankHolderPic.setLifecycleOwner(lifecycleOwner);
        this.duration.setLifecycleOwner(lifecycleOwner);
        this.goal.setLifecycleOwner(lifecycleOwner);
        this.participants.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentShareChallengeBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, (TextView) objArr[17], (ConstraintLayout) objArr[2], (ProgressBar) objArr[20], (TextView) objArr[19], (TextView) objArr[16], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[18], (ConstraintLayout) objArr[23], (ImageView) objArr[12], (View) objArr[21], (View) objArr[22], (ShareInfoLayoutBinding) objArr[4], (ChallengeFirstRankLayoutBinding) objArr[3], (ShareInfoLayoutBinding) objArr[5], (ImageView) objArr[8], (ImageView) objArr[9], (ImageView) objArr[10], (ShareInfoLayoutBinding) objArr[6], (ImageView) objArr[24], (Button) objArr[27], (ConstraintLayout) objArr[7], (TextView) objArr[15], (TextView) objArr[14], (TextView) objArr[25], (ImageView) objArr[13], (ConstraintLayout) objArr[11], (TextView) objArr[26]);
        this.i = -1L;
        this.challengeInfoLayout.setTag(null);
        this.clPic.setTag(null);
        setContainedBinding(this.duration);
        setContainedBinding(this.firstRankHolderPic);
        setContainedBinding(this.goal);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setContainedBinding(this.participants);
        setRootTag(view);
        invalidateAll();
    }
}
