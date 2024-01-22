package com.mappls.sdk.navigation.ui.navigation.searchalongroute;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.mappls.sdk.navigation.ui.databinding.LayoutSearchLongRouteBinding;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
@Keep
/* loaded from: classes11.dex */
public class SearchAlongRouteView extends CoordinatorLayout {
    public LayoutSearchLongRouteBinding binding;
    private ELocation eLocation;
    private com.mappls.sdk.navigation.ui.navigation.searchalongroute.a searchRouteCallback;

    /* loaded from: classes11.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchAlongRouteView.this.hide();
            if (SearchAlongRouteView.this.searchRouteCallback != null) {
                SearchAlongRouteView.this.searchRouteCallback.a(SearchAlongRouteView.this.eLocation);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchAlongRouteView.this.hide();
            if (SearchAlongRouteView.this.searchRouteCallback != null) {
                SearchAlongRouteView.this.searchRouteCallback.b(SearchAlongRouteView.this.eLocation);
            }
        }
    }

    public SearchAlongRouteView(@NonNull Context context) {
        this(context, null);
    }

    public SearchAlongRouteView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SearchAlongRouteView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.binding = LayoutSearchLongRouteBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public void hide() {
        setVisibility(8);
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        hide();
        this.binding.startNewRouteTextView.setOnClickListener(new a());
        this.binding.addWayPointTextView.setOnClickListener(new b());
    }

    public void setRouteDetail(ELocation eLocation) {
        this.eLocation = eLocation;
        this.binding.tvPlaceTitle.setText(eLocation.placeName);
        this.binding.addressTextView.setText(eLocation.placeAddress);
    }

    public void setSearchRouteCallback(com.mappls.sdk.navigation.ui.navigation.searchalongroute.a aVar) {
        this.searchRouteCallback = aVar;
    }

    public void show() {
        setVisibility(0);
    }
}
