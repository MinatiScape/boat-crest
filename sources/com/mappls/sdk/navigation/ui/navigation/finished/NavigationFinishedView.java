package com.mappls.sdk.navigation.ui.navigation.finished;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.navigation.ui.databinding.LayoutNavigationFinishedBinding;
import com.mappls.sdk.navigation.ui.navigation.MapplsNavigationViewHelper;
import com.mappls.sdk.navigation.util.NavigationSummaryHelper;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
import java.util.ArrayList;
@Keep
/* loaded from: classes11.dex */
public class NavigationFinishedView extends CoordinatorLayout {
    public LayoutNavigationFinishedBinding binding;
    private com.mappls.sdk.navigation.ui.navigation.finished.a callback;
    private BottomSheetBehavior mBottomSheetBehavior;

    /* loaded from: classes11.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (NavigationFinishedView.this.callback != null) {
                NavigationFinishedView.this.callback.onNavigationEnd();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (NavigationFinishedView.this.callback != null) {
                NavigationFinishedView.this.callback.onNavigationEnd();
            }
        }
    }

    public NavigationFinishedView(@NonNull Context context) {
        super(context);
        this.binding = LayoutNavigationFinishedBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public NavigationFinishedView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.binding = LayoutNavigationFinishedBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public NavigationFinishedView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.binding = LayoutNavigationFinishedBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public void hide() {
        BottomSheetBehavior bottomSheetBehavior = this.mBottomSheetBehavior;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(5);
        }
    }

    public boolean isVisible() {
        BottomSheetBehavior bottomSheetBehavior = this.mBottomSheetBehavior;
        return bottomSheetBehavior != null && bottomSheetBehavior.getState() == 3;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setClickable(false);
        setFocusableInTouchMode(false);
        BottomSheetBehavior from = BottomSheetBehavior.from(this.binding.navigationFinishedLayout);
        this.mBottomSheetBehavior = from;
        from.setHideable(true);
        this.mBottomSheetBehavior.setSkipCollapsed(true);
        this.mBottomSheetBehavior.setPeekHeight(0);
        this.mBottomSheetBehavior.setState(5);
        ELocation destination = MapplsNavigationViewHelper.getInstance().getDestination();
        if (destination != null) {
            TextView textView = this.binding.destinationTextView;
            textView.setText(destination.placeName + ", " + destination.placeAddress);
        } else {
            this.binding.destinationTextView.setText("End Stop");
        }
        this.binding.tripStatsRecyclerView.addItemDecoration(new com.mappls.sdk.navigation.ui.common.a(48));
        this.binding.finishedButton.setOnClickListener(new a());
        this.binding.knowMoreButton.setOnClickListener(new b());
    }

    public void refreshNavigationFinished() {
        ArrayList arrayList = new ArrayList();
        if (MapplsNavigationViewHelper.getInstance().getApplication() != null) {
            arrayList.add(new c("duration", "Duration", NavigationFormatter.getFormattedDuration(NavigationSummaryHelper.getInstance().getTotalTimeTaken(), MapplsNavigationViewHelper.getInstance().getApplication())));
            arrayList.add(new c("distance", "Distance Elapsed", NavigationFormatter.getFormattedDistance(NavigationSummaryHelper.getInstance().getTotalDistance(), MapplsNavigationViewHelper.getInstance().getApplication())));
            arrayList.add(new c("avg_speed", "Average Speed", NavigationFormatter.getFormattedSpeed(NavigationSummaryHelper.getInstance().getAverageSpeed() > 0.0f ? NavigationSummaryHelper.getInstance().getAverageSpeed() : 0.0f, MapplsNavigationViewHelper.getInstance().getApplication())));
        }
        this.binding.tripStatsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        this.binding.tripStatsRecyclerView.setAdapter(new com.mappls.sdk.navigation.ui.navigation.finished.b(arrayList));
    }

    public void setCallback(com.mappls.sdk.navigation.ui.navigation.finished.a aVar) {
        this.callback = aVar;
    }

    public void show() {
        refreshNavigationFinished();
        BottomSheetBehavior bottomSheetBehavior = this.mBottomSheetBehavior;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(3);
        }
    }
}
