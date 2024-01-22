package com.mappls.sdk.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Keep;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.mappls.sdk.category.databinding.MapplsCategorySearchFragmentBinding;
import com.mappls.sdk.category.fragment.CategoryResultFragment;
import com.mappls.sdk.category.fragment.CategorySearchFragment;
import com.mappls.sdk.category.fragment.ICategoryResultListener;
import com.mappls.sdk.category.fragment.ICategorySelectionListener;
import com.mappls.sdk.category.model.PoiResult;
import com.mappls.sdk.category.model.SearchCategoryOption;
import com.mappls.sdk.category.model.SearchCategoryUIOption;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.MapplsMapOptions;
import com.mappls.sdk.maps.OnMapReadyCallback;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;
@Keep
/* loaded from: classes11.dex */
public final class MapplsCategorySearchFragment extends Fragment implements OnMapReadyCallback, ICategorySelectionListener, ICategoryResultListener {
    @NotNull
    public static final Companion Companion = new Companion(0);
    private MapplsCategorySearchFragmentBinding mBinding;
    @Nullable
    private IMapplsCategoryCallback mCallback;
    @Nullable
    private MapView mMapView;
    @Nullable
    private IMapplsPOICallback mPoiCallback;

    /* loaded from: classes11.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        @JvmStatic
        @Keep
        @NotNull
        public final MapplsCategorySearchFragment newInstance() {
            SearchCategoryOption build = SearchCategoryOption.builder().build();
            Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
            SearchCategoryUIOption build2 = SearchCategoryUIOption.builder().build();
            Intrinsics.checkNotNullExpressionValue(build2, "builder().build()");
            return newInstance(build, build2);
        }

        @JvmStatic
        @Keep
        @NotNull
        public final MapplsCategorySearchFragment newInstance(@NotNull SearchCategoryOption options) {
            Intrinsics.checkNotNullParameter(options, "options");
            SearchCategoryUIOption build = SearchCategoryUIOption.builder().build();
            Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
            return newInstance(options, build);
        }

        @JvmStatic
        @Keep
        @NotNull
        public final MapplsCategorySearchFragment newInstance(@NotNull SearchCategoryOption options, @NotNull SearchCategoryUIOption uiOption) {
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(uiOption, "uiOption");
            a.a();
            a.a(options);
            a.a(uiOption);
            return new MapplsCategorySearchFragment();
        }

        @JvmStatic
        @Keep
        @NotNull
        public final MapplsCategorySearchFragment newInstance(@NotNull SearchCategoryUIOption uiOption) {
            Intrinsics.checkNotNullParameter(uiOption, "uiOption");
            SearchCategoryOption build = SearchCategoryOption.builder().build();
            Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
            return newInstance(build, uiOption);
        }
    }

    @JvmStatic
    @Keep
    @NotNull
    public static final MapplsCategorySearchFragment newInstance() {
        return Companion.newInstance();
    }

    @JvmStatic
    @Keep
    @NotNull
    public static final MapplsCategorySearchFragment newInstance(@NotNull SearchCategoryOption searchCategoryOption) {
        return Companion.newInstance(searchCategoryOption);
    }

    @JvmStatic
    @Keep
    @NotNull
    public static final MapplsCategorySearchFragment newInstance(@NotNull SearchCategoryOption searchCategoryOption, @NotNull SearchCategoryUIOption searchCategoryUIOption) {
        return Companion.newInstance(searchCategoryOption, searchCategoryUIOption);
    }

    @JvmStatic
    @Keep
    @NotNull
    public static final MapplsCategorySearchFragment newInstance(@NotNull SearchCategoryUIOption searchCategoryUIOption) {
        return Companion.newInstance(searchCategoryUIOption);
    }

    private final void replaceFragment(Fragment fragment, boolean z) {
        MapplsCategorySearchFragmentBinding mapplsCategorySearchFragmentBinding = null;
        Fragment findFragmentByTag = getChildFragmentManager().findFragmentByTag(fragment != null ? fragment.getClass().getSimpleName() : null);
        if (fragment == null || findFragmentByTag != null) {
            return;
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        FragmentTransaction beginTransaction = childFragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "fm.beginTransaction()");
        MapplsCategorySearchFragmentBinding mapplsCategorySearchFragmentBinding2 = this.mBinding;
        if (mapplsCategorySearchFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        } else {
            mapplsCategorySearchFragmentBinding = mapplsCategorySearchFragmentBinding2;
        }
        beginTransaction.replace(mapplsCategorySearchFragmentBinding.mapplsCategoryView.getId(), fragment, fragment.getClass().getSimpleName());
        if (z) {
            beginTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        try {
            beginTransaction.commit();
        } catch (IllegalStateException unused) {
            beginTransaction.commitAllowingStateLoss();
        }
    }

    @Override // com.mappls.sdk.category.fragment.ICategorySelectionListener
    public void onCancel() {
        IMapplsCategoryCallback iMapplsCategoryCallback = this.mCallback;
        if (iMapplsCategoryCallback != null) {
            iMapplsCategoryCallback.onCancel();
        }
    }

    @Override // com.mappls.sdk.category.fragment.ICategoryResultListener
    public void onCategoryResult(@NotNull List<PoiResult> poiResults) {
        Intrinsics.checkNotNullParameter(poiResults, "poiResults");
        IMapplsPOICallback iMapplsPOICallback = this.mPoiCallback;
        if (iMapplsPOICallback != null) {
            iMapplsPOICallback.getPOIResultListener(new ArrayList(poiResults));
        }
    }

    @Override // com.mappls.sdk.category.fragment.ICategorySelectionListener
    public void onCategorySelected(@NotNull List<? extends CategoryCode> categories) {
        Intrinsics.checkNotNullParameter(categories, "categories");
        CategoryResultFragment.a aVar = CategoryResultFragment.Companion;
        ArrayList arrayList = new ArrayList(categories);
        SearchCategoryUIOption d = a.d();
        aVar.getClass();
        CategoryResultFragment a2 = CategoryResultFragment.a.a(arrayList, d);
        a2.setCategoryResultListener(this);
        replaceFragment(a2, true);
        MapView mapView = this.mMapView;
        if (mapView != null) {
            Intrinsics.checkNotNull(mapView);
            a2.setMapView(mapView);
        }
    }

    @Override // com.mappls.sdk.category.fragment.ICategoryResultListener
    public void onCategorySelectedResult(@NotNull PoiResult poiResult) {
        Intrinsics.checkNotNullParameter(poiResult, "poiResult");
        IMapplsCategoryCallback iMapplsCategoryCallback = this.mCallback;
        if (iMapplsCategoryCallback != null) {
            iMapplsCategoryCallback.onCategorySelected(poiResult);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        MapplsCategorySearchFragmentBinding inflate = MapplsCategorySearchFragmentBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.mBinding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            inflate = null;
        }
        View root = inflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "mBinding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        MapView mapView;
        super.onDestroyView();
        Boolean isUsingInternalMap = a.c().isUsingInternalMap();
        Intrinsics.checkNotNullExpressionValue(isUsingInternalMap, "MapplsCategoryWidget.sea…Option.isUsingInternalMap");
        if (isUsingInternalMap.booleanValue() && (mapView = this.mMapView) != null) {
            mapView.onDestroy();
        }
        a.a();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        MapView mapView;
        super.onLowMemory();
        Boolean isUsingInternalMap = a.c().isUsingInternalMap();
        Intrinsics.checkNotNullExpressionValue(isUsingInternalMap, "MapplsCategoryWidget.sea…Option.isUsingInternalMap");
        if (!isUsingInternalMap.booleanValue() || (mapView = this.mMapView) == null) {
            return;
        }
        mapView.onLowMemory();
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapError(int i, @Nullable String str) {
        Timber.e(i + " --- " + str, new Object[0]);
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapReady(@NotNull MapplsMap mapplsMap) {
        Intrinsics.checkNotNullParameter(mapplsMap, "mapplsMap");
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        MapView mapView;
        super.onPause();
        Boolean isUsingInternalMap = a.c().isUsingInternalMap();
        Intrinsics.checkNotNullExpressionValue(isUsingInternalMap, "MapplsCategoryWidget.sea…Option.isUsingInternalMap");
        if (!isUsingInternalMap.booleanValue() || (mapView = this.mMapView) == null) {
            return;
        }
        mapView.onPause();
    }

    @Override // com.mappls.sdk.category.fragment.ICategoryResultListener
    public void onResultCancel() {
        getChildFragmentManager().popBackStack("CategoryResultFragment", 1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        MapView mapView;
        super.onResume();
        Boolean isUsingInternalMap = a.c().isUsingInternalMap();
        Intrinsics.checkNotNullExpressionValue(isUsingInternalMap, "MapplsCategoryWidget.sea…Option.isUsingInternalMap");
        if (!isUsingInternalMap.booleanValue() || (mapView = this.mMapView) == null) {
            return;
        }
        mapView.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NotNull Bundle outState) {
        MapView mapView;
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        Boolean isUsingInternalMap = a.c().isUsingInternalMap();
        Intrinsics.checkNotNullExpressionValue(isUsingInternalMap, "MapplsCategoryWidget.sea…Option.isUsingInternalMap");
        if (!isUsingInternalMap.booleanValue() || (mapView = this.mMapView) == null) {
            return;
        }
        mapView.onSaveInstanceState(outState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        MapView mapView;
        super.onStart();
        Boolean isUsingInternalMap = a.c().isUsingInternalMap();
        Intrinsics.checkNotNullExpressionValue(isUsingInternalMap, "MapplsCategoryWidget.sea…Option.isUsingInternalMap");
        if (!isUsingInternalMap.booleanValue() || (mapView = this.mMapView) == null) {
            return;
        }
        mapView.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        MapView mapView;
        super.onStop();
        Boolean isUsingInternalMap = a.c().isUsingInternalMap();
        Intrinsics.checkNotNullExpressionValue(isUsingInternalMap, "MapplsCategoryWidget.sea…Option.isUsingInternalMap");
        if (!isUsingInternalMap.booleanValue() || (mapView = this.mMapView) == null) {
            return;
        }
        mapView.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Boolean isUsingInternalMap = a.c().isUsingInternalMap();
        Intrinsics.checkNotNullExpressionValue(isUsingInternalMap, "MapplsCategoryWidget.sea…Option.isUsingInternalMap");
        if (isUsingInternalMap.booleanValue() && this.mMapView == null) {
            this.mMapView = new MapView(requireContext(), MapplsMapOptions.createFromAttributes(requireContext()).textureMode(true));
            MapplsCategorySearchFragmentBinding mapplsCategorySearchFragmentBinding = this.mBinding;
            if (mapplsCategorySearchFragmentBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                mapplsCategorySearchFragmentBinding = null;
            }
            mapplsCategorySearchFragmentBinding.mapplsCategoryView.addView(this.mMapView);
            MapView mapView = this.mMapView;
            if (mapView != null) {
                mapView.onCreate(bundle);
            }
        }
        if (bundle == null) {
            CategorySearchFragment.a aVar = CategorySearchFragment.Companion;
            SearchCategoryUIOption d = a.d();
            aVar.getClass();
            CategorySearchFragment a2 = CategorySearchFragment.a.a(d);
            a2.setCategorySelectionListener(this);
            replaceFragment(a2, false);
            return;
        }
        CategorySearchFragment categorySearchFragment = (CategorySearchFragment) getChildFragmentManager().findFragmentByTag("CategorySearchFragment");
        if (categorySearchFragment != null) {
            categorySearchFragment.setCategorySelectionListener(this);
        }
        CategoryResultFragment categoryResultFragment = (CategoryResultFragment) getChildFragmentManager().findFragmentByTag("CategoryResultFragment");
        MapView mapView2 = this.mMapView;
        if (mapView2 != null && categoryResultFragment != null) {
            Intrinsics.checkNotNull(mapView2);
            categoryResultFragment.setMapView(mapView2);
        }
        if (categoryResultFragment != null) {
            categoryResultFragment.setCategoryResultListener(this);
        }
    }

    public final void provideMapView(@NotNull MapView mapView) {
        Intrinsics.checkNotNullParameter(mapView, "mapView");
        if (this.mMapView != null) {
            throw new RuntimeException("MapView is already added");
        }
        this.mMapView = mapView;
    }

    public final void setCategoryCallback(@NotNull IMapplsCategoryCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.mCallback = callback;
    }

    public final void setCategoryCodes(@NotNull List<? extends CategoryCode> categories) {
        CategorySearchFragment categorySearchFragment;
        Intrinsics.checkNotNullParameter(categories, "categories");
        a.a(categories);
        if (!isAdded() || (categorySearchFragment = (CategorySearchFragment) getChildFragmentManager().findFragmentByTag("CategorySearchFragment")) == null) {
            return;
        }
        categorySearchFragment.setCategoryCodes(categories);
    }

    public final void setPoiResultCallback(@NotNull IMapplsPOICallback mPOICallback) {
        Intrinsics.checkNotNullParameter(mPOICallback, "mPOICallback");
        this.mPoiCallback = mPOICallback;
    }
}
