package com.coveiot.android.customreminders.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.customreminders.BR;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.databinding.RepeatLayoutNewBinding;
/* loaded from: classes3.dex */
public class MedicineReminderItemBindingImpl extends MedicineReminderItemBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k;
    @Nullable
    public static final SparseIntArray l;
    @NonNull
    public final ConstraintLayout h;
    @NonNull
    public final ConstraintLayout i;
    public long j;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(20);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"repeat_layout_new"}, new int[]{2}, new int[]{R.layout.repeat_layout_new});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.cvMedicine, 3);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.tv_medicine_name_lable, 4);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.edt_reminder_name, 5);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.tv_characters_left, 6);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.reminderLayout, 7);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.cl_start_time, 8);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.start_time_title, 9);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.tv_start_date, 10);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.cl_end_time, 11);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.end_time_title, 12);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.tv_end_date, 13);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.reminder_time_label, 14);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.rcv_time_infos, 15);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.add_time_info, 16);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.tv_notify_me_label, 17);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.sp_notify_me, 18);
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.doneButton, 19);
    }

    public MedicineReminderItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 20, k, l));
    }

    public final boolean a(RepeatLayoutNewBinding repeatLayoutNewBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.j = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.repeatLayout);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.j != 0) {
                return true;
            }
            return this.repeatLayout.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 4L;
        }
        this.repeatLayout.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((RepeatLayoutNewBinding) obj, i2);
    }

    @Override // com.coveiot.android.customreminders.databinding.MedicineReminderItemBinding
    public void setIsInEditMode(@Nullable Boolean bool) {
        this.mIsInEditMode = bool;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.repeatLayout.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.isInEditMode == i) {
            setIsInEditMode((Boolean) obj);
            return true;
        }
        return false;
    }

    public MedicineReminderItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (TextView) objArr[16], (ConstraintLayout) objArr[11], (ConstraintLayout) objArr[8], (CardView) objArr[3], (Button) objArr[19], (EditText) objArr[5], (TextView) objArr[12], (RecyclerView) objArr[15], (LinearLayout) objArr[7], (TextView) objArr[14], (RepeatLayoutNewBinding) objArr[2], (Spinner) objArr[18], (TextView) objArr[9], (TextView) objArr[6], (TextView) objArr[13], (TextView) objArr[4], (TextView) objArr[17], (TextView) objArr[10]);
        this.j = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) objArr[1];
        this.i = constraintLayout2;
        constraintLayout2.setTag(null);
        setContainedBinding(this.repeatLayout);
        setRootTag(view);
        invalidateAll();
    }
}
