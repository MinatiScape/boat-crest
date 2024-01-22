package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.TroubleshootingTestModel;
import com.coveiot.android.theme.compundview.DottedCircleProgressBarCustom;
/* loaded from: classes3.dex */
public class ListItemTroubleshootTestingBindingImpl extends ListItemTroubleshootTestingBinding {
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
        sparseIntArray.put(R.id.clMainLayout, 1);
        sparseIntArray.put(R.id.trouble_shoot_item_no, 2);
        sparseIntArray.put(R.id.trouble_shoot_title_tv, 3);
        sparseIntArray.put(R.id.trouble_shoot_progress_tv, 4);
        sparseIntArray.put(R.id.trouble_shoot_failure_msg, 5);
        sparseIntArray.put(R.id.fix_this_tv, 6);
        sparseIntArray.put(R.id.clTroubleshootProgress, 7);
        sparseIntArray.put(R.id.cl_trouble_shoot_progress, 8);
        sparseIntArray.put(R.id.trouble_shoot_progressV, 9);
        sparseIntArray.put(R.id.trouble_shoot_progress_img, 10);
        sparseIntArray.put(R.id.cl_appNotificationList, 11);
        sparseIntArray.put(R.id.notification_setting_list, 12);
    }

    public ListItemTroubleshootTestingBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 13, j, k));
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
            this.i = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.coveiot.android.boat.databinding.ListItemTroubleshootTestingBinding
    public void setTroubleshootingTestModel(@Nullable TroubleshootingTestModel troubleshootingTestModel) {
        this.mTroubleshootingTestModel = troubleshootingTestModel;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (128 == i) {
            setTroubleshootingTestModel((TroubleshootingTestModel) obj);
            return true;
        }
        return false;
    }

    public ListItemTroubleshootTestingBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[11], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[7], (TextView) objArr[6], (RecyclerView) objArr[12], (TextView) objArr[5], (TextView) objArr[2], (ImageView) objArr[10], (TextView) objArr[4], (DottedCircleProgressBarCustom) objArr[9], (TextView) objArr[3]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
