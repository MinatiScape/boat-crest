package com.coveiot.android.boat.databinding;

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
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class ActivityProfileLandingPageBindingImpl extends ActivityProfileLandingPageBinding {
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
        sparseIntArray.put(R.id.toolbar, 2);
        sparseIntArray.put(R.id.profile_complete, 3);
        sparseIntArray.put(R.id.clGuestUser, 4);
        sparseIntArray.put(R.id.guestProfile, 5);
        sparseIntArray.put(R.id.guestName, 6);
        sparseIntArray.put(R.id.clGetYourHands, 7);
        sparseIntArray.put(R.id.getYourText, 8);
        sparseIntArray.put(R.id.ivIcon, 9);
        sparseIntArray.put(R.id.btnLogin, 10);
        sparseIntArray.put(R.id.tvAppVersion, 11);
        sparseIntArray.put(R.id.cv_profile_details, 12);
        sparseIntArray.put(R.id.iv_profile, 13);
        sparseIntArray.put(R.id.tv_name, 14);
        sparseIntArray.put(R.id.tvUserContact, 15);
        sparseIntArray.put(R.id.cv_view_profile, 16);
        sparseIntArray.put(R.id.tv_view_profile, 17);
        sparseIntArray.put(R.id.cv_achievements_first, 18);
        sparseIntArray.put(R.id.iv_achievements_first, 19);
        sparseIntArray.put(R.id.tv_achievements_text, 20);
        sparseIntArray.put(R.id.tv_achievements_desc, 21);
        sparseIntArray.put(R.id.tv_view_more, 22);
        sparseIntArray.put(R.id.clMyGoals, 23);
        sparseIntArray.put(R.id.tv_my_goals, 24);
        sparseIntArray.put(R.id.tv_my_goals_desc, 25);
        sparseIntArray.put(R.id.rv_goals, 26);
        sparseIntArray.put(R.id.cv_goals_more, 27);
        sparseIntArray.put(R.id.tv_view_goals, 28);
        sparseIntArray.put(R.id.cv_achievements, 29);
        sparseIntArray.put(R.id.tv_my_achievements, 30);
        sparseIntArray.put(R.id.tv_achievements_more, 31);
        sparseIntArray.put(R.id.tv_my_achievements_desc, 32);
        sparseIntArray.put(R.id.rv_achievements, 33);
        sparseIntArray.put(R.id.tv_fitness_buddiess, 34);
        sparseIntArray.put(R.id.tv_my_fitness_desc, 35);
        sparseIntArray.put(R.id.rv_fitness_buddiess, 36);
        sparseIntArray.put(R.id.tv_more, 37);
        sparseIntArray.put(R.id.rv_more, 38);
        sparseIntArray.put(R.id.tv_logout, 39);
        sparseIntArray.put(R.id.tv_delete_myaccount, 40);
        sparseIntArray.put(R.id.tv_app_version_1, 41);
    }

    public ActivityProfileLandingPageBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 42, j, k));
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

    public ActivityProfileLandingPageBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[10], (ConstraintLayout) objArr[7], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[23], (ConstraintLayout) objArr[29], (ConstraintLayout) objArr[18], (ConstraintLayout) objArr[27], (ConstraintLayout) objArr[12], (ConstraintLayout) objArr[16], (TextView) objArr[8], (TextView) objArr[6], (ImageView) objArr[5], (ImageView) objArr[19], (ImageView) objArr[9], (ImageView) objArr[13], (View) objArr[3], (RecyclerView) objArr[33], (RecyclerView) objArr[36], (RecyclerView) objArr[26], (RecyclerView) objArr[38], (View) objArr[2], (TextView) objArr[21], (TextView) objArr[31], (TextView) objArr[20], (TextView) objArr[11], (TextView) objArr[41], (TextView) objArr[40], (TextView) objArr[34], (TextView) objArr[39], (TextView) objArr[37], (TextView) objArr[30], (TextView) objArr[32], (TextView) objArr[35], (TextView) objArr[24], (TextView) objArr[25], (TextView) objArr[14], (TextView) objArr[15], (TextView) objArr[28], (TextView) objArr[22], (TextView) objArr[17]);
        this.i = -1L;
        this.clLoggedInUser.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.h = linearLayout;
        linearLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
