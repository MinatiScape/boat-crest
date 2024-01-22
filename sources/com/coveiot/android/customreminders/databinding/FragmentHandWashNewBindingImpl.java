package com.coveiot.android.customreminders.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.coveiot.android.customreminders.BR;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.databinding.ReminderLayoutNewBinding;
import com.coveiot.android.theme.databinding.RepeatLayoutNewBinding;
/* loaded from: classes3.dex */
public class FragmentHandWashNewBindingImpl extends FragmentHandWashNewBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(4);
        j = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"reminder_layout_new", "repeat_layout_new"}, new int[]{1, 2}, new int[]{R.layout.reminder_layout_new, R.layout.repeat_layout_new});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(com.coveiot.android.customreminders.R.id.saveButton, 3);
    }

    public FragmentHandWashNewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 4, j, k));
    }

    public final boolean a(ReminderLayoutNewBinding reminderLayoutNewBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 2;
            }
            return true;
        }
        return false;
    }

    public final boolean b(RepeatLayoutNewBinding repeatLayoutNewBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.i = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.reminderLayout);
        ViewDataBinding.executeBindingsOn(this.repeatLayout);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.reminderLayout.hasPendingBindings() || this.repeatLayout.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 4L;
        }
        this.reminderLayout.invalidateAll();
        this.repeatLayout.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i != 1) {
                return false;
            }
            return a((ReminderLayoutNewBinding) obj, i2);
        }
        return b((RepeatLayoutNewBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.reminderLayout.setLifecycleOwner(lifecycleOwner);
        this.repeatLayout.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentHandWashNewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (ReminderLayoutNewBinding) objArr[1], (RepeatLayoutNewBinding) objArr[2], (Button) objArr[3]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setContainedBinding(this.reminderLayout);
        setContainedBinding(this.repeatLayout);
        setRootTag(view);
        invalidateAll();
    }
}
