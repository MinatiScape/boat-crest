package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class ActivityRemoteCameraBindingImpl extends ActivityRemoteCameraBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.previewView, 2);
        sparseIntArray.put(R.id.edit, 3);
        sparseIntArray.put(R.id.captureVideo, 4);
        sparseIntArray.put(R.id.capturePhoto, 5);
        sparseIntArray.put(R.id.toggleCamera, 6);
        sparseIntArray.put(R.id.openGallery, 7);
        sparseIntArray.put(R.id.controls, 8);
    }

    public ActivityRemoteCameraBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 9, i, j));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        synchronized (this) {
            j2 = this.h;
            this.h = 0L;
        }
        Boolean bool = this.mIsFlashSupported;
        int i2 = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        int i3 = 0;
        if (i2 != 0) {
            boolean safeUnbox = ViewDataBinding.safeUnbox(bool);
            if (i2 != 0) {
                j2 |= safeUnbox ? 8L : 4L;
            }
            if (!safeUnbox) {
                i3 = 8;
            }
        }
        if ((j2 & 3) != 0) {
            this.flash.setVisibility(i3);
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
            this.h = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // com.coveiot.android.boat.databinding.ActivityRemoteCameraBinding
    public void setIsFlashSupported(@Nullable Boolean bool) {
        this.mIsFlashSupported = bool;
        synchronized (this) {
            this.h |= 1;
        }
        notifyPropertyChanged(48);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        if (48 == i2) {
            setIsFlashSupported((Boolean) obj);
            return true;
        }
        return false;
    }

    public ActivityRemoteCameraBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageButton) objArr[5], (ImageButton) objArr[4], (NestedScrollView) objArr[8], (ImageButton) objArr[3], (ImageButton) objArr[1], (ImageButton) objArr[7], (PreviewView) objArr[2], (CoordinatorLayout) objArr[0], (ImageButton) objArr[6]);
        this.h = -1L;
        this.flash.setTag(null);
        this.root.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
