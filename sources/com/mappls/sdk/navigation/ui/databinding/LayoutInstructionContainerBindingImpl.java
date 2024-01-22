package com.mappls.sdk.navigation.ui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.common.NonSwipeableViewPager;
/* loaded from: classes11.dex */
public class LayoutInstructionContainerBindingImpl extends LayoutInstructionContainerBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.top_strip_layout, 1);
        sparseIntArray.put(R.id.navigation_info_layout_new, 2);
        sparseIntArray.put(R.id.navigation_strip_left_image_button, 3);
        sparseIntArray.put(R.id.navigation_strip_right_image_button, 4);
        sparseIntArray.put(R.id.other_info_text_view, 5);
    }

    public LayoutInstructionContainerBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 6, i, j));
    }

    public LayoutInstructionContainerBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (NonSwipeableViewPager) objArr[2], (ImageButton) objArr[3], (ImageButton) objArr[4], (TextView) objArr[5], (RelativeLayout) objArr[1]);
        this.h = -1L;
        ((ConstraintLayout) objArr[0]).setTag(null);
        setRootTag(view);
        invalidateAll();
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
}
