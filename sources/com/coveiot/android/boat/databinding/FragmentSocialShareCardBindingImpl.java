package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class FragmentSocialShareCardBindingImpl extends FragmentSocialShareCardBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.fsshareconstraintlayout, 1);
        sparseIntArray.put(R.id.iv_social_share_app_logo, 2);
        sparseIntArray.put(R.id.ivsocial_share_powered_cove, 3);
        sparseIntArray.put(R.id.tv_username, 4);
        sparseIntArray.put(R.id.tv_fitness, 5);
        sparseIntArray.put(R.id.cv_steps_card, 6);
        sparseIntArray.put(R.id.cl_steps, 7);
        sparseIntArray.put(R.id.pb_steps, 8);
        sparseIntArray.put(R.id.ic_steps_image, 9);
        sparseIntArray.put(R.id.tv_steps_day, 10);
        sparseIntArray.put(R.id.iv_social_share_arrow, 11);
        sparseIntArray.put(R.id.view4, 12);
        sparseIntArray.put(R.id.tv_steps, 13);
        sparseIntArray.put(R.id.tv_steps_label, 14);
        sparseIntArray.put(R.id.cal_image, 15);
        sparseIntArray.put(R.id.dis_image, 16);
        sparseIntArray.put(R.id.tv_calories_card_unit, 17);
        sparseIntArray.put(R.id.tv_calories, 18);
        sparseIntArray.put(R.id.tv_distance_card, 19);
        sparseIntArray.put(R.id.tv_distance_card_unit_desc, 20);
        sparseIntArray.put(R.id.tv_distance, 21);
        sparseIntArray.put(R.id.tv_yesterday, 22);
        sparseIntArray.put(R.id.tv_steps_calculate, 23);
        sparseIntArray.put(R.id.tv_ss_yesterday_goal, 24);
        sparseIntArray.put(R.id.tv_ss_goal, 25);
        sparseIntArray.put(R.id.fl_ss, 26);
        sparseIntArray.put(R.id.recyclerView_ss, 27);
        sparseIntArray.put(R.id.tv_ss_disclaimer, 28);
    }

    public FragmentSocialShareCardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 29, i, j));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.h = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.h != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.h = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }

    public FragmentSocialShareCardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[15], (ConstraintLayout) objArr[7], (CardView) objArr[6], (ImageView) objArr[16], (FrameLayout) objArr[26], (ConstraintLayout) objArr[1], (ScrollView) objArr[0], (ImageView) objArr[9], (ImageView) objArr[2], (ImageView) objArr[11], (ImageView) objArr[3], (ProgressBar) objArr[8], (RecyclerView) objArr[27], (TextView) objArr[18], (TextView) objArr[17], (TextView) objArr[21], (TextView) objArr[19], (TextView) objArr[20], (TextView) objArr[5], (TextView) objArr[28], (TextView) objArr[25], (TextView) objArr[24], (TextView) objArr[13], (TextView) objArr[23], (TextView) objArr[10], (TextView) objArr[14], (TextView) objArr[4], (TextView) objArr[22], (View) objArr[12]);
        this.h = -1L;
        this.fssharescrollview.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
