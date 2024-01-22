package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.databinding.ReminderLayoutNewBinding;
import com.coveiot.android.theme.databinding.RepeatLayoutNewBinding;
/* loaded from: classes3.dex */
public class ActivitySedentaryReminderBindingImpl extends ActivitySedentaryReminderBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(8);
        j = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"repeat_layout_new", "reminder_layout_new"}, new int[]{3, 4}, new int[]{R.layout.repeat_layout_new, R.layout.reminder_layout_new});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 2);
        sparseIntArray.put(R.id.cl_reminder, 5);
        sparseIntArray.put(R.id.switch_reminder, 6);
        sparseIntArray.put(R.id.btnSave, 7);
    }

    public ActivitySedentaryReminderBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 8, j, k));
    }

    public final boolean a(ReminderLayoutNewBinding reminderLayoutNewBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.i |= 2;
            }
            return true;
        }
        return false;
    }

    public final boolean b(RepeatLayoutNewBinding repeatLayoutNewBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.i |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        Boolean bool = this.mShowRepeatLayout;
        int i = ((j2 & 12) > 0L ? 1 : ((j2 & 12) == 0L ? 0 : -1));
        int i2 = 0;
        if (i != 0) {
            boolean safeUnbox = ViewDataBinding.safeUnbox(bool);
            if (i != 0) {
                j2 |= safeUnbox ? 32L : 16L;
            }
            if (!safeUnbox) {
                i2 = 8;
            }
        }
        if ((j2 & 12) != 0) {
            this.repeatLayout.getRoot().setVisibility(i2);
        }
        ViewDataBinding.executeBindingsOn(this.repeatLayout);
        ViewDataBinding.executeBindingsOn(this.reminderLayout);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.repeatLayout.hasPendingBindings() || this.reminderLayout.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 8L;
        }
        this.repeatLayout.invalidateAll();
        this.reminderLayout.invalidateAll();
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
        this.repeatLayout.setLifecycleOwner(lifecycleOwner);
        this.reminderLayout.setLifecycleOwner(lifecycleOwner);
    }

    @Override // com.coveiot.android.boat.databinding.ActivitySedentaryReminderBinding
    public void setShowRepeatLayout(@Nullable Boolean bool) {
        this.mShowRepeatLayout = bool;
        synchronized (this) {
            this.i |= 4;
        }
        notifyPropertyChanged(105);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (105 == i) {
            setShowRepeatLayout((Boolean) obj);
            return true;
        }
        return false;
    }

    public ActivitySedentaryReminderBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (Button) objArr[7], (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[1], (ReminderLayoutNewBinding) objArr[4], (RepeatLayoutNewBinding) objArr[3], (SwitchCompat) objArr[6], (View) objArr[2]);
        this.i = -1L;
        this.clRepeatLayout.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setContainedBinding(this.reminderLayout);
        setContainedBinding(this.repeatLayout);
        setRootTag(view);
        invalidateAll();
    }
}
