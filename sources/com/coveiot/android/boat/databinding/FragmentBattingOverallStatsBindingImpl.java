package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
/* loaded from: classes3.dex */
public class FragmentBattingOverallStatsBindingImpl extends FragmentBattingOverallStatsBinding {
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
        sparseIntArray.put(R.id.first_guideline, 1);
        sparseIntArray.put(R.id.second_guideline, 2);
        sparseIntArray.put(R.id.rvDays, 3);
        sparseIntArray.put(R.id.ivDuration, 4);
        sparseIntArray.put(R.id.tv_duration, 5);
        sparseIntArray.put(R.id.tv_duration_value, 6);
        sparseIntArray.put(R.id.view_duration, 7);
        sparseIntArray.put(R.id.clSession, 8);
        sparseIntArray.put(R.id.ivCalender, 9);
        sparseIntArray.put(R.id.tvSessionsTxt, 10);
        sparseIntArray.put(R.id.tvSessions, 11);
        sparseIntArray.put(R.id.clGoalAchieved, 12);
        sparseIntArray.put(R.id.ivGoal, 13);
        sparseIntArray.put(R.id.tvGoalTxt, 14);
        sparseIntArray.put(R.id.tvGoals, 15);
        sparseIntArray.put(R.id.view_total_balls, 16);
        sparseIntArray.put(R.id.cv_total_swings, 17);
        sparseIntArray.put(R.id.pieChart, 18);
        sparseIntArray.put(R.id.total_swings, 19);
        sparseIntArray.put(R.id.total_swings_count, 20);
        sparseIntArray.put(R.id.total_hit, 21);
        sparseIntArray.put(R.id.total_hit_count, 22);
        sparseIntArray.put(R.id.cl_missed, 23);
        sparseIntArray.put(R.id.iv_missed, 24);
        sparseIntArray.put(R.id.cl_hit, 25);
        sparseIntArray.put(R.id.iv_hit, 26);
        sparseIntArray.put(R.id.tvAcheivedPerc, 27);
        sparseIntArray.put(R.id.view_hit, 28);
        sparseIntArray.put(R.id.tv_hit_perc, 29);
        sparseIntArray.put(R.id.lineChartHitPerc, 30);
        sparseIntArray.put(R.id.clspeed, 31);
        sparseIntArray.put(R.id.clAvgHandSpeed, 32);
        sparseIntArray.put(R.id.ivHandSpeed, 33);
        sparseIntArray.put(R.id.tvAvgHandSpeedTxt, 34);
        sparseIntArray.put(R.id.tvAvgHandSpeed, 35);
        sparseIntArray.put(R.id.clMaxHandSpeed, 36);
        sparseIntArray.put(R.id.ivMaxHandSpeed, 37);
        sparseIntArray.put(R.id.tvMaxHandSpeedTxt, 38);
        sparseIntArray.put(R.id.tvMaxHandSpeed, 39);
        sparseIntArray.put(R.id.view, 40);
        sparseIntArray.put(R.id.cardView_hand_speed_graph, 41);
        sparseIntArray.put(R.id.avg_arm_speed_title, 42);
        sparseIntArray.put(R.id.lineChartArmSpeed, 43);
    }

    public FragmentBattingOverallStatsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 44, j, k));
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

    public FragmentBattingOverallStatsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[42], (LinearLayout) objArr[41], (ConstraintLayout) objArr[32], (ConstraintLayout) objArr[12], (ConstraintLayout) objArr[25], (ConstraintLayout) objArr[36], (ConstraintLayout) objArr[23], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[31], (LinearLayout) objArr[17], (Guideline) objArr[1], (ImageView) objArr[9], (ImageView) objArr[4], (ImageView) objArr[13], (ImageView) objArr[33], (ImageView) objArr[26], (ImageView) objArr[37], (ImageView) objArr[24], (LineChart) objArr[43], (LineChart) objArr[30], (PieChart) objArr[18], (RecyclerView) objArr[3], (Guideline) objArr[2], (TextView) objArr[21], (TextView) objArr[22], (TextView) objArr[19], (TextView) objArr[20], (TextView) objArr[27], (TextView) objArr[35], (TextView) objArr[34], (TextView) objArr[5], (TextView) objArr[6], (TextView) objArr[14], (TextView) objArr[15], (TextView) objArr[29], (TextView) objArr[39], (TextView) objArr[38], (TextView) objArr[11], (TextView) objArr[10], (View) objArr[40], (View) objArr[7], (View) objArr[28], (View) objArr[16]);
        this.i = -1L;
        NestedScrollView nestedScrollView = (NestedScrollView) objArr[0];
        this.h = nestedScrollView;
        nestedScrollView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
