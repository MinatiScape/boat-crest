package com.coveiot.android.tappy.databinding;

import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.tappy.BR;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public class FragmentAddNfcStrapFtuBindingImpl extends FragmentAddNfcStrapFtuBinding {
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
        sparseIntArray.put(R.id.toolbar, 5);
        sparseIntArray.put(R.id.viewPager, 6);
        sparseIntArray.put(R.id.clPageIndicator, 7);
        sparseIntArray.put(R.id.clBottomViews, 8);
        sparseIntArray.put(R.id.tvTitle, 9);
        sparseIntArray.put(R.id.tvDesc, 10);
        sparseIntArray.put(R.id.btnGetStarted, 11);
    }

    public FragmentAddNfcStrapFtuBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 12, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        int i;
        int i2;
        int i3;
        int i4;
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        Drawable drawable4;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        Integer num = this.mFTUItemCount;
        Integer num2 = this.mSelectedItemPosition;
        int i5 = ((j2 & 5) > 0L ? 1 : ((j2 & 5) == 0L ? 0 : -1));
        if (i5 != 0) {
            int safeUnbox = ViewDataBinding.safeUnbox(num);
            boolean z = safeUnbox >= 2;
            boolean z2 = safeUnbox >= 3;
            boolean z3 = safeUnbox >= 4;
            boolean z4 = safeUnbox > 1;
            if (i5 != 0) {
                j2 |= z ? 256L : 128L;
            }
            if ((j2 & 5) != 0) {
                j2 |= z2 ? 16L : 8L;
            }
            if ((j2 & 5) != 0) {
                j2 |= z3 ? PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM : PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            if ((j2 & 5) != 0) {
                j2 |= z4 ? 16384L : PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            i = 8;
            i3 = z ? 0 : 8;
            i4 = z2 ? 0 : 8;
            i2 = z3 ? 0 : 8;
            if (z4) {
                i = 0;
            }
        } else {
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
        }
        int i6 = ((j2 & 6) > 0L ? 1 : ((j2 & 6) == 0L ? 0 : -1));
        if (i6 != 0) {
            int safeUnbox2 = ViewDataBinding.safeUnbox(num2);
            boolean z5 = safeUnbox2 == 0;
            boolean z6 = safeUnbox2 == 1;
            boolean z7 = safeUnbox2 == 2;
            boolean z8 = safeUnbox2 == 3;
            if (i6 != 0) {
                j2 |= z5 ? 1024L : 512L;
            }
            if ((j2 & 6) != 0) {
                j2 |= z6 ? PlaybackStateCompat.ACTION_SET_REPEAT_MODE : PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
            }
            if ((j2 & 6) != 0) {
                j2 |= z7 ? 64L : 32L;
            }
            if ((j2 & 6) != 0) {
                j2 |= z8 ? PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH : PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            Drawable drawable5 = AppCompatResources.getDrawable(this.imageViewDot1.getContext(), z5 ? R.drawable.viewpager_selected_indicator : R.drawable.viewpager_unselected_indicator);
            Drawable drawable6 = AppCompatResources.getDrawable(this.imageViewDot2.getContext(), z6 ? R.drawable.viewpager_selected_indicator : R.drawable.viewpager_unselected_indicator);
            Drawable drawable7 = AppCompatResources.getDrawable(this.imageViewDot3.getContext(), z7 ? R.drawable.viewpager_selected_indicator : R.drawable.viewpager_unselected_indicator);
            drawable4 = AppCompatResources.getDrawable(this.imageViewDot4.getContext(), z8 ? R.drawable.viewpager_selected_indicator : R.drawable.viewpager_unselected_indicator);
            drawable3 = drawable7;
            drawable2 = drawable6;
            drawable = drawable5;
        } else {
            drawable = null;
            drawable2 = null;
            drawable3 = null;
            drawable4 = null;
        }
        if ((j2 & 6) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.imageViewDot1, drawable);
            ImageViewBindingAdapter.setImageDrawable(this.imageViewDot2, drawable2);
            ImageViewBindingAdapter.setImageDrawable(this.imageViewDot3, drawable3);
            ImageViewBindingAdapter.setImageDrawable(this.imageViewDot4, drawable4);
        }
        if ((j2 & 5) != 0) {
            this.imageViewDot1.setVisibility(i);
            this.imageViewDot2.setVisibility(i3);
            this.imageViewDot3.setVisibility(i4);
            this.imageViewDot4.setVisibility(i2);
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

    @Override // com.coveiot.android.tappy.databinding.FragmentAddNfcStrapFtuBinding
    public void setFTUItemCount(@Nullable Integer num) {
        this.mFTUItemCount = num;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.FTUItemCount);
        super.requestRebind();
    }

    @Override // com.coveiot.android.tappy.databinding.FragmentAddNfcStrapFtuBinding
    public void setSelectedItemPosition(@Nullable Integer num) {
        this.mSelectedItemPosition = num;
        synchronized (this) {
            this.i |= 2;
        }
        notifyPropertyChanged(BR.selectedItemPosition);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.FTUItemCount == i) {
            setFTUItemCount((Integer) obj);
        } else if (BR.selectedItemPosition != i) {
            return false;
        } else {
            setSelectedItemPosition((Integer) obj);
        }
        return true;
    }

    public FragmentAddNfcStrapFtuBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[11], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[7], (ImageView) objArr[1], (ImageView) objArr[2], (ImageView) objArr[3], (ImageView) objArr[4], (View) objArr[5], (TextView) objArr[10], (TextView) objArr[9], (ViewPager) objArr[6]);
        this.i = -1L;
        this.imageViewDot1.setTag(null);
        this.imageViewDot2.setTag(null);
        this.imageViewDot3.setTag(null);
        this.imageViewDot4.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
