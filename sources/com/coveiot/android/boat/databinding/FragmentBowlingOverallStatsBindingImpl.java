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
/* loaded from: classes3.dex */
public class FragmentBowlingOverallStatsBindingImpl extends FragmentBowlingOverallStatsBinding {
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
        sparseIntArray.put(R.id.tv_duration_text, 5);
        sparseIntArray.put(R.id.tvDuration, 6);
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
        sparseIntArray.put(R.id.clTotalBalls, 17);
        sparseIntArray.put(R.id.tvTotalBallsTxt, 18);
        sparseIntArray.put(R.id.tvTotalBalls, 19);
        sparseIntArray.put(R.id.tvAcheivedPerc, 20);
        sparseIntArray.put(R.id.clspeed, 21);
        sparseIntArray.put(R.id.clAvgArmSpeed, 22);
        sparseIntArray.put(R.id.ivArmSpeed, 23);
        sparseIntArray.put(R.id.tvAvgArmSpeedTxt, 24);
        sparseIntArray.put(R.id.tvAvgArmSpeed, 25);
        sparseIntArray.put(R.id.clMaxArmSpeed, 26);
        sparseIntArray.put(R.id.ivMaxArmSpeed, 27);
        sparseIntArray.put(R.id.tvMaxArmSpeedTxt, 28);
        sparseIntArray.put(R.id.tvMaxArmSpeed, 29);
        sparseIntArray.put(R.id.view, 30);
        sparseIntArray.put(R.id.cardView_hand_speed_graph, 31);
        sparseIntArray.put(R.id.avg_arm_speed_title, 32);
        sparseIntArray.put(R.id.lineChartArmSpeed, 33);
    }

    public FragmentBowlingOverallStatsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 34, j, k));
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

    public FragmentBowlingOverallStatsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[32], (LinearLayout) objArr[31], (ConstraintLayout) objArr[22], (ConstraintLayout) objArr[12], (ConstraintLayout) objArr[26], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[17], (ConstraintLayout) objArr[21], (Guideline) objArr[1], (ImageView) objArr[23], (ImageView) objArr[9], (ImageView) objArr[4], (ImageView) objArr[13], (ImageView) objArr[27], (LineChart) objArr[33], (RecyclerView) objArr[3], (Guideline) objArr[2], (TextView) objArr[20], (TextView) objArr[25], (TextView) objArr[24], (TextView) objArr[6], (TextView) objArr[5], (TextView) objArr[14], (TextView) objArr[15], (TextView) objArr[29], (TextView) objArr[28], (TextView) objArr[11], (TextView) objArr[10], (TextView) objArr[19], (TextView) objArr[18], (View) objArr[30], (View) objArr[7], (View) objArr[16]);
        this.i = -1L;
        NestedScrollView nestedScrollView = (NestedScrollView) objArr[0];
        this.h = nestedScrollView;
        nestedScrollView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
