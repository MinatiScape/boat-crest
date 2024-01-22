package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
/* loaded from: classes3.dex */
public class ActivitySensAiDetailsBindingImpl extends ActivitySensAiDetailsBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k;
    @Nullable
    public static final SparseIntArray l;
    @NonNull
    public final NestedScrollView h;
    @NonNull
    public final ConstraintLayout i;
    public long j;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(100);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"sens_ai_details"}, new int[]{3}, new int[]{R.layout.sens_ai_details});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 2);
        sparseIntArray.put(R.id.first_guideline, 4);
        sparseIntArray.put(R.id.second_guideline, 5);
        sparseIntArray.put(R.id.tvSessionInsights, 6);
        sparseIntArray.put(R.id.cl_session_highlights, 7);
        sparseIntArray.put(R.id.cl_speed, 8);
        sparseIntArray.put(R.id.clSpeed1, 9);
        sparseIntArray.put(R.id.clspeed, 10);
        sparseIntArray.put(R.id.ivSpeed, 11);
        sparseIntArray.put(R.id.tvSpeed, 12);
        sparseIntArray.put(R.id.tvSpeedtxt, 13);
        sparseIntArray.put(R.id.view, 14);
        sparseIntArray.put(R.id.clMaxspeed, 15);
        sparseIntArray.put(R.id.ivMaxSpeed, 16);
        sparseIntArray.put(R.id.tvMaxSpeed, 17);
        sparseIntArray.put(R.id.tvMaxSpeedtxt, 18);
        sparseIntArray.put(R.id.cl_vital_details, 19);
        sparseIntArray.put(R.id.clCalories, 20);
        sparseIntArray.put(R.id.ivCalories, 21);
        sparseIntArray.put(R.id.tvCalories, 22);
        sparseIntArray.put(R.id.tvCaloriestxt, 23);
        sparseIntArray.put(R.id.view1, 24);
        sparseIntArray.put(R.id.clHeartRate, 25);
        sparseIntArray.put(R.id.ivHeartRate, 26);
        sparseIntArray.put(R.id.tvHeartRate, 27);
        sparseIntArray.put(R.id.tvHeartRatetxt, 28);
        sparseIntArray.put(R.id.view2, 29);
        sparseIntArray.put(R.id.clSteps, 30);
        sparseIntArray.put(R.id.ivSteps, 31);
        sparseIntArray.put(R.id.tvSteps, 32);
        sparseIntArray.put(R.id.tvStepstxt, 33);
        sparseIntArray.put(R.id.cl_goal_acheived, 34);
        sparseIntArray.put(R.id.tvGoalAchievedTxt, 35);
        sparseIntArray.put(R.id.clGoalDetails, 36);
        sparseIntArray.put(R.id.ivGoalAcheived, 37);
        sparseIntArray.put(R.id.tvGoalAchieved, 38);
        sparseIntArray.put(R.id.tvGoalAchievedTarget, 39);
        sparseIntArray.put(R.id.tvGoalAchievedMsg, 40);
        sparseIntArray.put(R.id.clGoalSummary, 41);
        sparseIntArray.put(R.id.tvGoalSummaryTxt, 42);
        sparseIntArray.put(R.id.clGoalSummary2, 43);
        sparseIntArray.put(R.id.tvGoalCompletion, 44);
        sparseIntArray.put(R.id.tvGoalCompletionValue, 45);
        sparseIntArray.put(R.id.goalProgressBar, 46);
        sparseIntArray.put(R.id.tvGoalTxt, 47);
        sparseIntArray.put(R.id.barrierGoal, 48);
        sparseIntArray.put(R.id.view_goal_summary, 49);
        sparseIntArray.put(R.id.cl_arm_speed, 50);
        sparseIntArray.put(R.id.speed_title, 51);
        sparseIntArray.put(R.id.graphBg, 52);
        sparseIntArray.put(R.id.speedChart, 53);
        sparseIntArray.put(R.id.tvNoDataFound, 54);
        sparseIntArray.put(R.id.cl_hit_analysis, 55);
        sparseIntArray.put(R.id.tv_hit_analysis, 56);
        sparseIntArray.put(R.id.pieChart, 57);
        sparseIntArray.put(R.id.gridView, 58);
        sparseIntArray.put(R.id.cl_hit_analysis_details, 59);
        sparseIntArray.put(R.id.tv_total_balls, 60);
        sparseIntArray.put(R.id.cl_balls_details, 61);
        sparseIntArray.put(R.id.cl_total_balls_bowled, 62);
        sparseIntArray.put(R.id.tv_total_balls_bowled, 63);
        sparseIntArray.put(R.id.tv_total_balls_bowled_value, 64);
        sparseIntArray.put(R.id.view_total_balls_bowled, 65);
        sparseIntArray.put(R.id.cl_equivalent_overs, 66);
        sparseIntArray.put(R.id.tv_equivalent_overs, 67);
        sparseIntArray.put(R.id.tv_equivalent_overs_value, 68);
        sparseIntArray.put(R.id.view_equivalent_overs, 69);
        sparseIntArray.put(R.id.cl_avg_time_per_over, 70);
        sparseIntArray.put(R.id.tv_avg_time_per_over, 71);
        sparseIntArray.put(R.id.tv_avg_time_per_over_value, 72);
        sparseIntArray.put(R.id.barrier, 73);
        sparseIntArray.put(R.id.view_arm_speed, 74);
        sparseIntArray.put(R.id.cl_hr_graph, 75);
        sparseIntArray.put(R.id.heartrate_title, 76);
        sparseIntArray.put(R.id.heartrate_value, 77);
        sparseIntArray.put(R.id.heartrate_chart, 78);
        sparseIntArray.put(R.id.cl_hr_zone, 79);
        sparseIntArray.put(R.id.hr_zone_title, 80);
        sparseIntArray.put(R.id.iv_hr_zone_info, 81);
        sparseIntArray.put(R.id.ll_zone_warm, 82);
        sparseIntArray.put(R.id.pb_warm, 83);
        sparseIntArray.put(R.id.tv_progress_warm, 84);
        sparseIntArray.put(R.id.ll_zone_fat_burn, 85);
        sparseIntArray.put(R.id.pb_fat_burn, 86);
        sparseIntArray.put(R.id.tv_progress_fat_burn, 87);
        sparseIntArray.put(R.id.ll_zone_cardio, 88);
        sparseIntArray.put(R.id.pb_cardio, 89);
        sparseIntArray.put(R.id.tv_progress_cardio, 90);
        sparseIntArray.put(R.id.ll_zone_threshold, 91);
        sparseIntArray.put(R.id.pb_threshold, 92);
        sparseIntArray.put(R.id.tv_progress_threshold, 93);
        sparseIntArray.put(R.id.ll_zone_peak, 94);
        sparseIntArray.put(R.id.pb_peak, 95);
        sparseIntArray.put(R.id.tv_progress_peak, 96);
        sparseIntArray.put(R.id.cl_rate_sesssion, 97);
        sparseIntArray.put(R.id.rate_session, 98);
        sparseIntArray.put(R.id.rv_feedback, 99);
    }

    public ActivitySensAiDetailsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 100, k, l));
    }

    public final boolean a(SensAiDetailsBinding sensAiDetailsBinding, int i) {
        if (i == 0) {
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
        ViewDataBinding.executeBindingsOn(this.sensAiDetails);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.j != 0) {
                return true;
            }
            return this.sensAiDetails.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 2L;
        }
        this.sensAiDetails.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((SensAiDetailsBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.sensAiDetails.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public ActivitySensAiDetailsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (Barrier) objArr[73], (Barrier) objArr[48], (ConstraintLayout) objArr[50], (ConstraintLayout) objArr[70], (ConstraintLayout) objArr[61], (ConstraintLayout) objArr[20], (ConstraintLayout) objArr[66], (ConstraintLayout) objArr[34], (ConstraintLayout) objArr[36], (ConstraintLayout) objArr[41], (ConstraintLayout) objArr[43], (ConstraintLayout) objArr[25], (ConstraintLayout) objArr[55], (ConstraintLayout) objArr[59], (ConstraintLayout) objArr[75], (ConstraintLayout) objArr[79], (ConstraintLayout) objArr[15], (ConstraintLayout) objArr[97], (ConstraintLayout) objArr[7], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[9], (ConstraintLayout) objArr[30], (ConstraintLayout) objArr[62], (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[10], (Guideline) objArr[4], (ProgressBar) objArr[46], (ConstraintLayout) objArr[52], (RecyclerView) objArr[58], (LineChart) objArr[78], (TextView) objArr[76], (TextView) objArr[77], (TextView) objArr[80], (ImageView) objArr[21], (ImageView) objArr[37], (ImageView) objArr[26], (ImageView) objArr[81], (ImageView) objArr[16], (ImageView) objArr[11], (ImageView) objArr[31], (LinearLayout) objArr[88], (LinearLayout) objArr[85], (LinearLayout) objArr[94], (LinearLayout) objArr[91], (LinearLayout) objArr[82], (ProgressBar) objArr[89], (ProgressBar) objArr[86], (ProgressBar) objArr[95], (ProgressBar) objArr[92], (ProgressBar) objArr[83], (PieChart) objArr[57], (TextView) objArr[98], (RecyclerView) objArr[99], (Guideline) objArr[5], (SensAiDetailsBinding) objArr[3], (RoundedBarChart) objArr[53], (TextView) objArr[51], (View) objArr[2], (TextView) objArr[71], (TextView) objArr[72], (TextView) objArr[22], (TextView) objArr[23], (TextView) objArr[67], (TextView) objArr[68], (TextView) objArr[38], (TextView) objArr[40], (TextView) objArr[39], (TextView) objArr[35], (TextView) objArr[44], (TextView) objArr[45], (TextView) objArr[42], (TextView) objArr[47], (TextView) objArr[27], (TextView) objArr[28], (TextView) objArr[56], (TextView) objArr[17], (TextView) objArr[18], (TextView) objArr[54], (TextView) objArr[90], (TextView) objArr[87], (TextView) objArr[96], (TextView) objArr[93], (TextView) objArr[84], (TextView) objArr[6], (TextView) objArr[12], (TextView) objArr[13], (TextView) objArr[32], (TextView) objArr[33], (TextView) objArr[60], (TextView) objArr[63], (TextView) objArr[64], (View) objArr[14], (View) objArr[24], (View) objArr[29], (View) objArr[74], (View) objArr[69], (View) objArr[49], (View) objArr[65]);
        this.j = -1L;
        NestedScrollView nestedScrollView = (NestedScrollView) objArr[0];
        this.h = nestedScrollView;
        nestedScrollView.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[1];
        this.i = constraintLayout;
        constraintLayout.setTag(null);
        setContainedBinding(this.sensAiDetails);
        setRootTag(view);
        invalidateAll();
    }
}
