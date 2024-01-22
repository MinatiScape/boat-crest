package com.coveiot.android.activitymodes.databinding;

import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.coveiot.android.activitymodes.BR;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.models.Images;
import com.coveiot.android.activitymodes.models.PlanHistory;
import com.coveiot.android.activitymodes.models.PlanTemplate;
import com.coveiot.android.activitymodes.utils.ViewUtilsKt;
/* loaded from: classes2.dex */
public class ListItemFitnessPlanHistoryLayoutBindingImpl extends ListItemFitnessPlanHistoryLayoutBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.clTop, 13);
        sparseIntArray.put(R.id.cvWeekPlanImage, 14);
        sparseIntArray.put(R.id.clBottom, 15);
        sparseIntArray.put(R.id.tvTodayGoal, 16);
        sparseIntArray.put(R.id.ivTodayGoal, 17);
        sparseIntArray.put(R.id.tvTodayGoalName, 18);
        sparseIntArray.put(R.id.tvTodayGoalValue, 19);
        sparseIntArray.put(R.id.tvTodayGoalTotalValue, 20);
        sparseIntArray.put(R.id.clBottomTop, 21);
        sparseIntArray.put(R.id.clMainData, 22);
        sparseIntArray.put(R.id.view1, 23);
        sparseIntArray.put(R.id.view2, 24);
        sparseIntArray.put(R.id.tvTotalDistance, 25);
        sparseIntArray.put(R.id.dashView, 26);
        sparseIntArray.put(R.id.tvViewDetails, 27);
    }

    public ListItemFitnessPlanHistoryLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 28, i, j));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        String str;
        String str2;
        String str3;
        int i2;
        String str4;
        String str5;
        String str6;
        String str7;
        int i3;
        int i4;
        String str8;
        Drawable drawable;
        String str9;
        int i5;
        int i6;
        String str10;
        long j3;
        String string;
        long j4;
        long j5;
        long j6;
        long j7;
        int i7;
        PlanTemplate planTemplate;
        int i8;
        Images images;
        String str11;
        synchronized (this) {
            j2 = this.h;
            this.h = 0L;
        }
        PlanHistory planHistory = this.mPlanHistoryData;
        Boolean bool = this.mIsProgressFull;
        Boolean bool2 = this.mIsPlanCompleted;
        if ((j2 & 9) != 0) {
            if (planHistory != null) {
                i2 = planHistory.getProgress();
                i7 = planHistory.getTotalCalories();
                planTemplate = planHistory.getPlanTemplate();
                i8 = planHistory.getTotalSteps();
                str = planHistory.getStartAndEndDate();
            } else {
                str = null;
                i2 = 0;
                i7 = 0;
                planTemplate = null;
                i8 = 0;
            }
            String str12 = "Completion status : " + i2;
            str5 = i7 + " Cal";
            str6 = i8 + " Steps";
            if (planTemplate != null) {
                str7 = planTemplate.getShortDesc();
                str11 = planTemplate.getFullTitle();
                images = planTemplate.getImages();
            } else {
                images = null;
                str7 = null;
                str11 = null;
            }
            str2 = str12 + "%";
            if (images != null) {
                str3 = images.getThumbnail1();
                str4 = str11;
            } else {
                str4 = str11;
                str3 = null;
            }
        } else {
            str = null;
            str2 = null;
            str3 = null;
            i2 = 0;
            str4 = null;
            str5 = null;
            str6 = null;
            str7 = null;
        }
        int i9 = ((j2 & 10) > 0L ? 1 : ((j2 & 10) == 0L ? 0 : -1));
        if (i9 != 0) {
            boolean safeUnbox = ViewDataBinding.safeUnbox(bool);
            if (i9 != 0) {
                if (safeUnbox) {
                    j6 = j2 | 32;
                    j7 = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                } else {
                    j6 = j2 | 16;
                    j7 = 1024;
                }
                j2 = j6 | j7;
            }
            i4 = safeUnbox ? 0 : 8;
            i3 = safeUnbox ? 8 : 0;
        } else {
            i3 = 0;
            i4 = 0;
        }
        int i10 = ((j2 & 12) > 0L ? 1 : ((j2 & 12) == 0L ? 0 : -1));
        if (i10 != 0) {
            boolean safeUnbox2 = ViewDataBinding.safeUnbox(bool2);
            if (i10 != 0) {
                if (safeUnbox2) {
                    j4 = j2 | 128 | 512 | PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    j5 = PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                } else {
                    j4 = j2 | 64 | 256 | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    j5 = 16384;
                }
                j2 = j4 | j5;
            }
            drawable = AppCompatResources.getDrawable(this.tvWeekPlanStatus.getContext(), safeUnbox2 ? R.drawable.rounded_gradient_green_background : R.drawable.rounded_orange_gradient_background);
            if (safeUnbox2) {
                j3 = j2;
                string = this.tvWeekPlanStatus.getResources().getString(R.string.completed_caps);
            } else {
                j3 = j2;
                string = this.tvWeekPlanStatus.getResources().getString(R.string.ongoing_caps);
            }
            int i11 = safeUnbox2 ? 8 : 0;
            str9 = string;
            str8 = str4;
            i5 = safeUnbox2 ? 0 : 8;
            i6 = i11;
            j2 = j3;
        } else {
            str8 = str4;
            drawable = null;
            str9 = null;
            i5 = 0;
            i6 = 0;
        }
        if ((j2 & 12) != 0) {
            str10 = str6;
            this.clCompleted.setVisibility(i5);
            this.clOngoing.setVisibility(i6);
            ViewBindingAdapter.setBackground(this.tvWeekPlanStatus, drawable);
            TextViewBindingAdapter.setText(this.tvWeekPlanStatus, str9);
        } else {
            str10 = str6;
        }
        if ((j2 & 10) != 0) {
            this.clCompletionInfo.setVisibility(i3);
            this.tvCompletionStatus.setVisibility(i4);
        }
        if ((j2 & 9) != 0) {
            ViewUtilsKt.setImage(this.ivPlanImg, str3);
            this.todayGoalProgress.setProgress(i2);
            TextViewBindingAdapter.setText(this.tvCompletionStatus, str2);
            TextViewBindingAdapter.setText(this.tvPlanDate, str);
            TextViewBindingAdapter.setText(this.tvPlanDesc, str7);
            TextViewBindingAdapter.setText(this.tvTotalCalorie, str5);
            TextViewBindingAdapter.setText(this.tvTotalSteps, str10);
            TextViewBindingAdapter.setText(this.tvWeekPlanName, str8);
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
            this.h = 8L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // com.coveiot.android.activitymodes.databinding.ListItemFitnessPlanHistoryLayoutBinding
    public void setIsPlanCompleted(@Nullable Boolean bool) {
        this.mIsPlanCompleted = bool;
        synchronized (this) {
            this.h |= 4;
        }
        notifyPropertyChanged(BR.isPlanCompleted);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.ListItemFitnessPlanHistoryLayoutBinding
    public void setIsProgressFull(@Nullable Boolean bool) {
        this.mIsProgressFull = bool;
        synchronized (this) {
            this.h |= 2;
        }
        notifyPropertyChanged(BR.isProgressFull);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.ListItemFitnessPlanHistoryLayoutBinding
    public void setPlanHistoryData(@Nullable PlanHistory planHistory) {
        this.mPlanHistoryData = planHistory;
        synchronized (this) {
            this.h |= 1;
        }
        notifyPropertyChanged(BR.planHistoryData);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        if (BR.planHistoryData == i2) {
            setPlanHistoryData((PlanHistory) obj);
        } else if (BR.isProgressFull == i2) {
            setIsProgressFull((Boolean) obj);
        } else if (BR.isPlanCompleted != i2) {
            return false;
        } else {
            setIsPlanCompleted((Boolean) obj);
        }
        return true;
    }

    public ListItemFitnessPlanHistoryLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[15], (ConstraintLayout) objArr[21], (ConstraintLayout) objArr[7], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[0], (ConstraintLayout) objArr[22], (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[13], (CardView) objArr[14], (View) objArr[26], (ImageView) objArr[1], (ImageView) objArr[17], (ProgressBar) objArr[6], (TextView) objArr[10], (TextView) objArr[4], (TextView) objArr[9], (TextView) objArr[16], (TextView) objArr[18], (TextView) objArr[20], (TextView) objArr[19], (TextView) objArr[11], (TextView) objArr[25], (TextView) objArr[12], (TextView) objArr[27], (TextView) objArr[3], (TextView) objArr[2], (View) objArr[23], (View) objArr[24]);
        this.h = -1L;
        this.clCompleted.setTag(null);
        this.clCompletionInfo.setTag(null);
        this.clMain.setTag(null);
        this.clOngoing.setTag(null);
        this.ivPlanImg.setTag(null);
        this.todayGoalProgress.setTag(null);
        this.tvCompletionStatus.setTag(null);
        this.tvPlanDate.setTag(null);
        this.tvPlanDesc.setTag(null);
        this.tvTotalCalorie.setTag(null);
        this.tvTotalSteps.setTag(null);
        this.tvWeekPlanName.setTag(null);
        this.tvWeekPlanStatus.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
