package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.fitnesschallenges.databinding.NoChallengesBannerBinding;
import com.coveiot.android.theme.databinding.ArcCircularProgressBarBinding;
import com.coveiot.android.theme.databinding.LayoutDashboardDoMoreWithYourWatchGridItemBinding;
import com.coveiot.android.theme.databinding.ListItemWeekPlanLayoutBinding;
import com.coveiot.android.theme.databinding.RoundedCardNavLayoutBinding;
/* loaded from: classes3.dex */
public class FragmentFitnessBindingImpl extends FragmentFitnessBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final NestedScrollView h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(75);
        j = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"arc_circular_progress_bar"}, new int[]{5}, new int[]{R.layout.arc_circular_progress_bar});
        includedLayouts.setIncludes(2, new String[]{"layout_dashboard_do_more_with_your_watch_grid_item", "list_item_week_plan_layout"}, new int[]{6, 7}, new int[]{R.layout.layout_dashboard_do_more_with_your_watch_grid_item, R.layout.list_item_week_plan_layout});
        includedLayouts.setIncludes(3, new String[]{"rounded_card_nav_layout"}, new int[]{9}, new int[]{R.layout.rounded_card_nav_layout});
        includedLayouts.setIncludes(4, new String[]{"no_challenges_banner"}, new int[]{8}, new int[]{R.layout.no_challenges_banner});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.vertical_guideline_start, 10);
        sparseIntArray.put(R.id.vertical_guideline_end, 11);
        sparseIntArray.put(R.id.tv_goal_status, 12);
        sparseIntArray.put(R.id.iv_share, 13);
        sparseIntArray.put(R.id.tv_goal_desc, 14);
        sparseIntArray.put(R.id.clSelectedOptionValues, 15);
        sparseIntArray.put(R.id.ibPrevious, 16);
        sparseIntArray.put(R.id.tvSelectedTypeValue, 17);
        sparseIntArray.put(R.id.ibForward, 18);
        sparseIntArray.put(R.id.barrier, 19);
        sparseIntArray.put(R.id.tv_progress_value, 20);
        sparseIntArray.put(R.id.tv_goal_acheived, 21);
        sparseIntArray.put(R.id.cl_goals, 22);
        sparseIntArray.put(R.id.clGuestSteps, 23);
        sparseIntArray.put(R.id.iv_goal, 24);
        sparseIntArray.put(R.id.tv_goal_name, 25);
        sparseIntArray.put(R.id.tv_goal_value, 26);
        sparseIntArray.put(R.id.tv_goal_target, 27);
        sparseIntArray.put(R.id.clGuestSleep, 28);
        sparseIntArray.put(R.id.iv_goal1, 29);
        sparseIntArray.put(R.id.tv_goal_name1, 30);
        sparseIntArray.put(R.id.tv_goal_value1, 31);
        sparseIntArray.put(R.id.tv_goal_target1, 32);
        sparseIntArray.put(R.id.rv_goals, 33);
        sparseIntArray.put(R.id.view, 34);
        sparseIntArray.put(R.id.cl_goal_insights_main, 35);
        sparseIntArray.put(R.id.tv_goals_insights, 36);
        sparseIntArray.put(R.id.cl_goals_insights, 37);
        sparseIntArray.put(R.id.rv_calendar_goals_insights, 38);
        sparseIntArray.put(R.id.cl_daily_insights, 39);
        sparseIntArray.put(R.id.tv_steps_insights, 40);
        sparseIntArray.put(R.id.tv_sleep_insights, 41);
        sparseIntArray.put(R.id.cl_range_insights, 42);
        sparseIntArray.put(R.id.tv_goals_achieved, 43);
        sparseIntArray.put(R.id.tv_goals_achieved_value, 44);
        sparseIntArray.put(R.id.tv_goals_insights_desc, 45);
        sparseIntArray.put(R.id.tv_fitness_journey, 46);
        sparseIntArray.put(R.id.tv_fitness_plan, 47);
        sparseIntArray.put(R.id.cl_activity_history_main, 48);
        sparseIntArray.put(R.id.tv_activity_history, 49);
        sparseIntArray.put(R.id.cl_no_activity, 50);
        sparseIntArray.put(R.id.iv_no_history, 51);
        sparseIntArray.put(R.id.clOops, 52);
        sparseIntArray.put(R.id.tvOops, 53);
        sparseIntArray.put(R.id.tvNoActivities, 54);
        sparseIntArray.put(R.id.tvInfo, 55);
        sparseIntArray.put(R.id.clGetStarted, 56);
        sparseIntArray.put(R.id.tvGetStarted, 57);
        sparseIntArray.put(R.id.rv_activity_history, 58);
        sparseIntArray.put(R.id.linearLayoutDots, 59);
        sparseIntArray.put(R.id.imageViewDot1, 60);
        sparseIntArray.put(R.id.imageViewDot2, 61);
        sparseIntArray.put(R.id.cl_workout_videos_main, 62);
        sparseIntArray.put(R.id.tv_cult_videos, 63);
        sparseIntArray.put(R.id.tv_cult_videos_desc, 64);
        sparseIntArray.put(R.id.rv_cult_videos, 65);
        sparseIntArray.put(R.id.cl_fitness_blog_main, 66);
        sparseIntArray.put(R.id.tv_fitness_blogs, 67);
        sparseIntArray.put(R.id.tv_fitness_blogs_desc, 68);
        sparseIntArray.put(R.id.rv_fitness_blogs, 69);
        sparseIntArray.put(R.id.clChallengeHeaderSection, 70);
        sparseIntArray.put(R.id.tvFitnessChallengeHeader, 71);
        sparseIntArray.put(R.id.tvFitnessChallengeViewMore, 72);
        sparseIntArray.put(R.id.rvFitnessChallenge, 73);
        sparseIntArray.put(R.id.challengeLinearLayoutDots, 74);
    }

    public FragmentFitnessBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 75, j, k));
    }

    public final boolean a(ArcCircularProgressBarBinding arcCircularProgressBarBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.i |= 16;
            }
            return true;
        }
        return false;
    }

    public final boolean b(LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.i |= 8;
            }
            return true;
        }
        return false;
    }

    public final boolean c(ListItemWeekPlanLayoutBinding listItemWeekPlanLayoutBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.i |= 1;
            }
            return true;
        }
        return false;
    }

    public final boolean d(NoChallengesBannerBinding noChallengesBannerBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.i |= 2;
            }
            return true;
        }
        return false;
    }

    public final boolean e(RoundedCardNavLayoutBinding roundedCardNavLayoutBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.i |= 4;
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
        ViewDataBinding.executeBindingsOn(this.arcProgressBar);
        ViewDataBinding.executeBindingsOn(this.fitnessJourney);
        ViewDataBinding.executeBindingsOn(this.fitnessJourneyOngoing);
        ViewDataBinding.executeBindingsOn(this.noChallengeView);
        ViewDataBinding.executeBindingsOn(this.viewFitnessChallengeDashboardHeader);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.arcProgressBar.hasPendingBindings() || this.fitnessJourney.hasPendingBindings() || this.fitnessJourneyOngoing.hasPendingBindings() || this.noChallengeView.hasPendingBindings() || this.viewFitnessChallengeDashboardHeader.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 32L;
        }
        this.arcProgressBar.invalidateAll();
        this.fitnessJourney.invalidateAll();
        this.fitnessJourneyOngoing.invalidateAll();
        this.noChallengeView.invalidateAll();
        this.viewFitnessChallengeDashboardHeader.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return false;
                        }
                        return a((ArcCircularProgressBarBinding) obj, i2);
                    }
                    return b((LayoutDashboardDoMoreWithYourWatchGridItemBinding) obj, i2);
                }
                return e((RoundedCardNavLayoutBinding) obj, i2);
            }
            return d((NoChallengesBannerBinding) obj, i2);
        }
        return c((ListItemWeekPlanLayoutBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.arcProgressBar.setLifecycleOwner(lifecycleOwner);
        this.fitnessJourney.setLifecycleOwner(lifecycleOwner);
        this.fitnessJourneyOngoing.setLifecycleOwner(lifecycleOwner);
        this.noChallengeView.setLifecycleOwner(lifecycleOwner);
        this.viewFitnessChallengeDashboardHeader.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentFitnessBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 5, (ArcCircularProgressBarBinding) objArr[5], (Barrier) objArr[19], (LinearLayout) objArr[74], (ConstraintLayout) objArr[48], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[70], (ConstraintLayout) objArr[39], (ConstraintLayout) objArr[66], (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[56], (ConstraintLayout) objArr[35], (ConstraintLayout) objArr[22], (ConstraintLayout) objArr[37], (ConstraintLayout) objArr[28], (ConstraintLayout) objArr[23], (ConstraintLayout) objArr[50], (ConstraintLayout) objArr[52], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[42], (ConstraintLayout) objArr[15], (ConstraintLayout) objArr[62], (ConstraintLayout) objArr[3], (LayoutDashboardDoMoreWithYourWatchGridItemBinding) objArr[6], (ListItemWeekPlanLayoutBinding) objArr[7], (ImageButton) objArr[18], (ImageButton) objArr[16], (ImageView) objArr[60], (ImageView) objArr[61], (ImageView) objArr[24], (ImageView) objArr[29], (ImageView) objArr[51], (ImageButton) objArr[13], (LinearLayout) objArr[59], (NoChallengesBannerBinding) objArr[8], (RecyclerView) objArr[58], (RecyclerView) objArr[38], (RecyclerView) objArr[65], (RecyclerView) objArr[69], (RecyclerView) objArr[73], (RecyclerView) objArr[33], (TextView) objArr[49], (TextView) objArr[63], (TextView) objArr[64], (TextView) objArr[67], (TextView) objArr[68], (TextView) objArr[71], (TextView) objArr[72], (TextView) objArr[46], (TextView) objArr[47], (TextView) objArr[57], (TextView) objArr[21], (TextView) objArr[14], (TextView) objArr[25], (TextView) objArr[30], (TextView) objArr[12], (TextView) objArr[27], (TextView) objArr[32], (TextView) objArr[26], (TextView) objArr[31], (TextView) objArr[43], (TextView) objArr[44], (TextView) objArr[36], (TextView) objArr[45], (TextView) objArr[55], (TextView) objArr[54], (TextView) objArr[53], (TextView) objArr[20], (TextView) objArr[17], (TextView) objArr[41], (TextView) objArr[40], (Guideline) objArr[11], (Guideline) objArr[10], (View) objArr[34], (RoundedCardNavLayoutBinding) objArr[9]);
        this.i = -1L;
        setContainedBinding(this.arcProgressBar);
        this.clBannerLayout.setTag(null);
        this.clFitnessJourneyMain.setTag(null);
        this.clProgress.setTag(null);
        this.fitnessChallenge.setTag(null);
        setContainedBinding(this.fitnessJourney);
        setContainedBinding(this.fitnessJourneyOngoing);
        NestedScrollView nestedScrollView = (NestedScrollView) objArr[0];
        this.h = nestedScrollView;
        nestedScrollView.setTag(null);
        setContainedBinding(this.noChallengeView);
        setContainedBinding(this.viewFitnessChallengeDashboardHeader);
        setRootTag(view);
        invalidateAll();
    }
}
