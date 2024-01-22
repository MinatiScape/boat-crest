package com.coveiot.android.tappy.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public class FragmentScanCardBindingImpl extends FragmentScanCardBinding {
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
        sparseIntArray.put(R.id.enter_otp_description_txt, 1);
        sparseIntArray.put(R.id.card_scan_layout, 2);
        sparseIntArray.put(R.id.imageViewHolder1, 3);
        sparseIntArray.put(R.id.preview_camera1, 4);
        sparseIntArray.put(R.id.scn_pg_bar, 5);
        sparseIntArray.put(R.id.btnCapture, 6);
        sparseIntArray.put(R.id.note_txt, 7);
        sparseIntArray.put(R.id.tvSupportedIssuers, 8);
        sparseIntArray.put(R.id.view1, 9);
        sparseIntArray.put(R.id.or_txt, 10);
        sparseIntArray.put(R.id.btnAddManuVally, 11);
    }

    public FragmentScanCardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 12, j, k));
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
            this.i = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentScanCardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[11], (Button) objArr[6], (ConstraintLayout) objArr[2], (TextView) objArr[1], (ImageView) objArr[3], (TextView) objArr[7], (TextView) objArr[10], (PreviewView) objArr[4], (ImageView) objArr[5], (TextView) objArr[8], (View) objArr[9]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
