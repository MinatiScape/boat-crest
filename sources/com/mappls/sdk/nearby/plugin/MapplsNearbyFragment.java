package com.mappls.sdk.nearby.plugin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Keep;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyBaseFragmentBinding;
import com.mappls.sdk.nearby.plugin.fragment.NearbyFragment;
import com.mappls.sdk.nearby.plugin.fragment.NearbyResultFragment;
import com.mappls.sdk.nearby.plugin.model.MapplsNearbyResponse;
import com.mappls.sdk.nearby.plugin.model.NearbyOption;
import com.mappls.sdk.nearby.plugin.model.NearbyUIOption;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResult;
import java.util.List;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
/* loaded from: classes10.dex */
public final class MapplsNearbyFragment extends Fragment implements a, INearbyResultCallback {
    @NotNull
    public static final Companion Companion = new Companion(0);
    private MapplsNearbyBaseFragmentBinding mBinding;
    @Nullable
    private IMapplsNearbyCallback mCallbcak;
    @Nullable
    private MapView mapView;

    /* loaded from: classes10.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        @JvmStatic
        @Keep
        @NotNull
        public final MapplsNearbyFragment newInstance() {
            NearbyOption build = NearbyOption.builder().build();
            Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
            NearbyUIOption build2 = NearbyUIOption.builder().build();
            Intrinsics.checkNotNullExpressionValue(build2, "builder().build()");
            return newInstance(build, build2);
        }

        @JvmStatic
        @Keep
        @NotNull
        public final MapplsNearbyFragment newInstance(@NotNull NearbyOption nearbyOption) {
            Intrinsics.checkNotNullParameter(nearbyOption, "nearbyOption");
            NearbyUIOption build = NearbyUIOption.builder().build();
            Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
            return newInstance(nearbyOption, build);
        }

        @JvmStatic
        @Keep
        @NotNull
        public final MapplsNearbyFragment newInstance(@NotNull NearbyOption nearbyOption, @NotNull NearbyUIOption neabyUIOption) {
            Intrinsics.checkNotNullParameter(nearbyOption, "nearbyOption");
            Intrinsics.checkNotNullParameter(neabyUIOption, "neabyUIOption");
            MapplsNearbyWidget mapplsNearbyWidget = MapplsNearbyWidget.INSTANCE;
            mapplsNearbyWidget.setNearbyOption$mappls_nearby_widget_othersRelease(nearbyOption);
            mapplsNearbyWidget.setNearbyUIOption$mappls_nearby_widget_othersRelease(neabyUIOption);
            return new MapplsNearbyFragment();
        }

        @JvmStatic
        @Keep
        @NotNull
        public final MapplsNearbyFragment newInstance(@NotNull NearbyUIOption neabyUIOption) {
            Intrinsics.checkNotNullParameter(neabyUIOption, "neabyUIOption");
            NearbyOption build = NearbyOption.builder().build();
            Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
            return newInstance(build, neabyUIOption);
        }
    }

    @JvmStatic
    @Keep
    @NotNull
    public static final MapplsNearbyFragment newInstance() {
        return Companion.newInstance();
    }

    @JvmStatic
    @Keep
    @NotNull
    public static final MapplsNearbyFragment newInstance(@NotNull NearbyOption nearbyOption) {
        return Companion.newInstance(nearbyOption);
    }

    @JvmStatic
    @Keep
    @NotNull
    public static final MapplsNearbyFragment newInstance(@NotNull NearbyOption nearbyOption, @NotNull NearbyUIOption nearbyUIOption) {
        return Companion.newInstance(nearbyOption, nearbyUIOption);
    }

    @JvmStatic
    @Keep
    @NotNull
    public static final MapplsNearbyFragment newInstance(@NotNull NearbyUIOption nearbyUIOption) {
        return Companion.newInstance(nearbyUIOption);
    }

    private final void replaceFragment(Fragment fragment, boolean z) {
        MapplsNearbyBaseFragmentBinding mapplsNearbyBaseFragmentBinding = null;
        Fragment findFragmentByTag = getChildFragmentManager().findFragmentByTag(fragment != null ? fragment.getClass().getSimpleName() : null);
        if (fragment == null || findFragmentByTag != null) {
            return;
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        FragmentTransaction beginTransaction = childFragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "fm.beginTransaction()");
        MapplsNearbyBaseFragmentBinding mapplsNearbyBaseFragmentBinding2 = this.mBinding;
        if (mapplsNearbyBaseFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        } else {
            mapplsNearbyBaseFragmentBinding = mapplsNearbyBaseFragmentBinding2;
        }
        beginTransaction.replace(mapplsNearbyBaseFragmentBinding.mapplsNearbyBaseFragmentContainer.getId(), fragment, fragment.getClass().getSimpleName());
        if (z) {
            beginTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        try {
            beginTransaction.commit();
        } catch (IllegalStateException unused) {
            beginTransaction.commitAllowingStateLoss();
        }
    }

    @Override // com.mappls.sdk.nearby.plugin.INearbyResultCallback
    public void onCancel() {
        getChildFragmentManager().popBackStack("NearbyResultFragment", 1);
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.mappls_nearby_base_fragment, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater,\n      …agment, container, false)");
        MapplsNearbyBaseFragmentBinding mapplsNearbyBaseFragmentBinding = (MapplsNearbyBaseFragmentBinding) inflate;
        this.mBinding = mapplsNearbyBaseFragmentBinding;
        if (mapplsNearbyBaseFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsNearbyBaseFragmentBinding = null;
        }
        View root = mapplsNearbyBaseFragmentBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "mBinding.root");
        return root;
    }

    @Override // com.mappls.sdk.nearby.plugin.a
    public void onSelectCategory(@NotNull MapplsNearbyResponse mapplsNearbyResponse) {
        Intrinsics.checkNotNullParameter(mapplsNearbyResponse, "mapplsNearbyResponse");
        NearbyResultFragment newInstance = NearbyResultFragment.Companion.newInstance(mapplsNearbyResponse, MapplsNearbyWidget.INSTANCE.getNearbyUIOption());
        newInstance.setOnNearbyResultCallback(this);
        replaceFragment(newInstance, true);
        MapView mapView = this.mapView;
        if (mapView != null) {
            Intrinsics.checkNotNull(mapView);
            newInstance.provideMapView(mapView);
        }
    }

    @Override // com.mappls.sdk.nearby.plugin.INearbyResultCallback
    public void onSelectResult(@NotNull NearbyAtlasResult nearbyAtlasResult) {
        Intrinsics.checkNotNullParameter(nearbyAtlasResult, "nearbyAtlasResult");
        IMapplsNearbyCallback iMapplsNearbyCallback = this.mCallbcak;
        if (iMapplsNearbyCallback != null) {
            iMapplsNearbyCallback.getNearbyCallback(nearbyAtlasResult);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        NearbyUIOption nearbyUIOption = MapplsNearbyWidget.INSTANCE.getNearbyUIOption();
        if (bundle == null) {
            NearbyFragment newInstance = NearbyFragment.Companion.newInstance(nearbyUIOption);
            newInstance.setNearbyCallback(this);
            replaceFragment(newInstance, false);
            return;
        }
        NearbyFragment nearbyFragment = (NearbyFragment) getChildFragmentManager().findFragmentByTag("NearbyFragment");
        if (nearbyFragment != null) {
            nearbyFragment.setNearbyCallback(this);
        }
        NearbyResultFragment nearbyResultFragment = (NearbyResultFragment) getChildFragmentManager().findFragmentByTag("NearbyResultFragment");
        if (nearbyResultFragment != null) {
            nearbyResultFragment.setOnNearbyResultCallback(this);
        }
    }

    public final void provideMapView(@NotNull MapView mapView) {
        NearbyResultFragment nearbyResultFragment;
        Intrinsics.checkNotNullParameter(mapView, "mapView");
        this.mapView = mapView;
        if (!isAdded() || (nearbyResultFragment = (NearbyResultFragment) getChildFragmentManager().findFragmentByTag(NearbyResultFragment.class.getName())) == null) {
            return;
        }
        nearbyResultFragment.provideMapView(mapView);
    }

    public final void setCategoryList(@NotNull List<? extends CategoryCode> categories) {
        NearbyFragment nearbyFragment;
        Intrinsics.checkNotNullParameter(categories, "categories");
        MapplsNearbyWidget.INSTANCE.setCategoryList$mappls_nearby_widget_othersRelease(categories);
        if (!isAdded() || (nearbyFragment = (NearbyFragment) getChildFragmentManager().findFragmentByTag("NearbyFragment")) == null) {
            return;
        }
        nearbyFragment.setCategoryCodes(categories);
    }

    public final void setMapplsNearbyCallback(@NotNull IMapplsNearbyCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.mCallbcak = callback;
    }
}
