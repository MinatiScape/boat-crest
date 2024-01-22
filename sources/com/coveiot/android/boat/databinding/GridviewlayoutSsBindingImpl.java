package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.github.anastr.speedviewlib.SpeedView;
/* loaded from: classes3.dex */
public class GridviewlayoutSsBindingImpl extends GridviewlayoutSsBinding {
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
        sparseIntArray.put(R.id.cl_ss_hr_hrv_spo2, 1);
        sparseIntArray.put(R.id.cv_hrspo2hrv, 2);
        sparseIntArray.put(R.id.iv_ss, 3);
        sparseIntArray.put(R.id.tv_title_name, 4);
        sparseIntArray.put(R.id.tv_heart_min, 5);
        sparseIntArray.put(R.id.tv_min_rate, 6);
        sparseIntArray.put(R.id.tv_heart_max, 7);
        sparseIntArray.put(R.id.tv_max_rate, 8);
        sparseIntArray.put(R.id.cl_ss_em_badges, 9);
        sparseIntArray.put(R.id.iv_stress_score, 10);
        sparseIntArray.put(R.id.tv_energy_meter_name, 11);
        sparseIntArray.put(R.id.speedView_home_ss, 12);
        sparseIntArray.put(R.id.tv_energy_meter_status, 13);
        sparseIntArray.put(R.id.cl_ss_sleep, 14);
        sparseIntArray.put(R.id.iv_sleep_score, 15);
        sparseIntArray.put(R.id.tv_sleep_score_name, 16);
        sparseIntArray.put(R.id.guideline1, 17);
        sparseIntArray.put(R.id.tv_sleep_score_status, 18);
        sparseIntArray.put(R.id.iv_sleep_face, 19);
        sparseIntArray.put(R.id.tv_sleep_score_hour, 20);
        sparseIntArray.put(R.id.tv_sleep_hour_unit, 21);
        sparseIntArray.put(R.id.tv_sleep_score_minute, 22);
        sparseIntArray.put(R.id.tv_sleep_minute_unit, 23);
        sparseIntArray.put(R.id.cl_cl_ss_stress, 24);
        sparseIntArray.put(R.id.iv_stress, 25);
        sparseIntArray.put(R.id.tv_stress_score_name, 26);
        sparseIntArray.put(R.id.tv_stress_score_status, 27);
        sparseIntArray.put(R.id.tv_stress_desc, 28);
        sparseIntArray.put(R.id.cl_cl_ss_running, 29);
        sparseIntArray.put(R.id.iv_running, 30);
        sparseIntArray.put(R.id.tv_running_name, 31);
        sparseIntArray.put(R.id.ic_timer, 32);
        sparseIntArray.put(R.id.ic_heart_small, 33);
        sparseIntArray.put(R.id.ic_calorie_small, 34);
        sparseIntArray.put(R.id.tv_running_timer, 35);
        sparseIntArray.put(R.id.tv_heart, 36);
        sparseIntArray.put(R.id.tv_cal, 37);
        sparseIntArray.put(R.id.cl_ss_badge, 38);
        sparseIntArray.put(R.id.iv_badge_ss, 39);
        sparseIntArray.put(R.id.tv_badge_name, 40);
        sparseIntArray.put(R.id.iv_badge_earned, 41);
        sparseIntArray.put(R.id.tv_badge_desc, 42);
        sparseIntArray.put(R.id.guideline20, 43);
    }

    public GridviewlayoutSsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
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

    public GridviewlayoutSsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[29], (ConstraintLayout) objArr[24], (ConstraintLayout) objArr[38], (ConstraintLayout) objArr[9], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[14], (CardView) objArr[2], (Guideline) objArr[17], (Guideline) objArr[43], (ImageView) objArr[34], (ImageView) objArr[33], (ImageView) objArr[32], (ImageView) objArr[41], (ImageView) objArr[39], (ImageView) objArr[30], (ImageView) objArr[19], (ImageView) objArr[15], (ImageView) objArr[3], (ImageView) objArr[25], (ImageView) objArr[10], (SpeedView) objArr[12], (TextView) objArr[42], (TextView) objArr[40], (TextView) objArr[37], (TextView) objArr[11], (TextView) objArr[13], (TextView) objArr[36], (TextView) objArr[7], (TextView) objArr[5], (TextView) objArr[8], (TextView) objArr[6], (TextView) objArr[31], (TextView) objArr[35], (TextView) objArr[21], (TextView) objArr[23], (TextView) objArr[20], (TextView) objArr[22], (TextView) objArr[16], (TextView) objArr[18], (TextView) objArr[28], (TextView) objArr[26], (TextView) objArr[27], (TextView) objArr[4]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
