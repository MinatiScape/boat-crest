package com.coveiot.android.watchfaceui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.watchfaceui.R;
import com.github.siyamed.shapeimageview.RoundedImageView;
/* loaded from: classes8.dex */
public class WatchFaceHolderCosmosProBindingImpl extends WatchFaceHolderCosmosProBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.ivWaveBg, 1);
        sparseIntArray.put(R.id.ivWatch, 2);
        sparseIntArray.put(R.id.watchfacePlaceholderImgV, 3);
        sparseIntArray.put(R.id.selected_watch_face_bg, 4);
    }

    public WatchFaceHolderCosmosProBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, i, j));
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

    public WatchFaceHolderCosmosProBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[0], (ImageView) objArr[2], (ImageView) objArr[1], (RoundedImageView) objArr[4], (RoundedImageView) objArr[3]);
        this.h = -1L;
        this.clMainData.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}