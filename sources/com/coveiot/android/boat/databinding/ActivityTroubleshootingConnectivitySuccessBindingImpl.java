package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class ActivityTroubleshootingConnectivitySuccessBindingImpl extends ActivityTroubleshootingConnectivitySuccessBinding {
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
        sparseIntArray.put(R.id.toolbar, 3);
        sparseIntArray.put(R.id.iv_troubleshoot_inprogress, 4);
        sparseIntArray.put(R.id.tv_your_troubleshoot_in_progress, 5);
        sparseIntArray.put(R.id.tv_troubleshooting_header, 6);
        sparseIntArray.put(R.id.btn_ok, 7);
    }

    public ActivityTroubleshootingConnectivitySuccessBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 8, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        int i;
        long j3;
        long j4;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        Boolean bool = this.mIsShowingInProgress;
        int i2 = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        int i3 = 0;
        if (i2 != 0) {
            boolean safeUnbox = ViewDataBinding.safeUnbox(bool);
            if (i2 != 0) {
                if (safeUnbox) {
                    j3 = j2 | 8;
                    j4 = 32;
                } else {
                    j3 = j2 | 4;
                    j4 = 16;
                }
                j2 = j3 | j4;
            }
            i = safeUnbox ? 8 : 0;
            if (!safeUnbox) {
                i3 = 8;
            }
        } else {
            i = 0;
        }
        if ((j2 & 3) != 0) {
            this.toolbar.setVisibility(i3);
            this.troubleshootInProgressLayout.setVisibility(i3);
            this.troubleshootInSuccessLayout.setVisibility(i);
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

    @Override // com.coveiot.android.boat.databinding.ActivityTroubleshootingConnectivitySuccessBinding
    public void setIsShowingInProgress(@Nullable Boolean bool) {
        this.mIsShowingInProgress = bool;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(58);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (58 == i) {
            setIsShowingInProgress((Boolean) obj);
            return true;
        }
        return false;
    }

    public ActivityTroubleshootingConnectivitySuccessBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[7], (ImageView) objArr[4], (View) objArr[3], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[2], (TextView) objArr[6], (TextView) objArr[5]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.troubleshootInProgressLayout.setTag(null);
        this.troubleshootInSuccessLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
