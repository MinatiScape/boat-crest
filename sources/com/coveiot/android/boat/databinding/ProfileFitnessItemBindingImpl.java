package com.coveiot.android.boat.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.models.FitnessBuddiesModel;
/* loaded from: classes3.dex */
public class ProfileFitnessItemBindingImpl extends ProfileFitnessItemBinding {
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
        sparseIntArray.put(R.id.iv_arrow, 3);
        sparseIntArray.put(R.id.rv_fitness_subheaders, 4);
        sparseIntArray.put(R.id.list_item_divider, 5);
    }

    public ProfileFitnessItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 6, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        String str;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        FitnessBuddiesModel fitnessBuddiesModel = this.mFitnessData;
        int i = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        Drawable drawable = null;
        if (i == 0 || fitnessBuddiesModel == null) {
            str = null;
        } else {
            drawable = fitnessBuddiesModel.getImage();
            str = fitnessBuddiesModel.getFitnessBuddiesName();
        }
        if (i != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.ivFitness, drawable);
            TextViewBindingAdapter.setText(this.tvFitness, str);
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

    @Override // com.coveiot.android.boat.databinding.ProfileFitnessItemBinding
    public void setFitnessData(@Nullable FitnessBuddiesModel fitnessBuddiesModel) {
        this.mFitnessData = fitnessBuddiesModel;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(37);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (37 == i) {
            setFitnessData((FitnessBuddiesModel) obj);
            return true;
        }
        return false;
    }

    public ProfileFitnessItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[3], (ImageView) objArr[1], (View) objArr[5], (RecyclerView) objArr[4], (TextView) objArr[2]);
        this.i = -1L;
        this.ivFitness.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvFitness.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
