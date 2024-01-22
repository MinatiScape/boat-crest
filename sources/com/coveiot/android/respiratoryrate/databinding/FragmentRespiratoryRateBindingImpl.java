package com.coveiot.android.respiratoryrate.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.respiratoryrate.R;
import com.github.mikephil.charting.charts.LineChart;
/* loaded from: classes6.dex */
public class FragmentRespiratoryRateBindingImpl extends FragmentRespiratoryRateBinding {
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
        sparseIntArray.put(R.id.calendar_image, 1);
        sparseIntArray.put(R.id.history_image, 2);
        sparseIntArray.put(R.id.tv_date5, 3);
        sparseIntArray.put(R.id.tv_date3, 4);
        sparseIntArray.put(R.id.tv_date1, 5);
        sparseIntArray.put(R.id.tv_date4, 6);
        sparseIntArray.put(R.id.tv_date2, 7);
        sparseIntArray.put(R.id.share_image, 8);
        sparseIntArray.put(R.id.img_respiratory_rate, 9);
        sparseIntArray.put(R.id.tv_respiratory_rate, 10);
        sparseIntArray.put(R.id.img_respiratory_rate_min, 11);
        sparseIntArray.put(R.id.tv_respiratory_rate_min, 12);
        sparseIntArray.put(R.id.view1, 13);
        sparseIntArray.put(R.id.img_respiratory_rate_max, 14);
        sparseIntArray.put(R.id.tv_respiratory_rate_max, 15);
        sparseIntArray.put(R.id.tv_day, 16);
        sparseIntArray.put(R.id.v_empty, 17);
        sparseIntArray.put(R.id.frame_lay, 18);
        sparseIntArray.put(R.id.linechart_respiratory_rate, 19);
        sparseIntArray.put(R.id.no_data_tv, 20);
        sparseIntArray.put(R.id.no_settings_enabled_tv, 21);
        sparseIntArray.put(R.id.ll_not_synced_msg_tv, 22);
        sparseIntArray.put(R.id.no_sync_msg_tv, 23);
        sparseIntArray.put(R.id.tv_disclaimer, 24);
    }

    public FragmentRespiratoryRateBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 25, j, k));
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

    public FragmentRespiratoryRateBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[1], (FrameLayout) objArr[18], (ImageView) objArr[2], (ImageView) objArr[9], (ImageView) objArr[14], (ImageView) objArr[11], (LineChart) objArr[19], (LinearLayout) objArr[22], (TextView) objArr[20], (TextView) objArr[21], (TextView) objArr[23], (ImageView) objArr[8], (TextView) objArr[5], (TextView) objArr[7], (TextView) objArr[4], (TextView) objArr[6], (TextView) objArr[3], (TextView) objArr[16], (TextView) objArr[24], (TextView) objArr[10], (TextView) objArr[15], (TextView) objArr[12], (View) objArr[17], (View) objArr[13]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
