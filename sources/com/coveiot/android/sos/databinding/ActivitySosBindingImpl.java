package com.coveiot.android.sos.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.coveiot.android.sos.BR;
import com.coveiot.android.sos.R;
import com.coveiot.android.theme.databinding.TooltipDialogTwoButtonsBinding;
/* loaded from: classes7.dex */
public class ActivitySosBindingImpl extends ActivitySosBinding {
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
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(16);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"item_sos", "tooltip_dialog_two_buttons"}, new int[]{3, 4}, new int[]{R.layout.item_sos, com.coveiot.android.theme.R.layout.tooltip_dialog_two_buttons});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 2);
        sparseIntArray.put(R.id.rank_main, 5);
        sparseIntArray.put(R.id.tv_sos_desc, 6);
        sparseIntArray.put(R.id.view1, 7);
        sparseIntArray.put(R.id.tv_emergency_contact, 8);
        sparseIntArray.put(R.id.tv_sos_functionality, 9);
        sparseIntArray.put(R.id.tv_sos_point1, 10);
        sparseIntArray.put(R.id.tv_sos_point2, 11);
        sparseIntArray.put(R.id.tv_sos_point3, 12);
        sparseIntArray.put(R.id.tv_sos_point4, 13);
        sparseIntArray.put(R.id.tv_sos_point5, 14);
        sparseIntArray.put(R.id.tv_sos_terms_condition, 15);
    }

    public ActivitySosBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 16, k, l));
    }

    public final boolean a(ItemSosBinding itemSosBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 2;
            }
            return true;
        }
        return false;
    }

    public final boolean b(TooltipDialogTwoButtonsBinding tooltipDialogTwoButtonsBinding, int i) {
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
        ViewDataBinding.executeBindingsOn(this.itemSos);
        ViewDataBinding.executeBindingsOn(this.tooltip);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.j != 0) {
                return true;
            }
            return this.itemSos.hasPendingBindings() || this.tooltip.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 4L;
        }
        this.itemSos.invalidateAll();
        this.tooltip.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i != 1) {
                return false;
            }
            return a((ItemSosBinding) obj, i2);
        }
        return b((TooltipDialogTwoButtonsBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.itemSos.setLifecycleOwner(lifecycleOwner);
        this.tooltip.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public ActivitySosBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (ItemSosBinding) objArr[3], (NestedScrollView) objArr[5], (View) objArr[2], (TooltipDialogTwoButtonsBinding) objArr[4], (TextView) objArr[8], (TextView) objArr[6], (TextView) objArr[9], (TextView) objArr[10], (TextView) objArr[11], (TextView) objArr[12], (TextView) objArr[13], (TextView) objArr[14], (TextView) objArr[15], (View) objArr[7]);
        this.j = -1L;
        setContainedBinding(this.itemSos);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) objArr[1];
        this.i = constraintLayout2;
        constraintLayout2.setTag(null);
        setContainedBinding(this.tooltip);
        setRootTag(view);
        invalidateAll();
    }
}
