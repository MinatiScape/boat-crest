package com.mappls.sdk.navigation.ui.navigation.directions;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.routing.NavigationStep;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.databinding.LayoutDirectionListBinding;
import java.util.List;
@Keep
/* loaded from: classes11.dex */
public class DirectionsListView extends CoordinatorLayout {
    private com.mappls.sdk.navigation.ui.navigation.directions.a adapter;
    public LayoutDirectionListBinding binding;
    private BottomSheetBehavior mBottomSheetBehavior;

    /* loaded from: classes11.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (DirectionsListView.this.mBottomSheetBehavior != null) {
                DirectionsListView.this.mBottomSheetBehavior.setState(5);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (DirectionsListView.this.mBottomSheetBehavior != null) {
                DirectionsListView.this.mBottomSheetBehavior.setState(5);
            }
        }
    }

    public DirectionsListView(@NonNull Context context) {
        this(context, null);
    }

    public DirectionsListView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public DirectionsListView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.binding = LayoutDirectionListBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public void hide() {
        BottomSheetBehavior bottomSheetBehavior = this.mBottomSheetBehavior;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(5);
        }
    }

    public void init(NavigationApplication navigationApplication) {
        com.mappls.sdk.navigation.ui.navigation.directions.a aVar = new com.mappls.sdk.navigation.ui.navigation.directions.a(navigationApplication);
        this.adapter = aVar;
        this.binding.rvDirectionList.setAdapter(aVar);
        this.binding.rvDirectionList.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter.a(MapplsNavigationHelper.getInstance().getNavigationSteps());
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
        toggleTheme();
        BottomSheetBehavior from = BottomSheetBehavior.from(this.binding.directionListView);
        this.mBottomSheetBehavior = from;
        from.setHideable(true);
        this.mBottomSheetBehavior.setSkipCollapsed(true);
        this.mBottomSheetBehavior.setPeekHeight(0);
        this.mBottomSheetBehavior.setState(5);
        this.binding.closeBottomSheet.setOnClickListener(new a());
        this.binding.btnShowMap.setOnClickListener(new b());
    }

    public boolean onBackPress() {
        BottomSheetBehavior bottomSheetBehavior = this.mBottomSheetBehavior;
        if (bottomSheetBehavior == null || bottomSheetBehavior.getState() != 3) {
            return false;
        }
        this.mBottomSheetBehavior.setState(5);
        return true;
    }

    public void onNewRoute(NavigationApplication navigationApplication) {
        if (navigationApplication == null) {
            return;
        }
        List<NavigationStep> navigationSteps = MapplsNavigationHelper.getInstance().getNavigationSteps();
        if (this.adapter == null) {
            com.mappls.sdk.navigation.ui.navigation.directions.a aVar = new com.mappls.sdk.navigation.ui.navigation.directions.a(navigationApplication);
            this.adapter = aVar;
            this.binding.rvDirectionList.setAdapter(aVar);
            this.binding.rvDirectionList.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        this.adapter.a(navigationSteps);
    }

    public void setOnDirectionClick(com.mappls.sdk.navigation.ui.navigation.directions.b bVar) {
        com.mappls.sdk.navigation.ui.navigation.directions.a aVar = this.adapter;
        if (aVar != null) {
            aVar.a(bVar);
        }
    }

    public void show() {
        BottomSheetBehavior bottomSheetBehavior = this.mBottomSheetBehavior;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(3);
        }
    }

    public void toggleTheme() {
        com.mappls.sdk.navigation.ui.navigation.directions.a aVar = this.adapter;
        if (aVar != null) {
            aVar.a(MapplsNavigationHelper.getInstance().getNavigationSteps());
        }
        RelativeLayout relativeLayout = this.binding.directionListView;
        Context context = getContext();
        int i = R.attr.navigationViewPrimary;
        relativeLayout.setBackgroundColor(com.mappls.sdk.navigation.ui.theme.a.b(context, i));
        this.binding.toolbarDirection.setCardBackgroundColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i));
        this.binding.toolbarText.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), R.attr.navigationTextColorPrimary));
        this.binding.closeBottomSheet.setImageDrawable(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewBackButtonDrawable));
    }
}
