package com.coveiot.android.respiratoryrate.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.respiratoryrate.R;
import com.github.mikephil.charting.charts.LineChart;
/* loaded from: classes6.dex */
public class FragmentRespiratoryRateHistoryBindingImpl extends FragmentRespiratoryRateHistoryBinding {
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
        sparseIntArray.put(R.id.toolbar, 1);
        sparseIntArray.put(R.id.toolbar_layout, 2);
        sparseIntArray.put(R.id.back, 3);
        sparseIntArray.put(R.id.title, 4);
        sparseIntArray.put(R.id.share_iv, 5);
        sparseIntArray.put(R.id.tablayout, 6);
        sparseIntArray.put(R.id.daily_tv, 7);
        sparseIntArray.put(R.id.weekly_tv, 8);
        sparseIntArray.put(R.id.monthly_tv, 9);
        sparseIntArray.put(R.id.linechart_respiratory_rate, 10);
        sparseIntArray.put(R.id.date_tv, 11);
        sparseIntArray.put(R.id.cl_bottom_content, 12);
        sparseIntArray.put(R.id.cl_min_max_view, 13);
        sparseIntArray.put(R.id.img_respiratory_rate_min, 14);
        sparseIntArray.put(R.id.tv_respiratory_rate_min, 15);
        sparseIntArray.put(R.id.view1, 16);
        sparseIntArray.put(R.id.img_respiratory_rate_max, 17);
        sparseIntArray.put(R.id.tv_respiratory_rate_max, 18);
        sparseIntArray.put(R.id.cl_rv_view, 19);
        sparseIntArray.put(R.id.ll_rv_header, 20);
        sparseIntArray.put(R.id.v_separator, 21);
        sparseIntArray.put(R.id.rv_min_max, 22);
        sparseIntArray.put(R.id.no_data_tv, 23);
        sparseIntArray.put(R.id.disclaimer_info_lay, 24);
        sparseIntArray.put(R.id.disclaimer_info, 25);
    }

    public FragmentRespiratoryRateHistoryBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 26, j, k));
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

    public FragmentRespiratoryRateHistoryBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageButton) objArr[3], (ConstraintLayout) objArr[12], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[19], (TextView) objArr[7], (TextView) objArr[11], (TextView) objArr[25], (RelativeLayout) objArr[24], (ImageView) objArr[17], (ImageView) objArr[14], (LineChart) objArr[10], (LinearLayout) objArr[20], (TextView) objArr[9], (TextView) objArr[23], (RecyclerView) objArr[22], (ImageView) objArr[5], (LinearLayout) objArr[6], (TextView) objArr[4], (Toolbar) objArr[1], (LinearLayout) objArr[2], (TextView) objArr[18], (TextView) objArr[15], (View) objArr[21], (View) objArr[16], (TextView) objArr[8]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
