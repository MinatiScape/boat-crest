package com.mappls.sdk.direction.ui.databinding;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.direction.ui.DirectionViewModel;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public class MapplsRoutingLayoutBindingImpl extends MapplsRoutingLayoutBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    @Nullable
    private final MapplsDirectionCommonToolbarBinding mboundView0;
    @NonNull
    private final RelativeLayout mboundView2;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(36);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"mappls_direction_common_toolbar"}, new int[]{14}, new int[]{R.layout.mappls_direction_common_toolbar});
        includedLayouts.setIncludes(1, new String[]{"mappls_direction_layout_collapsed_route_view"}, new int[]{16}, new int[]{R.layout.mappls_direction_layout_collapsed_route_view});
        includedLayouts.setIncludes(2, new String[]{"mappls_direction_route_view"}, new int[]{15}, new int[]{R.layout.mappls_direction_route_view});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.map_container, 17);
        sparseIntArray.put(R.id.cd_select_location, 18);
        sparseIntArray.put(R.id.error_layout, 19);
        sparseIntArray.put(R.id.iv_error_state_direction, 20);
        sparseIntArray.put(R.id.tv_route_not_found_heading, 21);
        sparseIntArray.put(R.id.tv_route_not_found_text, 22);
        sparseIntArray.put(R.id.bottom_Sheet_details, 23);
        sparseIntArray.put(R.id.container_route_details, 24);
        sparseIntArray.put(R.id.bottom_sheet_shadow, 25);
        sparseIntArray.put(R.id.progressBar, 26);
        sparseIntArray.put(R.id.layout_time_details, 27);
        sparseIntArray.put(R.id.direction_time_layout, 28);
        sparseIntArray.put(R.id.trip_cost_summary, 29);
        sparseIntArray.put(R.id.direction_list_textview, 30);
        sparseIntArray.put(R.id.notification_layout, 31);
        sparseIntArray.put(R.id.tv_class_notification, 32);
        sparseIntArray.put(R.id.recycler_direction_step, 33);
        sparseIntArray.put(R.id.show_on_maps_layout, 34);
        sparseIntArray.put(R.id.show_on_maps_btn, 35);
    }

    public MapplsRoutingLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 36, sIncludes, sViewsWithIds));
    }

    private MapplsRoutingLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (LinearLayout) objArr[1], (RelativeLayout) objArr[23], (View) objArr[25], (TextView) objArr[6], (TextView) objArr[13], (CardView) objArr[18], (MapplsDirectionLayoutCollapsedRouteViewBinding) objArr[16], (LinearLayout) objArr[24], (CoordinatorLayout) objArr[0], (TextView) objArr[10], (TextView) objArr[30], (LinearLayout) objArr[28], (TextView) objArr[9], (ConstraintLayout) objArr[19], (ImageView) objArr[12], (ImageView) objArr[20], (MapplsDirectionRouteViewBinding) objArr[15], (ConstraintLayout) objArr[27], (FrameLayout) objArr[17], (ImageView) objArr[3], (ImageView) objArr[8], (LinearLayout) objArr[31], (ProgressBar) objArr[26], (RecyclerView) objArr[33], (ImageButton) objArr[5], (TextView) objArr[35], (LinearLayout) objArr[34], (LinearLayout) objArr[11], (TextView) objArr[7], (ImageView) objArr[29], (TextView) objArr[32], (TextView) objArr[21], (TextView) objArr[22], (RelativeLayout) objArr[4]);
        this.mDirtyFlags = -1L;
        this.addLocation.setTag(null);
        this.btnRetryRouteNotFound.setTag(null);
        this.btnStart.setTag(null);
        setContainedBinding(this.collapsedRouteTimeline);
        this.directionContainer.setTag(null);
        this.directionEtaTextView.setTag(null);
        this.distanceText.setTag(null);
        this.imgStart.setTag(null);
        setContainedBinding(this.layoutSelectTab);
        this.mapplsDirectionBack.setTag(null);
        MapplsDirectionCommonToolbarBinding mapplsDirectionCommonToolbarBinding = (MapplsDirectionCommonToolbarBinding) objArr[14];
        this.mboundView0 = mapplsDirectionCommonToolbarBinding;
        setContainedBinding(mapplsDirectionCommonToolbarBinding);
        RelativeLayout relativeLayout = (RelativeLayout) objArr[2];
        this.mboundView2 = relativeLayout;
        relativeLayout.setTag(null);
        this.nearbyReport.setTag(null);
        this.searchCategoryFab.setTag(null);
        this.startLayout.setTag(null);
        this.textRoute.setTag(null);
        this.viewGetRoute.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    private boolean onChangeCollapsedRouteTimeline(MapplsDirectionLayoutCollapsedRouteViewBinding mapplsDirectionLayoutCollapsedRouteViewBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLayoutSelectTab(MapplsDirectionRouteViewBinding mapplsDirectionRouteViewBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        String str;
        Drawable drawable;
        Context context;
        int i;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str2 = this.mRouteTime;
        View.OnClickListener onClickListener = this.mRetryButtonClick;
        View.OnClickListener onClickListener2 = this.mOnHandleBack;
        String str3 = this.mDistance;
        boolean z = false;
        View.OnClickListener onClickListener3 = this.mOnRouteReportClick;
        View.OnClickListener onClickListener4 = this.mStartButtonClick;
        String str4 = this.mArrival;
        View.OnClickListener onClickListener5 = this.mOnClickGetRoute;
        DirectionViewModel directionViewModel = this.mOnStartClick;
        View.OnClickListener onClickListener6 = this.mOnClickSearchCategory;
        int i2 = ((j & 5120) > 0L ? 1 : ((j & 5120) == 0L ? 0 : -1));
        String str5 = null;
        if (i2 != 0) {
            if (directionViewModel != null) {
                str5 = directionViewModel.setLocationText();
                z = directionViewModel.isCurrentLocation();
            }
            if (i2 != 0) {
                j |= z ? 16384L : PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            if (z) {
                context = this.imgStart.getContext();
                i = R.drawable.mappls_direction_baseline_near_me_24;
            } else {
                context = this.imgStart.getContext();
                i = R.drawable.mappls_direction_preview_direction_wt;
            }
            drawable = AppCompatResources.getDrawable(context, i);
            str = str5;
        } else {
            str = null;
            drawable = null;
        }
        long j2 = j & 6144;
        if ((j & 4104) != 0) {
            this.btnRetryRouteNotFound.setOnClickListener(onClickListener);
        }
        if ((j & 5120) != 0) {
            TextViewBindingAdapter.setText(this.btnStart, str);
            ImageViewBindingAdapter.setImageDrawable(this.imgStart, drawable);
        }
        if ((4352 & j) != 0) {
            TextViewBindingAdapter.setText(this.directionEtaTextView, str4);
        }
        if ((4128 & j) != 0) {
            TextViewBindingAdapter.setText(this.distanceText, str3);
        }
        if ((4112 & j) != 0) {
            this.mapplsDirectionBack.setOnClickListener(onClickListener2);
        }
        if ((4160 & j) != 0) {
            this.nearbyReport.setOnClickListener(onClickListener3);
        }
        if (j2 != 0) {
            this.searchCategoryFab.setOnClickListener(onClickListener6);
        }
        if ((4224 & j) != 0) {
            this.startLayout.setOnClickListener(onClickListener4);
        }
        if ((4100 & j) != 0) {
            TextViewBindingAdapter.setText(this.textRoute, str2);
        }
        if ((j & 4608) != 0) {
            this.viewGetRoute.setOnClickListener(onClickListener5);
        }
        ViewDataBinding.executeBindingsOn(this.mboundView0);
        ViewDataBinding.executeBindingsOn(this.layoutSelectTab);
        ViewDataBinding.executeBindingsOn(this.collapsedRouteTimeline);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView0.hasPendingBindings() || this.layoutSelectTab.hasPendingBindings() || this.collapsedRouteTimeline.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        this.mboundView0.invalidateAll();
        this.layoutSelectTab.invalidateAll();
        this.collapsedRouteTimeline.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i != 1) {
                return false;
            }
            return onChangeCollapsedRouteTimeline((MapplsDirectionLayoutCollapsedRouteViewBinding) obj, i2);
        }
        return onChangeLayoutSelectTab((MapplsDirectionRouteViewBinding) obj, i2);
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsRoutingLayoutBinding
    public void setArrival(@Nullable String str) {
        this.mArrival = str;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsRoutingLayoutBinding
    public void setDistance(@Nullable String str) {
        this.mDistance = str;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView0.setLifecycleOwner(lifecycleOwner);
        this.layoutSelectTab.setLifecycleOwner(lifecycleOwner);
        this.collapsedRouteTimeline.setLifecycleOwner(lifecycleOwner);
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsRoutingLayoutBinding
    public void setOnClickGetRoute(@Nullable View.OnClickListener onClickListener) {
        this.mOnClickGetRoute = onClickListener;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(4);
        super.requestRebind();
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsRoutingLayoutBinding
    public void setOnClickSearchCategory(@Nullable View.OnClickListener onClickListener) {
        this.mOnClickSearchCategory = onClickListener;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(5);
        super.requestRebind();
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsRoutingLayoutBinding
    public void setOnHandleBack(@Nullable View.OnClickListener onClickListener) {
        this.mOnHandleBack = onClickListener;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(6);
        super.requestRebind();
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsRoutingLayoutBinding
    public void setOnRouteReportClick(@Nullable View.OnClickListener onClickListener) {
        this.mOnRouteReportClick = onClickListener;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(9);
        super.requestRebind();
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsRoutingLayoutBinding
    public void setOnStartClick(@Nullable DirectionViewModel directionViewModel) {
        this.mOnStartClick = directionViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1024;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsRoutingLayoutBinding
    public void setRetryButtonClick(@Nullable View.OnClickListener onClickListener) {
        this.mRetryButtonClick = onClickListener;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(12);
        super.requestRebind();
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsRoutingLayoutBinding
    public void setRouteTime(@Nullable String str) {
        this.mRouteTime = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(13);
        super.requestRebind();
    }

    @Override // com.mappls.sdk.direction.ui.databinding.MapplsRoutingLayoutBinding
    public void setStartButtonClick(@Nullable View.OnClickListener onClickListener) {
        this.mStartButtonClick = onClickListener;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(15);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (13 == i) {
            setRouteTime((String) obj);
            return true;
        } else if (12 == i) {
            setRetryButtonClick((View.OnClickListener) obj);
            return true;
        } else if (6 == i) {
            setOnHandleBack((View.OnClickListener) obj);
            return true;
        } else if (3 == i) {
            setDistance((String) obj);
            return true;
        } else if (9 == i) {
            setOnRouteReportClick((View.OnClickListener) obj);
            return true;
        } else if (15 == i) {
            setStartButtonClick((View.OnClickListener) obj);
            return true;
        } else if (1 == i) {
            setArrival((String) obj);
            return true;
        } else if (4 == i) {
            setOnClickGetRoute((View.OnClickListener) obj);
            return true;
        } else if (10 == i) {
            setOnStartClick((DirectionViewModel) obj);
            return true;
        } else if (5 == i) {
            setOnClickSearchCategory((View.OnClickListener) obj);
            return true;
        } else {
            return false;
        }
    }
}
