package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class SensAiDetailsBindingImpl extends SensAiDetailsBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.first_guideline, 1);
        sparseIntArray.put(R.id.second_guideline, 2);
        sparseIntArray.put(R.id.clSelectedOptionValues, 3);
        sparseIntArray.put(R.id.tvSelectedTypeValue, 4);
        sparseIntArray.put(R.id.clDetails, 5);
        sparseIntArray.put(R.id.ibShare, 6);
        sparseIntArray.put(R.id.ivSensAIBg, 7);
        sparseIntArray.put(R.id.ivCenterVitalBg, 8);
        sparseIntArray.put(R.id.tvTitle, 9);
        sparseIntArray.put(R.id.cl_overall_stats_details, 10);
        sparseIntArray.put(R.id.cl_duration, 11);
        sparseIntArray.put(R.id.tv_duration, 12);
        sparseIntArray.put(R.id.tv_duration_value, 13);
        sparseIntArray.put(R.id.view1, 14);
        sparseIntArray.put(R.id.cl_total_sessions, 15);
        sparseIntArray.put(R.id.tv_total_sessions, 16);
        sparseIntArray.put(R.id.tv_total_sesions_value, 17);
        sparseIntArray.put(R.id.view2, 18);
        sparseIntArray.put(R.id.cl_goal_acheived, 19);
        sparseIntArray.put(R.id.tv_goal_acheived, 20);
        sparseIntArray.put(R.id.tv_goal_acheived_value, 21);
        sparseIntArray.put(R.id.cl_session_details, 22);
        sparseIntArray.put(R.id.cl_session_time, 23);
        sparseIntArray.put(R.id.tv_session_time, 24);
        sparseIntArray.put(R.id.tv_session_time_value, 25);
        sparseIntArray.put(R.id.view, 26);
        sparseIntArray.put(R.id.cl_sessions_duration, 27);
        sparseIntArray.put(R.id.tv_sessions_duration, 28);
        sparseIntArray.put(R.id.tv_sessions_duration_value, 29);
    }

    public SensAiDetailsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 30, j, k));
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

    public SensAiDetailsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[11], (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[10], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[22], (ConstraintLayout) objArr[23], (ConstraintLayout) objArr[27], (ConstraintLayout) objArr[15], (Guideline) objArr[1], (ImageButton) objArr[6], (ImageView) objArr[8], (ImageView) objArr[7], (Guideline) objArr[2], (TextView) objArr[12], (TextView) objArr[13], (TextView) objArr[20], (TextView) objArr[21], (TextView) objArr[4], (TextView) objArr[24], (TextView) objArr[25], (TextView) objArr[28], (TextView) objArr[29], (TextView) objArr[9], (TextView) objArr[17], (TextView) objArr[16], (View) objArr[26], (View) objArr[14], (View) objArr[18]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
