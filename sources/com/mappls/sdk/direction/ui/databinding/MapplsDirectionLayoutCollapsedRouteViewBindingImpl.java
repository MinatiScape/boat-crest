package com.mappls.sdk.direction.ui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public class MapplsDirectionLayoutCollapsedRouteViewBindingImpl extends MapplsDirectionLayoutCollapsedRouteViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.soucre_location_layout, 6);
        sparseIntArray.put(R.id.img_source_location, 7);
        sparseIntArray.put(R.id.view_source_location, 8);
        sparseIntArray.put(R.id.waypoints_layout, 9);
        sparseIntArray.put(R.id.img_waypoints, 10);
        sparseIntArray.put(R.id.view_waypoints, 11);
        sparseIntArray.put(R.id.img_destination, 12);
    }

    public MapplsDirectionLayoutCollapsedRouteViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private MapplsDirectionLayoutCollapsedRouteViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (LinearLayout) objArr[0], (ImageButton) objArr[5], (ImageView) objArr[1], (ImageView) objArr[12], (ImageView) objArr[7], (ImageView) objArr[10], (LinearLayout) objArr[6], (TextView) objArr[4], (TextView) objArr[2], (TextView) objArr[3], (LinearLayout) objArr[8], (LinearLayout) objArr[11], (LinearLayout) objArr[9]);
        this.mDirtyFlags = -1L;
        this.collapsedRouteTimeline.setTag(null);
        this.editWaypointsBtn.setTag(null);
        this.imgClickBack.setTag(null);
        this.textDestinationRoute.setTag(null);
        this.textSourceRoute.setTag(null);
        this.textViewWaypoints.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        View.OnClickListener onClickListener = this.mOnclickHandleBack;
        String str = this.mWayPoints;
        String str2 = this.mSourceRouteLocation;
        String str3 = this.mDestinationRouteLocation;
        long j2 = 17 & j;
        long j3 = 18 & j;
        long j4 = 20 & j;
        long j5 = j & 24;
        if (j2 != 0) {
            this.editWaypointsBtn.setOnClickListener(onClickListener);
            this.imgClickBack.setOnClickListener(onClickListener);
        }
        if (j5 != 0) {
            TextViewBindingAdapter.setText(this.textDestinationRoute, str3);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.textSourceRoute, str2);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.textViewWaypoints, str);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsDirectionLayoutCollapsedRouteViewBinding
    public void setDestinationRouteLocation(@Nullable String str) {
        this.mDestinationRouteLocation = str;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsDirectionLayoutCollapsedRouteViewBinding
    public void setOnclickHandleBack(@Nullable View.OnClickListener onClickListener) {
        this.mOnclickHandleBack = onClickListener;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsDirectionLayoutCollapsedRouteViewBinding
    public void setSourceRouteLocation(@Nullable String str) {
        this.mSourceRouteLocation = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(14);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (11 == i) {
            setOnclickHandleBack((View.OnClickListener) obj);
        } else if (16 == i) {
            setWayPoints((String) obj);
        } else if (14 == i) {
            setSourceRouteLocation((String) obj);
        } else if (2 != i) {
            return false;
        } else {
            setDestinationRouteLocation((String) obj);
        }
        return true;
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsDirectionLayoutCollapsedRouteViewBinding
    public void setWayPoints(@Nullable String str) {
        this.mWayPoints = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }
}
