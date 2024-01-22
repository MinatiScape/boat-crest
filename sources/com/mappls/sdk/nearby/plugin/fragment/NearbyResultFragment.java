package com.mappls.sdk.nearby.plugin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Keep;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.google.gson.Gson;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import com.mappls.sdk.nearby.plugin.INearbyResultCallback;
import com.mappls.sdk.nearby.plugin.MapplsNearbyWidget;
import com.mappls.sdk.nearby.plugin.R;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyResultFragmentBinding;
import com.mappls.sdk.nearby.plugin.model.MapplsNearbyResponse;
import com.mappls.sdk.nearby.plugin.model.NearbyOption;
import com.mappls.sdk.nearby.plugin.model.NearbyUIOption;
import com.mappls.sdk.nearby.plugin.view.NearbyResultView;
import com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption;
import com.mappls.sdk.nearby.plugin.view.e;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResult;
import java.util.ArrayList;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
/* loaded from: classes10.dex */
public final class NearbyResultFragment extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(0);
    private MapplsNearbyResultFragmentBinding mBinding;
    @Nullable
    private INearbyResultCallback mCallback;
    private com.mappls.sdk.nearby.plugin.viewmodel.b mViewModel;
    @Nullable
    private MapView mapView;
    @Nullable
    private NearbyResultView nearbyResultView;

    @SourceDebugExtension({"SMAP\nNearbyResultFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NearbyResultFragment.kt\ncom/mappls/sdk/nearby/plugin/fragment/NearbyResultFragment$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,196:1\n1855#2,2:197\n1855#2,2:199\n*S KotlinDebug\n*F\n+ 1 NearbyResultFragment.kt\ncom/mappls/sdk/nearby/plugin/fragment/NearbyResultFragment$Companion\n*L\n57#1:197,2\n75#1:199,2\n*E\n"})
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
        public final NearbyResultFragment newInstance(@NotNull MapplsNearbyResponse mapplsNearbyResponse) {
            Intrinsics.checkNotNullParameter(mapplsNearbyResponse, "mapplsNearbyResponse");
            NearbyUIOption build = NearbyUIOption.builder().build();
            Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
            return newInstance(mapplsNearbyResponse, build);
        }

        @JvmStatic
        @Keep
        @NotNull
        public final NearbyResultFragment newInstance(@NotNull MapplsNearbyResponse mapplsNearbyResponse, @NotNull NearbyUIOption nearbyUIOption) {
            Intrinsics.checkNotNullParameter(mapplsNearbyResponse, "mapplsNearbyResponse");
            Intrinsics.checkNotNullParameter(nearbyUIOption, "nearbyUIOption");
            NearbyResultFragment nearbyResultFragment = new NearbyResultFragment();
            Bundle bundle = new Bundle();
            bundle.putString("com.mappls.sdk.nearby.plugin.NEARBY_ATLAS_RESPONSE_KEY", new Gson().toJson(mapplsNearbyResponse.getNearbyAtlasResponse()));
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            for (CategoryCode categoryCode : mapplsNearbyResponse.getSelectedCategory()) {
                arrayList.add(categoryCode);
            }
            bundle.putParcelableArrayList("com.mappls.sdk.nearby.plugin.NEARBY_CATEGORIES_KEY", arrayList);
            bundle.putParcelable("com.mappls.sdk.nearby.plugin.NEARBY_REF_LAT_LNG_KEY", mapplsNearbyResponse.getRefLocation());
            bundle.putString("com.mappls.sdk.nearby.plugin.NEARBY_REF_MAPPLS_PIN_KEY", mapplsNearbyResponse.getRefMapplsPin());
            bundle.putParcelable("com.mappls.sdk.nearby.plugin.NEARBY_OPTION_KEY", nearbyUIOption);
            nearbyResultFragment.setArguments(bundle);
            return nearbyResultFragment;
        }

        @JvmStatic
        @Keep
        @NotNull
        public final NearbyResultFragment newInstance(@NotNull MapplsNearbyResponse mapplsNearbyResponse, @NotNull NearbyUIOption nearbyUIOption, @NotNull NearbyOption nearbyOption) {
            Intrinsics.checkNotNullParameter(mapplsNearbyResponse, "mapplsNearbyResponse");
            Intrinsics.checkNotNullParameter(nearbyUIOption, "nearbyUIOption");
            Intrinsics.checkNotNullParameter(nearbyOption, "nearbyOption");
            NearbyResultFragment nearbyResultFragment = new NearbyResultFragment();
            Bundle bundle = new Bundle();
            bundle.putString("com.mappls.sdk.nearby.plugin.NEARBY_ATLAS_RESPONSE_KEY", new Gson().toJson(mapplsNearbyResponse.getNearbyAtlasResponse()));
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            for (CategoryCode categoryCode : mapplsNearbyResponse.getSelectedCategory()) {
                arrayList.add(categoryCode);
            }
            bundle.putParcelableArrayList("com.mappls.sdk.nearby.plugin.NEARBY_CATEGORIES_KEY", arrayList);
            bundle.putParcelable("com.mappls.sdk.nearby.plugin.NEARBY_REF_LAT_LNG_KEY", mapplsNearbyResponse.getRefLocation());
            bundle.putString("com.mappls.sdk.nearby.plugin.NEARBY_REF_MAPPLS_PIN_KEY", mapplsNearbyResponse.getRefMapplsPin());
            bundle.putParcelable("com.mappls.sdk.nearby.plugin.NEARBY_OPTION_KEY", nearbyUIOption);
            nearbyResultFragment.setArguments(bundle);
            MapplsNearbyWidget.INSTANCE.setNearbyOption$mappls_nearby_widget_othersRelease(nearbyOption);
            return nearbyResultFragment;
        }
    }

    /* loaded from: classes10.dex */
    public static final class a extends OnBackPressedCallback {
        public a() {
            super(true);
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackPressed() {
            FragmentManager fragmentManager = NearbyResultFragment.this.getFragmentManager();
            if (fragmentManager != null) {
                fragmentManager.popBackStack("NearbyResultFragment", 1);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class b implements e {
        public b() {
        }

        @Override // com.mappls.sdk.nearby.plugin.view.e
        public final void a(@NotNull NearbyAtlasResult nearbyResult) {
            Intrinsics.checkNotNullParameter(nearbyResult, "nearbyResult");
            INearbyResultCallback iNearbyResultCallback = NearbyResultFragment.this.mCallback;
            if (iNearbyResultCallback != null) {
                iNearbyResultCallback.onSelectResult(nearbyResult);
            }
        }

        @Override // com.mappls.sdk.nearby.plugin.view.e
        public final void onCancel() {
            INearbyResultCallback iNearbyResultCallback = NearbyResultFragment.this.mCallback;
            if (iNearbyResultCallback != null) {
                iNearbyResultCallback.onCancel();
            }
        }
    }

    @JvmStatic
    @Keep
    @NotNull
    public static final NearbyResultFragment newInstance(@NotNull MapplsNearbyResponse mapplsNearbyResponse) {
        return Companion.newInstance(mapplsNearbyResponse);
    }

    @JvmStatic
    @Keep
    @NotNull
    public static final NearbyResultFragment newInstance(@NotNull MapplsNearbyResponse mapplsNearbyResponse, @NotNull NearbyUIOption nearbyUIOption) {
        return Companion.newInstance(mapplsNearbyResponse, nearbyUIOption);
    }

    @JvmStatic
    @Keep
    @NotNull
    public static final NearbyResultFragment newInstance(@NotNull MapplsNearbyResponse mapplsNearbyResponse, @NotNull NearbyUIOption nearbyUIOption, @NotNull NearbyOption nearbyOption) {
        return Companion.newInstance(mapplsNearbyResponse, nearbyUIOption, nearbyOption);
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.mappls_nearby_result_fragment, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, R.layo…agment, container, false)");
        MapplsNearbyResultFragmentBinding mapplsNearbyResultFragmentBinding = (MapplsNearbyResultFragmentBinding) inflate;
        this.mBinding = mapplsNearbyResultFragmentBinding;
        if (mapplsNearbyResultFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsNearbyResultFragmentBinding = null;
        }
        View root = mapplsNearbyResultFragmentBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "mBinding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        NearbyResultView nearbyResultView = this.nearbyResultView;
        if (nearbyResultView != null) {
            nearbyResultView.onDestroy();
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        NearbyResultView nearbyResultView = this.nearbyResultView;
        if (nearbyResultView != null) {
            nearbyResultView.onLowMemory();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        NearbyResultView nearbyResultView = this.nearbyResultView;
        if (nearbyResultView != null) {
            nearbyResultView.onPause();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        NearbyResultView nearbyResultView = this.nearbyResultView;
        if (nearbyResultView != null) {
            nearbyResultView.onResume();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NotNull Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        NearbyResultView nearbyResultView = this.nearbyResultView;
        if (nearbyResultView != null) {
            nearbyResultView.onSaveInstanceState(outState);
        }
        Gson gson = new Gson();
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this.mViewModel;
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar2 = null;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        outState.putString("com.mappls.sdk.nearby.plugin.NEARBY_ATLAS_RESPONSE_KEY", gson.toJson(bVar.b()));
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar3 = this.mViewModel;
        if (bVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar3 = null;
        }
        outState.putParcelableArrayList("com.mappls.sdk.nearby.plugin.NEARBY_CATEGORIES_KEY", bVar3.g());
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar4 = this.mViewModel;
        if (bVar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar4 = null;
        }
        outState.putString("com.mappls.sdk.nearby.plugin.NEARBY_REF_MAPPLS_PIN_KEY", bVar4.f());
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar5 = this.mViewModel;
        if (bVar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar5 = null;
        }
        outState.putParcelable("com.mappls.sdk.nearby.plugin.NEARBY_REF_LAT_LNG_KEY", bVar5.e());
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar6 = this.mViewModel;
        if (bVar6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            bVar2 = bVar6;
        }
        outState.putParcelable("com.mappls.sdk.nearby.plugin.NEARBY_OPTION_KEY", bVar2.d());
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        NearbyResultView nearbyResultView = this.nearbyResultView;
        if (nearbyResultView != null) {
            nearbyResultView.onStart();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        NearbyResultView nearbyResultView = this.nearbyResultView;
        if (nearbyResultView != null) {
            nearbyResultView.onStop();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.mViewModel = (com.mappls.sdk.nearby.plugin.viewmodel.b) new ViewModelProvider(requireActivity).get(com.mappls.sdk.nearby.plugin.viewmodel.b.class);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new a());
        Bundle arguments = getArguments();
        MapplsNearbyResultFragmentBinding mapplsNearbyResultFragmentBinding = null;
        if (arguments != null) {
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this.mViewModel;
            if (bVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar = null;
            }
            bVar.a((NearbyAtlasResponse) new Gson().fromJson(arguments.getString("com.mappls.sdk.nearby.plugin.NEARBY_ATLAS_RESPONSE_KEY"), (Class<Object>) NearbyAtlasResponse.class));
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar2 = this.mViewModel;
            if (bVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar2 = null;
            }
            bVar2.a(arguments.getParcelableArrayList("com.mappls.sdk.nearby.plugin.NEARBY_CATEGORIES_KEY"));
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar3 = this.mViewModel;
            if (bVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar3 = null;
            }
            bVar3.a(arguments.getString("com.mappls.sdk.nearby.plugin.NEARBY_REF_MAPPLS_PIN_KEY"));
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar4 = this.mViewModel;
            if (bVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar4 = null;
            }
            bVar4.a((LatLng) arguments.getParcelable("com.mappls.sdk.nearby.plugin.NEARBY_REF_LAT_LNG_KEY"));
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar5 = this.mViewModel;
            if (bVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar5 = null;
            }
            if (bVar5.d() == null) {
                com.mappls.sdk.nearby.plugin.viewmodel.b bVar6 = this.mViewModel;
                if (bVar6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    bVar6 = null;
                }
                bVar6.a((NearbyUIOption) arguments.getParcelable("com.mappls.sdk.nearby.plugin.NEARBY_OPTION_KEY"));
            }
            arguments.clear();
        }
        if (bundle != null) {
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar7 = this.mViewModel;
            if (bVar7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar7 = null;
            }
            bVar7.a((NearbyAtlasResponse) new Gson().fromJson(bundle.getString("com.mappls.sdk.nearby.plugin.NEARBY_ATLAS_RESPONSE_KEY"), (Class<Object>) NearbyAtlasResponse.class));
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar8 = this.mViewModel;
            if (bVar8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar8 = null;
            }
            bVar8.a(bundle.getParcelableArrayList("com.mappls.sdk.nearby.plugin.NEARBY_CATEGORIES_KEY"));
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar9 = this.mViewModel;
            if (bVar9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar9 = null;
            }
            bVar9.a(bundle.getString("com.mappls.sdk.nearby.plugin.NEARBY_REF_MAPPLS_PIN_KEY"));
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar10 = this.mViewModel;
            if (bVar10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar10 = null;
            }
            bVar10.a((LatLng) bundle.getParcelable("com.mappls.sdk.nearby.plugin.NEARBY_REF_LAT_LNG_KEY"));
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar11 = this.mViewModel;
            if (bVar11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar11 = null;
            }
            if (bVar11.d() == null) {
                com.mappls.sdk.nearby.plugin.viewmodel.b bVar12 = this.mViewModel;
                if (bVar12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    bVar12 = null;
                }
                bVar12.a((NearbyUIOption) bundle.getParcelable("com.mappls.sdk.nearby.plugin.NEARBY_OPTION_KEY"));
            }
            bundle.clear();
        }
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar13 = this.mViewModel;
        if (bVar13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar13 = null;
        }
        if (bVar13.d() == null) {
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar14 = this.mViewModel;
            if (bVar14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar14 = null;
            }
            bVar14.a(NearbyUIOption.builder().build());
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar15 = this.mViewModel;
        if (bVar15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar15 = null;
        }
        NearbyResultViewOption createFromNearbyOption = NearbyResultViewOption.createFromNearbyOption(bVar15.d());
        Intrinsics.checkNotNullExpressionValue(createFromNearbyOption, "createFromNearbyOption(mViewModel.nearbyUIOption)");
        NearbyResultView nearbyResultView = new NearbyResultView(requireContext, createFromNearbyOption);
        this.nearbyResultView = nearbyResultView;
        MapView mapView = this.mapView;
        if (mapView != null) {
            Intrinsics.checkNotNull(mapView);
            nearbyResultView.provideMapView(mapView);
        }
        MapplsNearbyResultFragmentBinding mapplsNearbyResultFragmentBinding2 = this.mBinding;
        if (mapplsNearbyResultFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        } else {
            mapplsNearbyResultFragmentBinding = mapplsNearbyResultFragmentBinding2;
        }
        mapplsNearbyResultFragmentBinding.mapplsNearbyResultView.addView(this.nearbyResultView);
        NearbyResultView nearbyResultView2 = this.nearbyResultView;
        if (nearbyResultView2 != null) {
            nearbyResultView2.onCreate(bundle);
        }
        NearbyResultView nearbyResultView3 = this.nearbyResultView;
        if (nearbyResultView3 != null) {
            nearbyResultView3.setNearbyResultViewCallback(new b());
        }
    }

    public final void provideMapView(@NotNull MapView mapView) {
        Intrinsics.checkNotNullParameter(mapView, "mapView");
        this.mapView = mapView;
        NearbyResultView nearbyResultView = this.nearbyResultView;
        if (nearbyResultView != null) {
            nearbyResultView.provideMapView(mapView);
        }
    }

    public final void setOnNearbyResultCallback(@NotNull INearbyResultCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.mCallback = callback;
    }
}
