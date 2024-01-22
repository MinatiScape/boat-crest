package com.coveiot.android.tappy.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public class ActivityRegisteredProductSummaryBindingImpl extends ActivityRegisteredProductSummaryBinding {
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
        sparseIntArray.put(R.id.virtualCard_title_layout, 2);
        sparseIntArray.put(R.id.settings_layout, 3);
        sparseIntArray.put(R.id.image_settings, 4);
        sparseIntArray.put(R.id.viewPagerRegisteredProductSummary, 5);
        sparseIntArray.put(R.id.button_layout, 6);
        sparseIntArray.put(R.id.linearLayoutDots, 7);
        sparseIntArray.put(R.id.btn_activeCard, 8);
        sparseIntArray.put(R.id.btn_resumeCard, 9);
        sparseIntArray.put(R.id.btnSuspendCard, 10);
        sparseIntArray.put(R.id.btnAddNfcStrap, 11);
        sparseIntArray.put(R.id.cl_transaction_history, 12);
        sparseIntArray.put(R.id.tv_last10Transaction, 13);
        sparseIntArray.put(R.id.tvLatestTenTransaction, 14);
        sparseIntArray.put(R.id.rv_transactionDetails, 15);
        sparseIntArray.put(R.id.tv_noTransaction, 16);
        sparseIntArray.put(R.id.noStrapAdded, 17);
        sparseIntArray.put(R.id.fragment_container, 18);
    }

    public ActivityRegisteredProductSummaryBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 19, j, k));
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

    public ActivityRegisteredProductSummaryBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[8], (Button) objArr[11], (Button) objArr[9], (Button) objArr[10], (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[12], (FrameLayout) objArr[18], (ImageView) objArr[4], (LinearLayout) objArr[7], (TextView) objArr[17], (RecyclerView) objArr[15], (ConstraintLayout) objArr[3], (View) objArr[1], (TextView) objArr[13], (TextView) objArr[14], (TextView) objArr[16], (ViewPager) objArr[5], (ConstraintLayout) objArr[2]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
