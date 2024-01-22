package com.mappls.sdk.category.fragment;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Keep;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Constants;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mappls.sdk.category.R;
import com.mappls.sdk.category.adapters.b;
import com.mappls.sdk.category.databinding.MapplsCategoryResultFragmentBinding;
import com.mappls.sdk.category.model.PoiResult;
import com.mappls.sdk.category.model.SearchCategoryUIOption;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.utils.PolylineUtils;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.OnMapReadyCallback;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.UiSettings;
import com.mappls.sdk.maps.camera.CameraMapplsPinPosition;
import com.mappls.sdk.maps.camera.CameraMapplsPinUpdateFactory;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.maps.utils.BitmapUtils;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import com.mappls.sdk.plugin.annotation.OnSymbolClickListener;
import com.mappls.sdk.plugin.annotation.Symbol;
import com.mappls.sdk.services.api.alongroute.models.POIAlongRouteResponse;
import com.mappls.sdk.services.api.alongroute.models.SuggestedPOI;
import com.mappls.sdk.services.api.autosuggest.model.AddressTokens;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
@SourceDebugExtension({"SMAP\nCategoryResultFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CategoryResultFragment.kt\ncom/mappls/sdk/category/fragment/CategoryResultFragment\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,542:1\n1855#2,2:543\n1855#2,2:545\n1855#2:547\n1855#2,2:548\n1856#2:550\n1855#2,2:551\n1855#2,2:553\n1855#2,2:555\n1855#2,2:557\n1855#2:559\n1855#2,2:560\n1856#2:562\n*S KotlinDebug\n*F\n+ 1 CategoryResultFragment.kt\ncom/mappls/sdk/category/fragment/CategoryResultFragment\n*L\n165#1:543,2\n194#1:545,2\n302#1:547\n304#1:548,2\n302#1:550\n320#1:551,2\n338#1:553,2\n423#1:555,2\n427#1:557,2\n446#1:559\n461#1:560,2\n446#1:562\n*E\n"})
/* loaded from: classes11.dex */
public final class CategoryResultFragment extends Fragment implements OnMapReadyCallback, OnSymbolClickListener {
    @NotNull
    public static final a Companion = new a(0);
    private MapplsCategoryResultFragmentBinding mBinding;
    @Nullable
    private ICategoryResultListener mCallback;
    @Nullable
    private com.mappls.sdk.category.utils.a mMapPlugin;
    @Nullable
    private MapView mMapView;
    @Nullable
    private MapplsMap mMapplsMap;
    private com.mappls.sdk.category.viewmodel.a mViewModel;
    private com.mappls.sdk.category.adapters.b poiResultAdapter;
    @NotNull
    private final Map<String, String> iconMap = new HashMap();
    @NotNull
    private final Observer<com.mappls.sdk.category.utils.c<NearbyAtlasResponse>> nearbyObserver = new Observer() { // from class: com.mappls.sdk.category.fragment.d
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            CategoryResultFragment.nearbyObserver$lambda$0(CategoryResultFragment.this, (com.mappls.sdk.category.utils.c) obj);
        }
    };
    @NotNull
    private final Observer<com.mappls.sdk.category.utils.c<POIAlongRouteResponse>> poiAlongRouteObserver = new Observer() { // from class: com.mappls.sdk.category.fragment.e
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            CategoryResultFragment.poiAlongRouteObserver$lambda$1(CategoryResultFragment.this, (com.mappls.sdk.category.utils.c) obj);
        }
    };

    /* loaded from: classes11.dex */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(int i) {
            this();
        }

        @JvmStatic
        @NotNull
        public static CategoryResultFragment a(@NotNull ArrayList selectedCategories) {
            Intrinsics.checkNotNullParameter(selectedCategories, "selectedCategories");
            SearchCategoryUIOption build = SearchCategoryUIOption.builder().build();
            Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
            return a(selectedCategories, build);
        }

        @JvmStatic
        @NotNull
        public static CategoryResultFragment a(@NotNull ArrayList selectedCategories, @NotNull SearchCategoryUIOption options) {
            Intrinsics.checkNotNullParameter(selectedCategories, "selectedCategories");
            Intrinsics.checkNotNullParameter(options, "options");
            CategoryResultFragment categoryResultFragment = new CategoryResultFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("com.mappls.sdk.category.CATEGORY_UI_OPTION", options);
            bundle.putParcelableArrayList("com.mappls.sdk.category.SELECTED_CATEGORY_CODES", selectedCategories);
            categoryResultFragment.setArguments(bundle);
            return categoryResultFragment;
        }
    }

    /* loaded from: classes11.dex */
    public static final class b extends OnBackPressedCallback {
        public b() {
            super(true);
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackPressed() {
            CategoryResultFragment.this.getParentFragmentManager().popBackStack("CategoryResultFragment", 1);
        }
    }

    /* loaded from: classes11.dex */
    public static final class c implements b.a {
        public c() {
        }

        @Override // com.mappls.sdk.category.adapters.b.a
        public final void a(@NotNull PoiResult data) {
            Intrinsics.checkNotNullParameter(data, "data");
            ICategoryResultListener iCategoryResultListener = CategoryResultFragment.this.mCallback;
            if (iCategoryResultListener != null) {
                iCategoryResultListener.onCategorySelectedResult(data);
            }
        }
    }

    private final void callPoi() {
        com.mappls.sdk.category.viewmodel.a aVar = this.mViewModel;
        com.mappls.sdk.category.viewmodel.a aVar2 = null;
        if (aVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar = null;
        }
        Integer searchType = aVar.d().searchType();
        if (searchType != null && searchType.intValue() == 0) {
            com.mappls.sdk.category.viewmodel.a aVar3 = this.mViewModel;
            if (aVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                aVar3 = null;
            }
            List<CategoryCode> f = aVar3.f();
            if (f != null) {
                com.mappls.sdk.category.viewmodel.a aVar4 = this.mViewModel;
                if (aVar4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                } else {
                    aVar2 = aVar4;
                }
                aVar2.a(f);
                return;
            }
            return;
        }
        com.mappls.sdk.category.viewmodel.a aVar5 = this.mViewModel;
        if (aVar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar5 = null;
        }
        List<CategoryCode> f2 = aVar5.f();
        if (f2 != null) {
            com.mappls.sdk.category.viewmodel.a aVar6 = this.mViewModel;
            if (aVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                aVar2 = aVar6;
            }
            aVar2.b(f2);
        }
    }

    private final List<PoiResult> getPOIResultFromAlongRoute(List<? extends SuggestedPOI> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (SuggestedPOI suggestedPOI : list) {
                PoiResult poiResult = new PoiResult();
                poiResult.setPlaceName(suggestedPOI.getPoi());
                poiResult.setPlaceAddress(suggestedPOI.getAddress());
                Integer distance = suggestedPOI.getDistance();
                com.mappls.sdk.category.viewmodel.a aVar = null;
                poiResult.setDistance(distance != null ? Long.valueOf(distance.intValue()) : null);
                poiResult.setLatitude(suggestedPOI.getLatitude());
                poiResult.setLongitude(suggestedPOI.getLongitude());
                poiResult.setMapplsPin(suggestedPOI.getMapplsPin());
                poiResult.setKeywords(kotlin.collections.e.listOf(suggestedPOI.getCategory()));
                com.mappls.sdk.category.viewmodel.a aVar2 = this.mViewModel;
                if (aVar2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                } else {
                    aVar = aVar2;
                }
                poiResult.setPoiType((String) aVar.b().get(suggestedPOI.getCategory()));
                poiResult.setBrandCode(suggestedPOI.getBrandCode());
                AddressTokens addressTokens = new AddressTokens();
                addressTokens.city = suggestedPOI.getCity();
                addressTokens.district = suggestedPOI.getDistrict();
                addressTokens.locality = suggestedPOI.getLocality();
                addressTokens.pincode = suggestedPOI.getLocality();
                addressTokens.state = suggestedPOI.getState();
                addressTokens.subDistrict = suggestedPOI.getSubDistrict();
                addressTokens.subLocality = suggestedPOI.getSubLocality();
                addressTokens.subSubLocality = suggestedPOI.getSubSubLocality();
                poiResult.setAddressToken(addressTokens);
                arrayList.add(poiResult);
            }
        }
        return arrayList;
    }

    private final List<PoiResult> getPOIResultFromNearby(List<? extends NearbyAtlasResult> list) {
        ArrayList arrayList = new ArrayList();
        for (NearbyAtlasResult nearbyAtlasResult : list) {
            PoiResult poiResult = new PoiResult();
            poiResult.setPlaceName(nearbyAtlasResult.placeName);
            poiResult.setPlaceAddress(nearbyAtlasResult.placeAddress);
            poiResult.setDistance(nearbyAtlasResult.distance);
            poiResult.setLatitude(nearbyAtlasResult.latitude);
            poiResult.setLongitude(nearbyAtlasResult.longitude);
            poiResult.setMapplsPin(nearbyAtlasResult.mapplsPin);
            poiResult.setKeywords(nearbyAtlasResult.keywords);
            com.mappls.sdk.category.viewmodel.a aVar = this.mViewModel;
            if (aVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                aVar = null;
            }
            poiResult.setPoiType((String) aVar.b().get(nearbyAtlasResult.keywords.get(0)));
            poiResult.setAddressToken(nearbyAtlasResult.addressTokens);
            arrayList.add(poiResult);
        }
        return arrayList;
    }

    private final void initialiseMapCamera() {
        List split$default;
        String str;
        List split$default2;
        String str2;
        com.mappls.sdk.category.viewmodel.a aVar = this.mViewModel;
        com.mappls.sdk.category.viewmodel.a aVar2 = null;
        if (aVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar = null;
        }
        Integer searchType = aVar.d().searchType();
        boolean z = false;
        if (searchType != null && searchType.intValue() == 0) {
            com.mappls.sdk.category.viewmodel.a aVar3 = this.mViewModel;
            if (aVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                aVar3 = null;
            }
            String location = aVar3.d().location();
            if (!(location != null && StringsKt__StringsKt.contains$default((CharSequence) location, (CharSequence) Constants.SEPARATOR_COMMA, false, 2, (Object) null))) {
                MapplsMap mapplsMap = this.mMapplsMap;
                if (mapplsMap != null) {
                    CameraMapplsPinPosition.Builder builder = new CameraMapplsPinPosition.Builder();
                    com.mappls.sdk.category.viewmodel.a aVar4 = this.mViewModel;
                    if (aVar4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    } else {
                        aVar2 = aVar4;
                    }
                    mapplsMap.setCameraMapplsPinPosition(builder.target(aVar2.d().location()).zoom(12.0d).build());
                    return;
                }
                return;
            }
            MapplsMap mapplsMap2 = this.mMapplsMap;
            if (mapplsMap2 == null) {
                return;
            }
            CameraPosition.Builder builder2 = new CameraPosition.Builder();
            com.mappls.sdk.category.viewmodel.a aVar5 = this.mViewModel;
            if (aVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                aVar5 = null;
            }
            String location2 = aVar5.d().location();
            double d = 0.0d;
            double parseDouble = (location2 == null || (split$default2 = StringsKt__StringsKt.split$default((CharSequence) location2, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null)) == null || (str2 = (String) split$default2.get(0)) == null) ? 0.0d : Double.parseDouble(str2);
            com.mappls.sdk.category.viewmodel.a aVar6 = this.mViewModel;
            if (aVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                aVar2 = aVar6;
            }
            String location3 = aVar2.d().location();
            if (location3 != null && (split$default = StringsKt__StringsKt.split$default((CharSequence) location3, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null)) != null && (str = (String) split$default.get(1)) != null) {
                d = Double.parseDouble(str);
            }
            mapplsMap2.setCameraPosition(builder2.target(new LatLng(parseDouble, d)).zoom(12.0d).build());
            return;
        }
        ArrayList arrayList = new ArrayList();
        com.mappls.sdk.category.viewmodel.a aVar7 = this.mViewModel;
        if (aVar7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar7 = null;
        }
        String geometries = aVar7.d().geometries();
        if (geometries != null && m.equals(geometries, "polyline", true)) {
            z = true;
        }
        if (z) {
            com.mappls.sdk.category.viewmodel.a aVar8 = this.mViewModel;
            if (aVar8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                aVar2 = aVar8;
            }
            String path = aVar2.d().path();
            Intrinsics.checkNotNull(path);
            List<Point> decode = PolylineUtils.decode(path, 5);
            Intrinsics.checkNotNullExpressionValue(decode, "decode(mViewModel.option…!, Constants.PRECISION_5)");
            for (Point point : decode) {
                arrayList.add(new LatLng(point.latitude(), point.longitude()));
            }
        } else {
            com.mappls.sdk.category.viewmodel.a aVar9 = this.mViewModel;
            if (aVar9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                aVar2 = aVar9;
            }
            String path2 = aVar2.d().path();
            Intrinsics.checkNotNull(path2);
            List<Point> decode2 = PolylineUtils.decode(path2, 6);
            Intrinsics.checkNotNullExpressionValue(decode2, "decode(mViewModel.option…!, Constants.PRECISION_6)");
            for (Point point2 : decode2) {
                arrayList.add(new LatLng(point2.latitude(), point2.longitude()));
            }
        }
        MapplsMap mapplsMap3 = this.mMapplsMap;
        if (mapplsMap3 != null) {
            mapplsMap3.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds.Builder().includes(arrayList).build(), 20));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void nearbyObserver$lambda$0(CategoryResultFragment this$0, com.mappls.sdk.category.utils.c cVar) {
        View view;
        Integer totalPages;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int ordinal = cVar.c().ordinal();
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding = null;
        com.mappls.sdk.category.adapters.b bVar = null;
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding2 = null;
        if (ordinal == 0) {
            if (cVar.a() == null || ((totalPages = ((NearbyAtlasResponse) cVar.a()).getPageInfo().getTotalPages()) != null && totalPages.intValue() == 0)) {
                MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding3 = this$0.mBinding;
                if (mapplsCategoryResultFragmentBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                    mapplsCategoryResultFragmentBinding3 = null;
                }
                mapplsCategoryResultFragmentBinding3.mapplsCategoryLayoutEmptyErrorState.setVisibility(0);
                MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding4 = this$0.mBinding;
                if (mapplsCategoryResultFragmentBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                    mapplsCategoryResultFragmentBinding4 = null;
                }
                ((TextView) mapplsCategoryResultFragmentBinding4.mapplsCategoryLayoutEmptyErrorState.findViewById(R.id.mappls_category_text_view_empty_state)).setText(this$0.getString(R.string.mappls_category_no_results));
                MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding5 = this$0.mBinding;
                if (mapplsCategoryResultFragmentBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                    mapplsCategoryResultFragmentBinding5 = null;
                }
                ((TextView) mapplsCategoryResultFragmentBinding5.mapplsCategoryLayoutEmptyErrorState.findViewById(R.id.mappls_category_text_view_retry)).setVisibility(8);
                MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding6 = this$0.mBinding;
                if (mapplsCategoryResultFragmentBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                    mapplsCategoryResultFragmentBinding6 = null;
                }
                mapplsCategoryResultFragmentBinding6.mapplsCategoryTextViewResults.setVisibility(8);
                com.mappls.sdk.category.adapters.b bVar2 = this$0.poiResultAdapter;
                if (bVar2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("poiResultAdapter");
                    bVar2 = null;
                }
                bVar2.a(new ArrayList());
            } else {
                MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding7 = this$0.mBinding;
                if (mapplsCategoryResultFragmentBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                    mapplsCategoryResultFragmentBinding7 = null;
                }
                mapplsCategoryResultFragmentBinding7.mapplsCategoryLayoutEmptyErrorState.setVisibility(8);
                ArrayList<NearbyAtlasResult> suggestedLocations = ((NearbyAtlasResponse) cVar.a()).getSuggestedLocations();
                Intrinsics.checkNotNullExpressionValue(suggestedLocations, "it.data.suggestedLocations");
                this$0.setPoiResultData(this$0.getPOIResultFromNearby(suggestedLocations));
            }
            MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding8 = this$0.mBinding;
            if (mapplsCategoryResultFragmentBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            } else {
                mapplsCategoryResultFragmentBinding = mapplsCategoryResultFragmentBinding8;
            }
            view = mapplsCategoryResultFragmentBinding.mapplsCategoryProgressLayout;
        } else if (ordinal != 1) {
            if (ordinal != 2) {
                return;
            }
            MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding9 = this$0.mBinding;
            if (mapplsCategoryResultFragmentBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                mapplsCategoryResultFragmentBinding9 = null;
            }
            mapplsCategoryResultFragmentBinding9.mapplsCategoryLayoutEmptyErrorState.setVisibility(0);
            MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding10 = this$0.mBinding;
            if (mapplsCategoryResultFragmentBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                mapplsCategoryResultFragmentBinding10 = null;
            }
            mapplsCategoryResultFragmentBinding10.mapplsCategoryProgressLayout.setVisibility(8);
            MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding11 = this$0.mBinding;
            if (mapplsCategoryResultFragmentBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                mapplsCategoryResultFragmentBinding11 = null;
            }
            ((TextView) mapplsCategoryResultFragmentBinding11.mapplsCategoryLayoutEmptyErrorState.findViewById(R.id.mappls_category_text_view_empty_state)).setText(cVar.b());
            MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding12 = this$0.mBinding;
            if (mapplsCategoryResultFragmentBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                mapplsCategoryResultFragmentBinding12 = null;
            }
            ((TextView) mapplsCategoryResultFragmentBinding12.mapplsCategoryLayoutEmptyErrorState.findViewById(R.id.mappls_category_text_view_retry)).setVisibility(0);
            MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding13 = this$0.mBinding;
            if (mapplsCategoryResultFragmentBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                mapplsCategoryResultFragmentBinding13 = null;
            }
            mapplsCategoryResultFragmentBinding13.mapplsCategoryTextViewResults.setVisibility(8);
            com.mappls.sdk.category.adapters.b bVar3 = this$0.poiResultAdapter;
            if (bVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("poiResultAdapter");
            } else {
                bVar = bVar3;
            }
            bVar.a(new ArrayList());
            return;
        } else {
            MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding14 = this$0.mBinding;
            if (mapplsCategoryResultFragmentBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                mapplsCategoryResultFragmentBinding14 = null;
            }
            mapplsCategoryResultFragmentBinding14.mapplsCategoryLayoutEmptyErrorState.setVisibility(8);
            MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding15 = this$0.mBinding;
            if (mapplsCategoryResultFragmentBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                mapplsCategoryResultFragmentBinding15 = null;
            }
            mapplsCategoryResultFragmentBinding15.mapplsCategoryProgressLayout.setVisibility(0);
            MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding16 = this$0.mBinding;
            if (mapplsCategoryResultFragmentBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            } else {
                mapplsCategoryResultFragmentBinding2 = mapplsCategoryResultFragmentBinding16;
            }
            view = mapplsCategoryResultFragmentBinding2.mapplsCategoryTextViewResults;
        }
        view.setVisibility(8);
    }

    @JvmStatic
    @NotNull
    public static final CategoryResultFragment newInstance(@NotNull ArrayList<CategoryCode> arrayList) {
        Companion.getClass();
        return a.a(arrayList);
    }

    @JvmStatic
    @NotNull
    public static final CategoryResultFragment newInstance(@NotNull ArrayList<CategoryCode> arrayList, @NotNull SearchCategoryUIOption searchCategoryUIOption) {
        Companion.getClass();
        return a.a(arrayList, searchCategoryUIOption);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onMapReady$lambda$18(CategoryResultFragment this$0, MapplsMap mapplsMap, Style it) {
        com.mappls.sdk.category.utils.a aVar;
        String path;
        int i;
        com.mappls.sdk.category.utils.a aVar2;
        List split$default;
        String str;
        List split$default2;
        String str2;
        String category;
        Bitmap bitmapFromDrawable;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mapplsMap, "$mapplsMap");
        Intrinsics.checkNotNullParameter(it, "it");
        com.mappls.sdk.category.viewmodel.a aVar3 = this$0.mViewModel;
        com.mappls.sdk.category.viewmodel.a aVar4 = null;
        if (aVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar3 = null;
        }
        List<CategoryCode> f = aVar3.f();
        if (f != null) {
            for (CategoryCode categoryCode : f) {
                if (categoryCode.getMarkerBitmap() != null) {
                    category = categoryCode.getCategory();
                    bitmapFromDrawable = categoryCode.getMarkerBitmap();
                } else {
                    category = categoryCode.getCategory();
                    bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(ContextCompat.getDrawable(this$0.requireContext(), categoryCode.getMarkerIcon()));
                    Intrinsics.checkNotNull(bitmapFromDrawable);
                }
                it.addImage(category, bitmapFromDrawable);
                List<String> categoryCode2 = categoryCode.getCategoryCode();
                Intrinsics.checkNotNullExpressionValue(categoryCode2, "categoryCode.categoryCode");
                for (String category2 : categoryCode2) {
                    Map<String, String> map = this$0.iconMap;
                    Intrinsics.checkNotNullExpressionValue(category2, "category");
                    String category3 = categoryCode.getCategory();
                    Intrinsics.checkNotNullExpressionValue(category3, "categoryCode.category");
                    map.put(category2, category3);
                }
            }
        }
        MapView mapView = this$0.mMapView;
        Intrinsics.checkNotNull(mapView);
        com.mappls.sdk.category.viewmodel.a aVar5 = this$0.mViewModel;
        if (aVar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar5 = null;
        }
        this$0.mMapPlugin = new com.mappls.sdk.category.utils.a(mapView, mapplsMap, it, this$0, aVar5.e());
        com.mappls.sdk.category.viewmodel.a aVar6 = this$0.mViewModel;
        if (aVar6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar6 = null;
        }
        Boolean showRequestOnMap = aVar6.d().showRequestOnMap();
        Intrinsics.checkNotNullExpressionValue(showRequestOnMap, "mViewModel.options.showRequestOnMap()");
        if (showRequestOnMap.booleanValue()) {
            com.mappls.sdk.category.viewmodel.a aVar7 = this$0.mViewModel;
            if (aVar7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                aVar7 = null;
            }
            Integer searchType = aVar7.d().searchType();
            boolean z = false;
            if (searchType == null || searchType.intValue() != 0) {
                com.mappls.sdk.category.viewmodel.a aVar8 = this$0.mViewModel;
                if (aVar8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    aVar8 = null;
                }
                String geometries = aVar8.d().geometries();
                if (geometries != null && m.equals(geometries, "polyline", true)) {
                    z = true;
                }
                if (z) {
                    aVar = this$0.mMapPlugin;
                    if (aVar == null) {
                        return;
                    }
                    com.mappls.sdk.category.viewmodel.a aVar9 = this$0.mViewModel;
                    if (aVar9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    } else {
                        aVar4 = aVar9;
                    }
                    path = aVar4.d().path();
                    Intrinsics.checkNotNull(path);
                    i = 5;
                } else {
                    aVar = this$0.mMapPlugin;
                    if (aVar == null) {
                        return;
                    }
                    com.mappls.sdk.category.viewmodel.a aVar10 = this$0.mViewModel;
                    if (aVar10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    } else {
                        aVar4 = aVar10;
                    }
                    path = aVar4.d().path();
                    Intrinsics.checkNotNull(path);
                    i = 6;
                }
                LineString fromPolyline = LineString.fromPolyline(path, i);
                Intrinsics.checkNotNullExpressionValue(fromPolyline, "fromPolyline(\n          …                        )");
                aVar.a(fromPolyline);
                return;
            }
            com.mappls.sdk.category.viewmodel.a aVar11 = this$0.mViewModel;
            if (aVar11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                aVar11 = null;
            }
            String location = aVar11.d().location();
            if (!(location != null && StringsKt__StringsKt.contains$default((CharSequence) location, (CharSequence) Constants.SEPARATOR_COMMA, false, 2, (Object) null))) {
                com.mappls.sdk.category.viewmodel.a aVar12 = this$0.mViewModel;
                if (aVar12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    aVar12 = null;
                }
                if (aVar12.d().location() == null || (aVar2 = this$0.mMapPlugin) == null) {
                    return;
                }
                com.mappls.sdk.category.viewmodel.a aVar13 = this$0.mViewModel;
                if (aVar13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                } else {
                    aVar4 = aVar13;
                }
                String location2 = aVar4.d().location();
                Intrinsics.checkNotNull(location2);
                aVar2.a(location2);
                return;
            }
            com.mappls.sdk.category.utils.a aVar14 = this$0.mMapPlugin;
            if (aVar14 != null) {
                com.mappls.sdk.category.viewmodel.a aVar15 = this$0.mViewModel;
                if (aVar15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    aVar15 = null;
                }
                String location3 = aVar15.d().location();
                double d = 0.0d;
                double parseDouble = (location3 == null || (split$default2 = StringsKt__StringsKt.split$default((CharSequence) location3, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null)) == null || (str2 = (String) split$default2.get(0)) == null) ? 0.0d : Double.parseDouble(str2);
                com.mappls.sdk.category.viewmodel.a aVar16 = this$0.mViewModel;
                if (aVar16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                } else {
                    aVar4 = aVar16;
                }
                String location4 = aVar4.d().location();
                if (location4 != null && (split$default = StringsKt__StringsKt.split$default((CharSequence) location4, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null)) != null && (str = (String) split$default.get(1)) != null) {
                    d = Double.parseDouble(str);
                }
                aVar14.a(new LatLng(parseDouble, d));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$5(CategoryResultFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding = this$0.mBinding;
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding2 = null;
        if (mapplsCategoryResultFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsCategoryResultFragmentBinding = null;
        }
        mapplsCategoryResultFragmentBinding.mapplsCategoryLayoutEmptyErrorState.setVisibility(8);
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding3 = this$0.mBinding;
        if (mapplsCategoryResultFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        } else {
            mapplsCategoryResultFragmentBinding2 = mapplsCategoryResultFragmentBinding3;
        }
        mapplsCategoryResultFragmentBinding2.mapplsCategoryProgressLayout.setVisibility(0);
        this$0.callPoi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$6(CategoryResultFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ICategoryResultListener iCategoryResultListener = this$0.mCallback;
        if (iCategoryResultListener != null) {
            iCategoryResultListener.onResultCancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7(CategoryResultFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ICategoryResultListener iCategoryResultListener = this$0.mCallback;
        if (iCategoryResultListener != null) {
            iCategoryResultListener.onResultCancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:66:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x014e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void poiAlongRouteObserver$lambda$1(com.mappls.sdk.category.fragment.CategoryResultFragment r8, com.mappls.sdk.category.utils.c r9) {
        /*
            Method dump skipped, instructions count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.category.fragment.CategoryResultFragment.poiAlongRouteObserver$lambda$1(com.mappls.sdk.category.fragment.CategoryResultFragment, com.mappls.sdk.category.utils.c):void");
    }

    private final void setPoiResultData(List<? extends PoiResult> list) {
        TextView textView;
        int i;
        com.mappls.sdk.category.adapters.b bVar = this.poiResultAdapter;
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding = null;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("poiResultAdapter");
            bVar = null;
        }
        bVar.a(list);
        ArrayList arrayList = new ArrayList();
        for (PoiResult poiResult : list) {
            arrayList.add(poiResult);
        }
        ICategoryResultListener iCategoryResultListener = this.mCallback;
        if (iCategoryResultListener != null) {
            iCategoryResultListener.onCategoryResult(arrayList);
        }
        if (!list.isEmpty()) {
            MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding2 = this.mBinding;
            if (mapplsCategoryResultFragmentBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                mapplsCategoryResultFragmentBinding2 = null;
            }
            mapplsCategoryResultFragmentBinding2.mapplsCategoryTextViewResults.setText(list.size() + "  results found");
            MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding3 = this.mBinding;
            if (mapplsCategoryResultFragmentBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            } else {
                mapplsCategoryResultFragmentBinding = mapplsCategoryResultFragmentBinding3;
            }
            textView = mapplsCategoryResultFragmentBinding.mapplsCategoryTextViewResults;
            i = 0;
        } else {
            MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding4 = this.mBinding;
            if (mapplsCategoryResultFragmentBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            } else {
                mapplsCategoryResultFragmentBinding = mapplsCategoryResultFragmentBinding4;
            }
            textView = mapplsCategoryResultFragmentBinding.mapplsCategoryTextViewResults;
            i = 8;
        }
        textView.setVisibility(i);
        com.mappls.sdk.category.utils.a aVar = this.mMapPlugin;
        if (aVar != null) {
            aVar.a(list, this.iconMap);
        }
        showResults(list);
    }

    private final void showResults(List<? extends PoiResult> list) {
        MapplsMap mapplsMap;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (PoiResult poiResult : list) {
            if (poiResult.getLatitude() == null || poiResult.getLongitude() == null) {
                String mapplsPin = poiResult.getMapplsPin();
                Intrinsics.checkNotNullExpressionValue(mapplsPin, "it.mapplsPin");
                arrayList2.add(mapplsPin);
            } else {
                Double latitude = poiResult.getLatitude();
                Intrinsics.checkNotNullExpressionValue(latitude, "it.latitude");
                double doubleValue = latitude.doubleValue();
                Double longitude = poiResult.getLongitude();
                Intrinsics.checkNotNullExpressionValue(longitude, "it.longitude");
                arrayList.add(new LatLng(doubleValue, longitude.doubleValue()));
            }
        }
        if (arrayList.size() > 1) {
            MapplsMap mapplsMap2 = this.mMapplsMap;
            if (mapplsMap2 != null) {
                mapplsMap2.animateCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds.Builder().includes(arrayList).build(), 10, 10, 10, 200));
            }
        } else if (arrayList2.size() > 1 && (mapplsMap = this.mMapplsMap) != null) {
            mapplsMap.animateCamera(CameraMapplsPinUpdateFactory.newMapplsPinBounds(arrayList2, 10, 10, 10, 200));
        }
    }

    private final void storeCategoryNames() {
        com.mappls.sdk.category.viewmodel.a aVar = this.mViewModel;
        if (aVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar = null;
        }
        List<CategoryCode> f = aVar.f();
        if (f != null) {
            for (CategoryCode categoryCode : f) {
                List<String> categoryCode2 = categoryCode.getCategoryCode();
                if (categoryCode2 != null) {
                    Intrinsics.checkNotNullExpressionValue(categoryCode2, "categoryCode");
                    for (String it : categoryCode2) {
                        com.mappls.sdk.category.viewmodel.a aVar2 = this.mViewModel;
                        if (aVar2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                            aVar2 = null;
                        }
                        LinkedHashMap b2 = aVar2.b();
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        String category = categoryCode.getCategory();
                        Intrinsics.checkNotNullExpressionValue(category, "categoryCode.category");
                        b2.put(it, category);
                    }
                }
            }
        }
    }

    private final void subscribeViewModel() {
        com.mappls.sdk.category.viewmodel.a aVar = this.mViewModel;
        com.mappls.sdk.category.viewmodel.a aVar2 = null;
        if (aVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar = null;
        }
        aVar.a().observe(getViewLifecycleOwner(), this.poiAlongRouteObserver);
        com.mappls.sdk.category.viewmodel.a aVar3 = this.mViewModel;
        if (aVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            aVar2 = aVar3;
        }
        aVar2.c().observe(getViewLifecycleOwner(), this.nearbyObserver);
    }

    @Override // com.mappls.sdk.plugin.annotation.OnAnnotationClickListener
    public boolean onAnnotationClick(@Nullable Symbol symbol) {
        JsonElement data;
        boolean z = true;
        if (symbol == null || (data = symbol.getData()) == null || !data.isJsonObject()) {
            z = false;
        }
        if (z) {
            JsonElement data2 = symbol.getData();
            Intrinsics.checkNotNull(data2, "null cannot be cast to non-null type com.google.gson.JsonObject");
            JsonObject jsonObject = (JsonObject) data2;
            ICategoryResultListener iCategoryResultListener = this.mCallback;
            if (iCategoryResultListener != null) {
                Object fromJson = new Gson().fromJson(jsonObject.get("com.mappls.sdk.category.plugin.NEARBY_RESULT_DATA").getAsString(), (Class<Object>) PoiResult.class);
                Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(\n       …ss.java\n                )");
                iCategoryResultListener.onCategorySelectedResult((PoiResult) fromJson);
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        MapplsCategoryResultFragmentBinding inflate = MapplsCategoryResultFragmentBinding.inflate(inflater, viewGroup, false);
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
        super.onDestroyView();
        com.mappls.sdk.category.utils.a aVar = this.mMapPlugin;
        if (aVar != null) {
            aVar.a();
        }
        com.mappls.sdk.category.utils.a aVar2 = this.mMapPlugin;
        if (aVar2 != null) {
            aVar2.b();
        }
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapError(int i, @Nullable String str) {
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapReady(@NotNull final MapplsMap mapplsMap) {
        Intrinsics.checkNotNullParameter(mapplsMap, "mapplsMap");
        this.mMapplsMap = mapplsMap;
        UiSettings uiSettings = mapplsMap.getUiSettings();
        if (uiSettings != null) {
            uiSettings.setLogoMargins(0, 0, 0, TypedValues.CycleType.TYPE_EASING);
        }
        callPoi();
        initialiseMapCamera();
        mapplsMap.getStyle(new Style.OnStyleLoaded() { // from class: com.mappls.sdk.category.fragment.f
            @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
            public final void onStyleLoaded(Style style) {
                CategoryResultFragment.onMapReady$lambda$18(CategoryResultFragment.this, mapplsMap, style);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Integer valueOf;
        CategoryCode categoryCode;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.mViewModel = (com.mappls.sdk.category.viewmodel.a) new ViewModelProvider(this).get(com.mappls.sdk.category.viewmodel.a.class);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new b());
        Bundle arguments = getArguments();
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding = null;
        if (arguments != null) {
            com.mappls.sdk.category.viewmodel.a aVar = this.mViewModel;
            if (aVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                aVar = null;
            }
            SearchCategoryUIOption searchCategoryUIOption = (SearchCategoryUIOption) arguments.getParcelable("com.mappls.sdk.category.CATEGORY_UI_OPTION");
            if (searchCategoryUIOption == null) {
                searchCategoryUIOption = com.mappls.sdk.category.a.d();
            }
            aVar.a(searchCategoryUIOption);
            com.mappls.sdk.category.viewmodel.a aVar2 = this.mViewModel;
            if (aVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                aVar2 = null;
            }
            aVar2.a(arguments.getParcelableArrayList("com.mappls.sdk.category.SELECTED_CATEGORY_CODES"));
            arguments.clear();
        }
        subscribeViewModel();
        StringBuilder sb = new StringBuilder();
        com.mappls.sdk.category.viewmodel.a aVar3 = this.mViewModel;
        if (aVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar3 = null;
        }
        List<CategoryCode> f = aVar3.f();
        Integer valueOf2 = f != null ? Integer.valueOf(f.size()) : null;
        Intrinsics.checkNotNull(valueOf2);
        int intValue = valueOf2.intValue();
        for (int i = 0; i < intValue; i++) {
            com.mappls.sdk.category.viewmodel.a aVar4 = this.mViewModel;
            if (aVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                aVar4 = null;
            }
            List<CategoryCode> f2 = aVar4.f();
            sb.append(String.valueOf((f2 == null || (categoryCode = f2.get(i)) == null) ? null : categoryCode.getCategory()));
            com.mappls.sdk.category.viewmodel.a aVar5 = this.mViewModel;
            if (aVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                aVar5 = null;
            }
            List<CategoryCode> f3 = aVar5.f();
            Intrinsics.checkNotNull(f3 != null ? Integer.valueOf(f3.size()) : null);
            if (i != valueOf.intValue() - 1) {
                sb.append(Constants.SEPARATOR_COMMA);
            }
        }
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding2 = this.mBinding;
        if (mapplsCategoryResultFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsCategoryResultFragmentBinding2 = null;
        }
        mapplsCategoryResultFragmentBinding2.mapplsCategoryResultSearchInput.setText(sb);
        MapView mapView = this.mMapView;
        if (mapView != null) {
            mapView.getMapAsync(this);
        }
        ArrayList arrayList = new ArrayList();
        com.mappls.sdk.category.viewmodel.a aVar6 = this.mViewModel;
        if (aVar6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar6 = null;
        }
        this.poiResultAdapter = new com.mappls.sdk.category.adapters.b(arrayList, aVar6.e(), new c());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding3 = this.mBinding;
        if (mapplsCategoryResultFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsCategoryResultFragmentBinding3 = null;
        }
        mapplsCategoryResultFragmentBinding3.mapplsCategoryPoiList.setLayoutManager(linearLayoutManager);
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding4 = this.mBinding;
        if (mapplsCategoryResultFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsCategoryResultFragmentBinding4 = null;
        }
        RecyclerView recyclerView = mapplsCategoryResultFragmentBinding4.mapplsCategoryPoiList;
        com.mappls.sdk.category.adapters.b bVar = this.poiResultAdapter;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("poiResultAdapter");
            bVar = null;
        }
        recyclerView.setAdapter(bVar);
        Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.mappls_search_bottom_sheet_bg);
        if (drawable != null) {
            com.mappls.sdk.category.viewmodel.a aVar7 = this.mViewModel;
            if (aVar7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                aVar7 = null;
            }
            Integer backgroundColor = aVar7.e().backgroundColor();
            Intrinsics.checkNotNullExpressionValue(backgroundColor, "mViewModel.searchUiOption.backgroundColor()");
            drawable.setColorFilter(new PorterDuffColorFilter(backgroundColor.intValue(), PorterDuff.Mode.SRC_IN));
        }
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding5 = this.mBinding;
        if (mapplsCategoryResultFragmentBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsCategoryResultFragmentBinding5 = null;
        }
        mapplsCategoryResultFragmentBinding5.mapplsCategoryPoiListBottomSheetContainer.setBackground(drawable);
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding6 = this.mBinding;
        if (mapplsCategoryResultFragmentBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsCategoryResultFragmentBinding6 = null;
        }
        TextView textView = (TextView) mapplsCategoryResultFragmentBinding6.mapplsCategoryProgressLayout.findViewById(R.id.mappls_category_description_text);
        com.mappls.sdk.category.viewmodel.a aVar8 = this.mViewModel;
        if (aVar8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar8 = null;
        }
        Integer resultMessageTextColor = aVar8.e().resultMessageTextColor();
        Intrinsics.checkNotNullExpressionValue(resultMessageTextColor, "mViewModel.searchUiOption.resultMessageTextColor()");
        textView.setTextColor(resultMessageTextColor.intValue());
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding7 = this.mBinding;
        if (mapplsCategoryResultFragmentBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsCategoryResultFragmentBinding7 = null;
        }
        TextView textView2 = mapplsCategoryResultFragmentBinding7.mapplsCategoryTextViewResults;
        com.mappls.sdk.category.viewmodel.a aVar9 = this.mViewModel;
        if (aVar9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar9 = null;
        }
        Integer resultCountTextColor = aVar9.e().resultCountTextColor();
        Intrinsics.checkNotNullExpressionValue(resultCountTextColor, "mViewModel.searchUiOption.resultCountTextColor()");
        textView2.setTextColor(resultCountTextColor.intValue());
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding8 = this.mBinding;
        if (mapplsCategoryResultFragmentBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsCategoryResultFragmentBinding8 = null;
        }
        TextView textView3 = (TextView) mapplsCategoryResultFragmentBinding8.mapplsCategoryLayoutEmptyErrorState.findViewById(R.id.mappls_category_text_view_empty_state);
        com.mappls.sdk.category.viewmodel.a aVar10 = this.mViewModel;
        if (aVar10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar10 = null;
        }
        Integer resultMessageTextColor2 = aVar10.e().resultMessageTextColor();
        Intrinsics.checkNotNullExpressionValue(resultMessageTextColor2, "mViewModel.searchUiOption.resultMessageTextColor()");
        textView3.setTextColor(resultMessageTextColor2.intValue());
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding9 = this.mBinding;
        if (mapplsCategoryResultFragmentBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsCategoryResultFragmentBinding9 = null;
        }
        ((TextView) mapplsCategoryResultFragmentBinding9.mapplsCategoryLayoutEmptyErrorState.findViewById(R.id.mappls_category_text_view_retry)).setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.category.fragment.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CategoryResultFragment.onViewCreated$lambda$5(CategoryResultFragment.this, view2);
            }
        });
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding10 = this.mBinding;
        if (mapplsCategoryResultFragmentBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsCategoryResultFragmentBinding10 = null;
        }
        AppBarLayout appBarLayout = mapplsCategoryResultFragmentBinding10.resultAppbar;
        com.mappls.sdk.category.viewmodel.a aVar11 = this.mViewModel;
        if (aVar11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            aVar11 = null;
        }
        Integer backgroundColor2 = aVar11.e().backgroundColor();
        Intrinsics.checkNotNullExpressionValue(backgroundColor2, "mViewModel.searchUiOption.backgroundColor()");
        appBarLayout.setBackgroundColor(backgroundColor2.intValue());
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding11 = this.mBinding;
        if (mapplsCategoryResultFragmentBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            mapplsCategoryResultFragmentBinding11 = null;
        }
        mapplsCategoryResultFragmentBinding11.mapplsCategoryResultBackIcon.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.category.fragment.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CategoryResultFragment.onViewCreated$lambda$6(CategoryResultFragment.this, view2);
            }
        });
        MapplsCategoryResultFragmentBinding mapplsCategoryResultFragmentBinding12 = this.mBinding;
        if (mapplsCategoryResultFragmentBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        } else {
            mapplsCategoryResultFragmentBinding = mapplsCategoryResultFragmentBinding12;
        }
        mapplsCategoryResultFragmentBinding.mapplsCategorySearchClearBtn.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.category.fragment.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CategoryResultFragment.onViewCreated$lambda$7(CategoryResultFragment.this, view2);
            }
        });
        storeCategoryNames();
    }

    public final void setCategoryResultListener(@NotNull ICategoryResultListener callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.mCallback = callback;
    }

    public final void setMapView(@NotNull MapView mapView) {
        MapView mapView2;
        Intrinsics.checkNotNullParameter(mapView, "mapView");
        this.mMapView = mapView;
        if (!isAdded() || (mapView2 = this.mMapView) == null) {
            return;
        }
        mapView2.getMapAsync(this);
    }
}
