package com.coveiot.android.boat.databinding;

import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class FragmentTroubleshoootingFTUBindingImpl extends FragmentTroubleshoootingFTUBinding {
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
        sparseIntArray.put(R.id.toolbar, 4);
        sparseIntArray.put(R.id.viewPager, 5);
        sparseIntArray.put(R.id.page_indicator_layout, 6);
    }

    public FragmentTroubleshoootingFTUBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 7, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        Drawable drawable;
        Drawable drawable2;
        int i;
        int i2;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        Integer num = this.mSelectedItemPosition;
        Integer num2 = this.mFTUItemCount;
        int i3 = ((j2 & 5) > 0L ? 1 : ((j2 & 5) == 0L ? 0 : -1));
        Drawable drawable3 = null;
        if (i3 != 0) {
            int safeUnbox = ViewDataBinding.safeUnbox(num);
            boolean z = safeUnbox == 2;
            boolean z2 = safeUnbox == 0;
            boolean z3 = safeUnbox == 1;
            if (i3 != 0) {
                j2 |= z ? 256L : 128L;
            }
            if ((j2 & 5) != 0) {
                j2 |= z2 ? PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM : PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            if ((j2 & 5) != 0) {
                j2 |= z3 ? 1024L : 512L;
            }
            Drawable drawable4 = z ? AppCompatResources.getDrawable(this.imageViewDot3.getContext(), R.drawable.viewpager_selected_indicator1) : AppCompatResources.getDrawable(this.imageViewDot3.getContext(), R.drawable.viewpager_unselected_indicator1);
            Drawable drawable5 = z2 ? AppCompatResources.getDrawable(this.imageViewDot1.getContext(), R.drawable.viewpager_selected_indicator1) : AppCompatResources.getDrawable(this.imageViewDot1.getContext(), R.drawable.viewpager_unselected_indicator1);
            drawable2 = z3 ? AppCompatResources.getDrawable(this.imageViewDot2.getContext(), R.drawable.viewpager_selected_indicator1) : AppCompatResources.getDrawable(this.imageViewDot2.getContext(), R.drawable.viewpager_unselected_indicator1);
            drawable = drawable4;
            drawable3 = drawable5;
        } else {
            drawable = null;
            drawable2 = null;
        }
        int i4 = ((j2 & 6) > 0L ? 1 : ((j2 & 6) == 0L ? 0 : -1));
        if (i4 != 0) {
            int safeUnbox2 = ViewDataBinding.safeUnbox(num2);
            boolean z4 = safeUnbox2 >= 2;
            boolean z5 = safeUnbox2 > 1;
            boolean z6 = safeUnbox2 >= 3;
            if (i4 != 0) {
                j2 |= z4 ? 64L : 32L;
            }
            if ((j2 & 6) != 0) {
                j2 |= z5 ? 16384L : PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            if ((j2 & 6) != 0) {
                j2 |= z6 ? 16L : 8L;
            }
            i2 = z4 ? 0 : 8;
            int i5 = z5 ? 0 : 8;
            i = z6 ? 0 : 8;
            r13 = i5;
        } else {
            i = 0;
            i2 = 0;
        }
        if ((5 & j2) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.imageViewDot1, drawable3);
            ImageViewBindingAdapter.setImageDrawable(this.imageViewDot2, drawable2);
            ImageViewBindingAdapter.setImageDrawable(this.imageViewDot3, drawable);
        }
        if ((j2 & 6) != 0) {
            this.imageViewDot1.setVisibility(r13);
            this.imageViewDot2.setVisibility(i2);
            this.imageViewDot3.setVisibility(i);
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
            this.i = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.coveiot.android.boat.databinding.FragmentTroubleshoootingFTUBinding
    public void setFTUItemCount(@Nullable Integer num) {
        this.mFTUItemCount = num;
        synchronized (this) {
            this.i |= 2;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    @Override // com.coveiot.android.boat.databinding.FragmentTroubleshoootingFTUBinding
    public void setSelectedItemPosition(@Nullable Integer num) {
        this.mSelectedItemPosition = num;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(84);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (84 == i) {
            setSelectedItemPosition((Integer) obj);
            return true;
        } else if (1 == i) {
            setFTUItemCount((Integer) obj);
            return true;
        } else {
            return false;
        }
    }

    public FragmentTroubleshoootingFTUBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[1], (ImageView) objArr[2], (ImageView) objArr[3], (ConstraintLayout) objArr[6], (View) objArr[4], (ViewPager) objArr[5]);
        this.i = -1L;
        this.imageViewDot1.setTag(null);
        this.imageViewDot2.setTag(null);
        this.imageViewDot3.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
