package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class FragmentSensAiShareDataBindingImpl extends FragmentSensAiShareDataBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.root_layout, 1);
        sparseIntArray.put(R.id.iv_app_logo, 2);
        sparseIntArray.put(R.id.iv_powered_cove, 3);
        sparseIntArray.put(R.id.cl_profile_details, 4);
        sparseIntArray.put(R.id.user_pic, 5);
        sparseIntArray.put(R.id.user_name, 6);
        sparseIntArray.put(R.id.week, 7);
        sparseIntArray.put(R.id.clDetails, 8);
        sparseIntArray.put(R.id.ivSensAIBg, 9);
        sparseIntArray.put(R.id.ivCenterVitalBg, 10);
        sparseIntArray.put(R.id.tvTitle, 11);
        sparseIntArray.put(R.id.tvActivity, 12);
        sparseIntArray.put(R.id.cl_activity_details, 13);
        sparseIntArray.put(R.id.cl_activity1, 14);
        sparseIntArray.put(R.id.iv_activity1, 15);
        sparseIntArray.put(R.id.tv_activity1, 16);
        sparseIntArray.put(R.id.tv_activity1_value, 17);
        sparseIntArray.put(R.id.cl_activity2, 18);
        sparseIntArray.put(R.id.iv_activity2, 19);
        sparseIntArray.put(R.id.tv_activity2, 20);
        sparseIntArray.put(R.id.tv_activity2_value, 21);
        sparseIntArray.put(R.id.view_vertical, 22);
        sparseIntArray.put(R.id.view_activity, 23);
        sparseIntArray.put(R.id.cl_activity3, 24);
        sparseIntArray.put(R.id.iv_activity3, 25);
        sparseIntArray.put(R.id.tv_activity3, 26);
        sparseIntArray.put(R.id.tv_activity3_value, 27);
        sparseIntArray.put(R.id.cl_activity4, 28);
        sparseIntArray.put(R.id.iv_activity4, 29);
        sparseIntArray.put(R.id.tv_activity4, 30);
        sparseIntArray.put(R.id.tv_activity4_value, 31);
        sparseIntArray.put(R.id.view1, 32);
        sparseIntArray.put(R.id.tvHandSpeed, 33);
        sparseIntArray.put(R.id.llHandSpeed, 34);
        sparseIntArray.put(R.id.clHandSpeed1, 35);
        sparseIntArray.put(R.id.ivHandSpeed1, 36);
        sparseIntArray.put(R.id.tvHandSpeed1, 37);
        sparseIntArray.put(R.id.tvHandSpeed1Txt, 38);
        sparseIntArray.put(R.id.clHandSpeed2, 39);
        sparseIntArray.put(R.id.viewHandSpeed2, 40);
        sparseIntArray.put(R.id.ivHandSpeed2, 41);
        sparseIntArray.put(R.id.tvHandSpeed2, 42);
        sparseIntArray.put(R.id.tvHandSpeed2Txt, 43);
        sparseIntArray.put(R.id.clHandSpeed3, 44);
        sparseIntArray.put(R.id.viewHandSpeed3, 45);
        sparseIntArray.put(R.id.ivHandSpeed3, 46);
        sparseIntArray.put(R.id.tvHandSpeed3, 47);
        sparseIntArray.put(R.id.tvHandSpeed3Txt, 48);
        sparseIntArray.put(R.id.view2, 49);
        sparseIntArray.put(R.id.tvVitals, 50);
        sparseIntArray.put(R.id.llVitals, 51);
        sparseIntArray.put(R.id.clVitals1, 52);
        sparseIntArray.put(R.id.ivVitals1, 53);
        sparseIntArray.put(R.id.tvVitals1, 54);
        sparseIntArray.put(R.id.tvVitals1Txt, 55);
        sparseIntArray.put(R.id.clVitals2, 56);
        sparseIntArray.put(R.id.viewVitals2, 57);
        sparseIntArray.put(R.id.ivVitals2, 58);
        sparseIntArray.put(R.id.tvVitals2, 59);
        sparseIntArray.put(R.id.tvVitals2Txt, 60);
        sparseIntArray.put(R.id.clVitals3, 61);
        sparseIntArray.put(R.id.viewVitals3, 62);
        sparseIntArray.put(R.id.ivVitals3, 63);
        sparseIntArray.put(R.id.tvVitals3, 64);
        sparseIntArray.put(R.id.tvVitals3Txt, 65);
        sparseIntArray.put(R.id.disclaimer_info, 66);
    }

    public FragmentSensAiShareDataBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 67, i, j));
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

    public FragmentSensAiShareDataBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[14], (ConstraintLayout) objArr[18], (ConstraintLayout) objArr[24], (ConstraintLayout) objArr[28], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[35], (ConstraintLayout) objArr[39], (ConstraintLayout) objArr[44], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[52], (ConstraintLayout) objArr[56], (ConstraintLayout) objArr[61], (TextView) objArr[66], (ImageView) objArr[15], (ImageView) objArr[19], (ImageView) objArr[25], (ImageView) objArr[29], (ImageView) objArr[2], (ImageView) objArr[10], (ImageView) objArr[36], (ImageView) objArr[41], (ImageView) objArr[46], (ImageView) objArr[3], (ImageView) objArr[9], (ImageView) objArr[53], (ImageView) objArr[58], (ImageView) objArr[63], (LinearLayout) objArr[34], (LinearLayout) objArr[51], (ConstraintLayout) objArr[1], (NestedScrollView) objArr[0], (TextView) objArr[12], (TextView) objArr[16], (TextView) objArr[17], (TextView) objArr[20], (TextView) objArr[21], (TextView) objArr[26], (TextView) objArr[27], (TextView) objArr[30], (TextView) objArr[31], (TextView) objArr[33], (TextView) objArr[37], (TextView) objArr[38], (TextView) objArr[42], (TextView) objArr[43], (TextView) objArr[47], (TextView) objArr[48], (TextView) objArr[11], (TextView) objArr[50], (TextView) objArr[54], (TextView) objArr[55], (TextView) objArr[59], (TextView) objArr[60], (TextView) objArr[64], (TextView) objArr[65], (TextView) objArr[6], (ImageView) objArr[5], (View) objArr[32], (View) objArr[49], (View) objArr[23], (View) objArr[40], (View) objArr[45], (View) objArr[22], (View) objArr[57], (View) objArr[62], (TextView) objArr[7]);
        this.h = -1L;
        this.sensAiShareView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
