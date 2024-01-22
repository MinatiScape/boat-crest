package com.coveiot.android.boat.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.coveiot.android.leonardo.more.models.AchievementsModel;
/* loaded from: classes3.dex */
public class ProfileAchievementsItemBindingImpl extends ProfileAchievementsItemBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k = null;
    @NonNull
    public final CardView h;
    public long i;

    public ProfileAchievementsItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 4, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        String str;
        String str2;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        AchievementsModel achievementsModel = this.mAchievementsData;
        int i = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        Drawable drawable = null;
        if (i == 0 || achievementsModel == null) {
            str = null;
            str2 = null;
        } else {
            String achievementsName = achievementsModel.getAchievementsName();
            String achievementsValue = achievementsModel.getAchievementsValue();
            str = achievementsName;
            drawable = achievementsModel.getImage();
            str2 = achievementsValue;
        }
        if (i != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.ivAchievements, drawable);
            TextViewBindingAdapter.setText(this.tvAchievementsName, str);
            TextViewBindingAdapter.setText(this.tvAchievementsValue, str2);
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

    @Override // com.coveiot.android.boat.databinding.ProfileAchievementsItemBinding
    public void setAchievementsData(@Nullable AchievementsModel achievementsModel) {
        this.mAchievementsData = achievementsModel;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (2 == i) {
            setAchievementsData((AchievementsModel) obj);
            return true;
        }
        return false;
    }

    public ProfileAchievementsItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[1], (TextView) objArr[3], (TextView) objArr[2]);
        this.i = -1L;
        this.ivAchievements.setTag(null);
        CardView cardView = (CardView) objArr[0];
        this.h = cardView;
        cardView.setTag(null);
        this.tvAchievementsName.setTag(null);
        this.tvAchievementsValue.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
