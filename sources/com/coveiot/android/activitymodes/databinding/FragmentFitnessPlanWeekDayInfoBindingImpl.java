package com.coveiot.android.activitymodes.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.BR;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.utils.ViewUtilsKt;
/* loaded from: classes2.dex */
public class FragmentFitnessPlanWeekDayInfoBindingImpl extends FragmentFitnessPlanWeekDayInfoBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final NestedScrollView h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.clTopPlanInfo, 20);
        sparseIntArray.put(R.id.clPlanDetail, 21);
        sparseIntArray.put(R.id.topView, 22);
        sparseIntArray.put(R.id.clDistance, 23);
        sparseIntArray.put(R.id.ivDistance, 24);
        sparseIntArray.put(R.id.tvDistance, 25);
        sparseIntArray.put(R.id.middleView, 26);
        sparseIntArray.put(R.id.clCalories, 27);
        sparseIntArray.put(R.id.ivCalories, 28);
        sparseIntArray.put(R.id.tvCalories, 29);
        sparseIntArray.put(R.id.rvWeeks, 30);
        sparseIntArray.put(R.id.rvDays, 31);
        sparseIntArray.put(R.id.clGoalDetails, 32);
        sparseIntArray.put(R.id.tvSelectedDay, 33);
        sparseIntArray.put(R.id.rvTodayGoal, 34);
        sparseIntArray.put(R.id.todayGoalProgress, 35);
        sparseIntArray.put(R.id.tvTodayGoalTotalValue, 36);
        sparseIntArray.put(R.id.ivTodayGoal, 37);
        sparseIntArray.put(R.id.tvTodayGoalName, 38);
        sparseIntArray.put(R.id.tvUnsubscribePlan, 39);
    }

    public FragmentFitnessPlanWeekDayInfoBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 40, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        String str;
        int i;
        int i2;
        String str2;
        int i3;
        int i4;
        String str3;
        int i5;
        int i6;
        long j3;
        int i7;
        String str4;
        int i8;
        long j4;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        long j11;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        Boolean bool = this.mIsPlanStartsTomorrow;
        String str5 = this.mFutureDayInfo;
        String str6 = this.mPlanBg;
        Integer num = this.mDayProgress;
        String str7 = this.mTotalActivitiesAndDistance;
        Integer num2 = this.mDayProgressMax;
        String str8 = this.mCompletedCalories;
        Boolean bool2 = this.mIsRestDay;
        String str9 = this.mGoalCategory;
        String str10 = this.mProgressText;
        String str11 = this.mActivitiesCompleted;
        String str12 = this.mPlanTitle;
        Boolean bool3 = this.mIsFutureDay;
        Boolean bool4 = this.mIsPlanCompleted;
        Boolean bool5 = this.mIsHistoryPlan;
        String str13 = this.mCompletedDistance;
        String str14 = this.mDistanceCovered;
        int i9 = ((j2 & 524289) > 0L ? 1 : ((j2 & 524289) == 0L ? 0 : -1));
        if (i9 != 0) {
            boolean safeUnbox = ViewDataBinding.safeUnbox(bool);
            if (i9 != 0) {
                if (safeUnbox) {
                    j10 = j2 | 33554432;
                    j11 = 34359738368L;
                } else {
                    j10 = j2 | 16777216;
                    j11 = 17179869184L;
                }
                j2 = j10 | j11;
            }
            int i10 = safeUnbox ? 8 : 0;
            i2 = safeUnbox ? 0 : 8;
            int i11 = i10;
            str = str14;
            i = i11;
        } else {
            str = str14;
            i = 0;
            i2 = 0;
        }
        int safeUnbox2 = (j2 & 524296) != 0 ? ViewDataBinding.safeUnbox(num) : 0;
        int safeUnbox3 = (j2 & 524320) != 0 ? ViewDataBinding.safeUnbox(num2) : 0;
        int i12 = ((j2 & 524416) > 0L ? 1 : ((j2 & 524416) == 0L ? 0 : -1));
        if (i12 != 0) {
            boolean safeUnbox4 = ViewDataBinding.safeUnbox(bool2);
            if (i12 != 0) {
                if (safeUnbox4) {
                    j8 = j2 | 8388608;
                    j9 = 536870912;
                } else {
                    j8 = j2 | 4194304;
                    j9 = 268435456;
                }
                j2 = j8 | j9;
            }
            int i13 = safeUnbox4 ? 0 : 8;
            i3 = safeUnbox4 ? 8 : 0;
            int i14 = i13;
            str2 = str9;
            i4 = i14;
        } else {
            str2 = str9;
            i3 = 0;
            i4 = 0;
        }
        int i15 = ((j2 & 540672) > 0L ? 1 : ((j2 & 540672) == 0L ? 0 : -1));
        if (i15 != 0) {
            boolean safeUnbox5 = ViewDataBinding.safeUnbox(bool3);
            if (i15 != 0) {
                if (safeUnbox5) {
                    j6 = j2 | 2147483648L;
                    j7 = 8589934592L;
                } else {
                    j6 = j2 | 1073741824;
                    j7 = 4294967296L;
                }
                j2 = j6 | j7;
            }
            int i16 = safeUnbox5 ? 0 : 8;
            i5 = safeUnbox5 ? 8 : 0;
            int i17 = i16;
            str3 = str5;
            i6 = i17;
        } else {
            str3 = str5;
            i5 = 0;
            i6 = 0;
        }
        int i18 = ((j2 & 557056) > 0L ? 1 : ((j2 & 557056) == 0L ? 0 : -1));
        if (i18 != 0) {
            boolean safeUnbox6 = ViewDataBinding.safeUnbox(bool4);
            if (i18 != 0) {
                if (safeUnbox6) {
                    j4 = j2 | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    j5 = 137438953472L;
                } else {
                    j4 = j2 | PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    j5 = 68719476736L;
                }
                j2 = j4 | j5;
            }
            j3 = j2;
            str4 = this.tvPlanHeader.getResources().getString(safeUnbox6 ? R.string.plan_completed : R.string.completion_progress);
            i7 = safeUnbox6 ? 0 : 8;
        } else {
            j3 = j2;
            i7 = 0;
            str4 = null;
        }
        int i19 = ((j3 & 589824) > 0L ? 1 : ((j3 & 589824) == 0L ? 0 : -1));
        if (i19 != 0) {
            boolean safeUnbox7 = ViewDataBinding.safeUnbox(bool5);
            if (i19 != 0) {
                j3 |= safeUnbox7 ? 134217728L : 67108864L;
            }
            i8 = safeUnbox7 ? 8 : 0;
        } else {
            i8 = 0;
        }
        int i20 = ((j3 & 655360) > 0L ? 1 : ((j3 & 655360) == 0L ? 0 : -1));
        int i21 = ((j3 & 786432) > 0L ? 1 : ((j3 & 786432) == 0L ? 0 : -1));
        if ((j3 & 524416) != 0) {
            this.clActivities.setVisibility(i3);
            this.clRest.setVisibility(i4);
        }
        if ((j3 & 557056) != 0) {
            this.clPlanCompletedDetail.setVisibility(i7);
            TextViewBindingAdapter.setText(this.tvPlanHeader, str4);
        }
        if ((j3 & 540672) != 0) {
            this.clProgress.setVisibility(i5);
            this.tvGoalInfo.setVisibility(i6);
        }
        if ((j3 & 524289) != 0) {
            this.clTopPlanDetail.setVisibility(i);
            this.tvPlanStartsTomorrow.setVisibility(i2);
        }
        if ((j3 & 589824) != 0) {
            this.clUnsubscribePlan.setVisibility(i8);
        }
        if ((j3 & 524292) != 0) {
            ViewUtilsKt.setImage(this.ivPlanBgImg, str6);
        }
        if ((j3 & 524320) != 0) {
            this.planProgressBar.setMax(safeUnbox3);
        }
        if ((j3 & 524296) != 0) {
            this.planProgressBar.setProgress(safeUnbox2);
        }
        if ((j3 & 524352) != 0) {
            TextViewBindingAdapter.setText(this.tvCalorieValue, str8);
        }
        if (i20 != 0) {
            TextViewBindingAdapter.setText(this.tvDistanceValue, str13);
        }
        if ((j3 & 524290) != 0) {
            TextViewBindingAdapter.setText(this.tvGoalInfo, str3);
        }
        if ((j3 & 524544) != 0) {
            TextViewBindingAdapter.setText(this.tvGoalSetup, str2);
        }
        if ((j3 & 528384) != 0) {
            TextViewBindingAdapter.setText(this.tvPlanActivityCompleted, str11);
        }
        if (i21 != 0) {
            TextViewBindingAdapter.setText(this.tvPlanDistanceCovered, str);
        }
        if ((j3 & 524800) != 0) {
            TextViewBindingAdapter.setText(this.tvPlanProgressValue, str10);
        }
        if ((j3 & 532480) != 0) {
            TextViewBindingAdapter.setText(this.tvPlanTitle, str12);
        }
        if ((j3 & 524304) != 0) {
            TextViewBindingAdapter.setText(this.tvTotalActivitiesAndDistance, str7);
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
            this.i = PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setActivitiesCompleted(@Nullable String str) {
        this.mActivitiesCompleted = str;
        synchronized (this) {
            this.i |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        notifyPropertyChanged(BR.activitiesCompleted);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setCompletedCalories(@Nullable String str) {
        this.mCompletedCalories = str;
        synchronized (this) {
            this.i |= 64;
        }
        notifyPropertyChanged(BR.completedCalories);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setCompletedDistance(@Nullable String str) {
        this.mCompletedDistance = str;
        synchronized (this) {
            this.i |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        notifyPropertyChanged(BR.completedDistance);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setDayProgress(@Nullable Integer num) {
        this.mDayProgress = num;
        synchronized (this) {
            this.i |= 8;
        }
        notifyPropertyChanged(BR.dayProgress);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setDayProgressMax(@Nullable Integer num) {
        this.mDayProgressMax = num;
        synchronized (this) {
            this.i |= 32;
        }
        notifyPropertyChanged(BR.dayProgressMax);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setDistanceCovered(@Nullable String str) {
        this.mDistanceCovered = str;
        synchronized (this) {
            this.i |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        notifyPropertyChanged(BR.distanceCovered);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setFutureDayInfo(@Nullable String str) {
        this.mFutureDayInfo = str;
        synchronized (this) {
            this.i |= 2;
        }
        notifyPropertyChanged(BR.futureDayInfo);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setGoalCategory(@Nullable String str) {
        this.mGoalCategory = str;
        synchronized (this) {
            this.i |= 256;
        }
        notifyPropertyChanged(BR.goalCategory);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setIsFutureDay(@Nullable Boolean bool) {
        this.mIsFutureDay = bool;
        synchronized (this) {
            this.i |= 16384;
        }
        notifyPropertyChanged(BR.isFutureDay);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setIsHistoryPlan(@Nullable Boolean bool) {
        this.mIsHistoryPlan = bool;
        synchronized (this) {
            this.i |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        notifyPropertyChanged(BR.isHistoryPlan);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setIsPlanCompleted(@Nullable Boolean bool) {
        this.mIsPlanCompleted = bool;
        synchronized (this) {
            this.i |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(BR.isPlanCompleted);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setIsPlanStartsTomorrow(@Nullable Boolean bool) {
        this.mIsPlanStartsTomorrow = bool;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.isPlanStartsTomorrow);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setIsRestDay(@Nullable Boolean bool) {
        this.mIsRestDay = bool;
        synchronized (this) {
            this.i |= 128;
        }
        notifyPropertyChanged(BR.isRestDay);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setPlanBg(@Nullable String str) {
        this.mPlanBg = str;
        synchronized (this) {
            this.i |= 4;
        }
        notifyPropertyChanged(BR.planBg);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setPlanHeader(@Nullable String str) {
        this.mPlanHeader = str;
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setPlanTitle(@Nullable String str) {
        this.mPlanTitle = str;
        synchronized (this) {
            this.i |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        notifyPropertyChanged(BR.planTitle);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setProgressText(@Nullable String str) {
        this.mProgressText = str;
        synchronized (this) {
            this.i |= 512;
        }
        notifyPropertyChanged(BR.progressText);
        super.requestRebind();
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setTotalActivities(@Nullable String str) {
        this.mTotalActivities = str;
    }

    @Override // com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding
    public void setTotalActivitiesAndDistance(@Nullable String str) {
        this.mTotalActivitiesAndDistance = str;
        synchronized (this) {
            this.i |= 16;
        }
        notifyPropertyChanged(BR.totalActivitiesAndDistance);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.isPlanStartsTomorrow == i) {
            setIsPlanStartsTomorrow((Boolean) obj);
        } else if (BR.futureDayInfo == i) {
            setFutureDayInfo((String) obj);
        } else if (BR.planBg == i) {
            setPlanBg((String) obj);
        } else if (BR.dayProgress == i) {
            setDayProgress((Integer) obj);
        } else if (BR.totalActivitiesAndDistance == i) {
            setTotalActivitiesAndDistance((String) obj);
        } else if (BR.dayProgressMax == i) {
            setDayProgressMax((Integer) obj);
        } else if (BR.completedCalories == i) {
            setCompletedCalories((String) obj);
        } else if (BR.isRestDay == i) {
            setIsRestDay((Boolean) obj);
        } else if (BR.goalCategory == i) {
            setGoalCategory((String) obj);
        } else if (BR.progressText == i) {
            setProgressText((String) obj);
        } else if (BR.totalActivities == i) {
            setTotalActivities((String) obj);
        } else if (BR.planHeader == i) {
            setPlanHeader((String) obj);
        } else if (BR.activitiesCompleted == i) {
            setActivitiesCompleted((String) obj);
        } else if (BR.planTitle == i) {
            setPlanTitle((String) obj);
        } else if (BR.isFutureDay == i) {
            setIsFutureDay((Boolean) obj);
        } else if (BR.isPlanCompleted == i) {
            setIsPlanCompleted((Boolean) obj);
        } else if (BR.isHistoryPlan == i) {
            setIsHistoryPlan((Boolean) obj);
        } else if (BR.completedDistance == i) {
            setCompletedDistance((String) obj);
        } else if (BR.distanceCovered != i) {
            return false;
        } else {
            setDistanceCovered((String) obj);
        }
        return true;
    }

    public FragmentFitnessPlanWeekDayInfoBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[15], (ConstraintLayout) objArr[27], (ConstraintLayout) objArr[23], (ConstraintLayout) objArr[32], (ConstraintLayout) objArr[12], (ConstraintLayout) objArr[21], (ConstraintLayout) objArr[17], (ConstraintLayout) objArr[18], (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[20], (ConstraintLayout) objArr[19], (ImageView) objArr[28], (ImageView) objArr[24], (ImageView) objArr[1], (ImageView) objArr[37], (View) objArr[26], (ProgressBar) objArr[10], (RecyclerView) objArr[31], (RecyclerView) objArr[34], (RecyclerView) objArr[30], (ProgressBar) objArr[35], (View) objArr[22], (TextView) objArr[14], (TextView) objArr[29], (TextView) objArr[25], (TextView) objArr[13], (TextView) objArr[16], (TextView) objArr[2], (TextView) objArr[8], (TextView) objArr[9], (TextView) objArr[7], (TextView) objArr[11], (TextView) objArr[5], (TextView) objArr[3], (TextView) objArr[33], (TextView) objArr[38], (TextView) objArr[36], (TextView) objArr[4], (TextView) objArr[39]);
        this.i = -1L;
        this.clActivities.setTag(null);
        this.clPlanCompletedDetail.setTag(null);
        this.clProgress.setTag(null);
        this.clRest.setTag(null);
        this.clTopPlanDetail.setTag(null);
        this.clUnsubscribePlan.setTag(null);
        this.ivPlanBgImg.setTag(null);
        NestedScrollView nestedScrollView = (NestedScrollView) objArr[0];
        this.h = nestedScrollView;
        nestedScrollView.setTag(null);
        this.planProgressBar.setTag(null);
        this.tvCalorieValue.setTag(null);
        this.tvDistanceValue.setTag(null);
        this.tvGoalInfo.setTag(null);
        this.tvGoalSetup.setTag(null);
        this.tvPlanActivityCompleted.setTag(null);
        this.tvPlanDistanceCovered.setTag(null);
        this.tvPlanHeader.setTag(null);
        this.tvPlanProgressValue.setTag(null);
        this.tvPlanStartsTomorrow.setTag(null);
        this.tvPlanTitle.setTag(null);
        this.tvTotalActivitiesAndDistance.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
