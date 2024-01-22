package com.coveiot.android.navigation.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public class FragmentNavigatFtuBindingImpl extends FragmentNavigatFtuBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final LinearLayout h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.ll_navigation_ftu_webview_container, 1);
        sparseIntArray.put(R.id.webview_tbt_navigation, 2);
        sparseIntArray.put(R.id.navigation_ftu, 3);
        sparseIntArray.put(R.id.tv_overview, 4);
        sparseIntArray.put(R.id.tv_introducing_navigation, 5);
        sparseIntArray.put(R.id.tv_sl_no_search_your_desired, 6);
        sparseIntArray.put(R.id.tv_search_your_desired, 7);
        sparseIntArray.put(R.id.tv_sl_no_tv_effortlessly_send, 8);
        sparseIntArray.put(R.id.tv_effortlessly_send, 9);
        sparseIntArray.put(R.id.tv_sl_no_tv_then_follow, 10);
        sparseIntArray.put(R.id.tv_then_follow, 11);
        sparseIntArray.put(R.id.btnNext, 12);
    }

    public FragmentNavigatFtuBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 13, j, k));
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

    public FragmentNavigatFtuBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[12], (LinearLayout) objArr[1], (ConstraintLayout) objArr[3], (TextView) objArr[9], (TextView) objArr[5], (TextView) objArr[4], (TextView) objArr[7], (TextView) objArr[6], (TextView) objArr[8], (TextView) objArr[10], (TextView) objArr[11], (WebView) objArr[2]);
        this.i = -1L;
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.h = linearLayout;
        linearLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
