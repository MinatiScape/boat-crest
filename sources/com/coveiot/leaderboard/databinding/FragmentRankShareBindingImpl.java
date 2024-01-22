package com.coveiot.leaderboard.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.CircularArcProgressBar;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public class FragmentRankShareBindingImpl extends FragmentRankShareBinding {
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
        sparseIntArray.put(R.id.root_layout, 1);
        sparseIntArray.put(R.id.iv_rank_share_app_logo, 2);
        sparseIntArray.put(R.id.iv_rank_share_powered_cove, 3);
        sparseIntArray.put(R.id.tv_rs_desc, 4);
        sparseIntArray.put(R.id.tv_rs_today_date, 5);
        sparseIntArray.put(R.id.cv_rs_steps, 6);
        sparseIntArray.put(R.id.constraint, 7);
        sparseIntArray.put(R.id.pb_steps_rs, 8);
        sparseIntArray.put(R.id.circularArcProgressBar, 9);
        sparseIntArray.put(R.id.tv_rs_steps_count, 10);
        sparseIntArray.put(R.id.tv_steps_label, 11);
        sparseIntArray.put(R.id.view_constraint, 12);
        sparseIntArray.put(R.id.tv_rs_count, 13);
        sparseIntArray.put(R.id.view, 14);
        sparseIntArray.put(R.id.progressStatusTv, 15);
        sparseIntArray.put(R.id.rs_view2_steps, 16);
        sparseIntArray.put(R.id.iv_rs_best_rank, 17);
        sparseIntArray.put(R.id.tv_rs_rank_score, 18);
        sparseIntArray.put(R.id.tv_rs_best_rank, 19);
        sparseIntArray.put(R.id.tv_rs_date, 20);
        sparseIntArray.put(R.id.tv_rs_last_badges, 21);
        sparseIntArray.put(R.id.fl_rs, 22);
        sparseIntArray.put(R.id.recyclerview_rs, 23);
        sparseIntArray.put(R.id.iv_rs_user_pic, 24);
        sparseIntArray.put(R.id.tv_rs_user_name, 25);
    }

    public FragmentRankShareBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 26, j, k));
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

    public FragmentRankShareBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (CircularArcProgressBar) objArr[9], (ConstraintLayout) objArr[7], (CardView) objArr[6], (FrameLayout) objArr[22], (ImageView) objArr[2], (ImageView) objArr[3], (ImageView) objArr[17], (ImageView) objArr[24], (CircularArcProgressBar) objArr[8], (TextView) objArr[15], (RecyclerView) objArr[23], (ConstraintLayout) objArr[1], (View) objArr[16], (TextView) objArr[19], (TextView) objArr[13], (TextView) objArr[20], (TextView) objArr[4], (TextView) objArr[21], (TextView) objArr[18], (TextView) objArr[10], (TextView) objArr[5], (TextView) objArr[25], (TextView) objArr[11], (View) objArr[14], (ConstraintLayout) objArr[12]);
        this.i = -1L;
        ScrollView scrollView = (ScrollView) objArr[0];
        this.h = scrollView;
        scrollView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
