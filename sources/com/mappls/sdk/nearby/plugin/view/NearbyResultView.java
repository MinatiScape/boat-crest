package com.mappls.sdk.nearby.plugin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.AttrRes;
import androidx.annotation.Keep;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import com.clevertap.android.sdk.Constants;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.Polygon;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.MapplsMapOptions;
import com.mappls.sdk.maps.OnMapReadyCallback;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.UiSettings;
import com.mappls.sdk.maps.camera.CameraMapplsPinUpdateFactory;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.maps.location.LocationComponent;
import com.mappls.sdk.maps.location.LocationComponentActivationOptions;
import com.mappls.sdk.maps.location.LocationComponentOptions;
import com.mappls.sdk.maps.location.engine.LocationEngine;
import com.mappls.sdk.maps.location.engine.LocationEngineCallback;
import com.mappls.sdk.maps.location.engine.LocationEngineRequest;
import com.mappls.sdk.maps.location.engine.LocationEngineResult;
import com.mappls.sdk.maps.utils.BitmapUtils;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import com.mappls.sdk.nearby.plugin.MapplsNearbyWidget;
import com.mappls.sdk.nearby.plugin.R;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyResultViewBinding;
import com.mappls.sdk.nearby.plugin.fragment.NearbyResultCallback;
import com.mappls.sdk.nearby.plugin.model.MapplsNearbyResponse;
import com.mappls.sdk.nearby.plugin.model.NearbyUIOption;
import com.mappls.sdk.plugin.annotation.OnSymbolClickListener;
import com.mappls.sdk.plugin.annotation.Symbol;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResult;
import com.mappls.sdk.services.api.nearby.model.PageInfo;
import com.mappls.sdk.turf.TurfConstants;
import com.mappls.sdk.turf.TurfTransformation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
@SourceDebugExtension({"SMAP\nNearbyResultView.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NearbyResultView.kt\ncom/mappls/sdk/nearby/plugin/view/NearbyResultView\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,487:1\n1855#2:488\n1855#2,2:489\n1856#2:491\n*S KotlinDebug\n*F\n+ 1 NearbyResultView.kt\ncom/mappls/sdk/nearby/plugin/view/NearbyResultView\n*L\n344#1:488\n353#1:489,2\n344#1:491\n*E\n"})
/* loaded from: classes10.dex */
public final class NearbyResultView extends RelativeLayout implements OnMapReadyCallback, LocationEngineCallback<LocationEngineResult>, LifecycleOwner, OnSymbolClickListener, NearbyResultCallback {
    @NotNull
    private final Map<String, String> iconMap;
    private boolean isMapInitialised;
    private boolean isZoomOnCurrentLocation;
    @Nullable
    private LifecycleRegistry lifecycleRegistry;
    @Nullable
    private LocationComponent locationComponent;
    @Nullable
    private LocationEngine locationEngine;
    @Nullable
    private com.mappls.sdk.nearby.plugin.adapter.c mAdapter;
    @NotNull
    private final MapplsNearbyResultViewBinding mBinding;
    @Nullable
    private e mCallback;
    @Nullable
    private Context mContext;
    private com.mappls.sdk.nearby.plugin.viewmodel.b mViewModel;
    @NotNull
    private NearbyResultViewOption mViewOption;
    @Nullable
    private MapView mapView;
    @Nullable
    private MapplsMap mapplsMap;
    @Nullable
    private com.mappls.sdk.nearby.plugin.util.b nearbyMapPlugin;
    @NotNull
    private final Observer<com.mappls.sdk.nearby.plugin.util.d<NearbyAtlasResponse>> nearbyObserver;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NearbyResultView(@NotNull Context context) {
        this(context, (AttributeSet) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NearbyResultView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NearbyResultView(@NotNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        MapplsNearbyResultViewBinding inflate = MapplsNearbyResultViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(context), this, true)");
        this.mBinding = inflate;
        this.iconMap = new HashMap();
        this.nearbyObserver = new Observer() { // from class: com.mappls.sdk.nearby.plugin.view.l
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NearbyResultView.nearbyObserver$lambda$0(NearbyResultView.this, (com.mappls.sdk.nearby.plugin.util.d) obj);
            }
        };
        NearbyResultViewOption createFromAttributes = NearbyResultViewOption.createFromAttributes(context, attributeSet);
        Intrinsics.checkNotNullExpressionValue(createFromAttributes, "createFromAttributes(context, attrs)");
        this.mViewOption = createFromAttributes;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NearbyResultView(@NotNull Context context, @NotNull NearbyResultViewOption nearbyViewOption) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(nearbyViewOption, "nearbyViewOption");
        MapplsNearbyResultViewBinding inflate = MapplsNearbyResultViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(context), this, true)");
        this.mBinding = inflate;
        this.iconMap = new HashMap();
        this.nearbyObserver = new Observer() { // from class: com.mappls.sdk.nearby.plugin.view.l
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NearbyResultView.nearbyObserver$lambda$0(NearbyResultView.this, (com.mappls.sdk.nearby.plugin.util.d) obj);
            }
        };
        this.mViewOption = nearbyViewOption;
    }

    private final void enableLocation(Style style) {
        LocationComponentOptions build = LocationComponentOptions.builder(getContext()).trackingGesturesManagement(true).build();
        Intrinsics.checkNotNullExpressionValue(build, "builder(context)\n       …\n                .build()");
        MapplsMap mapplsMap = this.mapplsMap;
        LocationComponent locationComponent = mapplsMap != null ? mapplsMap.getLocationComponent() : null;
        Intrinsics.checkNotNull(locationComponent);
        this.locationComponent = locationComponent;
        LocationComponentActivationOptions build2 = LocationComponentActivationOptions.builder(getContext(), style).locationComponentOptions(build).build();
        LocationComponent locationComponent2 = this.locationComponent;
        if (locationComponent2 != null) {
            locationComponent2.activateLocationComponent(build2);
        }
        if (ContextCompat.checkSelfPermission(getContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(getContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            LocationComponent locationComponent3 = this.locationComponent;
            if (locationComponent3 != null) {
                locationComponent3.setLocationComponentEnabled(true);
            }
            LocationComponent locationComponent4 = this.locationComponent;
            LocationEngine locationEngine = locationComponent4 != null ? locationComponent4.getLocationEngine() : null;
            Intrinsics.checkNotNull(locationEngine);
            this.locationEngine = locationEngine;
            LocationEngineRequest build3 = new LocationEngineRequest.Builder(1000L).setPriority(0).build();
            LocationEngine locationEngine2 = this.locationEngine;
            if (locationEngine2 != null) {
                locationEngine2.requestLocationUpdates(build3, this, Looper.getMainLooper());
            }
            LocationComponent locationComponent5 = this.locationComponent;
            if (locationComponent5 == null) {
                return;
            }
            locationComponent5.setCameraMode(8);
        }
    }

    private final void initialiseMap() {
        com.mappls.sdk.nearby.plugin.util.b bVar;
        MapplsMap mapplsMap = this.mapplsMap;
        if (mapplsMap != null) {
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar2 = this.mViewModel;
            if (bVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar2 = null;
            }
            if (bVar2.f() != null) {
                com.mappls.sdk.nearby.plugin.util.b bVar3 = this.nearbyMapPlugin;
                if (bVar3 != null) {
                    com.mappls.sdk.nearby.plugin.viewmodel.b bVar4 = this.mViewModel;
                    if (bVar4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                        bVar4 = null;
                    }
                    String f = bVar4.f();
                    Intrinsics.checkNotNull(f);
                    bVar3.a(f);
                }
                com.mappls.sdk.nearby.plugin.viewmodel.b bVar5 = this.mViewModel;
                if (bVar5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    bVar5 = null;
                }
                String f2 = bVar5.f();
                Intrinsics.checkNotNull(f2);
                mapplsMap.animateCamera(CameraMapplsPinUpdateFactory.newMapplsPinZoom(f2, 14.0d));
            } else {
                com.mappls.sdk.nearby.plugin.viewmodel.b bVar6 = this.mViewModel;
                if (bVar6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    bVar6 = null;
                }
                if (bVar6.e() != null) {
                    com.mappls.sdk.nearby.plugin.util.b bVar7 = this.nearbyMapPlugin;
                    if (bVar7 != null) {
                        com.mappls.sdk.nearby.plugin.viewmodel.b bVar8 = this.mViewModel;
                        if (bVar8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                            bVar8 = null;
                        }
                        LatLng e = bVar8.e();
                        Intrinsics.checkNotNull(e);
                        bVar7.a(e);
                    }
                    com.mappls.sdk.nearby.plugin.viewmodel.b bVar9 = this.mViewModel;
                    if (bVar9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                        bVar9 = null;
                    }
                    LatLng e2 = bVar9.e();
                    Intrinsics.checkNotNull(e2);
                    double longitude = e2.getLongitude();
                    com.mappls.sdk.nearby.plugin.viewmodel.b bVar10 = this.mViewModel;
                    if (bVar10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                        bVar10 = null;
                    }
                    LatLng e3 = bVar10.e();
                    Intrinsics.checkNotNull(e3);
                    Point fromLngLat = Point.fromLngLat(longitude, e3.getLatitude());
                    MapplsNearbyWidget mapplsNearbyWidget = MapplsNearbyWidget.INSTANCE;
                    Polygon circle = TurfTransformation.circle(fromLngLat, mapplsNearbyWidget.getNearbyOption().radius().intValue(), TurfConstants.UNIT_METRES);
                    Intrinsics.checkNotNullExpressionValue(circle, "circle(Point.fromLngLat(…urfConstants.UNIT_METRES)");
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    LineString outer = circle.outer();
                    List<Point> coordinates = outer != null ? outer.coordinates() : null;
                    Intrinsics.checkNotNull(coordinates);
                    LatLngBounds build = builder.includes(com.mappls.sdk.nearby.plugin.util.c.a(coordinates)).build();
                    mapplsMap.animateCamera(getContext().getResources().getConfiguration().orientation == 2 ? CameraUpdateFactory.newLatLngBounds(build, 100, 400, 100, 150) : CameraUpdateFactory.newLatLngBounds(build, 40, 40, 40, 40));
                    com.mappls.sdk.nearby.plugin.util.b bVar11 = this.nearbyMapPlugin;
                    if (bVar11 != null) {
                        com.mappls.sdk.nearby.plugin.viewmodel.b bVar12 = this.mViewModel;
                        if (bVar12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                            bVar12 = null;
                        }
                        LatLng e4 = bVar12.e();
                        Intrinsics.checkNotNull(e4);
                        bVar11.a(e4, mapplsNearbyWidget.getNearbyOption().radius().intValue());
                    }
                }
            }
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar13 = this.mViewModel;
            if (bVar13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar13 = null;
            }
            if (bVar13.g() != null) {
                com.mappls.sdk.nearby.plugin.viewmodel.b bVar14 = this.mViewModel;
                if (bVar14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    bVar14 = null;
                }
                ArrayList<CategoryCode> g = bVar14.g();
                if (g != null) {
                    for (final CategoryCode categoryCode : g) {
                        mapplsMap.getStyle(new Style.OnStyleLoaded() { // from class: com.mappls.sdk.nearby.plugin.view.n
                            @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
                            public final void onStyleLoaded(Style style) {
                                NearbyResultView.initialiseMap$lambda$11$lambda$10$lambda$8(CategoryCode.this, this, style);
                            }
                        });
                        List<String> categoryCode2 = categoryCode.getCategoryCode();
                        Intrinsics.checkNotNullExpressionValue(categoryCode2, "it.categoryCode");
                        for (String category : categoryCode2) {
                            Map<String, String> map = this.iconMap;
                            Intrinsics.checkNotNullExpressionValue(category, "category");
                            String category2 = categoryCode.getCategory();
                            Intrinsics.checkNotNullExpressionValue(category2, "it.category");
                            map.put(category, category2);
                        }
                    }
                }
            }
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar15 = this.mViewModel;
            if (bVar15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar15 = null;
            }
            NearbyAtlasResponse b = bVar15.b();
            if ((b != null ? b.getSuggestedLocations() : null) == null || (bVar = this.nearbyMapPlugin) == null) {
                return;
            }
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar16 = this.mViewModel;
            if (bVar16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar16 = null;
            }
            NearbyAtlasResponse b2 = bVar16.b();
            ArrayList<NearbyAtlasResult> suggestedLocations = b2 != null ? b2.getSuggestedLocations() : null;
            Intrinsics.checkNotNull(suggestedLocations);
            bVar.a(suggestedLocations, this.iconMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initialiseMap$lambda$11$lambda$10$lambda$8(CategoryCode it, NearbyResultView this$0, Style style) {
        Intrinsics.checkNotNullParameter(it, "$it");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(style, "style");
        if (it.getMarkerBitmap() != null) {
            style.addImage(it.getCategory(), it.getMarkerBitmap());
            return;
        }
        String category = it.getCategory();
        Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(ContextCompat.getDrawable(this$0.getContext(), it.getMarkerIcon()));
        Intrinsics.checkNotNull(bitmapFromDrawable);
        style.addImage(category, bitmapFromDrawable);
    }

    private final void initialiseView() {
        ConstraintLayout constraintLayout = this.mBinding.mapplsNearbyResultPage;
        Integer paginationBackgroundColor = this.mViewOption.paginationBackgroundColor();
        Intrinsics.checkNotNullExpressionValue(paginationBackgroundColor, "mViewOption.paginationBackgroundColor()");
        constraintLayout.setBackgroundColor(paginationBackgroundColor.intValue());
        ConstraintLayout constraintLayout2 = this.mBinding.mapplsNearbyResultToolbar;
        Integer num = this.mViewOption.toolbarBackgroundColor();
        Intrinsics.checkNotNullExpressionValue(num, "mViewOption.toolbarBackgroundColor()");
        constraintLayout2.setBackgroundColor(num.intValue());
        TabLayout tabLayout = this.mBinding.mapplsNearbyTabLayout;
        Integer tabBackgroundColor = this.mViewOption.tabBackgroundColor();
        Intrinsics.checkNotNullExpressionValue(tabBackgroundColor, "mViewOption.tabBackgroundColor()");
        tabLayout.setBackgroundColor(tabBackgroundColor.intValue());
        ImageView imageView = this.mBinding.mapplsNearbyToolbarBackButton;
        Integer num2 = this.mViewOption.toolbarTintColor();
        Intrinsics.checkNotNullExpressionValue(num2, "mViewOption.toolbarTintColor()");
        imageView.setColorFilter(num2.intValue());
        TextView textView = this.mBinding.mapplsNearbyResultToolbarTextView;
        Integer num3 = this.mViewOption.toolbarTintColor();
        Intrinsics.checkNotNullExpressionValue(num3, "mViewOption.toolbarTintColor()");
        textView.setTextColor(num3.intValue());
        this.mBinding.mapplsNearbyResultToolbarTextView.setText(this.mViewOption.toolbarText());
        ConstraintLayout constraintLayout3 = this.mBinding.mapplsNearbyResultCategoryBg;
        Integer categoryFilterBackgroundColor = this.mViewOption.categoryFilterBackgroundColor();
        Intrinsics.checkNotNullExpressionValue(categoryFilterBackgroundColor, "mViewOption.categoryFilterBackgroundColor()");
        constraintLayout3.setBackgroundColor(categoryFilterBackgroundColor.intValue());
        TabLayout tabLayout2 = this.mBinding.mapplsNearbyTabLayout;
        Integer tabTextColor = this.mViewOption.tabTextColor();
        Intrinsics.checkNotNullExpressionValue(tabTextColor, "mViewOption.tabTextColor()");
        int intValue = tabTextColor.intValue();
        Integer tabSelectedTextColor = this.mViewOption.tabSelectedTextColor();
        Intrinsics.checkNotNullExpressionValue(tabSelectedTextColor, "mViewOption.tabSelectedTextColor()");
        tabLayout2.setTabTextColors(intValue, tabSelectedTextColor.intValue());
        TabLayout tabLayout3 = this.mBinding.mapplsNearbyTabLayout;
        Integer tabIndicatorColor = this.mViewOption.tabIndicatorColor();
        Intrinsics.checkNotNullExpressionValue(tabIndicatorColor, "mViewOption.tabIndicatorColor()");
        tabLayout3.setSelectedTabIndicatorColor(tabIndicatorColor.intValue());
        TabLayout tabLayout4 = this.mBinding.mapplsNearbyTabLayout;
        Integer tabIconTint = this.mViewOption.tabIconTint();
        Intrinsics.checkNotNullExpressionValue(tabIconTint, "mViewOption.tabIconTint()");
        tabLayout4.setTabIconTintResource(tabIconTint.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void nearbyObserver$lambda$0(NearbyResultView this$0, com.mappls.sdk.nearby.plugin.util.d dVar) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int ordinal = dVar.c().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                this$0.mBinding.mapplsNearbyResultProgressBar.setVisibility(0);
                return;
            } else if (ordinal != 2) {
                return;
            } else {
                this$0.mBinding.mapplsNearbyResultProgressBar.setVisibility(8);
                Toast.makeText(this$0.getContext(), dVar.b(), 0).show();
                return;
            }
        }
        if (dVar.a() != null) {
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this$0.mViewModel;
            if (bVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar = null;
            }
            bVar.a((NearbyAtlasResponse) dVar.a());
            this$0.updateNearbyData();
        } else {
            Toast.makeText(this$0.getContext(), "No result found", 0).show();
        }
        this$0.mBinding.mapplsNearbyResultProgressBar.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(NearbyResultView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        e eVar = this$0.mCallback;
        if (eVar != null) {
            eVar.onCancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(NearbyResultView this$0, TabLayout.Tab tab, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(tab, "tab");
        tab.setText(i == 0 ? "Map" : "List");
        tab.setIcon(ContextCompat.getDrawable(this$0.getContext(), i == 0 ? R.drawable.mappls_nearby_map_icon : R.drawable.mappls_nearby_result_list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(NearbyResultView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this$0.mViewModel;
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar2 = null;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        if (bVar.a() != 1) {
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar3 = this$0.mViewModel;
            if (bVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar3 = null;
            }
            bVar3.a(bVar3.a() - 1);
            this$0.updatePage();
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar4 = this$0.mViewModel;
            if (bVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar4 = null;
            }
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar5 = this$0.mViewModel;
            if (bVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar5 = null;
            }
            ArrayList<CategoryCode> g = bVar5.g();
            Intrinsics.checkNotNull(g);
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar6 = this$0.mViewModel;
            if (bVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                bVar2 = bVar6;
            }
            bVar4.a(g, bVar2.a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5(NearbyResultView this$0, View view) {
        PageInfo pageInfo;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this$0.mViewModel;
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar2 = null;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        NearbyAtlasResponse b = bVar.b();
        Integer num = (b == null || (pageInfo = b.getPageInfo()) == null) ? null : pageInfo.totalPages;
        int intValue = num == null ? 0 : num.intValue();
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar3 = this$0.mViewModel;
        if (bVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar3 = null;
        }
        if (intValue > bVar3.a()) {
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar4 = this$0.mViewModel;
            if (bVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar4 = null;
            }
            bVar4.a(bVar4.a() + 1);
            this$0.updatePage();
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar5 = this$0.mViewModel;
            if (bVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar5 = null;
            }
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar6 = this$0.mViewModel;
            if (bVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar6 = null;
            }
            ArrayList<CategoryCode> g = bVar6.g();
            Intrinsics.checkNotNull(g);
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar7 = this$0.mViewModel;
            if (bVar7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                bVar2 = bVar7;
            }
            bVar5.a(g, bVar2.a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6(NearbyResultView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this$0.mViewModel;
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar2 = null;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        int a2 = bVar.a();
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar3 = this$0.mViewModel;
        if (bVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar3 = null;
        }
        bVar3.a(Integer.parseInt(this$0.mBinding.mapplsNearbyPageCurrentTv.getText().toString()));
        this$0.updatePage();
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar4 = this$0.mViewModel;
        if (bVar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar4 = null;
        }
        if (a2 != bVar4.a()) {
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar5 = this$0.mViewModel;
            if (bVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar5 = null;
            }
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar6 = this$0.mViewModel;
            if (bVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar6 = null;
            }
            ArrayList<CategoryCode> g = bVar6.g();
            Intrinsics.checkNotNull(g);
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar7 = this$0.mViewModel;
            if (bVar7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                bVar2 = bVar7;
            }
            bVar5.a(g, bVar2.a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$7(NearbyResultView this$0, View view) {
        PageInfo pageInfo;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this$0.mViewModel;
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar2 = null;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        NearbyAtlasResponse b = bVar.b();
        Integer num = (b == null || (pageInfo = b.getPageInfo()) == null) ? null : pageInfo.totalPages;
        int intValue = num == null ? 0 : num.intValue();
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar3 = this$0.mViewModel;
        if (bVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar3 = null;
        }
        if (intValue > bVar3.a()) {
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar4 = this$0.mViewModel;
            if (bVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar4 = null;
            }
            int a2 = bVar4.a();
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar5 = this$0.mViewModel;
            if (bVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar5 = null;
            }
            bVar5.a(Integer.parseInt(this$0.mBinding.mapplsNearbyPageNextTv.getText().toString()));
            this$0.updatePage();
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar6 = this$0.mViewModel;
            if (bVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar6 = null;
            }
            if (a2 != bVar6.a()) {
                com.mappls.sdk.nearby.plugin.viewmodel.b bVar7 = this$0.mViewModel;
                if (bVar7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    bVar7 = null;
                }
                com.mappls.sdk.nearby.plugin.viewmodel.b bVar8 = this$0.mViewModel;
                if (bVar8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    bVar8 = null;
                }
                ArrayList<CategoryCode> g = bVar8.g();
                Intrinsics.checkNotNull(g);
                com.mappls.sdk.nearby.plugin.viewmodel.b bVar9 = this$0.mViewModel;
                if (bVar9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                } else {
                    bVar2 = bVar9;
                }
                bVar7.a(g, bVar2.a());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onMapReady$lambda$13$lambda$12(NearbyResultView this$0, MapplsMap this_apply, Style it) {
        Bitmap bitmapFromDrawable;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.enableLocation(it);
        if (this$0.mViewOption.locationMarkerBitmap() != null) {
            bitmapFromDrawable = this$0.mViewOption.locationMarkerBitmap();
        } else {
            Context context = this$0.getContext();
            Integer locationMarkerIcon = this$0.mViewOption.locationMarkerIcon();
            Intrinsics.checkNotNullExpressionValue(locationMarkerIcon, "mViewOption.locationMarkerIcon()");
            bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(ContextCompat.getDrawable(context, locationMarkerIcon.intValue()));
        }
        Intrinsics.checkNotNull(bitmapFromDrawable);
        it.addImage("com.mappls.sdk.nearby.plugin.NEARBY_REF_LOCATION_MARKER_ICON", bitmapFromDrawable);
        MapView mapView = this$0.mapView;
        Intrinsics.checkNotNull(mapView);
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this$0.mViewModel;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        NearbyUIOption d = bVar.d();
        if (d == null) {
            d = NearbyUIOption.builder().build();
        }
        NearbyUIOption nearbyUIOption = d;
        Intrinsics.checkNotNullExpressionValue(nearbyUIOption, "mViewModel.nearbyUIOptio…IOption.builder().build()");
        this$0.nearbyMapPlugin = new com.mappls.sdk.nearby.plugin.util.b(mapView, this_apply, it, this$0, nearbyUIOption);
        this$0.initialiseMap();
    }

    private final void subscribeViewModel() {
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this.mViewModel;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        bVar.c().observe(this, this.nearbyObserver);
    }

    private final void updateNearbyData() {
        com.mappls.sdk.nearby.plugin.util.b bVar;
        if (this.mapplsMap != null) {
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar2 = this.mViewModel;
            if (bVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar2 = null;
            }
            NearbyAtlasResponse b = bVar2.b();
            if ((b != null ? b.getSuggestedLocations() : null) != null && (bVar = this.nearbyMapPlugin) != null) {
                com.mappls.sdk.nearby.plugin.viewmodel.b bVar3 = this.mViewModel;
                if (bVar3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    bVar3 = null;
                }
                NearbyAtlasResponse b2 = bVar3.b();
                ArrayList<NearbyAtlasResult> suggestedLocations = b2 != null ? b2.getSuggestedLocations() : null;
                Intrinsics.checkNotNull(suggestedLocations);
                bVar.a(suggestedLocations, this.iconMap);
            }
        }
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        Fragment findFragmentByTag = ((FragmentActivity) context).getSupportFragmentManager().findFragmentByTag("f1");
        if (findFragmentByTag != null) {
            ((com.mappls.sdk.nearby.plugin.fragment.b) findFragmentByTag).a();
        }
    }

    private final void updatePage() {
        TextView textView;
        int a2;
        PageInfo pageInfo;
        PageInfo pageInfo2;
        Integer totalPages;
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this.mViewModel;
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar2 = null;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        if (bVar.a() % 2 == 1) {
            this.mBinding.mapplsNearbyPageCurrent.setCardBackgroundColor(Color.parseColor("#3A78E7"));
            this.mBinding.mapplsNearbyPageNext.setCardBackgroundColor(Color.parseColor(Constants.WHITE));
            this.mBinding.mapplsNearbyPageCurrentTv.setTextColor(Color.parseColor(Constants.WHITE));
            this.mBinding.mapplsNearbyPageNextTv.setTextColor(Color.parseColor("#212121"));
            TextView textView2 = this.mBinding.mapplsNearbyPageCurrentTv;
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar3 = this.mViewModel;
            if (bVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar3 = null;
            }
            textView2.setText(String.valueOf(bVar3.a()));
            textView = this.mBinding.mapplsNearbyPageNextTv;
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar4 = this.mViewModel;
            if (bVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar4 = null;
            }
            a2 = bVar4.a() + 1;
        } else {
            this.mBinding.mapplsNearbyPageNext.setCardBackgroundColor(Color.parseColor("#3A78E7"));
            this.mBinding.mapplsNearbyPageCurrent.setCardBackgroundColor(Color.parseColor(Constants.WHITE));
            this.mBinding.mapplsNearbyPageNextTv.setTextColor(Color.parseColor(Constants.WHITE));
            this.mBinding.mapplsNearbyPageCurrentTv.setTextColor(Color.parseColor("#212121"));
            TextView textView3 = this.mBinding.mapplsNearbyPageCurrentTv;
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar5 = this.mViewModel;
            if (bVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar5 = null;
            }
            textView3.setText(String.valueOf(bVar5.a() - 1));
            textView = this.mBinding.mapplsNearbyPageNextTv;
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar6 = this.mViewModel;
            if (bVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar6 = null;
            }
            a2 = bVar6.a();
        }
        textView.setText(String.valueOf(a2));
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar7 = this.mViewModel;
        if (bVar7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar7 = null;
        }
        NearbyAtlasResponse b = bVar7.b();
        if (((b == null || (pageInfo2 = b.getPageInfo()) == null || (totalPages = pageInfo2.getTotalPages()) == null) ? 0 : totalPages.intValue()) % 2 == 1) {
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar8 = this.mViewModel;
            if (bVar8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar8 = null;
            }
            NearbyAtlasResponse b2 = bVar8.b();
            Integer totalPages2 = (b2 == null || (pageInfo = b2.getPageInfo()) == null) ? null : pageInfo.getTotalPages();
            int intValue = totalPages2 == null ? 0 : totalPages2.intValue();
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar9 = this.mViewModel;
            if (bVar9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                bVar2 = bVar9;
            }
            if (intValue == bVar2.a()) {
                this.mBinding.mapplsNearbyPageNext.setVisibility(8);
                return;
            }
        }
        this.mBinding.mapplsNearbyPageNext.setVisibility(0);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    @NotNull
    public LifecycleRegistry getLifecycle() {
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        Intrinsics.checkNotNull(lifecycleRegistry);
        return lifecycleRegistry;
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
            e eVar = this.mCallback;
            if (eVar != null) {
                Object fromJson = new Gson().fromJson(jsonObject.get("com.mappls.sdk.nearby.plugin.NEARBY_RESULT_DATA").getAsString(), (Class<Object>) NearbyAtlasResult.class);
                Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(jsonObje…yAtlasResult::class.java)");
                eVar.a((NearbyAtlasResult) fromJson);
            }
        }
        return false;
    }

    public final void onCreate(@Nullable Bundle bundle) {
        com.mappls.sdk.nearby.plugin.adapter.c cVar;
        this.isMapInitialised = false;
        if (!(getContext() instanceof FragmentActivity)) {
            throw new RuntimeException("Nearby View is not directly attached with Fragment Activity");
        }
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        this.mViewModel = (com.mappls.sdk.nearby.plugin.viewmodel.b) new ViewModelProvider((FragmentActivity) context).get(com.mappls.sdk.nearby.plugin.viewmodel.b.class);
        this.mContext = getContext();
        if (this.mAdapter == null) {
            this.mAdapter = new com.mappls.sdk.nearby.plugin.adapter.c();
        }
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycleRegistry = lifecycleRegistry;
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        initialiseView();
        this.mBinding.mapplsNearbyResultCategoryRv.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.mBinding.mapplsNearbyResultCategoryRv.setAdapter(this.mAdapter);
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this.mViewModel;
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar2 = null;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        if (bVar.g() != null && (cVar = this.mAdapter) != null) {
            com.mappls.sdk.nearby.plugin.viewmodel.b bVar3 = this.mViewModel;
            if (bVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                bVar2 = bVar3;
            }
            ArrayList<CategoryCode> g = bVar2.g();
            Intrinsics.checkNotNull(g);
            cVar.a(g);
        }
        ViewPager2 viewPager2 = this.mBinding.mapplsNearbyViewPager;
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        viewPager2.setAdapter(new com.mappls.sdk.nearby.plugin.adapter.b((FragmentActivity) context2, this.mViewOption, this));
        if (bundle != null && ((com.mappls.sdk.nearby.plugin.adapter.b) this.mBinding.mapplsNearbyViewPager.getAdapter()) != null) {
            Context context3 = getContext();
            Intrinsics.checkNotNull(context3, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            FragmentManager supportFragmentManager = ((FragmentActivity) context3).getSupportFragmentManager();
            StringBuilder sb = new StringBuilder();
            sb.append('f');
            sb.append(this.mBinding.mapplsNearbyViewPager.getCurrentItem());
            com.mappls.sdk.nearby.plugin.adapter.b.a(this, supportFragmentManager.findFragmentByTag(sb.toString()));
        }
        this.mBinding.mapplsNearbyToolbarBackButton.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.nearby.plugin.view.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NearbyResultView.onCreate$lambda$2(NearbyResultView.this, view);
            }
        });
        this.mBinding.mapplsNearbyViewPager.setUserInputEnabled(false);
        updatePage();
        MapplsNearbyResultViewBinding mapplsNearbyResultViewBinding = this.mBinding;
        new TabLayoutMediator(mapplsNearbyResultViewBinding.mapplsNearbyTabLayout, mapplsNearbyResultViewBinding.mapplsNearbyViewPager, new TabLayoutMediator.TabConfigurationStrategy() { // from class: com.mappls.sdk.nearby.plugin.view.m
            @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
            public final void onConfigureTab(TabLayout.Tab tab, int i) {
                NearbyResultView.onCreate$lambda$3(NearbyResultView.this, tab, i);
            }
        }).attach();
        Boolean showDefaultMap = this.mViewOption.showDefaultMap();
        Intrinsics.checkNotNullExpressionValue(showDefaultMap, "mViewOption.showDefaultMap()");
        if (showDefaultMap.booleanValue() && this.mapView == null) {
            MapView mapView = new MapView(getContext(), MapplsMapOptions.createFromAttributes(getContext()).textureMode(true));
            this.mapView = mapView;
            FrameLayout frameLayout = this.mBinding.mapplsNearbyMapContainer;
            if (frameLayout != null) {
                frameLayout.addView(mapView);
            }
            MapView mapView2 = this.mapView;
            if (mapView2 != null) {
                mapView2.onCreate(bundle);
            }
        }
        MapView mapView3 = this.mapView;
        if (mapView3 != null && !this.isMapInitialised) {
            mapView3.getMapAsync(this);
        }
        CardView cardView = this.mBinding.mapplsNearbyPrev;
        Integer prevButtonBackgroundColor = this.mViewOption.prevButtonBackgroundColor();
        Intrinsics.checkNotNullExpressionValue(prevButtonBackgroundColor, "mViewOption.prevButtonBackgroundColor()");
        cardView.setCardBackgroundColor(prevButtonBackgroundColor.intValue());
        CardView cardView2 = this.mBinding.mapplsNearbyNext;
        Integer nextButtonBackgroundColor = this.mViewOption.nextButtonBackgroundColor();
        Intrinsics.checkNotNullExpressionValue(nextButtonBackgroundColor, "mViewOption.nextButtonBackgroundColor()");
        cardView2.setCardBackgroundColor(nextButtonBackgroundColor.intValue());
        this.mBinding.mapplsNearbyPrev.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.nearby.plugin.view.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NearbyResultView.onCreate$lambda$4(NearbyResultView.this, view);
            }
        });
        this.mBinding.mapplsNearbyNext.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.nearby.plugin.view.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NearbyResultView.onCreate$lambda$5(NearbyResultView.this, view);
            }
        });
        this.mBinding.mapplsNearbyPageCurrent.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.nearby.plugin.view.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NearbyResultView.onCreate$lambda$6(NearbyResultView.this, view);
            }
        });
        this.mBinding.mapplsNearbyPageNext.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.nearby.plugin.view.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NearbyResultView.onCreate$lambda$7(NearbyResultView.this, view);
            }
        });
        subscribeViewModel();
    }

    public final void onDestroy() {
        MapView mapView;
        LocationComponent locationComponent;
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (lifecycleRegistry != null) {
            lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
        }
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this.mViewModel;
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar2 = null;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        bVar.c().removeObserver(this.nearbyObserver);
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar3 = this.mViewModel;
        if (bVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar3 = null;
        }
        bVar3.a((NearbyAtlasResponse) null);
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar4 = this.mViewModel;
        if (bVar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar4 = null;
        }
        boolean z = true;
        bVar4.a(1);
        LocationEngine locationEngine = this.locationEngine;
        if (locationEngine != null) {
            locationEngine.removeLocationUpdates(this);
        }
        com.mappls.sdk.nearby.plugin.util.b bVar5 = this.nearbyMapPlugin;
        if (bVar5 != null) {
            bVar5.a();
        }
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar6 = this.mViewModel;
        if (bVar6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            bVar2 = bVar6;
        }
        bVar2.a(new MutableLiveData<>());
        LocationComponent locationComponent2 = this.locationComponent;
        if (locationComponent2 == null || !locationComponent2.isLocationComponentActivated()) {
            z = false;
        }
        if (z && ((ContextCompat.checkSelfPermission(getContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(getContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) && (locationComponent = this.locationComponent) != null)) {
            locationComponent.setLocationComponentEnabled(false);
        }
        Boolean showDefaultMap = this.mViewOption.showDefaultMap();
        Intrinsics.checkNotNullExpressionValue(showDefaultMap, "mViewOption.showDefaultMap()");
        if (!showDefaultMap.booleanValue() || (mapView = this.mapView) == null) {
            return;
        }
        mapView.onDestroy();
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineCallback
    public void onFailure(@NotNull Exception p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        p0.printStackTrace();
    }

    @Override // com.mappls.sdk.nearby.plugin.fragment.NearbyResultCallback
    public void onLocationClick() {
        boolean z;
        MapplsMap mapplsMap = this.mapplsMap;
        if (mapplsMap != null) {
            if (ContextCompat.checkSelfPermission(getContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(getContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                Location lastKnownLocation = mapplsMap.getLocationComponent().getLastKnownLocation();
                if (lastKnownLocation != null) {
                    mapplsMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude()), 14.0d));
                    z = false;
                } else {
                    z = true;
                }
                this.isZoomOnCurrentLocation = z;
            }
        }
    }

    public final void onLowMemory() {
        MapView mapView;
        Boolean showDefaultMap = this.mViewOption.showDefaultMap();
        Intrinsics.checkNotNullExpressionValue(showDefaultMap, "mViewOption.showDefaultMap()");
        if (!showDefaultMap.booleanValue() || (mapView = this.mapView) == null) {
            return;
        }
        mapView.onLowMemory();
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapError(int i, @Nullable String str) {
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapReady(@NotNull final MapplsMap mapplsMap) {
        Intrinsics.checkNotNullParameter(mapplsMap, "mapplsMap");
        this.mapplsMap = mapplsMap;
        this.isMapInitialised = true;
        UiSettings uiSettings = mapplsMap.getUiSettings();
        if (uiSettings != null) {
            uiSettings.setLogoMargins(0, 0, 0, this.mBinding.mapplsNearbyResultPage.getHeight() + 2);
        }
        mapplsMap.getStyle(new Style.OnStyleLoaded() { // from class: com.mappls.sdk.nearby.plugin.view.o
            @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
            public final void onStyleLoaded(Style style) {
                NearbyResultView.onMapReady$lambda$13$lambda$12(NearbyResultView.this, mapplsMap, style);
            }
        });
    }

    @Override // com.mappls.sdk.nearby.plugin.fragment.NearbyResultCallback
    public void onNearbyResultClick(@NotNull NearbyAtlasResult nearbyAtlasResult) {
        Intrinsics.checkNotNullParameter(nearbyAtlasResult, "nearbyAtlasResult");
        e eVar = this.mCallback;
        if (eVar != null) {
            eVar.a(nearbyAtlasResult);
        }
    }

    public final void onPause() {
        MapView mapView;
        Boolean showDefaultMap = this.mViewOption.showDefaultMap();
        Intrinsics.checkNotNullExpressionValue(showDefaultMap, "mViewOption.showDefaultMap()");
        if (!showDefaultMap.booleanValue() || (mapView = this.mapView) == null) {
            return;
        }
        mapView.onPause();
    }

    public final void onResume() {
        MapView mapView;
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (lifecycleRegistry != null) {
            lifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED);
        }
        Boolean showDefaultMap = this.mViewOption.showDefaultMap();
        Intrinsics.checkNotNullExpressionValue(showDefaultMap, "mViewOption.showDefaultMap()");
        if (!showDefaultMap.booleanValue() || (mapView = this.mapView) == null) {
            return;
        }
        mapView.onResume();
    }

    public final void onSaveInstanceState(@NotNull Bundle outState) {
        MapView mapView;
        Intrinsics.checkNotNullParameter(outState, "outState");
        Boolean showDefaultMap = this.mViewOption.showDefaultMap();
        Intrinsics.checkNotNullExpressionValue(showDefaultMap, "mViewOption.showDefaultMap()");
        if (!showDefaultMap.booleanValue() || (mapView = this.mapView) == null) {
            return;
        }
        mapView.onSaveInstanceState(outState);
    }

    public final void onStart() {
        MapView mapView;
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (lifecycleRegistry != null) {
            lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
        }
        Boolean showDefaultMap = this.mViewOption.showDefaultMap();
        Intrinsics.checkNotNullExpressionValue(showDefaultMap, "mViewOption.showDefaultMap()");
        if (!showDefaultMap.booleanValue() || (mapView = this.mapView) == null) {
            return;
        }
        mapView.onStart();
    }

    public final void onStop() {
        MapView mapView;
        Boolean showDefaultMap = this.mViewOption.showDefaultMap();
        Intrinsics.checkNotNullExpressionValue(showDefaultMap, "mViewOption.showDefaultMap()");
        if (!showDefaultMap.booleanValue() || (mapView = this.mapView) == null) {
            return;
        }
        mapView.onStop();
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineCallback
    public void onSuccess(@Nullable LocationEngineResult locationEngineResult) {
        Location lastLocation = locationEngineResult != null ? locationEngineResult.getLastLocation() : null;
        if (lastLocation == null || !this.isZoomOnCurrentLocation) {
            return;
        }
        MapplsMap mapplsMap = this.mapplsMap;
        if (mapplsMap != null) {
            mapplsMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()), 14.0d));
        }
        this.isZoomOnCurrentLocation = false;
    }

    public final void provideMapView(@NotNull MapView mapView) {
        Intrinsics.checkNotNullParameter(mapView, "mapView");
        this.mapView = mapView;
        if (this.mContext == null || this.isMapInitialised) {
            return;
        }
        mapView.getMapAsync(this);
    }

    public final void setNearbyResultSelectedCategory(@NotNull MapplsNearbyResponse nearbyResponse) {
        Intrinsics.checkNotNullParameter(nearbyResponse, "nearbyResponse");
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar = this.mViewModel;
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar2 = null;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        bVar.a(new ArrayList<>(nearbyResponse.getSelectedCategory()));
        com.mappls.sdk.nearby.plugin.adapter.c cVar = this.mAdapter;
        if (cVar != null) {
            cVar.a(nearbyResponse.getSelectedCategory());
        }
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar3 = this.mViewModel;
        if (bVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar3 = null;
        }
        bVar3.a(nearbyResponse.getRefLocation());
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar4 = this.mViewModel;
        if (bVar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar4 = null;
        }
        bVar4.a(nearbyResponse.getRefMapplsPin());
        com.mappls.sdk.nearby.plugin.viewmodel.b bVar5 = this.mViewModel;
        if (bVar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            bVar2 = bVar5;
        }
        bVar2.a(nearbyResponse.getNearbyAtlasResponse());
        initialiseMap();
    }

    public final void setNearbyResultViewCallback(@NotNull e nearbyResultViewCallback) {
        Intrinsics.checkNotNullParameter(nearbyResultViewCallback, "nearbyResultViewCallback");
        this.mCallback = nearbyResultViewCallback;
    }
}
