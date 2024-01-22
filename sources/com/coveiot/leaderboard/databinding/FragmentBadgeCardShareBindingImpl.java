package com.coveiot.leaderboard.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public class FragmentBadgeCardShareBindingImpl extends FragmentBadgeCardShareBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ScrollView h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.badgeShareConstraintLayout, 1);
        sparseIntArray.put(R.id.iv_badge_share_app_logo, 2);
        sparseIntArray.put(R.id.iv_badge_share_powered_cove, 3);
        sparseIntArray.put(R.id.tv_badge_earned_on, 4);
        sparseIntArray.put(R.id.iv_rs_badge_icon, 5);
        sparseIntArray.put(R.id.tv_rs_badge_name, 6);
        sparseIntArray.put(R.id.tv_rs_earned_on, 7);
        sparseIntArray.put(R.id.tv_rs_when_earned, 8);
        sparseIntArray.put(R.id.tv_rs_bagde_desc, 9);
        sparseIntArray.put(R.id.iv_bs_user_pic, 10);
        sparseIntArray.put(R.id.tv_bs_user_name, 11);
    }

    public FragmentBadgeCardShareBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
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

    public FragmentBadgeCardShareBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[1], (ImageView) objArr[2], (ImageView) objArr[3], (ImageView) objArr[10], (ImageView) objArr[5], (TextView) objArr[4], (TextView) objArr[11], (TextView) objArr[6], (TextView) objArr[9], (TextView) objArr[7], (TextView) objArr[8]);
        this.i = -1L;
        ScrollView scrollView = (ScrollView) objArr[0];
        this.h = scrollView;
        scrollView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
