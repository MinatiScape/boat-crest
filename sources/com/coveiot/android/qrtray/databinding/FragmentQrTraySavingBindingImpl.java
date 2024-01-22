package com.coveiot.android.qrtray.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.qrtray.BR;
import com.coveiot.android.qrtray.R;
/* loaded from: classes5.dex */
public class FragmentQrTraySavingBindingImpl extends FragmentQrTraySavingBinding {
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
        sparseIntArray.put(R.id.toolbar, 1);
        sparseIntArray.put(R.id.clSavingNPush, 2);
        sparseIntArray.put(R.id.tvSaving, 3);
        sparseIntArray.put(R.id.ivInner, 4);
        sparseIntArray.put(R.id.ivOuter, 5);
        sparseIntArray.put(R.id.nestedScrollView, 6);
        sparseIntArray.put(R.id.tvUploadNew, 7);
        sparseIntArray.put(R.id.ivQRCode, 8);
        sparseIntArray.put(R.id.tvName, 9);
        sparseIntArray.put(R.id.etQRCodeName, 10);
        sparseIntArray.put(R.id.tvNameLength, 11);
        sparseIntArray.put(R.id.rvQRTrayCategories, 12);
        sparseIntArray.put(R.id.btnSaveNPush, 13);
        sparseIntArray.put(R.id.tvSave, 14);
    }

    public FragmentQrTraySavingBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 15, j, k));
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
            this.i = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.coveiot.android.qrtray.databinding.FragmentQrTraySavingBinding
    public void setQrEditImage(@Nullable String str) {
        this.mQrEditImage = str;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.qrEditImage == i) {
            setQrEditImage((String) obj);
            return true;
        }
        return false;
    }

    public FragmentQrTraySavingBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[13], (ConstraintLayout) objArr[2], (EditText) objArr[10], (ImageView) objArr[4], (ImageView) objArr[5], (ImageView) objArr[8], (NestedScrollView) objArr[6], (RecyclerView) objArr[12], (View) objArr[1], (TextView) objArr[9], (TextView) objArr[11], (TextView) objArr[14], (TextView) objArr[3], (TextView) objArr[7]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
