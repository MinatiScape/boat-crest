package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
/* loaded from: classes3.dex */
public class ActivitySessionInsightsDetailsBindingImpl extends ActivitySessionInsightsDetailsBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k = null;
    @Nullable
    public static final SparseIntArray l;
    @NonNull
    public final NestedScrollView h;
    @NonNull
    public final ConstraintLayout i;
    public long j;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 2);
        sparseIntArray.put(R.id.first_guideline, 3);
        sparseIntArray.put(R.id.second_guideline, 4);
        sparseIntArray.put(R.id.clDetails, 5);
        sparseIntArray.put(R.id.clDuration, 6);
        sparseIntArray.put(R.id.ivDuration, 7);
        sparseIntArray.put(R.id.tvDurationTxt, 8);
        sparseIntArray.put(R.id.ivSessionType, 9);
        sparseIntArray.put(R.id.tvSessionType, 10);
        sparseIntArray.put(R.id.tvTime, 11);
        sparseIntArray.put(R.id.iv_goal_achieved, 12);
        sparseIntArray.put(R.id.share_button, 13);
        sparseIntArray.put(R.id.cvSessionInsightsData, 14);
        sparseIntArray.put(R.id.tvSessionInsights, 15);
        sparseIntArray.put(R.id.view, 16);
        sparseIntArray.put(R.id.clSpeed1, 17);
        sparseIntArray.put(R.id.clspeed, 18);
        sparseIntArray.put(R.id.ivSpeed, 19);
        sparseIntArray.put(R.id.tvSpeed, 20);
        sparseIntArray.put(R.id.tvSpeedtxt, 21);
        sparseIntArray.put(R.id.clMaxspeed, 22);
        sparseIntArray.put(R.id.ivMaxSpeed, 23);
        sparseIntArray.put(R.id.tvMaxSpeed, 24);
        sparseIntArray.put(R.id.tvMaxSpeedtxt, 25);
        sparseIntArray.put(R.id.clCalories, 26);
        sparseIntArray.put(R.id.ivCalories, 27);
        sparseIntArray.put(R.id.tvCalories, 28);
        sparseIntArray.put(R.id.tvCaloriestxt, 29);
        sparseIntArray.put(R.id.clHeartRate, 30);
        sparseIntArray.put(R.id.ivHeartRate, 31);
        sparseIntArray.put(R.id.tvHeartRate, 32);
        sparseIntArray.put(R.id.tvHeartRatetxt, 33);
        sparseIntArray.put(R.id.clSteps, 34);
        sparseIntArray.put(R.id.ivSteps, 35);
        sparseIntArray.put(R.id.tvSteps, 36);
        sparseIntArray.put(R.id.tvStepstxt, 37);
        sparseIntArray.put(R.id.tvGoalSummaryTxt, 38);
        sparseIntArray.put(R.id.view_goal_summary, 39);
        sparseIntArray.put(R.id.clGoalSummary, 40);
        sparseIntArray.put(R.id.tvTargetShotsTxt, 41);
        sparseIntArray.put(R.id.tvTargetShots, 42);
        sparseIntArray.put(R.id.tvActualShotsTxt, 43);
        sparseIntArray.put(R.id.tvActualShots, 44);
        sparseIntArray.put(R.id.clGoalSummary2, 45);
        sparseIntArray.put(R.id.goalProgressBar, 46);
        sparseIntArray.put(R.id.tvGoalTxt, 47);
        sparseIntArray.put(R.id.clAnalysis, 48);
        sparseIntArray.put(R.id.tvShotAnalysisTxt, 49);
        sparseIntArray.put(R.id.cv_total_swings, 50);
        sparseIntArray.put(R.id.total_swings, 51);
        sparseIntArray.put(R.id.total_swings_count, 52);
        sparseIntArray.put(R.id.view_total_swings, 53);
        sparseIntArray.put(R.id.pieChart, 54);
        sparseIntArray.put(R.id.cl_missed, 55);
        sparseIntArray.put(R.id.iv_missed, 56);
        sparseIntArray.put(R.id.cl_hit, 57);
        sparseIntArray.put(R.id.iv_hit, 58);
        sparseIntArray.put(R.id.tvBowlingAnalysisTxt, 59);
        sparseIntArray.put(R.id.cv_bowling_analysis, 60);
        sparseIntArray.put(R.id.clBowlingAnalysis, 61);
        sparseIntArray.put(R.id.total_balls, 62);
        sparseIntArray.put(R.id.total_balls_count, 63);
        sparseIntArray.put(R.id.equivalent_over, 64);
        sparseIntArray.put(R.id.equivalent_over_count, 65);
        sparseIntArray.put(R.id.avg_time_over, 66);
        sparseIntArray.put(R.id.avg_time_over_count, 67);
        sparseIntArray.put(R.id.cv_speed_card, 68);
        sparseIntArray.put(R.id.speed_title, 69);
        sparseIntArray.put(R.id.speed_chart, 70);
        sparseIntArray.put(R.id.hr_Zone_cardView, 71);
        sparseIntArray.put(R.id.cl_hr_zone, 72);
        sparseIntArray.put(R.id.hr_zone_title, 73);
        sparseIntArray.put(R.id.iv_hr_zone_info, 74);
        sparseIntArray.put(R.id.ll_zone_warm, 75);
        sparseIntArray.put(R.id.pb_warm, 76);
        sparseIntArray.put(R.id.tv_progress_warm, 77);
        sparseIntArray.put(R.id.ll_zone_fat_burn, 78);
        sparseIntArray.put(R.id.pb_fat_burn, 79);
        sparseIntArray.put(R.id.tv_progress_fat_burn, 80);
        sparseIntArray.put(R.id.ll_zone_cardio, 81);
        sparseIntArray.put(R.id.pb_cardio, 82);
        sparseIntArray.put(R.id.tv_progress_cardio, 83);
        sparseIntArray.put(R.id.ll_zone_threshold, 84);
        sparseIntArray.put(R.id.pb_threshold, 85);
        sparseIntArray.put(R.id.tv_progress_threshold, 86);
        sparseIntArray.put(R.id.ll_zone_peak, 87);
        sparseIntArray.put(R.id.pb_peak, 88);
        sparseIntArray.put(R.id.tv_progress_peak, 89);
        sparseIntArray.put(R.id.cardView_hr_graph, 90);
        sparseIntArray.put(R.id.heartrate_title, 91);
        sparseIntArray.put(R.id.heartrate_value, 92);
        sparseIntArray.put(R.id.heartrate_chart, 93);
        sparseIntArray.put(R.id.cv_rate_session, 94);
        sparseIntArray.put(R.id.rate_session, 95);
        sparseIntArray.put(R.id.rv_feedback, 96);
    }

    public ActivitySessionInsightsDetailsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 97, k, l));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.j = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.j != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 1L;
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

    public ActivitySessionInsightsDetailsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[66], (TextView) objArr[67], (LinearLayout) objArr[90], (ConstraintLayout) objArr[48], (ConstraintLayout) objArr[61], (ConstraintLayout) objArr[26], (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[40], (ConstraintLayout) objArr[45], (ConstraintLayout) objArr[30], (ConstraintLayout) objArr[57], (ConstraintLayout) objArr[72], (ConstraintLayout) objArr[22], (ConstraintLayout) objArr[55], (ConstraintLayout) objArr[17], (ConstraintLayout) objArr[34], (ConstraintLayout) objArr[18], (LinearLayout) objArr[60], (LinearLayout) objArr[94], (LinearLayout) objArr[14], (LinearLayout) objArr[68], (LinearLayout) objArr[50], (TextView) objArr[64], (TextView) objArr[65], (Guideline) objArr[3], (ProgressBar) objArr[46], (LineChart) objArr[93], (TextView) objArr[91], (TextView) objArr[92], (LinearLayout) objArr[71], (TextView) objArr[73], (ImageView) objArr[27], (ImageView) objArr[7], (ImageView) objArr[12], (ImageView) objArr[31], (ImageView) objArr[58], (ImageView) objArr[74], (ImageView) objArr[23], (ImageView) objArr[56], (ImageView) objArr[9], (ImageView) objArr[19], (ImageView) objArr[35], (LinearLayout) objArr[81], (LinearLayout) objArr[78], (LinearLayout) objArr[87], (LinearLayout) objArr[84], (LinearLayout) objArr[75], (ProgressBar) objArr[82], (ProgressBar) objArr[79], (ProgressBar) objArr[88], (ProgressBar) objArr[85], (ProgressBar) objArr[76], (PieChart) objArr[54], (TextView) objArr[95], (RecyclerView) objArr[96], (Guideline) objArr[4], (TextView) objArr[13], (BarChart) objArr[70], (TextView) objArr[69], (View) objArr[2], (TextView) objArr[62], (TextView) objArr[63], (TextView) objArr[51], (TextView) objArr[52], (TextView) objArr[44], (TextView) objArr[43], (TextView) objArr[59], (TextView) objArr[28], (TextView) objArr[29], (TextView) objArr[8], (TextView) objArr[38], (TextView) objArr[47], (TextView) objArr[32], (TextView) objArr[33], (TextView) objArr[24], (TextView) objArr[25], (TextView) objArr[83], (TextView) objArr[80], (TextView) objArr[89], (TextView) objArr[86], (TextView) objArr[77], (TextView) objArr[15], (TextView) objArr[10], (TextView) objArr[49], (TextView) objArr[20], (TextView) objArr[21], (TextView) objArr[36], (TextView) objArr[37], (TextView) objArr[42], (TextView) objArr[41], (TextView) objArr[11], (View) objArr[16], (View) objArr[39], (View) objArr[53]);
        this.j = -1L;
        NestedScrollView nestedScrollView = (NestedScrollView) objArr[0];
        this.h = nestedScrollView;
        nestedScrollView.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[1];
        this.i = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
