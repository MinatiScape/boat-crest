package com.coveiot.android.boat.databinding;

import android.content.Context;
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
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class ActivityOnboardingFtuBindingImpl extends ActivityOnboardingFtuBinding {
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
        sparseIntArray.put(R.id.toolbar, 6);
        sparseIntArray.put(R.id.guidelineTop, 7);
        sparseIntArray.put(R.id.vpOnboardingFTU, 8);
        sparseIntArray.put(R.id.FTULinearLayoutDots, 9);
        sparseIntArray.put(R.id.guidelineBottom, 10);
        sparseIntArray.put(R.id.tvFtuInfo, 11);
        sparseIntArray.put(R.id.btnGetStarted, 12);
    }

    public ActivityOnboardingFtuBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 13, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        Drawable drawable4;
        Drawable drawable5;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        Integer num = this.mFTUItemCount;
        Integer num2 = this.mSelectedItemPosition;
        int i6 = ((j2 & 5) > 0L ? 1 : ((j2 & 5) == 0L ? 0 : -1));
        if (i6 != 0) {
            int safeUnbox = ViewDataBinding.safeUnbox(num);
            boolean z = safeUnbox >= 2;
            boolean z2 = safeUnbox >= 3;
            boolean z3 = safeUnbox >= 4;
            boolean z4 = safeUnbox >= 5;
            boolean z5 = safeUnbox >= 1;
            if (i6 != 0) {
                j2 |= z ? 256L : 128L;
            }
            if ((j2 & 5) != 0) {
                j2 |= z2 ? 16L : 8L;
            }
            if ((j2 & 5) != 0) {
                j2 |= z3 ? 16384L : PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            if ((j2 & 5) != 0) {
                j2 |= z4 ? PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH : PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            if ((j2 & 5) != 0) {
                j2 |= z5 ? 64L : 32L;
            }
            i4 = z ? 0 : 8;
            int i7 = z2 ? 0 : 8;
            int i8 = z3 ? 0 : 8;
            i3 = z4 ? 0 : 8;
            i2 = z5 ? 0 : 8;
            i = i7;
            i5 = i8;
        } else {
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
        }
        int i9 = ((j2 & 6) > 0L ? 1 : ((j2 & 6) == 0L ? 0 : -1));
        if (i9 != 0) {
            int safeUnbox2 = ViewDataBinding.safeUnbox(num2);
            boolean z6 = safeUnbox2 == 0;
            boolean z7 = safeUnbox2 == 4;
            boolean z8 = safeUnbox2 == 1;
            boolean z9 = safeUnbox2 == 2;
            boolean z10 = safeUnbox2 == 3;
            if (i9 != 0) {
                j2 |= z6 ? PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM : PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            if ((j2 & 6) != 0) {
                j2 |= z7 ? PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED : PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
            }
            if ((j2 & 6) != 0) {
                j2 |= z8 ? 4194304L : PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
            }
            if ((j2 & 6) != 0) {
                j2 |= z9 ? PlaybackStateCompat.ACTION_SET_REPEAT_MODE : PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
            }
            if ((j2 & 6) != 0) {
                j2 |= z10 ? 1024L : 512L;
            }
            Context context = this.dot1.getContext();
            Drawable drawable6 = z6 ? AppCompatResources.getDrawable(context, R.drawable.viewpager_selected_indicator) : AppCompatResources.getDrawable(context, R.drawable.viewpager_unselected_indicator);
            Context context2 = this.dot5.getContext();
            Drawable drawable7 = z7 ? AppCompatResources.getDrawable(context2, R.drawable.viewpager_selected_indicator) : AppCompatResources.getDrawable(context2, R.drawable.viewpager_unselected_indicator);
            Context context3 = this.dot2.getContext();
            drawable3 = z8 ? AppCompatResources.getDrawable(context3, R.drawable.viewpager_selected_indicator) : AppCompatResources.getDrawable(context3, R.drawable.viewpager_unselected_indicator);
            Context context4 = this.dot3.getContext();
            drawable4 = z9 ? AppCompatResources.getDrawable(context4, R.drawable.viewpager_selected_indicator) : AppCompatResources.getDrawable(context4, R.drawable.viewpager_unselected_indicator);
            drawable5 = drawable7;
            drawable2 = z10 ? AppCompatResources.getDrawable(this.dot4.getContext(), R.drawable.viewpager_selected_indicator) : AppCompatResources.getDrawable(this.dot4.getContext(), R.drawable.viewpager_unselected_indicator);
            drawable = drawable6;
        } else {
            drawable = null;
            drawable2 = null;
            drawable3 = null;
            drawable4 = null;
            drawable5 = null;
        }
        if ((j2 & 6) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.dot1, drawable);
            ImageViewBindingAdapter.setImageDrawable(this.dot2, drawable3);
            ImageViewBindingAdapter.setImageDrawable(this.dot3, drawable4);
            ImageViewBindingAdapter.setImageDrawable(this.dot4, drawable2);
            ImageViewBindingAdapter.setImageDrawable(this.dot5, drawable5);
        }
        if ((j2 & 5) != 0) {
            this.dot1.setVisibility(i2);
            this.dot2.setVisibility(i4);
            this.dot3.setVisibility(i);
            this.dot4.setVisibility(i5);
            this.dot5.setVisibility(i3);
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

    @Override // com.coveiot.android.boat.databinding.ActivityOnboardingFtuBinding
    public void setFTUItemCount(@Nullable Integer num) {
        this.mFTUItemCount = num;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    @Override // com.coveiot.android.boat.databinding.ActivityOnboardingFtuBinding
    public void setSelectedItemPosition(@Nullable Integer num) {
        this.mSelectedItemPosition = num;
        synchronized (this) {
            this.i |= 2;
        }
        notifyPropertyChanged(84);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (1 == i) {
            setFTUItemCount((Integer) obj);
            return true;
        } else if (84 == i) {
            setSelectedItemPosition((Integer) obj);
            return true;
        } else {
            return false;
        }
    }

    public ActivityOnboardingFtuBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[9], (Button) objArr[12], (ImageView) objArr[1], (ImageView) objArr[2], (ImageView) objArr[3], (ImageView) objArr[4], (ImageView) objArr[5], (Guideline) objArr[10], (Guideline) objArr[7], (View) objArr[6], (TextView) objArr[11], (ViewPager2) objArr[8]);
        this.i = -1L;
        this.dot1.setTag(null);
        this.dot2.setTag(null);
        this.dot3.setTag(null);
        this.dot4.setTag(null);
        this.dot5.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
