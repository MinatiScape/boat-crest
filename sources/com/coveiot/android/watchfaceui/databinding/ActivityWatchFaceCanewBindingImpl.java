package com.coveiot.android.watchfaceui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.watchfaceui.R;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes8.dex */
public class ActivityWatchFaceCanewBindingImpl extends ActivityWatchFaceCanewBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 1);
        sparseIntArray.put(R.id.clSelectedWatchFace, 2);
        sparseIntArray.put(R.id.watchFacePlaceholder, 3);
        sparseIntArray.put(R.id.selectedWatchFace, 4);
        sparseIntArray.put(R.id.selected_watch_face_bg, 5);
        sparseIntArray.put(R.id.selectedRoundedWatchFace, 6);
        sparseIntArray.put(R.id.applyToWatch, 7);
        sparseIntArray.put(R.id.editBtn, 8);
        sparseIntArray.put(R.id.llTabLayout, 9);
        sparseIntArray.put(R.id.tabs, 10);
        sparseIntArray.put(R.id.view_pager, 11);
        sparseIntArray.put(R.id.tvToolTip, 12);
        sparseIntArray.put(R.id.customiseWatchFace, 13);
    }

    public ActivityWatchFaceCanewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 14, i, j));
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

    public ActivityWatchFaceCanewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[7], (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[13], (TextView) objArr[8], (LinearLayout) objArr[9], (ConstraintLayout) objArr[0], (RoundedImageView) objArr[6], (ImageView) objArr[4], (RoundedImageView) objArr[5], (TabLayout) objArr[10], (View) objArr[1], (TextView) objArr[12], (ViewPager) objArr[11], (ImageView) objArr[3]);
        this.h = -1L;
        this.rootLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
